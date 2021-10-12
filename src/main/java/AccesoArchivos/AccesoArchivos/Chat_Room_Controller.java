package AccesoArchivos.AccesoArchivos;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.message_folder.Message;
import AccesoArchivos.AccesoArchivos.models.message_folder.MessageList;
import AccesoArchivos.AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.AccesoArchivos.models.room_folder.RoomList;
import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Chat_Room_Controller {
	public static Timer  t=null;
	private User user;
	private Room room;
	private RoomList rl;
	private ObservableList<Message> messages = FXCollections.observableArrayList();
	private ObservableList<User> users = FXCollections.observableArrayList();
	
	@FXML
	protected Button btn_return;
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
	protected TableView<User> table_users;
	@FXML
	protected TableColumn<User, String> column_user;

	@FXML
	public void setController(User u, Room r) {
		rl = RoomList.getMiRepositorioM();
		rl.charge();
		user = u;
		room = r;
		lab_room_Name.setText(r.getName());
		for (Message m : room.getMessages()) {
			messages.add(m);
		}
		for (User ua : room.getLog_users()) {
			users.add(ua);
		}
		table_messages.setItems(messages);
		table_users.setItems(users);
		setTableAndDetailsInfo();
		updateRoomInfo();
		sincronize();
	}

	public void setTableAndDetailsInfo() {
		if (messages.size() > 0) {
			column_autor.setCellValueFactory(eachroom -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(eachroom.getValue().getUser().getName());
				return v;
			});

			column_data.setCellValueFactory(eachroom -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(eachroom.getValue().getDate().toString() + "\n" + eachroom.getValue().getData());
				return v;
			});
		}
		
		if (users.size() > 0) {
			column_user.setCellValueFactory(eachuser -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(eachuser.getValue().getName());				
				return v;
			});
		}
	}

	private void updateRoomInfo() {

		rl.charge();
		messages = FXCollections.observableArrayList();
		users = FXCollections.observableArrayList();
		for (Message m : room.getMessages()) {
			messages.add(m);
		}
		for (User u2 : room.getLog_users()) {
			users.add(u2);
		}
		table_messages.setItems(messages);
		table_users.setItems(users);
		setTableAndDetailsInfo();
	}

	@FXML
	private void send_Message() throws IOException, JAXBException, InterruptedException {
		if (!txt_write.getText().matches("")) {
			
			//parar update momentaneamente.
			
			String message = "";
			int n = 50;
			for (int i = 0; i < txt_write.getText().length(); i++) {
				if (n > 0) {
					message += txt_write.getText().charAt(i);
					n--;
				} else {
					message += "-\n" + txt_write.getText().charAt(i);
					n = 50;
				}
			}
			// crear mensaje y generar newId
			MessageList ml = MessageList.getMiRepositorioM();
			ml.charge();
			Message m = new Message(ml.get_new_Id(), LocalDateTime.now(), message, user, room);
			// añadir mensaje a ml, messages y room
			ml.addMessages(m);
			ml.save();
			room = rl.refresh(room);
			room.getMessages().add(m);
			rl.save();
			updateRoomInfo();
			setTableAndDetailsInfo();
			
			txt_write.setText("");
		}
	}

	public void sincronize() {
		t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                	rl.charge();
                	room=rl.refresh(room);
                	updateRoomInfo();
                	System.out.println("hola");
                });
            }
        }, 0, 1500);
		
		
	}
	@FXML
	private void return_Chat_Selector() throws IOException, JAXBException {
		t.cancel();
		
		int i=room.getLog_users().lastIndexOf(user);
		room.getLog_users().remove(i);
		rl.reeplaceRoom(room);
		rl.save();
		
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
		
		Stage stage = (Stage) this.btn_return.getScene().getWindow();
		stage.close();
		stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {				
				
				try {			
					
					user.setOnline(false);
					UserList ul=UserList.getMiRepositorioU();
					ul.reeplaceUser(user);
					ul.save();
					
					if(room.getLog_users().size()>0) {
						if(room.getLog_users().size()==1) {
							room.getLog_users().remove(0);
						}
						else {
							int i=room.getLog_users().lastIndexOf(user);
							room.getLog_users().remove(i);
							rl.reeplaceRoom(room);
							rl.save();
						}
						rl.reeplaceRoom(room);
						rl.save();
					}
					
					
				} catch (IOException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		stage2.show();
	}

}
