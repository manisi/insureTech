package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.KhesaratSalesMali;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.repository.KhesaratSalesMaliRepository;
import ir.insurance.startup.service.KhesaratSalesMaliService;
import ir.insurance.startup.service.dto.KhesaratSalesMaliDTO;
import ir.insurance.startup.service.mapper.KhesaratSalesMaliMapper;
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

import ir.insurance.startup.domain.enumeration.SalesSarneshinEnum;
/**
 * Test class for the KhesaratSalesMaliResource REST controller.
 *
 * @see KhesaratSalesMaliResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class KhesaratSalesMaliResourceIntTest {

    private static final SalesSarneshinEnum DEFAULT_NOE = SalesSarneshinEnum.SALES;
    private static final SalesSarneshinEnum UPDATED_NOE = SalesSarneshinEnum.SARNESHIN;

    private static final Float DEFAULT_NERKH_KHESARAT_MALI = 0F;
    private static final Float UPDATED_NERKH_KHESARAT_MALI = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private KhesaratSalesMaliRepository khesaratSalesMaliRepository;

    @Autowired
    private KhesaratSalesMaliMapper khesaratSalesMaliMapper;

    @Autowired
    private KhesaratSalesMaliService khesaratSalesMaliService;

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

    private MockMvc restKhesaratSalesMaliMockMvc;

    private KhesaratSalesMali khesaratSalesMali;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KhesaratSalesMaliResource khesaratSalesMaliResource = new KhesaratSalesMaliResource(khesaratSalesMaliService);
        this.restKhesaratSalesMaliMockMvc = MockMvcBuilders.standaloneSetup(khesaratSalesMaliResource)
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
    public static KhesaratSalesMali createEntity(EntityManager em) {
        KhesaratSalesMali khesaratSalesMali = new KhesaratSalesMali()
            .noe(DEFAULT_NOE)
            .nerkhKhesaratMali(DEFAULT_NERKH_KHESARAT_MALI)
            .faal(DEFAULT_FAAL);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        khesaratSalesMali.setSabeghe(sabeghe);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        khesaratSalesMali.setNoeSabeghe(noeSabeghe);
        return khesaratSalesMali;
    }

    @Before
    public void initTest() {
        khesaratSalesMali = createEntity(em);
    }

    @Test
    @Transactional
    public void createKhesaratSalesMali() throws Exception {
        int databaseSizeBeforeCreate = khesaratSalesMaliRepository.findAll().size();

        // Create the KhesaratSalesMali
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(khesaratSalesMali);
        restKhesaratSalesMaliMockMvc.perform(post("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isCreated());

        // Validate the KhesaratSalesMali in the database
        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeCreate + 1);
        KhesaratSalesMali testKhesaratSalesMali = khesaratSalesMaliList.get(khesaratSalesMaliList.size() - 1);
        assertThat(testKhesaratSalesMali.getNoe()).isEqualTo(DEFAULT_NOE);
        assertThat(testKhesaratSalesMali.getNerkhKhesaratMali()).isEqualTo(DEFAULT_NERKH_KHESARAT_MALI);
        assertThat(testKhesaratSalesMali.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createKhesaratSalesMaliWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = khesaratSalesMaliRepository.findAll().size();

        // Create the KhesaratSalesMali with an existing ID
        khesaratSalesMali.setId(1L);
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(khesaratSalesMali);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKhesaratSalesMaliMockMvc.perform(post("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhesaratSalesMali in the database
        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoeIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSalesMaliRepository.findAll().size();
        // set the field null
        khesaratSalesMali.setNoe(null);

        // Create the KhesaratSalesMali, which fails.
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(khesaratSalesMali);

        restKhesaratSalesMaliMockMvc.perform(post("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNerkhKhesaratMaliIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSalesMaliRepository.findAll().size();
        // set the field null
        khesaratSalesMali.setNerkhKhesaratMali(null);

        // Create the KhesaratSalesMali, which fails.
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(khesaratSalesMali);

        restKhesaratSalesMaliMockMvc.perform(post("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSalesMaliRepository.findAll().size();
        // set the field null
        khesaratSalesMali.setFaal(null);

        // Create the KhesaratSalesMali, which fails.
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(khesaratSalesMali);

        restKhesaratSalesMaliMockMvc.perform(post("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesMalis() throws Exception {
        // Initialize the database
        khesaratSalesMaliRepository.saveAndFlush(khesaratSalesMali);

        // Get all the khesaratSalesMaliList
        restKhesaratSalesMaliMockMvc.perform(get("/api/khesarat-sales-malis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khesaratSalesMali.getId().intValue())))
            .andExpect(jsonPath("$.[*].noe").value(hasItem(DEFAULT_NOE.toString())))
            .andExpect(jsonPath("$.[*].nerkhKhesaratMali").value(hasItem(DEFAULT_NERKH_KHESARAT_MALI.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKhesaratSalesMali() throws Exception {
        // Initialize the database
        khesaratSalesMaliRepository.saveAndFlush(khesaratSalesMali);

        // Get the khesaratSalesMali
        restKhesaratSalesMaliMockMvc.perform(get("/api/khesarat-sales-malis/{id}", khesaratSalesMali.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(khesaratSalesMali.getId().intValue()))
            .andExpect(jsonPath("$.noe").value(DEFAULT_NOE.toString()))
            .andExpect(jsonPath("$.nerkhKhesaratMali").value(DEFAULT_NERKH_KHESARAT_MALI.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingKhesaratSalesMali() throws Exception {
        // Get the khesaratSalesMali
        restKhesaratSalesMaliMockMvc.perform(get("/api/khesarat-sales-malis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKhesaratSalesMali() throws Exception {
        // Initialize the database
        khesaratSalesMaliRepository.saveAndFlush(khesaratSalesMali);

        int databaseSizeBeforeUpdate = khesaratSalesMaliRepository.findAll().size();

        // Update the khesaratSalesMali
        KhesaratSalesMali updatedKhesaratSalesMali = khesaratSalesMaliRepository.findById(khesaratSalesMali.getId()).get();
        // Disconnect from session so that the updates on updatedKhesaratSalesMali are not directly saved in db
        em.detach(updatedKhesaratSalesMali);
        updatedKhesaratSalesMali
            .noe(UPDATED_NOE)
            .nerkhKhesaratMali(UPDATED_NERKH_KHESARAT_MALI)
            .faal(UPDATED_FAAL);
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(updatedKhesaratSalesMali);

        restKhesaratSalesMaliMockMvc.perform(put("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isOk());

        // Validate the KhesaratSalesMali in the database
        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeUpdate);
        KhesaratSalesMali testKhesaratSalesMali = khesaratSalesMaliList.get(khesaratSalesMaliList.size() - 1);
        assertThat(testKhesaratSalesMali.getNoe()).isEqualTo(UPDATED_NOE);
        assertThat(testKhesaratSalesMali.getNerkhKhesaratMali()).isEqualTo(UPDATED_NERKH_KHESARAT_MALI);
        assertThat(testKhesaratSalesMali.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingKhesaratSalesMali() throws Exception {
        int databaseSizeBeforeUpdate = khesaratSalesMaliRepository.findAll().size();

        // Create the KhesaratSalesMali
        KhesaratSalesMaliDTO khesaratSalesMaliDTO = khesaratSalesMaliMapper.toDto(khesaratSalesMali);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKhesaratSalesMaliMockMvc.perform(put("/api/khesarat-sales-malis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesMaliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhesaratSalesMali in the database
        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKhesaratSalesMali() throws Exception {
        // Initialize the database
        khesaratSalesMaliRepository.saveAndFlush(khesaratSalesMali);

        int databaseSizeBeforeDelete = khesaratSalesMaliRepository.findAll().size();

        // Delete the khesaratSalesMali
        restKhesaratSalesMaliMockMvc.perform(delete("/api/khesarat-sales-malis/{id}", khesaratSalesMali.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KhesaratSalesMali> khesaratSalesMaliList = khesaratSalesMaliRepository.findAll();
        assertThat(khesaratSalesMaliList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhesaratSalesMali.class);
        KhesaratSalesMali khesaratSalesMali1 = new KhesaratSalesMali();
        khesaratSalesMali1.setId(1L);
        KhesaratSalesMali khesaratSalesMali2 = new KhesaratSalesMali();
        khesaratSalesMali2.setId(khesaratSalesMali1.getId());
        assertThat(khesaratSalesMali1).isEqualTo(khesaratSalesMali2);
        khesaratSalesMali2.setId(2L);
        assertThat(khesaratSalesMali1).isNotEqualTo(khesaratSalesMali2);
        khesaratSalesMali1.setId(null);
        assertThat(khesaratSalesMali1).isNotEqualTo(khesaratSalesMali2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhesaratSalesMaliDTO.class);
        KhesaratSalesMaliDTO khesaratSalesMaliDTO1 = new KhesaratSalesMaliDTO();
        khesaratSalesMaliDTO1.setId(1L);
        KhesaratSalesMaliDTO khesaratSalesMaliDTO2 = new KhesaratSalesMaliDTO();
        assertThat(khesaratSalesMaliDTO1).isNotEqualTo(khesaratSalesMaliDTO2);
        khesaratSalesMaliDTO2.setId(khesaratSalesMaliDTO1.getId());
        assertThat(khesaratSalesMaliDTO1).isEqualTo(khesaratSalesMaliDTO2);
        khesaratSalesMaliDTO2.setId(2L);
        assertThat(khesaratSalesMaliDTO1).isNotEqualTo(khesaratSalesMaliDTO2);
        khesaratSalesMaliDTO1.setId(null);
        assertThat(khesaratSalesMaliDTO1).isNotEqualTo(khesaratSalesMaliDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(khesaratSalesMaliMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(khesaratSalesMaliMapper.fromId(null)).isNull();
    }
}
