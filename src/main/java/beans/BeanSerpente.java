package beans;

import DAO.DAOFuncionario;
import DAO.DAOSerpente;
import models.Serpente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Funcionario;

@ManagedBean
@SessionScoped
public class BeanSerpente {

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
    private int countSerpente;

    private List<Serpente> lista = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();

    private Serpente serpente = new Serpente();
    private DAOSerpente ds = new DAOSerpente();
    private BeanEstoque be = new BeanEstoque();

    // Esses métodos são intermediarios, o valor vem do campo value da pagina JSF e é passado para o DAO
    public void consultarById(int id) {
        serpente = ds.consultarById(id);

        this.idSerpente = serpente.getIdSerpente();
        this.fkFuncionario = serpente.getFkFuncionario();
        this.idFuncionario = serpente.getIdFuncionario();
        this.codigo = serpente.getCodigo();
        this.peso = serpente.getPeso();
        this.comprimento = serpente.getComprimento();
        this.especie = serpente.getEspecie();
        this.alimentacao = serpente.getAlimentacao();
        this.pesoAlimento = serpente.getPesoAlimento();
    }

    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (serpente.getCodigo() == null || serpente.getCodigo() == "") {
            msg = new FacesMessage("Informe o codigo da serpente!");
            view.addMessage(null, msg);
        }

        if (serpente.getEspecie() == null || serpente.getEspecie() == "") {
            msg = new FacesMessage("Informe a espécie da serpente!");
            view.addMessage(null, msg);
        }

        if (serpente.getAlimentacao().equals("Sim")) {
            be.atualizar();
        }

        if (msg == null) {

            if (ds.salvar(serpente)) {

                try {
                    serpente = new Serpente();
                    msg = new FacesMessage("Serpente salva com sucesso");
                    view.addMessage(null, msg);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                System.out.println("ERROOOOO");
            }
        }
    }

    public void Excluir(Serpente serpente) {
        try {
            if (ds.excluir(serpente)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
                lista = null;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void Editar(Serpente serpente) {
        try {
            this.serpente = serpente;
            FacesContext.getCurrentInstance().getExternalContext().redirect("EditarSerpente.jsf");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void editarSerp(Serpente serpente) {

        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;
/*
        if (serpente.getAlimentacao().equals("Sim")) {
            be.atualizar();
        }
*/
        if (ds.editar(serpente)) {
            try {
                serpente = new Serpente();
                msg = new FacesMessage("Serpente editada com sucesso");
                view.addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Faz uma lista das serpentes cadastradas
    public void consultar() {
        lista = new DAOSerpente().consultar(serpente.getEspecie());
    }

    // Faz uma lista dos funcionários cadastrados
    public void consultarFuncionarios() {
        funcionarios = new DAOFuncionario().todosFuncionarios();
    }

    public void countSerp() {
        countSerpente = new DAOSerpente().qtdeSerpente();
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

    public List<Serpente> getLista() {
        return lista;
    }

    public void setLista(List<Serpente> lista) {
        this.lista = lista;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Serpente getSerpente() {
        return serpente;
    }

    public void setSerpente(Serpente serpente) {
        this.serpente = serpente;
    }

    public DAOSerpente getDs() {
        return ds;
    }

    public void setDs(DAOSerpente ds) {
        this.ds = ds;
    }

    public BeanEstoque getBe() {
        return be;
    }

    public void setBe(BeanEstoque be) {
        this.be = be;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getCountSerpente() {
        return countSerpente;
    }

    public void setCountSerpente(int countSerpente) {
        this.countSerpente = countSerpente;
    }

}
