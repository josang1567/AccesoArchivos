package AccesoArchivos.AccesoArchivos.models.message_folder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import AccesoArchivos.AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.AccesoArchivos.models.room_folder.RoomList;
import AccesoArchivos.AccesoArchivos.models.user_folder.User;
import AccesoArchivos.AccesoArchivos.models.user_folder.UserList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="MessageList")

public class MessageList implements Serializable{
	@XmlElement(name="Messages",type=Message.class)
	public List<Message> Messages= new ArrayList();

	private static MessageList MiRepositorioM;
	
	private MessageList(List<Message> Messagges) {
		this.Messages=Messages;
	}
	
	private MessageList() {
		this.Messages=new ArrayList();
	}
	
	public static MessageList getMiRepositorioM(List<Message> message) {
		if(MiRepositorioM==null) {
			MiRepositorioM=new MessageList(message);
		}
		return MiRepositorioM;
	}
	
	public static MessageList getMiRepositorioM() {
		if(MiRepositorioM==null) {
			MiRepositorioM=new MessageList();
		}
		return MiRepositorioM;
	}

	public List<Message> getMessages() {
		return Messages;
	}
	public void setMessages(List<Message> messages) {
		if(messages!=null) {
			Messages = messages;
		}
	}
	 
	public void addMessages(Message newMessage) {
		if(newMessage!=null&&Messages!=null) {
			this.Messages.add(newMessage);
		}
	}
	public ObservableList<Message> accesMessagesAsObservable() {
		ObservableList<Message> result=FXCollections.observableArrayList();
		if(Messages!=null) {
			for(Message m:Messages) {
				result.add(m);
			}
		}
		return result;
	}
	public void charge(){
		JAXBContext jaxbC;
		try {
			jaxbC=JAXBContext.newInstance(UserList.class);
			Unmarshaller um = jaxbC.createUnmarshaller();
			MessageList ul=(MessageList)um.unmarshal(new File("MessagesList.xml"));
			Messages = new ArrayList();
			for(Message u:ul.getMessages()){		
				addMessages(u);
				
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void save() throws IOException, JAXBException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("MessagesList.xml")));
		
		JAXBContext context= JAXBContext.newInstance(MessageList.class);
		
		Marshaller m= context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		m.marshal(MiRepositorioM, writer);
		writer.close();
	}
	public int get_new_Id() {
		charge();	
		return (Messages.get(Messages.size()-1).getId())+1;
	}
}
