package ch.noseryoung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    System.out.println(
        "Menu:\n"
            + "  [1] List shows\n"
            + "  [2] Add new show\n"
            + "  [3] Book show\n"
            + "  [4] Delete show\n");
    int choice = scan.nextInt();

    switch (choice) {
      case 1:
        listShows(connection);
        break;
      case 2:

        break;
      case 3:
        break;
      case 4:
        break;
      default:
        break;
    }
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
      }
      connection = null;
    }
  }

  public static void listShows(Connection connection) {
    try {
      // create a statement
      Statement stmt = connection.createStatement();
      // execute SQL statement
      String sqlQuery =
          "SELECT m.MovieTitle, g.GenreType, d.DirectorName from Movie m "
              + "join Genre g "
              + "ON m.Genre_ID = g.ID_Genre "
              + "join Director d "
              + "ON m.Director_ID = d.ID_Director;";
      ResultSet rs = stmt.executeQuery(sqlQuery);
      // process the result set
      while (rs.next()) {
        String movie_title = rs.getString("MovieTitle");
        String genre_type = rs.getString("GenreType");
        String director_name = rs.getString("DirectorName");

        System.out.println("" + movie_title + "" + genre_type + " " + director_name + "");
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

  public static void addShows(Connection connection){

  };

  void bookShow() {}
}
