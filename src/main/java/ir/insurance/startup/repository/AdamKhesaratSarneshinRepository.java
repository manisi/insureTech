package ir.insurance.startup.repository;

import ir.insurance.startup.domain.AdamKhesaratSarneshin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdamKhesaratSarneshin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdamKhesaratSarneshinRepository extends JpaRepository<AdamKhesaratSarneshin, Long>, JpaSpecificationExecutor<AdamKhesaratSarneshin> {

}
