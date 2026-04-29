package models.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            DbConfig cfg = readConfig();
            return DriverManager.getConnection(cfg.jdbcUrl, cfg.user, cfg.password);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static DbConfig readConfig() {
        String url = getEnv("DATABASE_URL");
        String user = getEnv("DATABASE_USER");
        String password = getEnv("DATABASE_PASSWORD");

        if (url == null || url.isEmpty()) {
            throw new IllegalStateException(
                "Variavel de ambiente DATABASE_URL nao definida. "
                + "Defina como jdbc:postgresql://host:porta/banco ou postgres://user:senha@host:porta/banco"
            );
        }

        if (url.startsWith("postgres://") || url.startsWith("postgresql://")) {
            return parsePostgresUrl(url, user, password);
        }

        if (!url.startsWith("jdbc:")) {
            throw new IllegalStateException("DATABASE_URL invalida: " + url);
        }

        if ((user == null || password == null) && !url.contains("user=")) {
            throw new IllegalStateException(
                "DATABASE_URL no formato JDBC requer DATABASE_USER e DATABASE_PASSWORD, "
                + "ou os parametros user/password embutidos na URL"
            );
        }

        return new DbConfig(ensureSsl(url), user, password);
    }

    private static DbConfig parsePostgresUrl(String raw, String envUser, String envPassword) {
        try {
            URI uri = new URI(raw);
            String host = uri.getHost();
            int port = uri.getPort() == -1 ? 5432 : uri.getPort();
            String db = uri.getPath() == null ? "" : uri.getPath().replaceFirst("^/", "");

            String user = envUser;
            String password = envPassword;
            String userInfo = uri.getUserInfo();
            if (userInfo != null) {
                int sep = userInfo.indexOf(':');
                if (sep >= 0) {
                    if (user == null) user = userInfo.substring(0, sep);
                    if (password == null) password = userInfo.substring(sep + 1);
                } else if (user == null) {
                    user = userInfo;
                }
            }

            String jdbc = "jdbc:postgresql://" + host + ":" + port + "/" + db;
            String query = uri.getQuery();
            if (query != null && !query.isEmpty()) {
                jdbc += "?" + query;
            }
            return new DbConfig(ensureSsl(jdbc), user, password);
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("DATABASE_URL invalida: " + raw, ex);
        }
    }

    private static String ensureSsl(String jdbcUrl) {
        if (jdbcUrl.contains("sslmode=")) {
            return jdbcUrl;
        }
        return jdbcUrl + (jdbcUrl.contains("?") ? "&" : "?") + "sslmode=require";
    }

    private static String getEnv(String name) {
        String v = System.getenv(name);
        if (v == null || v.isEmpty()) {
            v = System.getProperty(name);
        }
        return v;
    }

    private static class DbConfig {
        final String jdbcUrl;
        final String user;
        final String password;

        DbConfig(String jdbcUrl, String user, String password) {
            this.jdbcUrl = jdbcUrl;
            this.user = user;
            this.password = password;
        }
    }
}
