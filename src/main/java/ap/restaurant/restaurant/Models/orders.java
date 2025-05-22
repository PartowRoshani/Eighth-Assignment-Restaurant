package ap.restaurant.restaurant.Models;

public class orders {
    private int order_id;
    private  int user_id;
    private String created_at;
    private double total_price;

    public orders(int order_id, int user_id, String created_at, double total_price){
        this.order_id = order_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.total_price = total_price;
    }

    public void setOrder_id(int order_id){
        this.order_id = order_id;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }

    public void setTotal_price(double total_price){
        this.total_price = total_price;
    }

    public int getOrder_id(){
        return order_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getCreated_at(){
        return created_at;
    }

    public double getTotal_price(){
        return total_price;
    }

}
