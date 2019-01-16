package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.AshkhasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Ashkhas.
 */
public interface AshkhasService {

    /**
     * Save a ashkhas.
     *
     * @param ashkhasDTO the entity to save
     * @return the persisted entity
     */
    AshkhasDTO save(AshkhasDTO ashkhasDTO);

    /**
     * Get all the ashkhas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AshkhasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ashkhas.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AshkhasDTO> findOne(Long id);

    /**
     * Delete the "id" ashkhas.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
