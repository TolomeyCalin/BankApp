package bankApp.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class StartMenu {
    private static boolean blaBla = true;
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static void loadStartMenu() throws IOException {

        System.out.print("\n\u001B[7m\033[1;30m      Welcome To SDABank        \033[0m\n\n");
        System.out.println("[\033[1;31mL\u001B[0m] Login\n");
        System.out.println("[\033[1;32mR\u001B[0m] Register\n");
        System.out.println("[\033[1;34mE\u001B[0m] Exit\n");
        while (blaBla) {
            System.out.print("Choice: ");
            String choice = input.readLine();
            choice = choice.toUpperCase();
            if (choice.equals("L")) {
                Login login = new Login();
                login.loginCheck();
                LogInMenu logInMenu = new LogInMenu();
                logInMenu.showMenu(login.customer);

            } else if (choice.equals("R")) {
                AppMenu register = new AppMenu();
                register.register();

            } else if (choice.equals("E")) {
                System.exit(0);
                blaBla = false;
            }
        }
    }


}




