package sbt.jpa_data;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateCrudRepository extends CrudRepository<EntityRate, Long> {
    Optional<EntityRate> findByDate(String date);
    void delete(EntityRate entity);
    EntityRate save(EntityRate rate);
}