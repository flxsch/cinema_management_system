package ch.noseryoung;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static ch.noseryoung.Booking.bookShow;
import static ch.noseryoung.Booking.cancelBooking;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        Connection connection = connect();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Seat> seats = new ArrayList<Seat>();
        HashMap<Customer, Seat> infos = new HashMap<>();


        Menu(connection, customers, seats, infos);
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            // connect to db
            connection =
                    DriverManager.getConnection("jdbc:mariadb://192.168.99.100/Cinema", "root", "mariadb");

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            if (connection != null) {
            }
        }
        return connection;
    }

    public static void Menu(Connection connection, ArrayList<Customer> customers, ArrayList<Seat> seats, HashMap<Customer, Seat> infos) {
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
                                    + "  [4] Cancel Booking\n"
                                    + "  [5] Delete movie\n"
                                    + "  [6] Exit\n");

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
                    bookShow(connection, customers, seats, infos);
                    break;
                case 4:
                    cancelBooking(customers, seats);
                    break;
                case 5:
                    deleteMovie(connection);
                    break;
                case 6:
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

    public static void addMovie(Connection connection) {
    }


    public static void deleteMovie(Connection connection) {
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
