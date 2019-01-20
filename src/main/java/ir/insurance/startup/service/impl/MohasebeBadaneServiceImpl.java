package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.MohasebeBadaneService;
import ir.insurance.startup.domain.MohasebeBadane;
import ir.insurance.startup.repository.MohasebeBadaneRepository;
import ir.insurance.startup.service.dto.MohasebeBadaneDTO;
import ir.insurance.startup.service.mapper.MohasebeBadaneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MohasebeBadane.
 */
@Service
@Transactional
public class MohasebeBadaneServiceImpl implements MohasebeBadaneService {

    private final Logger log = LoggerFactory.getLogger(MohasebeBadaneServiceImpl.class);

    private final MohasebeBadaneRepository mohasebeBadaneRepository;

    private final MohasebeBadaneMapper mohasebeBadaneMapper;

    public MohasebeBadaneServiceImpl(MohasebeBadaneRepository mohasebeBadaneRepository, MohasebeBadaneMapper mohasebeBadaneMapper) {
        this.mohasebeBadaneRepository = mohasebeBadaneRepository;
        this.mohasebeBadaneMapper = mohasebeBadaneMapper;
    }

    /**
     * Save a mohasebeBadane.
     *
     * @param mohasebeBadaneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MohasebeBadaneDTO save(MohasebeBadaneDTO mohasebeBadaneDTO) {
        log.debug("Request to save MohasebeBadane : {}", mohasebeBadaneDTO);

        MohasebeBadane mohasebeBadane = mohasebeBadaneMapper.toEntity(mohasebeBadaneDTO);
        mohasebeBadane = mohasebeBadaneRepository.save(mohasebeBadane);
        return mohasebeBadaneMapper.toDto(mohasebeBadane);
    }

    /**
     * Get all the mohasebeBadanes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MohasebeBadaneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MohasebeBadanes");
        return mohasebeBadaneRepository.findAll(pageable)
            .map(mohasebeBadaneMapper::toDto);
    }


    /**
     * Get one mohasebeBadane by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MohasebeBadaneDTO> findOne(Long id) {
        log.debug("Request to get MohasebeBadane : {}", id);
        return mohasebeBadaneRepository.findById(id)
            .map(mohasebeBadaneMapper::toDto);
    }

    /**
     * Delete the mohasebeBadane by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MohasebeBadane : {}", id);
        mohasebeBadaneRepository.deleteById(id);
    }
}
