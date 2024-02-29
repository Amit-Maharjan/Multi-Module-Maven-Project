package com.maharjan.amit.model.dao.impl;

import com.maharjan.amit.db.core.JdbcTemplate;
import com.maharjan.amit.model.User;
import com.maharjan.amit.model.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, rs -> mapData(rs));
    }

    private User mapData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(rs.getDate("created_date"));
        user.setModifiedDate(rs.getDate("modified_date"));
        return user;
    }

    @Override
    public User getById(Integer id) throws SQLException {
        String sql = "select * from user where id=?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rs -> mapData(rs));
    }

    @Override
    public Integer insert(User user) throws SQLException {
        String sql = "INSERT INTO user (`username`, `email`, `password`)\n" +
                "VALUES (?,?,?);";
        return jdbcTemplate.update(sql, new Object[]{
                user.getUsername(), user.getEmail(), user.getPassword()
        });
    }

    @Override
    public Integer update(User user) throws SQLException {
        String sql = "update user " +
                "set username = ?, " +
                "email = ?, " +
                "password = ?, " +
                "modified_date = ? " +
                "where id = ?";
        return jdbcTemplate.update(sql, new Object[]{
                user.getUsername(), user.getEmail(), user.getPassword(), user.getModifiedDate(), user.getId()
        });
    }

    @Override
    public Integer deleteById(Integer id) throws SQLException {
        String sql = "delete from user " +
                "where id = ?";
        return jdbcTemplate.update(sql, new Object[]{ id });
    }

    @Override
    public User login(String username, String password) throws SQLException {
        String sql = "select * from user where username=? and password=?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, rs -> mapData(rs));
    }
}
