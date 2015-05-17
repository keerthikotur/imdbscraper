package com.kuliza.imdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImdbScraperMain {

    public static void main(String[] str) {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "postgres");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
           Statement statement = null;
            try {
                statement = connection.createStatement();

                ResultSet resultset = statement.executeQuery("SELECT movieid, title, relaeseddate, imagepath, category, "
                        + "rating, noofusersrated, reviewscount, metascore FROM movies;");
                if(!resultset.next()){
                    System.out.println("No records found in db fetch from website");
                    ImdbScraper imdbScraper = new ImdbScraper(connection);
                    imdbScraper.fetchAndStoreMovieDetails(); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ImdbScraper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Failed to make connection!");
        }


    }
}