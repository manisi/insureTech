package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.NerkhService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.NerkhDTO;
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
 * REST controller for managing Nerkh.
 */
@RestController
@RequestMapping("/api")
public class NerkhResource {

    private final Logger log = LoggerFactory.getLogger(NerkhResource.class);

    private static final String ENTITY_NAME = "nerkh";

    private final NerkhService nerkhService;

    public NerkhResource(NerkhService nerkhService) {
        this.nerkhService = nerkhService;
    }

    /**
     * POST  /nerkhs : Create a new nerkh.
     *
     * @param nerkhDTO the nerkhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nerkhDTO, or with status 400 (Bad Request) if the nerkh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nerkhs")
    public ResponseEntity<NerkhDTO> createNerkh(@RequestBody NerkhDTO nerkhDTO) throws URISyntaxException {
        log.debug("REST request to save Nerkh : {}", nerkhDTO);
        if (nerkhDTO.getId() != null) {
            throw new BadRequestAlertException("A new nerkh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NerkhDTO result = nerkhService.save(nerkhDTO);
        return ResponseEntity.created(new URI("/api/nerkhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nerkhs : Updates an existing nerkh.
     *
     * @param nerkhDTO the nerkhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nerkhDTO,
     * or with status 400 (Bad Request) if the nerkhDTO is not valid,
     * or with status 500 (Internal Server Error) if the nerkhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nerkhs")
    public ResponseEntity<NerkhDTO> updateNerkh(@RequestBody NerkhDTO nerkhDTO) throws URISyntaxException {
        log.debug("REST request to update Nerkh : {}", nerkhDTO);
        if (nerkhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NerkhDTO result = nerkhService.save(nerkhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nerkhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nerkhs : get all the nerkhs.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of nerkhs in body
     */
    @GetMapping("/nerkhs")
    public ResponseEntity<List<NerkhDTO>> getAllNerkhs(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Nerkhs");
        Page<NerkhDTO> page;
        if (eagerload) {
            page = nerkhService.findAllWithEagerRelationships(pageable);
        } else {
            page = nerkhService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/nerkhs?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nerkhs/:id : get the "id" nerkh.
     *
     * @param id the id of the nerkhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nerkhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nerkhs/{id}")
    public ResponseEntity<NerkhDTO> getNerkh(@PathVariable Long id) {
        log.debug("REST request to get Nerkh : {}", id);
        Optional<NerkhDTO> nerkhDTO = nerkhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nerkhDTO);
    }

    /**
     * DELETE  /nerkhs/:id : delete the "id" nerkh.
     *
     * @param id the id of the nerkhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nerkhs/{id}")
    public ResponseEntity<Void> deleteNerkh(@PathVariable Long id) {
        log.debug("REST request to delete Nerkh : {}", id);
        nerkhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
