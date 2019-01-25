package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.GrouhKhodroService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.GrouhKhodroDTO;
import ir.insurance.startup.service.dto.GrouhKhodroCriteria;
import ir.insurance.startup.service.GrouhKhodroQueryService;
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
 * REST controller for managing GrouhKhodro.
 */
@RestController
@RequestMapping("/api")
public class GrouhKhodroResource {

    private final Logger log = LoggerFactory.getLogger(GrouhKhodroResource.class);

    private static final String ENTITY_NAME = "grouhKhodro";

    private final GrouhKhodroService grouhKhodroService;

    private final GrouhKhodroQueryService grouhKhodroQueryService;

    public GrouhKhodroResource(GrouhKhodroService grouhKhodroService, GrouhKhodroQueryService grouhKhodroQueryService) {
        this.grouhKhodroService = grouhKhodroService;
        this.grouhKhodroQueryService = grouhKhodroQueryService;
    }

    /**
     * POST  /grouh-khodros : Create a new grouhKhodro.
     *
     * @param grouhKhodroDTO the grouhKhodroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new grouhKhodroDTO, or with status 400 (Bad Request) if the grouhKhodro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grouh-khodros")
    public ResponseEntity<GrouhKhodroDTO> createGrouhKhodro(@Valid @RequestBody GrouhKhodroDTO grouhKhodroDTO) throws URISyntaxException {
        log.debug("REST request to save GrouhKhodro : {}", grouhKhodroDTO);
        if (grouhKhodroDTO.getId() != null) {
            throw new BadRequestAlertException("A new grouhKhodro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrouhKhodroDTO result = grouhKhodroService.save(grouhKhodroDTO);
        return ResponseEntity.created(new URI("/api/grouh-khodros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grouh-khodros : Updates an existing grouhKhodro.
     *
     * @param grouhKhodroDTO the grouhKhodroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated grouhKhodroDTO,
     * or with status 400 (Bad Request) if the grouhKhodroDTO is not valid,
     * or with status 500 (Internal Server Error) if the grouhKhodroDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grouh-khodros")
    public ResponseEntity<GrouhKhodroDTO> updateGrouhKhodro(@Valid @RequestBody GrouhKhodroDTO grouhKhodroDTO) throws URISyntaxException {
        log.debug("REST request to update GrouhKhodro : {}", grouhKhodroDTO);
        if (grouhKhodroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GrouhKhodroDTO result = grouhKhodroService.save(grouhKhodroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, grouhKhodroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grouh-khodros : get all the grouhKhodros.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of grouhKhodros in body
     */
    @GetMapping("/grouh-khodros")
    public ResponseEntity<List<GrouhKhodroDTO>> getAllGrouhKhodros(GrouhKhodroCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GrouhKhodros by criteria: {}", criteria);
        Page<GrouhKhodroDTO> page = grouhKhodroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/grouh-khodros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /grouh-khodros/count : count all the grouhKhodros.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/grouh-khodros/count")
    public ResponseEntity<Long> countGrouhKhodros(GrouhKhodroCriteria criteria) {
        log.debug("REST request to count GrouhKhodros by criteria: {}", criteria);
        return ResponseEntity.ok().body(grouhKhodroQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /grouh-khodros/:id : get the "id" grouhKhodro.
     *
     * @param id the id of the grouhKhodroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the grouhKhodroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/grouh-khodros/{id}")
    public ResponseEntity<GrouhKhodroDTO> getGrouhKhodro(@PathVariable Long id) {
        log.debug("REST request to get GrouhKhodro : {}", id);
        Optional<GrouhKhodroDTO> grouhKhodroDTO = grouhKhodroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grouhKhodroDTO);
    }

    /**
     * DELETE  /grouh-khodros/:id : delete the "id" grouhKhodro.
     *
     * @param id the id of the grouhKhodroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grouh-khodros/{id}")
    public ResponseEntity<Void> deleteGrouhKhodro(@PathVariable Long id) {
        log.debug("REST request to delete GrouhKhodro : {}", id);
        grouhKhodroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
