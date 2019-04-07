package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.List;

public class JDOTest {
	public static void main(String[] args) {
		DAO dao = new DAO();
		dao.cleanDatabase(Session.class);
		dao.cleanDatabase(Movie.class);
		dao.begin();
		System.out.println("Creating new movie");
		Movie movie = new Movie();
		movie.setTitle("movie");
		movie.setDirector("director");
		movie.setDirector("hhhh");
		List<String> cast = new ArrayList<String>();
		cast.add("actor1");
		cast.add("actor2");
		movie.setCast(cast);
		dao.storeObject(movie);
		dao.end();
		
		
		dao.begin();
		Movie movie2 = dao.getMovie("movie");
		Session s = new Session(2019, 04, 21, 17, 30);
		Session s2 = new Session(2019, 04, 21, 19, 00);
		s.setMovie(movie2);
		s2.setMovie(movie2);
		movie2.getSessions().add(s);
		movie2.getSessions().add(s2);
		dao.end();
		
		dao.begin();
		System.out.println("Getting sessions");
		List<Session> sessions = dao.getSessions(2019, 04, 21);
		for(Session ses : sessions) {
			System.out.println(ses.getMovie().getTitle());
			System.out.println(ses.getTime().toString());
		}
		
	}
}
