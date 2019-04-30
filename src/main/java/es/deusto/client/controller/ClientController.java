package es.deusto.client.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import es.deusto.client.logger.ClientLogger;
import es.deusto.client.remote.RMIServiceLocator;
import es.deusto.server.data.UserDTO;

public class ClientController {

    private static ClientController controller = new ClientController();
    private RMIServiceLocator rsl;
    private UserDTO loggedUser = null;
    private Logger log;
    private ArrayList<UserDTO> currentUser;

    private ClientController() {
        rsl = RMIServiceLocator.getServiceLocator();
        log = ClientLogger.getLogger();
        log.info("getClientManagerController initialized");
        this.currentUser = new ArrayList<>();
    }

    public static ClientController getController() {
        return controller;
    }

    public UserDTO logIn(String email, String password) throws RemoteException {
        if(loggedUser != null)
            logOut();
        loggedUser = rsl.getClientManager().logIn(email, password);
        if(loggedUser != null)
            log.info("Logged in user with email: " + email);
        else
            log.info("Did not logged in user with email: " + email);
        return loggedUser;
    }

    public boolean logOut() throws RemoteException {
        if(loggedUser == null) {
            log.info("Did not logged out any user - no users were logged");
            return false;
        }
        rsl.getClientManager().logOut(loggedUser);
        log.info("Logged out user with ID: " + loggedUser.getUserID());
        loggedUser = null;
        return true;
    }

    public UserDTO getLoggedUser() {
        return loggedUser;
    }
}
