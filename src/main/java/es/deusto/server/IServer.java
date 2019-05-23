package es.deusto.server;

import es.deusto.server.data.*;
import es.deusto.server.jdo.Movie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface IServer extends Remote {
	void addMovie(String title, String director, List<String> cast) throws RemoteException;
	void deleteMovie(String title) throws RemoteException;
	void addSession(MovieDTO movie, Date time) throws RemoteException;
	void deleteSession(SessionDTO session) throws RemoteException;
	MovieDTO getMovie(String title) throws RemoteException;
	List<MovieDTO> searchMovies(String query) throws RemoteException;
	List<SessionDTO> getSessionsForMovie(MovieDTO mdto) throws RemoteException;
	List<SessionDTO> getSessionsForDay(int year, int month, int day) throws RemoteException;
	boolean buyTickets(UserDTO user, SessionDTO session, int amount) throws RemoteException;
	UserDTO logIn(String email, String password) throws RemoteException;
	boolean logOut(UserDTO user) throws RemoteException;
	UserDetailsDTO getUserDetails(UserDTO user) throws RemoteException;
	UserDTO updateUser(UserDetailsDTO details) throws RemoteException;
	boolean registerUser(UserDetailsDTO user) throws RemoteException;
}
