package bg.softuni.onlineshop.repository;

import bg.softuni.onlineshop.model.entity.IpStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpStatsRepository extends JpaRepository<IpStatsEntity, Long> {
}
