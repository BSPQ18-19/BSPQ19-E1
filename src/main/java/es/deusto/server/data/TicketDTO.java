package es.deusto.server.data;

import java.io.Serializable;

import es.deusto.server.jdo.Ticket;

public class TicketDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1545774481760376675L;
	public long id;
	public SessionDTO session;
	
	public TicketDTO() {}
	public TicketDTO(Ticket ticket) {
		id = ticket.getId();
		session = new SessionDTO(ticket.getSession());
	}
}
