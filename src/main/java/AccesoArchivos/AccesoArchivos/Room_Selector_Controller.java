package AccesoArchivos.AccesoArchivos;

import java.io.IOException;

import AccesoArchivos.models.room_folder.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class Room_Selector_Controller {
	
	@FXML
	protected Button btn_user;
	@FXML
	protected TableView<Room> table_room;
	@FXML
	protected TableColumn<Room, String> col_asunto;
	@FXML
	protected TableColumn<Room, String> col_description;
	
	
    @FXML
    private void changeColorUserName() {
    	btn_user.setTextFill(Color.RED);
    }
    
    @FXML
    private void changeColorDefaultUserName() {
    	btn_user.setTextFill(Color.BLACK);
    }
}