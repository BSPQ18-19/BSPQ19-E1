package es.deusto.server;

import es.deusto.server.jdo.Movie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface IServer extends Remote {
	Movie getMovie(String title) throws RemoteException;
	void addMovie(String title, String director, List<String> cast) throws RemoteException;
	void deleteMovie(String title) throws RemoteException;
}
