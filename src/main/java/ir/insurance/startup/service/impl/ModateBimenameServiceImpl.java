package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.ModateBimenameService;
import ir.insurance.startup.domain.ModateBimename;
import ir.insurance.startup.repository.ModateBimenameRepository;
import ir.insurance.startup.service.dto.ModateBimenameDTO;
import ir.insurance.startup.service.mapper.ModateBimenameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ModateBimename.
 */
@Service
@Transactional
public class ModateBimenameServiceImpl implements ModateBimenameService {

    private final Logger log = LoggerFactory.getLogger(ModateBimenameServiceImpl.class);

    private final ModateBimenameRepository modateBimenameRepository;

    private final ModateBimenameMapper modateBimenameMapper;

    public ModateBimenameServiceImpl(ModateBimenameRepository modateBimenameRepository, ModateBimenameMapper modateBimenameMapper) {
        this.modateBimenameRepository = modateBimenameRepository;
        this.modateBimenameMapper = modateBimenameMapper;
    }

    /**
     * Save a modateBimename.
     *
     * @param modateBimenameDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ModateBimenameDTO save(ModateBimenameDTO modateBimenameDTO) {
        log.debug("Request to save ModateBimename : {}", modateBimenameDTO);
        ModateBimename modateBimename = modateBimenameMapper.toEntity(modateBimenameDTO);
        modateBimename = modateBimenameRepository.save(modateBimename);
        return modateBimenameMapper.toDto(modateBimename);
    }

    /**
     * Get all the modateBimenames.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ModateBimenameDTO> findAll() {
        log.debug("Request to get all ModateBimenames");
        return modateBimenameRepository.findAll().stream()
            .map(modateBimenameMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one modateBimename by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ModateBimenameDTO> findOne(Long id) {
        log.debug("Request to get ModateBimename : {}", id);
        return modateBimenameRepository.findById(id)
            .map(modateBimenameMapper::toDto);
    }

    /**
     * Delete the modateBimename by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ModateBimename : {}", id);
        modateBimenameRepository.deleteById(id);
    }

    @Override
    public List<ModateBimename> findAllforlookup() {
        return modateBimenameRepository.findAll();
    }
}
