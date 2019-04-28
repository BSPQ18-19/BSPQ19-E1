package es.deusto.server.jdo;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Ticket {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	private Session session;
	private Client client;
	
	public Ticket(Session session, Client client) {
		super();
		this.session = session;
		this.client = client;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public long getId() {
		return id;
	}
}
