package com.example.adsonline.repository;

import com.example.adsonline.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Класс - репозиторий пользователя
 */


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findUsersById(Integer user_id);

    Optional<Users> findUsersByUserName(String username);

    boolean existsUsersByUserName(String userName);
}