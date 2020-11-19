package ua.dnipro.epam.homework.service.impl;

import ua.dnipro.epam.homework.dao.RoleDAO;
import ua.dnipro.epam.homework.dao.impl.RoleDAOImpl;
import ua.dnipro.epam.homework.entity.Role;
import ua.dnipro.epam.homework.exception.RoleNotFoundException;
import ua.dnipro.epam.homework.exception.UserNotFoundException;
import ua.dnipro.epam.homework.service.RoleService;

public class RoleServiceImpl implements RoleService {
    RoleDAO roleDAO = new RoleDAOImpl();

    @Override
    public Role findByRole(String role) {
        return roleDAO.findByRole(role).orElseThrow(RoleNotFoundException::new);
    }
}
