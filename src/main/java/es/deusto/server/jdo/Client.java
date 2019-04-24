package es.deusto.server.jdo;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Client {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	
	private String name;
	private String surname;
	private String phone;
	
	@Unique
	private String email;
	private String password;
	
	// TODO 
	// Join table or primary key?
	// @Persistent(mappedBy="Client")
	// private List<Ticket> purchases;
	
	public Client(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
	
	public boolean fieldsEqual(Client c) {
		return this.name.equals(c.name) 
				&& this.surname.equals(c.surname)
				&& this.phone.equals(c.phone)
				&& this.email.equals(c.email)
				&& this.password.equals(c.password);
	}
}
