package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SalesJaniCalc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SalesJaniCalc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesJaniCalcRepository extends JpaRepository<SalesJaniCalc, Long>, JpaSpecificationExecutor<SalesJaniCalc> {

}
