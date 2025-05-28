package ap.restaurant.restaurant.Models;

import java.time.LocalDateTime;

public class orders {
    private int order_id;
    private  int user_id;
    private LocalDateTime created_at;
    private double total_price;

    public orders(int order_id, int user_id, LocalDateTime created_at, double total_price){
        this.order_id = order_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.total_price = total_price;
    }

    public orders() {

    }

    public void setOrder_id(int order_id){
        this.order_id = order_id;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public void setCreated_at(LocalDateTime created_at){
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

    public LocalDateTime getCreated_at(){
        return created_at;
    }

    public double getTotal_price(){
        return total_price;
    }

}
