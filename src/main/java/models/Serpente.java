package models;

public class Serpente {

    private int idSerpente;
    private Funcionario fkFuncionario;
    private int idFuncionario;
    private String codigo;
    private double peso;
    private Double comprimento;
    private String especie;
    private String alimentacao;
    private double pesoAlimento;
    private String sexo;
    private String ecdise;
    private String observacao;

    // Construtores
    public Serpente() {
    }

    public Serpente(int idFuncionario, String codigo, double peso, Double comprimento, String especie, String alimentacao, double pesoAlimento, String sexo, String ecdise, String observacao) {
        this.idFuncionario = idFuncionario;
        this.codigo = codigo;
        this.peso = peso;
        this.comprimento = comprimento;
        this.especie = especie;
        this.alimentacao = alimentacao;
        this.pesoAlimento = pesoAlimento;
        this.sexo = sexo;
        this.ecdise = ecdise;
        this.observacao = observacao;
    }

    public Serpente(int idSerpente, int idFuncionario, String codigo, double peso, Double comprimento, String especie, String alimentacao, double pesoAlimento, String sexo, String ecdise, String observacao) {
        this.idSerpente = idSerpente;
        this.idFuncionario = idFuncionario;
        this.codigo = codigo;
        this.peso = peso;
        this.comprimento = comprimento;
        this.especie = especie;
        this.alimentacao = alimentacao;
        this.pesoAlimento = pesoAlimento;
        this.sexo = sexo;
        this.ecdise = ecdise;
        this.observacao = observacao;
    }

    // Getters and Setters
    public int getIdSerpente() {
        return idSerpente;
    }

    public void setIdSerpente(int idSerpente) {
        this.idSerpente = idSerpente;
    }

    public Funcionario getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Funcionario fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public double getPesoAlimento() {
        return pesoAlimento;
    }

    public void setPesoAlimento(double pesoAlimento) {
        this.pesoAlimento = pesoAlimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEcdise() {
        return ecdise;
    }

    public void setEcdise(String ecdise) {
        this.ecdise = ecdise;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

}
