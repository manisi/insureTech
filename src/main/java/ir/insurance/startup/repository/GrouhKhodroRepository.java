package ir.insurance.startup.repository;

import ir.insurance.startup.domain.GrouhKhodro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GrouhKhodro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrouhKhodroRepository extends JpaRepository<GrouhKhodro, Long>, JpaSpecificationExecutor<GrouhKhodro> {

}
