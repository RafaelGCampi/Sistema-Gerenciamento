/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PrincipalController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Funcionario;

/**
 *
 * @author Rafael
 */
public class Principal extends Application {
    private Funcionario funcionario = new Funcionario();
    private PrincipalController pController = new PrincipalController();
    static Stage stage;
     
    @Override
    public void start(Stage stage) throws Exception {
      try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        loader.setController(pController);  
        pController.setFuncionario(funcionario);
        Parent root = loader.load();       
        stage = new Stage();
        Principal.stage = stage; 
        stage.setScene(new Scene(root));
        stage.setTitle("Principal");
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit() );
      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }
        
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public void fecha(){
        this.stage.close();
    }
}
