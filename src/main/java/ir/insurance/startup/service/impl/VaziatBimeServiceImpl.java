package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.VaziatBimeService;
import ir.insurance.startup.domain.VaziatBime;
import ir.insurance.startup.repository.VaziatBimeRepository;
import ir.insurance.startup.service.dto.VaziatBimeDTO;
import ir.insurance.startup.service.mapper.VaziatBimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing VaziatBime.
 */
@Service
@Transactional
public class VaziatBimeServiceImpl implements VaziatBimeService {

    private final Logger log = LoggerFactory.getLogger(VaziatBimeServiceImpl.class);

    private final VaziatBimeRepository vaziatBimeRepository;

    private final VaziatBimeMapper vaziatBimeMapper;

    public VaziatBimeServiceImpl(VaziatBimeRepository vaziatBimeRepository, VaziatBimeMapper vaziatBimeMapper) {
        this.vaziatBimeRepository = vaziatBimeRepository;
        this.vaziatBimeMapper = vaziatBimeMapper;
    }

    /**
     * Save a vaziatBime.
     *
     * @param vaziatBimeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VaziatBimeDTO save(VaziatBimeDTO vaziatBimeDTO) {
        log.debug("Request to save VaziatBime : {}", vaziatBimeDTO);
        VaziatBime vaziatBime = vaziatBimeMapper.toEntity(vaziatBimeDTO);
        vaziatBime = vaziatBimeRepository.save(vaziatBime);
        return vaziatBimeMapper.toDto(vaziatBime);
    }

    /**
     * Get all the vaziatBimes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VaziatBimeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VaziatBimes");
        return vaziatBimeRepository.findAll(pageable)
            .map(vaziatBimeMapper::toDto);
    }


    /**
     * Get one vaziatBime by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VaziatBimeDTO> findOne(Long id) {
        log.debug("Request to get VaziatBime : {}", id);
        return vaziatBimeRepository.findById(id)
            .map(vaziatBimeMapper::toDto);
    }

    /**
     * Delete the vaziatBime by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VaziatBime : {}", id);
        vaziatBimeRepository.deleteById(id);
    }
}
