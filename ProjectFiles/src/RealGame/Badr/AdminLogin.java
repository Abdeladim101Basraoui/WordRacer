package Badr;


import elasri.Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class AdminLogin extends JFrame {

    private JPanel contentPane;
    private JTextField txtNomDutilisateur;
    private JPasswordField pwdMotDePasse;
    private JLabel lblNewLabel_3 = new JLabel("");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
AdminLogin frame = new AdminLogin();
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
    public AdminLogin() {
        setTitle("Word Racing - ADMIN");
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setForeground(Color.BLACK);
        panel.setBorder(new LineBorder(Color.BLACK, 2));
        panel.setBackground(Color.WHITE);
        panel.setBounds(138, 264, 457, 73);
        contentPane.add(panel);
        panel.setLayout(null);

        txtNomDutilisateur = new JTextField();
        txtNomDutilisateur.setBackground(Color.WHITE);
        txtNomDutilisateur.setForeground(Color.BLACK);
        txtNomDutilisateur.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtNomDutilisateur.getText().equals("NOM D'UTILISATEUR"))
                {
                    txtNomDutilisateur.setText("");
                }
                else
                {
                    txtNomDutilisateur.selectAll();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtNomDutilisateur.getText().equals("")) {
                    txtNomDutilisateur.setText("NOM D'UTILISATEUR");
                }
            }
        });
        txtNomDutilisateur.setBorder(null);
        txtNomDutilisateur.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 22));
        txtNomDutilisateur.setText("NOM D'UTILISATEUR");
        txtNomDutilisateur.setBounds(10, 6, 411, 60);
        panel.add(txtNomDutilisateur);
        txtNomDutilisateur.setColumns(10);

        JPanel panel_1 = new JPanel();
        panel_1.setForeground(Color.BLACK);
        panel_1.setBorder(new LineBorder(Color.BLACK, 2));
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(138, 348, 457, 73);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        pwdMotDePasse = new JPasswordField();
        pwdMotDePasse.setBackground(Color.WHITE);
        pwdMotDePasse.setForeground(Color.BLACK);
        pwdMotDePasse.setBorder(null);
        pwdMotDePasse.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                if (pwdMotDePasse.getText().equals("MOT DE PASSE"))
                {
                    pwdMotDePasse.setEchoChar('@');
                    pwdMotDePasse.setText("");
                }
                else
                {
                    pwdMotDePasse.selectAll();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (pwdMotDePasse.getText().equals("")) {
                    pwdMotDePasse.setText("MOT DE PASSE");
                    pwdMotDePasse.setEchoChar((char)0);
                }
            }
        });
        pwdMotDePasse.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 22));
        pwdMotDePasse.setEchoChar((char)0);
        pwdMotDePasse.setText("MOT DE PASSE");
        pwdMotDePasse.setBounds(10, 6, 411, 60);
        panel_1.add(pwdMotDePasse);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setIcon(new ImageIcon("./RealGame/elasri/Image/key.png"));
        lblNewLabel_2.setBounds(205, 0, 323, 265);
        contentPane.add(lblNewLabel_2);
        lblNewLabel_3.setFocusable(false);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

        lblNewLabel_3.setBorder(null);
        lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_3.setForeground(Color.BLACK);
        lblNewLabel_3.setBounds(285, 572, 793, 36);
        contentPane.add(lblNewLabel_3);

        JButton btnNewButton = new JButton("SE CONNECTER");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(txtNomDutilisateur.getText().equals("admin") && pwdMotDePasse.getText().equals("admin123")) {
                    lblNewLabel_3.setText("");
                    JOptionPane.showMessageDialog(null, "CONNEXION REUSSITE");
                    dispose();
                    CHOICE C = new CHOICE();
                    C.show();
                }
                else if (txtNomDutilisateur.getText().equals("") || txtNomDutilisateur.getText().equals("NOM D'UTILISATEUR")
                        || pwdMotDePasse.getText().equals("") || pwdMotDePasse.getText().equals("MOT DE PASSE")){
                    lblNewLabel_3.setText("SVP ENTREZ TOUTES LES INFORMATIONS !");
                }
                else {
                    lblNewLabel_3.setText("VOS INFORMATIONS NE SONT PAS CORRECTES !");
                }
            }
        });
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 22));
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBorder(new LineBorder(Color.BLACK, 2));
        btnNewButton.setBounds(285, 630, 793, 92);
        contentPane.add(btnNewButton);

        JButton btnRetourner = new JButton("<");
        btnRetourner.setFocusable(false);
        btnRetourner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
Login lo = new Login();
                lo.show();
            }
        });
        btnRetourner.setBorder(null);
        btnRetourner.setForeground(Color.BLACK);
        btnRetourner.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnRetourner.setBackground(Color.WHITE);
        btnRetourner.setBounds(0, 0, 48, 26);
        contentPane.add(btnRetourner);

        JButton connect = new JButton("SE CONNECTER");
        connect.setFocusable(false);
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(txtNomDutilisateur.getText().equals("admin") && pwdMotDePasse.getText().equals("admin123")) {
                    lblNewLabel_3.setText("");
                    JOptionPane.showMessageDialog(connect, "CONNEXION REUSSITE");
                    dispose();
                    CHOICE C = new CHOICE();
                    C.show();
                }
                else if (txtNomDutilisateur.getText().equals("") || txtNomDutilisateur.getText().equals("NOM D'UTILISATEUR")
                        || pwdMotDePasse.getText().equals("") || pwdMotDePasse.getText().equals("MOT DE PASSE")){
                    JOptionPane.showMessageDialog(connect, "SVP ENTREZ TOUTES LES INFORMATIONS !");
                }
                else {
                    JOptionPane.showMessageDialog(connect, "VOS INFORMATIONS NE SONT PAS CORRECTES !");
                }
            }
        });
        connect.setBackground(Color.WHITE);
        connect.setFont(new Font("Century Gothic", Font.BOLD, 18));
        connect.setBorder(new LineBorder(Color.BLACK, 2));
        connect.setBounds(205, 432, 323, 45);
        contentPane.add(connect);
        //		setUndecorated(true);
        //		setExtendedState(LOGIN_ADMIN.MAXIMIZED_BOTH);
        setVisible(true);
    }
}