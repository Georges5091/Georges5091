import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    static int water = 400;
    static int milk = 540;
    static int coffee = 120;
    static int numCupsPossible = 9;
    static int money = 550;
    static int exit = 1;

    public static void main(String[] args) {
        while (exit != 0) {
            action();
        }
    }

    static void print() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffee + " g of coffee beans");
        System.out.println(numCupsPossible + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    static void action() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String answer = scanner.next();
        switch (answer) {
            case "buy" -> {
                try {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    int choice = scanner.nextInt();
                    processBuy(choice);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                }
            }
            case "fill" -> processFill();
            case "take" -> processTake();
            case "remaining" -> print();
            case "exit" -> exit = 0;
        }
    }

    static void processBuy(int choice) {
        if (choice == 1) {
            if(water < 250) {
                System.out.println("Sorry, not enough water!");
            } else if (coffee < 16) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (numCupsPossible == 0) {
                System.out.println("Sorry, not enough disposable cups!");
            } else {
                System.out.println("I have enough resources, making your coffee!");
                water -= 250;
                coffee -= 16;
                numCupsPossible--;
                money += 4;
            }
        } else if (choice == 2) {
            if(water < 350) {
                System.out.println("Sorry, not enough water!");
            } else if (milk < 75) {
                System.out.println("Sorry, not enough milk!");
            } else if (coffee < 20) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (numCupsPossible == 0) {
                System.out.println("Sorry, not enough disposable cups!");
            } else {
                System.out.println("I have enough resources, making your coffee!");
                water -= 350;
                milk -= 75;
                coffee -=20;
                numCupsPossible--;
                money += 7;
            }
        } else if (choice == 3) {
            if(water < 200) {
                System.out.println("Sorry, not enough water!");
            } else if (milk < 100) {
                System.out.println("Sorry, not enough milk!");
            } else if (coffee < 12) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (numCupsPossible == 0) {
                System.out.println("Sorry, not enough disposable cups!");
            } else {
                System.out.println("I have enough resources, making your coffee!");
                water -= 200;
                milk -= 100;
                coffee -= 12;
                numCupsPossible--;
                money += 6;
            }
        }
    }

    static void processFill() {
        System.out.println("Write how many mn of water you want to add:");
        int filledWater = scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        int filledMilk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        int filledCoffee = scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        int cups = scanner.nextInt();

        water += filledWater;
        milk += filledMilk;
        coffee += filledCoffee;
        numCupsPossible += cups;
    }

    static void processTake() {
        System.out.println("I gave you $" + money);
        money = 0;
    }
}
