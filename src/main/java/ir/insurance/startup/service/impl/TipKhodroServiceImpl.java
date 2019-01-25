package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.TipKhodroService;
import ir.insurance.startup.domain.TipKhodro;
import ir.insurance.startup.repository.TipKhodroRepository;
import ir.insurance.startup.service.dto.TipKhodroDTO;
import ir.insurance.startup.service.mapper.TipKhodroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TipKhodro.
 */
@Service
@Transactional
public class TipKhodroServiceImpl implements TipKhodroService {

    private final Logger log = LoggerFactory.getLogger(TipKhodroServiceImpl.class);

    private final TipKhodroRepository tipKhodroRepository;

    private final TipKhodroMapper tipKhodroMapper;

    public TipKhodroServiceImpl(TipKhodroRepository tipKhodroRepository, TipKhodroMapper tipKhodroMapper) {
        this.tipKhodroRepository = tipKhodroRepository;
        this.tipKhodroMapper = tipKhodroMapper;
    }

    /**
     * Save a tipKhodro.
     *
     * @param tipKhodroDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TipKhodroDTO save(TipKhodroDTO tipKhodroDTO) {
        log.debug("Request to save TipKhodro : {}", tipKhodroDTO);

        TipKhodro tipKhodro = tipKhodroMapper.toEntity(tipKhodroDTO);
        tipKhodro = tipKhodroRepository.save(tipKhodro);
        return tipKhodroMapper.toDto(tipKhodro);
    }

    /**
     * Get all the tipKhodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipKhodroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipKhodros");
        return tipKhodroRepository.findAll(pageable)
            .map(tipKhodroMapper::toDto);
    }


    /**
     * Get one tipKhodro by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipKhodroDTO> findOne(Long id) {
        log.debug("Request to get TipKhodro : {}", id);
        return tipKhodroRepository.findById(id)
            .map(tipKhodroMapper::toDto);
    }

    /**
     * Delete the tipKhodro by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipKhodro : {}", id);
        tipKhodroRepository.deleteById(id);
    }
}
