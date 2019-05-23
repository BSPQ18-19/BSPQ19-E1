package es.deusto.client.gui;

import javax.swing.JPanel;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.UserDetailsDTO;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.Box;

public class EditProfile extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	private UserDetailsDTO details;

	/**
	 * Create the panel.
	 */
	public EditProfile(Client client) {
		details = ClientController.getController().getUserDetails();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 0;
		add(verticalStrut, gbc_verticalStrut);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 1;
		gbc_horizontalStrut.gridy = 1;
		add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel lblName = new JLabel(client.text.getString("name"));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setText(details.name);
		
		JLabel lblSurname = new JLabel(client.text.getString("surname"));
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 2;
		gbc_lblSurname.gridy = 3;
		add(lblSurname, gbc_lblSurname);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(20);
		textField_1.setText(details.surname);
		
		JLabel lblPhone = new JLabel(client.text.getString("phone"));
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 2;
		gbc_lblPhone.gridy = 4;
		add(lblPhone, gbc_lblPhone);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 4;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		textField_2.setText(details.phone);
		
		JLabel lblChangePassword = new JLabel(client.text.getString("newPassword"));
		GridBagConstraints gbc_lblChangePassword = new GridBagConstraints();
		gbc_lblChangePassword.anchor = GridBagConstraints.EAST;
		gbc_lblChangePassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblChangePassword.gridx = 2;
		gbc_lblChangePassword.gridy = 5;
		add(lblChangePassword, gbc_lblChangePassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		add(passwordField, gbc_passwordField);
		
		JLabel lblRepeatPassword = new JLabel(client.text.getString("repeatPassword"));
		GridBagConstraints gbc_lblRepeatPassword = new GridBagConstraints();
		gbc_lblRepeatPassword.anchor = GridBagConstraints.EAST;
		gbc_lblRepeatPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepeatPassword.gridx = 2;
		gbc_lblRepeatPassword.gridy = 6;
		add(lblRepeatPassword, gbc_lblRepeatPassword);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 6;
		add(passwordField_1, gbc_passwordField_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 8;
		add(panel, gbc_panel);
		
		JButton btnSaveChanges = new JButton(client.text.getString("saveChanges"));
		panel.add(btnSaveChanges);
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details.name = textField.getText();
				details.surname = textField_1.getText();
				details.phone = textField_2.getText();
				if (passwordField.getPassword().length != 0) {
						if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
							details.password = new String(passwordField.getPassword());
						} else {
							JOptionPane.showMessageDialog(EditProfile.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
				}
				boolean success = ClientController.getController().updateUser(details);
				if (success) {
					JOptionPane.showMessageDialog(EditProfile.this, "Updated successfully");
					client.switchToHomePage();
				} else {
					JOptionPane.showMessageDialog(EditProfile.this, "An error has ocurred", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton(client.text.getString("cancel"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.switchToHomePage();
			}
		});
		panel.add(btnNewButton_1);

	}

}
