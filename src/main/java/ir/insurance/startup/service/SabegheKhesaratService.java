package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.SabegheKhesaratDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SabegheKhesarat.
 */
public interface SabegheKhesaratService {

    /**
     * Save a sabegheKhesarat.
     *
     * @param sabegheKhesaratDTO the entity to save
     * @return the persisted entity
     */
    SabegheKhesaratDTO save(SabegheKhesaratDTO sabegheKhesaratDTO);

    /**
     * Get all the sabegheKhesarats.
     *
     * @return the list of entities
     */
    List<SabegheKhesaratDTO> findAll();


    /**
     * Get the "id" sabegheKhesarat.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SabegheKhesaratDTO> findOne(Long id);

    /**
     * Delete the "id" sabegheKhesarat.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
