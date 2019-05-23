package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;

import es.deusto.server.data.UserDTO;
import es.deusto.server.data.UserDetailsDTO;

@PersistenceCapable
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	
	private String name;
	private String surname;
	private String phone;
	
	@Unique
	private String email;
	private String password;
	private UserType type;
	
	// TODO 
	// Join table or primary key?
	@Persistent(mappedBy="user")
	private List<Ticket> purchases;
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		purchases = new ArrayList<Ticket>();
	}

	public User(UserDetailsDTO user) {
		super();
		this.email = user.email;
		this.name = user.name;
		this.surname = user.surname;
		this.phone = user.phone;
		this.password = user.password;
	}

	public User(UserDTO userDTO) {
		super();
		this.name = userDTO.getName();
		this.id = userDTO.getUserID();
	}
	
	public void updateDetails(UserDetailsDTO details) {
		if(!name.equals(details.name)){
			name = new String(details.name);
		}
		
		if(!surname.equals(details.surname)){
			surname = new String(details.surname);
		}
		
		if(!phone.equals(details.phone)){
			phone = new String(details.phone);
		}
		
		if(!email.equals(details.email)){
			email = new String(details.email);
		}
		
		if(!password.equals(details.password)){
			password = new String(details.password);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	
	public List<Ticket> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Ticket> purchases) {
		this.purchases = purchases;
	}

	public boolean fieldsEqual(User c) {
		return this.name.equals(c.name) 
				&& this.surname.equals(c.surname)
				&& this.phone.equals(c.phone)
				&& this.email.equals(c.email)
				&& this.password.equals(c.password);
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
}
