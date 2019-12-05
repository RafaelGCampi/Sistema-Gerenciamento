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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Processo;

/**
 *
 * @author Rafael
 */
public class ProcessoDAO {
    private final DataBaseMySQL db = new DataBaseMySQL();
    
    public boolean inserir(Processo processo) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO tb_processo( processo, situacao, ")
                .append("observacao, data_verificacao ) VALUES(?,?,?,?)");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, processo.getProcesso());
            stmt.setString(2, processo.getSituacao());
            stmt.setString(3, processo.getObservacao());
            stmt.setString(4, processo.getDataVerificacao());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            conn.close();
        }
    }
     public Processo pesquisar(int codigo) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Processo processo = new Processo();
                
        sql.append("SELECT * FROM tb_processo WHERE id_processo = ?");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setInt(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                processo.setIdProcesso(resultado.getInt("id_processo"));
                processo.setProcesso(resultado.getString("processo"));
                processo.setSituacao(resultado.getString("situacao"));
                processo.setObservacao(resultado.getString("observacao"));
                processo.setDataVerificacao(resultado.getString("data_verificacao"));
                //listCliente.add(cliente);
            }
            return processo;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            conn.close();
        }
        return null;   
    } 
     
     public boolean excluir (int codigo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM tb_processo WHERE id_endereco = ?");
        Connection conn = db.getConnection();
        try{
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setInt(1, codigo);
        stmt.execute();
        return true;
        }
        catch (SQLException ex){
        Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            conn.close();
        }
        return false;
    }
     public boolean alterar (Processo processo) throws SQLException{
         StringBuilder sql = new StringBuilder();
         sql.append("UPDATE tb_processo")
                 .append(" SET processo = ?, situacao = ?, observacao = ?, data_verificacao = ? WHERE id_processo = ?");
         Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareCall(sql.toString());
         stmt.setInt(5, processo.getIdProcesso());
         try {    
             stmt.setString(1, processo.getProcesso());
             stmt.setString(2, processo.getSituacao());
             stmt.setString(3, processo.getObservacao());
             stmt.setString(4, processo.getDataVerificacao());
        
              stmt.executeUpdate();
              return true;
         }
         catch(SQLException ex){
         Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
         conn.close();
         }
         return false;
    }
     public Processo buscarEndereco(Cliente cliente) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Cliente clienteResult = new Cliente();
        Processo processo = new Processo();
        
       
        sql.append("SELECT p.* FROM tb_cliente AS c") 
                .append(" INNER JOIN tb_processo AS p WHERE id_cliente = ? AND p.id_processo = c.id_processo");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setInt(1, cliente.getIdCliente());
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                processo.setIdProcesso(resultado.getInt("id_processo"));
                processo.setProcesso(resultado.getString("processo"));
                processo.setSituacao(resultado.getString("situacao"));
                processo.setObservacao(resultado.getString("observacao"));
                processo.setDataVerificacao(resultado.getString("data_verificacao")); 
            }
            return processo;
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally {
            conn.close();
        }
        return null;   
    }
     public int retornaID() throws SQLException {
        StringBuilder sql = new StringBuilder();
        int result;
       
        sql.append("SELECT (max(id_processo)) AS ")
                .append("id_processo from tb_processo");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            result = resultado.getInt("id_processo");
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex.getMessage());
        } finally {
            conn.close();
        }
        return 0;   
    }
}
