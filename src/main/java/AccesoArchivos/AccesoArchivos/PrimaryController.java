package AccesoArchivos.AccesoArchivos;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrimaryController { //login
	UserList ul= null;
	User user=null;
	
	@FXML
	protected Button btn_login;
	@FXML
    protected TextField txt_name;
    @FXML
    protected PasswordField txt_password;
	@FXML
	private void login() throws IOException, JAXBException {
		boolean login_register=false;
		
		if(!txt_name.getText().matches("")
			&&!txt_password.getText().matches("")) { //campos escritos
			
			//cargo los usuarios...
			ul=UserList.getMiRepositorioU();
			ul.charge();
			
			boolean enter=false;
			
			for(int i=0; i<ul.getUsers().size()&&!enter;i++) {
				if(ul.getUsers().get(i).getName().matches(txt_name.getText())) { //existe un user con este nombre
					user=ul.getUsers().get(i);
					enter=true;
				}
			}
			
			if(user!=null) {
				if(user.getPassword().matches(txt_password.getText())&&!user.isOnline()) {
					login_register=true;
					user.setOnline(true);
					ul.save();
				}
				else {
					//error contraseña incorrecta o user logeado
					
					String f="";
					
					if(user.getPassword().matches(txt_password.getText())&&user.isOnline()) {
						f="Este usuario ha iniciado ya sesión, inténtelo con otro usuario.";
					}
					else {
						f="Contraseña incorrecta.";
					}
					
					user=null;
					
					Alert alert=new Alert(AlertType.INFORMATION);
		    		alert.setHeaderText(null);
		    		alert.setTitle("Información");
		    		alert.setContentText(f);
		    		alert.showAndWait();
		    		
		    		
				}
			}
			else { //registro
				//preguntar registro...
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setTitle("Confirmación");
				alert.setContentText("¿Este usuario no existe, quiere crearlo ahora?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					user=new User(ul.get_new_Id(),txt_name.getText(),txt_password.getText(),true);
					ul.addUser(user);
					ul.save();
					login_register=true;
					//mostrar mensaje de registro completo...
					
					Alert alert2=new Alert(AlertType.INFORMATION);
		    		alert.setHeaderText(null);
		    		alert.setTitle("Información");
		    		alert.setContentText("¡Usuario registrado correctamente!");
		    		alert.showAndWait();
				}
			}
		}
		
		else { //mensaje de error
			Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setTitle("Información");
    		alert.setContentText("Debe rellenar los dos campos requeridos.");
    		alert.showAndWait();
		}
		
		if(login_register) {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("room_selector.fxml"));
			Parent root = loader.load();
			Scene scene= new Scene(root);
			Room_Selector_Controller room_selector= loader.getController();
			room_selector.setController(user);
			Stage stage2= new Stage();
			stage2.setScene(scene);
			Image image= new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.getIcons().add(image);
			stage2.setTitle("Selector de Sala");
			stage2.setResizable(false);;
			stage2.initModality(Modality.WINDOW_MODAL);
			
			Stage stage = (Stage) this.btn_login.getScene().getWindow();
			stage.close();

			stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {				
					
					try {			
						
						user.setOnline(false);
						ul.reeplaceUser(user);
						ul.save();
						
						
					} catch (IOException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			stage2.show();
		}
	}
	
}
