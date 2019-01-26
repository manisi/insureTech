package ir.insurance.startup.repository;

import ir.insurance.startup.domain.AdamKhesaratBadane;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdamKhesaratBadane entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdamKhesaratBadaneRepository extends JpaRepository<AdamKhesaratBadane, Long>, JpaSpecificationExecutor<AdamKhesaratBadane> {

}
