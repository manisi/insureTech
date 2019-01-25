package ir.insurance.startup.repository;

import ir.insurance.startup.domain.TipKhodro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipKhodro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipKhodroRepository extends JpaRepository<TipKhodro, Long> {

}
