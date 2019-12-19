package ir.insurance.startup.repository;

import ir.insurance.startup.domain.KhesaratSalesMali;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KhesaratSalesMali entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhesaratSalesMaliRepository extends JpaRepository<KhesaratSalesMali, Long> {

}
