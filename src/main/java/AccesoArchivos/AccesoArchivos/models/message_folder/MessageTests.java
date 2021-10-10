package AccesoArchivos.AccesoArchivos.models.message_folder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.AccesoArchivos.models.room_folder.RoomList;
import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;

public class MessageTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Room r0= new Room(0, "Matematicas", "Sala de mates", null, null);
//		
//		User u0= new User(0, "Javier", "1234", false);
//		User u1= new User(1, "Fernando", "1234", false);	
//		UserList ul=UserList.getMiRepositorioU();
//		ul.addUser(u0);
//		ul.addUser(u1);
//		
//		Message m0=new Message(0, LocalDateTime.now(), "mensaje0", u1, r0);
//		Message m1=new Message(1, LocalDateTime.now(), "mensaje1", u1, r0);
//		Message m2=new Message(2, LocalDateTime.now(), "mensaje2", u1, r0);
//		Message m3=new Message(3, LocalDateTime.now(), "mensaje3", u1, r0);
//		MessageList ml=MessageList.getMiRepositorioM();
//		ml.addMessages(m0);
//		ml.addMessages(m1);
//		ml.addMessages(m2);
//		ml.addMessages(m3);
//		
//		r0.setMessages(ml.getMessages());
//		r0.setLog_users(ul.getUsers());
//		
//		try {
//			ml.save();
//		} catch (IOException | JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		MessageList ml=MessageList.getMiRepositorioM();
		ml.charge();
		System.out.println(ml.getMessages());
		
	}

}
