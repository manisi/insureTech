package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.PoosheshDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Pooshesh.
 */
public interface PoosheshService {

    /**
     * Save a pooshesh.
     *
     * @param poosheshDTO the entity to save
     * @return the persisted entity
     */
    PoosheshDTO save(PoosheshDTO poosheshDTO);

    /**
     * Get all the poosheshes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PoosheshDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pooshesh.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PoosheshDTO> findOne(Long id);

    /**
     * Delete the "id" pooshesh.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
