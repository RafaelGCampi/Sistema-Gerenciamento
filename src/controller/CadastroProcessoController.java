package controller;

import dao.ClienteDAO;
import dao.ProcessoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Processo;

public class CadastroProcessoController implements Initializable {
    private Cliente cliente = new Cliente();
    private ProcessoDAO processoDao = new ProcessoDAO();
    private ClienteDAO clienteDao = new ClienteDAO();
    private Processo processo;
    @FXML
    private TextField txtProcesso;

    @FXML
    private TextArea txaObs;

    @FXML
    private DatePicker dtVerificacao;

    @FXML
    private ComboBox<String> cbSituacao;

    public CadastroProcessoController(Cliente cliente) {
        this.cliente = cliente;
    }
    @FXML 
    public void cadastro() throws SQLException{
        if(txtProcesso.getText().isEmpty() || cbSituacao.getValue().isEmpty() || txaObs.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Todos os dados devem ser preenchidos");
        }
        else{
        processo = new Processo(txtProcesso.getText(),cbSituacao.selectionModelProperty().getValue().getSelectedItem(), txaObs.getText(), dtVerificacao.getValue().toString());
        processoDao.inserir(processo);
        int result = processoDao.retornaID();
        boolean ret = clienteDao.inserirIdProcesso(cliente, result);
        JOptionPane.showMessageDialog(null, "Cadastrou ");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cbSituacao.getItems().addAll("CONCEDIDO","EXIGENCIA","INDEFERIDO","ANALISE","CUMPRIDO","ANDAMENTO","EXIGENCIA INTERNA");
    }
}
