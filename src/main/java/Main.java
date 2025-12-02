import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (
                BasicDataSource dataSource = new BasicDataSource();
                Scanner myScanner = new Scanner(System.in)
        ) {
            dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
            dataSource.setUsername("root");
            dataSource.setPassword("Baldursgate#99");

            System.out.print("Enter the first name of an actor: ");
            String firstName = myScanner.nextLine();

            String actorQuery = """
                SELECT actor_id, first_name, last_name
                FROM actor
                WHERE first_name = ?
            """;

            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(actorQuery)
            ) {
                statement.setString(1, firstName);

                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        System.out.println("Matching actors:");
                        do {
                            int id = results.getInt("actor_id");
                            String first = results.getString("first_name");
                            String last = results.getString("last_name");
                            System.out.printf("ID: %d  Name: %s %s%n", id, first, last);
                        } while (results.next());
                    } else {
                        System.out.println("No actors found with that first name.");
                        return;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}