package repository;

import entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsReposirory extends JpaRepository<Ads, Long> {
}
