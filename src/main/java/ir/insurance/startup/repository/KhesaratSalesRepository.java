package ir.insurance.startup.repository;

import ir.insurance.startup.domain.KhesaratSales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the KhesaratSales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhesaratSalesRepository extends JpaRepository<KhesaratSales, Long>, JpaSpecificationExecutor<KhesaratSales> {

//    @Query("select ks.id as id, ks.sabeghe.name as name   from KhesaratSales ks")
    @Query("select ks from KhesaratSales ks")
    List<KhesaratSales> findAllforLookup();
}
