package es.deusto.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	
	String sayMessage(String login, String password, String message) throws RemoteException;
	void registerUser(String login, String password) throws RemoteException;

}
