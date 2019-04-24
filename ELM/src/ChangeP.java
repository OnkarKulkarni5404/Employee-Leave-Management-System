import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;


public class ChangeP extends JPanel {
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	/**
	 * Create the panel.
	 */
	public ChangeP() {
		
		
		this.setBackground(SystemColor.activeCaption);
		this.setBounds(220,13,600,347);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Passoword");
		lblNewLabel.setBounds(174, 114, 91, 16);
		add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(301, 165, 116, 22);
		add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(301, 111, 116, 22);
		add(passwordField_1);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(174, 168, 104, 16);
		add(lblConfirmPassword);
		
				
		
		
	}
}
