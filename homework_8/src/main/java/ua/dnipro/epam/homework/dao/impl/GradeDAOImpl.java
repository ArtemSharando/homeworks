package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.GradeDAO;
import ua.dnipro.epam.homework.exception.DBException;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.entity.Grade;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class GradeDAOImpl implements GradeDAO {

    private static final Logger LOG = Logger.getLogger(GradeDAOImpl.class);

    private final Connection connection;

    public GradeDAOImpl() {
        this.connection = DBManager.getConnection();
    }


    @Override
    public Grade findById(Long aLong) {
        return null;
    }

    @Override
    public List<Grade> findAll(String lang) {
        return null;
    }

    @Override
    public Grade create(Grade grade) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE_CREATE);
            preparedStatement.setString(1, grade.getResult());
            preparedStatement.setLong(2, grade.getTestId());
            preparedStatement.setLong(3, grade.getUserId());
            preparedStatement.executeUpdate();
            grade.setId(getLastInsertId());
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return grade;
    }

    @Override
    public Grade update(Grade entity, Long aLong) {
        return null;
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

    @Override
    public List <Grade> findByUserId(Long userId) {
        List <Grade> grades = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_GRADE_WHERE_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                grades.add( new Grade(
                        resultSet.getLong("id"),
                        resultSet.getString("result"),
                        resultSet.getLong("test_id"),
                        resultSet.getLong("user_id")));
            }
            return grades;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
