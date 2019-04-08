package es.deusto.server.jdo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Movie {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	
	@Unique
	private String title;
	private String synopsis;
	private int duration;
	private String genre;
	
	@Convert(StringListToStringConverter.class)
	private List<String> cast;
	private String director;
	
	@Persistent(mappedBy = "movie")
	private List<Session> sessions;
	
	public Movie(String title, String synopsis, int duration, String genre, List<String> cast, String director) {
		super();
		this.title = title;
		this.synopsis = synopsis;
		this.duration = duration;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
		sessions = new ArrayList<Session>();
	}
	
	
	
	
	public Movie() {
		cast = new ArrayList<String>();
		sessions = new ArrayList<Session>();
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
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public long getId() {
		return id;
	}
	
	
}
