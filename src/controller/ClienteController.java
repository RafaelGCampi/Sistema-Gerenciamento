package controller;

import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.ProcessoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Endereco;
import model.Funcionario;
import model.Processo;
import model.Sistema;
import textformat.TextFormat;
import view.CadastroClientes;
import view.PerfilCliente;

public class ClienteController implements Initializable {

    private ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
    private Cliente clientePerfil = new Cliente();
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();
    private ClienteDAO clienteDao = new ClienteDAO();
    private EnderecoDAO enderecoDao = new EnderecoDAO();
    private Funcionario funcionario = new Funcionario();
    private Processo processo = new Processo();
    private ProcessoDAO processoDao = new ProcessoDAO();
    boolean clienteCadastrar, clienteExcluir;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnBusca;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private TableView<Cliente> tbClientes;
    @FXML
    private TableColumn<Cliente, String> clmNome;
    @FXML
    private TableColumn<Cliente, String> clmCpf;
    @FXML
    private TableColumn<Endereco, String> clmProcesso;
    @FXML
    private TableColumn<Endereco, String> clmObs;
    @FXML
    private TableColumn<Endereco, String> clmDataVeri;
    @FXML
    private TableColumn<Endereco, String> clmSituacao;

    @FXML
    public void cadastrar() throws SQLException {

        CadastroClientes cadastro = new CadastroClientes(funcionario);

        try {
            cadastro.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable();
    }

    @FXML
    public void perfilCliente() throws Exception {

        clientePerfil = tbClientes.getSelectionModel().getSelectedItems().get(0);
        endereco = enderecoDao.buscarEndereco(clientePerfil);
        processo = processoDao.buscarEndereco(clientePerfil);
        clientePerfil.setProcesso(processo);
        clientePerfil.setEndereco(endereco);
        PerfilCliente perfil = new PerfilCliente(funcionario, clientePerfil);
        perfil.setCliente(clientePerfil);
        perfil.start(new Stage());

    }

    @FXML
    public void cancelar() {
        System.exit(0);
    }

    @FXML
    public void excluir() throws SQLException {

        Object[] options = {"Sim", "Não"};
        int i = JOptionPane.showOptionDialog(null,
                "Excluir cliente", "ATENÇÃO",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        if (i == JOptionPane.YES_OPTION) {

            clienteDao.excluir(tbClientes.getSelectionModel().getSelectedItems().get(0).getCpf());
            JOptionPane.showMessageDialog(null, "Cliente excluido");
        } else if (i == JOptionPane.NO_OPTION) {
            return;
        }
    }

    @FXML
    public void atualizaTabela() throws SQLException {
        listCliente = clienteDao.listCliente();
        initTable();
        System.out.println("PASSOU ");

    }

    @FXML
    public void pesquisa() throws SQLException {

        cliente = clienteDao.pesquisar(txtPesquisa.getText());
        //System.out.println("BANCO DE DADOS "+cliente);
        listCliente.removeAll(listCliente);
        listCliente.add(cliente);

        initTable();

    }

    @FXML
    public void tfCpf() {
        TextFormat tf = new TextFormat();
        tf.setMask("###.###.###-##");
        tf.setCaracteresValidos("0123456789");
        tf.setTf(txtPesquisa);
        tf.formatter();
    }

    @FXML
    public void clicarTabela() {
        clientePerfil = tbClientes.getSelectionModel().getSelectedItems().get(0);
        this.btnPerfil.setVisible(true);
        this.btnExcluir.setDisable(false);
    }

    @FXML
    public void tfPesquisar() {
        TextFormat tf = new TextFormat();
        tf.setMask("###.###.###-##");
        tf.setCaracteresValidos("0123456789");
        tf.setTf(txtPesquisa);
        tf.formatter();

    }

    private void initTable() throws SQLException {
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        clmProcesso.setCellValueFactory(new PropertyValueFactory("processoTb"));
        clmObs.setCellValueFactory(new PropertyValueFactory("obsTb"));
        clmDataVeri.setCellValueFactory(new PropertyValueFactory("dataVerificacaoTb"));
        clmSituacao.setCellValueFactory(new PropertyValueFactory("situacaoTb"));
        tbClientes.setItems(atualizarTabela());
    }

    public ObservableList<Cliente> atualizarTabela() throws SQLException {
        //listCliente = clienteDao.listCliente();
        System.out.println("Atualiza " + listCliente);
        return FXCollections.observableArrayList(listCliente);
    }

    public Cliente getClientePerfil() {
        return clientePerfil;

    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public ArrayList<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListCliente(ArrayList<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public ClienteController(Funcionario funcionario) {
        this.funcionario = funcionario;
        verificaAcesso(funcionario.getTipoAcesso());
    }

    public void verificaAcesso(String tipoAcesso) {
        Sistema sistema = new Sistema(tipoAcesso);
        this.clienteExcluir = sistema.getExcluirCliente();
        this.clienteCadastrar = sistema.getCadastroCliente();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnCadastrar.setVisible(clienteCadastrar);
        this.btnExcluir.setVisible(clienteExcluir);
        this.btnPerfil.setVisible(false);
        this.btnExcluir.setDisable(true);
        try {
            atualizaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
