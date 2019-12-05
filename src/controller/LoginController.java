
package controller;

import dao.FuncionarioDAO;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Funcionario;
import view.Login;
import view.Principal;


public class LoginController implements Initializable {
    private Funcionario funcionario = new Funcionario();
    private FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    @FXML
    private Label label;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField pswSenha;
    @FXML
    private AnchorPane acCliente;
    @FXML
    private AnchorPane acConteudo;
    
    @FXML
    public void entrar(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        
        funcionario = funcionarioDao.acessar(txtLogin.getText(), pswSenha.getText());       
         if (txtLogin.getText().equals(funcionario.getLogin()) && pswSenha.getText().equals(funcionario.getSenha())) {
            Principal p = new Principal();
            p.setFuncionario(funcionario);
            fechaLogin();
             try {
                p.start(new Stage() );
               
             } catch (Exception ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                 System.out.println("EXCECAO"+ ex.getMessage());
             }
             System.out.println("Heloddd");
        }
         else{
             JOptionPane.showMessageDialog(null,"Login ou senha incorretos digite novamente");
         }
        
    }
    @FXML 
    public void sair (ActionEvent event){
        System.exit(0);
    }
     public String desencriptar(String senha) throws NoSuchAlgorithmException {
        if (senha.length() == 0) {
            return null;
        } else {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(senha.getBytes());
            byte[] hash = md.digest();
            StringBuffer senhaEncrip = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                senhaEncrip.append(Integer.toHexString(hash[i] & 0xff));
            }
            return senhaEncrip.toString();
        }
    }

    public void fechaLogin(){
        Login.getStage().close();
    }
    public void fechaPrincipal(){
        Principal.getStage().close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
