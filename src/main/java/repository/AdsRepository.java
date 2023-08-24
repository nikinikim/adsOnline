package repository;

import entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

    List<Ads> findByPrice(int price);

    List<Ads> findByTitle(String tittle);


}