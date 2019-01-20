package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.MohasebeBadaneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MohasebeBadane.
 */
public interface MohasebeBadaneService {

    /**
     * Save a mohasebeBadane.
     *
     * @param mohasebeBadaneDTO the entity to save
     * @return the persisted entity
     */
    MohasebeBadaneDTO save(MohasebeBadaneDTO mohasebeBadaneDTO);

    /**
     * Get all the mohasebeBadanes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MohasebeBadaneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mohasebeBadane.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MohasebeBadaneDTO> findOne(Long id);

    /**
     * Delete the "id" mohasebeBadane.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
