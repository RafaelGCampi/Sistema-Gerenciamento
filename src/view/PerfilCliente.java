/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PerfilClienteController;
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
public class PerfilCliente extends Application { 
    public Cliente cliente = new Cliente();
    private PerfilClienteController pCliente ;
    private Funcionario funcionario = new Funcionario();
    static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PerfilCliente.fxml"));
        pCliente = new PerfilClienteController(funcionario, cliente);
        loader.setController(pCliente);
        Parent root = loader.load();
        
        pCliente.setCliente(cliente);
        stage.setTitle("Perfil Cliente");
        stage.setScene(new Scene(root));
        stage.show();
        setStage(stage);
        

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PerfilCliente(Funcionario funcionario, Cliente cliente ) {
        this.funcionario = funcionario;
        this.cliente = cliente;
    } 
    
}