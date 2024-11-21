//Code based on Jin He's Work, made operable by Zack Bolin
//Code based on Jin He's Work, made operable by Zack Bolin
//Code based on Jin He's Work, made operable by Zack Bolin

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.ProcessBuilder;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordManager manager = new PasswordManager(); //Moved by Zack
        System.out.println("What would you like to do?");
        System.out.println("(You can: Add a new Account, Modify an Account, Delete an Account, View existing Accounts, or change a password)");
        String Action = scanner.nextLine();
        if(Action.contains("add")){
            System.out.println("What is the name of the new Account?");
            String NewUserNameInput = scanner.nextLine();
            manager.addUser(NewUserNameInput);    // Adding users and Accounts - Moved into if statement by Zack
            User NewUser = manager.getUser(NewUserNameInput);
            NewUser.addAccount(new Account((NewUser+"_fb"), "fbPass123", "Social"));
            NewUser.addAccount(new Account((NewUser+"_gmail"), "gmPass456", "Email"));
            NewUser.addAccount(new Account((NewUser+"_amazon"), "amzPass789", "Shopping"));
            FileStorage.saveUserAccounts(NewUser); // Saving user Accounts - Moved into if statement by Zack
        }







        if(Action.contains("mod")){
            System.out.println("Which Account would you like to modify?");
            String Account = scanner.nextLine();
        try {

            Files.copy(Account+"_Accounts.txt", Account+"Temp_Accounts.txt");
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 












        }
        else if(Action.contains("del")){
            System.out.println("Which Account are you deleting?");
            String Account = scanner.nextLine();
        try {

            Files.delete(FileSystems.getDefault().getPath(Account+"_Accounts.txt"));
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
              // Delete Account - Moved into if statement by Zack
        }
        else if(Action.contains("password")){
            System.out.println("Which Account are you changing the password of?");
            String Account = scanner.nextLine();
            manager.generatePassword(); // Generate password - Moved into if statement by Zack
        }
        else if(Action.contains("view")){
            System.out.println("Which Account are you viewing?");
            String Account = scanner.nextLine();
            
        }
        // Random speech line
        SpeechLines.randomSpeech();
        scanner.close();
    }
}
class Account {
    static String username;
    private static String password;
    private static String category;
    public Account(String username, String password, String category) {
        this.username = username;
        this.password = password;
        this.category = category;
    }
    public String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
        Account.username = username;
    }
    public String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        Account.password = password;
    }
    public String getCategory() {
        return category;
    }
    public static void setCategory(String category) {
        Account.category = category;
    }
    @Override
    public String toString() {
        return "Account [username=" + username + ", password=" + password + ", category=" + category + "]";
    }
}
class User {
    private static String name;
        private List<Account> Accounts;
        public User(String name) {
            this.name = name;
            this.Accounts = new ArrayList<>();
        }
        public static String getName() {
                return name;
        }
        public List<Account> getAccounts() {
            return Accounts;
        }
        public void addAccount(Account Account) {
            Accounts.add(Account);
        }
        public void removeAccount(Account Account) {
            Accounts.remove(Account);
        }
        public void listCategories() {
            Set<String> categories = new HashSet<>();
            for (Account Account : Accounts) {
                categories.add(Account.getCategory());
            }
            System.out.println("Categories: " + categories);
        }
        public Account findAccount(String username) {
            for (Account Account : Accounts) {
                if (Account.getUsername().equals(username)) {
                    return Account;
                }
            }
            return null;
        }
    }
    class PasswordManager {
        private Map<String, User> users;
        public PasswordManager() {
            users = new HashMap<>();
        }
        public void addUser(String userName) {
            if (!users.containsKey(userName)) {
                users.put(userName, new User(userName));
                System.out.println("User added: " + userName);
            } else {
                System.out.println("User already exists.");
            }
        }
        public User getUser(String userName) {
            return users.get(userName);
        }
    
    
    
    
        public void deleteAccount(String userName, String AccountName) {
            User user = users.get(userName);
            if (user != null) {
                Account Account = user.findAccount(AccountName);
                if (Account != null) {
                    user.removeAccount(Account);
                    System.out.println("Account deleted.");
                } else {
                    System.out.println("Account not found.");
                }
            } else {
                System.out.println("User not found.");
            }
        }
    
    
    
    
        public void listCategories(String userName) {
            User user = users.get(userName);
            if (user != null) {
                user.listCategories();
            } else {
                System.out.println("User not found.");
            }
        }
        
        public void modifyAccount(String userName, String oldUsername, String Test1, String Test2, String Test3){ 
            Scanner scanner = new Scanner(System.in); //Added by Zack
            PasswordManager manager = new PasswordManager();
            if (true) {
                System.out.println(userName+" was your response");
                if(userName.contains(userName)){ 
                    System.out.println("What is your new username (just retype it if nothing has changed)");
                    User NewUsername = manager.getUser(scanner.next());
                    FileStorage.saveUserAccounts(NewUsername);
                    System.out.println("What is the new category of your account (just retype it if nothing has changed.)");
                    String newCategory = scanner.nextLine();
                    Account.setCategory(newCategory);
                    System.out.println("Account modified.");
                } else {
                    System.out.println("Account not found.");
                }
            }
        }
                
        public void generatePassword() {
            Scanner ui = new Scanner(System.in); //Added by Zack
            System.out.println("Would you like a random password?");
            String response = ui.nextLine(); 
            if(response.contains("y")){
                String password = UUID.randomUUID().toString(); //Jin's Work
                System.out.println("Generated Password: " + password); //Jin's Work
            }
            else{
                System.out.println("Please type out your password:"); //Added by Zack
                String password = ui.nextLine();
                System.out.println("Your New Password: " + password);
            }
            
        }
    }
    public class FileStorage {
        public static void saveUserAccounts(User user) {
            try (FileWriter writer = new FileWriter(User.getName() + "_Accounts.txt")) {
            for (Account Account : user.getAccounts()) {
                writer.write(Account.toString() + "\n");
            }
            System.out.println("Accounts saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
class SpeechLines {
    private static final String[] lines = {
        "Success! Keep moving forward.",
        "Operation completed!",
        "You're on fire today!",
        "Don't forget to save your work.",
        "Another day, another task done!"
    };
    public static void randomSpeech() {
        Random random = new Random();
        System.out.println(lines[random.nextInt(lines.length)]);
    }
}
