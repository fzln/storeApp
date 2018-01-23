package repository;

import entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description, String> {
    Description findBySerialNumber(String s);
}
