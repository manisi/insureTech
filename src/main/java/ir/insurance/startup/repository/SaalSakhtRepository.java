package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SaalSakht;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SaalSakht entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaalSakhtRepository extends JpaRepository<SaalSakht, Long> {

}
