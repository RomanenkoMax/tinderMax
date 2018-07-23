package ua.danit.dao;

public abstract class AbstractDAOtoDB<T> {

    abstract public void put(T obj);

    abstract public void delete(Object pk);
}
