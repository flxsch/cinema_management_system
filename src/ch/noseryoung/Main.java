package ch.noseryoung;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        Connection connection = connect();

        Menu(connection);
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            // connect to db
            connection =
                    DriverManager.getConnection("jdbc:mariadb://192.168.182.128/Cinema", "root", "1234");

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            if (connection != null) {}
        }
        return connection;
    }

    public static void Menu(Connection connection) {
        int choice;
        do {
            while (true) {
                try {
                    System.out.println(
                            "-------------------------\n"
                                    + "| Cinema Booking System |\n"
                                    + "-------------------------\n\n"
                                    + "  [1] List movies\n"
                                    + "  [2] Add movie\n"
                                    + "  [3] Book show\n"
                                    + "  [4] Delete movie\n"
                                    + "  [5] Exit\n");
                    choice = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("el hijo de puta");
                    continue;
                }
                break;
            }

            switch (choice) {
                case 1:
                    listMovies(connection);
                    break;
                case 2:
                    addMovie(connection);
                    break;
                case 3:
                    bookShow();
                    break;
                case 4:
                    deleteMovie(connection);
                    break;
                case 5:
                    exit(connection);
                    break;
                default:
                    break;
            }
            System.out.print("\n\n\n");
        } while (choice != 5);
    }

    public static void listMovies(Connection connection) {
        try {
            // create a statement
            Statement statement = connection.createStatement();
            // execute SQL statement
            String sql_query =
                    "SELECT m.ID_Movie, m.MovieTitle, g.GenreType, d.DirectorName FROM Movie m "
                            + "JOIN Genre g "
                            + "ON m.Genre_ID = g.ID_Genre "
                            + "JOIN Director d "
                            + "ON m.Director_ID = d.ID_Director ORDER BY ID_Movie ASC;";
            ResultSet result_set = statement.executeQuery(sql_query);

            TablePrinter tablePrinter = new TablePrinter();
            tablePrinter.setHeaders("Nr.", "Movie title", "Genre type", "Director name");

            // process the result set
            while (result_set.next()) {
                String id_movie = result_set.getString("ID_Movie");
                String movie_title = result_set.getString("MovieTitle");
                String genre_type = result_set.getString("GenreType");
                String director_name = result_set.getString("DirectorName");
                tablePrinter.addRow(id_movie, movie_title, genre_type, director_name);
            }
            tablePrinter.print();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static int getGenreID(Connection connection, String genre_type) {
        Map<String, Integer> genres = new HashMap<>();

        int genre_id = 0;
        System.out.println(genre_type);
        try {
            Statement statement = connection.createStatement();
            // execute SQL statement
            ResultSet result_set = statement.executeQuery("SELECT * FROM Genre;");
            while (result_set.next()) {
                genres.put(result_set.getString("GenreType"), result_set.getInt("ID_Genre"));
            }
            if (genres.containsKey(genre_type)) {
                genre_id = genres.get(genre_type);
            } else {
                statement.executeUpdate(
                        "INSERT INTO Genre (GenreType) VALUES (\'" + genre_type + "\');");
                for(int x : genres.values()){
                    int temp = 0;
                    if(x > temp){
                        temp = x;
                    }
                    genre_id = temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genre_id;
    }

    public static int getDirectorID(Connection connection, String director_name) {
        Map<String, Integer> directors = new HashMap<>();
        int director_id = 0;
        try {
            Statement statement = connection.createStatement();
            // execute SQL statement
            ResultSet result_set = statement.executeQuery("SELECT * FROM Director;");
            while (result_set.next()) {
                directors.put(result_set.getString("DirectorName"), result_set.getInt("ID_Director"));
            }
            if (directors.containsKey(director_name)) {
                director_id = directors.get(director_name);
            } else {
                statement.executeUpdate(
                        "INSERT INTO Director (DirectorName) VALUES (\'" + director_name + "\');");
                for(int x : directors.values()){
                    int temp = 0;
                    if(x > temp){
                        temp = x;
                    }
                    director_id = temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return director_id;
    }

    public static void addMovie(Connection connection) {
        try {
            System.out.println("Movie title: ");
            String movie_title = scan.nextLine();
            System.out.println("Movie play time in minutes:");
            String play_time = scan.nextLine();
            System.out.println("Genre type:");
            String genre_type = scan.nextLine();
            System.out.println("Director name");
            String director_name = scan.nextLine();

            // create a statement
            Statement statement = connection.createStatement();

            int genre_id = getGenreID(connection, genre_type);
            int director_id = getDirectorID(connection, director_name);

            statement.executeQuery(
                    "INSERT INTO Movie (MovieTitle, MovieTime, Genre_ID, Director_ID) VALUES (\'"
                            + movie_title
                            + "\',\'"
                            + play_time
                            + "\',\'"
                            + genre_id
                            + "\',\'"
                            + director_id
                            + "\');");

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        System.out.println("Added successfully");
    }

    public static void bookShow() {}

    public static void deleteMovie(Connection connection) {
        System.out.println("Which movie do you want to delete?");
        listMovies(connection);
        int movie_id = Integer.parseInt(scan.nextLine());
        try{
            Statement statement = connection.createStatement();
            // execute SQL statement
            statement.executeUpdate("DELETE FROM Movie WHERE ID_Movie =\' " + movie_id + "\';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exit(Connection connection) {
        System.out.println("Good bye, have a nice one!");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
            connection = null;
        }
    }
}

