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

import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.GrouhKhodroRepository;
import ir.insurance.startup.service.dto.GrouhKhodroCriteria;
import ir.insurance.startup.service.dto.GrouhKhodroDTO;
import ir.insurance.startup.service.mapper.GrouhKhodroMapper;

/**
 * Service for executing complex queries for GrouhKhodro entities in the database.
 * The main input is a {@link GrouhKhodroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GrouhKhodroDTO} or a {@link Page} of {@link GrouhKhodroDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GrouhKhodroQueryService extends QueryService<GrouhKhodro> {

    private final Logger log = LoggerFactory.getLogger(GrouhKhodroQueryService.class);

    private final GrouhKhodroRepository grouhKhodroRepository;

    private final GrouhKhodroMapper grouhKhodroMapper;

    public GrouhKhodroQueryService(GrouhKhodroRepository grouhKhodroRepository, GrouhKhodroMapper grouhKhodroMapper) {
        this.grouhKhodroRepository = grouhKhodroRepository;
        this.grouhKhodroMapper = grouhKhodroMapper;
    }

    /**
     * Return a {@link List} of {@link GrouhKhodroDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GrouhKhodroDTO> findByCriteria(GrouhKhodroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GrouhKhodro> specification = createSpecification(criteria);
        return grouhKhodroMapper.toDto(grouhKhodroRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GrouhKhodroDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GrouhKhodroDTO> findByCriteria(GrouhKhodroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GrouhKhodro> specification = createSpecification(criteria);
        return grouhKhodroRepository.findAll(specification, page)
            .map(grouhKhodroMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GrouhKhodroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<GrouhKhodro> specification = createSpecification(criteria);
        return grouhKhodroRepository.count(specification);
    }

    /**
     * Function to convert GrouhKhodroCriteria to a {@link Specification}
     */
    private Specification<GrouhKhodro> createSpecification(GrouhKhodroCriteria criteria) {
        Specification<GrouhKhodro> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), GrouhKhodro_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), GrouhKhodro_.name));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), GrouhKhodro_.faal));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), GrouhKhodro_.code));
            }
        }
        return specification;
    }
}
