/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CadastroFuncionario extends Application {
    
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroFuncionario.fxml"));
        Parent root = loader.load();
        
        stage.setScene(new Scene(root));
        stage.show();
        setStage(stage);
        stage.setTitle("Cadastro Funcionario");
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    } 
}
