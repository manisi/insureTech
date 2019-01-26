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

import ir.insurance.startup.domain.KhesaratSrneshin;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.KhesaratSrneshinRepository;
import ir.insurance.startup.service.dto.KhesaratSrneshinCriteria;
import ir.insurance.startup.service.dto.KhesaratSrneshinDTO;
import ir.insurance.startup.service.mapper.KhesaratSrneshinMapper;

/**
 * Service for executing complex queries for KhesaratSrneshin entities in the database.
 * The main input is a {@link KhesaratSrneshinCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link KhesaratSrneshinDTO} or a {@link Page} of {@link KhesaratSrneshinDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class KhesaratSrneshinQueryService extends QueryService<KhesaratSrneshin> {

    private final Logger log = LoggerFactory.getLogger(KhesaratSrneshinQueryService.class);

    private final KhesaratSrneshinRepository khesaratSrneshinRepository;

    private final KhesaratSrneshinMapper khesaratSrneshinMapper;

    public KhesaratSrneshinQueryService(KhesaratSrneshinRepository khesaratSrneshinRepository, KhesaratSrneshinMapper khesaratSrneshinMapper) {
        this.khesaratSrneshinRepository = khesaratSrneshinRepository;
        this.khesaratSrneshinMapper = khesaratSrneshinMapper;
    }

    /**
     * Return a {@link List} of {@link KhesaratSrneshinDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<KhesaratSrneshinDTO> findByCriteria(KhesaratSrneshinCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<KhesaratSrneshin> specification = createSpecification(criteria);
        return khesaratSrneshinMapper.toDto(khesaratSrneshinRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link KhesaratSrneshinDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<KhesaratSrneshinDTO> findByCriteria(KhesaratSrneshinCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<KhesaratSrneshin> specification = createSpecification(criteria);
        return khesaratSrneshinRepository.findAll(specification, page)
            .map(khesaratSrneshinMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(KhesaratSrneshinCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<KhesaratSrneshin> specification = createSpecification(criteria);
        return khesaratSrneshinRepository.count(specification);
    }

    /**
     * Function to convert KhesaratSrneshinCriteria to a {@link Specification}
     */
    private Specification<KhesaratSrneshin> createSpecification(KhesaratSrneshinCriteria criteria) {
        Specification<KhesaratSrneshin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), KhesaratSrneshin_.id));
            }
            if (criteria.getNerkhKhesarat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNerkhKhesarat(), KhesaratSrneshin_.nerkhKhesarat));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), KhesaratSrneshin_.faal));
            }
            if (criteria.getNoeSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoeSabegheId(),
                    root -> root.join(KhesaratSrneshin_.noeSabeghe, JoinType.LEFT).get(NoeSabeghe_.id)));
            }
            if (criteria.getSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getSabegheId(),
                    root -> root.join(KhesaratSrneshin_.sabeghe, JoinType.LEFT).get(Sabeghe_.id)));
            }
        }
        return specification;
    }
}
