package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.deusto.server.data.MovieDTO;
import es.deusto.server.data.SessionDTO;
import es.deusto.server.data.UserDTO;
import es.deusto.server.jdo.DAO;
import es.deusto.server.jdo.Movie;
import es.deusto.server.jdo.Session;
import es.deusto.server.jdo.User;

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

	public void addMovie(String title, String director, List<String> cast) {
		dao.begin();
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setDirector(director);
		movie.setCast(cast);
		dao.storeObject(movie);
		dao.end();
	}

	public Movie getMovie(String title) {
		dao.begin();
		Movie movie = dao.getMovie(title);
		dao.end();
		return movie;
	}

	public void deleteMovie(String title) {
		dao.begin();
		dao.deleteMovie(title);
		dao.end();
	}

	public UserDTO logIn(String email, String password) throws RemoteException {
		dao.begin();
		User user = dao.getClient(email, password);
		dao.end();
		UserDTO userDTO = new UserDTO(user);
		return userDTO;
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
		Movie movie = dao.getMovie(mdto.id);
		List<Session> sessions = movie.getSessions();
		List<SessionDTO> sessionsDTO = new ArrayList<>(sessions.size());
		for (Session s : sessions) {
			sessionsDTO.add(new SessionDTO(s));
		}
		dao.end();
		return sessionsDTO;
	}

	@Override
	public List<MovieDTO> getMoviesForDay(int year, int month, int day) throws RemoteException {
		dao.begin();
		List<Session> sessions = dao.getSessions(year, month, day);
		// This should make repeated movies not save twice
		Set<Movie> moviesset = new HashSet<>();
		for (Session s : sessions) {
			moviesset.add(s.getMovie());
		}
		List<MovieDTO> movieslist = new ArrayList<>(moviesset.size());
		for (Movie m : moviesset) {
			movieslist.add(new MovieDTO(m));
		}
		dao.end();
		return movieslist;
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
}
