/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CadastroClientesController;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;

/**
 *
 * @author Rafael
 */
public class CadastroClientes extends Application {

    private Funcionario funcionario = new Funcionario();
    private static Stage stage;
    private CadastroClientesController cadClientes;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroClientes.fxml"));
        cadClientes = new CadastroClientesController(funcionario);
        loader.setController(cadClientes);
        Parent root = loader.load();;
        stage = new Stage();
        this.stage = stage;
        stage.setTitle("Cadastro Clientes");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();        
        cadClientes.getListCliente();
        System.out.println("CLientes " + cadClientes.getListCliente());
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastroClientes.stage = stage;
    }

    public ArrayList<Cliente> getCadClientes() {
        return cadClientes.getListCliente();
    }

    public CadastroClientes(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    

}
