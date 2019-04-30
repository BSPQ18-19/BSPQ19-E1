package es.deusto.server.jdo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.jdo.annotations.*;
import javax.jdo.spi.Detachable;

@PersistenceCapable(detachable = "true")
public class Session {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	
	private Movie movie;
	private Date time;
	
	public Session(int year, int month, int day, int hour, int minute) {
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(year, month, day, hour, minute);
		time = calendar.getTime();
	}
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public long getId() {
		return id;
	}
	
	

}
