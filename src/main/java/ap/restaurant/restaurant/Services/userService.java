package ap.restaurant.restaurant.Services;

import ap.restaurant.restaurant.Models.user;
import ap.restaurant.restaurant.dataBase.UserDatabase;
import ap.restaurant.restaurant.security.PasswordHashing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userService {

    public  static void register(user newUser)throws SQLException {
        if (UserDatabase.getUserByUsername(newUser.getUsername() )!= null){
            throw new IllegalArgumentException("Username is already exist!");
        }

        if (newUser.getUsername() == null || newUser.getPassword() == null) {
            throw new IllegalArgumentException("Username and password cannot be null.");
        }

        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            String emailRegex = "^[a-zA-Z0-9]+[a-zA-Z0-9._%+-]+[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!newUser.getEmail().matches(emailRegex)) {
                throw new IllegalArgumentException("Invalid email format.");
            }
        }
        String usernameRegex = "\\b(?=[^\\s]*[A-Z])(?=[^\\s]*[a-z])(?=[^\\s]*\\d)(?=[^\\s]*[!@#$%^&*])[^\\s]{8,}\\b";
        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[!@#$%^&*])[A-Za-z\\\\d!@#$%^&*]{8,}";

        if(newUser.getUsername().matches(usernameRegex)&& newUser.getPassword().matches(passwordRegex)){
        String hashedPassword = PasswordHashing.hash(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        UserDatabase.createUser(newUser);
        }
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
