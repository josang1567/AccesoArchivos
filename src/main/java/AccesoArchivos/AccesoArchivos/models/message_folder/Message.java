package AccesoArchivos.AccesoArchivos.models.message_folder;


import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlTransient;

import AccesoArchivos.AccesoArchivos.models.room_folder.Room;
import AccesoArchivos.AccesoArchivos.models.user_folder.User;

public class Message {
	
	private int id;
	private String date;
	private String data;
	private User user; //<-- ¿que pasa si borras el user?
	//private String user_name;  ???  <--solución?
	private Room id_room;
	
	public Message() {
		this.date=null;
		this.data="";
		this.user=null;
		this.id_room=null;
	}
	
	public Message(int id, LocalDateTime date, String data, User user, Room room) {
		super();
		this.id = id;
		
		this.data = data;
		this.user = user;
		this.id_room = room;
		//convertir ldt a string:
		String day = date.getDayOfMonth()+"";
		String month = date.getMonth()+"";
		String year= date.getYear()+"";
		
		this.date="("+day+"/"+month+"/"+year+")";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Room getRoom() {
		return id_room;
	}

	@XmlTransient
	public void setRoom(Room room) {
		this.id_room = room;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", date=" + date + ", data=" + data + ", user=" + user + ", room=" + id_room + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
