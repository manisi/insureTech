package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.TakhfifTavafoghi;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.repository.TakhfifTavafoghiRepository;
import ir.insurance.startup.service.TakhfifTavafoghiService;
import ir.insurance.startup.service.dto.TakhfifTavafoghiDTO;
import ir.insurance.startup.service.mapper.TakhfifTavafoghiMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TakhfifTavafoghiResource REST controller.
 *
 * @see TakhfifTavafoghiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class TakhfifTavafoghiResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_DARSAD_TAKHFIF = 0F;
    private static final Float UPDATED_DARSAD_TAKHFIF = 1F;

    private static final LocalDate DEFAULT_AZ_TARIKH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AZ_TARIKH = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private TakhfifTavafoghiRepository takhfifTavafoghiRepository;

    @Autowired
    private TakhfifTavafoghiMapper takhfifTavafoghiMapper;

    @Autowired
    private TakhfifTavafoghiService takhfifTavafoghiService;

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

    private MockMvc restTakhfifTavafoghiMockMvc;

    private TakhfifTavafoghi takhfifTavafoghi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TakhfifTavafoghiResource takhfifTavafoghiResource = new TakhfifTavafoghiResource(takhfifTavafoghiService);
        this.restTakhfifTavafoghiMockMvc = MockMvcBuilders.standaloneSetup(takhfifTavafoghiResource)
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
    public static TakhfifTavafoghi createEntity(EntityManager em) {
        TakhfifTavafoghi takhfifTavafoghi = new TakhfifTavafoghi()
            .name(DEFAULT_NAME)
            .darsadTakhfif(DEFAULT_DARSAD_TAKHFIF)
            .azTarikh(DEFAULT_AZ_TARIKH)
            .faal(DEFAULT_FAAL);
        // Add required entity
        SherkatBime sherkatBime = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(sherkatBime);
        em.flush();
        takhfifTavafoghi.setSherkatBime(sherkatBime);
        return takhfifTavafoghi;
    }

    @Before
    public void initTest() {
        takhfifTavafoghi = createEntity(em);
    }

    @Test
    @Transactional
    public void createTakhfifTavafoghi() throws Exception {
        int databaseSizeBeforeCreate = takhfifTavafoghiRepository.findAll().size();

        // Create the TakhfifTavafoghi
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);
        restTakhfifTavafoghiMockMvc.perform(post("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isCreated());

        // Validate the TakhfifTavafoghi in the database
        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeCreate + 1);
        TakhfifTavafoghi testTakhfifTavafoghi = takhfifTavafoghiList.get(takhfifTavafoghiList.size() - 1);
        assertThat(testTakhfifTavafoghi.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTakhfifTavafoghi.getDarsadTakhfif()).isEqualTo(DEFAULT_DARSAD_TAKHFIF);
        assertThat(testTakhfifTavafoghi.getAzTarikh()).isEqualTo(DEFAULT_AZ_TARIKH);
        assertThat(testTakhfifTavafoghi.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createTakhfifTavafoghiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = takhfifTavafoghiRepository.findAll().size();

        // Create the TakhfifTavafoghi with an existing ID
        takhfifTavafoghi.setId(1L);
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTakhfifTavafoghiMockMvc.perform(post("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TakhfifTavafoghi in the database
        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = takhfifTavafoghiRepository.findAll().size();
        // set the field null
        takhfifTavafoghi.setName(null);

        // Create the TakhfifTavafoghi, which fails.
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);

        restTakhfifTavafoghiMockMvc.perform(post("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isBadRequest());

        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDarsadTakhfifIsRequired() throws Exception {
        int databaseSizeBeforeTest = takhfifTavafoghiRepository.findAll().size();
        // set the field null
        takhfifTavafoghi.setDarsadTakhfif(null);

        // Create the TakhfifTavafoghi, which fails.
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);

        restTakhfifTavafoghiMockMvc.perform(post("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isBadRequest());

        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAzTarikhIsRequired() throws Exception {
        int databaseSizeBeforeTest = takhfifTavafoghiRepository.findAll().size();
        // set the field null
        takhfifTavafoghi.setAzTarikh(null);

        // Create the TakhfifTavafoghi, which fails.
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);

        restTakhfifTavafoghiMockMvc.perform(post("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isBadRequest());

        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = takhfifTavafoghiRepository.findAll().size();
        // set the field null
        takhfifTavafoghi.setFaal(null);

        // Create the TakhfifTavafoghi, which fails.
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);

        restTakhfifTavafoghiMockMvc.perform(post("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isBadRequest());

        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTakhfifTavafoghis() throws Exception {
        // Initialize the database
        takhfifTavafoghiRepository.saveAndFlush(takhfifTavafoghi);

        // Get all the takhfifTavafoghiList
        restTakhfifTavafoghiMockMvc.perform(get("/api/takhfif-tavafoghis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(takhfifTavafoghi.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].darsadTakhfif").value(hasItem(DEFAULT_DARSAD_TAKHFIF.doubleValue())))
            .andExpect(jsonPath("$.[*].azTarikh").value(hasItem(DEFAULT_AZ_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTakhfifTavafoghi() throws Exception {
        // Initialize the database
        takhfifTavafoghiRepository.saveAndFlush(takhfifTavafoghi);

        // Get the takhfifTavafoghi
        restTakhfifTavafoghiMockMvc.perform(get("/api/takhfif-tavafoghis/{id}", takhfifTavafoghi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(takhfifTavafoghi.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.darsadTakhfif").value(DEFAULT_DARSAD_TAKHFIF.doubleValue()))
            .andExpect(jsonPath("$.azTarikh").value(DEFAULT_AZ_TARIKH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTakhfifTavafoghi() throws Exception {
        // Get the takhfifTavafoghi
        restTakhfifTavafoghiMockMvc.perform(get("/api/takhfif-tavafoghis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTakhfifTavafoghi() throws Exception {
        // Initialize the database
        takhfifTavafoghiRepository.saveAndFlush(takhfifTavafoghi);

        int databaseSizeBeforeUpdate = takhfifTavafoghiRepository.findAll().size();

        // Update the takhfifTavafoghi
        TakhfifTavafoghi updatedTakhfifTavafoghi = takhfifTavafoghiRepository.findById(takhfifTavafoghi.getId()).get();
        // Disconnect from session so that the updates on updatedTakhfifTavafoghi are not directly saved in db
        em.detach(updatedTakhfifTavafoghi);
        updatedTakhfifTavafoghi
            .name(UPDATED_NAME)
            .darsadTakhfif(UPDATED_DARSAD_TAKHFIF)
            .azTarikh(UPDATED_AZ_TARIKH)
            .faal(UPDATED_FAAL);
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(updatedTakhfifTavafoghi);

        restTakhfifTavafoghiMockMvc.perform(put("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isOk());

        // Validate the TakhfifTavafoghi in the database
        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeUpdate);
        TakhfifTavafoghi testTakhfifTavafoghi = takhfifTavafoghiList.get(takhfifTavafoghiList.size() - 1);
        assertThat(testTakhfifTavafoghi.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTakhfifTavafoghi.getDarsadTakhfif()).isEqualTo(UPDATED_DARSAD_TAKHFIF);
        assertThat(testTakhfifTavafoghi.getAzTarikh()).isEqualTo(UPDATED_AZ_TARIKH);
        assertThat(testTakhfifTavafoghi.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingTakhfifTavafoghi() throws Exception {
        int databaseSizeBeforeUpdate = takhfifTavafoghiRepository.findAll().size();

        // Create the TakhfifTavafoghi
        TakhfifTavafoghiDTO takhfifTavafoghiDTO = takhfifTavafoghiMapper.toDto(takhfifTavafoghi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTakhfifTavafoghiMockMvc.perform(put("/api/takhfif-tavafoghis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(takhfifTavafoghiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TakhfifTavafoghi in the database
        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTakhfifTavafoghi() throws Exception {
        // Initialize the database
        takhfifTavafoghiRepository.saveAndFlush(takhfifTavafoghi);

        int databaseSizeBeforeDelete = takhfifTavafoghiRepository.findAll().size();

        // Delete the takhfifTavafoghi
        restTakhfifTavafoghiMockMvc.perform(delete("/api/takhfif-tavafoghis/{id}", takhfifTavafoghi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TakhfifTavafoghi> takhfifTavafoghiList = takhfifTavafoghiRepository.findAll();
        assertThat(takhfifTavafoghiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TakhfifTavafoghi.class);
        TakhfifTavafoghi takhfifTavafoghi1 = new TakhfifTavafoghi();
        takhfifTavafoghi1.setId(1L);
        TakhfifTavafoghi takhfifTavafoghi2 = new TakhfifTavafoghi();
        takhfifTavafoghi2.setId(takhfifTavafoghi1.getId());
        assertThat(takhfifTavafoghi1).isEqualTo(takhfifTavafoghi2);
        takhfifTavafoghi2.setId(2L);
        assertThat(takhfifTavafoghi1).isNotEqualTo(takhfifTavafoghi2);
        takhfifTavafoghi1.setId(null);
        assertThat(takhfifTavafoghi1).isNotEqualTo(takhfifTavafoghi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TakhfifTavafoghiDTO.class);
        TakhfifTavafoghiDTO takhfifTavafoghiDTO1 = new TakhfifTavafoghiDTO();
        takhfifTavafoghiDTO1.setId(1L);
        TakhfifTavafoghiDTO takhfifTavafoghiDTO2 = new TakhfifTavafoghiDTO();
        assertThat(takhfifTavafoghiDTO1).isNotEqualTo(takhfifTavafoghiDTO2);
        takhfifTavafoghiDTO2.setId(takhfifTavafoghiDTO1.getId());
        assertThat(takhfifTavafoghiDTO1).isEqualTo(takhfifTavafoghiDTO2);
        takhfifTavafoghiDTO2.setId(2L);
        assertThat(takhfifTavafoghiDTO1).isNotEqualTo(takhfifTavafoghiDTO2);
        takhfifTavafoghiDTO1.setId(null);
        assertThat(takhfifTavafoghiDTO1).isNotEqualTo(takhfifTavafoghiDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(takhfifTavafoghiMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(takhfifTavafoghiMapper.fromId(null)).isNull();
    }
}
