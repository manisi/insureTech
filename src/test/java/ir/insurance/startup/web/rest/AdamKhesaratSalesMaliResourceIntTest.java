package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.AdamKhesaratSalesMali;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.repository.AdamKhesaratSalesMaliRepository;
import ir.insurance.startup.service.AdamKhesaratSalesMaliService;
import ir.insurance.startup.service.dto.AdamKhesaratSalesMaliDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratSalesMaliMapper;
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
 * Test class for the AdamKhesaratSalesMaliResource REST controller.
 *
 * @see AdamKhesaratSalesMaliResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class AdamKhesaratSalesMaliResourceIntTest {

    private static final Float DEFAULT_SALES_MALI = 0F;
    private static final Float UPDATED_SALES_MALI = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private AdamKhesaratSalesMaliRepository adamKhesaratSalesMaliRepository;

    @Autowired
    private AdamKhesaratSalesMaliMapper adamKhesaratSalesMaliMapper;

    @Autowired
    private AdamKhesaratSalesMaliService adamKhesaratSalesMaliService;

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

    private MockMvc restAdamKhesaratSalesMaliMockMvc;

    private AdamKhesaratSalesMali adamKhesaratSalesMali;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdamKhesaratSalesMaliResource adamKhesaratSalesMaliResource = new AdamKhesaratSalesMaliResource(adamKhesaratSalesMaliService);
        this.restAdamKhesaratSalesMaliMockMvc = MockMvcBuilders.standaloneSetup(adamKhesaratSalesMaliResource)
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
    public static AdamKhesaratSalesMali createEntity(EntityManager em) {
        AdamKhesaratSalesMali adamKhesaratSalesMali = new AdamKhesaratSalesMali()
            .salesMali(DEFAULT_SALES_MALI)
            .faal(DEFAULT_FAAL);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesaratSalesMali.setSabeghe(sabeghe);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesaratSalesMali.setNoeSabeghe(noeSabeghe);
        return adamKhesaratSalesMali;
    }

    @Before
    public void initTest() {
        adamKhesaratSalesMali = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdamKhesaratSalesMali() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratSalesMaliRepository.findAll().size();

        // Create the AdamKhesaratSalesMali
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliMapper.toDto(adamKhesaratSalesMali);
        restAdamKhesaratSalesMaliMockMvc.perform(post("/api/adam-khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSalesMaliDTO)))
            .andExpect(status().isCreated());

        // Validate the AdamKhesaratSalesMali in the database
        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeCreate + 1);
        AdamKhesaratSalesMali testAdamKhesaratSalesMali = adamKhesaratSalesMaliList.get(adamKhesaratSalesMaliList.size() - 1);
        assertThat(testAdamKhesaratSalesMali.getSalesMali()).isEqualTo(DEFAULT_SALES_MALI);
        assertThat(testAdamKhesaratSalesMali.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createAdamKhesaratSalesMaliWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratSalesMaliRepository.findAll().size();

        // Create the AdamKhesaratSalesMali with an existing ID
        adamKhesaratSalesMali.setId(1L);
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliMapper.toDto(adamKhesaratSalesMali);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdamKhesaratSalesMaliMockMvc.perform(post("/api/adam-khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesaratSalesMali in the database
        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSalesMaliIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratSalesMaliRepository.findAll().size();
        // set the field null
        adamKhesaratSalesMali.setSalesMali(null);

        // Create the AdamKhesaratSalesMali, which fails.
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliMapper.toDto(adamKhesaratSalesMali);

        restAdamKhesaratSalesMaliMockMvc.perform(post("/api/adam-khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratSalesMaliRepository.findAll().size();
        // set the field null
        adamKhesaratSalesMali.setFaal(null);

        // Create the AdamKhesaratSalesMali, which fails.
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliMapper.toDto(adamKhesaratSalesMali);

        restAdamKhesaratSalesMaliMockMvc.perform(post("/api/adam-khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSalesMalis() throws Exception {
        // Initialize the database
        adamKhesaratSalesMaliRepository.saveAndFlush(adamKhesaratSalesMali);

        // Get all the adamKhesaratSalesMaliList
        restAdamKhesaratSalesMaliMockMvc.perform(get("/api/adam-khesarat-sales-malis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesaratSalesMali.getId().intValue())))
            .andExpect(jsonPath("$.[*].salesMali").value(hasItem(DEFAULT_SALES_MALI.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdamKhesaratSalesMali() throws Exception {
        // Initialize the database
        adamKhesaratSalesMaliRepository.saveAndFlush(adamKhesaratSalesMali);

        // Get the adamKhesaratSalesMali
        restAdamKhesaratSalesMaliMockMvc.perform(get("/api/adam-khesarat-sales-malis/{id}", adamKhesaratSalesMali.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adamKhesaratSalesMali.getId().intValue()))
            .andExpect(jsonPath("$.salesMali").value(DEFAULT_SALES_MALI.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAdamKhesaratSalesMali() throws Exception {
        // Get the adamKhesaratSalesMali
        restAdamKhesaratSalesMaliMockMvc.perform(get("/api/adam-khesarat-sales-malis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdamKhesaratSalesMali() throws Exception {
        // Initialize the database
        adamKhesaratSalesMaliRepository.saveAndFlush(adamKhesaratSalesMali);

        int databaseSizeBeforeUpdate = adamKhesaratSalesMaliRepository.findAll().size();

        // Update the adamKhesaratSalesMali
        AdamKhesaratSalesMali updatedAdamKhesaratSalesMali = adamKhesaratSalesMaliRepository.findById(adamKhesaratSalesMali.getId()).get();
        // Disconnect from session so that the updates on updatedAdamKhesaratSalesMali are not directly saved in db
        em.detach(updatedAdamKhesaratSalesMali);
        updatedAdamKhesaratSalesMali
            .salesMali(UPDATED_SALES_MALI)
            .faal(UPDATED_FAAL);
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliMapper.toDto(updatedAdamKhesaratSalesMali);

        restAdamKhesaratSalesMaliMockMvc.perform(put("/api/adam-khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSalesMaliDTO)))
            .andExpect(status().isOk());

        // Validate the AdamKhesaratSalesMali in the database
        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeUpdate);
        AdamKhesaratSalesMali testAdamKhesaratSalesMali = adamKhesaratSalesMaliList.get(adamKhesaratSalesMaliList.size() - 1);
        assertThat(testAdamKhesaratSalesMali.getSalesMali()).isEqualTo(UPDATED_SALES_MALI);
        assertThat(testAdamKhesaratSalesMali.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAdamKhesaratSalesMali() throws Exception {
        int databaseSizeBeforeUpdate = adamKhesaratSalesMaliRepository.findAll().size();

        // Create the AdamKhesaratSalesMali
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliMapper.toDto(adamKhesaratSalesMali);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdamKhesaratSalesMaliMockMvc.perform(put("/api/adam-khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesaratSalesMali in the database
        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdamKhesaratSalesMali() throws Exception {
        // Initialize the database
        adamKhesaratSalesMaliRepository.saveAndFlush(adamKhesaratSalesMali);

        int databaseSizeBeforeDelete = adamKhesaratSalesMaliRepository.findAll().size();

        // Delete the adamKhesaratSalesMali
        restAdamKhesaratSalesMaliMockMvc.perform(delete("/api/adam-khesarat-sales-malis/{id}", adamKhesaratSalesMali.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdamKhesaratSalesMali> adamKhesaratSalesMaliList = adamKhesaratSalesMaliRepository.findAll();
        assertThat(adamKhesaratSalesMaliList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratSalesMali.class);
        AdamKhesaratSalesMali adamKhesaratSalesMali1 = new AdamKhesaratSalesMali();
        adamKhesaratSalesMali1.setId(1L);
        AdamKhesaratSalesMali adamKhesaratSalesMali2 = new AdamKhesaratSalesMali();
        adamKhesaratSalesMali2.setId(adamKhesaratSalesMali1.getId());
        assertThat(adamKhesaratSalesMali1).isEqualTo(adamKhesaratSalesMali2);
        adamKhesaratSalesMali2.setId(2L);
        assertThat(adamKhesaratSalesMali1).isNotEqualTo(adamKhesaratSalesMali2);
        adamKhesaratSalesMali1.setId(null);
        assertThat(adamKhesaratSalesMali1).isNotEqualTo(adamKhesaratSalesMali2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratSalesMaliDTO.class);
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO1 = new AdamKhesaratSalesMaliDTO();
        adamKhesaratSalesMaliDTO1.setId(1L);
        AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO2 = new AdamKhesaratSalesMaliDTO();
        assertThat(adamKhesaratSalesMaliDTO1).isNotEqualTo(adamKhesaratSalesMaliDTO2);
        adamKhesaratSalesMaliDTO2.setId(adamKhesaratSalesMaliDTO1.getId());
        assertThat(adamKhesaratSalesMaliDTO1).isEqualTo(adamKhesaratSalesMaliDTO2);
        adamKhesaratSalesMaliDTO2.setId(2L);
        assertThat(adamKhesaratSalesMaliDTO1).isNotEqualTo(adamKhesaratSalesMaliDTO2);
        adamKhesaratSalesMaliDTO1.setId(null);
        assertThat(adamKhesaratSalesMaliDTO1).isNotEqualTo(adamKhesaratSalesMaliDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adamKhesaratSalesMaliMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adamKhesaratSalesMaliMapper.fromId(null)).isNull();
    }
}
