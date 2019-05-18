package es.deusto.server.data;

import java.io.Serializable;
import java.util.Date;

import es.deusto.server.jdo.Session;

public class SessionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3146665473731969523L;
	public Date time;
	public long id;
	
	public SessionDTO() {}
	public SessionDTO(Session session) {
		time = new Date(session.getTime().getTime());
		id = session.getId();
	}
}
