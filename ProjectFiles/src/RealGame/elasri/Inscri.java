package elasri;

import Game.JDBC;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Canvas;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Inscri extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfMail;
	private JLabel lblNewLabel_2;
	private JTextField tfUser;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField tfBirth;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton btnLogIn;
	private JButton btnSignUp;
	
	private String Name;
	private String Mail;
	private String Age;
	private String User;
	private String Pass;
	private String RePass;
	
	private JButton btnReset;
	private JPasswordField pfPass;
	private JPasswordField pfRePass;
	private JLabel lblNewLabel_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscri frame = new Inscri();
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
	public Inscri() {
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inscri.class.getResource("./Image/key.png")));
		setTitle("WordRacing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Full Name :");
		lblNewLabel.setBounds(65, 55, 96, 13);
		contentPane.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setBounds(229, 52, 395, 19);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email :");
		lblNewLabel_1.setBounds(65, 103, 96, 19);
		contentPane.add(lblNewLabel_1);
		
		tfMail = new JTextField();
		tfMail.setBounds(229, 103, 395, 19);
		contentPane.add(tfMail);
		tfMail.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setBounds(65, 251, 85, 23);
		contentPane.add(lblNewLabel_2);
		
		tfUser = new JTextField();
		tfUser.setBounds(229, 253, 395, 19);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setBounds(65, 305, 85, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Age:");
		lblNewLabel_4.setBounds(65, 153, 85, 20);
		contentPane.add(lblNewLabel_4);
		
		tfBirth = new JTextField();
		tfBirth.setBounds(229, 154, 395, 19);
		contentPane.add(tfBirth);
		tfBirth.setColumns(10);
		
		lblNewLabel_5 = new JLabel("1. Tell us about yourself");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 10, 395, 20);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("2. Choose a login name and password");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(10, 205, 395, 20);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Re-enter Pwd:");
		lblNewLabel_7.setBounds(65, 349, 105, 19);
		contentPane.add(lblNewLabel_7);
		
		btnSignUp = new JButton("SignUp");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RePass = pfRePass.getText();
				Pass = pfPass.getText();
				Age = tfBirth.getText();
				User = tfUser.getText();
				Mail = tfMail.getText();
				Name = tfName.getText();
		
				if(User.equals("") || Pass.equals("") || Age.equals("") || User.equals("") || Mail.equals("") || Name.equals("")) {
					JOptionPane.showMessageDialog(lblNewLabel_6,"Remplissez les champs SVP", "Authentification", JOptionPane.WARNING_MESSAGE);
				}
				else if(!Pass.equals(RePass)) {
					JOptionPane.showMessageDialog(lblNewLabel_6, "Erreur de confirmation de mot de passe", "Authentification", JOptionPane.ERROR_MESSAGE);

				}
				else {

					try {
							JDBC.SignUp(Name,Integer.valueOf(Age),Mail,Pass,User);
							tfName.setText("");
							tfMail.setText("");
							tfBirth.setText("");
							tfUser.setText("");
							pfRePass.setText("");
							pfPass.setText("");
					        
					        JOptionPane.showMessageDialog(lblNewLabel_6, "Done","Authentification", JOptionPane.PLAIN_MESSAGE);
					        
							dispose();//close login page
							Login log = new Login();
							log.show();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
				}	
			}
		});
		btnSignUp.setBounds(289, 400, 116, 26);
		contentPane.add(btnSignUp);
		
		btnLogIn = new JButton("LogIn");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
				Login log = new Login();
				log.show();
			}
		});
		btnLogIn.setBounds(454, 452, 96, 21);
		contentPane.add(btnLogIn);
		
		JLabel lblNewLabel_8 = new JLabel("Already Have Account!");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_8.setBounds(263, 456, 142, 13);
		contentPane.add(lblNewLabel_8);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfName.setText("");
				tfMail.setText("");
				tfBirth.setText("");
				tfUser.setText("");
				pfRePass.setText("");
				pfPass.setText("");
			}
		});
		btnReset.setBounds(454, 400, 116, 26);
		contentPane.add(btnReset);
		
		pfPass = new JPasswordField();
		pfPass.setBounds(229, 302, 395, 19);
		contentPane.add(pfPass);
		
		pfRePass = new JPasswordField();
		pfRePass.setBounds(229, 349, 395, 19);
		contentPane.add(pfRePass);
		
		lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(Inscri.class.getResource("./Image/photo-1491947153227-33d59da6c448.jpg")));
		lblNewLabel_9.setBounds(0, 0, 736, 513);
		contentPane.add(lblNewLabel_9);
		

	}
}
