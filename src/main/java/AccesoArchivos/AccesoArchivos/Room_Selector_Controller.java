package AccesoArchivos.AccesoArchivos;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.message_folder.Message;
import AccesoArchivos.AccesoArchivos.models.message_folder.MessageList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	protected Button btn_close_sesion;
	@FXML
	protected Button btn_delete_user;
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
			Scene scene = new Scene(root);
			Chat_Room_Controller chat_room = loader.getController();
			room.getLog_users().add(user);
			RoomList rl = RoomList.getMiRepositorioM();
			rl.save();
			chat_room.setController(user, room);
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.getIcons().add(image);
			stage2.setTitle("Chat: " + room.getName());
			stage2.setResizable(false);
			;
			stage2.initModality(Modality.WINDOW_MODAL);

			Stage stage = (Stage) this.btn_enter.getScene().getWindow();
			stage.close();

			stage2.show();
			stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {

					try {
						user.setOnline(false);
						UserList ul = UserList.getMiRepositorioU();
						ul.reeplaceUser(user);
						ul.save();
						chat_room.t.cancel();

						if (room.getLog_users().size() > 0) {
							if (room.getLog_users().size() == 1) {
								room.getLog_users().remove(0);
							} else {
								int i = room.getLog_users().lastIndexOf(user);
								room.getLog_users().remove(i);
							}
							rl.reeplaceRoom(room);
							rl.save();
						}

					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			});

		} catch (IOException | JAXBException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	@FXML
	public void close_session() throws IOException, JAXBException {
		close_Options_Pane();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText("¿Se cerrará su sesión, desea continuar?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			user.setOnline(false);
			UserList ul = UserList.getMiRepositorioU();
			ul.reeplaceUser(user);
			ul.save();

			Stage stage = (Stage) this.btn_close_sesion.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			PrimaryController primary = loader.getController();
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.getIcons().add(image);
			stage2.setTitle("Inicio de Sesión");
			stage2.setResizable(false);
			;
			stage2.initModality(Modality.APPLICATION_MODAL);

			stage2.show();
		}
	}

	public void delete_user() throws IOException, JAXBException {
		close_Options_Pane();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText("¿Está seguro de que quiere borrar su usuario?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			user.setOnline(false);
			user.setName(user.getName()+"\n(DELETED ACCOUNT)");
			user.setPassword("");
			UserList ul = UserList.getMiRepositorioU();
			ul.reeplaceUser(user);
			ul.save();
			MessageList ml=MessageList.getMiRepositorioM();
			ml.charge();
			if(ml.Messages!=null&&ml.Messages.size()>0){
				for(Message m:ml.Messages) {
					if(m.getUser().getId()==user.getId()) {
						m.setUser(user);
					}
				}
			}
			ml.save();
			RoomList rl=RoomList.getMiRepositorioM();
			rl.charge();
			for(Room r:rl.getRooms()) {
				if(r.getMessages()!=null&&r.getMessages().size()>0) {
					for(Message m2:r.getMessages()) {
						if(m2.getUser().getId()==user.getId()) {
							m2.setUser(user);
						}
					}
				}
			}
			rl.save();
			
			Alert alert2=new Alert(AlertType.INFORMATION);
    		alert.setHeaderText(null);
    		alert.setTitle("Información");
    		alert.setContentText("¡Usuario eliminado correctamente!");
    		alert.showAndWait();
    		
    		Stage stage = (Stage) this.btn_close_sesion.getScene().getWindow();
			stage.close();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			PrimaryController primary = loader.getController();
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.getIcons().add(image);
			stage2.setTitle("Inicio de Sesión");
			stage2.setResizable(false);
			;
			stage2.initModality(Modality.APPLICATION_MODAL);

			stage2.show();
		}
	}

	@FXML
	private void open_Options_Pane() {
		options_Pane.setVisible(true);
	}

	@FXML
	private void close_Options_Pane() {
		options_Pane.setVisible(false);
		btn_delete_user.setTextFill(Color.BLACK);
		btn_close_sesion.setTextFill(Color.BLACK);
	}

	@FXML
	private void changeColorUserName() {
		btn_user.setTextFill(Color.WHITE);
	}

	@FXML
	private void Default_close_session() {
		btn_close_sesion.setTextFill(Color.BLACK);
	}

	@FXML
	private void Default_delete_user() {
		btn_delete_user.setTextFill(Color.BLACK);
	}

	@FXML
	private void changeColor_close_Session() {
		btn_close_sesion.setTextFill(Color.RED);
	}

	@FXML
	private void changeColor_delete_user() {
		btn_delete_user.setTextFill(Color.RED);
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