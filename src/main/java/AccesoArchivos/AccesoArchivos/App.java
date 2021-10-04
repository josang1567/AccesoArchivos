package AccesoArchivos.AccesoArchivos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.P_Attack.ExtraDAO;
import models.P_Character.RolDAO;
import models.P_User.UserDAO;
import utils.Conexion;

import java.io.IOException;

import P_Game.SesionDAO;

/**
 * JavaFX App
 */
public class App extends Application {

public void start(Stage stage) throws IOException {
		

		FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
		Parent root = loader.load();
		Scene scene= new Scene(root);
		Stage stage2= new Stage();
		stage2.setScene(scene);
		//Image image= new Image("file:src/main/resources/images/icons/icon_app.jpg");
		//stage2.getIcons().add(image);
		stage2.setTitle("Autenticar Usuario");
		stage2.setResizable(false);;
		stage2.initModality(Modality.APPLICATION_MODAL);
		
		stage2.show();
	};

    public static void main(String[] args) {
        launch();
    }

}