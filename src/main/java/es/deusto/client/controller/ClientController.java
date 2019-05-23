package es.deusto.client.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.deusto.server.data.*;
import org.slf4j.Logger;
import es.deusto.client.logger.ClientLogger;
import es.deusto.client.remote.RMIServiceLocator;

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
			log.error(e.getMessage());
			return new UserDetailsDTO();
		}
	}
	
	public boolean updateUser(UserDetailsDTO details) {
		try {
			UserDTO newuser = rsl.getClientManager().updateUser(details);
			loggedUser = newuser;
			return true; // Success
		} catch (RemoteException e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public boolean registerUser(UserDetailsDTO user) {
        boolean result = false;
        try {
            result = rsl.getClientManager().registerUser(user);
        } catch (RemoteException e) {
            log.error(e.getMessage());
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
			log.error(e.getMessage());
			return null;
		}
	}

	public MovieDTO getMovie(String movieTitle) {
		try {
			MovieDTO movie = rsl.getClientManager().getMovie(movieTitle);
			return movie;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean buyMovie(SessionDTO session, int amount) {
		// TODO Auto-generated method stub
		try {
			return rsl.getClientManager().buyTickets(loggedUser, session, amount);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return false;
		}
	}

	public List<TicketDTO> getTickets(UserDTO user) {
		try {
			return rsl.getClientManager().getTickets(user);
		} catch (RemoteException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<MovieDTO> getMovies() {
		try {
			return rsl.getClientManager().getAllMovies();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean deleteMovie(MovieDTO movie) {
		try {
			rsl.getClientManager().deleteMovie(movie);
			return true;
		} catch (RemoteException e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
