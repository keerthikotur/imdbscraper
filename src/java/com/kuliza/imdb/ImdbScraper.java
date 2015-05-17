package com.kuliza.imdb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImdbScraper {

    private String top250Url = "http://www.imdb.com/chart/top?ref_=nv_sr_1";
    List<ImdbMovie> allMovies = new ArrayList<ImdbMovie>();
    private Connection con = null;

    public ImdbScraper(Connection con) {
        this.con = con;
    }

    private Document getDocumentFromUrl(String url) {
        try {
            return Jsoup.connect(url).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fetchAndStoreMovieDetails() {
        Document doc = getDocumentFromUrl(top250Url);
        Elements titles = doc.select(".titleColumn");
        ImdbMovie movie;
        PreparedStatement preparedStatement = null;
        
        String l;
        int i = 0;
        
        try {
            String insertTableSQL = "INSERT INTO movies(movieid, title, relaeseddate, imagepath, category, "
                        + "rating, noofusersrated, reviewscount, metascore, rank) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = con.prepareStatement(insertTableSQL);
            for (Element e : titles) {
            movie = new ImdbMovie();

            movie.setRank(e.childNode(1).childNode(0).toString());
            movie.setName(e.childNode(3).childNode(0).toString());

            l = e.childNodes().get(3).attr("abs:href");
            movie.setLink(l);
            movie.setId(l.substring(26, l.lastIndexOf('/')));
//			movie.setImageLink(e.previousElementSibling().childNode(0).childNode(0).attr("src"));

            Document doc1 = getDocumentFromUrl(e.childNodes().get(3).attr("abs:href")); //URL for that movie
            movie.setImageLink(GetFieldsHelper.queryImageLink(doc1));
            movie.setReleasedOn(GetFieldsHelper.queryReleasedOn(doc1));
            movie.setCategory(GetFieldsHelper.queryCategory(doc1));
            movie.setRating(GetFieldsHelper.queryRating(doc1));
            movie.setUsersRated(GetFieldsHelper.queryUsersRated(doc1));
            movie.setReviewsCount(GetFieldsHelper.queryReviewsCount(doc1));
            movie.setMetascore(GetFieldsHelper.queryMetascore(doc1));
            

                preparedStatement.setString(1, movie.getId());
                preparedStatement.setString(2, movie.getName());
                preparedStatement.setString(3, movie.getReleasedOn());
                preparedStatement.setString(4, movie.getImageLink());
                preparedStatement.setString(5, movie.getCategory());
                preparedStatement.setString(6, movie.getRating());
                preparedStatement.setString(7, movie.getUsersRated());
                preparedStatement.setString(8, movie.getReviewsCount());
                preparedStatement.setString(9, movie.getMetascore());
                int rank = Integer.parseInt(movie.getRank().substring(0,movie.getRank().length()-2));
                System.out.println(rank);
                preparedStatement.setInt(10, rank);
                preparedStatement.addBatch();


            allMovies.add(movie);
        }
        System.out.println("Inserting into database");
            preparedStatement.executeBatch();
            } catch (SQLException ex) {
            Logger.getLogger(ImdbScraper.class.getName()).log(Level.SEVERE, null, ex);
        }
           finally{
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImdbScraper.class.getName()).log(Level.SEVERE, null, ex);
            }
                 System.out.println("Inserting done");
            }
    }
   
}
