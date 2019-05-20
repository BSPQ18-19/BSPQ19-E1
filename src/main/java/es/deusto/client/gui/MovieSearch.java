package es.deusto.client.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.SessionDTO;

public class MovieSearch extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5291853104390600502L;

	/**
	 * Create the panel.
	 */
	public MovieSearch(Client client) {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{65, 0};
		gbl_panel.rowHeights = new int[]{67, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 1;
		panel.add(btnSearch, gbc_btnSearch);
		
		DatePickerSettings s = new DatePickerSettings();
		s.setAllowEmptyDates(false);
		s.setFirstDayOfWeek(DayOfWeek.MONDAY);
		DatePicker picker = new DatePicker(s);
		GridBagConstraints gbc_picker = new GridBagConstraints();
		gbc_picker.gridx = 0;
		gbc_picker.gridy = 0;
		panel.add(picker, gbc_picker);
		
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		DefaultListModel<SessionDTO> listmodel = new DefaultListModel<>();
		JList<SessionDTO> list = new JList<>(listmodel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(list);
		
		btnSearch.addActionListener((e) -> {
			LocalDate date = picker.getDate();
			List<SessionDTO> sessions = ClientController.getController().getSessions(date);
			if (sessions != null) {
				listmodel.removeAllElements();
				//listmodel.addAll(sessions);
				for ( SessionDTO session : sessions) {
					listmodel.addElement(session);
				}
			}
		});
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		btnCancel.addActionListener((e) -> {
			client.switchToHomePage();
		});
		
		JButton btnSelect = new JButton("Select");
		panel_2.add(btnSelect);
		btnSelect.addActionListener((e) -> {
			SessionDTO session = list.getSelectedValue();
			client.switchToBuyTickets(session);
		});

	}

}
