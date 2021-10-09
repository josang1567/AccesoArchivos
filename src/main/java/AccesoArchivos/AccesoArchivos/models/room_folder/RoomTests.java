package AccesoArchivos.AccesoArchivos.models.room_folder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.message_folder.Message;
import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;

public class RoomTests {

	public static void main(String[] args) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		
		Room r0= new Room(0, "Matematicas", "Sala de mates", new ArrayList(), new ArrayList());
		RoomList rl=RoomList.getMiRepositorioM();
		rl.addRoom(r0);
		
		
		
//		User u0= new User(0, "Javier", "1234", false);
//		User u1= new User(1, "Fernando", "1234", false);	
//		UserList ul=UserList.getMiRepositorioU();
//		ul.addUser(u0);
//		ul.addUser(u1);
//		r0.setLog_users(ul.getUsers());
		rl.save();
		
//		Message m0=new Message(0, LocalDateTime.now(), "mensaje0", u1, r0);
//		Message m1=new Message(1, LocalDateTime.now(), "mensaje1", u1, r0);
//		Message m2=new Message(2, LocalDateTime.now(), "mensaje2", u1, r0);
//		List<Message> ml=new ArrayList();
//		ml.add(m0);
//		ml.add(m1);
//		ml.add(m2);
		
//		r0.setMessages(ml);
//		r0.setLog_users(ul.getUsers());
		
		
		
		try {
			rl.save();
		} catch (IOException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///---------------TEST CHARGE-----------------
//		User u1= new User(1, "Fernando", "1234", false);
//		Message m0=new Message(0, LocalDateTime.now(), "mensaje0", u1, 0);
//		Message m1=new Message(1, LocalDateTime.now(), "mensaje1", u1, 0);
//		Message m2=new Message(2, LocalDateTime.now(), "mensaje2", u1, 0);
//		
//		RoomList rl=RoomList.getMiRepositorioM();
//		rl.charge();
//		System.out.println("test -->"+rl.getRooms()+"\nya no hay mas ");
	}

}
