package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.SalesSarneshinCalcService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;
import ir.insurance.startup.service.dto.SalesSarneshinCalcCriteria;
import ir.insurance.startup.service.SalesSarneshinCalcQueryService;
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
 * REST controller for managing SalesSarneshinCalc.
 */
@RestController
@RequestMapping("/api")
public class SalesSarneshinCalcResource {

    private final Logger log = LoggerFactory.getLogger(SalesSarneshinCalcResource.class);

    private static final String ENTITY_NAME = "salesSarneshinCalc";

    private final SalesSarneshinCalcService salesSarneshinCalcService;

    private final SalesSarneshinCalcQueryService salesSarneshinCalcQueryService;

    public SalesSarneshinCalcResource(SalesSarneshinCalcService salesSarneshinCalcService, SalesSarneshinCalcQueryService salesSarneshinCalcQueryService) {
        this.salesSarneshinCalcService = salesSarneshinCalcService;
        this.salesSarneshinCalcQueryService = salesSarneshinCalcQueryService;
    }

    /**
     * POST  /sales-sarneshin-calcs : Create a new salesSarneshinCalc.
     *
     * @param salesSarneshinCalcDTO the salesSarneshinCalcDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new salesSarneshinCalcDTO, or with status 400 (Bad Request) if the salesSarneshinCalc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sales-sarneshin-calcs")
    public ResponseEntity<SalesSarneshinCalcDTO> createSalesSarneshinCalc(@Valid @RequestBody SalesSarneshinCalcDTO salesSarneshinCalcDTO) throws URISyntaxException {
        log.debug("REST request to save SalesSarneshinCalc : {}", salesSarneshinCalcDTO);
        if (salesSarneshinCalcDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesSarneshinCalc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesSarneshinCalcDTO result = salesSarneshinCalcService.save(salesSarneshinCalcDTO);
        return ResponseEntity.created(new URI("/api/sales-sarneshin-calcs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sales-sarneshin-calcs : Updates an existing salesSarneshinCalc.
     *
     * @param salesSarneshinCalcDTO the salesSarneshinCalcDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated salesSarneshinCalcDTO,
     * or with status 400 (Bad Request) if the salesSarneshinCalcDTO is not valid,
     * or with status 500 (Internal Server Error) if the salesSarneshinCalcDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sales-sarneshin-calcs")
    public ResponseEntity<SalesSarneshinCalcDTO> updateSalesSarneshinCalc(@Valid @RequestBody SalesSarneshinCalcDTO salesSarneshinCalcDTO) throws URISyntaxException {
        log.debug("REST request to update SalesSarneshinCalc : {}", salesSarneshinCalcDTO);
        if (salesSarneshinCalcDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalesSarneshinCalcDTO result = salesSarneshinCalcService.save(salesSarneshinCalcDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, salesSarneshinCalcDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sales-sarneshin-calcs : get all the salesSarneshinCalcs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of salesSarneshinCalcs in body
     */
    @GetMapping("/sales-sarneshin-calcs")
    public ResponseEntity<List<SalesSarneshinCalcDTO>> getAllSalesSarneshinCalcs(SalesSarneshinCalcCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SalesSarneshinCalcs by criteria: {}", criteria);
        Page<SalesSarneshinCalcDTO> page = salesSarneshinCalcQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sales-sarneshin-calcs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /sales-sarneshin-calcs/count : count all the salesSarneshinCalcs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/sales-sarneshin-calcs/count")
    public ResponseEntity<Long> countSalesSarneshinCalcs(SalesSarneshinCalcCriteria criteria) {
        log.debug("REST request to count SalesSarneshinCalcs by criteria: {}", criteria);
        return ResponseEntity.ok().body(salesSarneshinCalcQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /sales-sarneshin-calcs/:id : get the "id" salesSarneshinCalc.
     *
     * @param id the id of the salesSarneshinCalcDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the salesSarneshinCalcDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sales-sarneshin-calcs/{id}")
    public ResponseEntity<SalesSarneshinCalcDTO> getSalesSarneshinCalc(@PathVariable Long id) {
        log.debug("REST request to get SalesSarneshinCalc : {}", id);
        Optional<SalesSarneshinCalcDTO> salesSarneshinCalcDTO = salesSarneshinCalcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesSarneshinCalcDTO);
    }

    /**
     * DELETE  /sales-sarneshin-calcs/:id : delete the "id" salesSarneshinCalc.
     *
     * @param id the id of the salesSarneshinCalcDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sales-sarneshin-calcs/{id}")
    public ResponseEntity<Void> deleteSalesSarneshinCalc(@PathVariable Long id) {
        log.debug("REST request to delete SalesSarneshinCalc : {}", id);
        salesSarneshinCalcService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
