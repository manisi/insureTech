package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.TakhfifTavafoghiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TakhfifTavafoghi.
 */
public interface TakhfifTavafoghiService {

    /**
     * Save a takhfifTavafoghi.
     *
     * @param takhfifTavafoghiDTO the entity to save
     * @return the persisted entity
     */
    TakhfifTavafoghiDTO save(TakhfifTavafoghiDTO takhfifTavafoghiDTO);

    /**
     * Get all the takhfifTavafoghis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TakhfifTavafoghiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" takhfifTavafoghi.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TakhfifTavafoghiDTO> findOne(Long id);

    /**
     * Delete the "id" takhfifTavafoghi.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
