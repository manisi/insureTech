package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.KhodroService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.KhodroDTO;
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
 * REST controller for managing Khodro.
 */
@RestController
@RequestMapping("/api")
public class KhodroResource {

    private final Logger log = LoggerFactory.getLogger(KhodroResource.class);

    private static final String ENTITY_NAME = "khodro";

    private final KhodroService khodroService;

    public KhodroResource(KhodroService khodroService) {
        this.khodroService = khodroService;
    }

    /**
     * POST  /khodros : Create a new khodro.
     *
     * @param khodroDTO the khodroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new khodroDTO, or with status 400 (Bad Request) if the khodro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/khodros")
    public ResponseEntity<KhodroDTO> createKhodro(@RequestBody KhodroDTO khodroDTO) throws URISyntaxException {
        log.debug("REST request to save Khodro : {}", khodroDTO);
        if (khodroDTO.getId() != null) {
            throw new BadRequestAlertException("A new khodro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KhodroDTO result = khodroService.save(khodroDTO);
        return ResponseEntity.created(new URI("/api/khodros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /khodros : Updates an existing khodro.
     *
     * @param khodroDTO the khodroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated khodroDTO,
     * or with status 400 (Bad Request) if the khodroDTO is not valid,
     * or with status 500 (Internal Server Error) if the khodroDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/khodros")
    public ResponseEntity<KhodroDTO> updateKhodro(@RequestBody KhodroDTO khodroDTO) throws URISyntaxException {
        log.debug("REST request to update Khodro : {}", khodroDTO);
        if (khodroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KhodroDTO result = khodroService.save(khodroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, khodroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /khodros : get all the khodros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of khodros in body
     */
    @GetMapping("/khodros")
    public ResponseEntity<List<KhodroDTO>> getAllKhodros(Pageable pageable) {
        log.debug("REST request to get a page of Khodros");
        Page<KhodroDTO> page = khodroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/khodros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /khodros/:id : get the "id" khodro.
     *
     * @param id the id of the khodroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the khodroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/khodros/{id}")
    public ResponseEntity<KhodroDTO> getKhodro(@PathVariable Long id) {
        log.debug("REST request to get Khodro : {}", id);
        Optional<KhodroDTO> khodroDTO = khodroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(khodroDTO);
    }

    /**
     * DELETE  /khodros/:id : delete the "id" khodro.
     *
     * @param id the id of the khodroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/khodros/{id}")
    public ResponseEntity<Void> deleteKhodro(@PathVariable Long id) {
        log.debug("REST request to delete Khodro : {}", id);
        khodroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
