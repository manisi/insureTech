package ir.insurance.startup.repository;

import ir.insurance.startup.domain.NoeSabeghe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NoeSabeghe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoeSabegheRepository extends JpaRepository<NoeSabeghe, Long>, JpaSpecificationExecutor<NoeSabeghe> {

}
