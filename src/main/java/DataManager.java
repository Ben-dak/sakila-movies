import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {

    public ArrayList<Actor> getActors(String firstName, String lastName) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("Baldursgate#99");

        String query = "SELECT first_name, last_name, actor_id FROM actor WHERE last_name = ? AND first_name = ?";

        ArrayList<Actor> actors = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstNameResult = resultSet.getString("first_name");
                String lastNameResult = resultSet.getString("last_name");
                int id = resultSet.getInt("actor_id");
                actors.add(new Actor(id, firstNameResult, lastNameResult));
            }

        }catch (SQLException e) {
            System.out.println("Bad query" + e);
        }
        return actors;
    }

}
