package es.deusto.client.gui;

import es.deusto.client.Client;
import es.deusto.client.controller.ClientController;
import es.deusto.server.data.UserDTO;
import es.deusto.server.jdo.User;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
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
	public Login() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Username:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 81, SpringLayout.NORTH, this);
		add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 32, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 88, SpringLayout.WEST, this);
		add(lblPassword);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField, 146, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, -58, SpringLayout.WEST, textField);
		add(textField);
		textField.setColumns(20);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -26, SpringLayout.NORTH, passwordField);
		passwordField.setColumns(20);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, lblPassword);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, 0, SpringLayout.SOUTH, lblPassword);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Login in as: " +  textField.getText() + new String(passwordField.getPassword()));

					UserDTO user = ClientController.getController().logIn(textField.getText(), new String(passwordField.getPassword()));
					System.out.println(user.getName() + user.getUserID());
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 37, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, passwordField);
		add(btnLogin);

	}
}
