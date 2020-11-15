package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.AnswerDAO;
import ua.dnipro.epam.homework.manager.DBManager;
import ua.dnipro.epam.homework.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.dnipro.epam.homework.manager.QuerySQL.*;

public class AnswerDAOImpl implements AnswerDAO {

    private static final Logger LOG = Logger.getLogger(AnswerDAOImpl.class);

    private final Connection connection;

    public AnswerDAOImpl() {
        this.connection = DBManager.getConnection();
    }

    @Override
    public Answer findById(Long aLong) {
        return null;
    }

    @Override
    public List<Answer> findAll(String lang) {
        return null;
    }

    @Override
    public Answer create(Answer entity) {
        return null;
    }

    public Answer create(Answer entity, Long qId, Long tId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ANSWER_CREATE);
            preparedStatement.setString(1, entity.getVariant());
            preparedStatement.setInt(2, entity.getCounterAnswer());
            preparedStatement.setBoolean(3, entity.isCorrectAnswer());
            preparedStatement.setLong(4, qId);
            preparedStatement.setLong(5, tId);
            preparedStatement.executeUpdate();
            entity.setId(getLastInsertId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Answer update(Answer entity, Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    public void deleteByTestId(Long testId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_ANSWER_WHERE_TEST_ID)) {
            preparedStatement.setLong(1, testId);
            preparedStatement.executeUpdate();
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


    public List<Answer> findByQuestionId(Long testId) {
        List <Answer> answers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ANSWER_WHERE_QUESTION_ID);
            preparedStatement.setLong(1, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answers.add( new Answer(
                        resultSet.getLong("id"),
                        resultSet.getString("variant"),
                        resultSet.getInt("counter_answer"),
                        resultSet.getBoolean("correct_answer"),
                        resultSet.getLong("question_id"),
                        resultSet.getLong("test_id")));
            }
            return answers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
