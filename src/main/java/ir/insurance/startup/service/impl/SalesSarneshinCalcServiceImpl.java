package ir.insurance.startup.service.impl;

import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.service.SalesSarneshinCalcService;
import ir.insurance.startup.domain.SalesSarneshinCalc;
import ir.insurance.startup.repository.SalesSarneshinCalcRepository;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;
import ir.insurance.startup.service.mapper.SalesSarneshinCalcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SalesSarneshinCalc.
 */
@Service
@Transactional
public class SalesSarneshinCalcServiceImpl implements SalesSarneshinCalcService {

    private final Logger log = LoggerFactory.getLogger(SalesSarneshinCalcServiceImpl.class);

    private final SalesSarneshinCalcRepository salesSarneshinCalcRepository;

    private final SalesSarneshinCalcMapper salesSarneshinCalcMapper;

    public SalesSarneshinCalcServiceImpl(SalesSarneshinCalcRepository salesSarneshinCalcRepository, SalesSarneshinCalcMapper salesSarneshinCalcMapper) {
        this.salesSarneshinCalcRepository = salesSarneshinCalcRepository;
        this.salesSarneshinCalcMapper = salesSarneshinCalcMapper;
    }

    /**
     * Save a salesSarneshinCalc.
     *
     * @param salesSarneshinCalcDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SalesSarneshinCalcDTO save(SalesSarneshinCalcDTO salesSarneshinCalcDTO) {
        log.debug("Request to save SalesSarneshinCalc : {}", salesSarneshinCalcDTO);
        SalesSarneshinCalc salesSarneshinCalc = salesSarneshinCalcMapper.toEntity(salesSarneshinCalcDTO);
        salesSarneshinCalc = salesSarneshinCalcRepository.save(salesSarneshinCalc);
        return salesSarneshinCalcMapper.toDto(salesSarneshinCalc);
    }

    /**
     * Get all the salesSarneshinCalcs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesSarneshinCalcDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesSarneshinCalcs");
        return salesSarneshinCalcRepository.findAll(pageable)
            .map(salesSarneshinCalcMapper::toDto);
    }


    /**
     * Get one salesSarneshinCalc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SalesSarneshinCalcDTO> findOne(Long id) {
        log.debug("Request to get SalesSarneshinCalc : {}", id);
        return salesSarneshinCalcRepository.findById(id)
            .map(salesSarneshinCalcMapper::toDto);
    }

    /**
     * Delete the salesSarneshinCalc by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesSarneshinCalc : {}", id);        salesSarneshinCalcRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesSarneshinCalc> findAllByGrouhKhodroId(Long id) {
        return salesSarneshinCalcRepository.findAllByGrouhKhodroId(id);
    }
}
