package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.ModateBimenameDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ModateBimename.
 */
public interface ModateBimenameService {

    /**
     * Save a modateBimename.
     *
     * @param modateBimenameDTO the entity to save
     * @return the persisted entity
     */
    ModateBimenameDTO save(ModateBimenameDTO modateBimenameDTO);

    /**
     * Get all the modateBimenames.
     *
     * @return the list of entities
     */
    List<ModateBimenameDTO> findAll();


    /**
     * Get the "id" modateBimename.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ModateBimenameDTO> findOne(Long id);

    /**
     * Delete the "id" modateBimename.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
