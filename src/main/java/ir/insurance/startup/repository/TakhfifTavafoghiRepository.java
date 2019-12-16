package ir.insurance.startup.repository;

import ir.insurance.startup.domain.TakhfifTavafoghi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TakhfifTavafoghi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TakhfifTavafoghiRepository extends JpaRepository<TakhfifTavafoghi, Long> {

}
