package ch.noseryoung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import static ch.noseryoung.Main.listMovies;
import static ch.noseryoung.Theater.theatre;

public class Booking {

    public static void bookShow(Connection connection, ArrayList<Customer> customers, ArrayList<Seat> seats) {

        ArrayList<String> reservedSeats = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("|      Make Booking     |");
        System.out.println("-------------------------\n");

        Random rnd = new Random();
        int costumerId = rnd.nextInt(10000);
        System.out.print("Enter your name: \n");
        String name = scanner.nextLine();
        System.out.print("Enter your surname: \n");
        String surname = scanner.nextLine();
        System.out.print("Enter your streetname: \n");
        String streetname = scanner.nextLine();
        System.out.print("Enter your streetnumber: \n");
        String streetnumber = scanner.nextLine();
        System.out.print("Enter your PLZ: \n");
        String plz = scanner.nextLine();
        System.out.print("Enter your Cityname: \n");
        String cityname = scanner.nextLine();

        Customer customer = new Customer(costumerId, name, surname, streetname, streetnumber, plz, cityname);
        customers.add(customer);

        System.out.println(customers.getClass());

        listMovies(connection);

        int repeat = 0;
        boolean validShow = true;

        do {

            System.out.print("Enter the show number: \n");
            int showNumber = Integer.parseInt(scanner.nextLine());


            if (showNumber >= 10) {
                System.out.println("Not a Valid Show");
                validShow = false;
            } else {
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
                validShow = true;
            }

        } while (validShow == false);


        do {

            theatre();

            System.out.print("Please Enter a Seat: ");
            String seatNumber = scanner.nextLine();

            if (reservedSeats.contains(seatNumber)) {
                System.out.println("Sorry the seat is already reserved.");
                repeat = 1;

            } else {
                reservedSeats.add(seatNumber);
                System.out.println("The seat is now reserved for you.");
                System.out.println();
                System.out.print("Enter 1 to reserve another seat or 2 to check out: \n");
                repeat = Integer.parseInt(scanner.nextLine());
            }

        } while (repeat == 1);

        int prizePerSeat = 12;
        int totalCost = prizePerSeat * reservedSeats.size();
        System.out.println("\n-------------------------");
        System.out.println("         Your Bill");
        System.out.println("-------------------------");
        System.out.println("Costumer ID: " + customer.getId());
        System.out.println(customer.printCostumer());
        System.out.println("\nTotal costs: " + totalCost + " Franken");
        System.out.print("Seat number(s): ");
        for (String item : reservedSeats) {
            System.out.print(item.toString() + " ");
        }
        Seat seat = new Seat(reservedSeats, totalCost);
        seats.add(seat);
    }

    public static void cancelBooking(ArrayList<Customer> customers, ArrayList<Seat> seats) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------");
        System.out.println("|    Cancel Booking   |");
        System.out.println("-----------------------\n");
        System.out.print("Enter the costumer id: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        if (customers.contains(customerId)) {

            System.out.println("Your booking is now canceled");
        } else {
            System.out.println("There is no booking with this Customer-ID");
        }

        for (Customer item : customers) {
            System.out.print(item.printCostumer() + " ");
        }

        for (Seat item : seats) {
            System.out.print(item.toString() + " ");
        }
    }
}