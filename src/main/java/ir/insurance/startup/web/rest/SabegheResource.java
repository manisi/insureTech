package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.SabegheService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.SabegheDTO;
import ir.insurance.startup.service.dto.SabegheCriteria;
import ir.insurance.startup.service.SabegheQueryService;
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
 * REST controller for managing Sabeghe.
 */
@RestController
@RequestMapping("/api")
public class SabegheResource {

    private final Logger log = LoggerFactory.getLogger(SabegheResource.class);

    private static final String ENTITY_NAME = "sabeghe";

    private final SabegheService sabegheService;

    private final SabegheQueryService sabegheQueryService;

    public SabegheResource(SabegheService sabegheService, SabegheQueryService sabegheQueryService) {
        this.sabegheService = sabegheService;
        this.sabegheQueryService = sabegheQueryService;
    }

    /**
     * POST  /sabeghes : Create a new sabeghe.
     *
     * @param sabegheDTO the sabegheDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sabegheDTO, or with status 400 (Bad Request) if the sabeghe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sabeghes")
    public ResponseEntity<SabegheDTO> createSabeghe(@Valid @RequestBody SabegheDTO sabegheDTO) throws URISyntaxException {
        log.debug("REST request to save Sabeghe : {}", sabegheDTO);
        if (sabegheDTO.getId() != null) {
            throw new BadRequestAlertException("A new sabeghe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SabegheDTO result = sabegheService.save(sabegheDTO);
        return ResponseEntity.created(new URI("/api/sabeghes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sabeghes : Updates an existing sabeghe.
     *
     * @param sabegheDTO the sabegheDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sabegheDTO,
     * or with status 400 (Bad Request) if the sabegheDTO is not valid,
     * or with status 500 (Internal Server Error) if the sabegheDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sabeghes")
    public ResponseEntity<SabegheDTO> updateSabeghe(@Valid @RequestBody SabegheDTO sabegheDTO) throws URISyntaxException {
        log.debug("REST request to update Sabeghe : {}", sabegheDTO);
        if (sabegheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SabegheDTO result = sabegheService.save(sabegheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sabegheDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sabeghes : get all the sabeghes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of sabeghes in body
     */
    @GetMapping("/sabeghes")
    public ResponseEntity<List<SabegheDTO>> getAllSabeghes(SabegheCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Sabeghes by criteria: {}", criteria);
        Page<SabegheDTO> page = sabegheQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sabeghes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /sabeghes/count : count all the sabeghes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/sabeghes/count")
    public ResponseEntity<Long> countSabeghes(SabegheCriteria criteria) {
        log.debug("REST request to count Sabeghes by criteria: {}", criteria);
        return ResponseEntity.ok().body(sabegheQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /sabeghes/:id : get the "id" sabeghe.
     *
     * @param id the id of the sabegheDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sabegheDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sabeghes/{id}")
    public ResponseEntity<SabegheDTO> getSabeghe(@PathVariable Long id) {
        log.debug("REST request to get Sabeghe : {}", id);
        Optional<SabegheDTO> sabegheDTO = sabegheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sabegheDTO);
    }

    /**
     * DELETE  /sabeghes/:id : delete the "id" sabeghe.
     *
     * @param id the id of the sabegheDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sabeghes/{id}")
    public ResponseEntity<Void> deleteSabeghe(@PathVariable Long id) {
        log.debug("REST request to delete Sabeghe : {}", id);
        sabegheService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
