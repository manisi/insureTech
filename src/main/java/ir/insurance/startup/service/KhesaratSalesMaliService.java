package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.KhesaratSalesMaliDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing KhesaratSalesMali.
 */
public interface KhesaratSalesMaliService {

    /**
     * Save a khesaratSalesMali.
     *
     * @param khesaratSalesMaliDTO the entity to save
     * @return the persisted entity
     */
    KhesaratSalesMaliDTO save(KhesaratSalesMaliDTO khesaratSalesMaliDTO);

    /**
     * Get all the khesaratSalesMalis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<KhesaratSalesMaliDTO> findAll(Pageable pageable);


    /**
     * Get the "id" khesaratSalesMali.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KhesaratSalesMaliDTO> findOne(Long id);

    /**
     * Delete the "id" khesaratSalesMali.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
