package AccesoArchivos.AccesoArchivos.models.user_folder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
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

import AccesoArchivos.AccesoArchivos.models.room_folder.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UserList")
public class UserList implements Serializable {
	@XmlElement(name = "Users", type = User.class)
	private List<User> Users = new ArrayList();

	private static UserList MiRepositorioU;

	private UserList(List<User> Users) {
		this.Users = Users;
	}
	private UserList() {
		this.Users = new ArrayList();
	}

	public static UserList getMiRepositorioU(List<User> user) {
		if (MiRepositorioU == null) {
			MiRepositorioU = new UserList(user);
		}
		return MiRepositorioU;
	}
	
	public static UserList getMiRepositorioU() {
		if (MiRepositorioU == null) {
			MiRepositorioU = new UserList();
		}
		return MiRepositorioU;
	}

	public List<User> getUsers() {
		return Users;
	}

	public void setUsers(List<User> users) {
		Users = users;
	}

	public void addUser(User newUser) {
		
		if(this.Users!=null&&newUser!=null) {
			this.Users.add(newUser);
		}
		else {
			System.out.println("error: newUser era null");
		}
	}
	
	public void removeUser(User OldUser) throws JAXBException {
		if(OldUser!=null) {
			OldUser.setName(OldUser.getName()+"_Erased");
		}
		else {
			System.out.println("error: oldUser era null");
		}
	}
	
	public ObservableList<User> accesUsersAsObservable() {
		ObservableList<User> result=FXCollections.observableArrayList();
		for(User u:Users) {
			result.add(u);
		}
		return result;
	}
	
	public int get_new_Id() {
		charge();	
		return (Users.get(Users.size()-1).getId())+1;
	}
	
	public void reeplaceUser(User u) {
		if(u!=null&&Users!=null&&Users.contains(u)) {
			boolean done=false;
			for(int i=0;i<Users.size()&&!done;i++) {
				if(Users.get(i).getId()==u.getId()) {
					Users.add(i, u);
					Users.remove(i+1);
					done=true;
				}
			}
		}
	}
	
	public void save() throws IOException, JAXBException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("UsersList.xml")));
		
		JAXBContext context= JAXBContext.newInstance(UserList.class);
		
		Marshaller m= context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
		m.marshal(MiRepositorioU, writer);
		writer.close();
	}
	
	public void charge(){
		JAXBContext jaxbC;
		try {
			jaxbC=JAXBContext.newInstance(UserList.class);
			Unmarshaller um = jaxbC.createUnmarshaller();
			UserList ul=(UserList)um.unmarshal(new File("UsersList.xml"));
			Users = new ArrayList();
			for(User u:ul.getUsers()){		
				addUser(u);
				
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
