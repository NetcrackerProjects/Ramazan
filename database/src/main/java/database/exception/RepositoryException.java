package database.exception;

public class RepositoryException extends Exception {

    public RepositoryException(String type, Exception e) {
        super(type, e);
    }
}
