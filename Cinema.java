package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private final static Scanner input = new Scanner(System.in);
    private static int nbTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();

        totalIncome = computeTotal(rows, seats);

        int[][] cinema = new int[rows][seats];

        for (int[] ints : cinema) {
            Arrays.fill(ints, 0);
        }

        int answer;

        do {
            menu();
            answer = input.nextInt();

            switch (answer) {
                case 1 -> print(rows, seats, cinema);
                case 2 -> computeTicket(rows, seats, cinema);
                case 3 -> statistics(rows, seats);
            }
        } while (answer != 0);

    }

    public static void print(int rows, int seats, int[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < seats; j++) {
                if (cinema[i - 1][j] == 0) {
                    System.out.print("S ");
                } else {
                    System.out.print("B ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void statistics(int rows, int seats) {
        System.out.println("Number of purchased tickets: "  + nbTickets);
        System.out.printf("Percentage: %.2f%%", (double)nbTickets / (rows * seats) * 100);
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void computeTicket(int rows, int seats, int[][] cinema) {
        int check;
        do {
            check = 1;
            System.out.println("Enter a row number:");
            int row = input.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seat = input.nextInt();

            if (row < 1 || row > cinema.length || seat < 1 || seat > cinema[row - 1].length) {
                System.out.println("Wrong input!");
                check = 0;
            } else if (cinema[row -1][seat - 1] == 1) {
                System.out.println("That ticket has already been purchased!");
                check = 0;
            } else {
                int totalSeats = rows * seats;

                int ticket;

                if (totalSeats <= 60) {
                    ticket = 10;
                } else {
                    if (row <= (rows / 2)) {
                        ticket = 10;
                    } else {
                        ticket = 8;
                    }
                }

                System.out.println("Ticket price: $" + ticket);
                cinema[row - 1][seat - 1] = 1;
                nbTickets++;
                currentIncome += ticket;
            }
        } while (check != 1);
    }

    public static void menu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static int computeTotal(int rows, int seats) {
        int total;

        if (rows * seats <= 60) {
            total = rows  * seats * 10;
        } else {
            int firstHalf = rows / 2;
            int secondHalf = rows - firstHalf;
            total = (firstHalf * seats * 10) + (secondHalf * seats * 8);
        }

        return total;
    }
}