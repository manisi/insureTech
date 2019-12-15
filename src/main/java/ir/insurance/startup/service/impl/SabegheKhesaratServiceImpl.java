package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.SabegheKhesaratService;
import ir.insurance.startup.domain.SabegheKhesarat;
import ir.insurance.startup.repository.SabegheKhesaratRepository;
import ir.insurance.startup.service.dto.SabegheKhesaratDTO;
import ir.insurance.startup.service.mapper.SabegheKhesaratMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing SabegheKhesarat.
 */
@Service
@Transactional
public class SabegheKhesaratServiceImpl implements SabegheKhesaratService {

    private final Logger log = LoggerFactory.getLogger(SabegheKhesaratServiceImpl.class);

    private final SabegheKhesaratRepository sabegheKhesaratRepository;

    private final SabegheKhesaratMapper sabegheKhesaratMapper;

    public SabegheKhesaratServiceImpl(SabegheKhesaratRepository sabegheKhesaratRepository, SabegheKhesaratMapper sabegheKhesaratMapper) {
        this.sabegheKhesaratRepository = sabegheKhesaratRepository;
        this.sabegheKhesaratMapper = sabegheKhesaratMapper;
    }

    /**
     * Save a sabegheKhesarat.
     *
     * @param sabegheKhesaratDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SabegheKhesaratDTO save(SabegheKhesaratDTO sabegheKhesaratDTO) {
        log.debug("Request to save SabegheKhesarat : {}", sabegheKhesaratDTO);
        SabegheKhesarat sabegheKhesarat = sabegheKhesaratMapper.toEntity(sabegheKhesaratDTO);
        sabegheKhesarat = sabegheKhesaratRepository.save(sabegheKhesarat);
        return sabegheKhesaratMapper.toDto(sabegheKhesarat);
    }

    /**
     * Get all the sabegheKhesarats.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SabegheKhesaratDTO> findAll() {
        log.debug("Request to get all SabegheKhesarats");
        return sabegheKhesaratRepository.findAll().stream()
            .map(sabegheKhesaratMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one sabegheKhesarat by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SabegheKhesaratDTO> findOne(Long id) {
        log.debug("Request to get SabegheKhesarat : {}", id);
        return sabegheKhesaratRepository.findById(id)
            .map(sabegheKhesaratMapper::toDto);
    }

    /**
     * Delete the sabegheKhesarat by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SabegheKhesarat : {}", id);
        sabegheKhesaratRepository.deleteById(id);
    }
}
