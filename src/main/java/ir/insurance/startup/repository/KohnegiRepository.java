package ir.insurance.startup.repository;

import ir.insurance.startup.domain.Kohnegi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Kohnegi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KohnegiRepository extends JpaRepository<Kohnegi, Long>, JpaSpecificationExecutor<Kohnegi> {

}
