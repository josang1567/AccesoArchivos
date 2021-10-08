package AccesoArchivos.AccesoArchivos.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;

public class JAXBManagerUsers {
	public static void marshal(List<User> users, String f)throws JAXBException{
		marshal(users, "UsersList.xml");
	}
	public static void mashal(UserList ul, String UserList) throws JAXBException,IOException{
		marshal(ul, new File(UserList));
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

	public static User unmarshal(File f) {

		return null;
	}
	
	public List<User> unMarshalingUser() throws JAXBException{
		JAXBContext jaxcbcontext = JAXBContext.newInstance(User.class);
		Unmarshaller jaxbUnmarshaller= jaxcbcontext.createUnmarshaller();
		List<User> lista= new ArrayList<User>();
		//conversion del archivo
		UserList users= (UserList) jaxbUnmarshaller.unmarshal(new File("./UsersList.xml"));
		
		
		return users.getUsers();
	}
}
