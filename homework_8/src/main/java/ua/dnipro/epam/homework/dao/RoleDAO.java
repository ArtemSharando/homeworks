package ua.dnipro.epam.homework.dao;

import ua.dnipro.epam.homework.entity.Role;

import java.util.Optional;

public interface RoleDAO {

    Optional<Role> findByRole (String role);

}
