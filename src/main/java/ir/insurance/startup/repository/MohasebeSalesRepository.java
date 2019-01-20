package ir.insurance.startup.repository;

import ir.insurance.startup.domain.MohasebeSales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MohasebeSales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MohasebeSalesRepository extends JpaRepository<MohasebeSales, Long> {

}
