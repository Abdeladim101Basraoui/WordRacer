package elasri;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Profile extends JFrame {

	private JPanel contentPane;
	private JLabel wlcm_msg;
	private String path;
	static protected JLabel lb_Image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {
		setTitle("WordRacing");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Profile.class.getResource("./Image/key.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 205, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setBounds(10, 10, 46, 21);
		panel.add(btnNewButton);
		
		lb_Image = new JLabel("");
		lb_Image.setIcon(new ImageIcon(Profile.class.getResource("./Image/rsz_112.png")));
		lb_Image.setBounds(54, 97, 100, 100);
		panel.add(lb_Image);
		

		
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
			Image in =  image.getImage();
			Image myImg = in.getScaledInstance(lb_Image.getWidth(), lb_Image.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imggg = new ImageIcon(myImg);
			lb_Image.setIcon(imggg);
			}
			
			rs.close();
			con.close();
			 

			
			
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		System.out.println(e1.getMessage());
	}
		
		


		
		
		JButton Parcourir = new JButton("Browse");
		Parcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\Users\\yohan\\Downloads")); // directory from where we begin the Browse
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "jpg", "jpeg", "TIFF", "png", "gif");//accepter extensions
				fileChooser.addChoosableFileFilter(filter);//grant it to the file filter
				int result = fileChooser.showSaveDialog(null); // if all the process go safely it return 0 else the file chosen did not respect the rules 
			
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedfile = fileChooser.getSelectedFile();
					path = selectedfile.getAbsolutePath();//get the path of the selected path
					ImageIcon myImage = new ImageIcon(path); 
					Image img = myImage.getImage();//access to the image Icon
					Image newImage = img.getScaledInstance(lb_Image.getWidth(), lb_Image.getHeight(), Image.SCALE_SMOOTH);
					//getwidth+getheight = resize the image as the label form 
					// scale_smooth = make it smooth and in the best quality like it size = to the label size
					ImageIcon finalImg = new ImageIcon(newImage); // create object to set the image in the interface
					lb_Image.setIcon(finalImg);//put the image from the directory to the interface 
					
				}else {
					if(result == JFileChooser.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(null, "Nothing Selected");
					}
						
				}
			}
		});
		Parcourir.setBounds(54, 233, 100, 21);
		panel.add(Parcourir);
		
		JButton btn_save = new JButton("Save");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				String Userr = l.User;
				try {

					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","MOHAmed1234");
					//JDBC Obj fro Statement
					Statement st = con.createStatement();
					
				 //String sql = "INSERT INTO Player (image) VALUES (?)";
				 //String sql = "Update Player Set image = utl_raw.cast_to_raw('"+path+"') Where username='"+Userr+"'";
				 String sql = "Update Player Set image ='"+path+"' Where username='"+Userr+"'";
				 
				 
				 	//PreparedStatement pre = con.prepareStatement(sqe);
				 	//ResultSet rs = pre.executeQuery();
				 	st.executeUpdate(sql);
					System.out.println("Saved Successfully...");   
	    			
	    			/*while(rs.next()) {*/
					JOptionPane.showMessageDialog(btn_save,"Save Successfully", "Saving", JOptionPane.PLAIN_MESSAGE);

	    				/*String Best_S = rs.getString(1);
	    				String Score  = rs.getString(2);
	    			}*/

			     
					//st.close();
					con.close();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}


			}
		});
		
		btn_save.setBounds(54, 329, 100, 21);
		panel.add(btn_save);
		
		JLabel lblNewLabel = new JLabel("Make it as your final chose!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(28, 305, 167, 13);
		panel.add(lblNewLabel);
		
		/*JButton btnNewButton_1 = new JButton("*");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				String Usere = l.User;
				try {

					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","MOHAmed1234");
					
					//String sql = "select image from Player where username='"+Usere+"'"; //image = utl_raw.cast_to_raw('"+path+"') Where username='"+Usere+"'";
					String sql = "select image from Player where username='"+Usere+"'"; 
				 
				 	PreparedStatement pre = con.prepareStatement(sql);
				 	ResultSet rs = pre.executeQuery();

					while(rs.next()) {
					String img = rs.getString("image");
					ImageIcon image = new ImageIcon(img);
					java.awt.Image in =  image.getImage();
					java.awt.Image myImg = in.getScaledInstance(lb_Image.getWidth(), lb_Image.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon imggg = new ImageIcon(myImg);
					lb_Image.setIcon(imggg);
					}
					
					rs.close();
					con.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				//lb_Image.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(84, 263, 39, 21);
		panel.add(btnNewButton_1);*/
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(205, 0, 531, 413);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		wlcm_msg = new JLabel("");
		wlcm_msg.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		wlcm_msg.setBounds(130, 28, 264, 46);
		panel_1.add(wlcm_msg);
		Login ls = new Login();
		String Userr = ls.User;
		wlcm_msg.setText("Welcome "+Userr/*.substring(1)*/);
		
		JLabel lblBestScore = new JLabel("Best Score");
		lblBestScore.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblBestScore.setBounds(209, 113, 117, 21);
		panel_1.add(lblBestScore);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.GRAY));
		panel_2.setBounds(51, 139, 117, 81);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel Last_Score = new JLabel("0");
		Last_Score.setFont(new Font("Segoe UI Black", Font.PLAIN, 31));
		Last_Score.setBounds(10, 26, 86, 35);
		panel_2.add(Last_Score);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.GRAY));
		panel_2_1.setBounds(209, 139, 117, 81);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel Best_Score = new JLabel("0");
		Best_Score.setFont(new Font("Segoe UI Black", Font.PLAIN, 31));
		Best_Score.setBounds(10, 27, 86, 34);
		panel_2_1.add(Best_Score);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) Color.GRAY));
		panel_2_1_1.setBounds(363, 139, 117, 81);
		panel_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);
		
		JLabel Avrage_Speed = new JLabel("0");
		Avrage_Speed.setFont(new Font("Segoe UI Black", Font.PLAIN, 31));
		Avrage_Speed.setBounds(10, 20, 86, 41);
		panel_2_1_1.add(Avrage_Speed);
		
		JLabel lblScore = new JLabel("Last Score");
		lblScore.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblScore.setBounds(51, 116, 95, 18);
		panel_1.add(lblScore);
		
		JLabel lblNewLabel_3 = new JLabel("Level");
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(363, 115, 117, 19);
		panel_1.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("Show Results");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","MOHAmed1234");
					 
						
					 String sql = "select BestScore, WPM, Levelp from player p inner join race r on p.IDPlayer = r.ID_Player where p.username = '"+Userr+"'" ;
					 //            select BestScore, WPM, 'Level' from player p inner join race r on p.IDPlayer = r.ID_Player where p.username = 're'
					 
					 
					 	PreparedStatement pre = con.prepareStatement(sql);
					 	ResultSet rs = pre.executeQuery();
		    			
		    			
		    			while(rs.next()) {

		    				String Best_S = rs.getString("BestScore");
		    				String Score  = rs.getString("WPM");
		    				String LVL  = rs.getString("Levelp");
		    			
		    				Last_Score.setText(Score);
		    				Best_Score.setText(Best_S);
		    				Avrage_Speed.setText(LVL);
		    			}
						con.close();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}

			}
		});
		btnNewButton_2.setBounds(209, 329, 117, 21);
		panel_1.add(btnNewButton_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
				HomePage Hp = new HomePage();
				Hp.show();
			}
		});
	}
}
