package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Entities.Chef;

public class ChefDAO {

    public void InserisciChef(Chef chef) {
        String sql = "Query SQL";
        try (Connection conn = DBManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chef.getID_Chef());
            stmt.setString(2, chef.getNome());
            stmt.setString(3, chef.getCognome());
            stmt.setString(4, chef.getEmail());
            stmt.setString(5, chef.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("L'inserimento non è andato a buon fine!"); 
        }
    }
}