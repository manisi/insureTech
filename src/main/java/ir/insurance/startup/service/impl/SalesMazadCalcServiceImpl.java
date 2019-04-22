package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.SalesMazadCalcService;
import ir.insurance.startup.domain.SalesMazadCalc;
import ir.insurance.startup.repository.SalesMazadCalcRepository;
import ir.insurance.startup.service.dto.SalesMazadCalcDTO;
import ir.insurance.startup.service.mapper.SalesMazadCalcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SalesMazadCalc.
 */
@Service
@Transactional
public class SalesMazadCalcServiceImpl implements SalesMazadCalcService {

    private final Logger log = LoggerFactory.getLogger(SalesMazadCalcServiceImpl.class);

    private final SalesMazadCalcRepository salesMazadCalcRepository;

    private final SalesMazadCalcMapper salesMazadCalcMapper;

    public SalesMazadCalcServiceImpl(SalesMazadCalcRepository salesMazadCalcRepository, SalesMazadCalcMapper salesMazadCalcMapper) {
        this.salesMazadCalcRepository = salesMazadCalcRepository;
        this.salesMazadCalcMapper = salesMazadCalcMapper;
    }

    /**
     * Save a salesMazadCalc.
     *
     * @param salesMazadCalcDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SalesMazadCalcDTO save(SalesMazadCalcDTO salesMazadCalcDTO) {
        log.debug("Request to save SalesMazadCalc : {}", salesMazadCalcDTO);
        SalesMazadCalc salesMazadCalc = salesMazadCalcMapper.toEntity(salesMazadCalcDTO);
        salesMazadCalc = salesMazadCalcRepository.save(salesMazadCalc);
        return salesMazadCalcMapper.toDto(salesMazadCalc);
    }

    /**
     * Get all the salesMazadCalcs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesMazadCalcDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesMazadCalcs");
        return salesMazadCalcRepository.findAll(pageable)
            .map(salesMazadCalcMapper::toDto);
    }


    /**
     * Get one salesMazadCalc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SalesMazadCalcDTO> findOne(Long id) {
        log.debug("Request to get SalesMazadCalc : {}", id);
        return salesMazadCalcRepository.findById(id)
            .map(salesMazadCalcMapper::toDto);
    }

    /**
     * Delete the salesMazadCalc by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesMazadCalc : {}", id);        salesMazadCalcRepository.deleteById(id);
    }
}
