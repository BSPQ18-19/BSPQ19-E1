package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import es.deusto.server.data.UserDTO;
import es.deusto.server.jdo.DAO;
import es.deusto.server.jdo.Movie;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
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
		return null;
	}

	public boolean logOut(UserDTO user) throws RemoteException {
		return false;
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
