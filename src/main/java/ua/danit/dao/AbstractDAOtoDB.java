package ua.danit.dao;

public abstract class AbstractDAOtoDB<T> {

    abstract public void put(T obj);

    abstract public void update(T obj);

    abstract public T get(Object pk);

    abstract public void delete(Object pk);
}
