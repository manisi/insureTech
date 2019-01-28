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

import ir.insurance.startup.domain.AdamKhesarat;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.AdamKhesaratRepository;
import ir.insurance.startup.service.dto.AdamKhesaratCriteria;
import ir.insurance.startup.service.dto.AdamKhesaratDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratMapper;

/**
 * Service for executing complex queries for AdamKhesarat entities in the database.
 * The main input is a {@link AdamKhesaratCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdamKhesaratDTO} or a {@link Page} of {@link AdamKhesaratDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdamKhesaratQueryService extends QueryService<AdamKhesarat> {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratQueryService.class);

    private final AdamKhesaratRepository adamKhesaratRepository;

    private final AdamKhesaratMapper adamKhesaratMapper;

    public AdamKhesaratQueryService(AdamKhesaratRepository adamKhesaratRepository, AdamKhesaratMapper adamKhesaratMapper) {
        this.adamKhesaratRepository = adamKhesaratRepository;
        this.adamKhesaratMapper = adamKhesaratMapper;
    }

    /**
     * Return a {@link List} of {@link AdamKhesaratDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdamKhesaratDTO> findByCriteria(AdamKhesaratCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdamKhesarat> specification = createSpecification(criteria);
        return adamKhesaratMapper.toDto(adamKhesaratRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdamKhesaratDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdamKhesaratDTO> findByCriteria(AdamKhesaratCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdamKhesarat> specification = createSpecification(criteria);
        return adamKhesaratRepository.findAll(specification, page)
            .map(adamKhesaratMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdamKhesaratCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdamKhesarat> specification = createSpecification(criteria);
        return adamKhesaratRepository.count(specification);
    }

    /**
     * Function to convert AdamKhesaratCriteria to a {@link Specification}
     */
    private Specification<AdamKhesarat> createSpecification(AdamKhesaratCriteria criteria) {
        Specification<AdamKhesarat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AdamKhesarat_.id));
            }
            if (criteria.getSales() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSales(), AdamKhesarat_.sales));
            }
            if (criteria.getMazad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMazad(), AdamKhesarat_.mazad));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), AdamKhesarat_.faal));
            }
            if (criteria.getSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getSabegheId(),
                    root -> root.join(AdamKhesarat_.sabeghe, JoinType.LEFT).get(Sabeghe_.id)));
            }
            if (criteria.getNoeSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoeSabegheId(),
                    root -> root.join(AdamKhesarat_.noeSabeghe, JoinType.LEFT).get(NoeSabeghe_.id)));
            }
        }
        return specification;
    }
}
