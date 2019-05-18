package es.deusto.client.gui;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

public class HomePage extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePage(Client client) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{161, 67, 55, 83, 100, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as: ");
		GridBagConstraints gbc_lblLoggedInAs = new GridBagConstraints();
		gbc_lblLoggedInAs.anchor = GridBagConstraints.WEST;
		gbc_lblLoggedInAs.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoggedInAs.gridx = 0;
		gbc_lblLoggedInAs.gridy = 0;
		add(lblLoggedInAs, gbc_lblLoggedInAs);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setText(ClientController.getController().getLoggedUser().getName());
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 0;
		add(lblUsername, gbc_lblUsername);
		
		JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.switchToEditProfile();
			}
		});
		GridBagConstraints gbc_btnEditProfile = new GridBagConstraints();
		gbc_btnEditProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditProfile.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEditProfile.gridx = 2;
		gbc_btnEditProfile.gridy = 0;
		add(btnEditProfile, gbc_btnEditProfile);
		
		JLabel lblMovies = new JLabel("Movies (select to see sessions)");
		GridBagConstraints gbc_lblMovies = new GridBagConstraints();
		gbc_lblMovies.insets = new Insets(0, 0, 5, 5);
		gbc_lblMovies.gridx = 0;
		gbc_lblMovies.gridy = 1;
		add(lblMovies, gbc_lblMovies);
		
		JLabel lblYourTickets = new JLabel("Your tickets");
		GridBagConstraints gbc_lblYourTickets = new GridBagConstraints();
		gbc_lblYourTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lblYourTickets.gridx = 3;
		gbc_lblYourTickets.gridy = 1;
		add(lblYourTickets, gbc_lblYourTickets);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 2;
		add(list, gbc_list);
		
		JList list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 5, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 3;
		gbc_list_1.gridy = 2;
		add(list_1, gbc_list_1);
		
		JButton btnSeeAvailableSessions = new JButton("See available sessions");
		GridBagConstraints gbc_btnSeeAvailableSessions = new GridBagConstraints();
		gbc_btnSeeAvailableSessions.insets = new Insets(0, 0, 0, 5);
		gbc_btnSeeAvailableSessions.gridx = 0;
		gbc_btnSeeAvailableSessions.gridy = 3;
		add(btnSeeAvailableSessions, gbc_btnSeeAvailableSessions);
		
		JButton btnTicketDetails = new JButton("Ticket details");
		GridBagConstraints gbc_btnTicketDetails = new GridBagConstraints();
		gbc_btnTicketDetails.insets = new Insets(0, 0, 0, 5);
		gbc_btnTicketDetails.gridx = 3;
		gbc_btnTicketDetails.gridy = 3;
		add(btnTicketDetails, gbc_btnTicketDetails);

	}
}
