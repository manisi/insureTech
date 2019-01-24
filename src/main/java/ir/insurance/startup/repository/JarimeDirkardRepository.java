package ir.insurance.startup.repository;

import ir.insurance.startup.domain.JarimeDirkard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JarimeDirkard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JarimeDirkardRepository extends JpaRepository<JarimeDirkard, Long>, JpaSpecificationExecutor<JarimeDirkard> {

}
