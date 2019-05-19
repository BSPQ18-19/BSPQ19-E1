package es.deusto.client;


import es.deusto.client.gui.EditProfile;
import es.deusto.client.gui.HomePage;
import es.deusto.client.gui.Login;
import es.deusto.client.gui.Register;
import es.deusto.client.remote.RMIServiceLocator;
import es.deusto.server.IServer;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

	private IServer server;
	private JFrame frame;

	public static void main(String[] args) {

		boolean enableGUI = true;

		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] User.User [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		RMIServiceLocator.getServiceLocator().setService(args[0], args[1], args[2]);

		if(enableGUI) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Client window = new Client();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else {

			try {
				String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
				IServer objHello = (IServer) java.rmi.Naming.lookup(name);
				int choiceentry = -1;
				Scanner scanchoice = new Scanner(System.in);
				while (choiceentry < 1 || choiceentry > 3) {
					System.out.println("Enter \"1 - Add Movie\", \"2 - Delete Movie\"");
					if (scanchoice.hasNextInt())
						choiceentry = scanchoice.nextInt();
				}

				java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
				java.io.BufferedReader stdin = new java.io.BufferedReader(inputStreamReader);
				switch (choiceentry) {
					case 1:
						System.out.println("Add Movie");
						System.out.println("Title:");
						String title = stdin.readLine();
						System.out.println("Director:");
						String director = stdin.readLine();
						System.out.println("Cast (separated by tabs):");
						List<String> cast = Arrays.asList(stdin.readLine().split("\t"));
						objHello.addMovie(title, director, cast);
						System.out.println("Added Movie");
						break;
					case 2:
						System.out.println("Add Movie");
						System.out.println("Title:");
						String titleToDelete = stdin.readLine();
						objHello.deleteMovie(titleToDelete);
						break;
				}

			} catch (Exception e) {
				System.err.println("RMI Example exception: " + e.getMessage());
				e.printStackTrace();
			}

		}
	}

	public void accessRMIServer(String IP, String port, String name) throws RemoteException, NotBoundException, MalformedURLException {
		String lookupName = "//" + IP + ":" + port + "/" + name;
		this.server = (IServer) java.rmi.Naming.lookup(lookupName);
	}

	public void addMovie(String title, String director, List<String> actorCast) throws RemoteException {
	    this.server.addMovie(title, director, actorCast);
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Login(this));
	}
	
	public void switchToRegister() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new Register(this));
		frame.setTitle("Register");
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	public void switchToHomePage() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new HomePage(this));
		frame.setTitle("Home Page");
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	public void switchToEditProfile() {
		frame.getContentPane().removeAll();
		
		frame.getContentPane().add(new EditProfile(this));
		frame.setTitle("Edit Profile");
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	public void incorrectLoginAlert() {
		JOptionPane.showMessageDialog(frame, "Incorrect Login", "Incorrect email or password", JOptionPane.WARNING_MESSAGE);
	}
	
	
}
