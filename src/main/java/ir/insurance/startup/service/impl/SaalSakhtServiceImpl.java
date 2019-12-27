package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.SaalSakhtService;
import ir.insurance.startup.domain.SaalSakht;
import ir.insurance.startup.repository.SaalSakhtRepository;
import ir.insurance.startup.service.dto.SaalSakhtDTO;
import ir.insurance.startup.service.mapper.SaalSakhtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SaalSakht.
 */
@Service
@Transactional
public class SaalSakhtServiceImpl implements SaalSakhtService {

    private final Logger log = LoggerFactory.getLogger(SaalSakhtServiceImpl.class);

    private final SaalSakhtRepository saalSakhtRepository;

    private final SaalSakhtMapper saalSakhtMapper;

    public SaalSakhtServiceImpl(SaalSakhtRepository saalSakhtRepository, SaalSakhtMapper saalSakhtMapper) {
        this.saalSakhtRepository = saalSakhtRepository;
        this.saalSakhtMapper = saalSakhtMapper;
    }

    /**
     * Save a saalSakht.
     *
     * @param saalSakhtDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SaalSakhtDTO save(SaalSakhtDTO saalSakhtDTO) {
        log.debug("Request to save SaalSakht : {}", saalSakhtDTO);
        SaalSakht saalSakht = saalSakhtMapper.toEntity(saalSakhtDTO);
        saalSakht = saalSakhtRepository.save(saalSakht);
        return saalSakhtMapper.toDto(saalSakht);
    }

    /**
     * Get all the saalSakhts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SaalSakhtDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SaalSakhts");
        return saalSakhtRepository.findAll(pageable)
            .map(saalSakhtMapper::toDto);
    }


    /**
     * Get one saalSakht by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SaalSakhtDTO> findOne(Long id) {
        log.debug("Request to get SaalSakht : {}", id);
        return saalSakhtRepository.findById(id)
            .map(saalSakhtMapper::toDto);
    }

    /**
     * Delete the saalSakht by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SaalSakht : {}", id);
        saalSakhtRepository.deleteById(id);
    }

    @Override
    public List<SaalSakht> findAllforlookup() {
        return saalSakhtRepository.findAll();
    }
}
