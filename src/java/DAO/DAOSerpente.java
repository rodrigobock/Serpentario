package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Serpente;
import models.utils.Conection;

public class DAOSerpente {

    public Serpente consultarById(int id) {
        ResultSet rs = null;
        Serpente cobra = null;
        try {
            String sql = "select * from serpentes where id = ?";
            Connection con = Conection.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                cobra = new Serpente(
                        rs.getInt("fkfuncionario"),
                        rs.getString("codigo"),
                        rs.getDouble("peso"),
                        rs.getDouble("comprimento"),
                        rs.getString("especie"),
                        rs.getString("alimentacao"),
                        rs.getDouble("pesoalimento"),
                        rs.getString("sexo"),
                        rs.getString("ecdise"),
                        rs.getString("observacvao"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return cobra;
    }

    public boolean salvar(Serpente serpente) {

        try {
            Connection con = Conection.conectar();
            String sql;

            sql = "INSERT INTO serpentes(codigo, peso, comprimento, especie, pesoalimento, alimentacao, sexo, ecdise, observacao, idfuncionario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, serpente.getCodigo());
            stm.setDouble(2, serpente.getPeso());
            stm.setDouble(3, serpente.getComprimento());
            stm.setString(4, serpente.getEspecie());
            stm.setDouble(5, serpente.getPesoAlimento());
            stm.setString(6, serpente.getAlimentacao());
            stm.setString(7, serpente.getSexo());
            stm.setString(8, serpente.getEcdise());
            stm.setString(9, serpente.getObservacao());
            stm.setInt(10, serpente.getIdFuncionario());

            stm.execute();

            return true;

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public boolean excluir(Serpente serpente) {
        try {
            Connection con = Conection.conectar();
            String sql = "DELETE FROM serpentes WHERE id = " + serpente.getIdSerpente();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Serpente> consultar(String filtro) {

        ResultSet rs = null;
        List<Serpente> lista = new ArrayList<>();

        try {
            String sql;

            sql = "select * from serpentes where especie like '%" + filtro + "%'";

            Connection con = Conection.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(new Serpente(
                        rs.getInt("id"),
                        rs.getInt("idfuncionario"),
                        rs.getString("codigo"),
                        rs.getDouble("peso"),
                        rs.getDouble("comprimento"),
                        rs.getString("especie"),
                        rs.getString("alimentacao"),
                        rs.getDouble("pesoalimento"),
                        rs.getString("sexo"),
                        rs.getString("ecdise"),
                        rs.getString("observacao")
                ));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return lista;
    }

    public boolean editar(Serpente serpente) {
        try {

            Connection con = Conection.conectar();
            String sql;

            sql = "UPDATE serpentes SET codigo = '?', peso = ?, comprimento = ?, especie = '?', pesoalimento = ?, alimentacao = ?, sexo = '?', ecdise = '?', observacao = '?', idfuncionario = ? WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, serpente.getCodigo());
            stm.setDouble(2, serpente.getPeso());
            stm.setDouble(3, serpente.getComprimento());
            stm.setString(4, serpente.getEspecie());
            stm.setDouble(5, serpente.getPesoAlimento());
            stm.setString(6, serpente.getAlimentacao());
            stm.setString(7, serpente.getSexo());
            stm.setString(8, serpente.getEcdise());
            stm.setString(9, serpente.getObservacao());
            stm.setInt(10, serpente.getIdFuncionario());

            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
