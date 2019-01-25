package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.MohasebeBadaneService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.MohasebeBadaneDTO;
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
 * REST controller for managing MohasebeBadane.
 */
@RestController
@RequestMapping("/api")
public class MohasebeBadaneResource {

    private final Logger log = LoggerFactory.getLogger(MohasebeBadaneResource.class);

    private static final String ENTITY_NAME = "mohasebeBadane";

    private final MohasebeBadaneService mohasebeBadaneService;

    public MohasebeBadaneResource(MohasebeBadaneService mohasebeBadaneService) {
        this.mohasebeBadaneService = mohasebeBadaneService;
    }

    /**
     * POST  /mohasebe-badanes : Create a new mohasebeBadane.
     *
     * @param mohasebeBadaneDTO the mohasebeBadaneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mohasebeBadaneDTO, or with status 400 (Bad Request) if the mohasebeBadane has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mohasebe-badanes")
    public ResponseEntity<MohasebeBadaneDTO> createMohasebeBadane(@RequestBody MohasebeBadaneDTO mohasebeBadaneDTO) throws URISyntaxException {
        log.debug("REST request to save MohasebeBadane : {}", mohasebeBadaneDTO);
        if (mohasebeBadaneDTO.getId() != null) {
            throw new BadRequestAlertException("A new mohasebeBadane cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MohasebeBadaneDTO result = mohasebeBadaneService.save(mohasebeBadaneDTO);
        return ResponseEntity.created(new URI("/api/mohasebe-badanes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mohasebe-badanes : Updates an existing mohasebeBadane.
     *
     * @param mohasebeBadaneDTO the mohasebeBadaneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mohasebeBadaneDTO,
     * or with status 400 (Bad Request) if the mohasebeBadaneDTO is not valid,
     * or with status 500 (Internal Server Error) if the mohasebeBadaneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mohasebe-badanes")
    public ResponseEntity<MohasebeBadaneDTO> updateMohasebeBadane(@RequestBody MohasebeBadaneDTO mohasebeBadaneDTO) throws URISyntaxException {
        log.debug("REST request to update MohasebeBadane : {}", mohasebeBadaneDTO);
        if (mohasebeBadaneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MohasebeBadaneDTO result = mohasebeBadaneService.save(mohasebeBadaneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mohasebeBadaneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mohasebe-badanes : get all the mohasebeBadanes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mohasebeBadanes in body
     */
    @GetMapping("/mohasebe-badanes")
    public ResponseEntity<List<MohasebeBadaneDTO>> getAllMohasebeBadanes(Pageable pageable) {
        log.debug("REST request to get a page of MohasebeBadanes");
        Page<MohasebeBadaneDTO> page = mohasebeBadaneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mohasebe-badanes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /mohasebe-badanes/:id : get the "id" mohasebeBadane.
     *
     * @param id the id of the mohasebeBadaneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mohasebeBadaneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mohasebe-badanes/{id}")
    public ResponseEntity<MohasebeBadaneDTO> getMohasebeBadane(@PathVariable Long id) {
        log.debug("REST request to get MohasebeBadane : {}", id);
        Optional<MohasebeBadaneDTO> mohasebeBadaneDTO = mohasebeBadaneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mohasebeBadaneDTO);
    }

    /**
     * DELETE  /mohasebe-badanes/:id : delete the "id" mohasebeBadane.
     *
     * @param id the id of the mohasebeBadaneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mohasebe-badanes/{id}")
    public ResponseEntity<Void> deleteMohasebeBadane(@PathVariable Long id) {
        log.debug("REST request to delete MohasebeBadane : {}", id);
        mohasebeBadaneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
