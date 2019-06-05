package database;

import database.exception.FailedDataBaseCreationException;
import game.player.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements Repository<User> {

    private static final String URL = "jdbc:h2:mem:db1";

    private static final String TABLE_NAME = "users";

    private static final String CREATE_TABLE_COMMAND =
            "create table " + TABLE_NAME + "(name VARCHAR(255) PRIMARY KEY, id INT, objectId INT);";

    private Statement statement;

    public UserRepository() throws FailedDataBaseCreationException {
        try {
            Connection connection = DriverManager.getConnection(URL);

            this.statement = connection.createStatement();
            statement.execute(CREATE_TABLE_COMMAND);

        } catch (SQLException e) {
            throw new FailedDataBaseCreationException();
        }
    }

    @Override
    public void add(User user) {
        try {
            ResultSet set = statement.executeQuery(
                    "select name from " + TABLE_NAME +
                            " where name = '" + user.getName() + "';");

            if (!set.next()) {
                try {
                    statement.execute("insert into " + TABLE_NAME +
                            " values('" + user.getName()
                            + "', " + user.getId() + ", " + user.getObjectId() + ")");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ignored) {
        }
    }

    @Override
    public void clear() {
        try {
            statement.execute("drop table " + TABLE_NAME);
        } catch (SQLException ignored) {
        }
    }
}
