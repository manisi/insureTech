package ir.insurance.startup.repository;

import ir.insurance.startup.domain.KhesaratSrneshin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KhesaratSrneshin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhesaratSrneshinRepository extends JpaRepository<KhesaratSrneshin, Long>, JpaSpecificationExecutor<KhesaratSrneshin> {

}
