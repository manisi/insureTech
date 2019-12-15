package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.ModateBimenameService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.service.dto.ModateBimenameDTO;
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
 * REST controller for managing ModateBimename.
 */
@RestController
@RequestMapping("/api")
public class ModateBimenameResource {

    private final Logger log = LoggerFactory.getLogger(ModateBimenameResource.class);

    private static final String ENTITY_NAME = "modateBimename";

    private final ModateBimenameService modateBimenameService;

    public ModateBimenameResource(ModateBimenameService modateBimenameService) {
        this.modateBimenameService = modateBimenameService;
    }

    /**
     * POST  /modate-bimenames : Create a new modateBimename.
     *
     * @param modateBimenameDTO the modateBimenameDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new modateBimenameDTO, or with status 400 (Bad Request) if the modateBimename has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/modate-bimenames")
    public ResponseEntity<ModateBimenameDTO> createModateBimename(@Valid @RequestBody ModateBimenameDTO modateBimenameDTO) throws URISyntaxException {
        log.debug("REST request to save ModateBimename : {}", modateBimenameDTO);
        if (modateBimenameDTO.getId() != null) {
            throw new BadRequestAlertException("A new modateBimename cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModateBimenameDTO result = modateBimenameService.save(modateBimenameDTO);
        return ResponseEntity.created(new URI("/api/modate-bimenames/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /modate-bimenames : Updates an existing modateBimename.
     *
     * @param modateBimenameDTO the modateBimenameDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated modateBimenameDTO,
     * or with status 400 (Bad Request) if the modateBimenameDTO is not valid,
     * or with status 500 (Internal Server Error) if the modateBimenameDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/modate-bimenames")
    public ResponseEntity<ModateBimenameDTO> updateModateBimename(@Valid @RequestBody ModateBimenameDTO modateBimenameDTO) throws URISyntaxException {
        log.debug("REST request to update ModateBimename : {}", modateBimenameDTO);
        if (modateBimenameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModateBimenameDTO result = modateBimenameService.save(modateBimenameDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, modateBimenameDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /modate-bimenames : get all the modateBimenames.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of modateBimenames in body
     */
    @GetMapping("/modate-bimenames")
    public List<ModateBimenameDTO> getAllModateBimenames() {
        log.debug("REST request to get all ModateBimenames");
        return modateBimenameService.findAll();
    }

    /**
     * GET  /modate-bimenames/:id : get the "id" modateBimename.
     *
     * @param id the id of the modateBimenameDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the modateBimenameDTO, or with status 404 (Not Found)
     */
    @GetMapping("/modate-bimenames/{id}")
    public ResponseEntity<ModateBimenameDTO> getModateBimename(@PathVariable Long id) {
        log.debug("REST request to get ModateBimename : {}", id);
        Optional<ModateBimenameDTO> modateBimenameDTO = modateBimenameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modateBimenameDTO);
    }

    /**
     * DELETE  /modate-bimenames/:id : delete the "id" modateBimename.
     *
     * @param id the id of the modateBimenameDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/modate-bimenames/{id}")
    public ResponseEntity<Void> deleteModateBimename(@PathVariable Long id) {
        log.debug("REST request to delete ModateBimename : {}", id);
        modateBimenameService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
