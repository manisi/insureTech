package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.NoeSabegheService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.NoeSabegheDTO;
import ir.insurance.startup.service.dto.NoeSabegheCriteria;
import ir.insurance.startup.service.NoeSabegheQueryService;
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
 * REST controller for managing NoeSabeghe.
 */
@RestController
@RequestMapping("/api")
public class NoeSabegheResource {

    private final Logger log = LoggerFactory.getLogger(NoeSabegheResource.class);

    private static final String ENTITY_NAME = "noeSabeghe";

    private final NoeSabegheService noeSabegheService;

    private final NoeSabegheQueryService noeSabegheQueryService;

    public NoeSabegheResource(NoeSabegheService noeSabegheService, NoeSabegheQueryService noeSabegheQueryService) {
        this.noeSabegheService = noeSabegheService;
        this.noeSabegheQueryService = noeSabegheQueryService;
    }

    /**
     * POST  /noe-sabeghes : Create a new noeSabeghe.
     *
     * @param noeSabegheDTO the noeSabegheDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noeSabegheDTO, or with status 400 (Bad Request) if the noeSabeghe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/noe-sabeghes")
    public ResponseEntity<NoeSabegheDTO> createNoeSabeghe(@Valid @RequestBody NoeSabegheDTO noeSabegheDTO) throws URISyntaxException {
        log.debug("REST request to save NoeSabeghe : {}", noeSabegheDTO);
        if (noeSabegheDTO.getId() != null) {
            throw new BadRequestAlertException("A new noeSabeghe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoeSabegheDTO result = noeSabegheService.save(noeSabegheDTO);
        return ResponseEntity.created(new URI("/api/noe-sabeghes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /noe-sabeghes : Updates an existing noeSabeghe.
     *
     * @param noeSabegheDTO the noeSabegheDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noeSabegheDTO,
     * or with status 400 (Bad Request) if the noeSabegheDTO is not valid,
     * or with status 500 (Internal Server Error) if the noeSabegheDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/noe-sabeghes")
    public ResponseEntity<NoeSabegheDTO> updateNoeSabeghe(@Valid @RequestBody NoeSabegheDTO noeSabegheDTO) throws URISyntaxException {
        log.debug("REST request to update NoeSabeghe : {}", noeSabegheDTO);
        if (noeSabegheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoeSabegheDTO result = noeSabegheService.save(noeSabegheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, noeSabegheDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /noe-sabeghes : get all the noeSabeghes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of noeSabeghes in body
     */
    @GetMapping("/noe-sabeghes")
    public ResponseEntity<List<NoeSabegheDTO>> getAllNoeSabeghes(NoeSabegheCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NoeSabeghes by criteria: {}", criteria);
        Page<NoeSabegheDTO> page = noeSabegheQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/noe-sabeghes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /noe-sabeghes/count : count all the noeSabeghes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/noe-sabeghes/count")
    public ResponseEntity<Long> countNoeSabeghes(NoeSabegheCriteria criteria) {
        log.debug("REST request to count NoeSabeghes by criteria: {}", criteria);
        return ResponseEntity.ok().body(noeSabegheQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /noe-sabeghes/:id : get the "id" noeSabeghe.
     *
     * @param id the id of the noeSabegheDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noeSabegheDTO, or with status 404 (Not Found)
     */
    @GetMapping("/noe-sabeghes/{id}")
    public ResponseEntity<NoeSabegheDTO> getNoeSabeghe(@PathVariable Long id) {
        log.debug("REST request to get NoeSabeghe : {}", id);
        Optional<NoeSabegheDTO> noeSabegheDTO = noeSabegheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(noeSabegheDTO);
    }

    /**
     * DELETE  /noe-sabeghes/:id : delete the "id" noeSabeghe.
     *
     * @param id the id of the noeSabegheDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/noe-sabeghes/{id}")
    public ResponseEntity<Void> deleteNoeSabeghe(@PathVariable Long id) {
        log.debug("REST request to delete NoeSabeghe : {}", id);
        noeSabegheService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
