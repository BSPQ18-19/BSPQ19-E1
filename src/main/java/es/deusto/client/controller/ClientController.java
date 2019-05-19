package es.deusto.client.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import es.deusto.client.logger.ClientLogger;
import es.deusto.client.remote.RMIServiceLocator;
import es.deusto.server.data.SessionDTO;
import es.deusto.server.data.UserDTO;
import es.deusto.server.data.UserDetailsDTO;

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

	public UserDetailsDTO getUserDetails() {
		try {
			UserDetailsDTO details = rsl.getClientManager().getUserDetails(loggedUser);
			return details;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new UserDetailsDTO();
		}
	}
	
	public boolean updateUser(UserDetailsDTO details) {
		try {
			UserDTO newuser = rsl.getClientManager().updateUser(details);
			loggedUser = newuser;
			return true; // Success
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean registerUser(UserDetailsDTO user) {
        boolean result = false;
        try {
            result = rsl.getClientManager().registerUser(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public List<SessionDTO> getSessions(LocalDate date){
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
		
		List<SessionDTO> sessions;
		
		try {
			sessions = rsl.getClientManager().getSessionsForDay(year, month, day);
			return sessions;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
