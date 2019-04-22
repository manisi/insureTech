package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SalesMazadCalc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SalesMazadCalc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesMazadCalcRepository extends JpaRepository<SalesMazadCalc, Long>, JpaSpecificationExecutor<SalesMazadCalc> {

}
