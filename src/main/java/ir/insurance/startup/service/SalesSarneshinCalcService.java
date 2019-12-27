package ir.insurance.startup.service;

import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.domain.SalesSarneshinCalc;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SalesSarneshinCalc.
 */
public interface SalesSarneshinCalcService {

    /**
     * Save a salesSarneshinCalc.
     *
     * @param salesSarneshinCalcDTO the entity to save
     * @return the persisted entity
     */
    SalesSarneshinCalcDTO save(SalesSarneshinCalcDTO salesSarneshinCalcDTO);

    /**
     * Get all the salesSarneshinCalcs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SalesSarneshinCalcDTO> findAll(Pageable pageable);


    /**
     * Get the "id" salesSarneshinCalc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SalesSarneshinCalcDTO> findOne(Long id);

    /**
     * Delete the "id" salesSarneshinCalc.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<SalesSarneshinCalc> findAllByGrouhKhodroId(Long id);
}
