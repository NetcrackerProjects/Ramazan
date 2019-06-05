package database;

public interface Repository<T> {

    void add(T data);

    void clear();
}
