package es.deusto.client.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.MovieDTO;
import es.deusto.server.data.SessionDTO;

import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;

public class BuyTickets extends JPanel {

	/**
	 * Create the panel.
	 */
	public BuyTickets(Client client, SessionDTO session) {
		MovieDTO movie = ClientController.getController().getMovie(session.movieTitle);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 0, 0, 62, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel(movie.title);
		lblTitle.setFont(new Font("Sans-serif", Font.BOLD, 14));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		add(lblTitle, gbc_lblTitle);
		
		JLabel lblSynopsis = new JLabel(movie.synopsis);
		GridBagConstraints gbc_lblSynopsis = new GridBagConstraints();
		gbc_lblSynopsis.insets = new Insets(0, 0, 5, 5);
		gbc_lblSynopsis.anchor = GridBagConstraints.WEST;
		gbc_lblSynopsis.gridx = 1;
		gbc_lblSynopsis.gridy = 2;
		add(lblSynopsis, gbc_lblSynopsis);
		
		JLabel lblDuration = new JLabel(client.text.getString("duration") + movie.duration + " min");
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuration.anchor = GridBagConstraints.WEST;
		gbc_lblDuration.gridx = 1;
		gbc_lblDuration.gridy = 3;
		add(lblDuration, gbc_lblDuration);
		
		JLabel lblGenre = new JLabel(client.text.getString("genre") + movie.genre);
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.anchor = GridBagConstraints.WEST;
		gbc_lblGenre.gridx = 1;
		gbc_lblGenre.gridy = 4;
		add(lblGenre, gbc_lblGenre);
		
		JLabel lblDirector = new JLabel(client.text.getString("duration") + movie.director);
		GridBagConstraints gbc_lblDirector = new GridBagConstraints();
		gbc_lblDirector.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirector.anchor = GridBagConstraints.WEST;
		gbc_lblDirector.gridx = 1;
		gbc_lblDirector.gridy = 5;
		add(lblDirector, gbc_lblDirector);
		
		JLabel lblAmount = new JLabel(client.text.getString("amount"));
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.anchor = GridBagConstraints.WEST;
		gbc_lblAmount.gridx = 1;
		gbc_lblAmount.gridy = 6;
		add(lblAmount, gbc_lblAmount);
		
		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 6;
		add(spinner, gbc_spinner);
		
		JButton btnBuy = new JButton(client.text.getString("buy"));
		GridBagConstraints gbc_btnBuy = new GridBagConstraints();
		gbc_btnBuy.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuy.gridx = 3;
		gbc_btnBuy.gridy = 6;
		add(btnBuy, gbc_btnBuy);
		btnBuy.addActionListener((e) -> {
			System.out.println(session.id + "" + spinner.getValue());
			boolean success = ClientController.getController().buyMovie(session, (int) spinner.getValue());
			if(success) {
				JOptionPane.showMessageDialog(this, client.text.getString("buyTicketsOK"));
			} else {
				JOptionPane.showMessageDialog(this, client.text.getString("buyTicketsError"));
			}
			client.switchToHomePage();
		});
		
		JButton btnCancel = new JButton(client.text.getString("cancel"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 7;
		add(btnCancel, gbc_btnCancel);
		
		btnCancel.addActionListener((e) -> {
			client.switchToMovieSearch();
		});
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 3;
		gbc_horizontalStrut.gridy = 9;
		add(horizontalStrut, gbc_horizontalStrut);

	}

}
