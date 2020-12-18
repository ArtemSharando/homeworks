package com.epam.dao;

import com.epam.entity.User;

import java.util.UUID;

public interface UserDao {
    void create(User user);

    User readById(String uuid);

    User read(String name);

    void delete(String uuid);
}
