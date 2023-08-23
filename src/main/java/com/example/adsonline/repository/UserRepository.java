package com.example.adsonline.repository;

import com.example.adsonline.entity.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
@Service
public interface UserRepository extends JpaRepository<User, Long> {


}


