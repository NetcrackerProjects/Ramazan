package database;

import database.exception.FailedDataBaseCreationException;
import game.player.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UserRepositoryTest {

    private static final String URL = "jdbc:h2:mem:db1";

    private static final String TABLE_NAME = "users";

    private UserRepository userRepository;

    private Statement statement;

    @Before
    public void setup() throws FailedDataBaseCreationException, SQLException {
        this.userRepository = new UserRepository();
        Connection connection = DriverManager.getConnection(URL);

        this.statement = connection.createStatement();
    }

    @Test
    public void shouldSaveUserIntoDataBaseWhenCalledAdd() throws SQLException {
        userRepository.add(new User(1, 2, "test"));

        ResultSet set = statement.executeQuery("select * from " + TABLE_NAME + " where name = 'test'");
        assertTrue(set.next());
    }

    @Test
    public void shouldSaveIdIntoDataBaseWhenCalledAdd() throws SQLException {
        userRepository.add(new User(1, 2, "test"));

        ResultSet set = statement.executeQuery("select * from " + TABLE_NAME + " where name = 'test'");
        set.next();
        int id = set.getInt(2);
        assertEquals(1, id);
    }

    @Test
    public void shouldSaveObjectIdIntoDataBaseWhenCalledAdd() throws SQLException {
        userRepository.add(new User(1, 2, "test"));

        ResultSet set = statement.executeQuery("select * from " + TABLE_NAME + " where name = 'test'");
        set.next();
        int objectId = set.getInt(3);
        assertEquals(2, objectId);
    }

    @After
    public void cleanUp() {
        userRepository.clear();
    }
}
