
CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    email VARCHAR(200)

);

CREATE TABLE orders(
    order_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total_price NUMERIC(10,2) NOT NULL
);

CREATE TABLE menu_items(
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(100)  NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL,
    category VARCHAR(50)
);

CREATE TABLE order_details(
    order_details_id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(order_id) NOT NULL,
    menu_item_id INTEGER REFERENCES menu_items(item_id) NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(10,2)NOT NULL

);



INSERT INTO users (username, user_password) VALUES ('PARTOW', '744b7c6af6d16340268ba986853c01737f39a065a6c58c3ae1e8fdef7d6b730c');
INSERT INTO users (username, user_password) VALUES ('Sadaf', '41f4922418da3a5e35552a419f802d172bf712b713b1bcfcac2d453a0dd0f64a');
INSERT INTO users (username, user_password) VALUES ('Partow', '077a588a4352f6e61c95a99c215fa7b16378ed186f8690bd56ab486039219a92');
INSERT INTO users (username, user_password) VALUES ('Mina', '693fcef8b36a97b1fcd364aa3de054d87d379698343d4347eeed5475ff7554bb');
INSERT INTO users (username, user_password) VALUES ('PRoshani', '5b2f6f6ddc43c87d9c069c554308cbc0f56bd428c19b805eb1e07c86a0dab0d3');
INSERT INTO users (username, user_password) VALUES ('Behzad', '387b374483c07c00228535c989db90ab09dc683aa2e067f6b5760e753bab65cf');
INSERT INTO users (username, user_password) VALUES ('Sara', '3a96dcfdadff284d6be603e565ac1fdd9cf72a224804d53e9b60a40c31f95e39');
INSERT INTO users (username, user_password) VALUES ('user1', '1b992a2bb0984ad12a5efc86fb260801e275d71419c7b748b5ff33a497af8423');
INSERT INTO users (username, user_password) VALUES ('U1', '70d5daa01b1e45c0c985f37b4e32f222613edfbce571ff32d81151bb7ab711c1');
INSERT INTO users (username, user_password) VALUES ('U2', '5f9824422a19febf2621418cff4d5905477dcc0738f884deb253a4c62b7bc4c6');
INSERT INTO menu_items (name, price, category) VALUES ('Greek Salad', 85.89, 'Appetizer');
INSERT INTO menu_items (name, price, category) VALUES ('Bruschetta', 75.9, 'Appetizer');
INSERT INTO menu_items (name, price, category) VALUES ('Creamy Mushroom Soup', 80.56, 'Appetizer');
INSERT INTO menu_items (name, price, category) VALUES ('Mozzarella Sticks', 95.33, 'Appetizer');
INSERT INTO menu_items (name, price, category) VALUES ('Grilled Chicken with Rice', 165.0, 'Main Course');
INSERT INTO menu_items (name, price, category) VALUES ('Beef Steak', 245.0, 'Main Course');
INSERT INTO menu_items (name, price, category) VALUES ('Vegetarian Pasta', 135.0, 'Main Course');
INSERT INTO menu_items (name, price, category) VALUES ('Margherita Pizza', 120.0, 'Main Course');
INSERT INTO menu_items (name, price, category) VALUES ('Lamb Chops', 275.0, 'Main Course');
INSERT INTO menu_items (name, price, category) VALUES ('Chocolate Lava Cake', 95.99, 'Dessert');
INSERT INTO menu_items (name, price, category) VALUES ('Cheesecake', 90.0, 'Dessert');
INSERT INTO menu_items (name, price, category) VALUES ('Fruit Salad', 70.99, 'Dessert');
INSERT INTO menu_items (name, price, category) VALUES ('Tiramisu', 95.0, 'Dessert');
INSERT INTO menu_items (name, price, category) VALUES ('Fresh Orange Juice', 60.0, 'Drink');
INSERT INTO menu_items (name, price, category) VALUES ('Mineral Water', 20.35, 'Drink');
INSERT INTO menu_items (name, price, category) VALUES ('Soda', 25.35, 'Drink');
INSERT INTO menu_items (name, price, category) VALUES ('Iced Tea', 40.35, 'Drink');
INSERT INTO menu_items (name, price, category) VALUES ('Espresso', 45.0, 'Drink');
INSERT INTO menu_items (name, price, category) VALUES ('Latte', 50.0, 'Drink');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-27 22:07:33.848419');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-27 22:19:46.959291');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-27 22:25:45.635212');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-28 14:54:37.113104');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-28 14:56:17.18281');
INSERT INTO orders (user_id, created_at) VALUES (5, '2025-05-28 15:52:43.871524');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-28 15:53:41.03941');
INSERT INTO orders (user_id, created_at) VALUES (4, '2025-05-30 21:11:18.707555');
INSERT INTO orders (user_id, created_at) VALUES (8, '2025-05-31 17:39:21.251888');
INSERT INTO orders (user_id, created_at) VALUES (8, '2025-05-31 17:39:47.337762');
INSERT INTO orders (user_id, created_at) VALUES (8, '2025-05-31 17:40:20.876556');
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (6.0, 18.0, 3.0, 45.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (6.0, 11.0, 1.0, 90.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (7.0, 9.0, 1.0, 275.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (10.0, 1.0, 1.0, 85.89);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (10.0, 5.0, 2.0, 165.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (10.0, 17.0, 3.0, 40.35);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (11.0, 6.0, 1.0, 245.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (11.0, 4.0, 2.0, 95.33);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (11.0, 8.0, 1.0, 120.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (11.0, 14.0, 2.0, 60.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (11.0, 16.0, 3.0, 25.35);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (12.0, 6.0, 1.0, 245.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (12.0, 4.0, 2.0, 95.33);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (12.0, 8.0, 1.0, 120.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (12.0, 14.0, 2.0, 60.0);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (12.0, 16.0, 3.0, 25.35);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (13.0, 1.0, 1.0, 85.89);
INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (13.0, 4.0, 2.0, 95.33);