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

import ir.insurance.startup.domain.AdamKhesaratSarneshin;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.AdamKhesaratSarneshinRepository;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinCriteria;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratSarneshinMapper;

/**
 * Service for executing complex queries for AdamKhesaratSarneshin entities in the database.
 * The main input is a {@link AdamKhesaratSarneshinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdamKhesaratSarneshinDTO} or a {@link Page} of {@link AdamKhesaratSarneshinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdamKhesaratSarneshinQueryService extends QueryService<AdamKhesaratSarneshin> {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratSarneshinQueryService.class);

    private final AdamKhesaratSarneshinRepository adamKhesaratSarneshinRepository;

    private final AdamKhesaratSarneshinMapper adamKhesaratSarneshinMapper;

    public AdamKhesaratSarneshinQueryService(AdamKhesaratSarneshinRepository adamKhesaratSarneshinRepository, AdamKhesaratSarneshinMapper adamKhesaratSarneshinMapper) {
        this.adamKhesaratSarneshinRepository = adamKhesaratSarneshinRepository;
        this.adamKhesaratSarneshinMapper = adamKhesaratSarneshinMapper;
    }

    /**
     * Return a {@link List} of {@link AdamKhesaratSarneshinDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdamKhesaratSarneshinDTO> findByCriteria(AdamKhesaratSarneshinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdamKhesaratSarneshin> specification = createSpecification(criteria);
        return adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdamKhesaratSarneshinDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdamKhesaratSarneshinDTO> findByCriteria(AdamKhesaratSarneshinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdamKhesaratSarneshin> specification = createSpecification(criteria);
        return adamKhesaratSarneshinRepository.findAll(specification, page)
            .map(adamKhesaratSarneshinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdamKhesaratSarneshinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdamKhesaratSarneshin> specification = createSpecification(criteria);
        return adamKhesaratSarneshinRepository.count(specification);
    }

    /**
     * Function to convert AdamKhesaratSarneshinCriteria to a {@link Specification}
     */
    private Specification<AdamKhesaratSarneshin> createSpecification(AdamKhesaratSarneshinCriteria criteria) {
        Specification<AdamKhesaratSarneshin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AdamKhesaratSarneshin_.id));
            }
            if (criteria.getSarneshin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSarneshin(), AdamKhesaratSarneshin_.sarneshin));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), AdamKhesaratSarneshin_.faal));
            }
            if (criteria.getNoeSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoeSabegheId(),
                    root -> root.join(AdamKhesaratSarneshin_.noeSabeghe, JoinType.LEFT).get(NoeSabeghe_.id)));
            }
            if (criteria.getSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getSabegheId(),
                    root -> root.join(AdamKhesaratSarneshin_.sabeghe, JoinType.LEFT).get(Sabeghe_.id)));
            }
        }
        return specification;
    }
}
