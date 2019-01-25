package ir.insurance.startup.repository;

import ir.insurance.startup.domain.Sabeghe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sabeghe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SabegheRepository extends JpaRepository<Sabeghe, Long>, JpaSpecificationExecutor<Sabeghe> {

}
