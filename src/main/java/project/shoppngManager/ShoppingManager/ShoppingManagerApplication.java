package project.shoppngManager.ShoppingManager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.shoppngManager.ShoppingManager.generics.ShoppingManager;
import project.shoppngManager.ShoppingManager.generics.WestMinsterShoppingManager;
import project.shoppngManager.ShoppingManager.models.Clothing;
import project.shoppngManager.ShoppingManager.models.Electronics;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Shopping Manager",
                description = "This application handles basic services for an online store",
                version = "v1"
        ),
        servers = {
                @Server(url = "https://school-project-production.up.railway.app", description = "PROD"),
                @Server(url = "https://school-project-production.up.railway.app", description = "LOCALHOST")
        }
)
public class ShoppingManagerApplication {

    private static final ShoppingManager manager = new WestMinsterShoppingManager();

    public static void main(String[] args) {
        SpringApplication.run(ShoppingManagerApplication.class, args);
		String managerOrClient = input("Hi, welcome to the shopping manager! Are you a manager or a client (CLI or GUI)?\nPlease type 1 for MANAGER, 2 or CLIENT========================>");
		switch (managerOrClient) {
			case "1":
				startCliInterface();
				break;
			case "2":
                print("Application Running!");
				break;
			default:
                print("Please pick a correct input!");
				exitApplication(true);
		}
    }

    private static void startCliInterface() {
        String result = input("""
                Please pick an option:
                1. Please enter 1 to add a product
                2. Please enter 2 to delete a product
                3. Please enter 3 to print products list
                4. Please enter 4 to save to file
                5. Exit application""");
        switch (result) {
            case "1":
                addAProductMenu();
                break;
            case "2":
                deleteAProductMenu();
                break;
            case "3":
                printProductsListMenu();
                break;
            case "4":
                saveToFileMenu();
                break;
            case "5":
                exitApplication(false);
                break;
            default:
                print("An error occurred, please enter the correct input!");
                startCliInterface();
        }
    }

    private static void saveToFileMenu() {
		String response = input("""
                This will save products list and exit the application.
                1. Press 1 to confirm
                2. Press 2 to cancel""");
		switch (response) {
			case "1":
				manager.saveProductsToFile();
				exitApplication(false);
				break;
			case "2":
				print("Canceling...");
				startCliInterface();
				break;
			default:
				print("An error occurred, please enter the correct input!");
				saveToFileMenu();
		}
    }

    private static void printProductsListMenu() {
        manager.printProductList();
        startCliInterface();
    }

    private static void deleteAProductMenu() {
        print("Welcome to delete a product menu!");
        String result = validateStringInput("Please enter the id of the product you want to delete or enter a random value to go back to main menu");
        manager.removeProduct(result);
        startCliInterface();
    }

    private static void addAProductMenu() {
        print("Welcome to add a product menu!");
        String result = input("""
                Please select an option:
                1. Please enter 1 for Electronics
                2. Please enter 2 for Clothing
                3. Please enter 3 to go back to previous menu""");
        switch (result) {
            case "1":
                addElectronicsMenu();
                break;
            case "2":
                addClothingMenu();
                break;
            case "3":
                print("Going back...");
                startCliInterface();
                break;
            default:
                print("An error occurred, please enter the correct input!");
                addAProductMenu();
        }
    }

    private static void addClothingMenu() {
        List<String> existingIds = manager.loadExistingIds();
        String productId = validateStringInput("Enter product ID:");
        if (existingIds.contains(productId)) {
            print("ID exists already, start process again with different ID");
            addClothingMenu();
        }
        String productName = validateStringInput("Enter product name:");
        int availableItems = validateIntegerInput("Enter available items:");
        double price = validateDoubleInput("Enter price:");
        String size = validateStringInput("Enter size:");
        String color = validateStringInput("Enter color:");
        String info = validateStringInput("Enter info about product:");
        Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color, info);
        manager.addProduct(clothing);
        startCliInterface();
    }


    private static void addElectronicsMenu() {
        List<String> existingIds = manager.loadExistingIds();
        String productId = validateStringInput("Enter product ID:");
        if (existingIds.contains(productId)) {
            print("ID exists already, start process again with different ID");
            addElectronicsMenu();
        }
        String productName = validateStringInput("Enter product name:");
        int availableItems = validateIntegerInput("Enter number of available items:");
        double price = validateDoubleInput("Enter cost:");
		String brand = validateStringInput("Enter brand:");
		String warrantyPeriod = validateStringInput("Enter warranty period (in numbers):");
        String info = validateStringInput("Enter info about product:");
		Electronics electronics = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod, info);
		manager.addProduct(electronics);
		startCliInterface();
    }

    private static void exitApplication(boolean error) {
        if (!error) {
            print("Saving products...");
            manager.saveProductsToFile();
            print("Thanks for using our application");
        }
        System.exit(0);
    }

    private static String input(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private static void print(String output) {
        System.out.println(output);
    }

    private static int validateIntegerInput(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(input(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double validateDoubleInput(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(input(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static String validateStringInput(String prompt) {
        String input;
        do {
            input = input(prompt);
            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.trim().isEmpty());
        return input;
    }

}
