/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CadastroProcessoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;

/**
 *
 * @author Rafael
 */
public class CadastroProcesso extends Application {
    private Cliente cliente;
    private static Stage stage;
    private CadastroProcessoController cadProcesso = new CadastroProcessoController(cliente);
    @Override
    public void start(Stage stage) throws Exception {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroProcesso.fxml"));
        loader.setController(cadProcesso);
        Parent root = loader.load();
        cadProcesso.setCliente(cliente);
        stage.setScene(new Scene(root));
        stage.show();
        setStage(stage);
        stage.setTitle("Cadastro Processo");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public CadastroProcesso(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    } 
}
