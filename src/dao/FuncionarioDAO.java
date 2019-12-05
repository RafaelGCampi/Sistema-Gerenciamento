/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Funcionario;

/**
 *
 * @author Rafael
 */
public class FuncionarioDAO {
    private final DataBaseMySQL db = new DataBaseMySQL();

    public boolean inserir(Funcionario funcionario) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tb_funcionario( nome, login, ")
                .append("senha, email, data_nasc, tipo_acesso) VALUES(?,?,?,?,?,?)");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getLogin());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getDataNasc()); 
            stmt.setString(6, funcionario.getTipoAcesso()); 
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } finally {
            conn.close();
        }
    }
     public ArrayList<Funcionario> listaFuncionario() throws SQLException {
        StringBuilder sql = new StringBuilder();
        Funcionario funcionario;
        ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();
        sql.append("SELECT * FROM tb_funcionario");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                funcionario = new Funcionario();
                funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setDataNasc("data_nasc");
                funcionario.setTipoAcesso(resultado.getString("tipo_acesso"));
                listFuncionario.add(funcionario);
            }
            return listFuncionario;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally {
            conn.close();
        }
        return null;   
    }  
     public boolean excluir (int codigo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM tb_funcionario WHERE id_funcionario = ?");
        Connection conn = db.getConnection();
        try{
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setInt(1, codigo);
        stmt.execute();
        return true;
        }
        catch (SQLException ex){
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        finally{
            conn.close();
        }
        return false;
    }
     public boolean alterar (Funcionario funcionario) throws SQLException{
         StringBuilder sql = new StringBuilder();
         sql.append("UPDATE tb_funcionario")
                 .append(" SET nome = ?, login = ?, senha = ?, email = ?, data_nasc = ?, tipo_acesso = ? WHERE id_funcionario = ?");
         Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareCall(sql.toString());
         stmt.setInt(7,funcionario.getIdFuncionario());
         try {     
             stmt.setString(1, funcionario.getNome());
             stmt.setString(2, funcionario.getLogin());
             stmt.setString(3, funcionario.getSenha());
             stmt.setString(4, funcionario.getEmail());
             stmt.setString(5, funcionario.getDataNasc());  
             stmt.setString(6, funcionario.getTipoAcesso());
              stmt.executeUpdate();
              return true;
         }
         catch(SQLException ex){
         Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.getMessage());
         }
         finally{
         conn.close();
         }
         return false;
     }
      public Funcionario acessar(String login, String senha) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Funcionario funcionario = new Funcionario();
        sql.append("SELECT * FROM tb_funcionario WHERE login = ? AND senha= ?");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                
                funcionario.setIdFuncionario(resultado.getInt("id_funcionario"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setEmail(resultado.getString("email"));
                funcionario.setDataNasc("data_nasc");
                funcionario.setTipoAcesso(resultado.getString("tipo_acesso"));
                
            }
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally {
            conn.close();
        }
        return null;   
    }
}
