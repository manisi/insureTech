package ir.insurance.startup.repository;

import ir.insurance.startup.domain.KohnegiBadane;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KohnegiBadane entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KohnegiBadaneRepository extends JpaRepository<KohnegiBadane, Long>, JpaSpecificationExecutor<KohnegiBadane> {

}
