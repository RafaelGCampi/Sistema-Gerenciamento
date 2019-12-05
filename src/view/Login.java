
package view;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Login extends Application {
    
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        Image icon = new Image("/imagens/Medeiros_Logo.png"); 
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle("Login");
        
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }
public void fecha(){
        this.stage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
