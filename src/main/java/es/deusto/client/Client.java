package es.deusto.client;


import es.deusto.server.IServer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IServer objHello = (IServer) java.rmi.Naming.lookup(name);
			// Register to be allowed to send messages
			int choiceentry = -1;
			Scanner scanchoice = new Scanner(System.in);
			while(choiceentry < 1 || choiceentry > 3) {
				System.out.println("Enter \"1 - Add Movie\", \"2 - Delete Movie\", \"3\" or \"4\"");
				if(scanchoice.hasNextInt())
					choiceentry = scanchoice.nextInt();
			}

			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			switch(choiceentry){
				case 1:
					System.out.println("Add Movie");
					System.out.println("Title:");
					String title  = stdin.readLine();
					System.out.println("Director:");
					String director  = stdin.readLine();
					System.out.println("Cast:");
					List<String> cast = Arrays.asList(stdin.readLine().split(" "));
					objHello.addMovie(title, director, cast);
					System.out.println("Added Movie");
					break;
				case 2:
					System.out.println("Add Movie");
					System.out.println("Title:");
					String titleToDelete  = stdin.readLine();
					objHello.deleteMovie(titleToDelete);
					break;
				case 3:
					//do logic
					break;
			}
			// objHello.registerUser("dipina", "dipina");
			// System.out.println("* Message coming from the server: '" + objHello.sayMessage("dipina", "dipina", "This is a test!") + "'");
			
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
