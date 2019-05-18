package es.deusto.server;

import es.deusto.server.data.UserDTO;
import es.deusto.server.jdo.Movie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
	void addMovie(String title, String director, List<String> cast) throws RemoteException;
	void deleteMovie(String title) throws RemoteException;
	Movie getMovie(String title) throws RemoteException;
	UserDTO logIn(String email, String password) throws RemoteException;
	boolean logOut(UserDTO user) throws RemoteException;
}
