package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SalesJaniCalc;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sun.awt.image.MultiResolutionCachedImage;

import java.util.List;


/**
 * Spring Data  repository for the SalesJaniCalc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesJaniCalcRepository extends JpaRepository<SalesJaniCalc, Long>, JpaSpecificationExecutor<SalesJaniCalc> {


//    @Query("select salesJaniCalc from SalesJaniCalc salesJaniCalc left join fetch salesJaniCalc.grouhKhodro where salesJaniCalc.grouhKhodro.id =:id")
    @Query("select salesJaniCalc from SalesJaniCalc salesJaniCalc  where salesJaniCalc.grouhKhodro.id =:id")
    List<SalesJaniCalc> findAllByGrouhKhodroId(@Param("id") Long id);
}
