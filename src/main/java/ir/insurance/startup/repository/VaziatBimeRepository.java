package ir.insurance.startup.repository;

import ir.insurance.startup.domain.VaziatBime;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VaziatBime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VaziatBimeRepository extends JpaRepository<VaziatBime, Long> {

}
