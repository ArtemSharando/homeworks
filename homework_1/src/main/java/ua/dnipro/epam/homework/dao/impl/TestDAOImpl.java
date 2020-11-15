package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.TestDAO;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class TestDAOImpl implements TestDAO {

    private static final Logger LOG = Logger.getLogger(TestDAOImpl.class);

    private final Connection connection;

    QuestionDAOImpl questionDAOImpl = new QuestionDAOImpl();

    public TestDAOImpl() {
        this.connection = DBManager.getConnection();
    }

    @Override
    public Test findById(Long id) {
        Test test = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_TEST_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                test = new Test(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("time"),
                        resultSet.getInt("number_of_questions"),
                        resultSet.getLong("id_subject"),
                        resultSet.getLong("id_complexity"));
            }
            return test;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Test> findAll(String lang) {
        List<Test> tests = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_TEST_ALL);
            while (resultSet.next()) {
                tests.add(new Test(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("time"),
                        resultSet.getInt("number_of_questions"),
                        resultSet.getLong("id_subject"),
                        resultSet.getLong("id_complexity")));
            }
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Test create(Test test) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST_CREATE);
            preparedStatement.setString(1, test.getName());
            preparedStatement.setInt(2, test.getTime());
            preparedStatement.setInt(3, test.getNumberOfQuestions());
            preparedStatement.setLong(4, test.getSubjectId());
            preparedStatement.setLong(5, test.getComplexityId());
            preparedStatement.executeUpdate();
            test.setId(getLastInsertId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return test;
    }

    @Override
    public Test update(Test test, Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEST_WHERE_ID)) {
            preparedStatement.setLong(3, id);
            preparedStatement.setString(1, test.getName());
            preparedStatement.setInt(2, test.getTime());
            preparedStatement.executeUpdate();
            LOG.info("del");
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
        return test;
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_TEST_WHERE_ID)) {
            preparedStatement.setLong(1, id);
            questionDAOImpl.deleteByTestId(id);
            preparedStatement.executeUpdate();
            LOG.info("del");
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_TEST_WHERE_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            LOG.info("del");
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
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
            throw new RuntimeException(e);
        }
    }


}
