package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.repository.SherkatBimeRepository;
import ir.insurance.startup.service.SherkatBimeService;
import ir.insurance.startup.service.dto.SherkatBimeDTO;
import ir.insurance.startup.service.mapper.SherkatBimeMapper;
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
 * Test class for the SherkatBimeResource REST controller.
 *
 * @see SherkatBimeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SherkatBimeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private SherkatBimeRepository sherkatBimeRepository;

    @Autowired
    private SherkatBimeMapper sherkatBimeMapper;

    @Autowired
    private SherkatBimeService sherkatBimeService;

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

    private MockMvc restSherkatBimeMockMvc;

    private SherkatBime sherkatBime;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SherkatBimeResource sherkatBimeResource = new SherkatBimeResource(sherkatBimeService);
        this.restSherkatBimeMockMvc = MockMvcBuilders.standaloneSetup(sherkatBimeResource)
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
    public static SherkatBime createEntity(EntityManager em) {
        SherkatBime sherkatBime = new SherkatBime()
            .name(DEFAULT_NAME)
            .faal(DEFAULT_FAAL);
        return sherkatBime;
    }

    @Before
    public void initTest() {
        sherkatBime = createEntity(em);
    }

    @Test
    @Transactional
    public void createSherkatBime() throws Exception {
        int databaseSizeBeforeCreate = sherkatBimeRepository.findAll().size();

        // Create the SherkatBime
        SherkatBimeDTO sherkatBimeDTO = sherkatBimeMapper.toDto(sherkatBime);
        restSherkatBimeMockMvc.perform(post("/api/sherkat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sherkatBimeDTO)))
            .andExpect(status().isCreated());

        // Validate the SherkatBime in the database
        List<SherkatBime> sherkatBimeList = sherkatBimeRepository.findAll();
        assertThat(sherkatBimeList).hasSize(databaseSizeBeforeCreate + 1);
        SherkatBime testSherkatBime = sherkatBimeList.get(sherkatBimeList.size() - 1);
        assertThat(testSherkatBime.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSherkatBime.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createSherkatBimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sherkatBimeRepository.findAll().size();

        // Create the SherkatBime with an existing ID
        sherkatBime.setId(1L);
        SherkatBimeDTO sherkatBimeDTO = sherkatBimeMapper.toDto(sherkatBime);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSherkatBimeMockMvc.perform(post("/api/sherkat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sherkatBimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SherkatBime in the database
        List<SherkatBime> sherkatBimeList = sherkatBimeRepository.findAll();
        assertThat(sherkatBimeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSherkatBimes() throws Exception {
        // Initialize the database
        sherkatBimeRepository.saveAndFlush(sherkatBime);

        // Get all the sherkatBimeList
        restSherkatBimeMockMvc.perform(get("/api/sherkat-bimes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sherkatBime.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSherkatBime() throws Exception {
        // Initialize the database
        sherkatBimeRepository.saveAndFlush(sherkatBime);

        // Get the sherkatBime
        restSherkatBimeMockMvc.perform(get("/api/sherkat-bimes/{id}", sherkatBime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sherkatBime.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSherkatBime() throws Exception {
        // Get the sherkatBime
        restSherkatBimeMockMvc.perform(get("/api/sherkat-bimes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSherkatBime() throws Exception {
        // Initialize the database
        sherkatBimeRepository.saveAndFlush(sherkatBime);

        int databaseSizeBeforeUpdate = sherkatBimeRepository.findAll().size();

        // Update the sherkatBime
        SherkatBime updatedSherkatBime = sherkatBimeRepository.findById(sherkatBime.getId()).get();
        // Disconnect from session so that the updates on updatedSherkatBime are not directly saved in db
        em.detach(updatedSherkatBime);
        updatedSherkatBime
            .name(UPDATED_NAME)
            .faal(UPDATED_FAAL);
        SherkatBimeDTO sherkatBimeDTO = sherkatBimeMapper.toDto(updatedSherkatBime);

        restSherkatBimeMockMvc.perform(put("/api/sherkat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sherkatBimeDTO)))
            .andExpect(status().isOk());

        // Validate the SherkatBime in the database
        List<SherkatBime> sherkatBimeList = sherkatBimeRepository.findAll();
        assertThat(sherkatBimeList).hasSize(databaseSizeBeforeUpdate);
        SherkatBime testSherkatBime = sherkatBimeList.get(sherkatBimeList.size() - 1);
        assertThat(testSherkatBime.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSherkatBime.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSherkatBime() throws Exception {
        int databaseSizeBeforeUpdate = sherkatBimeRepository.findAll().size();

        // Create the SherkatBime
        SherkatBimeDTO sherkatBimeDTO = sherkatBimeMapper.toDto(sherkatBime);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSherkatBimeMockMvc.perform(put("/api/sherkat-bimes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sherkatBimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SherkatBime in the database
        List<SherkatBime> sherkatBimeList = sherkatBimeRepository.findAll();
        assertThat(sherkatBimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSherkatBime() throws Exception {
        // Initialize the database
        sherkatBimeRepository.saveAndFlush(sherkatBime);

        int databaseSizeBeforeDelete = sherkatBimeRepository.findAll().size();

        // Get the sherkatBime
        restSherkatBimeMockMvc.perform(delete("/api/sherkat-bimes/{id}", sherkatBime.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SherkatBime> sherkatBimeList = sherkatBimeRepository.findAll();
        assertThat(sherkatBimeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SherkatBime.class);
        SherkatBime sherkatBime1 = new SherkatBime();
        sherkatBime1.setId(1L);
        SherkatBime sherkatBime2 = new SherkatBime();
        sherkatBime2.setId(sherkatBime1.getId());
        assertThat(sherkatBime1).isEqualTo(sherkatBime2);
        sherkatBime2.setId(2L);
        assertThat(sherkatBime1).isNotEqualTo(sherkatBime2);
        sherkatBime1.setId(null);
        assertThat(sherkatBime1).isNotEqualTo(sherkatBime2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SherkatBimeDTO.class);
        SherkatBimeDTO sherkatBimeDTO1 = new SherkatBimeDTO();
        sherkatBimeDTO1.setId(1L);
        SherkatBimeDTO sherkatBimeDTO2 = new SherkatBimeDTO();
        assertThat(sherkatBimeDTO1).isNotEqualTo(sherkatBimeDTO2);
        sherkatBimeDTO2.setId(sherkatBimeDTO1.getId());
        assertThat(sherkatBimeDTO1).isEqualTo(sherkatBimeDTO2);
        sherkatBimeDTO2.setId(2L);
        assertThat(sherkatBimeDTO1).isNotEqualTo(sherkatBimeDTO2);
        sherkatBimeDTO1.setId(null);
        assertThat(sherkatBimeDTO1).isNotEqualTo(sherkatBimeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sherkatBimeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sherkatBimeMapper.fromId(null)).isNull();
    }
}
