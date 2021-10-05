package AccesoArchivos.AccesoArchivos;

import java.io.File;

import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.user_folder.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Room_Selector_Controller {
	
	ObservableList<Room> rooms;
	private User user;	
	private Room room;
	
	@FXML
	protected Pane options_Pane;
	@FXML
	protected Button btn_user;
	@FXML
	protected Button btn_enter;
	@FXML
	protected TableView<Room> table_room;
	@FXML
	protected TableColumn<Room, String> col_asunto;
	@FXML
	protected TableColumn<Room, String> col_description;
	
	public void setController(User u) {
		//falta cargar datos...
		btn_user.setText(u.getName());
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
	
	public void enterRoom() {
		
	}
	
	public void updateRooms() {
		
	}
	
	@FXML
	private void open_Options_Pane() {
		options_Pane.setVisible(true);
	}
	
	@FXML
	private void close_Options_Pane() {
		options_Pane.setVisible(false);
	}
	
    @FXML
    private void changeColorUserName() {
    	btn_user.setTextFill(Color.RED);
    }
    
    @FXML
    private void changeColorDefaultUserName() {
    	btn_user.setTextFill(Color.BLACK);
    }

    @FXML
	public void select_Room() { //actualizar esto cuando tenga tiempo por una variable power
		
		if(rooms.size()>0) {
			if(table_room.getSelectionModel().getSelectedItem()!=null) {
				room= this.table_room.getSelectionModel().getSelectedItem();
				btn_enter.setDisable(false);
			}
			else {
				room=null;
				btn_enter.setDisable(true);
			}

		}
		else { //no hay charas
			room=null;
		}
		
	}

}