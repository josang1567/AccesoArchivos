package AccesoArchivos.AccesoArchivos;

import AccesoArchivos.models.message_folder.Message;
import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.user_folder.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class Chat_Room_Controller {
	
	private User user;
	private Room room;
	
	@FXML
	protected Button btn_write;
	@FXML
	protected Label lab_room_Name;
	@FXML
	protected TextArea txt_write;
	@FXML
	protected TableView table_messages;
	@FXML
	protected TableColumn<Message, String> column_autor;
	@FXML
	protected TableColumn<Message, String> column_data;
	@FXML
	protected TableView table_users;
	@FXML
	protected TableColumn<User, String> column_user;
	
	@FXML
	public void setController(User u, Room r) {
		user=u;
		room=r;
		
		lab_room_Name.setText(r.getName());
	}
	
	
}
