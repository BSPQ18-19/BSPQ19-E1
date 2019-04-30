package es.deusto.server.remote;

import es.deusto.server.data.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientManager extends Remote{
    UserDTO logIn(String email, String password) throws RemoteException;
    boolean logOut(UserDTO user) throws RemoteException;
}
