package ir.insurance.startup.service;

import ir.insurance.startup.domain.MoredEstefadeSales;
import ir.insurance.startup.repository.MoredEstefadeSalesRepository;
import ir.insurance.startup.service.dto.MoredEstefadeSalesDTO;
import ir.insurance.startup.service.mapper.MoredEstefadeSalesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing MoredEstefadeSales.
 */
@Service
@Transactional
public class MoredEstefadeSalesService {

    private final Logger log = LoggerFactory.getLogger(MoredEstefadeSalesService.class);

    private final MoredEstefadeSalesRepository moredEstefadeSalesRepository;

    private final MoredEstefadeSalesMapper moredEstefadeSalesMapper;

    public MoredEstefadeSalesService(MoredEstefadeSalesRepository moredEstefadeSalesRepository, MoredEstefadeSalesMapper moredEstefadeSalesMapper) {
        this.moredEstefadeSalesRepository = moredEstefadeSalesRepository;
        this.moredEstefadeSalesMapper = moredEstefadeSalesMapper;
    }

    /**
     * Save a moredEstefadeSales.
     *
     * @param moredEstefadeSalesDTO the entity to save
     * @return the persisted entity
     */
    public MoredEstefadeSalesDTO save(MoredEstefadeSalesDTO moredEstefadeSalesDTO) {
        log.debug("Request to save MoredEstefadeSales : {}", moredEstefadeSalesDTO);
        MoredEstefadeSales moredEstefadeSales = moredEstefadeSalesMapper.toEntity(moredEstefadeSalesDTO);
        moredEstefadeSales = moredEstefadeSalesRepository.save(moredEstefadeSales);
        return moredEstefadeSalesMapper.toDto(moredEstefadeSales);
    }

    /**
     * Get all the moredEstefadeSales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MoredEstefadeSalesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MoredEstefadeSales");
        return moredEstefadeSalesRepository.findAll(pageable)
            .map(moredEstefadeSalesMapper::toDto);
    }


    /**
     * Get one moredEstefadeSales by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<MoredEstefadeSalesDTO> findOne(Long id) {
        log.debug("Request to get MoredEstefadeSales : {}", id);
        return moredEstefadeSalesRepository.findById(id)
            .map(moredEstefadeSalesMapper::toDto);
    }

    /**
     * Delete the moredEstefadeSales by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MoredEstefadeSales : {}", id);
        moredEstefadeSalesRepository.deleteById(id);
    }

    public List<MoredEstefadeSales> findAllforlookup() {
        return  moredEstefadeSalesRepository.findAll();
    }
}
