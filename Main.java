package chucknorris;

import java.util.Scanner;

public class Main {
    private final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String answer;

        do {
            System.out.println("Please input operation (encode/decode/exit):");
            answer = input.nextLine();

            switch (answer) {
                case "encode" -> convertString();
                case "decode" -> toBinary();
                case "exit" -> System.out.println("Bye!");
                default -> System.out.println("There is no '" + answer + "' operation\n");
            }
        } while (!answer.equals("exit"));
    }

    private static void convertString() {
        String answer;
        char[] charArray;
        StringBuilder finalBit = new StringBuilder();

        System.out.println("Input string:");
        answer = input.nextLine();

        charArray = new char[answer.length()];

        System.out.println("Encoded string:");

        for (int i = 0;  i < charArray.length; i++) {
            charArray[i] = answer.charAt(i);
            String bit = Integer.toBinaryString(charArray[i]);
            if (bit.length() < 7) {
                bit = "0" + bit;
            }
            finalBit.append(bit);
        }

        String result = convertBinary(finalBit.toString());
        System.out.println(result);
        System.out.println();

    }

    private static String convertBinary(String finalBit) {
        StringBuilder result = new StringBuilder();
        String[] temp;
        temp = finalBit.split("");
        for (int i = 0; i < temp.length - 1 ; i++) {
            if (temp[i].startsWith(temp[i + 1]) && !temp[i + 1].equals("")) {
                temp[i] += temp[i + 1];
                temp[i + 1] = "";
                deleteAtPosition(i + 1, temp);
                i--;

            }
        }

        for (String string : temp) {
            if (string.startsWith("1")) {
                result.append("0 ");
                result.append("0".repeat(string.length()));
                result.append(" ");
            } else if (string.startsWith("0")) {
                result.append("00 ");
                result.append("0".repeat(string.length()));
                result.append(" ");
            }
        }

        return String.valueOf(result);
    }

    private static void deleteAtPosition(int position, String[] arr) {
        for (int i = position; i < arr.length - 1; i++) {
            String temp = arr[i + 1];
            arr[i + 1] = arr[i];
            arr[i] = temp;
        }
    }

    private static void toBinary() {
        String[] temp;
        StringBuilder result = new StringBuilder();

        System.out.println("Input encoded string:");
        String answer = input.nextLine();

        temp = answer.split(" ");

        for (int i = 0; i < temp.length; i++) {
            if (temp.length % 2 != 0) {
                System.out.println("Encoded string is not valid.\n");
                return;
            }
            if (!temp[i].startsWith("0")) {
                System.out.println("Encoded string is not valid.\n");
                return;
            }
            if (i % 2 == 0) {
                if (!temp[i].equals("0") && !temp[i].equals("00")) {
                    System.out.println("Encoded string is not valid.\n");
                    return;
                }
            }
        }

        for (int i = 0; i < temp.length; i+=2) {
            if (temp[i].equals("0")) {
                result.append("1".repeat(temp[i + 1].length()));
            } else {
                result.append("0".repeat(temp[i + 1].length()));
            }
        }

        if (result.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.\n");
            return;
        }

        convertToString(String.valueOf(result));
    }

    private static void convertToString(String binary) {
        String[] block = new String[binary.length() / 7];
        char[] result = new char[block.length];

        for (int i = 0, j = 0; i < binary.length() && j < block.length; i+=7, j++) {
            int k = i + 7;
            block[j] = binary.substring(i, k);
        }

        for (int i = 0; i < block.length; i++) {
            for (int j = 6; j >= 0; j--) {
                int k = (j - 6) * -1;
                if (block[i].charAt(k) == '1') {
                    result[i] += Math.pow(2, j);
                }
            }
        }

        System.out.println("Decoded string:");
        for (char c : result) {
            System.out.print(c + "");
        }
        System.out.println();
        System.out.println();
    }
}
