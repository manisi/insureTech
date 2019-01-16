package ir.insurance.startup.repository;

import ir.insurance.startup.domain.Pooshesh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pooshesh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoosheshRepository extends JpaRepository<Pooshesh, Long> {

}
