package AccesoArchivos.AccesoArchivos;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.message_folder.Message;
import AccesoArchivos.AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.AccesoArchivos.models.room_folder.RoomList;
import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Room_Selector_Controller {

	private User user;
	private Room room;
	private RoomList rl;

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
		rl = RoomList.getMiRepositorioM();
		rl.charge();

		user = u;
		btn_user.setText(u.getName());
		ObservableList<Room> OLrooms = rl.accesRoomsAsObservable();
		table_room.setItems(OLrooms);
		setTableAndDetailsInfo();
	}

	public void setTableAndDetailsInfo() {
		if (rl.getRooms().size() > 0) {
			col_asunto.setCellValueFactory(eachroom -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(eachroom.getValue().getName());
				return v;
			});

			col_description.setCellValueFactory(eachroom -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(eachroom.getValue().getDescription());
				return v;
			});
		}
	}

	public void enterRoom() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("chat_room.fxml"));
			Parent root;
			root = loader.load();
			Scene scene= new Scene(root);
			Chat_Room_Controller chat_room=loader.getController();
			chat_room.setController(user,room);
			Stage stage2= new Stage();
			stage2.setScene(scene);
			Image image= new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.getIcons().add(image);
			stage2.setTitle("Chat: "+room.getName());
			stage2.setResizable(false);;
			stage2.initModality(Modality.WINDOW_MODAL);
			
			Stage stage = (Stage) this.btn_enter.getScene().getWindow();
			stage.close();

			stage2.show();
			stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {				
					
					try {			
						user.setOnline(false);
						UserList ul=UserList.getMiRepositorioU();
						ul.save();
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("room_selector.fxml"));
						Parent root = loader.load();
						Scene scene= new Scene(root);
						Room_Selector_Controller room_selector= loader.getController();
						room_selector.setController(user);
						Stage stage2= new Stage();
						stage2.setScene(scene);
						Image image= new Image("file:src/main/resources/images/icons/icon_app.jpg");
						stage2.getIcons().add(image);
						stage2.setTitle("Chat XML");
						stage2.setResizable(false);;
						stage2.initModality(Modality.WINDOW_MODAL);
						stage2.show();
					} catch (IOException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
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
	public void select_Room() {

		if (rl.getRooms().size() > 0) {
			if (table_room.getSelectionModel().getSelectedItem() != null) {
				room = this.table_room.getSelectionModel().getSelectedItem();
				btn_enter.setDisable(false);
			} else {
				room = null;
				btn_enter.setDisable(true);
			}

		} else {
			room = null;
		}

	}

}