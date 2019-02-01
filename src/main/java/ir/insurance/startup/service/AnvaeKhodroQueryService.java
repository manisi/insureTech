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

import ir.insurance.startup.domain.AnvaeKhodro;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.AnvaeKhodroRepository;
import ir.insurance.startup.service.dto.AnvaeKhodroCriteria;
import ir.insurance.startup.service.dto.AnvaeKhodroDTO;
import ir.insurance.startup.service.mapper.AnvaeKhodroMapper;

/**
 * Service for executing complex queries for AnvaeKhodro entities in the database.
 * The main input is a {@link AnvaeKhodroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnvaeKhodroDTO} or a {@link Page} of {@link AnvaeKhodroDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnvaeKhodroQueryService extends QueryService<AnvaeKhodro> {

    private final Logger log = LoggerFactory.getLogger(AnvaeKhodroQueryService.class);

    private final AnvaeKhodroRepository anvaeKhodroRepository;

    private final AnvaeKhodroMapper anvaeKhodroMapper;

    public AnvaeKhodroQueryService(AnvaeKhodroRepository anvaeKhodroRepository, AnvaeKhodroMapper anvaeKhodroMapper) {
        this.anvaeKhodroRepository = anvaeKhodroRepository;
        this.anvaeKhodroMapper = anvaeKhodroMapper;
    }

    /**
     * Return a {@link List} of {@link AnvaeKhodroDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnvaeKhodroDTO> findByCriteria(AnvaeKhodroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AnvaeKhodro> specification = createSpecification(criteria);
        return anvaeKhodroMapper.toDto(anvaeKhodroRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AnvaeKhodroDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnvaeKhodroDTO> findByCriteria(AnvaeKhodroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AnvaeKhodro> specification = createSpecification(criteria);
        return anvaeKhodroRepository.findAll(specification, page)
            .map(anvaeKhodroMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnvaeKhodroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AnvaeKhodro> specification = createSpecification(criteria);
        return anvaeKhodroRepository.count(specification);
    }

    /**
     * Function to convert AnvaeKhodroCriteria to a {@link Specification}
     */
    private Specification<AnvaeKhodro> createSpecification(AnvaeKhodroCriteria criteria) {
        Specification<AnvaeKhodro> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AnvaeKhodro_.id));
            }
            if (criteria.getGrouhVasile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrouhVasile(), AnvaeKhodro_.grouhVasile));
            }
            if (criteria.getSystemVasile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSystemVasile(), AnvaeKhodro_.systemVasile));
            }
            if (criteria.getOnvan() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOnvan(), AnvaeKhodro_.onvan));
            }
            if (criteria.getTonazh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTonazh(), AnvaeKhodro_.tonazh));
            }
            if (criteria.getTedadSarneshin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTedadSarneshin(), AnvaeKhodro_.tedadSarneshin));
            }
            if (criteria.getTedadSilandre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTedadSilandre(), AnvaeKhodro_.tedadSilandre));
            }
            if (criteria.getDasteBandi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDasteBandi(), AnvaeKhodro_.dasteBandi));
            }
            if (criteria.getSavariType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSavariType(), AnvaeKhodro_.savariType));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(AnvaeKhodro_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
        }
        return specification;
    }
}
