package es.deusto.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientManager extends UnicastRemoteObject implements IClientManager {

    protected ClientManager() throws RemoteException {
    }
}
