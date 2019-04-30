package es.deusto.client;


import es.deusto.client.gui.LoginPage;
import es.deusto.server.IServer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		boolean enableGUI = true;

		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		if(enableGUI) {
			JFrame frame = new JFrame("Login");
			frame.setContentPane(new LoginPage().$$$getRootComponent$$$());
			//frame.setPreferredSize(new Dimension(1200, 700));
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
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
}
