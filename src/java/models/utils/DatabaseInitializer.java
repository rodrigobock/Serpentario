package models.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Cria as tabelas (e dados iniciais) no banco quando a aplicação sobe,
 * caso ainda não existam. Idempotente — pode rodar a cada deploy.
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(DatabaseInitializer.class.getName());

    private static final String[] DDL_STATEMENTS = {
        "CREATE TABLE IF NOT EXISTS funcionario ("
            + "id SERIAL PRIMARY KEY,"
            + "nomecompleto VARCHAR(255) NOT NULL,"
            + "usuario VARCHAR(100) NOT NULL UNIQUE,"
            + "senha VARCHAR(255) NOT NULL,"
            + "tipouser INTEGER NOT NULL DEFAULT 0"
            + ")",

        "CREATE TABLE IF NOT EXISTS serpentes ("
            + "id SERIAL PRIMARY KEY,"
            + "codigo VARCHAR(50),"
            + "peso DOUBLE PRECISION,"
            + "comprimento DOUBLE PRECISION,"
            + "especie VARCHAR(255),"
            + "alimentacao VARCHAR(255),"
            + "pesoalimento DOUBLE PRECISION,"
            + "sexo VARCHAR(20),"
            + "ecdise VARCHAR(50),"
            + "observacao TEXT,"
            + "idfuncionario INTEGER REFERENCES funcionario(id) ON DELETE SET NULL"
            + ")",

        "CREATE TABLE IF NOT EXISTS estoque ("
            + "id INTEGER PRIMARY KEY,"
            + "quantidade INTEGER NOT NULL DEFAULT 0"
            + ")",

        "INSERT INTO estoque (id, quantidade) VALUES (1, 0) ON CONFLICT (id) DO NOTHING",

        "INSERT INTO funcionario (nomecompleto, usuario, senha, tipouser) "
            + "SELECT 'Administrador', 'admin', 'admin', 1 "
            + "WHERE NOT EXISTS (SELECT 1 FROM funcionario)"
    };

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Inicializando schema do banco...");
        try (Connection con = Conection.conectar();
             Statement stm = con.createStatement()) {
            for (String ddl : DDL_STATEMENTS) {
                stm.execute(ddl);
            }
            LOG.info("Schema do banco verificado/criado com sucesso.");
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Falha ao inicializar o banco", ex);
            throw new RuntimeException("Falha ao inicializar o banco", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
