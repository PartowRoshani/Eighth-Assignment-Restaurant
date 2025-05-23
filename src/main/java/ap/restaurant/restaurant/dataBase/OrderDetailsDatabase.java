package ap.restaurant.restaurant.dataBase;

import ap.restaurant.restaurant.Models.orderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDatabase {
    public static void createdOrderDetails(orderDetails detail)throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUE (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, detail.getOrder_id());
        ps.setInt(2, detail.getMenu_item_id());
        ps.setInt(3, detail.getQuantity());
        ps.setDouble(4, detail.getPrice());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static List<orderDetails> getDetailsByOrderId(int orderId) throws SQLException {
        List<orderDetails> details = new ArrayList<>();
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM order_details WHERE order_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            details.add(new orderDetails(
                    rs.getInt("order_detail_id"),
                    rs.getInt("order_id"),
                    rs.getInt("menu_item_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
            ));
        }
        rs.close();
        ps.close();
        conn.close();
        return details;
    }

    public static void deleteDetailsByOrderDetailsId(orderDetails details) throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query = "DELETE FROM order_details WHERE order_detail_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, details.getOrder_details_id());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }


    public static void deleteDetailsByOrderId(int orderId) throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query = "DELETE FROM order_details WHERE order_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, orderId);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

}
