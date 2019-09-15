package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.GroupOperationsDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GroupOperationsData.
 */
public interface GroupOperationsDataService {

    /**
     * Save a groupOperationsData.
     *
     * @param groupOperationsDataDTO the entity to save
     * @return the persisted entity
     */
    GroupOperationsDataDTO save(GroupOperationsDataDTO groupOperationsDataDTO);

    /**
     * Get all the groupOperationsData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GroupOperationsDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" groupOperationsData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GroupOperationsDataDTO> findOne(Long id);

    /**
     * Delete the "id" groupOperationsData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
