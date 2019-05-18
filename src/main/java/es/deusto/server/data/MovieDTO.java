package es.deusto.server.data;

import java.io.Serializable;

import es.deusto.server.jdo.Movie;

public class MovieDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5235775776186342746L;
	public long id;
	public String title;
	public String synopsis;
	public int duration;
	public String genre;
	public String director;
	
	public MovieDTO() {};
	
	public MovieDTO(Movie movie)
	{
		id = movie.getId();
		title = new String(movie.getTitle());
		synopsis = new String(movie.getSynopsis());
		duration = movie.getDuration();
		genre = new String(movie.getGenre());
		director = new String(movie.getDirector());
	}
}
