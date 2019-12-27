package ir.insurance.startup.service;

import ir.insurance.startup.domain.OnvanKhodro;
import ir.insurance.startup.service.dto.OnvanKhodroDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OnvanKhodro.
 */
public interface OnvanKhodroService {

    /**
     * Save a onvanKhodro.
     *
     * @param onvanKhodroDTO the entity to save
     * @return the persisted entity
     */
    OnvanKhodroDTO save(OnvanKhodroDTO onvanKhodroDTO);

    /**
     * Get all the onvanKhodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OnvanKhodroDTO> findAll(Pageable pageable);


    /**
     * Get the "id" onvanKhodro.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OnvanKhodroDTO> findOne(Long id);

    /**
     * Delete the "id" onvanKhodro.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<OnvanKhodro> findAllforlookup();
}
