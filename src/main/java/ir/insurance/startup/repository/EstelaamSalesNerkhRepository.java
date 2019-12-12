package ir.insurance.startup.repository;
import ir.insurance.startup.domain.EstelaamSalesNerkh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstelaamSalesNerkh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstelaamSalesNerkhRepository extends JpaRepository<EstelaamSalesNerkh, Long> {

}
