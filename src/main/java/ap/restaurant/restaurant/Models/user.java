package ap.restaurant.restaurant.Models;

public class user {
    private String username;
    private int user_id;
    private String password;

    public user(String username , int user_id , String password){
        this.username = username;
        this.user_id = user_id;
        this.password = password;
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
