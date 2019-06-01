package database;

import database.exception.FailedDataBaseCreationException;
import database.exception.FailedDataBaseQueryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryImplementation implements Repository {

    private static final String URL = "jdbc:h2:mem:db1";

    private static final String TABLE_NAME = "users";

    private static final String CREATE_TABLE_COMMAND =
            "create table " + TABLE_NAME + "(name VARCHAR(255) PRIMARY KEY, health INT);";

    private Statement statement;

    public RepositoryImplementation() throws FailedDataBaseCreationException {
        try {
            Connection connection = DriverManager.getConnection(URL);

            this.statement = connection.createStatement();
            statement.execute(CREATE_TABLE_COMMAND);

        } catch (SQLException e) {
            throw new FailedDataBaseCreationException();
        }
    }

    @Override
    public boolean exists(String name) throws FailedDataBaseQueryException {
        try {
            ResultSet set = statement.executeQuery(
                    "select name, health from " + TABLE_NAME + " where name = '" + name + "';");

            return set.next();
        } catch (SQLException e){
            throw new FailedDataBaseQueryException();
        }
    }

    @Override
    public UserInformation getByName(String name) throws FailedDataBaseQueryException {
        try {
            ResultSet set = statement.executeQuery(
                    "select name, health from " + TABLE_NAME + " where name = '" + name + "';");
            if (!set.next()) {
                throw  new FailedDataBaseQueryException();
            }
            String userInformationName = set.getString(1);
            int health = set.getInt(2);

            return new UserInformation(userInformationName, health);
        } catch (SQLException e){
            throw new FailedDataBaseQueryException();
        }
    }

    @Override
    public void save(UserInformation userInformation) {
        try {
            ResultSet set = statement.executeQuery(
                    "select name from " + TABLE_NAME +
                            " where name = '" + userInformation.getName() + "';");

            if (set.next()) {
                update(userInformation);
            }
            else {
                saveNewData(userInformation);
            }
        } catch (SQLException ignored) {
        }
    }

    @Override
    public void clean() {
        try {
            statement.execute("drop table " + TABLE_NAME);
        } catch (SQLException ignored) {
        }
    }

    private void update(UserInformation userInformation) {
        try {
            statement.execute(
                    "update " + TABLE_NAME +
                      " set health = " + userInformation.getHealth() +
                      " where name = '" + userInformation.getName() + "'");
        } catch (SQLException ignored) {
        }
    }

    private void saveNewData(UserInformation userInformation) {
        try {
            statement.execute("insert into " + TABLE_NAME +
                                " values('" + userInformation.getName()
                                    + "', " + userInformation.getHealth() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
