package ir.insurance.startup.repository;

import ir.insurance.startup.domain.AdamKhesarat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdamKhesarat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdamKhesaratRepository extends JpaRepository<AdamKhesarat, Long>, JpaSpecificationExecutor<AdamKhesarat> {

}
