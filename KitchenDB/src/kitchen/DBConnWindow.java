package kitchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class DBConnWindow {

	private JFrame frame;
	private JTextField usernameTextField;
	private JTextField serverTextField;
	private JTextField portTextField;
	private JTextField DBNameTextField;
	private JPasswordField passwordTextField;
	
	public JFrame getForm(){
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnWindow window = new DBConnWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBConnWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 218, 232);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDbConnectionInfo = new JLabel("DB Connection Info");
		lblDbConnectionInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDbConnectionInfo.setBounds(31, 11, 144, 27);
		frame.getContentPane().add(lblDbConnectionInfo);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 48, 74, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(10, 69, 74, 15);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblServerName = new JLabel("Server Name");
		lblServerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServerName.setBounds(10, 90, 74, 15);
		frame.getContentPane().add(lblServerName);
		
		JLabel lblPortNumber = new JLabel("Port Number");
		lblPortNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPortNumber.setBounds(10, 111, 74, 15);
		frame.getContentPane().add(lblPortNumber);
		
		JLabel lblDbName = new JLabel("DB Name");
		lblDbName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDbName.setBounds(10, 132, 74, 15);
		frame.getContentPane().add(lblDbName);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		usernameTextField.setBounds(90, 48, 100, 16);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		serverTextField = new JTextField();
		serverTextField.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		serverTextField.setColumns(10);
		serverTextField.setBounds(90, 90, 100, 15);
		frame.getContentPane().add(serverTextField);
		
		portTextField = new JTextField();
		portTextField.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		portTextField.setColumns(10);
		portTextField.setBounds(90, 111, 100, 15);
		frame.getContentPane().add(portTextField);
		
		DBNameTextField = new JTextField();
		DBNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		DBNameTextField.setColumns(10);
		DBNameTextField.setBounds(90, 132, 100, 15);
		frame.getContentPane().add(DBNameTextField);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConn.setUserName(usernameTextField.getText());
				DBConn.setPassword(passwordTextField.getText());
				DBConn.setServerName(serverTextField.getText());
				DBConn.setPortNumber(portTextField.getText());
				DBConn.setDBName(DBNameTextField.getText());
				frame.dispose();
			    frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(55, 158, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Times New Roman", Font.PLAIN, 9));
		passwordTextField.setBounds(90, 69, 100, 15);
		frame.getContentPane().add(passwordTextField);
	}
}
