package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.TakhfifTavafoghiService;
import ir.insurance.startup.domain.TakhfifTavafoghi;
import ir.insurance.startup.repository.TakhfifTavafoghiRepository;
import ir.insurance.startup.service.dto.TakhfifTavafoghiDTO;
import ir.insurance.startup.service.mapper.TakhfifTavafoghiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TakhfifTavafoghi.
 */
@Service
@Transactional
public class TakhfifTavafoghiServiceImpl implements TakhfifTavafoghiService {

    private final Logger log = LoggerFactory.getLogger(TakhfifTavafoghiServiceImpl.class);

    private final TakhfifTavafoghiRepository takhfifTavafoghiRepository;

    private final TakhfifTavafoghiMapper takhfifTavafoghiMapper;

    public TakhfifTavafoghiServiceImpl(TakhfifTavafoghiRepository takhfifTavafoghiRepository, TakhfifTavafoghiMapper takhfifTavafoghiMapper) {
        this.takhfifTavafoghiRepository = takhfifTavafoghiRepository;
        this.takhfifTavafoghiMapper = takhfifTavafoghiMapper;
    }

    /**
     * Save a takhfifTavafoghi.
     *
     * @param takhfifTavafoghiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TakhfifTavafoghiDTO save(TakhfifTavafoghiDTO takhfifTavafoghiDTO) {
        log.debug("Request to save TakhfifTavafoghi : {}", takhfifTavafoghiDTO);
        TakhfifTavafoghi takhfifTavafoghi = takhfifTavafoghiMapper.toEntity(takhfifTavafoghiDTO);
        takhfifTavafoghi = takhfifTavafoghiRepository.save(takhfifTavafoghi);
        return takhfifTavafoghiMapper.toDto(takhfifTavafoghi);
    }

    /**
     * Get all the takhfifTavafoghis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TakhfifTavafoghiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TakhfifTavafoghis");
        return takhfifTavafoghiRepository.findAll(pageable)
            .map(takhfifTavafoghiMapper::toDto);
    }


    /**
     * Get one takhfifTavafoghi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TakhfifTavafoghiDTO> findOne(Long id) {
        log.debug("Request to get TakhfifTavafoghi : {}", id);
        return takhfifTavafoghiRepository.findById(id)
            .map(takhfifTavafoghiMapper::toDto);
    }

    /**
     * Delete the takhfifTavafoghi by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TakhfifTavafoghi : {}", id);
        takhfifTavafoghiRepository.deleteById(id);
    }
}
