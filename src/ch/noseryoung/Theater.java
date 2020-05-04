package ch.noseryoung;

public class Theater {

    private String theater_ID;
    public static final int BOARD_SIZE = 5;
    private int total_seats;

    public static void theatre() {

            for (int row = 1; row < 8; row++)
            {
                System.out.println("");
                System.out.println("------------------------------");

                for (int column = 1; column < 14; column++)
                {
                    System.out.print("|"+column + "");
                }
            }
            System.out.println("");
            System.out.println("------------------------------");
        }
}
