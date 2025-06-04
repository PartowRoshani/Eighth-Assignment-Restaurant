package ap.restaurant.restaurant.Models;

public class user {
    private String username;
    private int user_id;
    private String password;
    private String email;

    public user(String username , String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;

    }


    public void  setUsername(String username){
        this.username =username;
    }

    public void setUser_id(int user_id){
        this.user_id =user_id;
    }

    public void  setPassword(String password){
        this.password =password;
    }

    public void setEmail(String email){ this.email = email;}

    public String getEmail(){
        return email;
    }

    public String getUsername(){
        return username;
    }

    public  int getUser_id(){
        return user_id;
    }

    public String getPassword(){
        return password;
    }
}
