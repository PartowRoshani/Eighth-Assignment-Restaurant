module ap.restaurant.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ap.restaurant.restaurant to javafx.fxml;
    exports ap.restaurant.restaurant;
    exports ap.restaurant.restaurant.Models;
    opens ap.restaurant.restaurant.Models to javafx.fxml;
}