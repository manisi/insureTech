package ir.insurance.startup.repository;

import ir.insurance.startup.domain.Khodro;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Khodro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhodroRepository extends JpaRepository<Khodro, Long> {

}
