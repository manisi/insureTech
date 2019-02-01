package ir.insurance.startup.repository;

import ir.insurance.startup.domain.AnvaeKhodro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AnvaeKhodro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnvaeKhodroRepository extends JpaRepository<AnvaeKhodro, Long>, JpaSpecificationExecutor<AnvaeKhodro> {

}
