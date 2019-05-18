package es.deusto.server.jdo;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Ticket {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	private Session session;
	private User user;
	
	public Ticket(Session session, User user) {
		super();
		this.session = session;
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public long getId() {
		return id;
	}
}
