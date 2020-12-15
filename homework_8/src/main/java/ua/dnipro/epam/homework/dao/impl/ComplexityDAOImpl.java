package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.ComplexityDAO;
import ua.dnipro.epam.homework.exception.DBException;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.entity.Complexity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class ComplexityDAOImpl implements ComplexityDAO {

    private final Connection connection;

    public ComplexityDAOImpl() {
        this.connection = DBManager.getConnection();
    }


    @Override
    public Complexity findById(Long id) {
        Complexity complexity = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_COMPLEXITY_WHERE_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                complexity = new Complexity(
                        resultSet.getLong("id"),
                        resultSet.getString("name"));
            }
            return complexity;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Complexity> findAll(String lang) {
        List<Complexity> complexities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_COMPLEXITY);
            while (resultSet.next()) {
                complexities.add(new Complexity(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }
            return complexities;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Complexity create(Complexity entity) {
        return null;
    }

    @Override
    public Complexity update(Complexity entity, Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }


}
