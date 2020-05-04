package ch.noseryoung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static ch.noseryoung.Main.listMovies;
import static ch.noseryoung.Theater.theatre;

public class Booking {

    public static void bookShow(Connection connection) {

        List<Integer> reservedSeats = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("|       Make Booking     |");
        System.out.println("-------------------------\n");

        Random rnd = new Random();
        int costumerId = rnd.nextInt(10000);
        System.out.print("Enter your surname: \n");
        String surname = scanner.nextLine();
        System.out.print("Enter your name: \n");
        String name = scanner.nextLine();
        System.out.print("Enter your streetname: \n");
        String streetname = scanner.nextLine();
        System.out.print("Enter your streetnumber: \n");
        int streetnumber = scanner.nextInt();
        System.out.print("Enter your PLZ: \n");
        int plz = scanner.nextInt();
        System.out.print("Enter your Cityname: \n");
        String cityname = scanner.nextLine();

        listMovies(connection);
        Customer customer = new Customer(costumerId, surname, name, streetname, streetnumber, plz, cityname);
        System.out.print("Enter the show number: \n");
        int showNumber = scanner.nextInt();

        int repeat = 0;

        try {
            Statement statement = connection.createStatement();
            String sql_query =
                    "SELECT m.MovieTitle, g.GenreType, d.DirectorName FROM Movie m "
                            + "JOIN Genre g "
                            + "ON m.Genre_ID = g.ID_Genre "
                            + "JOIN Director d "
                            + "ON m.Director_ID = d.ID_Director WHERE ID_Movie=" + showNumber + ";";

            ResultSet result_set = statement.executeQuery(sql_query);


            while (result_set.next()) {
                String movie_title = result_set.getString("MovieTitle");
                String genre_type = result_set.getString("GenreType");
                String director_name = result_set.getString("DirectorName");
                System.out.println(movie_title + ", " + genre_type + ", " + director_name);

            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        do {

            theatre();
            System.out.print("Please Enter a Seatnumber \n");
            int seatNumber = scanner.nextInt();

            if (reservedSeats.contains(seatNumber)) {

                System.out.println("Sorry the seat is already reserved.");
                repeat = 1;

            } else {

                reservedSeats.add(seatNumber);
                System.out.println("The seat is now reserved for you.");

                System.out.println();
                System.out.print("Enter 1 to reserve another seat or 2 to check out: \n");
                repeat = scanner.nextInt();
            }

        } while (repeat == 1);

        int prizePerSeat = 12;
        int totalCost = prizePerSeat * reservedSeats.size();
        System.out.println("\n-------------------------");
        System.out.println("       Your Bill");
        System.out.println("-------------------------");
        System.out.println("Costumer ID: " + customer.getId());
        System.out.println(customer.printCostumer());
        System.out.println("\nTotal costs: " + totalCost + " Franken");
        System.out.print("Seat number(s): ");
        for(Integer item : reservedSeats){
            System.out.print(item.toString()+" ");
        }

    }
}
