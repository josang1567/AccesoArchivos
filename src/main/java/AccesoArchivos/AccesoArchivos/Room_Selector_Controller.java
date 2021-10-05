package AccesoArchivos.AccesoArchivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.user_folder.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class Room_Selector_Controller {
	
	ObservableList<Room> rooms;
	
	@FXML
	protected Button btn_user;
	@FXML
	protected TableView<Room> table_room;
	@FXML
	protected TableColumn<Room, String> col_asunto;
	@FXML
	protected TableColumn<Room, String> col_description;
	
	public void setController() {
		//falta cargar datos...
		rooms=FXCollections.observableArrayList();
		rooms.add(new Room(1,"prueba","desc prueba",null,null));
		rooms.add(new Room(2,"prueba2","desc prueba2",null,null));
		table_room.setItems(rooms);
		setTableAndDetailsInfo();
	}
	
	public void setTableAndDetailsInfo() {
		if (rooms.size()>0) {
			col_asunto.setCellValueFactory(eachroom->{
	    		SimpleStringProperty v=new SimpleStringProperty();
	    		v.setValue(eachroom.getValue().getName());
	    		return v;
	    	});
			
			col_description.setCellValueFactory(eachroom->{
	    		SimpleStringProperty v=new SimpleStringProperty();
	    		v.setValue(eachroom.getValue().getDescription());
	    		return v;
	    	});
		}
	}
	
	public void updateRooms() {
		
	}
	
    @FXML
    private void changeColorUserName() {
    	btn_user.setTextFill(Color.RED);
    }
    
    @FXML
    private void changeColorDefaultUserName() {
    	btn_user.setTextFill(Color.BLACK);
    }
}