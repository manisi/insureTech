package ir.insurance.startup.service;

import ir.insurance.startup.service.dto.AdamKhesaratSalesMaliDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AdamKhesaratSalesMali.
 */
public interface AdamKhesaratSalesMaliService {

    /**
     * Save a adamKhesaratSalesMali.
     *
     * @param adamKhesaratSalesMaliDTO the entity to save
     * @return the persisted entity
     */
    AdamKhesaratSalesMaliDTO save(AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO);

    /**
     * Get all the adamKhesaratSalesMalis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AdamKhesaratSalesMaliDTO> findAll(Pageable pageable);


    /**
     * Get the "id" adamKhesaratSalesMali.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdamKhesaratSalesMaliDTO> findOne(Long id);

    /**
     * Delete the "id" adamKhesaratSalesMali.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
