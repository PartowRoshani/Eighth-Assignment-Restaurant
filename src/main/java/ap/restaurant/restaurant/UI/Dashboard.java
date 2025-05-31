package ap.restaurant.restaurant.UI;

import ap.restaurant.restaurant.Models.menuItems;
import ap.restaurant.restaurant.Models.orders;
import ap.restaurant.restaurant.dataBase.OrderDatabase;
import ap.restaurant.restaurant.dataBase.menuItemDetabase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class Dashboard extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
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

                List<String> cart = new ArrayList<>();

                for (int i = 0; i < menu.size(); i++) {
                    menuItems item = menu.get(i);

                    Label nameLabel = new Label(item.getName());
                    Label priceLabel = new Label("$" + item.getPrice());
                    Button addToCart = new Button("Add to Cart");

                    int row = i;
                    addToCart.setOnAction(ev -> {
                        cart.add(item.getName() + " - $" + item.getPrice());
                        System.out.println("Added to cart: " + item.getName());
                    });

                    grid.add(nameLabel, 0, row);
                    grid.add(priceLabel, 1, row);
                    grid.add(addToCart, 2, row);
                }

                Button viewCartButton = new Button("View Cart");
                viewCartButton.setOnAction(ev -> {
                    Stage cartStage = new Stage();
                    VBox cartLayout = new VBox(10);
                    cartLayout.setStyle("-fx-padding: 20;");
                    for (String c : cart) {
                        cartLayout.getChildren().add(new Label(c));
                    }
                    cartLayout.getChildren().add(new Button("Close"));
                    Scene cartScene = new Scene(cartLayout, 300, 200);
                    cartStage.setScene(cartScene);
                    cartStage.setTitle("Your Cart");
                    cartStage.show();
                });

                VBox container = new VBox(15, grid, viewCartButton);
                Scene menuScene = new Scene(container, 400, 500);
                Stage menuStage = new Stage();
                menuStage.setTitle("Place Order");
                menuStage.setScene(menuScene);
                menuStage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnViewOrders.setOnAction(e -> System.out.println("View My Orders clicked"));
        btnUpdateProfile.setOnAction(e-> System.out.println("Update Profile clicked"));
        btnChangePassword.setOnAction(e-> System.out.println("Change Password clicked"));
        btnDeleteAccount.setOnAction(e -> System.out.println("Delete Account clicked"));
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
