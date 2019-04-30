package ir.insurance.startup.repository;

import ir.insurance.startup.domain.OnvanKhodro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OnvanKhodro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnvanKhodroRepository extends JpaRepository<OnvanKhodro, Long>, JpaSpecificationExecutor<OnvanKhodro> {

}
