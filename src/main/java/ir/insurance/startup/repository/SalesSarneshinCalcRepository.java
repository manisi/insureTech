package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.domain.SalesSarneshinCalc;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SalesSarneshinCalc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesSarneshinCalcRepository extends JpaRepository<SalesSarneshinCalc, Long>, JpaSpecificationExecutor<SalesSarneshinCalc> {

    //    @Query("select salesJaniCalc from SalesJaniCalc salesJaniCalc left join fetch salesJaniCalc.grouhKhodro where salesJaniCalc.grouhKhodro.id =:id")
    @Query("select salesSarneshinCalc from SalesSarneshinCalc salesSarneshinCalc  where salesSarneshinCalc.grouhKhodro.id =:id")
    List<SalesSarneshinCalc> findAllByGrouhKhodroId(@Param("id") Long id);

}
