package com.jarvis.ab.service;

import java.util.List;

/**
 * Created by jarvis on 11/02/2017.
 */
public interface BaseService<T> {

    /**
     * find subList from list.
     * @param condition property of object as a search condition.
     * @param value search condition value, can be a regular expression.
     * @return list contains search result.
     */
    public List<T> search(String condition,String value);

    /**
     * add an object to the list
     * @param t object to be added
     */
    public void add(T t);

    /**
     * remove objects from list.
     * @param condition property of object as a remove condition
     * @param value remove condtion value, can be a regular expression.
     * @return number of objects removed.
     */
    public int delete(String condition,String value);

    /**
     * save list in memory to the local file.
     */
    public void save();

    /**
     * load list from local file to memory.
     */
    public void load();
}
