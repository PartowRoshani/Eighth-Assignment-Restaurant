package ap.restaurant.restaurant.dataBase;
import ap.restaurant.restaurant.Models.menuItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class menuItemDetabase {

    public static void CreateItem(menuItems item)throws SQLException {

        Connection conn = DatabaseManager.connect();
        String query = "INSERT INTO menu_items (name, description, price, category) VALUE(?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, item.getName());
        ps.setString(2, item.getDescription());
        ps.setDouble(3, item.getPrice());
        ps.setString(4, item.getCategory());


        ps.executeUpdate();
        ps.close();
        conn.close();

    }


    public static void updateMenuItem(menuItems item)throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "UPDATE menu_items SET name = ?, description = ?, price = ?, category = ? WHERE item_id = ?";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, item.getName());
        ps.setString(2, item.getDescription());
        ps.setDouble(3, item.getPrice());
        ps.setString(4, item.getCategory());
        ps.setInt(6, item.getItem_id());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void deleteMenuItem(int itemId) throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query = "DELETE FROM menu_items WHERE item_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, itemId);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static List<menuItems> getAllMenuItems() throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM menu_items";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        List<menuItems> items = new ArrayList<>();

        while (rs.next()) {
            menuItems item = new menuItems(
                    rs.getInt("item_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("category")
            );
            items.add(item);
        }

        rs.close();
        ps.close();
        conn.close();

        return items;
    }

    public static menuItems getMenuItemById(int itemId) throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM menu_items WHERE item_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, itemId);
        ResultSet rs = ps.executeQuery();

        menuItems item = null;
        if (rs.next()) {
            item = new menuItems(
                    rs.getInt("item_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("category")
            );
        }

        rs.close();
        ps.close();
        conn.close();

        return item;
    }
}
