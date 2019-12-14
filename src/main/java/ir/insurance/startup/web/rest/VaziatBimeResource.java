package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.VaziatBimeService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.VaziatBimeDTO;
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
 * REST controller for managing VaziatBime.
 */
@RestController
@RequestMapping("/api")
public class VaziatBimeResource {

    private final Logger log = LoggerFactory.getLogger(VaziatBimeResource.class);

    private static final String ENTITY_NAME = "vaziatBime";

    private final VaziatBimeService vaziatBimeService;

    public VaziatBimeResource(VaziatBimeService vaziatBimeService) {
        this.vaziatBimeService = vaziatBimeService;
    }

    /**
     * POST  /vaziat-bimes : Create a new vaziatBime.
     *
     * @param vaziatBimeDTO the vaziatBimeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vaziatBimeDTO, or with status 400 (Bad Request) if the vaziatBime has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vaziat-bimes")
    public ResponseEntity<VaziatBimeDTO> createVaziatBime(@Valid @RequestBody VaziatBimeDTO vaziatBimeDTO) throws URISyntaxException {
        log.debug("REST request to save VaziatBime : {}", vaziatBimeDTO);
        if (vaziatBimeDTO.getId() != null) {
            throw new BadRequestAlertException("A new vaziatBime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VaziatBimeDTO result = vaziatBimeService.save(vaziatBimeDTO);
        return ResponseEntity.created(new URI("/api/vaziat-bimes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vaziat-bimes : Updates an existing vaziatBime.
     *
     * @param vaziatBimeDTO the vaziatBimeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vaziatBimeDTO,
     * or with status 400 (Bad Request) if the vaziatBimeDTO is not valid,
     * or with status 500 (Internal Server Error) if the vaziatBimeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vaziat-bimes")
    public ResponseEntity<VaziatBimeDTO> updateVaziatBime(@Valid @RequestBody VaziatBimeDTO vaziatBimeDTO) throws URISyntaxException {
        log.debug("REST request to update VaziatBime : {}", vaziatBimeDTO);
        if (vaziatBimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VaziatBimeDTO result = vaziatBimeService.save(vaziatBimeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vaziatBimeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vaziat-bimes : get all the vaziatBimes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vaziatBimes in body
     */
    @GetMapping("/vaziat-bimes")
    public ResponseEntity<List<VaziatBimeDTO>> getAllVaziatBimes(Pageable pageable) {
        log.debug("REST request to get a page of VaziatBimes");
        Page<VaziatBimeDTO> page = vaziatBimeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vaziat-bimes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vaziat-bimes/:id : get the "id" vaziatBime.
     *
     * @param id the id of the vaziatBimeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vaziatBimeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vaziat-bimes/{id}")
    public ResponseEntity<VaziatBimeDTO> getVaziatBime(@PathVariable Long id) {
        log.debug("REST request to get VaziatBime : {}", id);
        Optional<VaziatBimeDTO> vaziatBimeDTO = vaziatBimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vaziatBimeDTO);
    }

    /**
     * DELETE  /vaziat-bimes/:id : delete the "id" vaziatBime.
     *
     * @param id the id of the vaziatBimeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vaziat-bimes/{id}")
    public ResponseEntity<Void> deleteVaziatBime(@PathVariable Long id) {
        log.debug("REST request to delete VaziatBime : {}", id);
        vaziatBimeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
