package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.MohasebeSalesService;
import ir.insurance.startup.domain.MohasebeSales;
import ir.insurance.startup.repository.MohasebeSalesRepository;
import ir.insurance.startup.service.dto.MohasebeSalesDTO;
import ir.insurance.startup.service.mapper.MohasebeSalesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MohasebeSales.
 */
@Service
@Transactional
public class MohasebeSalesServiceImpl implements MohasebeSalesService {

    private final Logger log = LoggerFactory.getLogger(MohasebeSalesServiceImpl.class);

    private final MohasebeSalesRepository mohasebeSalesRepository;

    private final MohasebeSalesMapper mohasebeSalesMapper;

    public MohasebeSalesServiceImpl(MohasebeSalesRepository mohasebeSalesRepository, MohasebeSalesMapper mohasebeSalesMapper) {
        this.mohasebeSalesRepository = mohasebeSalesRepository;
        this.mohasebeSalesMapper = mohasebeSalesMapper;
    }

    /**
     * Save a mohasebeSales.
     *
     * @param mohasebeSalesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MohasebeSalesDTO save(MohasebeSalesDTO mohasebeSalesDTO) {
        log.debug("Request to save MohasebeSales : {}", mohasebeSalesDTO);
        MohasebeSales mohasebeSales = mohasebeSalesMapper.toEntity(mohasebeSalesDTO);
        mohasebeSales = mohasebeSalesRepository.save(mohasebeSales);
        return mohasebeSalesMapper.toDto(mohasebeSales);
    }

    /**
     * Get all the mohasebeSales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MohasebeSalesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MohasebeSales");
        return mohasebeSalesRepository.findAll(pageable)
            .map(mohasebeSalesMapper::toDto);
    }


    /**
     * Get one mohasebeSales by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MohasebeSalesDTO> findOne(Long id) {
        log.debug("Request to get MohasebeSales : {}", id);
        return mohasebeSalesRepository.findById(id)
            .map(mohasebeSalesMapper::toDto);
    }

    /**
     * Delete the mohasebeSales by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MohasebeSales : {}", id);        mohasebeSalesRepository.deleteById(id);
    }
}
