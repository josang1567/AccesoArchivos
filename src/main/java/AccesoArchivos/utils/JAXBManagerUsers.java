package AccesoArchivos.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.room_folder.RoomList;

public class JAXBManagerUsers {
	public static void marshal(RoomList ul, String f)throws JAXBException{
		marshal(ul, "UsersList.xml");
	}
	public static void mashal(RoomList ul, String roomList) throws JAXBException,IOException{
		marshal(ul, new File(roomList));
	}
	
public static void marshal(RoomList ul, File f) throws IOException, JAXBException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		
		JAXBContext context= JAXBContext.newInstance(RoomList.class);
		
		Marshaller m= context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		m.marshal(ul, writer);
		writer.close();
	}

	public static Room unmarshal(File f) {

		return null;
	}
}
