package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.SherkatBimeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SherkatBime.
 */
public interface SherkatBimeService {

    /**
     * Save a sherkatBime.
     *
     * @param sherkatBimeDTO the entity to save
     * @return the persisted entity
     */
    SherkatBimeDTO save(SherkatBimeDTO sherkatBimeDTO);

    /**
     * Get all the sherkatBimes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SherkatBimeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sherkatBime.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SherkatBimeDTO> findOne(Long id);

    /**
     * Delete the "id" sherkatBime.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
