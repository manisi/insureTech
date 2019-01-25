package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.KhodroDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Khodro.
 */
public interface KhodroService {

    /**
     * Save a khodro.
     *
     * @param khodroDTO the entity to save
     * @return the persisted entity
     */
    KhodroDTO save(KhodroDTO khodroDTO);

    /**
     * Get all the khodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<KhodroDTO> findAll(Pageable pageable);


    /**
     * Get the "id" khodro.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KhodroDTO> findOne(Long id);

    /**
     * Delete the "id" khodro.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
