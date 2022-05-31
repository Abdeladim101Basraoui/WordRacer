package Badr;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class CHOICE extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CHOICE frame = new CHOICE();
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
    public CHOICE() {
        setTitle("Word Racing - ADMIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 734, 511);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton RANKING = new JButton("");
        RANKING.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        RANKING.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashBoard D = new DashBoard();
                D.show();
            }
        });
        RANKING.setIcon(new ImageIcon("./Images/monitor.png"));
        RANKING.setFocusable(false);
        RANKING.setBorder(null);
        RANKING.setBackground(Color.WHITE);
        RANKING.setFont(new Font("Tahoma", Font.PLAIN, 11));
        RANKING.setBounds(57, 137, 281, 272);
        panel.add(RANKING);

        JLabel lblNewLabel = new JLabel("DASHBOARD");
        lblNewLabel.setFocusable(false);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(64, 420, 267, 43);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("RANKING");
        lblNewLabel_1.setFocusable(false);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
        lblNewLabel_1.setBounds(402, 420, 267, 43);
        panel.add(lblNewLabel_1);

        JButton RANKING_1 = new JButton("");
        RANKING_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        RANKING_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Rank R = new Rank();
                R.show();
            }
        });
        RANKING_1.setIcon(new ImageIcon("./Images/rank (1).png"));
        RANKING_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        RANKING_1.setFocusable(false);
        RANKING_1.setBorder(null);
        RANKING_1.setBackground(Color.WHITE);
        RANKING_1.setBounds(395, 137, 281, 272);
        panel.add(RANKING_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(0, 0, 734, 126);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblWordRacing = new JLabel("WORD  RACING");
        lblWordRacing.setHorizontalAlignment(SwingConstants.CENTER);
        lblWordRacing.setFont(new Font("Century Gothic", Font.PLAIN, 50));
        lblWordRacing.setBounds(169, 23, 396, 79);
        panel_1.add(lblWordRacing);

        JButton btnNewButton = new JButton("<");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setFocusable(false);
        btnNewButton.setBorder(null);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        btnNewButton.setBounds(0, 11, 57, 23);
        panel_1.add(btnNewButton);
    }

}
