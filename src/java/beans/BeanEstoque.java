package beans;

import DAO.DAOAlimento;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Alimento;

@ManagedBean
@SessionScoped
public class BeanEstoque {

    private int qtde;
    private int qtdeBD;
//    private Alimento alimento;
//    private DAOAlimento da;

    Alimento alimento = new Alimento();
    DAOAlimento da = new DAOAlimento();
    
    /*
        Metodos intermediarios
    
        - Inserir: Quando recebe uma quantidade nova de alimento para colocar no estoque
        - atualizar: Ao alimentar a serpente é necessário realizar o update da quantidade (se serpente foi alimentada, estoque = qtde - 1)
        - consultar: mostrar a quantidade no estoque ao abrir a pagina de estoque
     */
    
    public void inserir() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        this.consultar();

        qtde = qtdeBD + alimento.getQntd();

        if (da.atualizar(qtde)) {
            try {
                alimento = new Alimento();
                msg = new FacesMessage("Atualizado com sucesso");
                view.addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void atualizar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        this.consultar();

        qtde = qtdeBD - 1;

        if (da.atualizar(qtde)) {
            try {
                alimento = new Alimento();
                msg = new FacesMessage("Atualizado com sucesso");
                view.addMessage(null, msg);
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.jsf");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void consultar() {
        qtdeBD = new DAOAlimento().consultar();
    }

    // Getters and setters
    
    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getQtdeBD() {
        return qtdeBD;
    }

    public void setQtdeBD(int qtdeBD) {
        this.qtdeBD = qtdeBD;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public DAOAlimento getDa() {
        return da;
    }

    public void setDa(DAOAlimento da) {
        this.da = da;
    }

}
