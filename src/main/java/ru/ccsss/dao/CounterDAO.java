package ru.ccsss.dao;

public interface CounterDAO {
    void decrementCounter();

    void incrementCounter();

    int getCounter();

}
