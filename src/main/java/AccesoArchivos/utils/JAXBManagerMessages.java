package AccesoArchivos.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import AccesoArchivos.models.message_folder.MessageList;
import AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.models.room_folder.UserList;

public class JAXBManagerMessages {
	public static void marshal(MessageList ml, String f)throws JAXBException{
		marshal(ml, "MessagesList.xml");
	}
	public static void mashal(MessageList ml,String MessageList) throws JAXBException,IOException{
		marshal(ml, new File(MessageList));
	}
	
public static void marshal(MessageList ml, File f) throws IOException, JAXBException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		
		JAXBContext context= JAXBContext.newInstance(MessageList.class);
		
		Marshaller m= context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		m.marshal(ml, writer);
		writer.close();
	}

	public static Room unmarshal(File f) {

		return null;
	}
}
