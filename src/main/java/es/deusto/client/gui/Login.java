package es.deusto.client.gui;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.UserDTO;
import es.deusto.server.jdo.User;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login(Client client) {
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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UserDTO user = ClientController.getController().logIn(textField.getText(), new String(passwordField.getPassword()));
					if(user != null) {
						
						client.switchToHomePage();
					} else {
						client.incorrectLoginAlert();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 37, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, passwordField);
		add(btnLogin);
		
		JButton btnRegister = new JButton("Registration");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.switchToRegister();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnRegister, 0, SpringLayout.NORTH, btnLogin);
		springLayout.putConstraint(SpringLayout.EAST, btnRegister, 0, SpringLayout.EAST, textField);
		add(btnRegister);

	}
}
