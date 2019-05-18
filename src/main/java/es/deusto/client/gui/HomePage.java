package es.deusto.client.gui;

import es.deusto.client.controller.ClientController;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class HomePage extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePage() {
		
		JLabel lblLoggedInAs = new JLabel("Logged in as: ");
		add(lblLoggedInAs);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setText(ClientController.getController().getLoggedUser().getName());
		add(lblUsername);

	}

}
