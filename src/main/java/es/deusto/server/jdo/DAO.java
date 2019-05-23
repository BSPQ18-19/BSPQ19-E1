package es.deusto.server.jdo;

import es.deusto.server.data.UserDTO;

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
		if (tx.isActive()) {
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
	@SuppressWarnings("unchecked")
	public List<Movie> getMovies() {
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

	@SuppressWarnings("unchecked")
	public List<Movie> searchMovieByTitle(String query) {
		// Extent<Movie> ex = pm.getExtent(Movie.class, false);
		Query<Movie> q = pm.newQuery(Movie.class);
		q.setFilter("this.title.matches(:expression)");
		Collection<Movie> col = (Collection<Movie>) q.execute(".*" + query + ".*");
		return new ArrayList<Movie>(col);

	}

	public void deleteMovie(String title) {
		Movie movie = getMovie(title);
		deleteMovie(movie);

	}

	public void deleteMovie(Movie movie) {
		if (movie.getSessions() != null) {
			for (Session s : movie.getSessions()) {
				pm.deletePersistent(s);
			}
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

	public Session getSession(Movie movie, Date time) {
		Query<Session> q = pm.newQuery(Session.class);

		Session session = q.setParameters(movie, time).executeUnique();
		return session;
	}

	public void deleteSession(Movie movie, Date time) {
		Session session = getSession(movie, time);
		deleteSession(session);

	}

	public void deleteSession(Session session) {
		Movie m = session.getMovie();
		pm.deletePersistent(m);
		pm.deletePersistent(session);
	}

	// User methods
	/**
	 * 
	 * @param c User to register
	 * @return <b>true</b> if sucess <b>false</b> if fail (user already exists)
	 */
	public boolean registerClient(User c) {
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
	 * @return The client corresponding to the given credentials, or <b>null</b> if
	 *         no user exists with those parameters.
	 */
	public User getClient(String email, String password) {
		Query<User> q = pm.newQuery(User.class);
		q.setFilter("email == em && password == pw");
		q.declareParameters("java.lang.String em, java.lang.String pw");
		User c = q.setParameters(email, password).executeUnique();
		return c;
	}

	public void deleteClient(String email, String password) {
		User c = getClient(email, password);
		deleteClient(c);
	}

	public void deleteClient(User c) {
		if (c.getPurchases() != null) {
			for (Ticket ticket : c.getPurchases()) {
				pm.deletePersistent(ticket);
			}
		}
		pm.deletePersistent(c);
	}

	public void deleteTicket(Ticket t) {
		pm.deletePersistent(t);
	}

	public <T> T getObjectById(Class<T> c, long id) {
		T obj = pm.getObjectById(c, id);
		return obj;
	}

	public List<Ticket> getTickets(UserDTO userDTO) {
		Query<User> q = pm.newQuery(User.class);
		q.setFilter("id == user_id");
		q.declareParameters("java.lang.Integer user_id");
		User user = q.setParameters(userDTO.getUserID()).executeUnique();
		return user.getPurchases();
	}
}
