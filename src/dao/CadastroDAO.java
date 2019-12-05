/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cadastro;

/**
 *
 * @author Rafael
 */
public class CadastroDAO {
     private final DataBaseMySQL db = new DataBaseMySQL();
     public boolean inserir(Cadastro cadastro) throws SQLException {
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlRetorna = new StringBuilder();
        sql.append("INSERT INTO tb_cadastro(data_cadastro, id_funcionario, id_cliente) ")
                .append(" VALUES(now(),?,?)");
        Connection conn = db.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            stmt.setInt(1, cadastro.getIdFuncionario());
            stmt.setInt(2, cadastro.getIdCliente());
            

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } finally {
            conn.close();
        }
    }
}
