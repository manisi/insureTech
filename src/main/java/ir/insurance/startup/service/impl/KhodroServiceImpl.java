package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.KhodroService;
import ir.insurance.startup.domain.Khodro;
import ir.insurance.startup.repository.KhodroRepository;
import ir.insurance.startup.service.dto.KhodroDTO;
import ir.insurance.startup.service.mapper.KhodroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Khodro.
 */
@Service
@Transactional
public class KhodroServiceImpl implements KhodroService {

    private final Logger log = LoggerFactory.getLogger(KhodroServiceImpl.class);

    private final KhodroRepository khodroRepository;

    private final KhodroMapper khodroMapper;

    public KhodroServiceImpl(KhodroRepository khodroRepository, KhodroMapper khodroMapper) {
        this.khodroRepository = khodroRepository;
        this.khodroMapper = khodroMapper;
    }

    /**
     * Save a khodro.
     *
     * @param khodroDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public KhodroDTO save(KhodroDTO khodroDTO) {
        log.debug("Request to save Khodro : {}", khodroDTO);

        Khodro khodro = khodroMapper.toEntity(khodroDTO);
        khodro = khodroRepository.save(khodro);
        return khodroMapper.toDto(khodro);
    }

    /**
     * Get all the khodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KhodroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Khodros");
        return khodroRepository.findAll(pageable)
            .map(khodroMapper::toDto);
    }


    /**
     * Get one khodro by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KhodroDTO> findOne(Long id) {
        log.debug("Request to get Khodro : {}", id);
        return khodroRepository.findById(id)
            .map(khodroMapper::toDto);
    }

    /**
     * Delete the khodro by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Khodro : {}", id);
        khodroRepository.deleteById(id);
    }
}
