package database;

import database.exception.FailedDataBaseQueryException;

public interface Repository {

    boolean exists(String name) throws FailedDataBaseQueryException;

    UserInformation getByName(String name) throws FailedDataBaseQueryException;

    void save(UserInformation userInformation);

    void clean();
}
