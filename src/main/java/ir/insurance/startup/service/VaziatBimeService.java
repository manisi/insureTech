package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.VaziatBimeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing VaziatBime.
 */
public interface VaziatBimeService {

    /**
     * Save a vaziatBime.
     *
     * @param vaziatBimeDTO the entity to save
     * @return the persisted entity
     */
    VaziatBimeDTO save(VaziatBimeDTO vaziatBimeDTO);

    /**
     * Get all the vaziatBimes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VaziatBimeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vaziatBime.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VaziatBimeDTO> findOne(Long id);

    /**
     * Delete the "id" vaziatBime.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
