package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.SalesMazadCalcService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.SalesMazadCalcDTO;
import ir.insurance.startup.service.dto.SalesMazadCalcCriteria;
import ir.insurance.startup.service.SalesMazadCalcQueryService;
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
 * REST controller for managing SalesMazadCalc.
 */
@RestController
@RequestMapping("/api")
public class SalesMazadCalcResource {

    private final Logger log = LoggerFactory.getLogger(SalesMazadCalcResource.class);

    private static final String ENTITY_NAME = "salesMazadCalc";

    private final SalesMazadCalcService salesMazadCalcService;

    private final SalesMazadCalcQueryService salesMazadCalcQueryService;

    public SalesMazadCalcResource(SalesMazadCalcService salesMazadCalcService, SalesMazadCalcQueryService salesMazadCalcQueryService) {
        this.salesMazadCalcService = salesMazadCalcService;
        this.salesMazadCalcQueryService = salesMazadCalcQueryService;
    }

    /**
     * POST  /sales-mazad-calcs : Create a new salesMazadCalc.
     *
     * @param salesMazadCalcDTO the salesMazadCalcDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new salesMazadCalcDTO, or with status 400 (Bad Request) if the salesMazadCalc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sales-mazad-calcs")
    public ResponseEntity<SalesMazadCalcDTO> createSalesMazadCalc(@Valid @RequestBody SalesMazadCalcDTO salesMazadCalcDTO) throws URISyntaxException {
        log.debug("REST request to save SalesMazadCalc : {}", salesMazadCalcDTO);
        if (salesMazadCalcDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesMazadCalc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesMazadCalcDTO result = salesMazadCalcService.save(salesMazadCalcDTO);
        return ResponseEntity.created(new URI("/api/sales-mazad-calcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sales-mazad-calcs : Updates an existing salesMazadCalc.
     *
     * @param salesMazadCalcDTO the salesMazadCalcDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated salesMazadCalcDTO,
     * or with status 400 (Bad Request) if the salesMazadCalcDTO is not valid,
     * or with status 500 (Internal Server Error) if the salesMazadCalcDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sales-mazad-calcs")
    public ResponseEntity<SalesMazadCalcDTO> updateSalesMazadCalc(@Valid @RequestBody SalesMazadCalcDTO salesMazadCalcDTO) throws URISyntaxException {
        log.debug("REST request to update SalesMazadCalc : {}", salesMazadCalcDTO);
        if (salesMazadCalcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalesMazadCalcDTO result = salesMazadCalcService.save(salesMazadCalcDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, salesMazadCalcDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sales-mazad-calcs : get all the salesMazadCalcs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of salesMazadCalcs in body
     */
    @GetMapping("/sales-mazad-calcs")
    public ResponseEntity<List<SalesMazadCalcDTO>> getAllSalesMazadCalcs(SalesMazadCalcCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SalesMazadCalcs by criteria: {}", criteria);
        Page<SalesMazadCalcDTO> page = salesMazadCalcQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sales-mazad-calcs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /sales-mazad-calcs/count : count all the salesMazadCalcs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/sales-mazad-calcs/count")
    public ResponseEntity<Long> countSalesMazadCalcs(SalesMazadCalcCriteria criteria) {
        log.debug("REST request to count SalesMazadCalcs by criteria: {}", criteria);
        return ResponseEntity.ok().body(salesMazadCalcQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /sales-mazad-calcs/:id : get the "id" salesMazadCalc.
     *
     * @param id the id of the salesMazadCalcDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the salesMazadCalcDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sales-mazad-calcs/{id}")
    public ResponseEntity<SalesMazadCalcDTO> getSalesMazadCalc(@PathVariable Long id) {
        log.debug("REST request to get SalesMazadCalc : {}", id);
        Optional<SalesMazadCalcDTO> salesMazadCalcDTO = salesMazadCalcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesMazadCalcDTO);
    }

    /**
     * DELETE  /sales-mazad-calcs/:id : delete the "id" salesMazadCalc.
     *
     * @param id the id of the salesMazadCalcDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sales-mazad-calcs/{id}")
    public ResponseEntity<Void> deleteSalesMazadCalc(@PathVariable Long id) {
        log.debug("REST request to delete SalesMazadCalc : {}", id);
        salesMazadCalcService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
