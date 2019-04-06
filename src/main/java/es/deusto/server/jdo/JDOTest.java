package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.List;

public class JDOTest {
	public static void main(String[] args) {
		DAO dao = new DAO();
		
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
		System.out.println(movie2 == null);
		System.out.println("Title: " + movie2.getTitle());
		System.out.println("Director: " + movie2.getDirector());
		System.out.println("Cast: " + String.join(",", movie2.getCast()));
		dao.deleteMovie(movie2.getTitle());
		dao.end();
	}
}
