/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.ProcessoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Endereco;
import model.Funcionario;
import model.Processo;
import model.Sistema;
import textformat.TextFormat;
import view.CadastroProcesso;
import view.PerfilCliente;

/**
 *
 * @author Rafael
 */
public class PerfilClienteController implements Initializable {

    private Cliente cliente = new Cliente();
    private Funcionario funcionario;
    private Cliente clienteAlterar;
    private Endereco enderecoAlterar;
    private Processo processoAlterar;
    private String genero;
    private final ClienteDAO clienteDao = new ClienteDAO();
    private final EnderecoDAO enderecoDao = new EnderecoDAO();
    private final ProcessoDAO processoDao = new ProcessoDAO();
    boolean clienteAlteraVerifica, clienteCadastrar;
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnConcluir;
    @FXML
    private Label lblCliente;
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEstadoCivil;

    @FXML
    private TextField txtProfissao;

    @FXML
    private TextField txtEscolaridade;

    @FXML
    private DatePicker dtDataNasc;

    @FXML
    private RadioButton rbMasc;

    @FXML
    private ToggleGroup rbGenero;

    @FXML
    private RadioButton rbFem;

    @FXML
    private TextField txtProcesso;

    @FXML
    private TextArea txaObs;

    @FXML
    private ComboBox<String> cbSituacao;

    @FXML
    private DatePicker dtDataVerificacao;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtCep;

    @FXML
    private TextField txtTelefone;

    @FXML
    public void concluir() throws SQLException {

        if (rbMasc.isSelected()) {
            genero = "M";
        }
        if (rbFem.isSelected()) {
            genero = "F";
        }
        if (txtCpf.getText().isEmpty() || txtNome.getText().isEmpty() || txtEscolaridade.getText().isEmpty() || txtProfissao.getText().isEmpty()
                || txtEscolaridade.getText().isEmpty() || txtRua.getText().isEmpty() || txtLogradouro.getText().isEmpty()
                || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty() || txtEstado.getText().isEmpty() || txtCep.getText().isEmpty() || txtTelefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos. ");

        } else {
            clienteAlterar = new Cliente(txtNome.getText(), txtCpf.getText(), txtEstadoCivil.getText(), txtProfissao.getText(), txtEscolaridade.getText(),
                    dtDataNasc.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), genero);
            clienteAlterar.setIdCliente(cliente.getIdCliente());
            clienteDao.alterar(clienteAlterar);

            enderecoAlterar = new Endereco(txtRua.getText(), txtLogradouro.getText(), txtBairro.getText(), txtCidade.getText(),
                    txtEstado.getText(), txtCep.getText(), txtTelefone.getText());
            enderecoAlterar.setIdEndereco(cliente.getEndereco().getIdEndereco());
            enderecoDao.alterar(enderecoAlterar);
            
            if (cliente.getProcesso().getIdProcesso()!=0) {
                processoAlterar = new Processo(txtProcesso.getText(), cbSituacao.selectionModelProperty().getValue().getSelectedItem(), txaObs.getText(),
                        dtDataVerificacao.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                processoAlterar.setIdProcesso(cliente.getProcesso().getIdProcesso());
                processoDao.alterar(processoAlterar);
            }
                JOptionPane.showMessageDialog(null, "Alterado ");
                fecha();
            
        }
    }

    @FXML
    public void editar() {
        setandoBotaoCliente(false);
        setandoEndereco(false);
        if (!(cliente.getProcesso().getIdProcesso() == 0)) {
            setandoProcesso(false);
        } else {
            setandoCamposProcesso(true);
        }

    }

    @FXML
    public void endereco() {
        this.txtCidade.setText(cliente.getEndereco().getCidade());
        this.txtRua.setText(cliente.getEndereco().getRua());
        this.txtLogradouro.setText(cliente.getEndereco().getLogradouro());
        this.txtBairro.setText(cliente.getEndereco().getBairro());
        this.txtEstado.setText(cliente.getEndereco().getEstado());
        this.txtCep.setText(cliente.getEndereco().getCep());
        this.txtTelefone.setText(cliente.getEndereco().getTelefone());
        System.out.println("Cliente Pegou " + cliente.getEndereco().getCep());
    }

    @FXML
    public void tfCpf() {
        TextFormat tf = new TextFormat();
        tf.setMask("###.###.###-##");
        tf.setCaracteresValidos("0123456789");
        tf.setTf(txtCpf);
        tf.formatter();
    }

    @FXML
    public void tfTelefone() {
        TextFormat tf = new TextFormat();
        tf.setMask("(##)#####-####");
        tf.setCaracteresValidos("0123456789");
        tf.setTf(txtTelefone);
        tf.formatter();

    }

