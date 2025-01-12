package codeclause_internship;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceShoppingCart {
    // Product class representing individual products
    static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %s | Price: %.2f", id, name, price);
        }
    }

    // CartItem class representing an item in the shopping cart
    static class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return product.getPrice() * quantity;
        }

        @Override
        public String toString() {
            return String.format("%s | Quantity: %d | Total: %.2f", product.getName(), quantity, getTotalPrice());
        }
    }

    // ShoppingCart class for managing cart items
    static class ShoppingCart {
        private List<CartItem> cartItems;

        public ShoppingCart() {
            this.cartItems = new ArrayList<>();
        }

        public void addItem(Product product, int quantity) {
            for (CartItem item : cartItems) {
                if (item.getProduct().getId() == product.getId()) {
                    item.setQuantity(item.getQuantity() + quantity);
                    return;
                }
            }
            cartItems.add(new CartItem(product, quantity));
        }

        public void removeItem(int productId) {
            cartItems.removeIf(item -> item.getProduct().getId() == productId);
        }

        public double getTotal() {
            double total = 0;
            for (CartItem item : cartItems) {
                total += item.getTotalPrice();
            }
            return total;
        }

        public void displayCart() {
            if (cartItems.isEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                for (CartItem item : cartItems) {
                    System.out.println(item);
                }
            }
        }
    }

    // Main class for driving the application

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some sample products
        Product p1 = new Product(1, "Laptop", 999);
        Product p2 = new Product(2, "Smartphone", 499);
        Product p3 = new Product(3, "Headphones", 199);
        Product p4 = new Product(4, "Books", 99.99);


        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Display menu
        while (true) {
            System.out.println("\n--- Welcome to the E-Commerce Store ---");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Display products
                    System.out.println("\nAvailable Products:");
                    System.out.println(p1);
                    System.out.println(p2);
                    System.out.println(p3);
                    System.out.println(p4);
                    break;
                case 2:
                    // Add product to cart
                    System.out.print("Enter product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Product selectedProduct = null;
                    if (productId == p1.getId()) {
                        selectedProduct = p1;
                    } else if (productId == p2.getId()) {
                        selectedProduct = p2;
                    } else if (productId == p3.getId()) {
                        selectedProduct = p3;
                    }else if (productId == p4.getId()) {
                        selectedProduct = p4;
                    }

                    if (selectedProduct != null) {
                        cart.addItem(selectedProduct, quantity);
                        System.out.println(quantity + " " + selectedProduct.getName() + "(s) added to the cart.");
                    } else {
                        System.out.println("Invalid product ID.");
                    }
                    break;
                case 3:
                    // View cart
                    System.out.println("\nYour Cart:");
                    cart.displayCart();
                    break;
                case 4:
                    // Checkout
                    System.out.println("\n--- Checkout ---");
                    System.out.println("Total amount: $" + cart.getTotal());
                    System.out.print("Enter payment (amount): $");
                    double payment = scanner.nextDouble();
                    if (payment >= cart.getTotal()) {
                        System.out.println("Payment successful! Thank you for your purchase.");
                        cart = new ShoppingCart(); // Empty the cart after purchase
                    } else {
                        System.out.println("Insufficient funds. Transaction failed.");
                    }
                    break;
                case 5:
                    // Exit
                    System.out.println("Thank you for visiting! Goodbye.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

    

