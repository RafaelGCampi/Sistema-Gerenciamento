/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javafx.scene.control.Label;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Funcionario;
import view.CadastroFuncionario;

/**
 *
 * @author Rafael
 */
public class FuncionarioController {

    private ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();
    private final FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private Funcionario funcionario = new Funcionario();
    private Funcionario funcionarioGuarda = new Funcionario();
    @FXML
    private Label lblNome;
    @FXML
    private TableView<Funcionario> tbFuncionario;

    @FXML
    private TableColumn<Funcionario, String> clmLogin;

    @FXML
    private TableColumn<Funcionario, String> clmNome;

    @FXML
    private TableColumn<Funcionario, String> clmTipoAcesso;

    @FXML
    private TableColumn<Funcionario, String> clmEmail;

    @FXML
    public void cadastrar() {
        CadastroFuncionario cadastro = new CadastroFuncionario();

        try {
            cadastro.start(new Stage());

        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void atualizaTabela() throws SQLException {
        listFuncionario = funcionarioDao.listaFuncionario();
        initTable();
        System.out.println("PASSOU ");

    }

    @FXML
    public void clicarTabela() {
        funcionarioGuarda = tbFuncionario.getSelectionModel().getSelectedItems().get(0);

        System.out.println("Dados " + tbFuncionario.getSelectionModel().getSelectedItems().get(0));

    }

    @FXML
    public void excluir() throws SQLException {
        if (funcionario.getIdFuncionario() == tbFuncionario.getSelectionModel().getSelectedItems().get(0).getIdFuncionario()) {
            JOptionPane.showMessageDialog(null, "Funcionario está logado ");
        } else {
            Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null,
                    "Excluir cliente", "ATENÇÃO",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (i == JOptionPane.YES_OPTION) {
                funcionarioDao.excluir(tbFuncionario.getSelectionModel().getSelectedItems().get(0).getIdFuncionario());
                JOptionPane.showMessageDialog(null, "Funcionario excluido");
            } else if (i == JOptionPane.NO_OPTION) {
                return;
            }
        }
    }

    private void initTable() throws SQLException {
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmLogin.setCellValueFactory(new PropertyValueFactory("login"));
        clmTipoAcesso.setCellValueFactory(new PropertyValueFactory("tipoAcesso"));
        clmEmail.setCellValueFactory(new PropertyValueFactory("email"));

        tbFuncionario.setItems(atualizarTabela());
    }

    

    public ObservableList<Funcionario> atualizarTabela() throws SQLException {
        //listCliente = clienteDao.listCliente();
        System.out.println("Atualiza " + listFuncionario);
        return FXCollections.observableArrayList(listFuncionario);
    }

    public FuncionarioController(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
