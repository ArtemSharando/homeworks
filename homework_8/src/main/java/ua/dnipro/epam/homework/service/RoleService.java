package ua.dnipro.epam.homework.service;

import ua.dnipro.epam.homework.entity.Role;

import java.util.Optional;

public interface RoleService {
    public Role findByRole(String role);
}
