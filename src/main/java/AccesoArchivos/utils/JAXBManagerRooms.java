package AccesoArchivos.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.room_folder.UserList;

public class JAXBManagerRooms {
	public static void marshal(UserList bl, String f)throws JAXBException{
		marshal(bl, "RoomsList.xml");
	}
	public static void mashal(UserList rl,String roomList) throws JAXBException,IOException{
		marshal(rl, new File(roomList));
	}
	
public static void marshal(UserList rl, File f) throws IOException, JAXBException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		
		JAXBContext context= JAXBContext.newInstance(UserList.class);
		
		Marshaller m= context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		m.marshal(rl, writer);
		writer.close();
	}

	public static Room unmarshal(File f) {

		return null;
	}
}
