package bankApp.Services;

import java.util.Scanner;

import static bankApp.Services.LogIn.login;
import static bankApp.Services.Register.register;


public class StartMenu {

    public static void loadStartMenu() {
        System.out.print("\n\u001B[7m\033[1;33m Bank Application Project                              \033[0m\n");
        System.out.println("[\033[1;33mL\u001B[0m] Login");
        System.out.println("[\033[1;33mR\u001B[0m] Register\n");
        System.out.println("[\033[1;33mE\u001B[0m] Exit\n");
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Choice: ");
            String choice = input.nextLine();
            choice = choice.toUpperCase();
            if (choice.equals("L")) {
                login();
                break;
            } else if (choice.equals("R")) {
                register();
                break;
            } else if (choice.equals("E")) {
                System.exit(0);
            }
        }
    }


}
