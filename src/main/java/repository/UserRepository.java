package repository;

import entity.RegisterReq;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
@Service
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(Long id);

    User findByUsername(String username);
}


