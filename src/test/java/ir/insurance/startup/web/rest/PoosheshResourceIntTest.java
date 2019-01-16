package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.Pooshesh;
import ir.insurance.startup.repository.PoosheshRepository;
import ir.insurance.startup.service.PoosheshService;
import ir.insurance.startup.service.dto.PoosheshDTO;
import ir.insurance.startup.service.mapper.PoosheshMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PoosheshResource REST controller.
 *
 * @see PoosheshResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class PoosheshResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    private static final Instant DEFAULT_AZTARIKH = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_AZTARIKH = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PoosheshRepository poosheshRepository;

    @Autowired
    private PoosheshMapper poosheshMapper;

    @Autowired
    private PoosheshService poosheshService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPoosheshMockMvc;

    private Pooshesh pooshesh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PoosheshResource poosheshResource = new PoosheshResource(poosheshService);
        this.restPoosheshMockMvc = MockMvcBuilders.standaloneSetup(poosheshResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pooshesh createEntity(EntityManager em) {
        Pooshesh pooshesh = new Pooshesh()
            .name(DEFAULT_NAME)
            .faal(DEFAULT_FAAL)
            .aztarikh(DEFAULT_AZTARIKH);
        return pooshesh;
    }

    @Before
    public void initTest() {
        pooshesh = createEntity(em);
    }

    @Test
    @Transactional
    public void createPooshesh() throws Exception {
        int databaseSizeBeforeCreate = poosheshRepository.findAll().size();

        // Create the Pooshesh
        PoosheshDTO poosheshDTO = poosheshMapper.toDto(pooshesh);
        restPoosheshMockMvc.perform(post("/api/poosheshes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poosheshDTO)))
            .andExpect(status().isCreated());

        // Validate the Pooshesh in the database
        List<Pooshesh> poosheshList = poosheshRepository.findAll();
        assertThat(poosheshList).hasSize(databaseSizeBeforeCreate + 1);
        Pooshesh testPooshesh = poosheshList.get(poosheshList.size() - 1);
        assertThat(testPooshesh.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPooshesh.isFaal()).isEqualTo(DEFAULT_FAAL);
        assertThat(testPooshesh.getAztarikh()).isEqualTo(DEFAULT_AZTARIKH);
    }

    @Test
    @Transactional
    public void createPoosheshWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = poosheshRepository.findAll().size();

        // Create the Pooshesh with an existing ID
        pooshesh.setId(1L);
        PoosheshDTO poosheshDTO = poosheshMapper.toDto(pooshesh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPoosheshMockMvc.perform(post("/api/poosheshes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poosheshDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pooshesh in the database
        List<Pooshesh> poosheshList = poosheshRepository.findAll();
        assertThat(poosheshList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPoosheshes() throws Exception {
        // Initialize the database
        poosheshRepository.saveAndFlush(pooshesh);

        // Get all the poosheshList
        restPoosheshMockMvc.perform(get("/api/poosheshes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pooshesh.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())))
            .andExpect(jsonPath("$.[*].aztarikh").value(hasItem(DEFAULT_AZTARIKH.toString())));
    }
    
    @Test
    @Transactional
    public void getPooshesh() throws Exception {
        // Initialize the database
        poosheshRepository.saveAndFlush(pooshesh);

        // Get the pooshesh
        restPoosheshMockMvc.perform(get("/api/poosheshes/{id}", pooshesh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pooshesh.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()))
            .andExpect(jsonPath("$.aztarikh").value(DEFAULT_AZTARIKH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPooshesh() throws Exception {
        // Get the pooshesh
        restPoosheshMockMvc.perform(get("/api/poosheshes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePooshesh() throws Exception {
        // Initialize the database
        poosheshRepository.saveAndFlush(pooshesh);

        int databaseSizeBeforeUpdate = poosheshRepository.findAll().size();

        // Update the pooshesh
        Pooshesh updatedPooshesh = poosheshRepository.findById(pooshesh.getId()).get();
        // Disconnect from session so that the updates on updatedPooshesh are not directly saved in db
        em.detach(updatedPooshesh);
        updatedPooshesh
            .name(UPDATED_NAME)
            .faal(UPDATED_FAAL)
            .aztarikh(UPDATED_AZTARIKH);
        PoosheshDTO poosheshDTO = poosheshMapper.toDto(updatedPooshesh);

        restPoosheshMockMvc.perform(put("/api/poosheshes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poosheshDTO)))
            .andExpect(status().isOk());

        // Validate the Pooshesh in the database
        List<Pooshesh> poosheshList = poosheshRepository.findAll();
        assertThat(poosheshList).hasSize(databaseSizeBeforeUpdate);
        Pooshesh testPooshesh = poosheshList.get(poosheshList.size() - 1);
        assertThat(testPooshesh.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPooshesh.isFaal()).isEqualTo(UPDATED_FAAL);
        assertThat(testPooshesh.getAztarikh()).isEqualTo(UPDATED_AZTARIKH);
    }

    @Test
    @Transactional
    public void updateNonExistingPooshesh() throws Exception {
        int databaseSizeBeforeUpdate = poosheshRepository.findAll().size();

        // Create the Pooshesh
        PoosheshDTO poosheshDTO = poosheshMapper.toDto(pooshesh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPoosheshMockMvc.perform(put("/api/poosheshes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(poosheshDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pooshesh in the database
        List<Pooshesh> poosheshList = poosheshRepository.findAll();
        assertThat(poosheshList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePooshesh() throws Exception {
        // Initialize the database
        poosheshRepository.saveAndFlush(pooshesh);

        int databaseSizeBeforeDelete = poosheshRepository.findAll().size();

        // Get the pooshesh
        restPoosheshMockMvc.perform(delete("/api/poosheshes/{id}", pooshesh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pooshesh> poosheshList = poosheshRepository.findAll();
        assertThat(poosheshList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pooshesh.class);
        Pooshesh pooshesh1 = new Pooshesh();
        pooshesh1.setId(1L);
        Pooshesh pooshesh2 = new Pooshesh();
        pooshesh2.setId(pooshesh1.getId());
        assertThat(pooshesh1).isEqualTo(pooshesh2);
        pooshesh2.setId(2L);
        assertThat(pooshesh1).isNotEqualTo(pooshesh2);
        pooshesh1.setId(null);
        assertThat(pooshesh1).isNotEqualTo(pooshesh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PoosheshDTO.class);
        PoosheshDTO poosheshDTO1 = new PoosheshDTO();
        poosheshDTO1.setId(1L);
        PoosheshDTO poosheshDTO2 = new PoosheshDTO();
        assertThat(poosheshDTO1).isNotEqualTo(poosheshDTO2);
        poosheshDTO2.setId(poosheshDTO1.getId());
        assertThat(poosheshDTO1).isEqualTo(poosheshDTO2);
        poosheshDTO2.setId(2L);
        assertThat(poosheshDTO1).isNotEqualTo(poosheshDTO2);
        poosheshDTO1.setId(null);
        assertThat(poosheshDTO1).isNotEqualTo(poosheshDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(poosheshMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(poosheshMapper.fromId(null)).isNull();
    }
}
