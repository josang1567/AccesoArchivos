package AccesoArchivos.AccesoArchivos;

import java.util.Arrays;

import AccesoArchivos.models.message_folder.Message;
import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.user_folder.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class Chat_Room_Controller {
	
	private User user;
	private Room room;
	private ObservableList<Message> messages=FXCollections.observableArrayList();
	
	@FXML
	protected Button btn_write;
	@FXML
	protected Label lab_room_Name;
	@FXML
	protected TextArea txt_write;
	@FXML
	protected TableView<Message> table_messages;
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
		for(Message m:room.getMessages()) {
			messages.add(m);
		}
		table_messages.setItems(messages);
		setTableAndDetailsInfo();
	}
	
	public void setTableAndDetailsInfo() {
		if (messages.size()>0) {
			column_autor.setCellValueFactory(eachroom->{
	    		SimpleStringProperty v=new SimpleStringProperty();
	    		v.setValue(eachroom.getValue().getUser().getName());
	    		return v;
	    	});
			
			column_data.setCellValueFactory(eachroom->{
	    		SimpleStringProperty v=new SimpleStringProperty();
	    		v.setValue("04/01/2021\n"+eachroom.getValue().getData());
	    		return v;
	    	});
		}
	}
	
	private void updateRoomInfo(){
		
	}
	
	
}
