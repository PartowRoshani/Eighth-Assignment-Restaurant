package ap.restaurant.restaurant.Models;

public class menuItems {
    private int item_id;
    private String name;
    private String description;
    private double price;
    private String category;
    public menuItems(int item_id , String name, String description, double price, String category){
        this.item_id = item_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    public void setItem_id(int item_id){
        this.item_id = item_id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public  void setPrice(double price){
        this.price = price;
    }

    public void setCategory(String category){
        this.category =category;
    }

    public int getItem_id(){
        return item_id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public double getPrice(){
        return price;
    }

    public String getCategory(){
        return category;
    }
}
