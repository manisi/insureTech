package ir.insurance.startup.repository;

import ir.insurance.startup.domain.SabegheKhesarat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SabegheKhesarat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SabegheKhesaratRepository extends JpaRepository<SabegheKhesarat, Long> {

}
