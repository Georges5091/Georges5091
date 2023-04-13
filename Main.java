package tictactoe;

import java.util.Scanner;

public class Main {

    private final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        print();
    }

    public static void print() {
        System.out.println("----------");
        for (int i = 0; i < 9; i +=3) {
            System.out.println("|       |");
        }
        System.out.println("----------");
        printNew();
    }

    public static String analyze(String msg) {
        int count = 0;
        int xCount = 0;
        int oCount = 0;
        int xWin = 0;
        int oWin = 0;
        String resultStr;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == ' ') {
                count++;
            } else if (msg.charAt(i) == 'X') {
                xCount++;
            } else {
                oCount++;
            }
        }

        int result = xCount - oCount;
        if (result != 1 && result != 0 && result != -1) {
            System.out.println("Impossible");
            return "Impossible";
        }

        for (int i = 0; i < msg.length() - 2; i+=3) {
            if (msg.startsWith("XXX", i)) {
                xWin++;
            } else if (msg.charAt(i) == 'O' && msg.charAt(i + 1) == 'O' && msg.charAt(i + 2) == 'O') {
                oWin++;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (msg.charAt(i) == 'X' && msg.charAt(i + 3) == 'X' && msg.charAt(i + 6) == 'X') {
                xWin++;
            } else if (msg.charAt(i) == 'O' && msg.charAt(i + 3) == 'O' && msg.charAt(i + 6) == 'O') {
                oWin++;
            }
        }

        if ((msg.charAt(0) == 'X' && msg.charAt(4) == 'X' && msg.charAt(8) == 'X') || (msg.charAt(2) == 'X' && msg.charAt(4) == 'X' && msg.charAt(6) == 'X')) {
            xWin++;
        } else if ((msg.charAt(0) == 'O' && msg.charAt(4) == 'O' && msg.charAt(8) == 'O') || (msg.charAt(2) == 'O' && msg.charAt(4) == 'O' && msg.charAt(6) == 'O')) {
            oWin++;
        }

        if (xWin > 0 && oWin > 0) {
            System.out.println("Impossible");
            resultStr = "Impossible";
        } else if (oWin > 0) {
            System.out.println("O wins");
            resultStr = "O wins";
        } else if (xWin > 0) {
            System.out.println("X wins");
            resultStr = "X wins";
        } else if (count == 0) {
            System.out.println("Draw");
            resultStr = "Draw";
        } else {
            System.out.println("Game not finished");
            resultStr = "Game not finished";
        }
        return resultStr;
    }

    public static void printNew() {
        int check;
        String checkAnalyse = "Game not finished";
        String newMsg = "         ";
        char[] arr = newMsg.toCharArray();
        int count = 0;
        char c;

        do {
            check = 1;

            String answer = input.nextLine();

            int firstDigit;
            int secondDigit;

            if (answer.charAt(0) < '0' || answer.charAt(0) > '9' || answer.charAt(1) != ' ' || answer.charAt(2) < '0' || answer.charAt(2) > '9') {
                System.out.println("You should enter numbers!");
                check = 0;
            } else {
                firstDigit = Integer.parseInt(String.valueOf(answer.charAt(0)));
                secondDigit = Integer.parseInt(String.valueOf(answer.charAt(2)));
                if ((firstDigit < 1 || firstDigit > 3) || (secondDigit < 1 || secondDigit > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    check = 0;
                } else {
                    if (count % 2 == 0) {
                        c = 'X';
                    } else {
                        c = 'O';
                    }
                    if (firstDigit == 1) {
                        if (newMsg.charAt(secondDigit - 1) != ' ') {
                            System.out.println("This cell is occupied! Choose another one!");
                            check = 0;
                        } else {
                            arr[secondDigit - 1] = c;
                            newMsg = new String(arr);
                            count++;
                        }
                    } else if (firstDigit == 2) {
                        if (newMsg.charAt(secondDigit + firstDigit) != ' ') {
                            System.out.println("This cell is occupied! Choose another one!");
                            check = 0;
                        } else {
                            arr[secondDigit + firstDigit] = c;
                            newMsg = new String(arr);
                            count++;
                        }
                    } else {
                        if (newMsg.charAt(secondDigit + firstDigit + 2) != ' ') {
                            System.out.println("This cell is occupied! Choose another one!");
                            check = 0;
                        } else {
                            arr[secondDigit + firstDigit + 2] = c;
                            newMsg = new String(arr);
                            count++;
                        }
                    }
                    System.out.println("----------");
                    for (int i = 0; i < 9; i +=3) {
                        System.out.println("| " + newMsg.charAt(i) + " " + newMsg.charAt(i + 1) + " " + newMsg.charAt(i + 2) + " |");
                    }
                    System.out.println("----------");
                    checkAnalyse = analyze(newMsg);
                }
            }

        } while ((check != 1 || count < 9) && checkAnalyse.equals("Game not finished"));

    }
}
