package com.example.adsonline.repository;

import com.example.adsonline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllUser_Id(Integer user_id);
    Optional<User> findUserByUsername(String username);
}