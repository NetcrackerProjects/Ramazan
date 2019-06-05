package database;

import database.exception.RepositoryException;

public interface Repository<T> {

    void add(T data) throws RepositoryException;

    void clear() throws RepositoryException;
}
