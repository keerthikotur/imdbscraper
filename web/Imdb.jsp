<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.kuliza.imdb.*" %>
<% Class.forName("org.postgresql.Driver");%>

<HTML>
    <HEAD>
        <TITLE>Fetching Data From a Database</TITLE>
        <style>
            body {
                font-size: 75%;
                color: #222;
                background: #fff;
                font-family: "Helvetica Neue",Arial,Helvetica,sans-serif;
                font-size: 12px;
                line-height: 1.3;
                font-family: Arial,Helvetica,Sans-Serif;
                color: #ccc;
                background: url('http://www.yify-torrent.org/images/body-bg.gif');
            }

            .movies {
                margin: 0;
                padding: 0;
                border: 0;
                font-size: 100%;
                font: inherit;
                vertical-align: baseline;
            }

            .box{
                padding-bottom: 21px;
            }
            .box .movie{
                float: left;
                padding: 15px 35px;
                text-align: center;
                width: 170px;
            }

            .movie DIV.name {
                width:200px;
                height:50px;
                font-weight: bold;
                color: #fff;
                font-size: 14px;
                text-align: left;
                display: block;
                padding: 5px;
                margin-left: 20px;
            }

            .star-box {
                border-color: #e8e8e8;
                border-style: solid;
                border-width: 1px 0;
                font-size: 12px;
                margin-right: -12px;
                padding: 10px 12px 11px 0;
                width: 200px;
                height:50px;
            }

            .star-box-details {
                width: 200px;
                float: left;
                margin-right: -5px;
            }

            strong {
                background: url(http://ia.media-imdb.com/images/G/01/imdb/images/seen/star-sprite-1859495344._V_.png) no-repeat 0 -83px;
                padding-left: 20px;
            }
            .movie-image{
                height: 200px;
                padding: 5px;
                margin-left: 30px;
            }
        </style>
    </HEAD>

    <BODY>

        <H1>Top 250 Movies</H1>

        <%
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

                    ResultSet resultset = statement.executeQuery("SELECT title, relaeseddate, imagepath, category, "
                            + "rating, noofusersrated, reviewscount, metascore, rank FROM movies order by rank;");
                    if (!resultset.next()) {
                        System.out.println("No records found in db fetch from website");
                        ImdbScraper imdbScraper = new ImdbScraper(connection);
                        imdbScraper.fetchAndStoreMovieDetails();
                    } else {
        %>


        <div class="movies">
            <div class="box">
                <%
                    do {
                        String metastore = "";
                        if (resultset.getString(8) != null) {
                            metastore = resultset.getString(8);
                        }

                %>
                
                <div class="movie">

                    <DIV class="name"> <%= resultset.getString(9)%>. <%= resultset.getString(1)%> (<%= resultset.getString(2)%>)</DIV>
                    <div class="movie-image-div">
                        <img class="movie-image" src="<%= resultset.getString(3)%>" alt="<%= resultset.getString(1)%> (<%= resultset.getString(2)%>)"/>
                    </div>
                    <div class="star-box">
                        <div class="star-box-details">
                            Ratings:
                            <strong><span><b><%= resultset.getString(5)%></b></span></strong>
                            <span>/<span>10</span></span> from <%= resultset.getString(6)%> users

                            &nbsp; <span>Metascore:<b>
                                    <%= metastore%></b> </span>           

                            <br>  Reviews: <span itemprop="reviewCount"><%= resultset.getString(7)%></span>
                        </div> 
                        <div class="clear"></div>
                    </div>
                </div>
                <%
                    }while (resultset.next());
                %>
            </div>
        </div>
        <%
            }
            } catch (SQLException ex) {
            }
            } else {
                System.out.println("Failed to make connection!");
    }%>




    </BODY>
</HTML>
