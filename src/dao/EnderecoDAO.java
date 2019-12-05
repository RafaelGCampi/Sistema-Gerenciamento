
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Endereco;


public class EnderecoDAO {
    private final DataBaseMySQL db = new DataBaseMySQL();
    
    public int inserir(Cliente cliente) throws SQLException {
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO tb_endereco( id_endereco, rua, logradouro, ")
                .append("bairro, cidade, estado, cep, telefone ) VALUES(?,?,?,?,?,?,?,?) ");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
           
            stmt.setInt(1, cliente.getEndereco().getIdEndereco());
            stmt.setString(2, cliente.getEndereco().getRua());
            stmt.setString(3, cliente.getEndereco().getLogradouro());
            stmt.setString(4, cliente.getEndereco().getBairro());
            stmt.setString(5, cliente.getEndereco().getCidade());
            stmt.setString(6, cliente.getEndereco().getEstado());
            stmt.setString(7, cliente.getEndereco().getCep());
            stmt.setString(8, cliente.getEndereco().getTelefone());
            
            stmt.execute();
            
            return cliente.getEndereco().getIdEndereco();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            
            conn.close();
        }
    }
     public Endereco pesquisar(int codigo) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Endereco endereco = new Endereco();
        //ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
        sql.append("SELECT * FROM tb_endereco WHERE id_endereco = ?");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setInt(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                endereco.setIdEndereco(resultado.getInt("id_endereco"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setEstado(resultado.getString("estado"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setTelefone(resultado.getString("telefone"));
                
                
                //listCliente.add(cliente);
            }
            return endereco;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            conn.close();
        }
        return null;   
    } 
      public Endereco buscarEndereco(Cliente cliente) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Cliente clienteResult = new Cliente();
        Endereco endereco = new Endereco();
        
       
        sql.append("SELECT e.* FROM tb_cliente AS c") 
                .append(" INNER JOIN tb_endereco AS e WHERE id_cliente = ? AND e.id_endereco = c.id_endereco");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setInt(1, cliente.getIdCliente());
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                endereco.setIdEndereco(resultado.getInt("id_endereco"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setEstado(resultado.getString("estado"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setTelefone(resultado.getString("telefone"));   
                
            }
            return endereco;
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
        sql.append("DELETE FROM tb_endereco WHERE id_endereco = ?");
        Connection conn = db.getConnection();
        try{
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setInt(1, codigo);
        stmt.execute();
        return true;
        }
        catch (SQLException ex){
        Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            conn.close();
        }
        return false;
    }
     public boolean alterar (Endereco endereco) throws SQLException{
         StringBuilder sql = new StringBuilder();
         sql.append("UPDATE tb_endereco")
                 .append(" SET rua = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, telefone = ? WHERE id_endereco = ?");
         Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareCall(sql.toString());
         stmt.setInt(8,endereco.getIdEndereco());
         try {           
         stmt.setString(1, endereco.getRua());
         stmt.setString(2, endereco.getLogradouro());
         stmt.setString(3, endereco.getBairro());
         stmt.setString(4, endereco.getCidade());
         stmt.setString(5, endereco.getEstado());
         stmt.setString(6, endereco.getCep());
         stmt.setString(7, endereco.getTelefone());
              stmt.executeUpdate();
              return true;
         }
         catch(SQLException ex){
         Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally{
         conn.close();
         }
         return false;
    }
     public int retornaID() throws SQLException {
        StringBuilder sql = new StringBuilder();
        int result;
       
        sql.append("SELECT (max(id_endereco)) AS ")
                .append("id_endereco from tb_endereco");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            result = resultado.getInt("id_endereco");
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
