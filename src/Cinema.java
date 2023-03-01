import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static String[][] cinema;
    public static int ticketsCounter = 0, currentIncome = 0, totalIncome = 0;
    public static int totalSeats = 0;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        totalSeats = rows * seats;
        String[] top = new String[seats+1];
        top[0] = " ";
        for (int i = 1; i <= seats; i++) {
            top[i] = ""+i;
        }

        cinema = new String[rows+1][seats+1];
        cinema[0] = top;

        for (int i = 1; i <= rows; i++) {
            cinema[i][0] = ""+i;
            for (int j = 1; j <= seats; j++) {
                cinema[i][j] = "S";
            }
        }

        boolean loop = true;
        while (loop) {
            showOptions();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showLayout();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    showStats();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }

    public static void showLayout() {
        System.out.println("Cinema:");
        int rows = cinema.length-1;
        for (int i = 0; i <= rows; i++) {
            System.out.println(String.join(" ", cinema[i]));
        }
        System.out.println();
    }

    public static void showOptions() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void buyTicket() {

        System.out.println("Enter a row number:");
        int chooseRow, chooseSeat;
        while(true){
            chooseRow = scanner.nextInt();
            if (chooseRow > 0 && chooseRow < cinema.length){
                break;
            } else {
                System.out.println("Wrong input!");
                System.out.println();
                System.out.println("Enter a row number:");
            }
        }

        System.out.println("Enter a seat number in that row:");
        while(true){
            chooseSeat = scanner.nextInt();
            scanner.nextLine();
            if (chooseSeat > 0 && chooseSeat < cinema[0].length){
                break;
            } else {
                System.out.println("Wrong input!");
                System.out.println();
                System.out.println("Enter a seat number in that row:");
            }
        }

        int rows = cinema.length-1;
        int price;

        if (totalSeats<60) {
            price = 10;
        } else {
            int halfOfRows = rows/2;
            if(chooseRow > halfOfRows){
                price = 8;
            } else {
                price = 10;
            }
        }

        if (cinema[chooseRow][chooseSeat].equals("B")){
            System.out.println("That ticket has already been purchased!");
            buyTicket();
        } else {
            cinema[chooseRow][chooseSeat] = "B";
            System.out.println();
            System.out.println("Ticket price: $" + price);
            System.out.println();
            ticketsCounter++;
            currentIncome += price;
        }

    }

    public static int getTotalIncome() {
        int rows = cinema.length-1;
        int seats = cinema[0].length-1;
        int totalIncome = 0;

        if (totalSeats<60) {
            totalIncome = 10*totalSeats;
        } else {
            int halfOfRows = rows/2;
            totalIncome = (rows - halfOfRows) * seats * 8 + halfOfRows * seats * 10;
        }
        return totalIncome;
    }

    public static void showStats() {
        System.out.println("Number of purchased tickets: " + ticketsCounter);
        double seatsPercentage = (double)(ticketsCounter * 100)/totalSeats;
        System.out.printf("Percentage: %.2f%%\n", seatsPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + getTotalIncome());
    }
}