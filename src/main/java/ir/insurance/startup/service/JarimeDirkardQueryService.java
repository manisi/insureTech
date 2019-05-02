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

import ir.insurance.startup.domain.JarimeDirkard;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.JarimeDirkardRepository;
import ir.insurance.startup.service.dto.JarimeDirkardCriteria;
import ir.insurance.startup.service.dto.JarimeDirkardDTO;
import ir.insurance.startup.service.mapper.JarimeDirkardMapper;

/**
 * Service for executing complex queries for JarimeDirkard entities in the database.
 * The main input is a {@link JarimeDirkardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JarimeDirkardDTO} or a {@link Page} of {@link JarimeDirkardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JarimeDirkardQueryService extends QueryService<JarimeDirkard> {

    private final Logger log = LoggerFactory.getLogger(JarimeDirkardQueryService.class);

    private final JarimeDirkardRepository jarimeDirkardRepository;

    private final JarimeDirkardMapper jarimeDirkardMapper;

    public JarimeDirkardQueryService(JarimeDirkardRepository jarimeDirkardRepository, JarimeDirkardMapper jarimeDirkardMapper) {
        this.jarimeDirkardRepository = jarimeDirkardRepository;
        this.jarimeDirkardMapper = jarimeDirkardMapper;
    }

    /**
     * Return a {@link List} of {@link JarimeDirkardDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JarimeDirkardDTO> findByCriteria(JarimeDirkardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JarimeDirkard> specification = createSpecification(criteria);
        return jarimeDirkardMapper.toDto(jarimeDirkardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JarimeDirkardDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JarimeDirkardDTO> findByCriteria(JarimeDirkardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JarimeDirkard> specification = createSpecification(criteria);
        return jarimeDirkardRepository.findAll(specification, page)
            .map(jarimeDirkardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JarimeDirkardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JarimeDirkard> specification = createSpecification(criteria);
        return jarimeDirkardRepository.count(specification);
    }

    /**
     * Function to convert JarimeDirkardCriteria to a {@link Specification}
     */
    private Specification<JarimeDirkard> createSpecification(JarimeDirkardCriteria criteria) {
        Specification<JarimeDirkard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JarimeDirkard_.id));
            }
            if (criteria.getRoozane() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoozane(), JarimeDirkard_.roozane));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), JarimeDirkard_.faal));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(JarimeDirkard_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
            if (criteria.getSherkatBimeId() != null) {
                specification = specification.and(buildSpecification(criteria.getSherkatBimeId(),
                    root -> root.join(JarimeDirkard_.sherkatBime, JoinType.LEFT).get(SherkatBime_.id)));
            }
        }
        return specification;
    }
}
