package AccesoArchivos.AccesoArchivos.models.room_folder;

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
	private List<Room> Rooms = new ArrayList();

	private static RoomList MiRepositorioR;
	
	private RoomList(List<Room> rooms) {
		this.Rooms=rooms;
	}
	
	private RoomList() {
		this.Rooms=new ArrayList();;
	}
	
	public static RoomList getMiRepositorioM(List<Room> room) {
		if(MiRepositorioR==null) {
			MiRepositorioR=new RoomList(room);
		}
		return MiRepositorioR;
	}
	
	public static RoomList getMiRepositorioM() {
		if(MiRepositorioR==null) {
			MiRepositorioR=new RoomList();
		}
		return MiRepositorioR;
	}
	
	
	public List<Room> getRooms() {
		return Rooms;
	}

	public void setRooms(List<Room> rooms) {
		Rooms = rooms;
	}
	
	public void addRoom(Room newRoom) {
		if(this.Rooms!=null&&newRoom!=null) {
			this.Rooms.add(newRoom);
		}
		else {
			System.out.println("error: newRoom era null");
		}
	}
	
	public ObservableList<Room> accesRoomsAsObservable() {
		ObservableList<Room> result=FXCollections.observableArrayList();
		if(Rooms!=null) {
			for(Room r:Rooms) {
				result.add(r);
			}
		}
		return result;
	}
	
	public void save() throws IOException, JAXBException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("RoomsList.xml")));
		
		JAXBContext context= JAXBContext.newInstance(RoomList.class);
		
		Marshaller m= context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		m.marshal(MiRepositorioR, writer);
		writer.close();
	}
	
	public void charge() {
		JAXBContext jaxbC;
		try {
			jaxbC=JAXBContext.newInstance(RoomList.class);
			Unmarshaller um = jaxbC.createUnmarshaller();
			RoomList rl=(RoomList)um.unmarshal(new File("RoomsList.xml"));
			Rooms = new ArrayList();
			for(Room r:rl.getRooms()){		
				addRoom(r);	
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Room refresh(Room sala) {
		Room refresh= new Room();
		boolean encontrado=false;
		charge();
		for (int i = 0; i < Rooms.size() && !encontrado ; i++) {
			if(Rooms.get(i).equals(sala)) {
				refresh=Rooms.get(i);
				encontrado=true;
			}
			
		}
		return refresh;
	}
	
	public void reeplaceRoom(Room r) {
		if(r!=null&&Rooms!=null&&Rooms.contains(r)) {
			boolean done=false;
			for(int i=0;i<Rooms.size()&&!done;i++) {
				if(Rooms.get(i).getId()==r.getId()) {
					Rooms.add(i, r);
					Rooms.remove(i+1);
					done=true;
				}
			}
		}
	}
	
}
