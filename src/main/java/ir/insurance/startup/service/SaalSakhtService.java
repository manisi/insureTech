package ir.insurance.startup.service;

import ir.insurance.startup.domain.SaalSakht;
import ir.insurance.startup.service.dto.SaalSakhtDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SaalSakht.
 */
public interface SaalSakhtService {

    /**
     * Save a saalSakht.
     *
     * @param saalSakhtDTO the entity to save
     * @return the persisted entity
     */
    SaalSakhtDTO save(SaalSakhtDTO saalSakhtDTO);

    /**
     * Get all the saalSakhts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SaalSakhtDTO> findAll(Pageable pageable);


    /**
     * Get the "id" saalSakht.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SaalSakhtDTO> findOne(Long id);

    /**
     * Delete the "id" saalSakht.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<SaalSakht> findAllforlookup();
}
