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

public class BuyTickets extends JPanel {

	/**
	 * Create the panel.
	 */
	public BuyTickets(Client client, SessionDTO session) {
		MovieDTO movie = ClientController.getController().getMovie(session.movieTitle);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel(movie.title);
		lblTitle.setFont(new Font("Sans-serif", Font.BOLD, 14));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		
		JLabel lblSynopsis = new JLabel(movie.synopsis);
		GridBagConstraints gbc_lblSynopsis = new GridBagConstraints();
		gbc_lblSynopsis.insets = new Insets(0, 0, 5, 5);
		gbc_lblSynopsis.anchor = GridBagConstraints.WEST;
		gbc_lblSynopsis.gridx = 0;
		gbc_lblSynopsis.gridy = 1;
		add(lblSynopsis, gbc_lblSynopsis);
		
		JLabel lblDuration = new JLabel("Duration: " + movie.duration + " min");
		GridBagConstraints gbc_lblDuration = new GridBagConstraints();
		gbc_lblDuration.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuration.anchor = GridBagConstraints.WEST;
		gbc_lblDuration.gridx = 0;
		gbc_lblDuration.gridy = 2;
		add(lblDuration, gbc_lblDuration);
		
		JLabel lblGenre = new JLabel("Genre: " + movie.genre);
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.anchor = GridBagConstraints.WEST;
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 3;
		add(lblGenre, gbc_lblGenre);
		
		JLabel lblDirector = new JLabel("Director:" + movie.director);
		GridBagConstraints gbc_lblDirector = new GridBagConstraints();
		gbc_lblDirector.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirector.anchor = GridBagConstraints.WEST;
		gbc_lblDirector.gridx = 0;
		gbc_lblDirector.gridy = 4;
		add(lblDirector, gbc_lblDirector);
		
		JLabel lblAmount = new JLabel("Amount:");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.anchor = GridBagConstraints.WEST;
		gbc_lblAmount.gridx = 0;
		gbc_lblAmount.gridy = 6;
		add(lblAmount, gbc_lblAmount);
		
		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 6;
		add(spinner, gbc_spinner);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		add(btnCancel, gbc_btnCancel);
		
		btnCancel.addActionListener((e) -> {
			client.switchToMovieSearch();
		});
		
		JButton btnBuy = new JButton("Buy");
		GridBagConstraints gbc_btnBuy = new GridBagConstraints();
		gbc_btnBuy.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuy.gridx = 1;
		gbc_btnBuy.gridy = 7;
		add(btnBuy, gbc_btnBuy);
		btnBuy.addActionListener((e) -> {
			boolean success = ClientController.getController().buyMovie(session, (int) spinner.getValue());
			if(success) {
				JOptionPane.showMessageDialog(this, "Successfully bought tickets");
			} else {
				JOptionPane.showMessageDialog(this, "An error has ocurred");
			}
			client.switchToHomePage();
		});

	}

}
