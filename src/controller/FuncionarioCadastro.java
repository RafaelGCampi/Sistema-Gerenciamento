package controller;

import dao.FuncionarioDAO;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import model.Funcionario;
import view.CadastroFuncionario;

public class FuncionarioCadastro implements Initializable {

    private FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private Funcionario funcionario;
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField pswSenha;

    @FXML
    private PasswordField pswSenhaRep;

    @FXML
    private DatePicker dtDataNasc;
    @FXML
    private ComboBox<String> cbTipoAcesso;

    @FXML
    public void cadastrar() throws SQLException, NoSuchAlgorithmException {
        if (!(txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtLogin.getText().isEmpty() || pswSenha.getText().isEmpty()
                || pswSenhaRep.getText().isEmpty())) {

            if (pswSenha.getText().equals(pswSenhaRep.getText())) {
                funcionario = new Funcionario(txtNome.getText(), txtEmail.getText(), dtDataNasc.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), txtLogin.getText(), pswSenha.getText(), cbTipoAcesso.selectionModelProperty().getValue().getSelectedItem());
                System.out.println(cbTipoAcesso.getEditor().getText());
                funcionarioDao.inserir(funcionario);
                JOptionPane.showMessageDialog(null, "Funcionario Cadastrado");
                fecha();
            } else {
                JOptionPane.showMessageDialog(null, "As senhas devem ser iguais");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos os dados devem ser preenchidos. ");
        }
    }

    public void fecha() {
        CadastroFuncionario.getStage().close();
    }

    public String encriptar(String senha) throws NoSuchAlgorithmException {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTipoAcesso.getItems().addAll("Administrador", "Atendente", "Estagiario");

    }

}
