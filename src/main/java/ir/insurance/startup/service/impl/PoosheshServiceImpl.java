package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.PoosheshService;
import ir.insurance.startup.domain.Pooshesh;
import ir.insurance.startup.repository.PoosheshRepository;
import ir.insurance.startup.service.dto.PoosheshDTO;
import ir.insurance.startup.service.mapper.PoosheshMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Pooshesh.
 */
@Service
@Transactional
public class PoosheshServiceImpl implements PoosheshService {

    private final Logger log = LoggerFactory.getLogger(PoosheshServiceImpl.class);

    private final PoosheshRepository poosheshRepository;

    private final PoosheshMapper poosheshMapper;

    public PoosheshServiceImpl(PoosheshRepository poosheshRepository, PoosheshMapper poosheshMapper) {
        this.poosheshRepository = poosheshRepository;
        this.poosheshMapper = poosheshMapper;
    }

    /**
     * Save a pooshesh.
     *
     * @param poosheshDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PoosheshDTO save(PoosheshDTO poosheshDTO) {
        log.debug("Request to save Pooshesh : {}", poosheshDTO);

        Pooshesh pooshesh = poosheshMapper.toEntity(poosheshDTO);
        pooshesh = poosheshRepository.save(pooshesh);
        return poosheshMapper.toDto(pooshesh);
    }

    /**
     * Get all the poosheshes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PoosheshDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Poosheshes");
        return poosheshRepository.findAll(pageable)
            .map(poosheshMapper::toDto);
    }


    /**
     * Get one pooshesh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PoosheshDTO> findOne(Long id) {
        log.debug("Request to get Pooshesh : {}", id);
        return poosheshRepository.findById(id)
            .map(poosheshMapper::toDto);
    }

    /**
     * Delete the pooshesh by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pooshesh : {}", id);
        poosheshRepository.deleteById(id);
    }
}
