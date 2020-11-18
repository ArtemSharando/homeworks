package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.SubjectDAO;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class SubjectDAOImpl implements SubjectDAO {

    private final Connection connection;

    public SubjectDAOImpl() {
        this.connection = DBManager.getConnection();
    }

    @Override
    public Subject findById(Long id) {
        Subject subject = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SUBJECT_WHERE_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                subject = new Subject(
                        resultSet.getLong("id"),
                        resultSet.getString("name"));
            }
            return subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List <Subject> findAll(String lang) {
        List<Subject> subjects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            if (lang.equals("en")){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_SUBJECT);
                while (resultSet.next()) {
                    subjects.add(new Subject(
                            resultSet.getLong("id"),
                            resultSet.getString("name")
                    ));
                }
                return subjects;
            }
            else {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_SUBJECT_RU);
                while (resultSet.next()) {
                    subjects.add(new Subject(
                            resultSet.getLong("id"),
                            resultSet.getString("name_ru")
                    ));
                }
                return subjects;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject create(Subject entity) {
        return null;
    }

    @Override
    public Subject update(Subject entity, Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }


    @Override
    public Subject findByName(String name) {
        Subject subject = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SUBJECT_NAME);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                subject = new Subject(
                        resultSet.getLong("id"),
                        resultSet.getString("name"));
            }
            return subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
