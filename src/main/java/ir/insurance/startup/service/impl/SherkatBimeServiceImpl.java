package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.SherkatBimeService;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.repository.SherkatBimeRepository;
import ir.insurance.startup.service.dto.SherkatBimeDTO;
import ir.insurance.startup.service.mapper.SherkatBimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SherkatBime.
 */
@Service
@Transactional
public class SherkatBimeServiceImpl implements SherkatBimeService {

    private final Logger log = LoggerFactory.getLogger(SherkatBimeServiceImpl.class);

    private final SherkatBimeRepository sherkatBimeRepository;

    private final SherkatBimeMapper sherkatBimeMapper;

    public SherkatBimeServiceImpl(SherkatBimeRepository sherkatBimeRepository, SherkatBimeMapper sherkatBimeMapper) {
        this.sherkatBimeRepository = sherkatBimeRepository;
        this.sherkatBimeMapper = sherkatBimeMapper;
    }

    /**
     * Save a sherkatBime.
     *
     * @param sherkatBimeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SherkatBimeDTO save(SherkatBimeDTO sherkatBimeDTO) {
        log.debug("Request to save SherkatBime : {}", sherkatBimeDTO);
        SherkatBime sherkatBime = sherkatBimeMapper.toEntity(sherkatBimeDTO);
        sherkatBime = sherkatBimeRepository.save(sherkatBime);
        return sherkatBimeMapper.toDto(sherkatBime);
    }

    /**
     * Get all the sherkatBimes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SherkatBimeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SherkatBimes");
        return sherkatBimeRepository.findAll(pageable)
            .map(sherkatBimeMapper::toDto);
    }


    /**
     * Get one sherkatBime by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SherkatBimeDTO> findOne(Long id) {
        log.debug("Request to get SherkatBime : {}", id);
        return sherkatBimeRepository.findById(id)
            .map(sherkatBimeMapper::toDto);
    }

    /**
     * Delete the sherkatBime by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SherkatBime : {}", id);        sherkatBimeRepository.deleteById(id);
    }

    @Override
    public List<SherkatBime> findAllforlookup() {
        return sherkatBimeRepository.findAll();
    }
}
