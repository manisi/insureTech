package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.PoosheshService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.PoosheshDTO;
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
 * REST controller for managing Pooshesh.
 */
@RestController
@RequestMapping("/api")
public class PoosheshResource {

    private final Logger log = LoggerFactory.getLogger(PoosheshResource.class);

    private static final String ENTITY_NAME = "pooshesh";

    private final PoosheshService poosheshService;

    public PoosheshResource(PoosheshService poosheshService) {
        this.poosheshService = poosheshService;
    }

    /**
     * POST  /poosheshes : Create a new pooshesh.
     *
     * @param poosheshDTO the poosheshDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new poosheshDTO, or with status 400 (Bad Request) if the pooshesh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poosheshes")
    public ResponseEntity<PoosheshDTO> createPooshesh(@RequestBody PoosheshDTO poosheshDTO) throws URISyntaxException {
        log.debug("REST request to save Pooshesh : {}", poosheshDTO);
        if (poosheshDTO.getId() != null) {
            throw new BadRequestAlertException("A new pooshesh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PoosheshDTO result = poosheshService.save(poosheshDTO);
        return ResponseEntity.created(new URI("/api/poosheshes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poosheshes : Updates an existing pooshesh.
     *
     * @param poosheshDTO the poosheshDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated poosheshDTO,
     * or with status 400 (Bad Request) if the poosheshDTO is not valid,
     * or with status 500 (Internal Server Error) if the poosheshDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poosheshes")
    public ResponseEntity<PoosheshDTO> updatePooshesh(@RequestBody PoosheshDTO poosheshDTO) throws URISyntaxException {
        log.debug("REST request to update Pooshesh : {}", poosheshDTO);
        if (poosheshDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PoosheshDTO result = poosheshService.save(poosheshDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, poosheshDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poosheshes : get all the poosheshes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of poosheshes in body
     */
    @GetMapping("/poosheshes")
    public ResponseEntity<List<PoosheshDTO>> getAllPoosheshes(Pageable pageable) {
        log.debug("REST request to get a page of Poosheshes");
        Page<PoosheshDTO> page = poosheshService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/poosheshes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /poosheshes/:id : get the "id" pooshesh.
     *
     * @param id the id of the poosheshDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the poosheshDTO, or with status 404 (Not Found)
     */
    @GetMapping("/poosheshes/{id}")
    public ResponseEntity<PoosheshDTO> getPooshesh(@PathVariable Long id) {
        log.debug("REST request to get Pooshesh : {}", id);
        Optional<PoosheshDTO> poosheshDTO = poosheshService.findOne(id);
        return ResponseUtil.wrapOrNotFound(poosheshDTO);
    }

    /**
     * DELETE  /poosheshes/:id : delete the "id" pooshesh.
     *
     * @param id the id of the poosheshDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poosheshes/{id}")
    public ResponseEntity<Void> deletePooshesh(@PathVariable Long id) {
        log.debug("REST request to delete Pooshesh : {}", id);
        poosheshService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
