package AccesoArchivos.AccesoArchivos.models.user_folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import AccesoArchivos.AccesoArchivos.models.room_folder.RoomList;

public class UserTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * TEST DE SAVE
		 * 
		 */		
		User u0= new User(0, "Javier", "1234", false);
		User u1= new User(1, "Fernando", "1234", false);
		User u2= new User(2, "Ramón", "1234", false);
		User u3= new User(3, "Sara", "1234", false);
		
		UserList ul=UserList.getMiRepositorioU();  //<-- eso instsancia la lista de ul a vacia.
		ul.addUser(u0);
		ul.addUser(u1);
		ul.addUser(u2);
		ul.addUser(u3);
		
		try {
			ul.save(); //<-- guarda la base de datos
		} catch (IOException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		*TEST DE CHARGE
		*
		*/
		
//		UserList ul=UserList.getMiRepositorioU();
//		ul.charge();
//		System.out.println(ul.getUsers());
		
	}

}
