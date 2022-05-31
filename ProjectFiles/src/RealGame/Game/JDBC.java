package Game;
import com.sun.tools.jconsole.JConsoleContext;

import elasri.*;
import oracle.jdbc.logging.annotations.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Properties;


public class JDBC {
    public final static String ConnectionString = "jdbc:oracle:thin:@localhost:1521:XE";
    public static final String UserName = "SYSTEM";
    public static final String PWD = "maroc2001mark5";


    public static void InsertValues(int level, int WPM, int correctwords, int wrongWords,
                                             int totalWords, int accuracy, int IDPlayer) {
        try (Connection DBConnect = DriverManager.getConnection(ConnectionString, UserName, PWD);
             Statement stmt = DBConnect.createStatement();) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            int BestScore = BestSCoreByLvl(IDPlayer,level);
            if(WPM>BestScore)
                BestScore= WPM;
            // Execute a query
            System.out.printf("Inserting records into the table...");
            //(P_name,PWD,image,email,age,username)values(
            //("level",wpm,bestscore,correctwords,wrongwords,totalwords,accuracy)
            String sql = "insert into system.race (\"level\",wpm,bestscore,correctwords,wrongwords,totalwords,accuracy,id_player) values(" +
                    level + "," +
                    WPM + "," +
                    BestScore + "," +
                    correctwords + "," +
                    wrongWords + "," +
                    totalWords + "," +
                    accuracy + "," +
                    IDPlayer + ")";



            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            //excecute the restart sequence from the last values
            System.out.println(ex.getMessage());
        }

    }
    public static int SelectBEST(int IDPlayer) {
        int bestscore = 0;
        // Open a connection
        try (Connection conn = DriverManager.getConnection(ConnectionString, UserName, PWD);
             Statement stmt = conn.createStatement();
        ) {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            ResultSet rs = stmt.executeQuery("select max(BESTSCORE) \"BESTSCORE\",ID_PLAYER from system.race where ID_Player = "+IDPlayer+" group by ID_PLAYER");

            while (rs.next()) {
                bestscore = rs.getInt("BESTSCORE");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bestscore;
    }

    public static int BestSCoreByLvl(int IDPlayer, int level) {
        int BestByLevel = 0;
        try (Connection conn = DriverManager.getConnection(ConnectionString, UserName, PWD);
             Statement stmt = conn.createStatement();
        ) {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            ResultSet rs = stmt.executeQuery("select max(BESTSCORE) \"BestByLevel\",ID_PLAYER,\"level\" from system.race where  ID_PLAYER = "+IDPlayer+" " +
                    " and \"level\" =  "+level+" group by \"level\",ID_PLAYER");

            while (rs.next()) {
                BestByLevel = rs.getInt("BestByLevel");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return BestByLevel;
    }


    public static int LastPlayedLvl(int IDPlayer)
    {
        int lsd = 0;
        try (Connection conn = DriverManager.getConnection(ConnectionString, UserName, PWD);
             Statement stmt = conn.createStatement();
        ) {
            Class.forName("oracle.jdbc.driver.OracleDriver");


            ResultSet rs = stmt.executeQuery("select \"level\" from system.race where ID_Race in (select max(ID_RACE) from system.race where ID_PLAYER = "+IDPlayer+")");

            while (rs.next()) {
                lsd = rs.getInt("\"level\"");
            }
        } catch (SQLException ex) {
            System.out.printf(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lsd;
    }
    public static int checkEmail(String User,String Pass)
    {
        try (Connection conn = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
             Statement stmt = conn.createStatement();
        ) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = "Select IDPlayer,Username, EMAIL, PWD from system.Player where (Username = '"+User+"' or EMail = '"+User+"') and PWD = '"+Pass+"'";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {

                Login.User = rs.getString("Username");
                Login.Pass = rs.getString("PWD");
                Login.IDPlayer = rs.getInt("IDPlayer");
                return 1;
                           }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            System.out.println(e1.getMessage());
        }
        return 0;
    }

    public static int SignUp(String Name,int Age,String Mail,String Pass,String User)
    {
        try (Connection conn = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
             Statement stmt = conn.createStatement();
        ) {
            Statement st = conn.createStatement();

            String sql = "INSERT INTO system.PLAYER (P_Name, AGE, EMAIL, PWD, USERNAME) VALUES ('"+Name+"', "+Age+",'"+Mail+"','"+Pass+"','"+User+"')" ;
           return st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
return 0;
    }

public static void showValues(DefaultTableModel model,JTable TRank)
{
    try (Connection conn = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
        // Statement stmt = conn.createStatement();
    ) {
        String sql = "SELECT p.P_Name, r.BestScore, p.Email, p.Username FROM system.Player p " +
                "INNER JOIN system.Race r on p.IDPlayer = r.ID_Player ORDER BY r.BestScore DESC";
        model = (DefaultTableModel)TRank.getModel();
        model.setRowCount(0);//the select begin from the first line


        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();


        while(rs.next()) {
            String name = rs.getString(1);
            String best_S = rs.getString(2);
            String mail = rs.getString(3);
            String user = rs.getString(4);
            model.addRow(new Object[] {TRank.getRowCount() + 1, name, best_S, mail, user});
        }

    } catch (Exception e1) {
        // TODO Auto-generated catch block
        System.out.println(e1.getMessage());
    }

}

public static void showDashBoard(JTable table)
{
    try (Connection conn = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
         Statement stmt = conn.createStatement();
    ) {
        Statement st = conn.createStatement();
        String sql = "select p.IDPlayer, p.P_Name, r.WPM, r.\"level\", r.BestScore, p.Image from system.Player p" +
                " INNER JOIN system.Race r on p.IDPlayer = r.ID_PLAYER order by p.IDPlayer asc";

        ResultSet rs = st.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd .getColumnCount();
        String[] colName = new String[cols];
        for (int i=0; i<cols; i++)
            colName[i]=rsmd.getColumnName(i+1);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(colName);
        while(rs.next()) {
//					    int ID = Integer.valueOf(rs.getInt("ID"));
            String  Id = String.valueOf(rs.getInt("IDPlayer")) ;
            String  Nom_Complet = rs.getString("P_Name");
            String	Dernier_Score = String.valueOf(rs.getInt("WPM"));
            String	Niveau_Actuel = String.valueOf(rs.getInt("\"level\""));
            String	Meilleur_Score = String.valueOf(rs.getInt("BestScore"));
            String  Photo_Profil = String.valueOf(rs.getString("Image"));

            String row [] = {Id, Nom_Complet, Dernier_Score, Niveau_Actuel,  Meilleur_Score, Photo_Profil};
            DefaultTableModel model1 = (DefaultTableModel) table.getModel();
            model1.addRow(row);
        }

    } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
}


    public static void showTable(TableModel model, JTextArea Id_Joueur, JTextArea Nom_Complet
            , JTextArea Dernier_Score, JTextArea Niveau_Actuel, JTextArea Meilleur_Score,
                                 int selectedRow,JLabel Photo_Profil) {

        String sql = "select p.IDPlayer, p.P_Name, r.WPM, r.\"level\", r.BestScore, p.Image from system.Player p" +
            " INNER JOIN system.Race r on p.IDPlayer = r.ID_PLAYER order by p.IDPlayer asc";
    try (Connection conn = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
         Statement stmt = conn.createStatement();
    ) {

        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            Id_Joueur.setText(model.getValueAt(selectedRow, 0).toString());
            Nom_Complet.setText(model.getValueAt(selectedRow, 1).toString());
            Dernier_Score.setText(model.getValueAt(selectedRow, 2).toString());
            Niveau_Actuel.setText(model.getValueAt(selectedRow, 3).toString());
            Meilleur_Score.setText(model.getValueAt(selectedRow, 4).toString());
          // Photo_Profil.setText(model.getValueAt(selectedRow, 5).toString());
//
            String img = rs.getString("image");
            ImageIcon image = new ImageIcon(img);
            java.awt.Image in =  image.getImage();
            java.awt.Image myImg = in.getScaledInstance(Photo_Profil.getWidth(), Photo_Profil.getHeight(), java.awt.Image.SCALE_SMOOTH);
            ImageIcon imggg = new ImageIcon(myImg);
            Photo_Profil.setIcon(imggg);
//
////				byte[] image = rs.getBytes("Image");
////                ImageIcon format = new ImageIcon(image);
////                Image img = format.getImage();
////                Image newimage = img.getScaledInstance(Photo_Profil.getWidth(), Photo_Profil.getHeight(), Image.SCALE_SMOOTH);
////                ImageIcon images = new ImageIcon(newimage);
////                Photo_Profil.setIcon(images);
//
//
        }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }



    public static void Refresh(DefaultTableModel model,JTable TRank)
    {
        String sql = "select P_Name, BestScore, Username from player p inner join race r on p.IDPlayer = r.ID_Player where r.bestscore is not null ORDER BY r.bestScore desc" ;
        try (Connection con = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
             Statement stmt = con.createStatement();
        ) {

            model = (DefaultTableModel)TRank.getModel();
            model.setRowCount(0);

            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();


            while(rs.next()) {
                //Object o[]= {rs.getString("column1"), rs.getInt("column2"), rs.getString("column3")};
                //tm.addRow(o);
                String name = rs.getString(1);
                String best_S = rs.getString(2);
                String user = rs.getString(3);
                model.addRow(new Object[] {TRank.getRowCount() + 1, name, best_S,user});
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            System.out.println(e1.getMessage());
        }
    }


    public static void MYFRank(String Rankk,DefaultTableModel model,JTable TRank,JTable TMyRank,
                               String myRank)
    {
        String sql = "select p.P_Name, r.BestScore, p.Username from system.player" +
                "p inner join system.race r on p.IDPlayer = r.ID_Player where" +
                " p.username=" +
                ""+Rankk+";" ;
        try (Connection con = DriverManager.getConnection(JDBC.ConnectionString, JDBC.UserName, JDBC.PWD);
             Statement stmt = con.createStatement();
        ) {
            model = (DefaultTableModel)TMyRank.getModel();
            model.setRowCount(0);//the select begin from the first line

            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();


            String query = "select count(*) from player p inner join race r on p.IDPlayer = r.ID_Player where r.bestscore > ( select r.bestscore from player p inner join race r on p.IDPlayer = r.ID_Player where p.username = '"+myRank+"') order by r.bestscore desc";

            Statement pr = con.prepareStatement(query);
            ResultSet rt = pr.executeQuery(query);

            while(rs.next()&& rt.next()) {
                int Rank = rt.getInt(1);
                String name = rs.getString(1);
                String best_S = rs.getString(2);
                String user = rs.getString(3);
                model.addRow(new Object[] {Rank+1, name, best_S,user});
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            System.out.println(e1.getMessage());
        }
    }

    public static void main(String []args) {
//        System.out.println(SelectBEST(1));
  // System.out.println(BestSCoreByLvl(1,2) );
//        InsertValues(2,50,200,4,208,50,7);
//        System.out.println( LastPlayedLvl(1));
//        System.out.println(checkEmail("aka","root12345"));
//        System.out.println(SignUp("baba",20,"chen.ten@gmail.com","admin","checnTEn"));
    }


}
