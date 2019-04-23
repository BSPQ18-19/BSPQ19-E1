package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.*;

public class DAO {
	
	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	private Transaction tx;
	
	// Generic DAO methods
	public DAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	public void begin() {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		tx.begin();
	}
	
	public void end() {
		tx.commit();
		if (tx.isActive())
        {
            tx.rollback();
        }
        pm.close();
	}
	
	public void detachOnCommit() {
		pm.setDetachAllOnCommit(true);
	}
	
	public void rollback() {
		tx.rollback();
		pm.close();
	}
	
	public void storeObject(Object o) {
		pm.makePersistent(o);
	}
	
	public <T> void cleanDatabase(Class<T> c) {
		begin();
		Query<T> qs = pm.newQuery(c);
		qs.deletePersistentAll();
		end();
	}
	
	// Movie methods
	public List<Movie> getMovies(){
		Extent<Movie> ex = pm.getExtent(Movie.class, false);
		Query<Movie> q = pm.newQuery(ex);
		Collection<Movie> col = (Collection<Movie>) q.execute();
		List<Movie> list = new ArrayList<>(col);
		return list;
	}
	
	public Movie getMovie(String title) {
		Query<Movie> q = pm.newQuery(Movie.class);
		q.setUnique(true);
		q.setFilter("title == my_title");
		q.declareParameters("java.lang.String my_title");
		Movie movie = q.setParameters(title).executeUnique();
		return movie;
	}
	
	public Movie getMovie(long id) {
		Movie movie = pm.getObjectById(Movie.class, id);
		return movie;
	}
	
	public void deleteMovie(String title) {
		Movie movie = getMovie(title);
		for(Session s : movie.getSessions()) {
			pm.deletePersistent(s);
		}
		pm.deletePersistent(movie);
	}
	
	// Session methods
	public List<Session> getSessions(int year, int month, int day) {
		Query<Session> q = pm.newQuery(Session.class);
		q.setFilter("time > d1");
		q.setFilter("time < d2");
		q.declareParameters("java.util.Date d1, java.util.Date d2");
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(year, month, day, 0, 0, 0);
		Date d1 = cal.getTime();
		cal.set(year, month, day, 23, 59, 59);
		Date d2 = cal.getTime();
		List<Session> list = (List<Session>) q.execute(d1, d2);
		return list;
		
	}
	
	
	// Client methods
	/**
	 * 
	 * @param c Client to register
	 * @return <b>true</b> if sucess <b>false</b> if fail (user already exists)
	 */
	public boolean registerClient(Client c) {
		try {
			storeObject(c);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return The client corresponding to the given credentials, or <b>null</b> 
	 * if no user exists with those parameters.
	 */
	public Client getClient(String email, String password) {
		Query<Client> q = pm.newQuery(Client.class);
		q.setFilter("email == em");
		q.setFilter("password == pw");
		q.declareParameters("java.lang.String em, java.lang.String pw");
		Client c = q.setParameters(email, password).executeUnique();
		return c;
	}	
}
