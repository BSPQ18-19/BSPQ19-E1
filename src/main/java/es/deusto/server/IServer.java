package es.deusto.server;

import es.deusto.server.data.*;
import es.deusto.server.jdo.Movie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
	void addMovie(String title, String director, List<String> cast) throws RemoteException;
	void deleteMovie(String title) throws RemoteException;
	Movie getMovie(String title) throws RemoteException;
	List<MovieDTO> searchMovies(String query) throws RemoteException;
	List<SessionDTO> getSessionsForMovie(MovieDTO mdto) throws RemoteException;
	List<MovieDTO> getMoviesForDay(int year, int month, int day) throws RemoteException;
	UserDTO logIn(String email, String password) throws RemoteException;
	boolean logOut(UserDTO user) throws RemoteException;
}
