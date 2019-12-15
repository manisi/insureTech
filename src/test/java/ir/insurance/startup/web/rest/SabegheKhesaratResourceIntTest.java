package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.SabegheKhesarat;
import ir.insurance.startup.repository.SabegheKhesaratRepository;
import ir.insurance.startup.service.SabegheKhesaratService;
import ir.insurance.startup.service.dto.SabegheKhesaratDTO;
import ir.insurance.startup.service.mapper.SabegheKhesaratMapper;
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
 * Test class for the SabegheKhesaratResource REST controller.
 *
 * @see SabegheKhesaratResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SabegheKhesaratResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private SabegheKhesaratRepository sabegheKhesaratRepository;

    @Autowired
    private SabegheKhesaratMapper sabegheKhesaratMapper;

    @Autowired
    private SabegheKhesaratService sabegheKhesaratService;

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

    private MockMvc restSabegheKhesaratMockMvc;

    private SabegheKhesarat sabegheKhesarat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SabegheKhesaratResource sabegheKhesaratResource = new SabegheKhesaratResource(sabegheKhesaratService);
        this.restSabegheKhesaratMockMvc = MockMvcBuilders.standaloneSetup(sabegheKhesaratResource)
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
    public static SabegheKhesarat createEntity(EntityManager em) {
        SabegheKhesarat sabegheKhesarat = new SabegheKhesarat()
            .name(DEFAULT_NAME)
            .sharh(DEFAULT_SHARH)
            .faal(DEFAULT_FAAL);
        return sabegheKhesarat;
    }

    @Before
    public void initTest() {
        sabegheKhesarat = createEntity(em);
    }

    @Test
    @Transactional
    public void createSabegheKhesarat() throws Exception {
        int databaseSizeBeforeCreate = sabegheKhesaratRepository.findAll().size();

        // Create the SabegheKhesarat
        SabegheKhesaratDTO sabegheKhesaratDTO = sabegheKhesaratMapper.toDto(sabegheKhesarat);
        restSabegheKhesaratMockMvc.perform(post("/api/sabeghe-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheKhesaratDTO)))
            .andExpect(status().isCreated());

        // Validate the SabegheKhesarat in the database
        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeCreate + 1);
        SabegheKhesarat testSabegheKhesarat = sabegheKhesaratList.get(sabegheKhesaratList.size() - 1);
        assertThat(testSabegheKhesarat.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSabegheKhesarat.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testSabegheKhesarat.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createSabegheKhesaratWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sabegheKhesaratRepository.findAll().size();

        // Create the SabegheKhesarat with an existing ID
        sabegheKhesarat.setId(1L);
        SabegheKhesaratDTO sabegheKhesaratDTO = sabegheKhesaratMapper.toDto(sabegheKhesarat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSabegheKhesaratMockMvc.perform(post("/api/sabeghe-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheKhesaratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SabegheKhesarat in the database
        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sabegheKhesaratRepository.findAll().size();
        // set the field null
        sabegheKhesarat.setName(null);

        // Create the SabegheKhesarat, which fails.
        SabegheKhesaratDTO sabegheKhesaratDTO = sabegheKhesaratMapper.toDto(sabegheKhesarat);

        restSabegheKhesaratMockMvc.perform(post("/api/sabeghe-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheKhesaratDTO)))
            .andExpect(status().isBadRequest());

        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = sabegheKhesaratRepository.findAll().size();
        // set the field null
        sabegheKhesarat.setFaal(null);

        // Create the SabegheKhesarat, which fails.
        SabegheKhesaratDTO sabegheKhesaratDTO = sabegheKhesaratMapper.toDto(sabegheKhesarat);

        restSabegheKhesaratMockMvc.perform(post("/api/sabeghe-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheKhesaratDTO)))
            .andExpect(status().isBadRequest());

        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSabegheKhesarats() throws Exception {
        // Initialize the database
        sabegheKhesaratRepository.saveAndFlush(sabegheKhesarat);

        // Get all the sabegheKhesaratList
        restSabegheKhesaratMockMvc.perform(get("/api/sabeghe-khesarats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sabegheKhesarat.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSabegheKhesarat() throws Exception {
        // Initialize the database
        sabegheKhesaratRepository.saveAndFlush(sabegheKhesarat);

        // Get the sabegheKhesarat
        restSabegheKhesaratMockMvc.perform(get("/api/sabeghe-khesarats/{id}", sabegheKhesarat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sabegheKhesarat.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSabegheKhesarat() throws Exception {
        // Get the sabegheKhesarat
        restSabegheKhesaratMockMvc.perform(get("/api/sabeghe-khesarats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSabegheKhesarat() throws Exception {
        // Initialize the database
        sabegheKhesaratRepository.saveAndFlush(sabegheKhesarat);

        int databaseSizeBeforeUpdate = sabegheKhesaratRepository.findAll().size();

        // Update the sabegheKhesarat
        SabegheKhesarat updatedSabegheKhesarat = sabegheKhesaratRepository.findById(sabegheKhesarat.getId()).get();
        // Disconnect from session so that the updates on updatedSabegheKhesarat are not directly saved in db
        em.detach(updatedSabegheKhesarat);
        updatedSabegheKhesarat
            .name(UPDATED_NAME)
            .sharh(UPDATED_SHARH)
            .faal(UPDATED_FAAL);
        SabegheKhesaratDTO sabegheKhesaratDTO = sabegheKhesaratMapper.toDto(updatedSabegheKhesarat);

        restSabegheKhesaratMockMvc.perform(put("/api/sabeghe-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheKhesaratDTO)))
            .andExpect(status().isOk());

        // Validate the SabegheKhesarat in the database
        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeUpdate);
        SabegheKhesarat testSabegheKhesarat = sabegheKhesaratList.get(sabegheKhesaratList.size() - 1);
        assertThat(testSabegheKhesarat.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSabegheKhesarat.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testSabegheKhesarat.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSabegheKhesarat() throws Exception {
        int databaseSizeBeforeUpdate = sabegheKhesaratRepository.findAll().size();

        // Create the SabegheKhesarat
        SabegheKhesaratDTO sabegheKhesaratDTO = sabegheKhesaratMapper.toDto(sabegheKhesarat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSabegheKhesaratMockMvc.perform(put("/api/sabeghe-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheKhesaratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SabegheKhesarat in the database
        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSabegheKhesarat() throws Exception {
        // Initialize the database
        sabegheKhesaratRepository.saveAndFlush(sabegheKhesarat);

        int databaseSizeBeforeDelete = sabegheKhesaratRepository.findAll().size();

        // Delete the sabegheKhesarat
        restSabegheKhesaratMockMvc.perform(delete("/api/sabeghe-khesarats/{id}", sabegheKhesarat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SabegheKhesarat> sabegheKhesaratList = sabegheKhesaratRepository.findAll();
        assertThat(sabegheKhesaratList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SabegheKhesarat.class);
        SabegheKhesarat sabegheKhesarat1 = new SabegheKhesarat();
        sabegheKhesarat1.setId(1L);
        SabegheKhesarat sabegheKhesarat2 = new SabegheKhesarat();
        sabegheKhesarat2.setId(sabegheKhesarat1.getId());
        assertThat(sabegheKhesarat1).isEqualTo(sabegheKhesarat2);
        sabegheKhesarat2.setId(2L);
        assertThat(sabegheKhesarat1).isNotEqualTo(sabegheKhesarat2);
        sabegheKhesarat1.setId(null);
        assertThat(sabegheKhesarat1).isNotEqualTo(sabegheKhesarat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SabegheKhesaratDTO.class);
        SabegheKhesaratDTO sabegheKhesaratDTO1 = new SabegheKhesaratDTO();
        sabegheKhesaratDTO1.setId(1L);
        SabegheKhesaratDTO sabegheKhesaratDTO2 = new SabegheKhesaratDTO();
        assertThat(sabegheKhesaratDTO1).isNotEqualTo(sabegheKhesaratDTO2);
        sabegheKhesaratDTO2.setId(sabegheKhesaratDTO1.getId());
        assertThat(sabegheKhesaratDTO1).isEqualTo(sabegheKhesaratDTO2);
        sabegheKhesaratDTO2.setId(2L);
        assertThat(sabegheKhesaratDTO1).isNotEqualTo(sabegheKhesaratDTO2);
        sabegheKhesaratDTO1.setId(null);
        assertThat(sabegheKhesaratDTO1).isNotEqualTo(sabegheKhesaratDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sabegheKhesaratMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sabegheKhesaratMapper.fromId(null)).isNull();
    }
}
