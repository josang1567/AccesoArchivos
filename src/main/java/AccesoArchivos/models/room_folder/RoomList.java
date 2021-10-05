package AccesoArchivos.models.room_folder;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="RoomList")

public class RoomList {
	@XmlElement(name="Rooms",type=Room.class)
	private static List<Room> Rooms = new ArrayList();

	public RoomList() {}

	public List<Room> getRooms() {
		return Rooms;
	}

	public void setRooms(List<Room> rooms) {
		Rooms = rooms;
	}
	
	public void addRooms(Room newRoom) {
		this.Rooms.add(newRoom);
	}
	
	public ObservableList<Room> accesRoomsAsObservable() {
		ObservableList<Room> result=FXCollections.observableArrayList();
		for(Room r:Rooms) {
			result.add(r);
		}
		return result;
	}
	
}
