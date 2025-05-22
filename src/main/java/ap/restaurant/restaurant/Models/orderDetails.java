package ap.restaurant.restaurant.Models;

public class orderDetails {
    private int order_details_id;
    private int order_id;
    private int menu_item_id;
    private int quantity;
    private double price;

    public orderDetails(int order_details_id, int order_id, int menu_item_id, int quantity, double price){
        this.order_details_id = order_details_id;
        this.order_id = order_id;
        this.menu_item_id = menu_item_id;
        this.quantity = quantity;
        this.price = price;
    }

    public void setOrder_id(int order_id){
        this.order_id = order_id;
    }

    public void setOrder_details_id(int order_details_id){
        this.order_details_id = order_details_id;
    }

    public void setMenu_item_id(int menu_item_id){
        this.menu_item_id = menu_item_id;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getOrder_id(){
        return order_id;
    }

    public int getOrder_details_id(){
        return order_details_id;
    }

    public int getMenu_item_id(){
        return menu_item_id;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }
}
