package es.deusto.server.remote;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import es.deusto.server.jdo.Movie;
import org.apache.log4j.Logger;
import es.deusto.server.data.UserDTO;


public class ClientManager extends UnicastRemoteObject implements IClientManager {

    protected ClientManager() throws RemoteException {
        final long serialVersionUID = 1L;
        Set<UserDTO> loggedUsers;
        Logger log;
        Map<String, Movie> movies = new TreeMap<String, Movie>();
    }

    @Override
    public UserDTO logIn(String email, String password) throws RemoteException {
        return null;
    }

    @Override
    public boolean logOut(UserDTO user) throws RemoteException {
        return false;
    }
}
