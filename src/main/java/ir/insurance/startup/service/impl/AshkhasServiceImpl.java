package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.AshkhasService;
import ir.insurance.startup.domain.Ashkhas;
import ir.insurance.startup.repository.AshkhasRepository;
import ir.insurance.startup.service.dto.AshkhasDTO;
import ir.insurance.startup.service.mapper.AshkhasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Ashkhas.
 */
@Service
@Transactional
public class AshkhasServiceImpl implements AshkhasService {

    private final Logger log = LoggerFactory.getLogger(AshkhasServiceImpl.class);

    private final AshkhasRepository ashkhasRepository;

    private final AshkhasMapper ashkhasMapper;

    public AshkhasServiceImpl(AshkhasRepository ashkhasRepository, AshkhasMapper ashkhasMapper) {
        this.ashkhasRepository = ashkhasRepository;
        this.ashkhasMapper = ashkhasMapper;
    }

    /**
     * Save a ashkhas.
     *
     * @param ashkhasDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AshkhasDTO save(AshkhasDTO ashkhasDTO) {
        log.debug("Request to save Ashkhas : {}", ashkhasDTO);

        Ashkhas ashkhas = ashkhasMapper.toEntity(ashkhasDTO);
        ashkhas = ashkhasRepository.save(ashkhas);
        return ashkhasMapper.toDto(ashkhas);
    }

    /**
     * Get all the ashkhas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AshkhasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ashkhas");
        return ashkhasRepository.findAll(pageable)
            .map(ashkhasMapper::toDto);
    }


    /**
     * Get one ashkhas by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AshkhasDTO> findOne(Long id) {
        log.debug("Request to get Ashkhas : {}", id);
        return ashkhasRepository.findById(id)
            .map(ashkhasMapper::toDto);
    }

    /**
     * Delete the ashkhas by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ashkhas : {}", id);
        ashkhasRepository.deleteById(id);
    }
}
