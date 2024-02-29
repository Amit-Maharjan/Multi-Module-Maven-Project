package com.maharjan.amit.model.dao;

import com.maharjan.amit.app.core.dao.GenericDAO;
import com.maharjan.amit.model.User;

import java.sql.SQLException;

public interface UserDAO extends GenericDAO<User> {
    User login(String username, String password) throws SQLException;
}
