/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CadastroDAO;
import dao.ClienteDAO;
import dao.EnderecoDAO;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Cadastro;
import model.Cliente;
import model.Endereco;
import model.Funcionario;
import textformat.TextFormat;
import view.CadastroClientes;
import view.CadastroProcesso;

/**
 *
 * @author Rafael
 */
public class CadastroClientesController {

    public ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
    private Funcionario funcionario = new Funcionario();
    private Cliente cliente;
    private Endereco endereco;
    private Cadastro cadastro;
    private String genero;
    private ClienteDAO clienteDao = new ClienteDAO();
    private EnderecoDAO enderecoDao = new EnderecoDAO();
    private CadastroDAO cadastroDao = new CadastroDAO();
    @FXML
    private AnchorPane acCliente;
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
    private RadioButton rbMasc;
    @FXML
    private RadioButton rbFem;
    @FXML
    private Button btnPerfil;

    @FXML
    public void cadastrar() throws SQLException {
        boolean retornoCli;
        
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
            cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtEstadoCivil.getText(), txtProfissao.getText(), txtEscolaridade.getText(),
                    dtDataNasc.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), genero);

            retornoCli = clienteDao.inserir(cliente);
            //cadastro = new Cadastro(funcionario.getIdFuncionario(), clienteDao.retornaID());
            //cadastroDao.inserir(cadastro);
            if (retornoCli = true) {
                endereco = new Endereco(txtRua.getText(), txtLogradouro.getText(), txtBairro.getText(), txtCidade.getText(),
                        txtEstado.getText(), txtCep.getText(), txtTelefone.getText());
                cliente.setEndereco(endereco);
                enderecoDao.inserir(cliente);
                int retornEnd = enderecoDao.retornaID();
                boolean ret = clienteDao.inserirIdEndereco(cliente, retornEnd);

            }

            Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null,
                    "Cadastrar processo", "ATENÇÃO",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (i == JOptionPane.YES_OPTION) {
                cadastrarProcesso();
            }
            else if (i == JOptionPane.NO_OPTION){
                fechaCadastro();
            }
            //JOptionPane.showMessageDialog(null, "Cadastrado.");
            //fechaCadastro();
            //limpaTela();
        }

    }

    @FXML
    public void alterar() {

    }

    @FXML
    public void cancelar() {

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

    @FXML
    public void fechaCadastro() {
        CadastroClientes.getStage().close();
    }

    public ArrayList<Cliente> getListCliente() {
        return listCliente;
    }

    public void cadastrarProcesso() {

        CadastroProcesso cadProcesso = new CadastroProcesso(cliente);
        try {
            cadProcesso.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void limpaTela() {

        txtNome.clear();
        txtCpf.clear();
        txtEstadoCivil.clear();
        txtProfissao.clear();

        txtEscolaridade.clear();

        dtDataNasc.setValue(null);

        txtRua.clear();

        txtLogradouro.clear();

        txtBairro.clear();

        txtCidade.clear();

        txtEstado.clear();

        txtCep.clear();

        txtTelefone.clear();

        rbMasc.setSelected(false);

        rbFem.setSelected(false);

    }

    public CadastroClientesController(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
   

}
