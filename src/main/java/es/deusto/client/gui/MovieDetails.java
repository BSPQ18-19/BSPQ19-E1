package es.deusto.client.gui;

import javax.swing.JPanel;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.MovieDTO;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MovieDetails extends JPanel {
	private JTextField textField_title;
	private JTextField textField_synopsis;
	private JTextField textField_duration;
	private JTextField textField_genre;
	private JTextField textField_director;
	private JTextField textField_cast;

	/**
	 * Create the panel.
	 */
	public MovieDetails(Client client) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel(client.text.getString("movieTitle"));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		
		textField_title = new JTextField();
		GridBagConstraints gbc_textField_title = new GridBagConstraints();
		gbc_textField_title.insets = new Insets(0, 0, 5, 0);
		gbc_textField_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_title.gridx = 1;
		gbc_textField_title.gridy = 0;
		add(textField_title, gbc_textField_title);
		textField_title.setColumns(10);
		
		JLabel lblSynopsis = new JLabel(client.text.getString("movieSynopsis"));
		GridBagConstraints gbc_lblSynopsis = new GridBagConstraints();
		gbc_lblSynopsis.insets = new Insets(0, 0, 5, 5);
		gbc_lblSynopsis.anchor = GridBagConstraints.EAST;
		gbc_lblSynopsis.gridx = 0;
		gbc_lblSynopsis.gridy = 1;
		add(lblSynopsis, gbc_lblSynopsis);
		
		textField_synopsis = new JTextField();
		GridBagConstraints gbc_textField_synopsis = new GridBagConstraints();
		gbc_textField_synopsis.insets = new Insets(0, 0, 5, 0);
		gbc_textField_synopsis.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_synopsis.gridx = 1;
		gbc_textField_synopsis.gridy = 1;
		add(textField_synopsis, gbc_textField_synopsis);
		textField_synopsis.setColumns(10);
		
		JLabel lblDurationmin = new JLabel(client.text.getString("duration"));
		GridBagConstraints gbc_lblDurationmin = new GridBagConstraints();
		gbc_lblDurationmin.anchor = GridBagConstraints.EAST;
		gbc_lblDurationmin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDurationmin.gridx = 0;
		gbc_lblDurationmin.gridy = 2;
		add(lblDurationmin, gbc_lblDurationmin);
		
		textField_duration = new JTextField();
		GridBagConstraints gbc_textField_duration = new GridBagConstraints();
		gbc_textField_duration.insets = new Insets(0, 0, 5, 0);
		gbc_textField_duration.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_duration.gridx = 1;
		gbc_textField_duration.gridy = 2;
		add(textField_duration, gbc_textField_duration);
		textField_duration.setColumns(10);
		
		JLabel lblGenre = new JLabel(client.text.getString("genre"));
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.anchor = GridBagConstraints.EAST;
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 3;
		add(lblGenre, gbc_lblGenre);
		
		textField_genre = new JTextField();
		GridBagConstraints gbc_textField_genre = new GridBagConstraints();
		gbc_textField_genre.insets = new Insets(0, 0, 5, 0);
		gbc_textField_genre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_genre.gridx = 1;
		gbc_textField_genre.gridy = 3;
		add(textField_genre, gbc_textField_genre);
		textField_genre.setColumns(10);
		
		JLabel lblDirector = new JLabel(client.text.getString("director"));
		GridBagConstraints gbc_lblDirector = new GridBagConstraints();
		gbc_lblDirector.anchor = GridBagConstraints.EAST;
		gbc_lblDirector.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirector.gridx = 0;
		gbc_lblDirector.gridy = 4;
		add(lblDirector, gbc_lblDirector);
		
		textField_director = new JTextField();
		GridBagConstraints gbc_textField_director = new GridBagConstraints();
		gbc_textField_director.insets = new Insets(0, 0, 5, 0);
		gbc_textField_director.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_director.gridx = 1;
		gbc_textField_director.gridy = 4;
		add(textField_director, gbc_textField_director);
		textField_director.setColumns(10);
		
		JLabel lblCast = new JLabel(client.text.getString("movieCast"));
		GridBagConstraints gbc_lblCast = new GridBagConstraints();
		gbc_lblCast.insets = new Insets(0, 0, 5, 5);
		gbc_lblCast.anchor = GridBagConstraints.EAST;
		gbc_lblCast.gridx = 0;
		gbc_lblCast.gridy = 5;
		add(lblCast, gbc_lblCast);
		
		textField_cast = new JTextField();
		GridBagConstraints gbc_textField_cast = new GridBagConstraints();
		gbc_textField_cast.insets = new Insets(0, 0, 5, 0);
		gbc_textField_cast.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_cast.gridx = 1;
		gbc_textField_cast.gridy = 5;
		add(textField_cast, gbc_textField_cast);
		textField_cast.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
		add(panel, gbc_panel);
		
		JButton btnAdd = new JButton(client.text.getString("addMovie"));
		panel.add(btnAdd);
		btnAdd.addActionListener((e) -> {
			MovieDTO movie = new MovieDTO();
			movie.director = textField_director.getText();
			movie.duration = Integer.parseInt(textField_duration.getText());
			movie.genre = textField_genre.getText();
			movie.synopsis = textField_synopsis.getText();
			movie.title = textField_title.getText();
			String[] cast = textField_cast.getText().split(",");
			boolean success = ClientController.getController().addMovie(movie, cast);
			if (success) {
				client.switchToAdminHome();
			}
		});
		
		JButton btnCancel = new JButton(client.text.getString("cancel"));
		panel.add(btnCancel);
		btnCancel.addActionListener((e) -> {
			client.switchToAdminHome();
		});
		
	}

}
