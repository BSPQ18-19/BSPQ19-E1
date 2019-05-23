package es.deusto.client.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.UserDTO;
import es.deusto.server.data.UserDetailsDTO;
import java.awt.FlowLayout;
import java.util.Arrays;

public class Register extends JPanel {
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField phoneField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	private UserDetailsDTO details;
	private JTextField emailField;

	/**
	 * Create the panel.
	 */
	public Register(Client client) {
		details = new UserDetailsDTO();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblEmail = new JLabel(client.text.getString("email"));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 2;
		add(lblEmail, gbc_lblEmail);
		
		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 3;
		gbc_emailField.gridy = 2;
		add(emailField, gbc_emailField);
		emailField.setColumns(20);
		
		JLabel lblName = new JLabel(client.text.getString("name"));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 3;
		add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 3;
		add(nameField, gbc_nameField);
		nameField.setColumns(20);
		nameField.setText(details.name);
		
		JLabel lblSurname = new JLabel(client.text.getString("surname"));
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 2;
		gbc_lblSurname.gridy = 4;
		add(lblSurname, gbc_lblSurname);
		
		surnameField = new JTextField();
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.insets = new Insets(0, 0, 5, 0);
		gbc_surnameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameField.gridx = 3;
		gbc_surnameField.gridy = 4;
		add(surnameField, gbc_surnameField);
		surnameField.setColumns(20);
		surnameField.setText(details.surname);
		
		JLabel lblPhone = new JLabel(client.text.getString("phone"));
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 2;
		gbc_lblPhone.gridy = 5;
		add(lblPhone, gbc_lblPhone);
		
		phoneField = new JTextField();
		GridBagConstraints gbc_phoneField = new GridBagConstraints();
		gbc_phoneField.insets = new Insets(0, 0, 5, 0);
		gbc_phoneField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneField.gridx = 3;
		gbc_phoneField.gridy = 5;
		add(phoneField, gbc_phoneField);
		phoneField.setColumns(10);
		phoneField.setText(details.phone);
		
		JLabel lblChangePassword = new JLabel(client.text.getString("password"));
		GridBagConstraints gbc_lblChangePassword = new GridBagConstraints();
		gbc_lblChangePassword.anchor = GridBagConstraints.EAST;
		gbc_lblChangePassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblChangePassword.gridx = 2;
		gbc_lblChangePassword.gridy = 6;
		add(lblChangePassword, gbc_lblChangePassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 6;
		add(passwordField, gbc_passwordField);
		
		JLabel lblRepeatPassword = new JLabel(client.text.getString("repeatPassword"));
		GridBagConstraints gbc_lblRepeatPassword = new GridBagConstraints();
		gbc_lblRepeatPassword.anchor = GridBagConstraints.EAST;
		gbc_lblRepeatPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepeatPassword.gridx = 2;
		gbc_lblRepeatPassword.gridy = 7;
		add(lblRepeatPassword, gbc_lblRepeatPassword);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 3;
		gbc_passwordField_1.gridy = 7;
		add(passwordField_1, gbc_passwordField_1);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);
		
		JButton btnRegister = new JButton(client.text.getString("register"));
		panel.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details.email = emailField.getText();
				details.name = nameField.getText();
				details.surname = surnameField.getText();
				details.phone = phoneField.getText();
				if(details.email.length() == 0 || details.name.length() == 0 || details.surname.length() == 0 || details.phone.length() == 0){
					JOptionPane.showMessageDialog(Register.this, "There is one or more empty fields", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (passwordField.getPassword().length != 0) {
						if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
							details.password = new String(passwordField.getPassword());
						} else {
							JOptionPane.showMessageDialog(Register.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
				} else {
					JOptionPane.showMessageDialog(Register.this, "Password field is empty", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean success = ClientController.getController().registerUser(details);
				if (success) {
					JOptionPane.showMessageDialog(Register.this, "Registered successfully");
					client.switchToLogin();
				} else {
					JOptionPane.showMessageDialog(Register.this, "An error has ocurred", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton(client.text.getString("cancel"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.switchToLogin();
			}
		});
		panel.add(btnNewButton_1);

	}

}
