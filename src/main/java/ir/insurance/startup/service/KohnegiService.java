package ir.insurance.startup.service;

import ir.insurance.startup.domain.Kohnegi;
import ir.insurance.startup.repository.KohnegiRepository;
import ir.insurance.startup.service.dto.KohnegiDTO;
import ir.insurance.startup.service.mapper.KohnegiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Kohnegi.
 */
@Service
@Transactional
public class KohnegiService {

    private final Logger log = LoggerFactory.getLogger(KohnegiService.class);

    private final KohnegiRepository kohnegiRepository;

    private final KohnegiMapper kohnegiMapper;

    public KohnegiService(KohnegiRepository kohnegiRepository, KohnegiMapper kohnegiMapper) {
        this.kohnegiRepository = kohnegiRepository;
        this.kohnegiMapper = kohnegiMapper;
    }

    /**
     * Save a kohnegi.
     *
     * @param kohnegiDTO the entity to save
     * @return the persisted entity
     */
    public KohnegiDTO save(KohnegiDTO kohnegiDTO) {
        log.debug("Request to save Kohnegi : {}", kohnegiDTO);

        Kohnegi kohnegi = kohnegiMapper.toEntity(kohnegiDTO);
        kohnegi = kohnegiRepository.save(kohnegi);
        return kohnegiMapper.toDto(kohnegi);
    }

    /**
     * Get all the kohnegis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<KohnegiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Kohnegis");
        return kohnegiRepository.findAll(pageable)
            .map(kohnegiMapper::toDto);
    }


    /**
     * Get one kohnegi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<KohnegiDTO> findOne(Long id) {
        log.debug("Request to get Kohnegi : {}", id);
        return kohnegiRepository.findById(id)
            .map(kohnegiMapper::toDto);
    }

    /**
     * Delete the kohnegi by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Kohnegi : {}", id);
        kohnegiRepository.deleteById(id);
    }
}
