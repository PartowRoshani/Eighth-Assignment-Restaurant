package ap.restaurant.restaurant.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ap.restaurant.restaurant.Models.user;

public class UserDatabase {

    public static void createUser(user User )throws SQLException{
        try {
            Connection conn = DatabaseManager.connect();
            String query = "INSERT INTO users( username, user_password, email) VALUES( ?, ?, ?)";
            System.out.println("🟢 Trying to insert user into database...");

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, User.getUsername());
            ps.setString(2, User.getPassword());
            ps.setString(3, User.getEmail());

            ps.executeUpdate();
            System.out.println("✅ User inserted successfully.");


            ps.close();
            conn.close();
        }catch(SQLException e){
            throw e;
        }


    }

    public static void updateUser(user User)throws SQLException{

        Connection conn = DatabaseManager.connect();
        System.out.println("Updating user with id: " + User.getUser_id());


        String query = "UPDATE users SET username = ?, user_password = ?, email = ? WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, User.getUsername());
        ps.setString(2, User.getPassword());
        ps.setString(3,User.getEmail());
        ps.setInt(4,User.getUser_id());

        int rowsAffected = ps.executeUpdate();
        System.out.println("Rows updated: " + rowsAffected);

        ps.close();
        conn.close();
    }

    public static void deleteUser(int user_id)throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1,user_id);
        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("No user found with the given ID.");
        }

        ps.executeUpdate();
        ps.close();
        conn.close();

    }

    public static user getUserById(int userId)throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,userId);

        ResultSet rs = ps.executeQuery();
        user result = null;
        if(rs.next()){
            result = new user(
                    rs.getString("username"),
                    rs.getString("user_password"),
                    rs.getString("email")
            );

        }
        rs.close();
        ps.close();
        conn.close();
        return result;

    }

    public static user getUserByUsername(String username)throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,username);

        ResultSet rs = ps.executeQuery();
        user result = null;
        if(rs.next()){
            result = new user(

                    rs.getString("username"),
                    rs.getString("user_password"),
                    rs.getString("email")
            );
            result.setUser_id(rs.getInt("user_id"));

        }
        rs.close();
        ps.close();
        conn.close();
        return result;

    }

    public static List<user> getAllUsers() throws SQLException {
        Connection conn = DatabaseManager.connect();
        String query ="SELECT * FROM users";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<user> users = new ArrayList<>();
        while (rs.next())
        {
            user u = new user( rs.getString("username"),
                    rs.getString("use_password"),
                    rs.getString("email"));
            users.add(u);
        }

        rs.close();
        ps.close();
        conn.close();
        return users;

    }

    public static boolean checkUserCredentials(String username, String hashedPassword) throws SQLException{
        Connection conn = DatabaseManager.connect();
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND user_password = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        boolean available = false;
         if(rs.next()){
             if(rs.getInt(1)> 0)
             {
                 available =true;
             }
         }
        rs.close();
        ps.close();
        conn.close();
        return available;

    }


}
