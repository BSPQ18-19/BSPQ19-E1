package es.deusto.server.data;

import java.io.Serializable;

import es.deusto.server.jdo.User;
import es.deusto.server.jdo.UserType;

public class UserDetailsDTO implements Serializable {

	public long id;
	public String name;
	public String surname;
	public String phone;
	public String email;
	public String password;
	public UserType type;
	
	public UserDetailsDTO() {};
	
	public UserDetailsDTO(User u) {
		id = u.getId();
		name = new String(u.getName());
		surname = new String(u.getSurname());
		phone = new String(u.getPhone());
		email = new String(u.getEmail());
		password = new String(u.getPassword());
		type = u.getType();
	}
}
