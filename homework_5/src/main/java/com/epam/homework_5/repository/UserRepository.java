package com.epam.homework_5.repository;

import com.epam.homework_5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
