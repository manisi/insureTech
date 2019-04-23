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

import ir.insurance.startup.domain.MoredEstefadeSales;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.MoredEstefadeSalesRepository;
import ir.insurance.startup.service.dto.MoredEstefadeSalesCriteria;
import ir.insurance.startup.service.dto.MoredEstefadeSalesDTO;
import ir.insurance.startup.service.mapper.MoredEstefadeSalesMapper;

/**
 * Service for executing complex queries for MoredEstefadeSales entities in the database.
 * The main input is a {@link MoredEstefadeSalesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MoredEstefadeSalesDTO} or a {@link Page} of {@link MoredEstefadeSalesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MoredEstefadeSalesQueryService extends QueryService<MoredEstefadeSales> {

    private final Logger log = LoggerFactory.getLogger(MoredEstefadeSalesQueryService.class);

    private final MoredEstefadeSalesRepository moredEstefadeSalesRepository;

    private final MoredEstefadeSalesMapper moredEstefadeSalesMapper;

    public MoredEstefadeSalesQueryService(MoredEstefadeSalesRepository moredEstefadeSalesRepository, MoredEstefadeSalesMapper moredEstefadeSalesMapper) {
        this.moredEstefadeSalesRepository = moredEstefadeSalesRepository;
        this.moredEstefadeSalesMapper = moredEstefadeSalesMapper;
    }

    /**
     * Return a {@link List} of {@link MoredEstefadeSalesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MoredEstefadeSalesDTO> findByCriteria(MoredEstefadeSalesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MoredEstefadeSales> specification = createSpecification(criteria);
        return moredEstefadeSalesMapper.toDto(moredEstefadeSalesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MoredEstefadeSalesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MoredEstefadeSalesDTO> findByCriteria(MoredEstefadeSalesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MoredEstefadeSales> specification = createSpecification(criteria);
        return moredEstefadeSalesRepository.findAll(specification, page)
            .map(moredEstefadeSalesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MoredEstefadeSalesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MoredEstefadeSales> specification = createSpecification(criteria);
        return moredEstefadeSalesRepository.count(specification);
    }

    /**
     * Function to convert MoredEstefadeSalesCriteria to a {@link Specification}
     */
    private Specification<MoredEstefadeSales> createSpecification(MoredEstefadeSalesCriteria criteria) {
        Specification<MoredEstefadeSales> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MoredEstefadeSales_.id));
            }
            if (criteria.getDarsadEzafeNerkh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDarsadEzafeNerkh(), MoredEstefadeSales_.darsadEzafeNerkh));
            }
            if (criteria.getAzTarikh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzTarikh(), MoredEstefadeSales_.azTarikh));
            }
            if (criteria.getTaTarikh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTaTarikh(), MoredEstefadeSales_.taTarikh));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), MoredEstefadeSales_.faal));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(MoredEstefadeSales_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
            if (criteria.getSherkatBimeId() != null) {
                specification = specification.and(buildSpecification(criteria.getSherkatBimeId(),
                    root -> root.join(MoredEstefadeSales_.sherkatBime, JoinType.LEFT).get(SherkatBime_.id)));
            }
        }
        return specification;
    }
}
