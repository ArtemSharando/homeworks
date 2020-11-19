package ua.dnipro.epam.homework.dao.impl;

import ua.dnipro.epam.homework.dao.RoleDAO;
import ua.dnipro.epam.homework.entity.Role;
import ua.dnipro.epam.homework.exception.DBException;
import ua.dnipro.epam.homework.exception.Messages;
import ua.dnipro.epam.homework.exception.RoleNotFoundException;
import ua.dnipro.epam.homework.manager.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static ua.dnipro.epam.homework.manager.QuerySQL.SELECT_FROM_ROLES_BY_ROLE;

public class RoleDAOImpl implements RoleDAO {

    private final Connection connection;

    public RoleDAOImpl() {
        this.connection = DBManager.getConnection();
    }

    @Override
    public Optional<Role> findByRole(String role) {
        Role roles = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ROLES_BY_ROLE);
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                roles = new Role(
                        resultSet.getLong("id_roles"),
                        resultSet.getString("role"));
            }
            if(roles == null){
                throw new RoleNotFoundException();
            }
            return Optional.ofNullable(roles);
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
        }
    }

}
