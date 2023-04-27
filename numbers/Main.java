package numbers;

import java.util.Scanner;

public class Main {
    final private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        displayMenu();
        ask();
    }

    //Welcome users and display instructions
    public static void displayMenu() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive number are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers");
        System.out.println("- separate the parameters with one space");
        System.out.println("- enter 0 to exit.");
    }

    //Ask for a request and handle answer
    public static void ask() {
        final String[] properties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "EVEN", "ODD", "HAPPY", "SAD"};
        String[] chosenProperties = new String[0];
        String answer;
        String[] numbers;
        long number = 1;
        int length;
        int arrLength;
        int propLength;

        do {
            length = 1;
            System.out.print("\nEnter a request: ");
            answer = input.nextLine();
            System.out.println();

            if (!answer.equals("")) {
                numbers = answer.split(" ");
                arrLength = numbers.length;
                propLength = arrLength - 2;

                if (checkAnswer(numbers[0])) {
                    number = Long.parseLong(numbers[0]);
                } else {
                    number = -1;
                }

                if (numbers.length >= 2) {
                    if (checkAnswer(numbers[1])) {
                        length = Integer.parseInt(numbers[1]);
                    } else {
                        length = 0;
                    }
                }

                if (propLength > 0) {
                    chosenProperties = new String[propLength];
                    for (int i = 0; i < chosenProperties.length; i++) {
                        chosenProperties[i] = numbers[i + 2].toUpperCase();
                    }
                }

                /*If a user enters zero, terminate the program
                If numbers are not natural, print the error message
                If an incorrect property is specified, print the error message
                and the list of available properties
                If the user specifies mutually exclusive properties, abort the request and warn the user*/
                if (number == 0 ) {
                    System.out.println("Goodbye!");
                } else if (number < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (length < 1) {
                    System.out.println("The second parameter should be a natural number.");
                } else if (numbers.length > 2 && !checkProperties(chosenProperties, properties)) {
                    printProps(properties);
                } else if (numbers.length > 3 && !checkMutuallyExclusiveProps(chosenProperties)) {
                    System.out.println("There are no numbers with these properties.");
                }
                else {
                    if (arrLength == 1) { //for one number print the properties of the number
                        printOneNumber(number);
                    } else if (arrLength == 2) { //for two numbers, print the list of numbers with their properties
                        printListOfNumbers(number, length);
                    } else {
                        sortByProperties(chosenProperties, number, length);
                    }
                }
            }
        } while (number != 0);
    }

    public static boolean checkParity(long number) {
        return number % 2 == 0;
    }

    public static boolean isDivisibleBySeven(long number) {
        long result;
        long remainder = number % 10;

        remainder *= 2;

        number /= 10;

        result = number - remainder;

        return result % 7 == 0;
    }

    public static boolean isLastDigitSeven(long number) {
        return number % 10 == 7;
    }

    public static boolean checkBuzz(long number) {
        return isDivisibleBySeven(number) || isLastDigitSeven(number);
    }

    public static boolean checkDuck(long number) {
        String strNumber = Long.toString(number);

        for (int i = 1; i < strNumber.length(); i++) {
            if (strNumber.charAt(i) == '0') {
                return true;
            }
        }

        return false;
    }

    public static boolean checkPalindromic(long number) {
        StringBuilder newNumber = new StringBuilder();
        String strNumber = Long.toString(number);
        for (int i = strNumber.length() - 1; i >= 0; i--) {
            newNumber.append(strNumber.charAt(i));
        }

        return strNumber.equals(newNumber.toString());
    }

    public static boolean checkGapful(long number) {
        String strNumber = Long.toString(number);

        if (strNumber.length() < 3){
            return false;
        }

        String strDivisor = strNumber.charAt(0) + Character.toString(strNumber.charAt(strNumber.length() - 1));
        int divisor = Integer.parseInt(strDivisor);
        return number % divisor == 0;
    }

    public static boolean checkSpy(long number) {
        String strNumber = Long.toString(number);
        String[] digits = strNumber.split("");
        int sum = 0;
        int product = 1;

        for (String digit : digits) {
            sum += Integer.parseInt(digit);
            product *= Integer.parseInt(digit);
        }

        return sum == product;
    }

    public static boolean checkSquare(long number) {
        return number == (long) Math.sqrt(number) * (long) Math.sqrt(number);
    }

    public static boolean checkSunny(long number) {
        return checkSquare(number + 1);
    }

    public static boolean checkJumping(long number) {
        String str = Long.toString(number);
        int length = str.length();

        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = (int)number % 10;
            number /= 10;
        }

        for (int i = 0; i <  length - 1; i++) {
            int sub = arr[i] - arr[i + 1];
            if (sub != 1 && sub != - 1) {
                return false;
            }
        }

        return true;
    }

    public static int findSumDigitSquare(long n) {
        int sum = 0;
        long lastDigit;
        while (n > 0) {
            lastDigit = n % 10;
            sum += lastDigit * lastDigit;
            n /= 10;
        }
        return sum;
    }

    public static boolean checkHappy(long n) {
        if (n <= 0) {
            return false;
        }
        while (!(n == 1 || n == 4)) {
            n = findSumDigitSquare(n);
        }

        return n == 1;
    }

    public static boolean checkAnswer(String answer) {
        for (int i = 0; i < answer.length(); i++) {
            char index = answer.charAt(i);
            if (index >= 48 && index <= 57) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkProperty(String[] properties, String chosenProperty) {
        int length = chosenProperty.length();

        for (String property : properties) {
            if (chosenProperty.charAt(0) == '-') {
                if (property.equals(chosenProperty.substring(1, length))) {
                    return true;
                }
            } else if (property.equals(chosenProperty)) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkProperties(String[] chosenProperties, String[] properties) {
        boolean check = true;
        int numErr = 0;
        StringBuilder msg = new StringBuilder("The property ");
        for (String property : chosenProperties) {
            if (!checkProperty(properties, property)) {
                msg.append(property).append(" ");
                check = false;
                numErr++;
            }
        }

        if (numErr > 0) {
            if (numErr > 1) {
                msg.replace(11, 12, "ies");
                System.out.println(msg + "are wrong");
            } else {
                System.out.println(msg + "is wrong");
            }
        }

        return check;
    }

    public static boolean mutuallyExclusiveProps(String property1, String property2) {
        switch (property1) {
            case "EVEN": case "-ODD":
                if (property2.equals("ODD") || property2.equals("-EVEN")) {
                    return true;
                }
                break;
            case "ODD": case "-EVEN":
                if (property2.equals("EVEN") || property2.equals("-ODD")) {
                    return true;
                }
                break;
            case "DUCK": case "-SPY":
                if (property2.equals("SPY") || property2.equals("-DUCK")) {
                    return true;
                }
                break;
            case "SPY": case "-DUCK":
                if (property2.equals("DUCK") || property2.equals("-SPY")) {
                    return true;
                }
                break;
            case "SUNNY": case "-SQUARE":
                if (property2.equals("SQUARE") || property2.equals("-SUNNY")) {
                    return true;
                }
                break;
            case "SQUARE": case "-SUNNY":
                if (property2.equals("SUNNY") || property2.equals("-SQUARE")) {
                    return true;
                }
                break;
            case "SAD": case "-HAPPY":
                if (property2.equals("HAPPY") || property2.equals("-SAD")) {
                    return true;
                }
                break;
            case "HAPPY": case "-SAD":
                if (property2.equals("SAD") || property2.equals("-HAPPY")) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static boolean checkMutuallyExclusiveProps(String[] properties) {
        for (int i = 0; i < properties.length - 1; i++) {
            for (int j = i + 1; j < properties.length; j++) {
                if (mutuallyExclusiveProps(properties[i], properties[j])) {
                    System.out.println("The request contains mutually exclusive properties: " + properties[i] + " " + properties[j]);
                    return false;
                }
            }
        }
        return true;
    }

    public static void printProps(String[] properties) {
        System.out.print("Available properties: ");
        for (String property : properties) {
            System.out.print(property + " ");
        }
    }

    public static void printOneNumber(long number) {
        System.out.println("Properties of " + number);
        System.out.println("\t\tbuzz: " + checkBuzz(number));
        System.out.println("\t\tduck: " + checkDuck(number));
        System.out.println(" palindromic: " + checkPalindromic(number));
        System.out.println("\t  gapful: " + checkGapful(number));
        System.out.println("\t\t spy: " + checkSpy(number));
        System.out.println("\t  square: " + checkSquare(number));
        System.out.println("\t   sunny: " + checkSunny(number));
        System.out.println("\t jumping: " + checkJumping(number));
        System.out.println("\t\teven: " + checkParity(number));
        System.out.println("\t\t odd: " + !checkParity(number));
        System.out.println("\t   happy: " + checkHappy(number));
        System.out.println("\t\t sad: " + !checkHappy(number));
    }

    public static void printListOfNumbers(long number, int length) {
        for (int i = 0; i < length; i++) {
            StringBuilder result = new StringBuilder(number + " is ");

            if (Long.toString(number).length() == 1) {
                result = new StringBuilder(" " + number + " is ");
            }

            if (checkBuzz(number)) {
                result.append("buzz, ");
            }
            if (checkDuck(number)) {
                result.append("duck, ");
            }
            if (checkPalindromic(number)) {
                result.append("palindromic, ");
            }
            if (checkGapful(number)) {
                result.append("gapful, ");
            }
            if (checkSpy(number)) {
                result.append("spy, ");
            }
            if (checkSquare(number)) {
                result.append("square, ");
            }
            if (checkSunny(number)) {
                result.append("sunny, ");
            }
            if (checkJumping(number)) {
                result.append("jumping, ");
            }
            if (checkParity(number)) {
                result.append("even, ");
            } else {
                result.append("odd, ");
            }
            if (checkHappy(number)) {
                result.append("happy");
            } else {
                result.append("sad");
            }

            System.out.println(result);

            number++;
        }
    }

    public static void sortByProperties(String[] properties, long number, int length) {
        int count = 0;
        while (count < length) {
            StringBuilder result = new StringBuilder(number + " is ");

            if (Long.toString(number).length() == 1) {
                result = new StringBuilder(" " + number + " is ");
            }

            if (checkBuzz(number)) {
                result.append("buzz, ");
            }
            if (checkDuck(number)) {
                result.append("duck, ");
            }
            if (checkPalindromic(number)) {
                result.append("palindromic, ");
            }
            if (checkGapful(number)) {
                result.append("gapful, ");
            }
            if (checkSpy(number)) {
                result.append("spy, ");
            }
            if (checkSquare(number)) {
                result.append("square, ");
            }
            if (checkSunny(number)) {
                result.append("sunny, ");
            }
            if (checkJumping(number)) {
                result.append("jumping, ");
            }
            if (checkParity(number)) {
                result.append("even, ");
            } else {
                result.append("odd, ");
            }
            if (checkHappy(number)) {
                result.append("happy");
            } else {
                result.append("sad");
            }

            if (checkResult(String.valueOf(result), properties)) {
                System.out.println(result);
                count++;
            }

            number++;
        }
    }

    public static boolean checkResult(String result, String[] properties) {
        for (String property : properties) {
            if (property.charAt(0) == '-') {
                if (result.contains(property.substring(1).toLowerCase())) {
                    return false;
                }
            } else if (!result.contains(property.toLowerCase())) {
                return false;
            }
        }

        return true;
    }
}