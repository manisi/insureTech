package ir.insurance.startup.repository;

import ir.insurance.startup.domain.GroupOperationsData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GroupOperationsData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupOperationsDataRepository extends JpaRepository<GroupOperationsData, Long>, JpaSpecificationExecutor<GroupOperationsData> {

}
