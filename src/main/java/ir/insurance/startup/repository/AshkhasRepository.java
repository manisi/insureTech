package ir.insurance.startup.repository;

import ir.insurance.startup.domain.Ashkhas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ashkhas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AshkhasRepository extends JpaRepository<Ashkhas, Long> {

}
