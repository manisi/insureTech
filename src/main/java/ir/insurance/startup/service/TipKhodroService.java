package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.TipKhodroDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TipKhodro.
 */
public interface TipKhodroService {

    /**
     * Save a tipKhodro.
     *
     * @param tipKhodroDTO the entity to save
     * @return the persisted entity
     */
    TipKhodroDTO save(TipKhodroDTO tipKhodroDTO);

    /**
     * Get all the tipKhodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipKhodroDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipKhodro.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipKhodroDTO> findOne(Long id);

    /**
     * Delete the "id" tipKhodro.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
