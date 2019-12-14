package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.VaziatBime;
import ir.insurance.startup.repository.VaziatBimeRepository;
import ir.insurance.startup.service.VaziatBimeService;
import ir.insurance.startup.service.dto.VaziatBimeDTO;
import ir.insurance.startup.service.mapper.VaziatBimeMapper;
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
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VaziatBimeResource REST controller.
 *
 * @see VaziatBimeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class VaziatBimeResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private VaziatBimeRepository vaziatBimeRepository;

    @Autowired
    private VaziatBimeMapper vaziatBimeMapper;

    @Autowired
    private VaziatBimeService vaziatBimeService;

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

    private MockMvc restVaziatBimeMockMvc;

    private VaziatBime vaziatBime;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VaziatBimeResource vaziatBimeResource = new VaziatBimeResource(vaziatBimeService);
        this.restVaziatBimeMockMvc = MockMvcBuilders.standaloneSetup(vaziatBimeResource)
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
    public static VaziatBime createEntity(EntityManager em) {
        VaziatBime vaziatBime = new VaziatBime()
            .title(DEFAULT_TITLE)
            .faal(DEFAULT_FAAL);
        return vaziatBime;
    }

    @Before
    public void initTest() {
        vaziatBime = createEntity(em);
    }

    @Test
    @Transactional
    public void createVaziatBime() throws Exception {
        int databaseSizeBeforeCreate = vaziatBimeRepository.findAll().size();

        // Create the VaziatBime
        VaziatBimeDTO vaziatBimeDTO = vaziatBimeMapper.toDto(vaziatBime);
        restVaziatBimeMockMvc.perform(post("/api/vaziat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vaziatBimeDTO)))
            .andExpect(status().isCreated());

        // Validate the VaziatBime in the database
        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeCreate + 1);
        VaziatBime testVaziatBime = vaziatBimeList.get(vaziatBimeList.size() - 1);
        assertThat(testVaziatBime.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testVaziatBime.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createVaziatBimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vaziatBimeRepository.findAll().size();

        // Create the VaziatBime with an existing ID
        vaziatBime.setId(1L);
        VaziatBimeDTO vaziatBimeDTO = vaziatBimeMapper.toDto(vaziatBime);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVaziatBimeMockMvc.perform(post("/api/vaziat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vaziatBimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VaziatBime in the database
        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaziatBimeRepository.findAll().size();
        // set the field null
        vaziatBime.setTitle(null);

        // Create the VaziatBime, which fails.
        VaziatBimeDTO vaziatBimeDTO = vaziatBimeMapper.toDto(vaziatBime);

        restVaziatBimeMockMvc.perform(post("/api/vaziat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vaziatBimeDTO)))
            .andExpect(status().isBadRequest());

        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = vaziatBimeRepository.findAll().size();
        // set the field null
        vaziatBime.setFaal(null);

        // Create the VaziatBime, which fails.
        VaziatBimeDTO vaziatBimeDTO = vaziatBimeMapper.toDto(vaziatBime);

        restVaziatBimeMockMvc.perform(post("/api/vaziat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vaziatBimeDTO)))
            .andExpect(status().isBadRequest());

        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVaziatBimes() throws Exception {
        // Initialize the database
        vaziatBimeRepository.saveAndFlush(vaziatBime);

        // Get all the vaziatBimeList
        restVaziatBimeMockMvc.perform(get("/api/vaziat-bimes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vaziatBime.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getVaziatBime() throws Exception {
        // Initialize the database
        vaziatBimeRepository.saveAndFlush(vaziatBime);

        // Get the vaziatBime
        restVaziatBimeMockMvc.perform(get("/api/vaziat-bimes/{id}", vaziatBime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vaziatBime.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVaziatBime() throws Exception {
        // Get the vaziatBime
        restVaziatBimeMockMvc.perform(get("/api/vaziat-bimes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVaziatBime() throws Exception {
        // Initialize the database
        vaziatBimeRepository.saveAndFlush(vaziatBime);

        int databaseSizeBeforeUpdate = vaziatBimeRepository.findAll().size();

        // Update the vaziatBime
        VaziatBime updatedVaziatBime = vaziatBimeRepository.findById(vaziatBime.getId()).get();
        // Disconnect from session so that the updates on updatedVaziatBime are not directly saved in db
        em.detach(updatedVaziatBime);
        updatedVaziatBime
            .title(UPDATED_TITLE)
            .faal(UPDATED_FAAL);
        VaziatBimeDTO vaziatBimeDTO = vaziatBimeMapper.toDto(updatedVaziatBime);

        restVaziatBimeMockMvc.perform(put("/api/vaziat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vaziatBimeDTO)))
            .andExpect(status().isOk());

        // Validate the VaziatBime in the database
        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeUpdate);
        VaziatBime testVaziatBime = vaziatBimeList.get(vaziatBimeList.size() - 1);
        assertThat(testVaziatBime.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testVaziatBime.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingVaziatBime() throws Exception {
        int databaseSizeBeforeUpdate = vaziatBimeRepository.findAll().size();

        // Create the VaziatBime
        VaziatBimeDTO vaziatBimeDTO = vaziatBimeMapper.toDto(vaziatBime);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVaziatBimeMockMvc.perform(put("/api/vaziat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vaziatBimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VaziatBime in the database
        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVaziatBime() throws Exception {
        // Initialize the database
        vaziatBimeRepository.saveAndFlush(vaziatBime);

        int databaseSizeBeforeDelete = vaziatBimeRepository.findAll().size();

        // Delete the vaziatBime
        restVaziatBimeMockMvc.perform(delete("/api/vaziat-bimes/{id}", vaziatBime.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VaziatBime> vaziatBimeList = vaziatBimeRepository.findAll();
        assertThat(vaziatBimeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VaziatBime.class);
        VaziatBime vaziatBime1 = new VaziatBime();
        vaziatBime1.setId(1L);
        VaziatBime vaziatBime2 = new VaziatBime();
        vaziatBime2.setId(vaziatBime1.getId());
        assertThat(vaziatBime1).isEqualTo(vaziatBime2);
        vaziatBime2.setId(2L);
        assertThat(vaziatBime1).isNotEqualTo(vaziatBime2);
        vaziatBime1.setId(null);
        assertThat(vaziatBime1).isNotEqualTo(vaziatBime2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VaziatBimeDTO.class);
        VaziatBimeDTO vaziatBimeDTO1 = new VaziatBimeDTO();
        vaziatBimeDTO1.setId(1L);
        VaziatBimeDTO vaziatBimeDTO2 = new VaziatBimeDTO();
        assertThat(vaziatBimeDTO1).isNotEqualTo(vaziatBimeDTO2);
        vaziatBimeDTO2.setId(vaziatBimeDTO1.getId());
        assertThat(vaziatBimeDTO1).isEqualTo(vaziatBimeDTO2);
        vaziatBimeDTO2.setId(2L);
        assertThat(vaziatBimeDTO1).isNotEqualTo(vaziatBimeDTO2);
        vaziatBimeDTO1.setId(null);
        assertThat(vaziatBimeDTO1).isNotEqualTo(vaziatBimeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vaziatBimeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vaziatBimeMapper.fromId(null)).isNull();
    }
}
