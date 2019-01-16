package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.NerkhDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Nerkh.
 */
public interface NerkhService {

    /**
     * Save a nerkh.
     *
     * @param nerkhDTO the entity to save
     * @return the persisted entity
     */
    NerkhDTO save(NerkhDTO nerkhDTO);

    /**
     * Get all the nerkhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NerkhDTO> findAll(Pageable pageable);

    /**
     * Get all the Nerkh with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NerkhDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" nerkh.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NerkhDTO> findOne(Long id);

    /**
     * Delete the "id" nerkh.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
