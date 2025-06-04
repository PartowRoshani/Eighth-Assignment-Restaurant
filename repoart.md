
# 🍽️ Restaurant Management System - Final Report

## 📘 Overview

The **Restaurant Management System** is a desktop-based application developed using **JavaFX** and backed by a **PostgreSQL** database. It enables customers to register, log in, browse the menu , place and view orders, and manage their profiles. 

---

## 📂 Project Architecture

### ➤ Layers:

1. **Presentation Layer (UI)**  
   - Built with JavaFX
   - Interfaces include Login, Register, Dashboard, Menu Viewer, Order Form, etc.

2. **Business Logic Layer**  
   - `OrderService.java`, `userService.java` handle all operational logic (register, order, update, etc.)

3. **Data Access Layer**  
   - DAO classes like `menuItemDetabase.java`, `DatabaseManager.java`
   - Manage all JDBC-based database operations

4. **Model Layer**  
   - POJOs: `user.java`, `menuItems.java`, `orders.java`, `orderDetails.java`

---

## 🧩 Entity Relationship (Database Schema)

| Table         | Description                              |
|---------------|------------------------------------------|
| `users`       | Stores user credentials and metadata     |
| `menu_items`  | Restaurant menu with name, price, category, and description |
| `orders`      | Order header with reference to user      |
| `order_details` | Line items for each order               |

The schema is defined in `init.sql`, which includes both table definitions and sample data using `INSERT INTO`.

---

## 🔒 Security Measures

- Passwords are hashed using **SHA-256** (`PasswordHashing.java`)
- Stored passwords are never saved in plaintext
- Users cannot change password without confirming the current one

---

## 🖥️ JavaFX UI Features

- **Login & Registration**
- **Scroll-based Order Form** (select quantity)
- **Order History** with detail popups
- **Profile Editor** and **Password Updater**

---

## 🧠 Core Functionalities

- `registerCtrl(username, password, email)` – Validates and registers users
- `login(username, password)` – Checks SHA-256 password hash
- `placeOrder(order, userId, orderDetails)` – Persists full order
- `getAllMenuItems()` – Dynamically loads menu from DB

---

## 📊 Technologies Used

| Technology     | Purpose                      |
|----------------|------------------------------|
| Java 17        | Core programming             |
| JavaFX         | User Interface               |
| PostgreSQL     | Persistent database          |
| JDBC           | Database connectivity        |
| Gradle         | Build automation             |

---

## 🧪 How to Run the Project

1. Setup PostgreSQL and run `init.sql`
2. Open the project in IntelliJ or Eclipse
3. Run `RestaurantApp.java` from `UI` package

---

## ✅ Final Notes

- This project demonstrates clear layering (UI, Logic, DAO)
- Strong password hashing and clean navigation
- Ready for expansion to admin roles, analytics, etc.

🧑‍💻 Authors
Author Name: [Partow Roshani]
Email: [roshanipartow@gmail.com]
GitHub: [https://github.com/PartowRoshani]


