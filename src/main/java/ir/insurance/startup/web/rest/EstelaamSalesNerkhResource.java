package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.EstelaamSalesNerkhService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.EstelaamSalesNerkhDTO;
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
 * REST controller for managing EstelaamSalesNerkh.
 */
@RestController
@RequestMapping("/api")
public class EstelaamSalesNerkhResource {

    private final Logger log = LoggerFactory.getLogger(EstelaamSalesNerkhResource.class);

    private static final String ENTITY_NAME = "estelaamSalesNerkh";

    private final EstelaamSalesNerkhService estelaamSalesNerkhService;

    public EstelaamSalesNerkhResource(EstelaamSalesNerkhService estelaamSalesNerkhService) {
        this.estelaamSalesNerkhService = estelaamSalesNerkhService;
    }

    /**
     * POST  /estelaam-sales-nerkhs : Create a new estelaamSalesNerkh.
     *
     * @param estelaamSalesNerkhDTO the estelaamSalesNerkhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estelaamSalesNerkhDTO, or with status 400 (Bad Request) if the estelaamSalesNerkh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estelaam-sales-nerkhs")
    public ResponseEntity<EstelaamSalesNerkhDTO> createEstelaamSalesNerkh(@Valid @RequestBody EstelaamSalesNerkhDTO estelaamSalesNerkhDTO) throws URISyntaxException {
        log.debug("REST request to save EstelaamSalesNerkh : {}", estelaamSalesNerkhDTO);
        if (estelaamSalesNerkhDTO.getId() != null) {
            throw new BadRequestAlertException("A new estelaamSalesNerkh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstelaamSalesNerkhDTO result = estelaamSalesNerkhService.save(estelaamSalesNerkhDTO);
        return ResponseEntity.created(new URI("/api/estelaam-sales-nerkhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estelaam-sales-nerkhs : Updates an existing estelaamSalesNerkh.
     *
     * @param estelaamSalesNerkhDTO the estelaamSalesNerkhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estelaamSalesNerkhDTO,
     * or with status 400 (Bad Request) if the estelaamSalesNerkhDTO is not valid,
     * or with status 500 (Internal Server Error) if the estelaamSalesNerkhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estelaam-sales-nerkhs")
    public ResponseEntity<EstelaamSalesNerkhDTO> updateEstelaamSalesNerkh(@Valid @RequestBody EstelaamSalesNerkhDTO estelaamSalesNerkhDTO) throws URISyntaxException {
        log.debug("REST request to update EstelaamSalesNerkh : {}", estelaamSalesNerkhDTO);
        if (estelaamSalesNerkhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstelaamSalesNerkhDTO result = estelaamSalesNerkhService.save(estelaamSalesNerkhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estelaamSalesNerkhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estelaam-sales-nerkhs : get all the estelaamSalesNerkhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of estelaamSalesNerkhs in body
     */
    @GetMapping("/estelaam-sales-nerkhs")
    public ResponseEntity<List<EstelaamSalesNerkhDTO>> getAllEstelaamSalesNerkhs(Pageable pageable) {
        log.debug("REST request to get a page of EstelaamSalesNerkhs");
        Page<EstelaamSalesNerkhDTO> page = estelaamSalesNerkhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estelaam-sales-nerkhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /estelaam-sales-nerkhs/:id : get the "id" estelaamSalesNerkh.
     *
     * @param id the id of the estelaamSalesNerkhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estelaamSalesNerkhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/estelaam-sales-nerkhs/{id}")
    public ResponseEntity<EstelaamSalesNerkhDTO> getEstelaamSalesNerkh(@PathVariable Long id) {
        log.debug("REST request to get EstelaamSalesNerkh : {}", id);
        Optional<EstelaamSalesNerkhDTO> estelaamSalesNerkhDTO = estelaamSalesNerkhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estelaamSalesNerkhDTO);
    }

    /**
     * DELETE  /estelaam-sales-nerkhs/:id : delete the "id" estelaamSalesNerkh.
     *
     * @param id the id of the estelaamSalesNerkhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estelaam-sales-nerkhs/{id}")
    public ResponseEntity<Void> deleteEstelaamSalesNerkh(@PathVariable Long id) {
        log.debug("REST request to delete EstelaamSalesNerkh : {}", id);
        estelaamSalesNerkhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
