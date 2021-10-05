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

public class JAXBManageUsers {
	public static void marshal(UserList ul, String f)throws JAXBException{
		marshal(ul, "UsersList.xml");
	}
	public static void mashal(UserList ul, String roomList) throws JAXBException,IOException{
		marshal(ul, new File(roomList));
	}
	
public static void marshal(UserList ul, File f) throws IOException, JAXBException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		
		JAXBContext context= JAXBContext.newInstance(UserList.class);
		
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
