package windows;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import implementations.LoginImpl;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmOrderClient;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmOrderClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		frmOrderClient = new JFrame();
		frmOrderClient.setFont(new Font("SansSerif", Font.PLAIN, 14));
		frmOrderClient.setTitle("Order Client");
		frmOrderClient.setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			frmOrderClient.setIconImage(ImageIO.read(classLoader.getResourceAsStream("Deliver Food-48.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		frmOrderClient.setBounds(100, 100, 304, 155);
		frmOrderClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOrderClient.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel.setBounds(27, 14, 79, 14);
		frmOrderClient.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 44, 107, 14);
		frmOrderClient.getContentPane().add(lblNewLabel_1);

		txtUsername = new JTextField();
		txtUsername.setText("test");
		txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtUsername.setBounds(127, 11, 154, 20);
		frmOrderClient.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setText("test");
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtPassword.setBounds(127, 41, 154, 20);
		frmOrderClient.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginImpl loginImpl = new LoginImpl();
				int userId = loginImpl.login(txtUsername.getText(), txtPassword.getPassword());
				if (userId != -1) {
					MainUserInterface mui = new MainUserInterface(userId);
					mui.setVisible(true);
					frmOrderClient.setVisible(false);
					frmOrderClient.dispose();
				} else {
					JOptionPane.showMessageDialog(frmOrderClient, "Login data incorrect! Please try again.");
				}
			}
		});
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnLogin.setBounds(44, 82, 89, 23);
		frmOrderClient.getContentPane().add(btnLogin);

		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton_1.setBounds(153, 82, 89, 23);
		frmOrderClient.getContentPane().add(btnNewButton_1);
	}
}
