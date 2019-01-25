package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.MohasebeSalesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MohasebeSales.
 */
public interface MohasebeSalesService {

    /**
     * Save a mohasebeSales.
     *
     * @param mohasebeSalesDTO the entity to save
     * @return the persisted entity
     */
    MohasebeSalesDTO save(MohasebeSalesDTO mohasebeSalesDTO);

    /**
     * Get all the mohasebeSales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MohasebeSalesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mohasebeSales.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MohasebeSalesDTO> findOne(Long id);

    /**
     * Delete the "id" mohasebeSales.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
