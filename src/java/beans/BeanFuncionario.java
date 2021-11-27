package beans;

import DAO.DAOFuncionario;
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
public class BeanFuncionario {

    private int id;
    private String nomeCompleto;
    private String usuario;
    private String senha;
    private int tipoUser;

    private List<Funcionario> lista = new ArrayList<>();

    private Funcionario funcionario = new Funcionario();
    private DAOFuncionario df = new DAOFuncionario();

    // Esses métodos são intermediarios, o valor vem do campo value da pagina JSF e é passado para o DAO
    public void consultarById(int id) {
        funcionario = df.consultarById(id);

        this.nomeCompleto = funcionario.getNomeCompleto();
        this.usuario = funcionario.getUsuario();
        this.senha = funcionario.getSenha();
        this.tipoUser = funcionario.getTipoUser();
    }

    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (funcionario.getNomeCompleto() == null) {
            msg = new FacesMessage("Informe o nome completo!");
            view.addMessage(null, msg);
        }

        if (funcionario.getUsuario() == null) {
            msg = new FacesMessage("Informe o usuario!");
            view.addMessage(null, msg);
        }

        if (msg == null) {
            if (df.salvar(funcionario)) {
                try {
                    funcionario = new Funcionario();
                    msg = new FacesMessage("Funcionario salvo com sucesso");
                    view.addMessage(null, msg);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
                } catch (IOException ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            }
        }
    }

    public void validateAccess() {

        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (funcionario.getUsuario() == null) {
            msg = new FacesMessage("Informe o usuario!");
            view.addMessage(null, msg);
        }

        if (msg == null) {
            if (df.access(funcionario)) {
                try {
                    msg = new FacesMessage("Login realizado com sucesso.");
                    view.addMessage(null, msg);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                msg = new FacesMessage("Usuário ou senha incorretos, tente novamente.");
                view.addMessage(null, msg);
            }
        }
    }

    public void editaFunc(Funcionario funcionario) {

        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (df.editar(funcionario)) {
            try {
                funcionario = new Funcionario();
                msg = new FacesMessage("Funcionario editado com sucesso");
                view.addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
            } catch (IOException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    public void Editar(Funcionario funcionario) {
        try {
            this.funcionario = funcionario;
            FacesContext.getCurrentInstance().getExternalContext().redirect("EditarFuncionario.jsf");
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void Excluir(Funcionario funcionario) {
        try {
            if (df.excluir(funcionario)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
                lista = null;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // Metodo para filtrar a consulta por um usuário com determinado nome.
    public void consultar() {
        lista = new DAOFuncionario().consultar(funcionario.getNomeCompleto());
    }

    // Getters and Setters
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

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public DAOFuncionario getDf() {
        return df;
    }

    public void setDf(DAOFuncionario df) {
        this.df = df;
    }

}
