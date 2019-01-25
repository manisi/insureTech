package ir.insurance.startup.repository;

import ir.insurance.startup.domain.Nerkh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Nerkh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NerkhRepository extends JpaRepository<Nerkh, Long> {

    @Query(value = "select distinct nerkh from Nerkh nerkh left join fetch nerkh.sherkatBimes",
        countQuery = "select count(distinct nerkh) from Nerkh nerkh")
    Page<Nerkh> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct nerkh from Nerkh nerkh left join fetch nerkh.sherkatBimes")
    List<Nerkh> findAllWithEagerRelationships();

    @Query("select nerkh from Nerkh nerkh left join fetch nerkh.sherkatBimes where nerkh.id =:id")
    Optional<Nerkh> findOneWithEagerRelationships(@Param("id") Long id);

}
