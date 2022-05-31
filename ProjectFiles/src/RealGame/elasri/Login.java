package elasri;

import Badr.AdminLogin;
import Game.JDBC;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField pfPass;

	private JButton btLogIn;
	private JButton btSignUp;

	public static  String User;
	public static String Pass;
	public static int IDPlayer;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("./Image/key.png")));
		setTitle("WordRacing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Login Page");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(25, 54, 152, 26);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("User or Mail : ");
		lblNewLabel.setBounds(35, 136, 85, 13);
		contentPane.add(lblNewLabel);

		tfUser = new JTextField();
		tfUser.setBounds(193, 133, 369, 19);
		contentPane.add(tfUser);
		tfUser.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBounds(35, 218, 85, 13);
		contentPane.add(lblNewLabel_1);

		btLogIn = new JButton("LogIn");
		btLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User = tfUser.getText();
				Pass = pfPass.getText();
				if(JDBC.checkEmail(User,Pass)!=0) {
					JOptionPane.showMessageDialog(btLogIn,"Authentification R�ussite", "Authentification", JOptionPane.PLAIN_MESSAGE);
					dispose();//close login page
					HomePage hpage = new HomePage();
					hpage.show();
				}

				else if (User.equals("") || Pass.equals("") ) {//User = null && Pass = null
					JOptionPane.showMessageDialog(btLogIn,"Remplissez les champs SVP", "Authentification", JOptionPane.WARNING_MESSAGE);
					//if username and password is wrong show message
				} else {
					JOptionPane.showMessageDialog(pfPass,"user or password wrong...", "Authentification", JOptionPane.ERROR_MESSAGE);
					tfUser.setText("");
					pfPass.setText("");
				};
			}

		});
		btLogIn.setBounds(241, 337, 106, 26);
		contentPane.add(btLogIn);

		btSignUp = new JButton("SignUp!");
		btSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
				Inscri In = new Inscri();
				In.show();
			}
		});
		btSignUp.setBounds(413, 401, 85, 21);
		contentPane.add(btSignUp);

		JLabel lblNewLabel_2 = new JLabel("First time at WordRacing!");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_2.setBounds(151, 402, 221, 17);
		contentPane.add(lblNewLabel_2);

		pfPass = new JPasswordField();
		pfPass.setBounds(193, 215, 369, 19);
		contentPane.add(pfPass);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfUser.setText("");
				pfPass.setText("");
			}
		});
		btnReset.setBounds(413, 337, 106, 26);
		contentPane.add(btnReset);

		JButton btnNewButton = new JButton("Admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			AdminLogin l = new AdminLogin();
				l.show();
			}
		});
		btnNewButton.setBounds(413, 447, 85, 21);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_5 = new JLabel("Admin Space");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_5.setBounds(151, 451, 132, 17);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("./Image/original.jpg")));
		lblNewLabel_4.setBounds(0, 0, 736, 513);
		contentPane.add(lblNewLabel_4);
	}
}
