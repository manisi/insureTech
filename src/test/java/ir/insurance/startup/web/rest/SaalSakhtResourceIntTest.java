package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.SaalSakht;
import ir.insurance.startup.repository.SaalSakhtRepository;
import ir.insurance.startup.service.SaalSakhtService;
import ir.insurance.startup.service.dto.SaalSakhtDTO;
import ir.insurance.startup.service.mapper.SaalSakhtMapper;
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
 * Test class for the SaalSakhtResource REST controller.
 *
 * @see SaalSakhtResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SaalSakhtResourceIntTest {

    private static final String DEFAULT_SAAL_SHAMSI = "AAAA";
    private static final String UPDATED_SAAL_SHAMSI = "BBBB";

    private static final String DEFAULT_SAAL_MILADI = "AAAA";
    private static final String UPDATED_SAAL_MILADI = "BBBB";

    @Autowired
    private SaalSakhtRepository saalSakhtRepository;

    @Autowired
    private SaalSakhtMapper saalSakhtMapper;

    @Autowired
    private SaalSakhtService saalSakhtService;

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

    private MockMvc restSaalSakhtMockMvc;

    private SaalSakht saalSakht;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SaalSakhtResource saalSakhtResource = new SaalSakhtResource(saalSakhtService);
        this.restSaalSakhtMockMvc = MockMvcBuilders.standaloneSetup(saalSakhtResource)
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
    public static SaalSakht createEntity(EntityManager em) {
        SaalSakht saalSakht = new SaalSakht()
            .saalShamsi(DEFAULT_SAAL_SHAMSI)
            .saalMiladi(DEFAULT_SAAL_MILADI);
        return saalSakht;
    }

    @Before
    public void initTest() {
        saalSakht = createEntity(em);
    }

    @Test
    @Transactional
    public void createSaalSakht() throws Exception {
        int databaseSizeBeforeCreate = saalSakhtRepository.findAll().size();

        // Create the SaalSakht
        SaalSakhtDTO saalSakhtDTO = saalSakhtMapper.toDto(saalSakht);
        restSaalSakhtMockMvc.perform(post("/api/saal-sakhts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saalSakhtDTO)))
            .andExpect(status().isCreated());

        // Validate the SaalSakht in the database
        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeCreate + 1);
        SaalSakht testSaalSakht = saalSakhtList.get(saalSakhtList.size() - 1);
        assertThat(testSaalSakht.getSaalShamsi()).isEqualTo(DEFAULT_SAAL_SHAMSI);
        assertThat(testSaalSakht.getSaalMiladi()).isEqualTo(DEFAULT_SAAL_MILADI);
    }

    @Test
    @Transactional
    public void createSaalSakhtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = saalSakhtRepository.findAll().size();

        // Create the SaalSakht with an existing ID
        saalSakht.setId(1L);
        SaalSakhtDTO saalSakhtDTO = saalSakhtMapper.toDto(saalSakht);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSaalSakhtMockMvc.perform(post("/api/saal-sakhts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saalSakhtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SaalSakht in the database
        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSaalShamsiIsRequired() throws Exception {
        int databaseSizeBeforeTest = saalSakhtRepository.findAll().size();
        // set the field null
        saalSakht.setSaalShamsi(null);

        // Create the SaalSakht, which fails.
        SaalSakhtDTO saalSakhtDTO = saalSakhtMapper.toDto(saalSakht);

        restSaalSakhtMockMvc.perform(post("/api/saal-sakhts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saalSakhtDTO)))
            .andExpect(status().isBadRequest());

        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSaalMiladiIsRequired() throws Exception {
        int databaseSizeBeforeTest = saalSakhtRepository.findAll().size();
        // set the field null
        saalSakht.setSaalMiladi(null);

        // Create the SaalSakht, which fails.
        SaalSakhtDTO saalSakhtDTO = saalSakhtMapper.toDto(saalSakht);

        restSaalSakhtMockMvc.perform(post("/api/saal-sakhts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saalSakhtDTO)))
            .andExpect(status().isBadRequest());

        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSaalSakhts() throws Exception {
        // Initialize the database
        saalSakhtRepository.saveAndFlush(saalSakht);

        // Get all the saalSakhtList
        restSaalSakhtMockMvc.perform(get("/api/saal-sakhts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(saalSakht.getId().intValue())))
            .andExpect(jsonPath("$.[*].saalShamsi").value(hasItem(DEFAULT_SAAL_SHAMSI.toString())))
            .andExpect(jsonPath("$.[*].saalMiladi").value(hasItem(DEFAULT_SAAL_MILADI.toString())));
    }
    
    @Test
    @Transactional
    public void getSaalSakht() throws Exception {
        // Initialize the database
        saalSakhtRepository.saveAndFlush(saalSakht);

        // Get the saalSakht
        restSaalSakhtMockMvc.perform(get("/api/saal-sakhts/{id}", saalSakht.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(saalSakht.getId().intValue()))
            .andExpect(jsonPath("$.saalShamsi").value(DEFAULT_SAAL_SHAMSI.toString()))
            .andExpect(jsonPath("$.saalMiladi").value(DEFAULT_SAAL_MILADI.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSaalSakht() throws Exception {
        // Get the saalSakht
        restSaalSakhtMockMvc.perform(get("/api/saal-sakhts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSaalSakht() throws Exception {
        // Initialize the database
        saalSakhtRepository.saveAndFlush(saalSakht);

        int databaseSizeBeforeUpdate = saalSakhtRepository.findAll().size();

        // Update the saalSakht
        SaalSakht updatedSaalSakht = saalSakhtRepository.findById(saalSakht.getId()).get();
        // Disconnect from session so that the updates on updatedSaalSakht are not directly saved in db
        em.detach(updatedSaalSakht);
        updatedSaalSakht
            .saalShamsi(UPDATED_SAAL_SHAMSI)
            .saalMiladi(UPDATED_SAAL_MILADI);
        SaalSakhtDTO saalSakhtDTO = saalSakhtMapper.toDto(updatedSaalSakht);

        restSaalSakhtMockMvc.perform(put("/api/saal-sakhts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saalSakhtDTO)))
            .andExpect(status().isOk());

        // Validate the SaalSakht in the database
        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeUpdate);
        SaalSakht testSaalSakht = saalSakhtList.get(saalSakhtList.size() - 1);
        assertThat(testSaalSakht.getSaalShamsi()).isEqualTo(UPDATED_SAAL_SHAMSI);
        assertThat(testSaalSakht.getSaalMiladi()).isEqualTo(UPDATED_SAAL_MILADI);
    }

    @Test
    @Transactional
    public void updateNonExistingSaalSakht() throws Exception {
        int databaseSizeBeforeUpdate = saalSakhtRepository.findAll().size();

        // Create the SaalSakht
        SaalSakhtDTO saalSakhtDTO = saalSakhtMapper.toDto(saalSakht);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSaalSakhtMockMvc.perform(put("/api/saal-sakhts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(saalSakhtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SaalSakht in the database
        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSaalSakht() throws Exception {
        // Initialize the database
        saalSakhtRepository.saveAndFlush(saalSakht);

        int databaseSizeBeforeDelete = saalSakhtRepository.findAll().size();

        // Delete the saalSakht
        restSaalSakhtMockMvc.perform(delete("/api/saal-sakhts/{id}", saalSakht.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SaalSakht> saalSakhtList = saalSakhtRepository.findAll();
        assertThat(saalSakhtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaalSakht.class);
        SaalSakht saalSakht1 = new SaalSakht();
        saalSakht1.setId(1L);
        SaalSakht saalSakht2 = new SaalSakht();
        saalSakht2.setId(saalSakht1.getId());
        assertThat(saalSakht1).isEqualTo(saalSakht2);
        saalSakht2.setId(2L);
        assertThat(saalSakht1).isNotEqualTo(saalSakht2);
        saalSakht1.setId(null);
        assertThat(saalSakht1).isNotEqualTo(saalSakht2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaalSakhtDTO.class);
        SaalSakhtDTO saalSakhtDTO1 = new SaalSakhtDTO();
        saalSakhtDTO1.setId(1L);
        SaalSakhtDTO saalSakhtDTO2 = new SaalSakhtDTO();
        assertThat(saalSakhtDTO1).isNotEqualTo(saalSakhtDTO2);
        saalSakhtDTO2.setId(saalSakhtDTO1.getId());
        assertThat(saalSakhtDTO1).isEqualTo(saalSakhtDTO2);
        saalSakhtDTO2.setId(2L);
        assertThat(saalSakhtDTO1).isNotEqualTo(saalSakhtDTO2);
        saalSakhtDTO1.setId(null);
        assertThat(saalSakhtDTO1).isNotEqualTo(saalSakhtDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(saalSakhtMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(saalSakhtMapper.fromId(null)).isNull();
    }
}
