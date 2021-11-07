package pl.dawidpalka.readstack.domain.category;

import pl.dawidpalka.readstack.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao {
    private final DataSource dataSource;

    public CategoryDao() {
        try {
            dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findAll() {
        final String query = """
                SELECT
                    id, name, description
                FROM
                    category c
                """;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            List<Category> allCategories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                allCategories.add(category);
            }
            return allCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Category> findById(int categoryId) {
        final String query = """
                SELECT
                    id, name, description
                FROM
                    category
                WHERE
                    id = ?
                """;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = mapRow(resultSet);
                return Optional.of(category);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException {
        int categoryId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        return new Category(categoryId, name, description);
    }
}
