package ir.insurance.startup.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import ir.insurance.startup.domain.KohnegiBadane;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.KohnegiBadaneRepository;
import ir.insurance.startup.service.dto.KohnegiBadaneCriteria;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;
import ir.insurance.startup.service.mapper.KohnegiBadaneMapper;

/**
 * Service for executing complex queries for KohnegiBadane entities in the database.
 * The main input is a {@link KohnegiBadaneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link KohnegiBadaneDTO} or a {@link Page} of {@link KohnegiBadaneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class KohnegiBadaneQueryService extends QueryService<KohnegiBadane> {

    private final Logger log = LoggerFactory.getLogger(KohnegiBadaneQueryService.class);

    private final KohnegiBadaneRepository kohnegiBadaneRepository;

    private final KohnegiBadaneMapper kohnegiBadaneMapper;

    public KohnegiBadaneQueryService(KohnegiBadaneRepository kohnegiBadaneRepository, KohnegiBadaneMapper kohnegiBadaneMapper) {
        this.kohnegiBadaneRepository = kohnegiBadaneRepository;
        this.kohnegiBadaneMapper = kohnegiBadaneMapper;
    }

    /**
     * Return a {@link List} of {@link KohnegiBadaneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<KohnegiBadaneDTO> findByCriteria(KohnegiBadaneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<KohnegiBadane> specification = createSpecification(criteria);
        return kohnegiBadaneMapper.toDto(kohnegiBadaneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link KohnegiBadaneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<KohnegiBadaneDTO> findByCriteria(KohnegiBadaneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<KohnegiBadane> specification = createSpecification(criteria);
        return kohnegiBadaneRepository.findAll(specification, page)
            .map(kohnegiBadaneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(KohnegiBadaneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<KohnegiBadane> specification = createSpecification(criteria);
        return kohnegiBadaneRepository.count(specification);
    }

    /**
     * Function to convert KohnegiBadaneCriteria to a {@link Specification}
     */
    private Specification<KohnegiBadane> createSpecification(KohnegiBadaneCriteria criteria) {
        Specification<KohnegiBadane> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), KohnegiBadane_.id));
            }
            if (criteria.getDarsadHarSal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDarsadHarSal(), KohnegiBadane_.darsadHarSal));
            }
            if (criteria.getMaxDarsad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDarsad(), KohnegiBadane_.maxDarsad));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), KohnegiBadane_.faal));
            }
        }
        return specification;
    }
}
