package ir.insurance.startup.repository;

import ir.insurance.startup.domain.ModateBimename;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ModateBimename entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModateBimenameRepository extends JpaRepository<ModateBimename, Long> {

}
