package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.TipKhodroService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.TipKhodroDTO;
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
 * REST controller for managing TipKhodro.
 */
@RestController
@RequestMapping("/api")
public class TipKhodroResource {

    private final Logger log = LoggerFactory.getLogger(TipKhodroResource.class);

    private static final String ENTITY_NAME = "tipKhodro";

    private final TipKhodroService tipKhodroService;

    public TipKhodroResource(TipKhodroService tipKhodroService) {
        this.tipKhodroService = tipKhodroService;
    }

    /**
     * POST  /tip-khodros : Create a new tipKhodro.
     *
     * @param tipKhodroDTO the tipKhodroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipKhodroDTO, or with status 400 (Bad Request) if the tipKhodro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tip-khodros")
    public ResponseEntity<TipKhodroDTO> createTipKhodro(@RequestBody TipKhodroDTO tipKhodroDTO) throws URISyntaxException {
        log.debug("REST request to save TipKhodro : {}", tipKhodroDTO);
        if (tipKhodroDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipKhodro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipKhodroDTO result = tipKhodroService.save(tipKhodroDTO);
        return ResponseEntity.created(new URI("/api/tip-khodros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tip-khodros : Updates an existing tipKhodro.
     *
     * @param tipKhodroDTO the tipKhodroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipKhodroDTO,
     * or with status 400 (Bad Request) if the tipKhodroDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipKhodroDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tip-khodros")
    public ResponseEntity<TipKhodroDTO> updateTipKhodro(@RequestBody TipKhodroDTO tipKhodroDTO) throws URISyntaxException {
        log.debug("REST request to update TipKhodro : {}", tipKhodroDTO);
        if (tipKhodroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipKhodroDTO result = tipKhodroService.save(tipKhodroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipKhodroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tip-khodros : get all the tipKhodros.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tipKhodros in body
     */
    @GetMapping("/tip-khodros")
    public ResponseEntity<List<TipKhodroDTO>> getAllTipKhodros(Pageable pageable) {
        log.debug("REST request to get a page of TipKhodros");
        Page<TipKhodroDTO> page = tipKhodroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tip-khodros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tip-khodros/:id : get the "id" tipKhodro.
     *
     * @param id the id of the tipKhodroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipKhodroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tip-khodros/{id}")
    public ResponseEntity<TipKhodroDTO> getTipKhodro(@PathVariable Long id) {
        log.debug("REST request to get TipKhodro : {}", id);
        Optional<TipKhodroDTO> tipKhodroDTO = tipKhodroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipKhodroDTO);
    }

    /**
     * DELETE  /tip-khodros/:id : delete the "id" tipKhodro.
     *
     * @param id the id of the tipKhodroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tip-khodros/{id}")
    public ResponseEntity<Void> deleteTipKhodro(@PathVariable Long id) {
        log.debug("REST request to delete TipKhodro : {}", id);
        tipKhodroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
