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

import ir.insurance.startup.domain.AdamKhesaratBadane;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.AdamKhesaratBadaneRepository;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneCriteria;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratBadaneMapper;

/**
 * Service for executing complex queries for AdamKhesaratBadane entities in the database.
 * The main input is a {@link AdamKhesaratBadaneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdamKhesaratBadaneDTO} or a {@link Page} of {@link AdamKhesaratBadaneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdamKhesaratBadaneQueryService extends QueryService<AdamKhesaratBadane> {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratBadaneQueryService.class);

    private final AdamKhesaratBadaneRepository adamKhesaratBadaneRepository;

    private final AdamKhesaratBadaneMapper adamKhesaratBadaneMapper;

    public AdamKhesaratBadaneQueryService(AdamKhesaratBadaneRepository adamKhesaratBadaneRepository, AdamKhesaratBadaneMapper adamKhesaratBadaneMapper) {
        this.adamKhesaratBadaneRepository = adamKhesaratBadaneRepository;
        this.adamKhesaratBadaneMapper = adamKhesaratBadaneMapper;
    }

    /**
     * Return a {@link List} of {@link AdamKhesaratBadaneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdamKhesaratBadaneDTO> findByCriteria(AdamKhesaratBadaneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdamKhesaratBadane> specification = createSpecification(criteria);
        return adamKhesaratBadaneMapper.toDto(adamKhesaratBadaneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdamKhesaratBadaneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdamKhesaratBadaneDTO> findByCriteria(AdamKhesaratBadaneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdamKhesaratBadane> specification = createSpecification(criteria);
        return adamKhesaratBadaneRepository.findAll(specification, page)
            .map(adamKhesaratBadaneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdamKhesaratBadaneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdamKhesaratBadane> specification = createSpecification(criteria);
        return adamKhesaratBadaneRepository.count(specification);
    }

    /**
     * Function to convert AdamKhesaratBadaneCriteria to a {@link Specification}
     */
    private Specification<AdamKhesaratBadane> createSpecification(AdamKhesaratBadaneCriteria criteria) {
        Specification<AdamKhesaratBadane> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AdamKhesaratBadane_.id));
            }
            if (criteria.getBadane() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBadane(), AdamKhesaratBadane_.badane));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), AdamKhesaratBadane_.faal));
            }
            if (criteria.getNoeSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoeSabegheId(),
                    root -> root.join(AdamKhesaratBadane_.noeSabeghe, JoinType.LEFT).get(NoeSabeghe_.id)));
            }
            if (criteria.getSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getSabegheId(),
                    root -> root.join(AdamKhesaratBadane_.sabeghe, JoinType.LEFT).get(Sabeghe_.id)));
            }
        }
        return specification;
    }
}
