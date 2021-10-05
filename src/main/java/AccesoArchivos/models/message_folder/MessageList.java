package models.message_folder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="MessageList")

public class MessageList implements Serializable{
	@XmlElement(name="Messages",type=Message.class)
	private List<Message> Messages= new ArrayList();

	public MessageList() {}

	public List<Message> getMessages() {
		return Messages;
	}

	public void setMessages(List<Message> messages) {
		Messages = messages;
	}
	 
	public void addMessages(Message newMessage) {
		this.Messages.add(newMessage);
	}
	
}
