package ir.insurance.startup.repository;

import ir.insurance.startup.domain.MohasebeBadane;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MohasebeBadane entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MohasebeBadaneRepository extends JpaRepository<MohasebeBadane, Long> {

}
