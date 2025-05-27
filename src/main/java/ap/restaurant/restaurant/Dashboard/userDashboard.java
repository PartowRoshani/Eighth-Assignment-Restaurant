package ap.restaurant.restaurant.Dashboard;

import ap.restaurant.restaurant.Controller.userController;
import ap.restaurant.restaurant.Models.menuItems;
import ap.restaurant.restaurant.Models.orderDetails;
import ap.restaurant.restaurant.Models.orders;
import ap.restaurant.restaurant.Models.user;
import ap.restaurant.restaurant.Services.OrderService;
import ap.restaurant.restaurant.dataBase.UserDatabase;
import ap.restaurant.restaurant.dataBase.menuItemDetabase;
import ap.restaurant.restaurant.security.PasswordHashing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class userDashboard {
    public static userController controller = new userController();

    public static void main(String [] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 :
                    System.out.println("Username: ");
                    String username = scanner.nextLine();
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    boolean login = controller.login(username,password);
                    if(login){
                        userMenu(username);
                    }
                    break;
                case 2 :
                    System.out.println("Username: ");
                    String usernameRegister = scanner.nextLine();
                    System.out.println("Password: ");
                    String passwordRegister = scanner.nextLine();
                    System.out.println("Email(optional): ");
                    String email = scanner.nextLine();
                    boolean Register = controller.registerCtrl(usernameRegister,passwordRegister,email);

                    if(Register){
                        System.out.println("SUCCESSFUL");
                        userMenu(usernameRegister);
                    }
                    break;

                case 3:
                    System.out.println("Goodbye...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }



    }
    public static void userMenu(String username) throws SQLException {
        user target = UserDatabase.getUserByUsername(username);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. View Menu Items");
            System.out.println("2. Place Order");
            System.out.println("3. View My Orders");
            System.out.println("4. Update Profile");
            System.out.println("5. Change Password");
            System.out.println("6. Delete Account");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try {
                        List<menuItems> menu = menuItemDetabase.getAllMenuItems();
                        System.out.println("-------- FULL MENU --------");
                        for (menuItems item : menu) {
                            System.out.println("Name: " + item.getName());
                            System.out.println("Category: " + item.getCategory());
                            System.out.println("Price: " + item.getPrice());
                            System.out.println("Description: " + item.getDescription());
                            System.out.println("-----------------------------");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error fetching menu: " + e.getMessage());
                    }                    break;
                case 2:
                    user User= UserDatabase.getUserByUsername(username);
                    int user_id = User.getUser_id();
                    //order(user_id);
                    break;
                case 3:
                    OrderService.AllOrdersForUser(target.getUser_id());
                    break;
                case 4:
                    user U = UserDatabase.getUserByUsername(username);
                    System.out.println("New username: ");
                    String newUsername = scanner.nextLine();
                    System.out.println("New email:");
                    String newEmail = scanner.nextLine();
                    controller.UpdateProfile(username, newUsername, newEmail,U.getPassword());
                    username = newUsername;
                    break;
                case 5:
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    String passwordRegex = "\\b(?=[^\\s]*[A-Z])(?=[^\\s]*[a-z])(?=[^\\s]*\\d)(?=[^\\s]*[!@#$%^&*])[^\\s]{8,}\\b";
                    while (!newPassword.matches(passwordRegex)){
                        System.out.println("Invalid please try again: ");
                         newPassword = scanner.nextLine();
                    }
                    controller.changePassword(newPassword , username);


                    break;
                case 6:
                    controller.deleteAccount(target);
                    return;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }

    }
}


