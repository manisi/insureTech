package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.SalesJaniCalcService;
import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.repository.SalesJaniCalcRepository;
import ir.insurance.startup.service.dto.SalesJaniCalcDTO;
import ir.insurance.startup.service.mapper.SalesJaniCalcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SalesJaniCalc.
 */
@Service
@Transactional
public class SalesJaniCalcServiceImpl implements SalesJaniCalcService {

    private final Logger log = LoggerFactory.getLogger(SalesJaniCalcServiceImpl.class);

    private final SalesJaniCalcRepository salesJaniCalcRepository;

    private final SalesJaniCalcMapper salesJaniCalcMapper;

    public SalesJaniCalcServiceImpl(SalesJaniCalcRepository salesJaniCalcRepository, SalesJaniCalcMapper salesJaniCalcMapper) {
        this.salesJaniCalcRepository = salesJaniCalcRepository;
        this.salesJaniCalcMapper = salesJaniCalcMapper;
    }

    /**
     * Save a salesJaniCalc.
     *
     * @param salesJaniCalcDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SalesJaniCalcDTO save(SalesJaniCalcDTO salesJaniCalcDTO) {
        log.debug("Request to save SalesJaniCalc : {}", salesJaniCalcDTO);
        SalesJaniCalc salesJaniCalc = salesJaniCalcMapper.toEntity(salesJaniCalcDTO);
        salesJaniCalc = salesJaniCalcRepository.save(salesJaniCalc);
        return salesJaniCalcMapper.toDto(salesJaniCalc);
    }

    /**
     * Get all the salesJaniCalcs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesJaniCalcDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesJaniCalcs");
        return salesJaniCalcRepository.findAll(pageable)
            .map(salesJaniCalcMapper::toDto);
    }


    /**
     * Get one salesJaniCalc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SalesJaniCalcDTO> findOne(Long id) {
        log.debug("Request to get SalesJaniCalc : {}", id);
        return salesJaniCalcRepository.findById(id)
            .map(salesJaniCalcMapper::toDto);
    }

    /**
     * Delete the salesJaniCalc by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesJaniCalc : {}", id);        salesJaniCalcRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesJaniCalc> findAllByGrouhKhodroId(Long id) {
        return salesJaniCalcRepository.findAllByGrouhKhodroId(id);
    }

}
