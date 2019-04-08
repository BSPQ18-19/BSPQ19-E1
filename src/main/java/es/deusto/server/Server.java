package es.deusto.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.server.jdo.User;
import es.deusto.server.jdo.Message;


public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	protected Server() throws RemoteException {
		super();
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}
	
	protected void finalize () throws Throwable {
		if (tx.isActive()) {
            tx.rollback();
        }
        pm.close();
	}

	@SuppressWarnings("unchecked")
	public String sayMessage(String login, String password, String message) throws RemoteException {
		User user = null;
		try{
			tx.begin();
			System.out.println("Creating query ...");
			
			
			Query<User> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + login + "\" &&  password == \"" + password + "\"");
			q.setUnique(true);
			user = (User)q.execute();
			
			System.out.println("User retrieved: " + user);
			if (user != null)  {
				Message message1 = new Message(message);
				user.getMessages().add(message1);
				pm.makePersistent(user);					 
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		
		}
		
		if (user != null) {
			cont++;
			System.out.println(" * Client number: " + cont);
			return message;
		} else {
			throw new RemoteException("Login details supplied for message delivery are not correct");
		} 
	}
	
	public void registerUser(String login, String password) {
		try
        {	
            tx.begin();
            System.out.println("Checking whether the user already exits or not: '" + login +"'");
			User user = null;
			try {
				user = pm.getObjectById(User.class, login);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception launched: " + jonfe.getMessage());
			}
			System.out.println("User: " + user);
			if (user != null) {
				System.out.println("Setting password user: " + user);
				user.setPassword(password);
				System.out.println("Password set user: " + user);
			} else {
				System.out.println("Creating user: " + user);
				user = new User(login, password);
				pm.makePersistent(user);					 
				System.out.println("User created: " + user);
			}
			tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
        }
		
		
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

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
