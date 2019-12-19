package ir.insurance.startup.repository;

import ir.insurance.startup.domain.AdamKhesaratSalesMali;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdamKhesaratSalesMali entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdamKhesaratSalesMaliRepository extends JpaRepository<AdamKhesaratSalesMali, Long> {

}
