package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.deusto.server.data.*;
import es.deusto.server.jdo.DAO;
import es.deusto.server.jdo.Movie;
import es.deusto.server.jdo.Session;
import es.deusto.server.jdo.Ticket;
import es.deusto.server.jdo.User;

import javax.swing.*;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	// This probably isn't a very good idea as all clients
	// will be using the same DAO instance so transactions may collide
	// But it can be easily changed
	private DAO dao;

	public Server() throws RemoteException {
		super();
		this.dao = new DAO();
	}

	public void addMovie(String title, String director, List<String> cast) throws RemoteException {
		dao.begin();
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setDirector(director);
		movie.setCast(cast);
		dao.storeObject(movie);
		dao.end();
	}
	public void addSession(MovieDTO movie, Date time) throws RemoteException {
		dao.begin();
		Session session = new Session();
		Movie m = dao.getObjectById(Movie.class, movie.id);
		session.setMovie(m);
		session.setTime(time);
		dao.storeObject(session);
		dao.end();
	}
	public MovieDTO getMovie(String title) throws RemoteException {
		dao.begin();
		Movie movie = dao.getMovie(title);
		MovieDTO mdto = new MovieDTO(movie);
		dao.end();
		return mdto;
	}

	public void deleteMovie(String title) throws RemoteException {
		dao.begin();
		dao.deleteMovie(title);
		dao.end();
	}
	public void deleteSession(SessionDTO session) throws RemoteException {//
		dao.begin();
		Session s = dao.getObjectById(Session.class, session.id);
		dao.deleteSession(s);
		dao.end();
		
	}

	public UserDTO logIn(String email, String password) throws RemoteException {
		dao.begin();
		User user = dao.getClient(email, password);
		dao.end();
		if(user != null) {
			return new UserDTO(user);
		} else {
			return null;
		}
	}

	// We don't need this I think
	public boolean logOut(UserDTO user) throws RemoteException {
		return false;
	}


	@Override
	public List<MovieDTO> searchMovies(String query) throws RemoteException {
		dao.begin();
		List<Movie> movies = dao.searchMovieByTitle(query);
		List<MovieDTO> moviesDTO = new ArrayList<>(movies.size());
		for(Movie m : movies) {
			moviesDTO.add(new MovieDTO(m));
		}
		dao.end();
		return moviesDTO;
	}

	@Override
	public List<SessionDTO> getSessionsForMovie(MovieDTO mdto) throws RemoteException {
		dao.begin();
		Movie movie = dao.getObjectById(Movie.class, mdto.id);
		List<Session> sessions = movie.getSessions();
		List<SessionDTO> sessionsDTO = new ArrayList<>(sessions.size());
		for (Session s : sessions) {
			sessionsDTO.add(new SessionDTO(s));
		}
		dao.end();
		return sessionsDTO;
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		System.out.println(args);
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		
		try {
			IServer objServer = new Server();
			Naming.rebind(name, objServer);
			System.out.println("Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();
		} catch (Exception e) {
			System.err.println("Hello exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public List<SessionDTO> getSessionsForDay(int year, int month, int day) throws RemoteException {
		dao.begin();
		List<Session> sessions = dao.getSessions(year, month, day);
		List<SessionDTO> sessionsDTO = new ArrayList<>(sessions.size());
		
		for (Session s : sessions) {
			sessionsDTO.add(new SessionDTO(s));
		}
		dao.end();
		return sessionsDTO;
	}

	@Override
	public boolean buyTickets(UserDTO user, SessionDTO session, int amount) throws RemoteException {
		dao.begin();
		User u = dao.getObjectById(User.class, user.getUserID());
		Session s = dao.getObjectById(Session.class, session.id);
		if (u == null || s == null || amount < 1) {
			dao.end();
			return false;
		}
		else {
			for (int i = 0; i < amount; i++) {
				Ticket ticket = new Ticket(s, u);
				u.getPurchases().add(ticket);
			}
			dao.end();
			return true;
		}
	}

	@Override
	public UserDetailsDTO getUserDetails(UserDTO user) throws RemoteException {
		dao.begin();
		User u = dao.getObjectById(User.class, user.getUserID());
		if (u == null) 
			return null;
		
		UserDetailsDTO details = new UserDetailsDTO(u);
		dao.end();
		return details;
	}

	@Override
	public UserDTO updateUser(UserDetailsDTO details) throws RemoteException {
		dao.begin();
		User u = dao.getObjectById(User.class, details.id);
		u.updateDetails(details);
		UserDTO udto = new UserDTO(u);
		dao.end();
		return udto;
	}

	@Override
	public boolean registerUser(UserDetailsDTO user) throws RemoteException {
		dao.begin();
		User u = new User(user);
		boolean result = dao.registerClient(u);
		dao.end();
		return result;
	}

	@Override
	public List<TicketDTO> getTickets(UserDTO user) throws RemoteException {
		dao.begin();
		List<Ticket> list = dao.getTickets(user);
		List<TicketDTO> listDTO = new ArrayList<>(list.size());
		for(Ticket ticket: list){
			listDTO.add(new TicketDTO(ticket));
		}
		dao.end();
		return listDTO;
	}

	@Override
	public List<MovieDTO> getAllMovies() throws RemoteException {
		dao.begin();
		List<Movie> movies = dao.getMovies();
		List<MovieDTO> dtos = new ArrayList<>();
		for (Movie m : movies) {
			dtos.add(new MovieDTO(m));
		}
		
		return null;
	}

	@Override
	public void deleteMovie(MovieDTO movie) throws RemoteException {
		dao.begin();
		Movie m = dao.getObjectById(Movie.class, movie.id);
		dao.deleteMovie(m);
		dao.end();
		
	}

}
