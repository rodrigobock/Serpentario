package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Funcionario;
import models.utils.Conection;

public class DAOFuncionario {

    public Funcionario consultarById(int id) {
        ResultSet rs = null;
        Funcionario pessoa = null;
        try {
            String sql = "select * from funcionario where id = ? ";
            Connection con = Conection.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                pessoa = new Funcionario(
                        rs.getString("nomecompleto"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getInt("tipouser"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return pessoa;
    }

    public boolean salvar(Funcionario funcionario) {
        try {
            Connection con = Conection.conectar();
            String sql;

            sql = "INSERT INTO funcionario(nomecompleto, usuario, senha, tipouser) VALUES (?, ?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, funcionario.getNomeCompleto());
            stm.setString(2, funcionario.getUsuario());
            stm.setString(3, funcionario.getSenha());
            stm.setInt(4, funcionario.getTipoUser());
            stm.execute();
            return true;

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public boolean editar(Funcionario funcionario) {
        try {

            Connection con = Conection.conectar();
            String sql;

            sql = "UPDATE funcionario SET nomecompleto = '" + funcionario.getNomeCompleto() + "' , usuario= '" + funcionario.getUsuario()
                    + "' , senha= '" + funcionario.getSenha() + "' , tipouser= " + funcionario.getTipoUser() + " WHERE id = " + funcionario.getId();

            PreparedStatement stm = con.prepareStatement(sql);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public boolean excluir(Funcionario funcionario) {
        try {
            Connection con = Conection.conectar();
            String sql = "DELETE FROM funcionario WHERE id = " + funcionario.getId();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> consultar(String filtro) {

        ResultSet rs = null;
        List<Funcionario> lista = new ArrayList<>();

        try {

            String sql;

            sql = "select * from funcionario where nomecompleto like '%" + filtro + "%'";

            Connection con = Conection.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nomecompleto"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getInt("tipouser")
                ));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return lista;
    }

    public List<Funcionario> todosFuncionarios() {

        ResultSet rs = null;
        List<Funcionario> lista = new ArrayList<>();

        try {

            String sql;

            sql = "select * from funcionario";

            Connection con = Conection.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nomecompleto"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getInt("tipouser")
                ));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return lista;
    }

    public boolean access(Funcionario funcionario) {

        try {

            boolean autenticado = false;
            String sql;
            sql = "select usuario, senha from funcionario where usuario = ? and senha = ?";
            PreparedStatement ps;

            Connection con = Conection.conectar();

            ps = con.prepareStatement(sql);
            ps.setString(1, funcionario.getUsuario());
            ps.setString(2, funcionario.getSenha());

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {
                String loginBanco = rs.getString("usuario");
                String senhaBanco = rs.getString("senha");

                autenticado = true;
            }

            ps.close();

            return autenticado;

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public int qtdeFunc() {
        try {

            int quantidade = 0;
            
            String sql;
            sql = "SELECT COUNT(*) as qtde FROM funcionario";
            PreparedStatement ps;

            Connection con = Conection.conectar();

            ps = con.prepareStatement(sql);

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {
                int qtdeFunc = rs.getInt("qtde");

                quantidade = qtdeFunc;
            }

            ps.close();

            return quantidade;

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
