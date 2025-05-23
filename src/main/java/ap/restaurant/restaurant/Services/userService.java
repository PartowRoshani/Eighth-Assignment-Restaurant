package ap.restaurant.restaurant.Services;

import ap.restaurant.restaurant.Models.user;
import ap.restaurant.restaurant.dataBase.UserDatabase;
import ap.restaurant.restaurant.security.PasswordHashing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userService {

    public  static void register(user newUser)throws SQLException {
        if (UserDatabase.getUserByUsername(newUser.getUsername() )!= null){
            throw new IllegalArgumentException("Username is already exist!");
        }

        String hashedPassword = PasswordHashing.hash(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        UserDatabase.createUser(newUser);
    }


    public static boolean login(String username , String password)throws SQLException {
        user DatabaseUser = UserDatabase.getUserByUsername(username);
        if (DatabaseUser == null) {
            return false;
        }

        return PasswordHashing.verify(password, DatabaseUser.getPassword());
    }


    public static void updateProfile(user User) throws SQLException {
        String hashedPassword = PasswordHashing.hash(User.getPassword());
        User.setPassword(hashedPassword);

        UserDatabase.updateUser(User);
    }

    public static void deleteAccount(user User)throws SQLException{
        UserDatabase.deleteUser(User);
    }

    public static void changePassword(String username , String newPassword) throws SQLException {
        user User = UserDatabase.getUserByUsername(username);

        if(UserDatabase.getUserByUsername(username) == null){
            throw new IllegalArgumentException("user not found!");
        }

        String hashedPassword = PasswordHashing.hash(newPassword);
        User.setPassword(hashedPassword);
        UserDatabase.updateUser(User);
    }


    public static List<user> AllUsersList() throws SQLException {
       List<user> Users = new ArrayList<>();
       Users = UserDatabase.getAllUsers();
        return Users;
    }

    public static user getProfile(String username) throws SQLException {
        return UserDatabase.getUserByUsername(username);
    }
}
