package ap.restaurant.restaurant.Services;

import ap.restaurant.restaurant.Models.menuItems;
import ap.restaurant.restaurant.Models.orderDetails;
import ap.restaurant.restaurant.Models.orders;
import ap.restaurant.restaurant.dataBase.OrderDatabase;
import ap.restaurant.restaurant.dataBase.OrderDetailsDatabase;
import ap.restaurant.restaurant.dataBase.menuItemDetabase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    public static void placeOrder(orders order, int user_id, List<orderDetails> details) throws SQLException {

        order.setUser_id(user_id);
        if(order.getCreated_at() == null){
            order.setCreated_at(LocalDateTime.now());

            }

        double totalPrice = 0;

        for (orderDetails detail : details) {
            menuItems item = menuItemDetabase.getMenuItemById(detail.getMenu_item_id());
            if (item == null) {
                throw new IllegalArgumentException("Item not found!");
            }

            detail.setPrice(item.getPrice());

            totalPrice += item.getPrice() * detail.getQuantity();

        }
        order.setTotal_price(totalPrice);



        if(order.getTotal_price() <= 0){
            throw new IllegalArgumentException("Total price cannot be 0 or negative !");

        }

        OrderDatabase.createOrder(order);


        int orderId = order.getOrder_id();
        if (orderId == 0) {
            throw new SQLException("Failed to retrieve order ID after creation.");
        }

        for(orderDetails detail : details){
            menuItems item = menuItemDetabase.getMenuItemById(detail.getMenu_item_id());
            detail.setOrder_id(orderId);
            if (item == null){
                throw new IllegalArgumentException("Item not found!");

            }


            OrderDetailsDatabase.createdOrderDetails(detail);

        }
        System.out.println("Order placed successfully with ID: " + orderId);

    }

    public static List<orders> AllOrdersForUser(int user_id) throws SQLException {
        return OrderDatabase.getOrderByUserId(user_id);
    }

    public static void CancelOrder(int order_id) throws SQLException {
        orders order = OrderDatabase.getOrderById(order_id);
        if(order == null){
            throw new IllegalArgumentException("Order not found!");
        }

        OrderDatabase.deleteOrder(order);
        OrderDetailsDatabase.deleteDetailsByOrderId(order.getOrder_id());
    }

    public static List<orderDetails>Details(int order_id)throws SQLException{
        return OrderDetailsDatabase.getDetailsByOrderId(order_id);
    }



}


