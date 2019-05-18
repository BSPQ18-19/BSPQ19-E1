package es.deusto.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.UserDTO;

public class Register extends JPanel {

	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Create the panel.
	 */
	public Register(Client client) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblUsername = new JLabel("Email:");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 81, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 61, SpringLayout.WEST, this);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 32, SpringLayout.SOUTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblUsername);
		add(lblPassword);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 78, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 117, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -37, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, -87, SpringLayout.WEST, textField);
		add(textField);
		textField.setColumns(20);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 117, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -6, SpringLayout.WEST, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 121, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -6, SpringLayout.WEST, passwordField);
		passwordField.setColumns(20);
		add(passwordField);
		
		JButton btnLogin = new JButton("Register");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO registration
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 37, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, passwordField);
		add(btnLogin);
		
		JLabel lblRepeat = new JLabel("Repeat:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRepeat, 6, SpringLayout.SOUTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, lblRepeat, 0, SpringLayout.WEST, lblUsername);
		add(lblRepeat);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField_1, 6, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField_1, 0, SpringLayout.WEST, textField);
		add(passwordField_1);
	}
}
