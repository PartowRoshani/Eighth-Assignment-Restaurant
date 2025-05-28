package ap.restaurant.restaurant.dataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import  ap.restaurant.restaurant.Models.orders;

public class OrderDatabase {

    public static void createOrder(orders order) throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "INSERT INTO orders(user_id, created_at, total_price) VALUES (?, ?, ?) RETURNING order_id";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, order.getUser_id());
        ps.setTimestamp(2, Timestamp.valueOf(order.getCreated_at()));
        ps.setDouble(3, order.getTotal_price());
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            int generatedId = rs.getInt("order_id");
            order.setOrder_id(generatedId);
        }
        else
        {
            throw new SQLException("Failed to retrieve generated order ID.");
        }


        rs.close();
        ps.close();
        conn.close();
    }

    public static void updateOrder(orders order) throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "UPDATE orders SET user_id = ?, created_at = ?, total_price = ? WHERE order_id = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, order.getUser_id());
        ps.setTimestamp(2, Timestamp.valueOf(order.getCreated_at()));
        ps.setDouble(3, order.getTotal_price());
        ps.setInt(4, order.getOrder_id());
        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public static void deleteOrder(orders order) throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "DELETE FROM orders WHERE order_id = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, order.getOrder_id());

        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public static orders getOrderById(int id) throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM orders WHERE order_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        orders order = null;
        if (rs.next()) {
            order = new orders(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getDouble("total_price")
            );
        }

        rs.close();
        ps.close();
        conn.close();
        return order;
    }


    public static List<orders> getOrderByUserId(int user_id) throws SQLException {
        Connection conn = DatabaseManager.connect();
        List<orders> Orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Orders.add(new orders(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getDouble("total_price")
            ));
        }

        rs.close();
        ps.close();
        conn.close();
        return Orders;
    }

    public static List<orders> getAllOrders() throws SQLException {
        List<orders> Orders = new ArrayList<>();
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM orders";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Orders.add(new orders(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getDouble("total_price")
            ));
        }

        rs.close();
        ps.close();
        conn.close();
        return Orders;
    }

    public static void deleteOrderByUserId(int userId)throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "DELETE FROM orders WHERE user_id = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, userId);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }



}
