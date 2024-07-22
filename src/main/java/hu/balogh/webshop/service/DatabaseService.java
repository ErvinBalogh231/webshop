package hu.balogh.webshop.service;

import hu.balogh.webshop.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {
    /*private final DataSource dataSource;*/

    @PersistenceContext
    private EntityManager entityManager;

    /*@Autowired
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*public List<Item> fetchItems() {
        List<Item> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, price FROM items");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                BigDecimal price = resultSet.getBigDecimal(3);

                result.add(new Item(id, name, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }*/

    public List<Item> fetchItems() {
        return (List<Item>) entityManager.createQuery("SELECT i FROM Item i").getResultList();
    }

    /*public void insertItem(Item item) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items (name, price) VALUES (?, ?)");
            preparedStatement.setString(1, item.name());
            preparedStatement.setBigDecimal(2, item.price());
            int i = preparedStatement.executeUpdate();
            if (i != 1) {
                throw new SQLException("Insert failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/

    @Transactional
    public void insertItem(final Item item) {
        entityManager.persist(item);
    }

}
