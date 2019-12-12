package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.EstelaamSalesNerkhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ir.insurance.startup.domain.EstelaamSalesNerkh}.
 */
public interface EstelaamSalesNerkhService {

    /**
     * Save a estelaamSalesNerkh.
     *
     * @param estelaamSalesNerkhDTO the entity to save.
     * @return the persisted entity.
     */
    EstelaamSalesNerkhDTO save(EstelaamSalesNerkhDTO estelaamSalesNerkhDTO);

    /**
     * Get all the estelaamSalesNerkhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EstelaamSalesNerkhDTO> findAll(Pageable pageable);


    /**
     * Get the "id" estelaamSalesNerkh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstelaamSalesNerkhDTO> findOne(Long id);

    /**
     * Delete the "id" estelaamSalesNerkh.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