    @FXML
    public void tfLogradouro() {
        TextFormat tf = new TextFormat();
        tf.setMask("#####");
        tf.setCaracteresValidos("0123456789");
        tf.setTf(txtLogradouro);
        tf.formatter();

    }

    @FXML
    public void tfCep() {
        TextFormat tf = new TextFormat();
        tf.setMask("######-###");
        tf.setCaracteresValidos("0123456789");
        tf.setTf(txtCep);
        tf.formatter();

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        setandoBotaoCliente(true);
        setandoEndereco(true);
        if (!(cliente.getProcesso().getIdProcesso() == 0)) {
            setandoProcesso(true);
        } else {
            setandoCamposProcesso(true);
        }

    }

    public void verificaAcesso(String tipoAcesso) {
        Sistema sistema = new Sistema(tipoAcesso);
        this.clienteAlteraVerifica = sistema.getAlterarCliente();
        this.clienteCadastrar = sistema.getCadastroCliente();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSituacao.getItems().addAll("CONCEDIDO", "EXIGENCIA", "INDEFERIDO", "ANALISE", "CUMPRIDO", "ANDAMENTO", "EXIGENCIA INTERNA");
        verificaAcesso(funcionario.getTipoAcesso());
        this.btnConcluir.setVisible(clienteAlteraVerifica);
        this.btnEditar.setVisible(clienteAlteraVerifica);
        System.out.println("Cliente " + cliente.getNome() + " Funcionario " + funcionario.getNome());
        if (cliente.getProcesso().getIdProcesso()==0) {
            
        
        Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null,
                    "Cadastrar processo", "ATENÇÃO",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (i == JOptionPane.YES_OPTION) {
                if (cliente.getProcesso().getIdProcesso() == 0) {
                CadastroProcesso cadProcesso = new CadastroProcesso(cliente);
                try {
                    cadProcesso.start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(CadastroClientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
            else if (i == JOptionPane.NO_OPTION){
                return;
            }
        }
    }

    private void setandoBotaoCliente(boolean resp) {
        this.txtNome.setText(cliente.getNome());
        this.txtCpf.setText(cliente.getCpf());
        this.txtEstadoCivil.setText(cliente.getEstadoCivil());
        this.txtProfissao.setText(cliente.getProfissao());
        this.txtEscolaridade.setText(cliente.getEscolaridade());
        this.dtDataNasc.setValue(LocalDate.parse(cliente.getDataNasc()));
        if (cliente.getGenero().equals("M")) {
            this.rbMasc.setSelected(true);
        }
        if (cliente.getGenero().equals("F")) {
            this.rbMasc.setSelected(true);
        }

        this.txtNome.setDisable(resp);
        this.txtCpf.setDisable(resp);
        this.txtEstadoCivil.setDisable(resp);
        this.txtProfissao.setDisable(resp);
        this.txtEscolaridade.setDisable(resp);
        this.dtDataNasc.setDisable(resp);
        this.rbMasc.setDisable(resp);
        this.rbFem.setDisable(resp);

    }

    public void setandoEndereco(boolean resp) {
        this.txtCidade.setText(cliente.getEndereco().getCidade());
        this.txtRua.setText(cliente.getEndereco().getRua());
        this.txtLogradouro.setText(cliente.getEndereco().getLogradouro());
        this.txtBairro.setText(cliente.getEndereco().getBairro());
        this.txtEstado.setText(cliente.getEndereco().getEstado());
        this.txtCep.setText(cliente.getEndereco().getCep());
        this.txtTelefone.setText(cliente.getEndereco().getTelefone());

        this.txtCidade.setDisable(resp);
        this.txtRua.setDisable(resp);
        this.txtLogradouro.setDisable(resp);
        this.txtBairro.setDisable(resp);
        this.txtEstado.setDisable(resp);
        this.txtCep.setDisable(resp);
        this.txtTelefone.setDisable(resp);
    }

    private void setandoProcesso(boolean resp) {
        this.txtProcesso.setText(cliente.getProcesso().getProcesso());
        this.cbSituacao.setValue(cliente.getProcesso().getSituacao());
        this.txaObs.setText(cliente.getProcesso().getObservacao());
        this.dtDataVerificacao.setValue(LocalDate.parse(cliente.getProcesso().getDataVerificacao()));

        this.txtProcesso.setDisable(resp);
        this.txaObs.setDisable(resp);
        this.dtDataVerificacao.setDisable(resp);
        this.cbSituacao.setDisable(resp);
    }

    private void setandoCamposProcesso(boolean resp) {
        this.txtProcesso.setDisable(resp);
        this.txaObs.setDisable(resp);
        this.dtDataVerificacao.setDisable(resp);
        this.cbSituacao.setDisable(resp);
    }

    private void fecha() {
        PerfilCliente.getStage().close();

    }

    public PerfilClienteController(Funcionario funcionario, Cliente cliente) {
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

}
