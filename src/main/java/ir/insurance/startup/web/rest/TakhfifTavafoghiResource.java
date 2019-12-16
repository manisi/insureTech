package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.TakhfifTavafoghiService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.TakhfifTavafoghiDTO;
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
 * REST controller for managing TakhfifTavafoghi.
 */
@RestController
@RequestMapping("/api")
public class TakhfifTavafoghiResource {

    private final Logger log = LoggerFactory.getLogger(TakhfifTavafoghiResource.class);

    private static final String ENTITY_NAME = "takhfifTavafoghi";

    private final TakhfifTavafoghiService takhfifTavafoghiService;

    public TakhfifTavafoghiResource(TakhfifTavafoghiService takhfifTavafoghiService) {
        this.takhfifTavafoghiService = takhfifTavafoghiService;
    }

    /**
     * POST  /takhfif-tavafoghis : Create a new takhfifTavafoghi.
     *
     * @param takhfifTavafoghiDTO the takhfifTavafoghiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new takhfifTavafoghiDTO, or with status 400 (Bad Request) if the takhfifTavafoghi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/takhfif-tavafoghis")
    public ResponseEntity<TakhfifTavafoghiDTO> createTakhfifTavafoghi(@Valid @RequestBody TakhfifTavafoghiDTO takhfifTavafoghiDTO) throws URISyntaxException {
        log.debug("REST request to save TakhfifTavafoghi : {}", takhfifTavafoghiDTO);
        if (takhfifTavafoghiDTO.getId() != null) {
            throw new BadRequestAlertException("A new takhfifTavafoghi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TakhfifTavafoghiDTO result = takhfifTavafoghiService.save(takhfifTavafoghiDTO);
        return ResponseEntity.created(new URI("/api/takhfif-tavafoghis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /takhfif-tavafoghis : Updates an existing takhfifTavafoghi.
     *
     * @param takhfifTavafoghiDTO the takhfifTavafoghiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated takhfifTavafoghiDTO,
     * or with status 400 (Bad Request) if the takhfifTavafoghiDTO is not valid,
     * or with status 500 (Internal Server Error) if the takhfifTavafoghiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/takhfif-tavafoghis")
    public ResponseEntity<TakhfifTavafoghiDTO> updateTakhfifTavafoghi(@Valid @RequestBody TakhfifTavafoghiDTO takhfifTavafoghiDTO) throws URISyntaxException {
        log.debug("REST request to update TakhfifTavafoghi : {}", takhfifTavafoghiDTO);
        if (takhfifTavafoghiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TakhfifTavafoghiDTO result = takhfifTavafoghiService.save(takhfifTavafoghiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, takhfifTavafoghiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /takhfif-tavafoghis : get all the takhfifTavafoghis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of takhfifTavafoghis in body
     */
    @GetMapping("/takhfif-tavafoghis")
    public ResponseEntity<List<TakhfifTavafoghiDTO>> getAllTakhfifTavafoghis(Pageable pageable) {
        log.debug("REST request to get a page of TakhfifTavafoghis");
        Page<TakhfifTavafoghiDTO> page = takhfifTavafoghiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/takhfif-tavafoghis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /takhfif-tavafoghis/:id : get the "id" takhfifTavafoghi.
     *
     * @param id the id of the takhfifTavafoghiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the takhfifTavafoghiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/takhfif-tavafoghis/{id}")
    public ResponseEntity<TakhfifTavafoghiDTO> getTakhfifTavafoghi(@PathVariable Long id) {
        log.debug("REST request to get TakhfifTavafoghi : {}", id);
        Optional<TakhfifTavafoghiDTO> takhfifTavafoghiDTO = takhfifTavafoghiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(takhfifTavafoghiDTO);
    }

    /**
     * DELETE  /takhfif-tavafoghis/:id : delete the "id" takhfifTavafoghi.
     *
     * @param id the id of the takhfifTavafoghiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/takhfif-tavafoghis/{id}")
    public ResponseEntity<Void> deleteTakhfifTavafoghi(@PathVariable Long id) {
        log.debug("REST request to delete TakhfifTavafoghi : {}", id);
        takhfifTavafoghiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
