package ch.noseryoung;

public class Theater {

    final static int ROWS = 5, SEATS = 9;

    public static void theatre() {

        StringBuilder b = new StringBuilder();
        for (char c = 'A'; c <= 'H'; c++) {
            for (int i = 1; i <= 8; i++) {
                b.append(c);
                b.append(i);
                b.append(' ');
            }
            b.deleteCharAt(b.length() - 1);
            b.append(System.getProperty("line.separator"));
        }
        System.out.println("\n");
        System.out.print(b);
        System.out.println("\n");
    }
}
