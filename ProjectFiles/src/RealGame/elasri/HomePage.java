package elasri;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import lvlpackages.*;

public class HomePage extends JFrame {

	private final JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
					
					Login log = new Login(); //can't access to this page is we don't login
					log.show();
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setTitle("WordRacing");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("./Image/key.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WordRacing");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(306, 33, 140, 52);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
			MatchingFrame mf = new MatchingFrame();
			MatchingFrame.IDPlayer = Login.IDPlayer;
				mf.show();
			}
		});
		btnNewButton.setBounds(328, 239, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Profile");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();//close login page
				Profile Pe = new Profile();
				Pe.show();	
				/*
				Profile pro = new Profile();
				JLabel prof = pro.lb_Image;
				
				Login l = new Login();
				String Usere = l.User;
				try {

					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","MOHAmed1234");

					String sql = "select image from Player where username='"+Usere+"'"; 
				 
				 	PreparedStatement pre = con.prepareStatement(sql);
				 	ResultSet rs = pre.executeQuery();

					while(rs.next()) {
					String img = rs.getString("image");
					ImageIcon image = new ImageIcon(img);
					java.awt.Image in =  image.getImage();
					java.awt.Image myImg = in.getScaledInstance(prof.getWidth(), prof.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon imggg = new ImageIcon(myImg);
					prof.setIcon(imggg);
					}
					
					rs.close();
					con.close();
					 
		
					
					
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
				
				*/
			}
		});
		btnNewButton_1.setBounds(328, 292, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Ranking");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
				Ranking Ra = new Ranking();
				Ra.show();
			}
		});
		btnNewButton_4.setBounds(328, 346, 85, 21);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("LogOut");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
				Login lo = new Login();
				lo.show();
			}
		});
		btnNewButton_3.setBounds(10, 482, 85, 21);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("\"WordRacing, Speed up your typing\"");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_2_1.setBounds(268, 82, 225, 21);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(HomePage.class.getResource("./Image/1442945289_wpm-key_auto_x2.jpg")));
		lblNewLabel_1.setBounds(0, 0, 736, 513);
		contentPane.add(lblNewLabel_1);
	}
}
