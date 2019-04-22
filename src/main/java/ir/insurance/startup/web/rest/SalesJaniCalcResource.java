package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.SalesJaniCalcService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.SalesJaniCalcDTO;
import ir.insurance.startup.service.dto.SalesJaniCalcCriteria;
import ir.insurance.startup.service.SalesJaniCalcQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SalesJaniCalc.
 */
@RestController
@RequestMapping("/api")
public class SalesJaniCalcResource {

    private final Logger log = LoggerFactory.getLogger(SalesJaniCalcResource.class);

    private static final String ENTITY_NAME = "salesJaniCalc";

    private final SalesJaniCalcService salesJaniCalcService;

    private final SalesJaniCalcQueryService salesJaniCalcQueryService;

    public SalesJaniCalcResource(SalesJaniCalcService salesJaniCalcService, SalesJaniCalcQueryService salesJaniCalcQueryService) {
        this.salesJaniCalcService = salesJaniCalcService;
        this.salesJaniCalcQueryService = salesJaniCalcQueryService;
    }

    /**
     * POST  /sales-jani-calcs : Create a new salesJaniCalc.
     *
     * @param salesJaniCalcDTO the salesJaniCalcDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new salesJaniCalcDTO, or with status 400 (Bad Request) if the salesJaniCalc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sales-jani-calcs")
    public ResponseEntity<SalesJaniCalcDTO> createSalesJaniCalc(@Valid @RequestBody SalesJaniCalcDTO salesJaniCalcDTO) throws URISyntaxException {
        log.debug("REST request to save SalesJaniCalc : {}", salesJaniCalcDTO);
        if (salesJaniCalcDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesJaniCalc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesJaniCalcDTO result = salesJaniCalcService.save(salesJaniCalcDTO);
        return ResponseEntity.created(new URI("/api/sales-jani-calcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sales-jani-calcs : Updates an existing salesJaniCalc.
     *
     * @param salesJaniCalcDTO the salesJaniCalcDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated salesJaniCalcDTO,
     * or with status 400 (Bad Request) if the salesJaniCalcDTO is not valid,
     * or with status 500 (Internal Server Error) if the salesJaniCalcDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sales-jani-calcs")
    public ResponseEntity<SalesJaniCalcDTO> updateSalesJaniCalc(@Valid @RequestBody SalesJaniCalcDTO salesJaniCalcDTO) throws URISyntaxException {
        log.debug("REST request to update SalesJaniCalc : {}", salesJaniCalcDTO);
        if (salesJaniCalcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalesJaniCalcDTO result = salesJaniCalcService.save(salesJaniCalcDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, salesJaniCalcDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sales-jani-calcs : get all the salesJaniCalcs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of salesJaniCalcs in body
     */
    @GetMapping("/sales-jani-calcs")
    public ResponseEntity<List<SalesJaniCalcDTO>> getAllSalesJaniCalcs(SalesJaniCalcCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SalesJaniCalcs by criteria: {}", criteria);
        Page<SalesJaniCalcDTO> page = salesJaniCalcQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sales-jani-calcs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /sales-jani-calcs/count : count all the salesJaniCalcs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/sales-jani-calcs/count")
    public ResponseEntity<Long> countSalesJaniCalcs(SalesJaniCalcCriteria criteria) {
        log.debug("REST request to count SalesJaniCalcs by criteria: {}", criteria);
        return ResponseEntity.ok().body(salesJaniCalcQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /sales-jani-calcs/:id : get the "id" salesJaniCalc.
     *
     * @param id the id of the salesJaniCalcDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the salesJaniCalcDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sales-jani-calcs/{id}")
    public ResponseEntity<SalesJaniCalcDTO> getSalesJaniCalc(@PathVariable Long id) {
        log.debug("REST request to get SalesJaniCalc : {}", id);
        Optional<SalesJaniCalcDTO> salesJaniCalcDTO = salesJaniCalcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesJaniCalcDTO);
    }

    /**
     * DELETE  /sales-jani-calcs/:id : delete the "id" salesJaniCalc.
     *
     * @param id the id of the salesJaniCalcDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sales-jani-calcs/{id}")
    public ResponseEntity<Void> deleteSalesJaniCalc(@PathVariable Long id) {
        log.debug("REST request to delete SalesJaniCalc : {}", id);
        salesJaniCalcService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
