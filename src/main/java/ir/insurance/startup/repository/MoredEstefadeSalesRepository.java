package ir.insurance.startup.repository;

import ir.insurance.startup.domain.MoredEstefadeSales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MoredEstefadeSales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MoredEstefadeSalesRepository extends JpaRepository<MoredEstefadeSales, Long>, JpaSpecificationExecutor<MoredEstefadeSales> {

}
