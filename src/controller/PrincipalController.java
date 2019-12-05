package controller;

import com.sun.glass.ui.Cursor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Funcionario;
import model.Sistema;
import view.CadastroClientes;
import view.Login;
import view.Principal;

public class PrincipalController implements Initializable {

    private Funcionario funcionario = new Funcionario();
    private String tipoAcesso;
    boolean clienteAcesso;
    boolean funcionarioAcesso;
    Sistema sistema;
    ClienteController clienteControl;
    FuncionarioController funcionarioControl;
    @FXML
    private Label lblNome;
    @FXML
    private AnchorPane acConteudo;
    @FXML
    private Button btnFuncionario;
    @FXML
    private Button btnCliente;

    @FXML
    public void principal() throws IOException {

        try {
            URL url = getClass().getResource("/view/TelaPrincipal.fxml");
            AnchorPane a = FXMLLoader.load(url);
            acConteudo.getChildren().setAll(a);
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        //lblNome.setText(funcionario.getNome());

    }

    @FXML
    public void cliente() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Cliente.fxml"));

        loader.setController(clienteControl = new ClienteController(funcionario));

        AnchorPane a = loader.load();

        acConteudo.getChildren().setAll(a);
    }

    @FXML
    public void funcionario() throws IOException {
        //URL url = getClass().getResource("/view/Funcionario.fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Funcionario.fxml"));
        loader.setController(funcionarioControl = new FuncionarioController(funcionario));
        AnchorPane a = loader.load();
        acConteudo.getChildren().setAll(a);
    }

    @FXML
    public void sairConta() throws Exception {
        Object[] options = {"Sim", "Não"};
        int i = JOptionPane.showOptionDialog(null,
                "Sair da conta", "ATENÇÃO",
                JOptionPane.YES_NO_OPTION, JOptionPane.OK_CANCEL_OPTION, null,
                options, options[0]);
        if (i == JOptionPane.YES_OPTION) {
            fecha();
            Login login = new Login();
            login.start(new Stage());
        } else if (i == JOptionPane.NO_OPTION) {
            return;
        }

    }

    @FXML
    public void sair() {
        System.exit(0);
    }

    private void fecha() {
        Principal.getStage().close();

   /*CadastroClientes.getStage().close();
    CadastroFuncionario.getStage().close();
    CadastroProcesso.getStage().close();
    PerfilCliente.getStage().close();*/
    }

    public void verificaAcesso(String tipoAcesso) {
        Sistema sistema = new Sistema(tipoAcesso);
        this.clienteAcesso = sistema.getCliente();
        this.funcionarioAcesso = sistema.getFuncionario();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    /*public PrincipalController(String tipoAcesso) {
        Sistema sistema = new Sistema(tipoAcesso);
        this.btnFuncionario.setVisible(sistema.getCliente()); 
        this.btnCliente.setVisible(sistema.getFuncionario());
    }*/
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        System.out.println("NOME SEr" + funcionario.getNome());
        //this.tipoAcesso = funcionario.getTipoAcesso();
        verificaAcesso(funcionario.getTipoAcesso());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Entrou Principal " + clienteAcesso + " Funcionario " + funcionarioAcesso);
        lblNome.setText(funcionario.getNome());
        btnCliente.setVisible(this.clienteAcesso);
        btnFuncionario.setDisable(!(this.funcionarioAcesso));
    }

}
