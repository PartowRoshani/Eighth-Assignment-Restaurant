package ap.restaurant.restaurant.Controller;

import ap.restaurant.restaurant.Models.user;
import ap.restaurant.restaurant.Services.userService;

import java.sql.SQLException;

public class userController {

    public boolean registerCtrl(String username, String password, String email){

        user newUser = new user(username, password, email);
        try{
            return userService.register(newUser);
        } catch (IllegalArgumentException e) {
            System.out.println("Register failed: "+e.getMessage());
            return false;

        }
        catch (SQLException e){
            System.out.println("Database Error: "+e.getMessage());
            return false;
        }

    }

    public boolean login(String username, String password){
        try {
            boolean verify = userService.login(username,password);
            if(verify){
                System.out.println("Login successful");

            }
            else{
                System.out.println("Something went wrong! try again");
            }
            return verify;
        }
        catch (SQLException e){
            System.out.println("Database error"+ e.getMessage());
            return false;
        }


    }

    public void UpdateProfile(String username, String newUsername, String newEmail, String Password){
        try{
            userService.updateProfile(username, newUsername, newEmail, Password);
            System.out.println("Updated");
        }
        catch (SQLException e){
            System.out.println("Something went Wrong :"+e.getMessage());
        }
    }

    public void deleteAccount(user target){
        try{
            userService.deleteAccount(target);
            System.out.println("Deleted");
        }
        catch (SQLException e){
            System.out.println("Something went wrong"+e.getMessage());
        }
    }

    public void changePassword(String newPassword, String username){
        try{
            userService.changePassword(username,newPassword);
        }
        catch (IllegalArgumentException e){
            System.out.println("Error :"+e.getMessage());
        }
        catch (SQLException e ){
            System.out.println("Somethings went Wrong:"+e.getMessage());
        }
    }
}
