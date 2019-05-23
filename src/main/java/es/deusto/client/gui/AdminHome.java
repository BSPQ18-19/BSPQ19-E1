package es.deusto.client.gui;

import javax.swing.JPanel;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.MovieDTO;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class AdminHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminHome(Client client) {
		setLayout(new BorderLayout(0, 0));
		
		DefaultListModel<MovieDTO> model = new DefaultListModel<>();
		JList<MovieDTO> movie_list = new JList<MovieDTO>();
		List<MovieDTO> movies = ClientController.getController().getMovies();
		for (MovieDTO m : movies) {
			model.addElement(m);
		}
		add(movie_list, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton btnViewSessions = new JButton(client.text.getString("viewSessions"));
		panel.add(btnViewSessions);
		
		JButton btnUpdateDetails = new JButton(client.text.getString("updateDetails"));
		panel.add(btnUpdateDetails);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener((e) -> {
			MovieDTO movie = movie_list.getSelectedValue();
			boolean success = ClientController.getController().deleteMovie(movie);
			if (success) {
				model.removeElement(movie);
				JOptionPane.showMessageDialog(this, client.text.getString("movieDeletedOK"));
			} else {
				JOptionPane.showMessageDialog(this, client.text.getString("movieDeletedError"));
			}
		});
		panel.add(btnDelete);
		
		JButton btnLogout = new JButton(client.text.getString("logout"));
		panel.add(btnLogout);
		
		

	}

}
