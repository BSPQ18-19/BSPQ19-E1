package es.deusto.client.gui;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.SessionDTO;
import es.deusto.server.data.TicketDTO;
import es.deusto.server.jdo.Ticket;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.util.List;

public class HomePage extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePage(Client client) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 161, 67, 55, 83, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 23, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 0;
		add(verticalStrut_1, gbc_verticalStrut_1);
		
		JLabel lblLoggedInAs = new JLabel(client.text.getString("logged"));
		GridBagConstraints gbc_lblLoggedInAs = new GridBagConstraints();
		gbc_lblLoggedInAs.anchor = GridBagConstraints.WEST;
		gbc_lblLoggedInAs.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoggedInAs.gridx = 1;
		gbc_lblLoggedInAs.gridy = 1;
		add(lblLoggedInAs, gbc_lblLoggedInAs);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setText(ClientController.getController().getUserDetails().name);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 1;
		add(lblUsername, gbc_lblUsername);
		
		JButton btnEditProfile = new JButton(client.text.getString("editProfile"));
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.switchToEditProfile();
			}
		});
		GridBagConstraints gbc_btnEditProfile = new GridBagConstraints();
		gbc_btnEditProfile.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditProfile.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEditProfile.gridx = 3;
		gbc_btnEditProfile.gridy = 1;
		add(btnEditProfile, gbc_btnEditProfile);
		
		JButton btnLogout = new JButton(client.text.getString("logout"));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClientController.getController().logOut();
					client.switchToLogin();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogout.gridx = 4;
		gbc_btnLogout.gridy = 1;
		add(btnLogout, gbc_btnLogout);
		
		JLabel lblMovies = new JLabel(client.text.getString("latestMovies"));
		GridBagConstraints gbc_lblMovies = new GridBagConstraints();
		gbc_lblMovies.insets = new Insets(0, 0, 5, 5);
		gbc_lblMovies.gridx = 1;
		gbc_lblMovies.gridy = 2;
		add(lblMovies, gbc_lblMovies);
		
		JLabel lblYourTickets = new JLabel(client.text.getString("yourTickets"));
		GridBagConstraints gbc_lblYourTickets = new GridBagConstraints();
		gbc_lblYourTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lblYourTickets.gridx = 4;
		gbc_lblYourTickets.gridy = 2;
		add(lblYourTickets, gbc_lblYourTickets);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 3;
		add(horizontalStrut, gbc_horizontalStrut);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 3;
		add(list, gbc_list);

		DefaultListModel<SessionDTO> listModel = new DefaultListModel<>();
		List<TicketDTO> dtoList = ClientController.getController().getTickets(ClientController.getController().getLoggedUser());
		System.out.println(dtoList);
		if(dtoList != null){
			listModel.removeAllElements();
			for(TicketDTO ticket : dtoList){
				listModel.addElement(ticket.session);
			}
		}

		JList<SessionDTO> ticketsList = new JList<>(listModel);
		ticketsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_ticketsList = new GridBagConstraints();
		gbc_ticketsList.insets = new Insets(0, 0, 5, 5);
		gbc_ticketsList.fill = GridBagConstraints.BOTH;
		gbc_ticketsList.gridx = 4;
		gbc_ticketsList.gridy = 3;
		add(ticketsList, gbc_ticketsList);
		
		JButton btnSeeAvailableSessions = new JButton(client.text.getString("seeSessions"));
		btnSeeAvailableSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnSeeAvailableSessions = new GridBagConstraints();
		gbc_btnSeeAvailableSessions.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeeAvailableSessions.gridx = 1;
		gbc_btnSeeAvailableSessions.gridy = 4;
		add(btnSeeAvailableSessions, gbc_btnSeeAvailableSessions);
		
		JButton btnTicketDetails = new JButton(client.text.getString("ticketDetails"));
		GridBagConstraints gbc_btnTicketDetails = new GridBagConstraints();
		gbc_btnTicketDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btnTicketDetails.gridx = 4;
		gbc_btnTicketDetails.gridy = 4;
		add(btnTicketDetails, gbc_btnTicketDetails);
		
		JButton btnSearchMovies = new JButton(client.text.getString("searchMovies"));
		btnSearchMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.switchToMovieSearch();
			}
		});
		GridBagConstraints gbc_btnSearchMovies = new GridBagConstraints();
		gbc_btnSearchMovies.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearchMovies.gridx = 1;
		gbc_btnSearchMovies.gridy = 5;
		add(btnSearchMovies, gbc_btnSearchMovies);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 6;
		add(verticalStrut, gbc_verticalStrut);

	}
}
