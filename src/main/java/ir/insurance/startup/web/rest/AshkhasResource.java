package ir.insurance.startup.web.rest;

import com.codahale.metrics.annotation.Timed;
import ir.insurance.startup.service.AshkhasService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.AshkhasDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Ashkhas.
 */
@RestController
@RequestMapping("/api")
public class AshkhasResource {

    private final Logger log = LoggerFactory.getLogger(AshkhasResource.class);

    private static final String ENTITY_NAME = "ashkhas";

    private final AshkhasService ashkhasService;

    public AshkhasResource(AshkhasService ashkhasService) {
        this.ashkhasService = ashkhasService;
    }

    /**
     * POST  /ashkhas : Create a new ashkhas.
     *
     * @param ashkhasDTO the ashkhasDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ashkhasDTO, or with status 400 (Bad Request) if the ashkhas has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ashkhas")
    @Timed
    public ResponseEntity<AshkhasDTO> createAshkhas(@RequestBody AshkhasDTO ashkhasDTO) throws URISyntaxException {
        log.debug("REST request to save Ashkhas : {}", ashkhasDTO);
        if (ashkhasDTO.getId() != null) {
            throw new BadRequestAlertException("A new ashkhas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AshkhasDTO result = ashkhasService.save(ashkhasDTO);
        return ResponseEntity.created(new URI("/api/ashkhas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ashkhas : Updates an existing ashkhas.
     *
     * @param ashkhasDTO the ashkhasDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ashkhasDTO,
     * or with status 400 (Bad Request) if the ashkhasDTO is not valid,
     * or with status 500 (Internal Server Error) if the ashkhasDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ashkhas")
    @Timed
    public ResponseEntity<AshkhasDTO> updateAshkhas(@RequestBody AshkhasDTO ashkhasDTO) throws URISyntaxException {
        log.debug("REST request to update Ashkhas : {}", ashkhasDTO);
        if (ashkhasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AshkhasDTO result = ashkhasService.save(ashkhasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ashkhasDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ashkhas : get all the ashkhas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ashkhas in body
     */
    @GetMapping("/ashkhas")
    @Timed
    public ResponseEntity<List<AshkhasDTO>> getAllAshkhas(Pageable pageable) {
        log.debug("REST request to get a page of Ashkhas");
        Page<AshkhasDTO> page = ashkhasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ashkhas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ashkhas/:id : get the "id" ashkhas.
     *
     * @param id the id of the ashkhasDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ashkhasDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ashkhas/{id}")
    @Timed
    public ResponseEntity<AshkhasDTO> getAshkhas(@PathVariable Long id) {
        log.debug("REST request to get Ashkhas : {}", id);
        Optional<AshkhasDTO> ashkhasDTO = ashkhasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ashkhasDTO);
    }

    /**
     * DELETE  /ashkhas/:id : delete the "id" ashkhas.
     *
     * @param id the id of the ashkhasDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ashkhas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAshkhas(@PathVariable Long id) {
        log.debug("REST request to delete Ashkhas : {}", id);
        ashkhasService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
