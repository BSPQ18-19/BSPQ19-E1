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
    private ArrayList<UserDTO> currentHotels;

    private ClientController() {
        rsl = RMIServiceLocator.getServiceLocator();
        log = ClientLogger.getLogger();
        log.info("HotelManagementController initialized");
        this.currentHotels = new ArrayList<>();
    }

    public static ClientController getController() {
        return controller;
    }

    public UserDTO signInGuest(String name, String email, String password, String phone, String address) throws RemoteException {
        log.info("signInGuest called");
        UserDTO result = rsl.getHotelManager().signInGuest(name, email, password, phone, address);
        if(result != null)
            log.info("Signed in user with email: " + email);
        else
            log.info("Did not signed in user with email: " + email);
        return result;
    }

    public UserDTO logIn(String email, String password) throws RemoteException {
        if(loggedUser != null)
            logOut();
        loggedUser = rsl.getHotelManager().logIn(email, password);
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
        rsl.getHotelManager().logOut(loggedUser);
        log.info("Logged out user with ID: " + loggedUser.getUserID());
        loggedUser = null;
        return true;
    }

    public UserDTO getLoggedUser() {
        return loggedUser;
    }
}
