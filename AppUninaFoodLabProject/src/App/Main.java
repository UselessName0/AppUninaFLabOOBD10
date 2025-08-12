package App;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Database.DBManager;
import GUI.WelcomeFrame;

//Main
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WelcomeFrame frame = new WelcomeFrame();
                frame.setVisible(true);
            }
        });
        
        try (Connection conn = DBManager.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Connessione al database riuscita!");
            } else {
                System.out.println("⚠️ Connessione non riuscita.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Errore di connessione: " + e.getMessage());
        }
        
    }
}
