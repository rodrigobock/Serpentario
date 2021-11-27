package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Alimento;
import models.utils.Conection;

public class DAOAlimento {

    public int consultar() {

        try {
            int qtde = 0;

            String sql;
            sql = "SELECT quantidade FROM estoque";

            PreparedStatement stm;
            Connection con = Conection.conectar();

            stm = con.prepareStatement(sql);

            ResultSet rs;
            rs = stm.executeQuery();

            if (rs.next()) {
                int qtdeBD = rs.getInt("quantidade");

                qtde = qtdeBD;
            }

            stm.close();

            return qtde;

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public boolean atualizar(int qtde) {

        try {
            Connection con = Conection.conectar();
            String sql;

            sql = "UPDATE estoque SET quantidade = " + qtde + " WHERE id = 1";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.execute();
            return true;

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
