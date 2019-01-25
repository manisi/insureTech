package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SherkatBime;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SherkatBime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SherkatBimeRepository extends JpaRepository<SherkatBime, Long> {

}
