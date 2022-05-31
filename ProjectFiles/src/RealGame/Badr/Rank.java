package Badr;

import Game.JDBC;
import elasri.HomePage;

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

import java.sql.ResultSet;

import java.awt.event.ActionEvent;

import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rank extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable TRank;
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
                    Rank frame = new Rank();
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
    public Rank() {
        setTitle("WordRacing - RANKING_ADMIN");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Rank.class.getResource("/APP/Image/key.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(157, 83, 577, 428);
        contentPane.add(scrollPane);

        TRank = new JTable();
        scrollPane.setViewportView(TRank);
        TRank.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[] {
                        "POSITION", "NOM COMPLET", "MEILLEUR SCORE", "EMAIL", "@USER"
                }
        ));
        TRank.getColumnModel().getColumn(0).setPreferredWidth(74);
        TRank.getColumnModel().getColumn(0).setMinWidth(74);
        TRank.getColumnModel().getColumn(0).setMaxWidth(80);
        TRank.getColumnModel().getColumn(1).setPreferredWidth(100);
        TRank.getColumnModel().getColumn(1).setMinWidth(100);
        TRank.getColumnModel().getColumn(2).setPreferredWidth(96);
        TRank.getColumnModel().getColumn(2).setMinWidth(96);
        TRank.getColumnModel().getColumn(3).setPreferredWidth(115);
        TRank.getColumnModel().getColumn(3).setMinWidth(100);
        TRank.getColumnModel().getColumn(4).setPreferredWidth(90);
        TRank.getColumnModel().getColumn(4).setMinWidth(90);
        TRank.getColumnModel().getColumn(4).setMaxWidth(90);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 734, 85);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("ADMIN RANKING");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 33));
        lblNewLabel.setBounds(214, 0, 306, 47);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 84, 159, 427);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("REINITIALISER");
        btnNewButton.setFocusable(false);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDBC.showValues(model,TRank);
               }
        });
        btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(5, 192, 149, 43);
        panel_1.add(btnNewButton);

        JPanel panel_4 = new JPanel();
        panel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
				dispose();
               DashBoard HP = new DashBoard();
				HP.show();
            }
        });
        panel_4.setLayout(null);
        panel_4.setBounds(0, 32, 159, 36);
        panel_1.add(panel_4);

        JButton btnNewButton_4 = new JButton("ACCEUIL");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				dispose();
                CHOICE ch = new CHOICE();
                ch.show();
                            }
        });
        btnNewButton_4.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnNewButton_4.setFocusable(false);
        btnNewButton_4.setBorder(null);
        btnNewButton_4.setBackground(Color.WHITE);
        btnNewButton_4.setBounds(38, 0, 121, 36);
        panel_4.add(btnNewButton_4);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\yohan\\Downloads\\home.png"));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFocusable(false);
        lblNewLabel_2.setBorder(null);
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setBounds(0, 0, 40, 36);
        panel_4.add(lblNewLabel_2);

        JPanel panel_4_1 = new JPanel();
        panel_4_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                dispose();
//                DashBoard D = new DashBoard();
//                D.show();
            }
        });
        panel_4_1.setLayout(null);
        panel_4_1.setBounds(0, 71, 159, 36);
        panel_1.add(panel_4_1);

        JButton btnNewButton_4_1 = new JButton("DASHBOARD");
        btnNewButton_4_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashBoard D = new DashBoard();
                D.show();
            }
        });
        btnNewButton_4_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnNewButton_4_1.setFocusable(false);
        btnNewButton_4_1.setBorder(null);
        btnNewButton_4_1.setBackground(Color.WHITE);
        btnNewButton_4_1.setBounds(38, 0, 121, 36);
        panel_4_1.add(btnNewButton_4_1);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\yohan\\Downloads\\monitor (3).png"));
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFocusable(false);
        lblNewLabel_2_1.setBorder(null);
        lblNewLabel_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1.setBounds(0, 0, 40, 36);
        panel_4_1.add(lblNewLabel_2_1);
    }
}