package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.SalesMazadCalcDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SalesMazadCalc.
 */
public interface SalesMazadCalcService {

    /**
     * Save a salesMazadCalc.
     *
     * @param salesMazadCalcDTO the entity to save
     * @return the persisted entity
     */
    SalesMazadCalcDTO save(SalesMazadCalcDTO salesMazadCalcDTO);

    /**
     * Get all the salesMazadCalcs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SalesMazadCalcDTO> findAll(Pageable pageable);


    /**
     * Get the "id" salesMazadCalc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SalesMazadCalcDTO> findOne(Long id);

    /**
     * Delete the "id" salesMazadCalc.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
