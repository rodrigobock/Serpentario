/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Rodrigo
 */
public class Alimento {

    private int ID;
    private int qntd;

    // contrutor
    public Alimento() {
    }

    public Alimento(int qntd) {
        this.qntd = qntd;
    }

    public Alimento(int ID, int qntd) {
        this.ID = ID;
        this.qntd = qntd;
    }

    // getters and setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

}
