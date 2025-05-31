package ap.restaurant.restaurant.UI;

import ap.restaurant.restaurant.Dashboard.userDashboard;
import ap.restaurant.restaurant.Models.menuItems;
import ap.restaurant.restaurant.Models.orderDetails;
import ap.restaurant.restaurant.Models.orders;
import ap.restaurant.restaurant.Models.user;
import ap.restaurant.restaurant.Services.OrderService;
import ap.restaurant.restaurant.dataBase.OrderDetailsDatabase;
import ap.restaurant.restaurant.dataBase.UserDatabase;
import ap.restaurant.restaurant.dataBase.menuItemDetabase;
import ap.restaurant.restaurant.security.PasswordHashing;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static ap.restaurant.restaurant.Dashboard.userDashboard.controller;

public class RestaurantApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");


        Button btnLogin = new Button("Login");
        Button btnRegister = new Button("Register");
        Button btnExit = new Button("Exit");

        HBox buttonsBox = new HBox(10, btnLogin, btnRegister, btnExit);

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(buttonsBox);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        btnRegister.setOnAction(e -> showRegisterForm(primaryStage));



        btnLogin.setOnAction(e -> showLoginForm(primaryStage));
        btnExit.setOnAction(e -> primaryStage.close());
    }


    private void showRegisterForm(Stage primaryStage) {
        Label lblUsername = new Label("Username:");
        TextField tfUsername = new TextField();

        Label lblPassword = new Label("Password:");
        PasswordField pfPassword = new PasswordField();

        Label lblConfirmPassword = new Label("Confirm Password:");
        Label lblEmail = new Label("Email (optional):");
        TextField tfEmail = new TextField();
        PasswordField pfConfirmPassword = new PasswordField();

        Button btnSubmit = new Button("Submit");
        Button btnBack = new Button("Back");

        GridPane registerGrid = new GridPane();
        registerGrid.setPadding(new Insets(20));
        registerGrid.setVgap(10);
        registerGrid.setHgap(10);

        registerGrid.add(lblUsername, 0, 0);
        registerGrid.add(tfUsername, 1, 0);
        registerGrid.add(lblPassword, 0, 1);
        registerGrid.add(pfPassword, 1, 1);
        registerGrid.add(lblConfirmPassword, 0, 2);
        registerGrid.add(pfConfirmPassword, 1, 2);
        registerGrid.add(lblEmail,0,3);
        registerGrid.add(tfEmail, 1,3);
        registerGrid.add(btnSubmit, 0, 4);
        registerGrid.add(btnBack, 1, 4);

        Scene registerScene = new Scene(registerGrid, 400, 250);

        primaryStage.setScene(registerScene);


        btnBack.setOnAction(e -> start(primaryStage));

        btnSubmit.setOnAction(e -> {
            String username = tfUsername.getText();
            String password = pfPassword.getText();
            String confirmPassword = pfConfirmPassword.getText();
            String email = tfEmail.getText();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
                return;
            }



                    boolean Register = controller.registerCtrl(username, password, email);

                    if (Register) {
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful! You can now login.");
                            showDashboard(primaryStage, username);
                    }
                    else {
                        showAlert(Alert.AlertType.INFORMATION, "Error", "Registration  not successful! try again.");
                    }
        });
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showLoginForm(Stage primaryStage) {
        Label lblUsername = new Label("Username:");
        TextField tfUsername = new TextField();

        Label lblPassword = new Label("Password:");
        PasswordField pfPassword = new PasswordField();

        Button btnSubmit = new Button("Login");
        Button btnBack = new Button("Back");

        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(20));
        loginGrid.setVgap(10);
        loginGrid.setHgap(10);

        loginGrid.add(lblUsername, 0, 0);
        loginGrid.add(tfUsername, 1, 0);
        loginGrid.add(lblPassword, 0, 1);
        loginGrid.add(pfPassword, 1, 1);
        loginGrid.add(btnSubmit, 0, 2);
        loginGrid.add(btnBack, 1, 2);

        Scene loginScene = new Scene(loginGrid, 350, 200);

        primaryStage.setScene(loginScene);

        btnBack.setOnAction(e -> start(primaryStage));

        btnSubmit.setOnAction(e -> {
            String username = tfUsername.getText();
            String password = pfPassword.getText();

            boolean login = controller.login(username, password);
            if (login) {
                showDashboard(primaryStage, username);
            }
             else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });
    }




    private void showDashboard(Stage primaryStage, String username) {


        Button btnShowMenu = new Button("View Menu Items");
        Button btnPlaceOrder = new Button("Place Order");
        Button btnViewOrders = new Button("View My Orders");
        Button btnUpdateProfile = new Button("Update Profile");
        Button btnChangePassword = new Button("Change Password");
        Button btnDeleteAccount = new Button("Delete Account");
        Button btnExit = new Button("Logout");
        btnShowMenu.setOnAction(e -> {
            try {
                List<menuItems> menu = menuItemDetabase.getAllMenuItems();

                VBox menuLayout = new VBox(10);
                menuLayout.setStyle("-fx-padding: 20;");

                for (menuItems item : menu) {
                    String text = item.getItem_id() + ". " + item.getName() + " - $" + item.getPrice();
                    menuLayout.getChildren().add(new javafx.scene.control.Label(text));
                    String D ="Description: " +item.getDescription();
                    String C = "Category: " + item.getCategory();
                    menuLayout.getChildren().add(new javafx.scene.control.Label((D)));
                    menuLayout.getChildren().add(new javafx.scene.control.Label(C));

                }

                ScrollPane scrollPane = new ScrollPane(menuLayout);
                scrollPane.setPrefSize(300, 400);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

                Scene menuScene = new Scene(scrollPane);
                Stage menuStage = new Stage();
                menuStage.setTitle("Menu");
                menuStage.setScene(menuScene);
                menuStage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnPlaceOrder.setOnAction(e -> {
            try {
                List<menuItems> menu = menuItemDetabase.getAllMenuItems();
                GridPane grid = new GridPane();
                grid.setVgap(10);
                grid.setHgap(10);
                grid.setStyle("-fx-padding: 20;");

                List<orderDetails> orderDetailsList = new ArrayList<>();

                for (int i = 0; i < menu.size(); i++) {
                    menuItems item = menu.get(i);

                    Label nameLabel = new Label(item.getName());
                    Label priceLabel = new Label("$" + item.getPrice());
                    TextField quantityField = new TextField();
                    quantityField.setPromptText("Qty");

                    grid.add(nameLabel, 0, i);
                    grid.add(priceLabel, 1, i);
                    grid.add(quantityField, 2, i);

                    // Store quantity field to process later
                    quantityField.textProperty().addListener((obs, oldVal, newVal) -> {
                        try {
                            int quantity = Integer.parseInt(newVal);
                            if (quantity > 0) {
                                orderDetailsList.removeIf(d -> d.getMenu_item_id() == item.getItem_id());

                                orderDetails detail = new orderDetails();
                                detail.setMenu_item_id(item.getItem_id());
                                detail.setQuantity(quantity);
                                orderDetailsList.add(detail);
                            }
                        } catch (NumberFormatException ignored) {
                            // Invalid number typed, ignore
                        }
                    });
                }

                Button btnSubmitOrder = new Button("Place Order");
                btnSubmitOrder.setOnAction(ev -> {
                    if (orderDetailsList.isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, "No items selected", "Please select at least one item.");
                        return;
                    }

                    orders order = new orders(); // You can set properties like date/time if needed
                    try {
                        user current = UserDatabase.getUserByUsername(username);

                        int currentUserId = current.getUser_id();
                        OrderService.placeOrder(order, currentUserId, orderDetailsList);
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Order placed successfully!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Error", "Could not place order.");
                    }
                });

                VBox layout = new VBox(10, grid, btnSubmitOrder);
                layout.setStyle("-fx-padding: 20;");
                ScrollPane scrollPane = new ScrollPane(layout);

                Scene orderScene = new Scene(scrollPane, 400, 500);
                Stage orderStage = new Stage();
                orderStage.setScene(orderScene);
                orderStage.setTitle("Place Order");
                orderStage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        btnViewOrders.setOnAction(ev -> {

            user currentUser = null;
            try {
                currentUser = UserDatabase.getUserByUsername(username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                List<orders> orderList = OrderService.AllOrdersForUser(currentUser.getUser_id());

                VBox orderLayout = new VBox(10);
                orderLayout.setPadding(new Insets(20));

                if (orderList.isEmpty()) {
                    orderLayout.getChildren().add(new Label("No orders found."));
                } else {
                    for (orders order : orderList) {
                        VBox orderBox = new VBox(5);
                        orderBox.setStyle("-fx-border-color: gray; -fx-padding: 10;");

                        Label orderIdLabel = new Label("Order ID: " + order.getOrder_id());
                        Label dateLabel = new Label("Created At: " + order.getCreated_at());
                        Label totalLabel = new Label("Total Price: $" + order.getTotal_price());

                        Button showDetailsBtn = new Button("Show Details");
                        showDetailsBtn.setOnAction(event -> {
                            try {
                                List<orderDetails> details = OrderDetailsDatabase.getDetailsByOrderId(order.getOrder_id());

                                if (details.isEmpty()) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No details found for this order.");
                                    alert.show();
                                    return;
                                }

                                VBox detailBox = new VBox(10);
                                detailBox.setPadding(new Insets(15));
                                detailBox.getChildren().add(new Label("Details for Order #" + order.getOrder_id()));

                                for (orderDetails detail : details) {
                                    menuItems item = menuItemDetabase.getMenuItemById(detail.getMenu_item_id());
                                    if (item != null) {
                                        String itemInfo = item.getName() + " (x" + detail.getQuantity() + ") - $" + detail.getPrice();
                                        detailBox.getChildren().add(new Label(itemInfo));
                                    } else {
                                        detailBox.getChildren().add(new Label("Menu item not found (ID: " + detail.getMenu_item_id() + ")"));
                                    }
                                }

                                ScrollPane scrollPane = new ScrollPane(detailBox);
                                scrollPane.setPrefSize(300, 400);
                                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


                                Stage detailStage = new Stage();
                                detailStage.setTitle("Order Details");
                                detailStage.setScene(new Scene(scrollPane));
                                detailStage.show();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }


                        });

                        orderBox.getChildren().addAll(orderIdLabel, dateLabel, totalLabel, showDetailsBtn);
                        orderLayout.getChildren().add(orderBox);
                    }
                }

                ScrollPane scrollPane = new ScrollPane(orderLayout);
                scrollPane.setPrefSize(300, 400);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                Scene orderScene = new Scene(scrollPane);
                Stage orderStage = new Stage();
                orderStage.setTitle("My Orders");
                orderStage.setScene(orderScene);
                orderStage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        AtomicReference<String> currentUsername = new AtomicReference<>(username);

        btnUpdateProfile.setOnAction(e -> {
            user U = null;
            try {
                U = UserDatabase.getUserByUsername(currentUsername.get());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (U == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not found!");
                alert.show();
                return;
            }

            Label lblNewUsername = new Label("New Username:");
            TextField tfNewUsername = new TextField(U.getUsername());

            Label lblNewEmail = new Label("New Email:");
            TextField tfNewEmail = new TextField(U.getEmail());

            Button btnChange = new Button("Change");
            Button btnBack = new Button("Back");

            GridPane updateProfileGrid = new GridPane();
            updateProfileGrid.setPadding(new Insets(20));
            updateProfileGrid.setVgap(10);
            updateProfileGrid.setHgap(10);

            updateProfileGrid.add(lblNewUsername, 0, 0);
            updateProfileGrid.add(tfNewUsername, 1, 0);
            updateProfileGrid.add(lblNewEmail, 0, 1);
            updateProfileGrid.add(tfNewEmail, 1, 1);
            updateProfileGrid.add(btnChange, 0, 2);
            updateProfileGrid.add(btnBack, 1, 2);

            Scene updateProfileScene = new Scene(updateProfileGrid, 400, 200);
            primaryStage.setScene(updateProfileScene);

            btnBack.setOnAction(ev -> start(primaryStage));

            user finalU = U;
            user finalU1 = U;
            btnChange.setOnAction(ev -> {
                String newUsername = tfNewUsername.getText().trim();
                String newEmail = tfNewEmail.getText().trim();

                if (newUsername.isEmpty() && newEmail.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in at list one field.");
                    alert.show();
                    return;
                }

                if(newUsername.isEmpty()){
                    controller.UpdateProfile(username, username, newEmail, finalU.getPassword());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully.");
                    alert.show();
                    start(primaryStage);
                } else if (newEmail.isEmpty()) {
                    controller.UpdateProfile(username, newUsername, finalU.getEmail(), finalU.getPassword());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully.");
                    alert.show();
                    start(primaryStage);

                }
                else {
                    controller.UpdateProfile(username, newUsername, newEmail, finalU.getPassword());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully.");
                    alert.show();
                    start(primaryStage);
                }

            });
        });





        btnChangePassword.setOnAction(e -> {
            try {
                user target = UserDatabase.getUserByUsername(username);
                user finalTarget = target;

                Label lblPassword = new Label("Password:");
                PasswordField pfPassword = new PasswordField();

                Label lblNewPassword = new Label("New Password:");
                PasswordField pfNewPassword = new PasswordField();

                Label lblConfirmNewPassword = new Label("Confirm Password:");
                PasswordField pfConfirmNewPassword = new PasswordField();

                Button btnChange = new Button("Change");
                Button btnBack = new Button("Back");

                GridPane updateProfileGrid = new GridPane();
                updateProfileGrid.setPadding(new Insets(20));
                updateProfileGrid.setVgap(10);
                updateProfileGrid.setHgap(10);

                updateProfileGrid.add(lblPassword, 0, 0);
                updateProfileGrid.add(pfPassword, 1, 0);
                updateProfileGrid.add(lblNewPassword, 0, 1);
                updateProfileGrid.add(pfNewPassword, 1, 1);
                updateProfileGrid.add(lblConfirmNewPassword, 0, 2);
                updateProfileGrid.add(pfConfirmNewPassword, 1, 2);
                updateProfileGrid.add(btnBack, 0, 3);
                updateProfileGrid.add(btnChange, 1, 3);

                Scene changePasswordScene = new Scene(updateProfileGrid, 400, 250);
                primaryStage.setScene(changePasswordScene);

                btnBack.setOnAction(ev -> start(primaryStage));

                btnChange.setOnAction(ev -> {
                    String password = pfPassword.getText();
                    String newPassword = pfNewPassword.getText();
                    String confirmPassword = pfConfirmNewPassword.getText();
                    String newPass = newPassword;
                    password =  PasswordHashing.hash(password);
                    newPassword =  PasswordHashing.hash(newPassword);
                    confirmPassword =  PasswordHashing.hash(confirmPassword);

                    if (finalTarget.getPassword().equals(password) && newPassword.equals(confirmPassword)) {
                        controller.changePassword(newPass, username);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password changed successfully.");
                        alert.show();
                        start(primaryStage);
                    } else if (finalTarget.getPassword().equals(password) && !newPassword.equals(confirmPassword)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Passwords don't match.");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong current password.");
                        alert.show();
                    }
                });

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnDeleteAccount.setOnAction(e -> {
            user userToDelete = null;
            try {
                 userToDelete = UserDatabase.getUserByUsername(username);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText("Are you sure you want to delete your account?");
            confirmAlert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                try {
                    userDashboard.deleteUserAccount(userToDelete.getUser_id());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Alert info = new Alert(Alert.AlertType.INFORMATION, "Your account has been deleted.");
                info.showAndWait();


                start(primaryStage);
            }
        });

        btnExit.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20;");
        layout.getChildren().addAll(btnShowMenu, btnPlaceOrder, btnViewOrders, btnUpdateProfile, btnChangePassword,btnDeleteAccount, btnExit);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setPrefSize(300, 400);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scrollPane);
        primaryStage.setTitle("Restaurant User Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
