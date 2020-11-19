package ua.dnipro.epam.homework.dao.impl;


import ua.dnipro.epam.homework.dao.UserDAO;
import ua.dnipro.epam.homework.entity.Role;
import ua.dnipro.epam.homework.entity.User;
import ua.dnipro.epam.homework.exception.DBException;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.service.RoleService;
import ua.dnipro.epam.homework.service.impl.RoleServiceImpl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    RoleService roleService = new RoleServiceImpl();

    public UserDAOImpl() {
        this.connection = DBManager.getConnection();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_USERNAME);
            preparedStatement.setString(1, username.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getBoolean("status"),
                        new Role(resultSet.getLong("id_roles"),
                                resultSet.getString("role")));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void ban (Long id, Boolean status){
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        User user = userDAOImpl.findById(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_STATUS);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getBoolean("status"),
                        new Role(resultSet.getLong("id_roles"),
                                resultSet.getString("role")));
            }
            return user;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }


    @Override
    public List<User> findAll(String sql) {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getBoolean("status"),
                        new Role(resultSet.getLong("id"),
                                resultSet.getString("role"))));
            }
            return users;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_LOGIN);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setBoolean(5, true);
            preparedStatement.setLong(6, roleService.findByRole("user").getId_roles());
            preparedStatement.executeUpdate();
            user.setId(getLastInsertId());
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public User update(User entity, Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_WHERE_ID);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getSurname());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            entity.setId(getLastInsertId());
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return entity;
    }

    @Override
    public void deleteById(Long aLong) {

    }


    protected Long getLastInsertId() {
        String sql = "select last_insert_id() as Id";
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
