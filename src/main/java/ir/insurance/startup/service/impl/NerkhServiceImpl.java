package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.NerkhService;
import ir.insurance.startup.domain.Nerkh;
import ir.insurance.startup.repository.NerkhRepository;
import ir.insurance.startup.service.dto.NerkhDTO;
import ir.insurance.startup.service.mapper.NerkhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Nerkh.
 */
@Service
@Transactional
public class NerkhServiceImpl implements NerkhService {

    private final Logger log = LoggerFactory.getLogger(NerkhServiceImpl.class);

    private final NerkhRepository nerkhRepository;

    private final NerkhMapper nerkhMapper;

    public NerkhServiceImpl(NerkhRepository nerkhRepository, NerkhMapper nerkhMapper) {
        this.nerkhRepository = nerkhRepository;
        this.nerkhMapper = nerkhMapper;
    }

    /**
     * Save a nerkh.
     *
     * @param nerkhDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NerkhDTO save(NerkhDTO nerkhDTO) {
        log.debug("Request to save Nerkh : {}", nerkhDTO);

        Nerkh nerkh = nerkhMapper.toEntity(nerkhDTO);
        nerkh = nerkhRepository.save(nerkh);
        return nerkhMapper.toDto(nerkh);
    }

    /**
     * Get all the nerkhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NerkhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Nerkhs");
        return nerkhRepository.findAll(pageable)
            .map(nerkhMapper::toDto);
    }

    /**
     * Get all the Nerkh with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NerkhDTO> findAllWithEagerRelationships(Pageable pageable) {
        return nerkhRepository.findAllWithEagerRelationships(pageable).map(nerkhMapper::toDto);
    }
    

    /**
     * Get one nerkh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NerkhDTO> findOne(Long id) {
        log.debug("Request to get Nerkh : {}", id);
        return nerkhRepository.findOneWithEagerRelationships(id)
            .map(nerkhMapper::toDto);
    }

    /**
     * Delete the nerkh by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nerkh : {}", id);
        nerkhRepository.deleteById(id);
    }
}
