package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.Role;

import java.util.Optional;

public interface RoleDAOI {

    Optional<Role> findByRole (String role);

}
