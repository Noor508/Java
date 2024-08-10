import java.sql.*;

public class AddressBook {
    private Connection connection;
    private Statement statement;

    public AddressBook() {
        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "username", "password");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(String name, String address, String city, String phone) {
        try {
            String sql = "INSERT INTO info (username, address, city, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, phone);
            preparedStatement.executeUpdate();
            System.out.println("Person added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(String name) {
        try {
            String sql = "DELETE FROM info WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Person deleted successfully!");
            } else {
                System.out.println("Person not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchPerson(String name) {
        try {
            String sql = "SELECT * FROM info WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String phone = resultSet.getString("phone");
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
                System.out.println("City: " + city);
                System.out.println("Phone: " + phone);
            } else {
                System.out.println("Person not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
 import java.util.Scanner;

public class AddressBookDriver {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book Menu:");
            System.out.println("1. Add a person");
            System.out.println("2. Delete a person");
            System.out.println("3. Search for a person");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    addressBook.addPerson(name, address, city, phone);
                    break;
                case 2:
                    System.out.print("Enter name to delete: ");
                    String deleteName = scanner.nextLine();
                    addressBook.deletePerson(deleteName);
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    addressBook.searchPerson(searchName);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            System.out.println();
        }
    }
}