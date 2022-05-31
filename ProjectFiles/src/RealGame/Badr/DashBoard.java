package Badr;

import Game.JDBC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



import java.awt.Font;


import javax.swing.JLabel;

import javax.swing.SwingConstants;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DashBoard extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JPanel panel = new JPanel();
    private JTable table;
    private JTextField Recherche;
    String filename=null;
    byte[] photo=null;
    //private String Photo_Profil;
    private JLabel Photo_Profil;



    /**
     * Launch the application.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DashBoard frame = new DashBoard();
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
    public DashBoard() {
        setTitle("Word Racing - DASHBOARD_ADMIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        panel.setFont(new Font("Century Gothic", Font.BOLD, 13));
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(177, 391, 557, 120);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setFont(new Font("Century Gothic", Font.BOLD, 13));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(null);
        scrollPane.setBounds(0, 0, 557, 120);
        panel.add(scrollPane);
        table = new JTable();

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(177, 0, 557, 391);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel = new JLabel("IDENTIFIENT JOUEUR");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblNewLabel.setBounds(39, 82, 153, 29);
        panel_2.add(lblNewLabel);

        JLabel lblDernierScore = new JLabel("NOM COMPLET");
        lblDernierScore.setHorizontalAlignment(SwingConstants.CENTER);
        lblDernierScore.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblDernierScore.setBounds(218, 82, 132, 29);
        panel_2.add(lblDernierScore);

        JLabel lblFaibleScore = new JLabel("MEILLEUR SCORE");
        lblFaibleScore.setHorizontalAlignment(SwingConstants.CENTER);
        lblFaibleScore.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblFaibleScore.setBounds(387, 200, 132, 29);
        panel_2.add(lblFaibleScore);

        JLabel lblNiveauActuel = new JLabel("NIVEAU ACTUEL");
        lblNiveauActuel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNiveauActuel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblNiveauActuel.setBounds(218, 200, 132, 29);
        panel_2.add(lblNiveauActuel);

        JPanel panel_3 = new JPanel();
        panel_3.setFont(new Font("Century Gothic", Font.BOLD, 16));
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_3.setBounds(54, 125, 122, 61);
        panel_2.add(panel_3);
        panel_3.setLayout(null);

        JTextArea Id_Joueur = new JTextArea();
        Id_Joueur.setForeground(Color.BLACK);
        Id_Joueur.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        Id_Joueur.setBackground(Color.LIGHT_GRAY);
        Id_Joueur.setMargin(new Insets(20, 4, 4, 4));
        Id_Joueur.setFont(new Font("Century Gothic", Font.BOLD, 16));
        Id_Joueur.setBounds(0, 0, 122, 61);
        panel_3.add(Id_Joueur);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setFont(new Font("Century Gothic", Font.BOLD, 16));
        panel_3_1.setBackground(Color.LIGHT_GRAY);
        panel_3_1.setBounds(223, 125, 291, 61);
        panel_2.add(panel_3_1);
        panel_3_1.setLayout(null);

        JTextArea Nom_Complet = new JTextArea();
        Nom_Complet.setForeground(Color.BLACK);
        Nom_Complet.setMargin(new Insets(20, 4, 4, 4));
        Nom_Complet.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        Nom_Complet.setBackground(Color.LIGHT_GRAY);
        Nom_Complet.setFont(new Font("Century Gothic", Font.BOLD, 16));
        Nom_Complet.setBounds(0, 0, 291, 61);
        panel_3_1.add(Nom_Complet);

        JPanel panel_3_2 = new JPanel();
        panel_3_2.setFont(new Font("Century Gothic", Font.BOLD, 16));
        panel_3_2.setBackground(Color.LIGHT_GRAY);
        panel_3_2.setBounds(392, 243, 122, 61);
        panel_2.add(panel_3_2);
        panel_3_2.setLayout(null);

        JTextArea Meilleur_Score = new JTextArea();
        Meilleur_Score.setForeground(new Color(0, 128, 0));
        Meilleur_Score.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        Meilleur_Score.setBackground(Color.LIGHT_GRAY);
        Meilleur_Score.setMargin(new Insets(20, 4, 4, 4));
        Meilleur_Score.setFont(new Font("Century Gothic", Font.BOLD, 16));
        Meilleur_Score.setBounds(0, 0, 122, 61);
        panel_3_2.add(Meilleur_Score);

        JPanel panel_3_3 = new JPanel();
        panel_3_3.setLayout(null);
        panel_3_3.setFont(new Font("Century Gothic", Font.BOLD, 16));
        panel_3_3.setBackground(Color.LIGHT_GRAY);
        panel_3_3.setBounds(54, 243, 122, 61);
        panel_2.add(panel_3_3);

        JTextArea Dernier_Score = new JTextArea();
        Dernier_Score.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        Dernier_Score.setForeground(new Color(0, 0, 128));
        Dernier_Score.setMargin(new Insets(20, 4, 4, 4));
        Dernier_Score.setFont(new Font("Century Gothic", Font.BOLD, 16));
        Dernier_Score.setBackground(Color.LIGHT_GRAY);
        Dernier_Score.setBounds(0, 0, 122, 61);
        panel_3_3.add(Dernier_Score);


        JPanel panel_3_2_1 = new JPanel();
        panel_3_2_1.setFont(new Font("Century Gothic", Font.BOLD, 16));
        panel_3_2_1.setBackground(Color.LIGHT_GRAY);
        panel_3_2_1.setBounds(223, 243, 122, 61);
        panel_2.add(panel_3_2_1);
        panel_3_2_1.setLayout(null);

        JTextArea Niveau_Actuel = new JTextArea();
        Niveau_Actuel.setForeground(new Color(220, 20, 60));
        Niveau_Actuel.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        Niveau_Actuel.setBackground(Color.LIGHT_GRAY);
        Niveau_Actuel.setMargin(new Insets(20, 4, 4, 4));
        Niveau_Actuel.setFont(new Font("Century Gothic", Font.BOLD, 16));
        Niveau_Actuel.setBounds(0, 0, 122, 61);
        panel_3_2_1.add(Niveau_Actuel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(null);
        panel_1.setBounds(0, 0, 177, 511);
        contentPane.add(panel_1);
        panel_1.setBackground(Color.LIGHT_GRAY);
        panel_1.setLayout(null);

        Photo_Profil = new JLabel("");
        Photo_Profil.setHorizontalAlignment(SwingConstants.CENTER);
        Photo_Profil.setHorizontalTextPosition(SwingConstants.CENTER);
        Photo_Profil.setIcon(new ImageIcon("C:\\Users\\yohan\\Downloads\\identity_75x75.png"));
        Photo_Profil.setBounds(0, 0, 177, 178);
        panel_1.add(Photo_Profil);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = table.getSelectedRow();
                TableModel model = table.getModel();
//				String value = (table.getModel().getValueAt(selectedRow, 0).toString());
                JDBC.showTable(model,Id_Joueur,Nom_Complet,Dernier_Score,Niveau_Actuel,Meilleur_Score,selectedRow,Photo_Profil);
                		}
        });
        table.setFont(new Font("Century Gothic", Font.BOLD, 13));
        table.setBorder(null);
        scrollPane.setViewportView(table);
//		table.setModel(model);



        JButton AFFICHER = new JButton("AFFICHER");
        AFFICHER.setBorder(null);
        AFFICHER.setFocusable(false);
        AFFICHER.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				JDBC.showDashBoard(table);

            }
        });


        AFFICHER.setBackground(Color.WHITE);
        AFFICHER.setForeground(Color.BLACK);
        AFFICHER.setFont(new Font("Century Gothic", Font.BOLD, 15));
        AFFICHER.setBounds(0, 406, 177, 36);
        panel_1.add(AFFICHER);

        JButton btnNewButton_3 = new JButton("REINITIALISER");
        btnNewButton_3.setBorder(null);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Id_Joueur.setText("");
                Nom_Complet.setText("");
                Meilleur_Score.setText("");
                Dernier_Score.setText("");
                Niveau_Actuel.setText("");
                Photo_Profil.setIcon(null);
            }
        });
        btnNewButton_3.setFocusable(false);
        btnNewButton_3.setForeground(Color.BLACK);
        btnNewButton_3.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnNewButton_3.setBackground(Color.WHITE);
        btnNewButton_3.setBounds(0, 453, 177, 36);
        panel_1.add(btnNewButton_3);

        JPanel panel_4 = new JPanel();
        panel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//				dispose();
//				HomePage HP = new HomePage();
//				HP.show();
            }
        });
        panel_4.setBounds(0, 178, 177, 36);
        panel_1.add(panel_4);
        panel_4.setLayout(null);

        JButton btnNewButton_4 = new JButton("ACCEUIL");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				dispose();
//				HomePage HP = new HomePage();
//				HP.show();
            }
        });
        btnNewButton_4.setFocusable(false);
        btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_4.setBorder(null);
        btnNewButton_4.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnNewButton_4.setBackground(Color.WHITE);
        btnNewButton_4.setBounds(38, 0, 139, 36);
        panel_4.add(btnNewButton_4);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//				dispose();
//				Home H = new Home();
//				H.show();
            }
        });
        lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_2.setBorder(null);
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setFocusable(false);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\yohan\\Downloads\\home.png"));
        lblNewLabel_2.setBounds(0, 0, 40, 36);
        panel_4.add(lblNewLabel_2);

        JPanel panel_4_1 = new JPanel();
        panel_4_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                //RANKING R = new RANKING();
                //R.show();
            }
        });
        panel_4_1.setLayout(null);
        panel_4_1.setBounds(0, 217, 177, 36);
        panel_1.add(panel_4_1);

        JButton btnNewButton_4_1 = new JButton("RANKING");
        btnNewButton_4_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Rank R = new Rank();
                R.show();
            }
        });
        btnNewButton_4_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_4_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnNewButton_4_1.setFocusable(false);
        btnNewButton_4_1.setBorder(null);
        btnNewButton_4_1.setBackground(Color.WHITE);
        btnNewButton_4_1.setBounds(38, 0, 139, 36);
        panel_4_1.add(btnNewButton_4_1);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//				dispose();
//			    Settings S = new Settings();
//				S.show();
            }
        });
        lblNewLabel_2_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\yohan\\Downloads\\ranking_2_22x22.png"));
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFocusable(false);
        lblNewLabel_2_1.setBorder(null);
        lblNewLabel_2_1.setBackground(Color.WHITE);
        lblNewLabel_2_1.setBounds(0, 0, 40, 36);
        panel_4_1.add(lblNewLabel_2_1);


        JLabel lblRechercherJoueur = new JLabel("RECHERCHER JOUEUR");
        lblRechercherJoueur.setHorizontalAlignment(SwingConstants.CENTER);
        lblRechercherJoueur.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblRechercherJoueur.setBounds(39, 339, 177, 29);
        panel_2.add(lblRechercherJoueur);

        Recherche = new JTextField();

        //search in the database->
        Recherche.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchString = Recherche.getText();
                search(searchString);
            }
            private void search(String str) {
                // TODO Auto-generated method stub
                DefaultTableModel model1 = (DefaultTableModel) table.getModel();
                TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model1);
                table.setRowSorter(trs);
                trs.setRowFilter(RowFilter.regexFilter(str));
            }
        });
        //search in the database<-
        Recherche.setFont(new Font("Century Gothic", Font.BOLD, 13));
        Recherche.setBackground(Color.LIGHT_GRAY);
        Recherche.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        Recherche.setBounds(223, 339, 291, 29);
        panel_2.add(Recherche);
        Recherche.setColumns(10);


        JLabel lblScoreActuel = new JLabel("DERNIER SCORE");
        lblScoreActuel.setHorizontalAlignment(SwingConstants.CENTER);
        lblScoreActuel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblScoreActuel.setBounds(49, 200, 132, 29);
        panel_2.add(lblScoreActuel);

        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(0, 0, 557, 83);
        panel_2.add(panel_5);
        panel_5.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("DASHBOARD");
        lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 36));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(67, 16, 422, 51);
        panel_5.add(lblNewLabel_1);
    }
}