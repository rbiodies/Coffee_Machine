package machine;

import java.util.Scanner;

enum CoffeeMachineStatus {
    ACTION, COFFEE, WATER, MILK, BEANS, CUPS
}

class InputString {

    static void inputString(String str) {
        switch (CoffeeMachine.state) {
            case ACTION:
                CoffeeMachine.chooseAction(str);
                break;
            case COFFEE:
                CoffeeMachine.processBuy(str);
                break;
            case WATER:
                if (checkInteger(str) == 1)
                    break;
                CoffeeMachine.water += Integer.parseInt(str);
                break;
            case MILK:
                if (checkInteger(str) == 1)
                    break;
                CoffeeMachine.milk += Integer.parseInt(str);
                break;
            case BEANS:
                if (checkInteger(str) == 1)
                    break;
                CoffeeMachine.beans += Integer.parseInt(str);
                break;
            case CUPS:
                if (checkInteger(str) == 1)
                    break;
                CoffeeMachine.cups += Integer.parseInt(str);
                break;
        }
    }

    static int  checkInteger(String str) {
        if (str == null) {
            throw new NullPointerException("str == null");
        }
        else if (str.equals("exit")) {
            System.exit(0);
        }
        else if (!str.matches("\\d++")) {
            System.out.println("\nError: Input value has not integer type!\n");
            return 1;
        }
        return 0;
    }
}

public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);
    static CoffeeMachineStatus state;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    public static void main(String[] args) {
        while (true) {
            state = CoffeeMachineStatus.ACTION;
            System.out.println("\nWrite action (remaining, buy, fill, take, exit):");
            System.out.print("> ");
            InputString.inputString(scanner.nextLine());
        }
    }

    static void chooseAction(String str) {
        if (str == null) {
            throw new NullPointerException("str == null");
        }
        System.out.print("\n");
        switch (str) {
            case "remaining":
                printState();
                break;
            case "buy":
                state = CoffeeMachineStatus.COFFEE;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                System.out.print("> ");
                InputString.inputString(scanner.nextLine());
                break;
            case "fill":
                processFill();
                break;
            case "take":
                processTake();
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Error: No this action!");
                break;
        }
    }

    static void printState() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans\n", beans);
        System.out.printf("%d disposable cups\n", cups);
        System.out.printf("$%d of money\n", money);
    }

    static void processBuy(String str) {
        if (str == null) {
            throw new NullPointerException("str == null");
        } else if (str.equals("back")) {
            return;
        } else if (InputString.checkInteger(str) == 1) {
            return;
        }

        int variety = Integer.parseInt(str);

        if (variety == 1) {
            if (checkResources(250, 0, 16) == 1) {
                return;
            }
            water -= 250;
            beans -= 16;
            cups--;
            money += 4;
        } else if (variety == 2) {
            if (checkResources(350, 75, 20) == 1) {
                return;
            }
            water -= 350;
            milk -= 75;
            beans -= 20;
            cups--;
            money += 7;
        } else if (variety == 3) {
            if (checkResources(200, 100, 12) == 1) {
                return;
            }
            water -= 200;
            milk -= 100;
            beans -= 12;
            cups--;
            money += 6;
        } else {
            System.out.println("\nError: No this value!\n");
        }
    }

    static int checkResources(int w, int m, int b) {
        if (water < w) {
            System.out.println("Sorry, not enough water!");
            return 1;
        } else if (milk < m) {
            System.out.println("Sorry, not enough milk!");
            return 1;
        } else if (beans < b) {
            System.out.println("Sorry, not enough beans!");
            return 1;
        } else if (cups == 0) {
            System.out.println("Sorry, not enough cups!");
            return 1;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return 0;
        }
    }

    static void processFill() {
        state = CoffeeMachineStatus.WATER;
        System.out.println("Write how many ml of water you want to add:");
        System.out.print("> ");
        InputString.inputString(scanner.nextLine());
        state = CoffeeMachineStatus.MILK;
        System.out.println("Write how many ml of milk you want to add:");
        System.out.print("> ");
        InputString.inputString(scanner.nextLine());
        state = CoffeeMachineStatus.BEANS;
        System.out.println("Write how many grams of coffee beans you want to add:");
        System.out.print("> ");
        InputString.inputString(scanner.nextLine());
        state = CoffeeMachineStatus.CUPS;
        System.out.println("Write how many disposable cups of coffee you want to add:");
        System.out.print("> ");
        InputString.inputString(scanner.nextLine());
    }

    static void processTake() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }
}
