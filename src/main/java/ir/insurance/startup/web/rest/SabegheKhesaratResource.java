package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.SabegheKhesaratService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.service.dto.SabegheKhesaratDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SabegheKhesarat.
 */
@RestController
@RequestMapping("/api")
public class SabegheKhesaratResource {

    private final Logger log = LoggerFactory.getLogger(SabegheKhesaratResource.class);

    private static final String ENTITY_NAME = "sabegheKhesarat";

    private final SabegheKhesaratService sabegheKhesaratService;

    public SabegheKhesaratResource(SabegheKhesaratService sabegheKhesaratService) {
        this.sabegheKhesaratService = sabegheKhesaratService;
    }

    /**
     * POST  /sabeghe-khesarats : Create a new sabegheKhesarat.
     *
     * @param sabegheKhesaratDTO the sabegheKhesaratDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sabegheKhesaratDTO, or with status 400 (Bad Request) if the sabegheKhesarat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sabeghe-khesarats")
    public ResponseEntity<SabegheKhesaratDTO> createSabegheKhesarat(@Valid @RequestBody SabegheKhesaratDTO sabegheKhesaratDTO) throws URISyntaxException {
        log.debug("REST request to save SabegheKhesarat : {}", sabegheKhesaratDTO);
        if (sabegheKhesaratDTO.getId() != null) {
            throw new BadRequestAlertException("A new sabegheKhesarat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SabegheKhesaratDTO result = sabegheKhesaratService.save(sabegheKhesaratDTO);
        return ResponseEntity.created(new URI("/api/sabeghe-khesarats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sabeghe-khesarats : Updates an existing sabegheKhesarat.
     *
     * @param sabegheKhesaratDTO the sabegheKhesaratDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sabegheKhesaratDTO,
     * or with status 400 (Bad Request) if the sabegheKhesaratDTO is not valid,
     * or with status 500 (Internal Server Error) if the sabegheKhesaratDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sabeghe-khesarats")
    public ResponseEntity<SabegheKhesaratDTO> updateSabegheKhesarat(@Valid @RequestBody SabegheKhesaratDTO sabegheKhesaratDTO) throws URISyntaxException {
        log.debug("REST request to update SabegheKhesarat : {}", sabegheKhesaratDTO);
        if (sabegheKhesaratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SabegheKhesaratDTO result = sabegheKhesaratService.save(sabegheKhesaratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sabegheKhesaratDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sabeghe-khesarats : get all the sabegheKhesarats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sabegheKhesarats in body
     */
    @GetMapping("/sabeghe-khesarats")
    public List<SabegheKhesaratDTO> getAllSabegheKhesarats() {
        log.debug("REST request to get all SabegheKhesarats");
        return sabegheKhesaratService.findAll();
    }

    /**
     * GET  /sabeghe-khesarats/:id : get the "id" sabegheKhesarat.
     *
     * @param id the id of the sabegheKhesaratDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sabegheKhesaratDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sabeghe-khesarats/{id}")
    public ResponseEntity<SabegheKhesaratDTO> getSabegheKhesarat(@PathVariable Long id) {
        log.debug("REST request to get SabegheKhesarat : {}", id);
        Optional<SabegheKhesaratDTO> sabegheKhesaratDTO = sabegheKhesaratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sabegheKhesaratDTO);
    }

    /**
     * DELETE  /sabeghe-khesarats/:id : delete the "id" sabegheKhesarat.
     *
     * @param id the id of the sabegheKhesaratDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sabeghe-khesarats/{id}")
    public ResponseEntity<Void> deleteSabegheKhesarat(@PathVariable Long id) {
        log.debug("REST request to delete SabegheKhesarat : {}", id);
        sabegheKhesaratService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
