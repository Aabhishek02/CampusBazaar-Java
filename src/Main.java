import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to CampusBazaar ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        System.out.println("Choose an option");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if(choice == 1){
            registerUser(sc);
        } else if (choice == 2){
            loginUser(sc);
        } else{
            System.out.println("Thank you for using CampusBazaar!");
        }
        sc.close();
    }
    static void registerUser(Scanner sc){
        try {
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            User user = new User(username, password);

            FileWriter fw = new FileWriter("data/users.txt", true);
            fw.write(user + "\n");
            fw.close();

            System.out.println("Register successfully!");
        } catch (IOException e) {
            System.out.println("There was an error while trying to register user!");
        }
    }
    static void loginUser(Scanner sc){
        System.out.println("Enter Username: ");
        String username = sc.nextLine();

        System.out.println("Enter Password: ");
        String password = sc.nextLine();

        boolean isAuthenticated = false;

        try{
            BufferedReader br = new BufferedReader(new FileReader("data/users.txt"));
            String line;

            while((line = br.readLine()) != null){
                String[] userData = line.split(",");

                if(userData[0].equals(username) && userData[1].equals(password)){
                    isAuthenticated = true;
                    break;
                }
            }

            br.close();

            if(isAuthenticated){
                System.out.println("✅ Login successful. Welcome " + username + "!");
                userMenu(sc, username);
            } else{
                System.out.println("❌ Invalid credentials.");
            }
        } catch (IOException e){
            System.out.println("There was an error while trying to login user!");
        }
    }

    static void userMenu(Scanner sc, String username){

        while(true){
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Product");
            System.out.println("3. Logout");

            System.out.println("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                addProduct(sc, username);
            }else if(choice == 2) {
                viewProducts();
            }else{
                System.out.println("Thank you for using CampusBazaar!");
                break;
            }
        }
    }

    static void addProduct(Scanner sc, String username){
        try{
            System.out.println("Enter Product Name: ");
            String title = sc.nextLine();

            System.out.println("Enter Product Price: ");
            double price =  sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter Product Category: ");
            String category = sc.nextLine();

            Products product = new Products(title,price,category,username);

            FileWriter fw = new FileWriter("data/products.txt",true);
            fw.write(product.toString() + "\n");
            fw.close();

            System.out.println("✅ Product added successfully!");
        } catch (IOException e){
            System.out.println("There was an error while trying to add product!");
        }
    }

    static void viewProducts(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/products.txt"));
            String line;

            System.out.println("\n--- Available Products ---");
            
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                System.out.println(
                        "Product Name: " + data[0] + "\n" + "Price: " +  data[1] + "\n" + "Category: " + data[2] + "\n" + "Seller: " + data[3] + "\n"
                );
            }
            br.close();
        } catch (IOException e){
            System.out.println("There was an error while trying to view products!");
        }
        
    }
}
