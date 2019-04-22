package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SalesSarneshinCalc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SalesSarneshinCalc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesSarneshinCalcRepository extends JpaRepository<SalesSarneshinCalc, Long>, JpaSpecificationExecutor<SalesSarneshinCalc> {

}
