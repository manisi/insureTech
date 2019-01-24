package ir.insurance.startup.repository;

import ir.insurance.startup.domain.KhesaratSales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KhesaratSales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhesaratSalesRepository extends JpaRepository<KhesaratSales, Long>, JpaSpecificationExecutor<KhesaratSales> {

}
