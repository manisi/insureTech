package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.SalesJaniCalcDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SalesJaniCalc.
 */
public interface SalesJaniCalcService {

    /**
     * Save a salesJaniCalc.
     *
     * @param salesJaniCalcDTO the entity to save
     * @return the persisted entity
     */
    SalesJaniCalcDTO save(SalesJaniCalcDTO salesJaniCalcDTO);

    /**
     * Get all the salesJaniCalcs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SalesJaniCalcDTO> findAll(Pageable pageable);


    /**
     * Get the "id" salesJaniCalc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SalesJaniCalcDTO> findOne(Long id);

    /**
     * Delete the "id" salesJaniCalc.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
