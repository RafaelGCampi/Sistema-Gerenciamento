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
import model.Cliente;
import model.Endereco;
import model.Processo;

/**
 *
 * @author Rafael
 */
public class ClienteDAO {

    private final DataBaseMySQL db = new DataBaseMySQL();

    public boolean inserir(Cliente cliente) throws SQLException {
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlRetorna = new StringBuilder();
        sql.append("INSERT INTO tb_cliente( nome, cpf, ")
                .append("estado_civil, profissao, escolaridade, data_nasc, sexo) VALUES(?,?,?,?,?,?,?)");
        Connection conn = db.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEstadoCivil());
            stmt.setString(4, cliente.getProfissao());
            stmt.setString(5, cliente.getEscolaridade());
            stmt.setString(6, cliente.getDataNasc());
            stmt.setString(7, cliente.getGenero());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } finally {
            conn.close();
        }
    }

    public Cliente pesquisar(String cpf) throws SQLException {
        StringBuilder sql = new StringBuilder();
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        Processo processo = new Processo();
        //ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
        sql.append("SELECT * FROM tb_cliente WHERE cpf = ?");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setString(1, cpf);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setEstadoCivil(resultado.getString("estado_civil"));
                cliente.setProfissao(resultado.getString("profissao"));
                cliente.setEscolaridade(resultado.getString("escolaridade"));
                cliente.setDataNasc(resultado.getString("data_nasc"));
                cliente.setGenero(resultado.getString("sexo"));
                /*cliente.setProcessoTb(resultado.getString("processo"));
                cliente.setSituacaoTb("situacao");
                cliente.setObsTb(resultado.getString("observacao"));
                cliente.setDataVerificacaoTb("data_verificacao");*/

            }
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    public Cliente buscarEndereco(Cliente cliente) throws SQLException {
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
            while (resultado.next()) {
                endereco.setIdEndereco(resultado.getInt("id_endereco"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setEstado(resultado.getString("estado"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setTelefone(resultado.getString("telefone"));
                cliente.setEndereco(endereco);
            }
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Cliente> listCliente() throws SQLException {
        StringBuilder sql = new StringBuilder();
        Cliente cliente;
        ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
        sql.append("SELECT c.*, p.processo, p.situacao, p.observacao, p.data_verificacao")
                .append("  FROM tb_cliente AS c INNER JOIN tb_processo AS p ON p.id_processo = c.id_processo LIMIT 10");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setEstadoCivil(resultado.getString("estado_civil"));
                cliente.setProfissao(resultado.getString("profissao"));
                cliente.setEscolaridade(resultado.getString("escolaridade"));
                cliente.setDataNasc(resultado.getString("data_nasc"));
                cliente.setGenero(resultado.getString("sexo"));
                cliente.setProcessoTb(resultado.getString("processo"));
                cliente.setSituacaoTb(resultado.getString("situacao"));
                cliente.setObsTb(resultado.getString("observacao"));
                cliente.setDataVerificacaoTb(resultado.getString("data_verificacao"));
                listCliente.add(cliente);
            }
            return listCliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return null;
    }

    public boolean excluir(String cpf) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM tb_cliente WHERE cpf = ?  ");

        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareCall(sql.toString());
            stmt.setString(1, cpf);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            conn.close();
        }
        return false;
    }

    public boolean alterar(Cliente cliente) throws SQLException {
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE tb_cliente")
                .append(" SET nome = ?, cpf = ?, estado_civil = ?, profissao = ?, data_nasc = ?, sexo = ? WHERE id_cliente = ?");
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setInt(7, cliente.getIdCliente());
        try {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEstadoCivil());
            stmt.setString(4, cliente.getProfissao());
            stmt.setString(5, cliente.getDataNasc());
            stmt.setString(6, cliente.getGenero());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            conn.close();
        }
        return false;
    }

    public boolean inserirIdProcesso(Cliente cliente, int idProcesso) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tb_cliente")
                .append(" SET id_processo = ? WHERE cpf = ?");
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setString(2, cliente.getCpf());
        try {
            stmt.setInt(1, idProcesso);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return false;
    }

    public boolean inserirIdEndereco(Cliente cliente, int idEndereco) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE tb_cliente")
                .append(" SET id_endereco = ? WHERE cpf = ?");
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareCall(sql.toString());
        stmt.setString(2, cliente.getCpf());
        try {
            stmt.setInt(1, idEndereco);

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } finally {
            conn.close();
        }
        return false;
    }

    public int retornaID() throws SQLException {
        StringBuilder sql = new StringBuilder();
        int result;

        sql.append("SELECT (max(id_cliente)) AS ")
                .append("id_cliente from tb_cliente");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            result = resultado.getInt("id_cliente");
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            conn.close();
        }
        return 0;
    }
}
