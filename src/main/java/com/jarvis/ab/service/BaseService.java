package com.jarvis.ab.service;

import java.util.List;

/**
 * Created by jarvis on 11/02/2017.
 */
public interface BaseService<T> {

    public List<T> search(String condition);

    public void add(T t);
    public void delete(String condition);
}
