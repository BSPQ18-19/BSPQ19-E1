package es.deusto.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
	void addMovie(String title, String director, List<String> cast) throws RemoteException;
	void deleteMovie(String title) throws RemoteException;
}
