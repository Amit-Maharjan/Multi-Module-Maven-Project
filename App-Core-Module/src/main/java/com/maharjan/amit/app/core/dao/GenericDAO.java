package com.maharjan.amit.app.core.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    List<T> getAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    Integer insert(T model) throws SQLException;
    Integer update(T model) throws SQLException;
    Integer deleteById(Integer id) throws SQLException;
}
