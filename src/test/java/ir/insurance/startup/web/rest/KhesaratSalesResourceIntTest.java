package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.KhesaratSales;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.repository.KhesaratSalesRepository;
import ir.insurance.startup.service.KhesaratSalesService;
import ir.insurance.startup.service.dto.KhesaratSalesDTO;
import ir.insurance.startup.service.mapper.KhesaratSalesMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.KhesaratSalesCriteria;
import ir.insurance.startup.service.KhesaratSalesQueryService;

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
 * Test class for the KhesaratSalesResource REST controller.
 *
 * @see KhesaratSalesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class KhesaratSalesResourceIntTest {

    private static final SalesSarneshinEnum DEFAULT_NOE = SalesSarneshinEnum.SALES;
    private static final SalesSarneshinEnum UPDATED_NOE = SalesSarneshinEnum.SARNESHIN;

    private static final Float DEFAULT_NERKH_KHESARAT = 0F;
    private static final Float UPDATED_NERKH_KHESARAT = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private KhesaratSalesRepository khesaratSalesRepository;

    @Autowired
    private KhesaratSalesMapper khesaratSalesMapper;

    @Autowired
    private KhesaratSalesService khesaratSalesService;

    @Autowired
    private KhesaratSalesQueryService khesaratSalesQueryService;

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

    private MockMvc restKhesaratSalesMockMvc;

    private KhesaratSales khesaratSales;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KhesaratSalesResource khesaratSalesResource = new KhesaratSalesResource(khesaratSalesService, khesaratSalesQueryService);
        this.restKhesaratSalesMockMvc = MockMvcBuilders.standaloneSetup(khesaratSalesResource)
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
    public static KhesaratSales createEntity(EntityManager em) {
        KhesaratSales khesaratSales = new KhesaratSales()
            .noe(DEFAULT_NOE)
            .nerkhKhesarat(DEFAULT_NERKH_KHESARAT)
            .faal(DEFAULT_FAAL);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        khesaratSales.setSabeghe(sabeghe);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        khesaratSales.setNoeSabeghe(noeSabeghe);
        return khesaratSales;
    }

    @Before
    public void initTest() {
        khesaratSales = createEntity(em);
    }

    @Test
    @Transactional
    public void createKhesaratSales() throws Exception {
        int databaseSizeBeforeCreate = khesaratSalesRepository.findAll().size();

        // Create the KhesaratSales
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(khesaratSales);
        restKhesaratSalesMockMvc.perform(post("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isCreated());

        // Validate the KhesaratSales in the database
        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeCreate + 1);
        KhesaratSales testKhesaratSales = khesaratSalesList.get(khesaratSalesList.size() - 1);
        assertThat(testKhesaratSales.getNoe()).isEqualTo(DEFAULT_NOE);
        assertThat(testKhesaratSales.getNerkhKhesarat()).isEqualTo(DEFAULT_NERKH_KHESARAT);
        assertThat(testKhesaratSales.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createKhesaratSalesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = khesaratSalesRepository.findAll().size();

        // Create the KhesaratSales with an existing ID
        khesaratSales.setId(1L);
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(khesaratSales);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKhesaratSalesMockMvc.perform(post("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhesaratSales in the database
        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNoeIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSalesRepository.findAll().size();
        // set the field null
        khesaratSales.setNoe(null);

        // Create the KhesaratSales, which fails.
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(khesaratSales);

        restKhesaratSalesMockMvc.perform(post("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNerkhKhesaratIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSalesRepository.findAll().size();
        // set the field null
        khesaratSales.setNerkhKhesarat(null);

        // Create the KhesaratSales, which fails.
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(khesaratSales);

        restKhesaratSalesMockMvc.perform(post("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSalesRepository.findAll().size();
        // set the field null
        khesaratSales.setFaal(null);

        // Create the KhesaratSales, which fails.
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(khesaratSales);

        restKhesaratSalesMockMvc.perform(post("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKhesaratSales() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khesaratSales.getId().intValue())))
            .andExpect(jsonPath("$.[*].noe").value(hasItem(DEFAULT_NOE.toString())))
            .andExpect(jsonPath("$.[*].nerkhKhesarat").value(hasItem(DEFAULT_NERKH_KHESARAT.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKhesaratSales() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get the khesaratSales
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales/{id}", khesaratSales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(khesaratSales.getId().intValue()))
            .andExpect(jsonPath("$.noe").value(DEFAULT_NOE.toString()))
            .andExpect(jsonPath("$.nerkhKhesarat").value(DEFAULT_NERKH_KHESARAT.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByNoeIsEqualToSomething() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where noe equals to DEFAULT_NOE
        defaultKhesaratSalesShouldBeFound("noe.equals=" + DEFAULT_NOE);

        // Get all the khesaratSalesList where noe equals to UPDATED_NOE
        defaultKhesaratSalesShouldNotBeFound("noe.equals=" + UPDATED_NOE);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByNoeIsInShouldWork() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where noe in DEFAULT_NOE or UPDATED_NOE
        defaultKhesaratSalesShouldBeFound("noe.in=" + DEFAULT_NOE + "," + UPDATED_NOE);

        // Get all the khesaratSalesList where noe equals to UPDATED_NOE
        defaultKhesaratSalesShouldNotBeFound("noe.in=" + UPDATED_NOE);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByNoeIsNullOrNotNull() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where noe is not null
        defaultKhesaratSalesShouldBeFound("noe.specified=true");

        // Get all the khesaratSalesList where noe is null
        defaultKhesaratSalesShouldNotBeFound("noe.specified=false");
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByNerkhKhesaratIsEqualToSomething() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where nerkhKhesarat equals to DEFAULT_NERKH_KHESARAT
        defaultKhesaratSalesShouldBeFound("nerkhKhesarat.equals=" + DEFAULT_NERKH_KHESARAT);

        // Get all the khesaratSalesList where nerkhKhesarat equals to UPDATED_NERKH_KHESARAT
        defaultKhesaratSalesShouldNotBeFound("nerkhKhesarat.equals=" + UPDATED_NERKH_KHESARAT);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByNerkhKhesaratIsInShouldWork() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where nerkhKhesarat in DEFAULT_NERKH_KHESARAT or UPDATED_NERKH_KHESARAT
        defaultKhesaratSalesShouldBeFound("nerkhKhesarat.in=" + DEFAULT_NERKH_KHESARAT + "," + UPDATED_NERKH_KHESARAT);

        // Get all the khesaratSalesList where nerkhKhesarat equals to UPDATED_NERKH_KHESARAT
        defaultKhesaratSalesShouldNotBeFound("nerkhKhesarat.in=" + UPDATED_NERKH_KHESARAT);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByNerkhKhesaratIsNullOrNotNull() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where nerkhKhesarat is not null
        defaultKhesaratSalesShouldBeFound("nerkhKhesarat.specified=true");

        // Get all the khesaratSalesList where nerkhKhesarat is null
        defaultKhesaratSalesShouldNotBeFound("nerkhKhesarat.specified=false");
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where faal equals to DEFAULT_FAAL
        defaultKhesaratSalesShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the khesaratSalesList where faal equals to UPDATED_FAAL
        defaultKhesaratSalesShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultKhesaratSalesShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the khesaratSalesList where faal equals to UPDATED_FAAL
        defaultKhesaratSalesShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        // Get all the khesaratSalesList where faal is not null
        defaultKhesaratSalesShouldBeFound("faal.specified=true");

        // Get all the khesaratSalesList where faal is null
        defaultKhesaratSalesShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllKhesaratSalesBySabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        khesaratSales.setSabeghe(sabeghe);
        khesaratSalesRepository.saveAndFlush(khesaratSales);
        Long sabegheId = sabeghe.getId();

        // Get all the khesaratSalesList where sabeghe equals to sabegheId
        defaultKhesaratSalesShouldBeFound("sabegheId.equals=" + sabegheId);

        // Get all the khesaratSalesList where sabeghe equals to sabegheId + 1
        defaultKhesaratSalesShouldNotBeFound("sabegheId.equals=" + (sabegheId + 1));
    }


    @Test
    @Transactional
    public void getAllKhesaratSalesByNoeSabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        khesaratSales.setNoeSabeghe(noeSabeghe);
        khesaratSalesRepository.saveAndFlush(khesaratSales);
        Long noeSabegheId = noeSabeghe.getId();

        // Get all the khesaratSalesList where noeSabeghe equals to noeSabegheId
        defaultKhesaratSalesShouldBeFound("noeSabegheId.equals=" + noeSabegheId);

        // Get all the khesaratSalesList where noeSabeghe equals to noeSabegheId + 1
        defaultKhesaratSalesShouldNotBeFound("noeSabegheId.equals=" + (noeSabegheId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultKhesaratSalesShouldBeFound(String filter) throws Exception {
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khesaratSales.getId().intValue())))
            .andExpect(jsonPath("$.[*].noe").value(hasItem(DEFAULT_NOE.toString())))
            .andExpect(jsonPath("$.[*].nerkhKhesarat").value(hasItem(DEFAULT_NERKH_KHESARAT.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultKhesaratSalesShouldNotBeFound(String filter) throws Exception {
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingKhesaratSales() throws Exception {
        // Get the khesaratSales
        restKhesaratSalesMockMvc.perform(get("/api/khesarat-sales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKhesaratSales() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        int databaseSizeBeforeUpdate = khesaratSalesRepository.findAll().size();

        // Update the khesaratSales
        KhesaratSales updatedKhesaratSales = khesaratSalesRepository.findById(khesaratSales.getId()).get();
        // Disconnect from session so that the updates on updatedKhesaratSales are not directly saved in db
        em.detach(updatedKhesaratSales);
        updatedKhesaratSales
            .noe(UPDATED_NOE)
            .nerkhKhesarat(UPDATED_NERKH_KHESARAT)
            .faal(UPDATED_FAAL);
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(updatedKhesaratSales);

        restKhesaratSalesMockMvc.perform(put("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isOk());

        // Validate the KhesaratSales in the database
        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeUpdate);
        KhesaratSales testKhesaratSales = khesaratSalesList.get(khesaratSalesList.size() - 1);
        assertThat(testKhesaratSales.getNoe()).isEqualTo(UPDATED_NOE);
        assertThat(testKhesaratSales.getNerkhKhesarat()).isEqualTo(UPDATED_NERKH_KHESARAT);
        assertThat(testKhesaratSales.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingKhesaratSales() throws Exception {
        int databaseSizeBeforeUpdate = khesaratSalesRepository.findAll().size();

        // Create the KhesaratSales
        KhesaratSalesDTO khesaratSalesDTO = khesaratSalesMapper.toDto(khesaratSales);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKhesaratSalesMockMvc.perform(put("/api/khesarat-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhesaratSales in the database
        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKhesaratSales() throws Exception {
        // Initialize the database
        khesaratSalesRepository.saveAndFlush(khesaratSales);

        int databaseSizeBeforeDelete = khesaratSalesRepository.findAll().size();

        // Get the khesaratSales
        restKhesaratSalesMockMvc.perform(delete("/api/khesarat-sales/{id}", khesaratSales.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KhesaratSales> khesaratSalesList = khesaratSalesRepository.findAll();
        assertThat(khesaratSalesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhesaratSales.class);
        KhesaratSales khesaratSales1 = new KhesaratSales();
        khesaratSales1.setId(1L);
        KhesaratSales khesaratSales2 = new KhesaratSales();
        khesaratSales2.setId(khesaratSales1.getId());
        assertThat(khesaratSales1).isEqualTo(khesaratSales2);
        khesaratSales2.setId(2L);
        assertThat(khesaratSales1).isNotEqualTo(khesaratSales2);
        khesaratSales1.setId(null);
        assertThat(khesaratSales1).isNotEqualTo(khesaratSales2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhesaratSalesDTO.class);
        KhesaratSalesDTO khesaratSalesDTO1 = new KhesaratSalesDTO();
        khesaratSalesDTO1.setId(1L);
        KhesaratSalesDTO khesaratSalesDTO2 = new KhesaratSalesDTO();
        assertThat(khesaratSalesDTO1).isNotEqualTo(khesaratSalesDTO2);
        khesaratSalesDTO2.setId(khesaratSalesDTO1.getId());
        assertThat(khesaratSalesDTO1).isEqualTo(khesaratSalesDTO2);
        khesaratSalesDTO2.setId(2L);
        assertThat(khesaratSalesDTO1).isNotEqualTo(khesaratSalesDTO2);
        khesaratSalesDTO1.setId(null);
        assertThat(khesaratSalesDTO1).isNotEqualTo(khesaratSalesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(khesaratSalesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(khesaratSalesMapper.fromId(null)).isNull();
    }
}
