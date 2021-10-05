package AccesoArchivos.models.user_folder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="UserList")
public class UserList  implements Serializable{
	@XmlElement(name="Users",type=User.class)
	private List<User> Users= new ArrayList();

	public UserList() {}

	public List<User> getUsers() {
		return Users;
	}

	public void setUsers(List<User> users) {
		Users = users;
	}
	
	public void addUser(User newUser) {
		this.Users.add(newUser);
	}
	public void removeUser(User newUser) {
		this.Users.remove(newUser);
	}
	
}
