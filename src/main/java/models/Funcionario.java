package models;

public class Funcionario {

    private Integer id;
    private String nomeCompleto;
    private String usuario;
    private String senha;
    private int tipoUser;

    // Contrutures - Vazio / Sem ID / Com ID
    public Funcionario() {
    }

    public Funcionario(String nomeCompleto, String usuario, String senha, int tipoUser) {
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
        this.senha = senha;
        this.tipoUser = tipoUser;
    }

    public Funcionario(Integer id, String nomeCompleto, String usuario, String senha, int tipoUser) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.usuario = usuario;
        this.senha = senha;
        this.tipoUser = tipoUser;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(int tipoUser) {
        this.tipoUser = tipoUser;
    }

}
