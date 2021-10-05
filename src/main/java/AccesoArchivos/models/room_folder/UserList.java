package AccesoArchivos.models.room_folder;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="RoomList")

public class UserList {
	@XmlElement(name="Rooms",type=Room.class)
	private List<Room> Rooms = new ArrayList();

	public UserList() {}

	public List<Room> getRooms() {
		return Rooms;
	}

	public void setRooms(List<Room> rooms) {
		Rooms = rooms;
	}
	
	public void addRooms(Room newRoom) {
		this.Rooms.add(newRoom);
	}
	
}
