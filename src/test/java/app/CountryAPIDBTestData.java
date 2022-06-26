package app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.*;

abstract class CountryAPIDBTestData {

    private PreparedStatement statement;
    protected static Connection connection;

    @BeforeAll
    protected static void setBDConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/app-db",
                "app-db-admin",
                "mysecretpassword"
        );
    }

    @AfterAll
    protected static void disconnect() throws SQLException {
        connection.close();
    }

    protected Long addCountry(Long id, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO country(id, country_name) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        statement.setLong(1, id);
        statement.setString(2, name);

        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getLong(1);
    }

    protected void deleteCountry(Long id) throws SQLException {
        statement = connection.prepareStatement(
                "DELETE from country where id = ?"
        );
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    protected ResultSet getCountryById(Long id) throws SQLException {
        statement = connection.prepareStatement(
                "SELECT * from country where id = ?"
        );
        statement.setLong(1, id);
        ResultSet row = statement.executeQuery();
        if (row.next()) return row;
        else return null;
    }

    public ResultSet sqlRequest(String request) throws SQLException {
        statement = connection.prepareStatement(request);
        ResultSet result = statement.executeQuery();
        result.next();
        return result;
    }

}
