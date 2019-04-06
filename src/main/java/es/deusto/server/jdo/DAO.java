package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.*;

public class DAO {
	
	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	private Transaction tx;

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
	
	private void rollback() {
		tx.rollback();
		pm.close();
	}
	
	public void storeObject(Object o) {
		pm.makePersistent(o);
	}
	
	public List<Movie> getMovies(){
		begin();
		Extent<Movie> ex = pm.getExtent(Movie.class, false);
		Query<Movie> q = pm.newQuery(ex);
		Collection<Movie> col = (Collection<Movie>) q.execute();
		List<Movie> list = new ArrayList<>(col);
		end();
		return list;
	}
	
	public Movie getMovie(String title) {
		if (tx == null || !tx.isActive()) 
			begin();
		Query<Movie> q = pm.newQuery(Movie.class);
		q.setUnique(true);
		q.setFilter("title == my_title");
		q.declareParameters("java.lang.String my_title");
		Movie movie = q.executeUnique();
		return movie;
	}
	
	public boolean deleteMovie(String title) {
		begin();
		Query<Movie> q = pm.newQuery(Movie.class);
		q.setUnique(true);
		q.setFilter("title == my_title");
		q.declareParameters("java.lang.String my_title");
		long number = q.deletePersistentAll(title);
		if (number == 1) {
			end();
			return true;
		}
		else
		{
			rollback();
			return false;
		}
	}
	
	public void cleanDatabase() {
		begin();
		Query<Movie> q = pm.newQuery(Movie.class);
		q.deletePersistentAll();
		end();
	}
	
}
