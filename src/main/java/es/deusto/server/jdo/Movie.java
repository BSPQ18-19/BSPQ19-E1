package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Movie {
	
	@Unique
	private String title;
	private String synopsis;
	private int duration;
	private String genre;
	
	@Convert(StringListToStringConverter.class)
	private List<String> cast;
	private String director;
	
	public Movie(String title, String synopsis, int duration, String genre, List<String> cast, String director) {
		super();
		this.title = title;
		this.synopsis = synopsis;
		this.duration = duration;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
	}
	
	
	
	
	public Movie() {
		cast = new ArrayList<String>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	
}
