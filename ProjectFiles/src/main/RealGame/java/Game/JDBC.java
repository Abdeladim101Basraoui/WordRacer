package Game;
import com.sun.tools.jconsole.JConsoleContext;

import java.sql.*;
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
            String sql = "insert into sys.race (\"level\",wpm,bestscore,correctwords,wrongwords,totalwords,accuracy,id_player) values(" +
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

            ResultSet rs = stmt.executeQuery("select max(BESTSCORE) \"BESTSCORE\",ID_PLAYER from sys.race where ID_Player = "+IDPlayer+" group by ID_PLAYER");

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

            ResultSet rs = stmt.executeQuery("select max(BESTSCORE) \"BestByLevel\",ID_PLAYER,\"level\" from sys.race where  ID_PLAYER = "+IDPlayer+" " +
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


            ResultSet rs = stmt.executeQuery("select \"level\" from sys.race where ID_Race in (select max(ID_RACE) from sys.race where ID_PLAYER = "+IDPlayer+")");

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
//    }
//    public static void main(String []args) {
////        System.out.println(SelectBEST(1));
//  // System.out.println(BestSCoreByLvl(1,2) );
////        InsertValues(2,50,200,4,208,50,7);
//        System.out.println( LastPlayedLvl(1));
//}
}
