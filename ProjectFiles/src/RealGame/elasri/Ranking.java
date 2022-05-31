package elasri;

import Game.JDBC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Ranking extends JFrame {

	private JPanel contentPane;
	private JTable TRank;
	private JTable TMyRank;
	private DefaultTableModel model;
	private ResultSet rs;
	//public String Rank;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking frame = new Ranking();
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
	public Ranking() {
		setTitle("WordRacing");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ranking.class.getResource("./Image/key.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Retour = new JButton("<");
		Retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//close login page
				HomePage Hp = new HomePage();
				Hp.show();
			}
		});
		Retour.setBounds(10, 10, 47, 21);
		contentPane.add(Retour);
		
		JLabel lblNewLabel = new JLabel("Word Ranking");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(254, 34, 171, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			JDBC.Refresh(model,TRank);
			}	
		
	});
				
		btnNewButton.setBounds(41, 197, 92, 31);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(157, 107, 439, 184);
		contentPane.add(scrollPane);
		
		TRank = new JTable();
		scrollPane.setViewportView(TRank);
		TRank.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Rank", "Full Name", "Best Score", "@User"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnNewButton_1 = new JButton("My Rank");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				 String myRank = l.User;

JDBC.MYFRank(myRank,model,TRank,TMyRank,myRank);

			}
		});
		btnNewButton_1.setBounds(48, 366, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(157, 350, 416, 44);
		contentPane.add(scrollPane_1);
		
		TMyRank = new JTable();
		scrollPane_1.setViewportView(TMyRank);
		TMyRank.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Rank", "Full Name", "Best Score", "@User"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
}
