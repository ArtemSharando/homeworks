package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.QuestionDAO;
import ua.dnipro.epam.homework.exception.DBException;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.entity.Question;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class QuestionDAOImpl implements QuestionDAO {

    private static final Logger LOG = Logger.getLogger(QuestionDAOImpl.class);

    private final Connection connection;

    AnswerDAOImpl answerDAOImpl = new AnswerDAOImpl();

    public QuestionDAOImpl() {
        this.connection = DBManager.getConnection();
    }

    @Override
    public Question findById(Long aLong) {
        return null;
    }

    @Override
    public List<Question> findAll(String lang) {
        return null;
    }


    @Override
    public Question create(Question entity) {
        return null;
    }

    public Question create(Question entity, Long testId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUESTION_CREATE);
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setInt(2, entity.getCounterQuestion());
            preparedStatement.setInt(3, entity.getNumberOfAnswer());
            preparedStatement.setLong(4, testId);
            preparedStatement.executeUpdate();
            entity.setId(getLastInsertId());
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return entity;
    }
    @Override
    public Question update(Question entity, Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void deleteByTestId(Long testId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_QUESTION_WHERE_TEST_ID)) {
            preparedStatement.setLong(1, testId);
            answerDAOImpl.deleteByTestId(testId);
            preparedStatement.executeUpdate();
            LOG.info("del");
        } catch (SQLException e) {
            LOG.error(e);
            throw new DBException(e);
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
            throw new DBException(e);
        }
    }

    public List <Question> findAllTestId(Long testId) {
        List <Question> questions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_QUESTION_WHERE_TEST_ID);
            preparedStatement.setLong(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                questions.add( new Question(
                        resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getInt("counter_question"),
                        resultSet.getInt("number_of_answer"),
                        resultSet.getLong("test_id")));
            }
            return questions;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
