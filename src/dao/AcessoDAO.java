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
import model.Acesso;


/**
 *
 * @author Rafael
 */
public class AcessoDAO {
    private final DataBaseMySQL db = new DataBaseMySQL();

    public boolean inserir(Acesso acesso) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tb_acesso( tipo_acesso, data_acesso, ")
                .append("id_funcionario) VALUES(?,?,?)");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1, acesso.getTipoAcesso());
            stmt.setString(2, acesso.getDataAcesso());
            stmt.setInt(3, acesso.getIdFuncionario());
            
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } finally {
            conn.close();
        }
    }
     public Acesso pesquisar(String cpf) throws SQLException {
        StringBuilder sql = new StringBuilder();
        //Cliente cliente = new Cliente();
        //ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
        sql.append("SELECT * FROM tb_cliente WHERE cpf = ?");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
               
                
                //listCliente.add(cliente);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally {
            conn.close();
        }
        return null;   
    } 
 
     /*
     public boolean excluir (String cpf) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM tb_cliente WHERE cpf= ?");
        Connection conn = db.getConnection();
        try{
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setString(1, cpf);
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
     public boolean alterar (Cliente cliente) throws SQLException{
         StringBuilder sql = new StringBuilder();
         sql.append("UPDATE tb_cliente")
                 .append("SET nome = ?, cpf = ?, estado_civil = ?, profissao = ?, data_nasc = ?, sexo = ? WHERE id_cliente = ?");
         Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareCall(sql.toString());
         stmt.setInt(7,cliente.getIdCliente());
         try {           
         stmt.setString(1, cliente.getNome());
         stmt.setString(2, cliente.getCpf());
         stmt.setString(3, cliente.getEstadoCivil());
         stmt.setString(4, cliente.getProfissao());
         stmt.setString(5, cliente.getDataNasc());
         stmt.setString(6, cliente.getGenero());
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
     }*/
}