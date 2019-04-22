package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.SalesJaniCalcRepository;
import ir.insurance.startup.service.SalesJaniCalcService;
import ir.insurance.startup.service.dto.SalesJaniCalcDTO;
import ir.insurance.startup.service.mapper.SalesJaniCalcMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.SalesJaniCalcCriteria;
import ir.insurance.startup.service.SalesJaniCalcQueryService;

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
 * Test class for the SalesJaniCalcResource REST controller.
 *
 * @see SalesJaniCalcResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SalesJaniCalcResourceIntTest {

    private static final Float DEFAULT_MABLAGH_JANI = 0F;
    private static final Float UPDATED_MABLAGH_JANI = 1F;

    private static final Float DEFAULT_MABLAGH_MALI_EJBARI = 0F;
    private static final Float UPDATED_MABLAGH_MALI_EJBARI = 1F;

    private static final Integer DEFAULT_TEDAD_ROOZ = 1;
    private static final Integer UPDATED_TEDAD_ROOZ = 2;

    private static final LocalDate DEFAULT_TARIKH_SHORO_FAALIAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH_SHORO_FAALIAT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TARIKH_PAYAN_FAALIAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH_PAYAN_FAALIAT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NAAM_SHERKAT = "AAAAAAAAAA";
    private static final String UPDATED_NAAM_SHERKAT = "BBBBBBBBBB";

    private static final Float DEFAULT_HAGHBIME = 1F;
    private static final Float UPDATED_HAGHBIME = 2F;

    @Autowired
    private SalesJaniCalcRepository salesJaniCalcRepository;

    @Autowired
    private SalesJaniCalcMapper salesJaniCalcMapper;

    @Autowired
    private SalesJaniCalcService salesJaniCalcService;

    @Autowired
    private SalesJaniCalcQueryService salesJaniCalcQueryService;

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

    private MockMvc restSalesJaniCalcMockMvc;

    private SalesJaniCalc salesJaniCalc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SalesJaniCalcResource salesJaniCalcResource = new SalesJaniCalcResource(salesJaniCalcService, salesJaniCalcQueryService);
        this.restSalesJaniCalcMockMvc = MockMvcBuilders.standaloneSetup(salesJaniCalcResource)
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
    public static SalesJaniCalc createEntity(EntityManager em) {
        SalesJaniCalc salesJaniCalc = new SalesJaniCalc()
            .mablaghJani(DEFAULT_MABLAGH_JANI)
            .mablaghMaliEjbari(DEFAULT_MABLAGH_MALI_EJBARI)
            .tedadRooz(DEFAULT_TEDAD_ROOZ)
            .tarikhShoroFaaliat(DEFAULT_TARIKH_SHORO_FAALIAT)
            .tarikhPayanFaaliat(DEFAULT_TARIKH_PAYAN_FAALIAT)
            .naamSherkat(DEFAULT_NAAM_SHERKAT)
            .haghbime(DEFAULT_HAGHBIME);
        // Add required entity
        SherkatBime sherkatBime = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(sherkatBime);
        em.flush();
        salesJaniCalc.setBimename(sherkatBime);
        // Add required entity
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        salesJaniCalc.setGrouhKhodro(grouhKhodro);
        return salesJaniCalc;
    }

    @Before
    public void initTest() {
        salesJaniCalc = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesJaniCalc() throws Exception {
        int databaseSizeBeforeCreate = salesJaniCalcRepository.findAll().size();

        // Create the SalesJaniCalc
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);
        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesJaniCalc in the database
        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeCreate + 1);
        SalesJaniCalc testSalesJaniCalc = salesJaniCalcList.get(salesJaniCalcList.size() - 1);
        assertThat(testSalesJaniCalc.getMablaghJani()).isEqualTo(DEFAULT_MABLAGH_JANI);
        assertThat(testSalesJaniCalc.getMablaghMaliEjbari()).isEqualTo(DEFAULT_MABLAGH_MALI_EJBARI);
        assertThat(testSalesJaniCalc.getTedadRooz()).isEqualTo(DEFAULT_TEDAD_ROOZ);
        assertThat(testSalesJaniCalc.getTarikhShoroFaaliat()).isEqualTo(DEFAULT_TARIKH_SHORO_FAALIAT);
        assertThat(testSalesJaniCalc.getTarikhPayanFaaliat()).isEqualTo(DEFAULT_TARIKH_PAYAN_FAALIAT);
        assertThat(testSalesJaniCalc.getNaamSherkat()).isEqualTo(DEFAULT_NAAM_SHERKAT);
        assertThat(testSalesJaniCalc.getHaghbime()).isEqualTo(DEFAULT_HAGHBIME);
    }

    @Test
    @Transactional
    public void createSalesJaniCalcWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesJaniCalcRepository.findAll().size();

        // Create the SalesJaniCalc with an existing ID
        salesJaniCalc.setId(1L);
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesJaniCalc in the database
        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMablaghJaniIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesJaniCalcRepository.findAll().size();
        // set the field null
        salesJaniCalc.setMablaghJani(null);

        // Create the SalesJaniCalc, which fails.
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMablaghMaliEjbariIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesJaniCalcRepository.findAll().size();
        // set the field null
        salesJaniCalc.setMablaghMaliEjbari(null);

        // Create the SalesJaniCalc, which fails.
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTedadRoozIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesJaniCalcRepository.findAll().size();
        // set the field null
        salesJaniCalc.setTedadRooz(null);

        // Create the SalesJaniCalc, which fails.
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarikhShoroFaaliatIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesJaniCalcRepository.findAll().size();
        // set the field null
        salesJaniCalc.setTarikhShoroFaaliat(null);

        // Create the SalesJaniCalc, which fails.
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarikhPayanFaaliatIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesJaniCalcRepository.findAll().size();
        // set the field null
        salesJaniCalc.setTarikhPayanFaaliat(null);

        // Create the SalesJaniCalc, which fails.
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHaghbimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesJaniCalcRepository.findAll().size();
        // set the field null
        salesJaniCalc.setHaghbime(null);

        // Create the SalesJaniCalc, which fails.
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        restSalesJaniCalcMockMvc.perform(post("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcs() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesJaniCalc.getId().intValue())))
            .andExpect(jsonPath("$.[*].mablaghJani").value(hasItem(DEFAULT_MABLAGH_JANI.doubleValue())))
            .andExpect(jsonPath("$.[*].mablaghMaliEjbari").value(hasItem(DEFAULT_MABLAGH_MALI_EJBARI.doubleValue())))
            .andExpect(jsonPath("$.[*].tedadRooz").value(hasItem(DEFAULT_TEDAD_ROOZ)))
            .andExpect(jsonPath("$.[*].tarikhShoroFaaliat").value(hasItem(DEFAULT_TARIKH_SHORO_FAALIAT.toString())))
            .andExpect(jsonPath("$.[*].tarikhPayanFaaliat").value(hasItem(DEFAULT_TARIKH_PAYAN_FAALIAT.toString())))
            .andExpect(jsonPath("$.[*].naamSherkat").value(hasItem(DEFAULT_NAAM_SHERKAT.toString())))
            .andExpect(jsonPath("$.[*].haghbime").value(hasItem(DEFAULT_HAGHBIME.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getSalesJaniCalc() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get the salesJaniCalc
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs/{id}", salesJaniCalc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(salesJaniCalc.getId().intValue()))
            .andExpect(jsonPath("$.mablaghJani").value(DEFAULT_MABLAGH_JANI.doubleValue()))
            .andExpect(jsonPath("$.mablaghMaliEjbari").value(DEFAULT_MABLAGH_MALI_EJBARI.doubleValue()))
            .andExpect(jsonPath("$.tedadRooz").value(DEFAULT_TEDAD_ROOZ))
            .andExpect(jsonPath("$.tarikhShoroFaaliat").value(DEFAULT_TARIKH_SHORO_FAALIAT.toString()))
            .andExpect(jsonPath("$.tarikhPayanFaaliat").value(DEFAULT_TARIKH_PAYAN_FAALIAT.toString()))
            .andExpect(jsonPath("$.naamSherkat").value(DEFAULT_NAAM_SHERKAT.toString()))
            .andExpect(jsonPath("$.haghbime").value(DEFAULT_HAGHBIME.doubleValue()));
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByMablaghJaniIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where mablaghJani equals to DEFAULT_MABLAGH_JANI
        defaultSalesJaniCalcShouldBeFound("mablaghJani.equals=" + DEFAULT_MABLAGH_JANI);

        // Get all the salesJaniCalcList where mablaghJani equals to UPDATED_MABLAGH_JANI
        defaultSalesJaniCalcShouldNotBeFound("mablaghJani.equals=" + UPDATED_MABLAGH_JANI);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByMablaghJaniIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where mablaghJani in DEFAULT_MABLAGH_JANI or UPDATED_MABLAGH_JANI
        defaultSalesJaniCalcShouldBeFound("mablaghJani.in=" + DEFAULT_MABLAGH_JANI + "," + UPDATED_MABLAGH_JANI);

        // Get all the salesJaniCalcList where mablaghJani equals to UPDATED_MABLAGH_JANI
        defaultSalesJaniCalcShouldNotBeFound("mablaghJani.in=" + UPDATED_MABLAGH_JANI);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByMablaghJaniIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where mablaghJani is not null
        defaultSalesJaniCalcShouldBeFound("mablaghJani.specified=true");

        // Get all the salesJaniCalcList where mablaghJani is null
        defaultSalesJaniCalcShouldNotBeFound("mablaghJani.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByMablaghMaliEjbariIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where mablaghMaliEjbari equals to DEFAULT_MABLAGH_MALI_EJBARI
        defaultSalesJaniCalcShouldBeFound("mablaghMaliEjbari.equals=" + DEFAULT_MABLAGH_MALI_EJBARI);

        // Get all the salesJaniCalcList where mablaghMaliEjbari equals to UPDATED_MABLAGH_MALI_EJBARI
        defaultSalesJaniCalcShouldNotBeFound("mablaghMaliEjbari.equals=" + UPDATED_MABLAGH_MALI_EJBARI);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByMablaghMaliEjbariIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where mablaghMaliEjbari in DEFAULT_MABLAGH_MALI_EJBARI or UPDATED_MABLAGH_MALI_EJBARI
        defaultSalesJaniCalcShouldBeFound("mablaghMaliEjbari.in=" + DEFAULT_MABLAGH_MALI_EJBARI + "," + UPDATED_MABLAGH_MALI_EJBARI);

        // Get all the salesJaniCalcList where mablaghMaliEjbari equals to UPDATED_MABLAGH_MALI_EJBARI
        defaultSalesJaniCalcShouldNotBeFound("mablaghMaliEjbari.in=" + UPDATED_MABLAGH_MALI_EJBARI);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByMablaghMaliEjbariIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where mablaghMaliEjbari is not null
        defaultSalesJaniCalcShouldBeFound("mablaghMaliEjbari.specified=true");

        // Get all the salesJaniCalcList where mablaghMaliEjbari is null
        defaultSalesJaniCalcShouldNotBeFound("mablaghMaliEjbari.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTedadRoozIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tedadRooz equals to DEFAULT_TEDAD_ROOZ
        defaultSalesJaniCalcShouldBeFound("tedadRooz.equals=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesJaniCalcList where tedadRooz equals to UPDATED_TEDAD_ROOZ
        defaultSalesJaniCalcShouldNotBeFound("tedadRooz.equals=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTedadRoozIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tedadRooz in DEFAULT_TEDAD_ROOZ or UPDATED_TEDAD_ROOZ
        defaultSalesJaniCalcShouldBeFound("tedadRooz.in=" + DEFAULT_TEDAD_ROOZ + "," + UPDATED_TEDAD_ROOZ);

        // Get all the salesJaniCalcList where tedadRooz equals to UPDATED_TEDAD_ROOZ
        defaultSalesJaniCalcShouldNotBeFound("tedadRooz.in=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTedadRoozIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tedadRooz is not null
        defaultSalesJaniCalcShouldBeFound("tedadRooz.specified=true");

        // Get all the salesJaniCalcList where tedadRooz is null
        defaultSalesJaniCalcShouldNotBeFound("tedadRooz.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTedadRoozIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tedadRooz greater than or equals to DEFAULT_TEDAD_ROOZ
        defaultSalesJaniCalcShouldBeFound("tedadRooz.greaterOrEqualThan=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesJaniCalcList where tedadRooz greater than or equals to UPDATED_TEDAD_ROOZ
        defaultSalesJaniCalcShouldNotBeFound("tedadRooz.greaterOrEqualThan=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTedadRoozIsLessThanSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tedadRooz less than or equals to DEFAULT_TEDAD_ROOZ
        defaultSalesJaniCalcShouldNotBeFound("tedadRooz.lessThan=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesJaniCalcList where tedadRooz less than or equals to UPDATED_TEDAD_ROOZ
        defaultSalesJaniCalcShouldBeFound("tedadRooz.lessThan=" + UPDATED_TEDAD_ROOZ);
    }


    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhShoroFaaliatIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat equals to DEFAULT_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhShoroFaaliat.equals=" + DEFAULT_TARIKH_SHORO_FAALIAT);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat equals to UPDATED_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhShoroFaaliat.equals=" + UPDATED_TARIKH_SHORO_FAALIAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhShoroFaaliatIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat in DEFAULT_TARIKH_SHORO_FAALIAT or UPDATED_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhShoroFaaliat.in=" + DEFAULT_TARIKH_SHORO_FAALIAT + "," + UPDATED_TARIKH_SHORO_FAALIAT);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat equals to UPDATED_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhShoroFaaliat.in=" + UPDATED_TARIKH_SHORO_FAALIAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhShoroFaaliatIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat is not null
        defaultSalesJaniCalcShouldBeFound("tarikhShoroFaaliat.specified=true");

        // Get all the salesJaniCalcList where tarikhShoroFaaliat is null
        defaultSalesJaniCalcShouldNotBeFound("tarikhShoroFaaliat.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhShoroFaaliatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat greater than or equals to DEFAULT_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhShoroFaaliat.greaterOrEqualThan=" + DEFAULT_TARIKH_SHORO_FAALIAT);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat greater than or equals to UPDATED_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhShoroFaaliat.greaterOrEqualThan=" + UPDATED_TARIKH_SHORO_FAALIAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhShoroFaaliatIsLessThanSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat less than or equals to DEFAULT_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhShoroFaaliat.lessThan=" + DEFAULT_TARIKH_SHORO_FAALIAT);

        // Get all the salesJaniCalcList where tarikhShoroFaaliat less than or equals to UPDATED_TARIKH_SHORO_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhShoroFaaliat.lessThan=" + UPDATED_TARIKH_SHORO_FAALIAT);
    }


    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhPayanFaaliatIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat equals to DEFAULT_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhPayanFaaliat.equals=" + DEFAULT_TARIKH_PAYAN_FAALIAT);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat equals to UPDATED_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhPayanFaaliat.equals=" + UPDATED_TARIKH_PAYAN_FAALIAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhPayanFaaliatIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat in DEFAULT_TARIKH_PAYAN_FAALIAT or UPDATED_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhPayanFaaliat.in=" + DEFAULT_TARIKH_PAYAN_FAALIAT + "," + UPDATED_TARIKH_PAYAN_FAALIAT);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat equals to UPDATED_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhPayanFaaliat.in=" + UPDATED_TARIKH_PAYAN_FAALIAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhPayanFaaliatIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat is not null
        defaultSalesJaniCalcShouldBeFound("tarikhPayanFaaliat.specified=true");

        // Get all the salesJaniCalcList where tarikhPayanFaaliat is null
        defaultSalesJaniCalcShouldNotBeFound("tarikhPayanFaaliat.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhPayanFaaliatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat greater than or equals to DEFAULT_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhPayanFaaliat.greaterOrEqualThan=" + DEFAULT_TARIKH_PAYAN_FAALIAT);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat greater than or equals to UPDATED_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhPayanFaaliat.greaterOrEqualThan=" + UPDATED_TARIKH_PAYAN_FAALIAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByTarikhPayanFaaliatIsLessThanSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat less than or equals to DEFAULT_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldNotBeFound("tarikhPayanFaaliat.lessThan=" + DEFAULT_TARIKH_PAYAN_FAALIAT);

        // Get all the salesJaniCalcList where tarikhPayanFaaliat less than or equals to UPDATED_TARIKH_PAYAN_FAALIAT
        defaultSalesJaniCalcShouldBeFound("tarikhPayanFaaliat.lessThan=" + UPDATED_TARIKH_PAYAN_FAALIAT);
    }


    @Test
    @Transactional
    public void getAllSalesJaniCalcsByNaamSherkatIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where naamSherkat equals to DEFAULT_NAAM_SHERKAT
        defaultSalesJaniCalcShouldBeFound("naamSherkat.equals=" + DEFAULT_NAAM_SHERKAT);

        // Get all the salesJaniCalcList where naamSherkat equals to UPDATED_NAAM_SHERKAT
        defaultSalesJaniCalcShouldNotBeFound("naamSherkat.equals=" + UPDATED_NAAM_SHERKAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByNaamSherkatIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where naamSherkat in DEFAULT_NAAM_SHERKAT or UPDATED_NAAM_SHERKAT
        defaultSalesJaniCalcShouldBeFound("naamSherkat.in=" + DEFAULT_NAAM_SHERKAT + "," + UPDATED_NAAM_SHERKAT);

        // Get all the salesJaniCalcList where naamSherkat equals to UPDATED_NAAM_SHERKAT
        defaultSalesJaniCalcShouldNotBeFound("naamSherkat.in=" + UPDATED_NAAM_SHERKAT);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByNaamSherkatIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where naamSherkat is not null
        defaultSalesJaniCalcShouldBeFound("naamSherkat.specified=true");

        // Get all the salesJaniCalcList where naamSherkat is null
        defaultSalesJaniCalcShouldNotBeFound("naamSherkat.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByHaghbimeIsEqualToSomething() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where haghbime equals to DEFAULT_HAGHBIME
        defaultSalesJaniCalcShouldBeFound("haghbime.equals=" + DEFAULT_HAGHBIME);

        // Get all the salesJaniCalcList where haghbime equals to UPDATED_HAGHBIME
        defaultSalesJaniCalcShouldNotBeFound("haghbime.equals=" + UPDATED_HAGHBIME);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByHaghbimeIsInShouldWork() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where haghbime in DEFAULT_HAGHBIME or UPDATED_HAGHBIME
        defaultSalesJaniCalcShouldBeFound("haghbime.in=" + DEFAULT_HAGHBIME + "," + UPDATED_HAGHBIME);

        // Get all the salesJaniCalcList where haghbime equals to UPDATED_HAGHBIME
        defaultSalesJaniCalcShouldNotBeFound("haghbime.in=" + UPDATED_HAGHBIME);
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByHaghbimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        // Get all the salesJaniCalcList where haghbime is not null
        defaultSalesJaniCalcShouldBeFound("haghbime.specified=true");

        // Get all the salesJaniCalcList where haghbime is null
        defaultSalesJaniCalcShouldNotBeFound("haghbime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesJaniCalcsByBimenameIsEqualToSomething() throws Exception {
        // Initialize the database
        SherkatBime bimename = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(bimename);
        em.flush();
        salesJaniCalc.setBimename(bimename);
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);
        Long bimenameId = bimename.getId();

        // Get all the salesJaniCalcList where bimename equals to bimenameId
        defaultSalesJaniCalcShouldBeFound("bimenameId.equals=" + bimenameId);

        // Get all the salesJaniCalcList where bimename equals to bimenameId + 1
        defaultSalesJaniCalcShouldNotBeFound("bimenameId.equals=" + (bimenameId + 1));
    }


    @Test
    @Transactional
    public void getAllSalesJaniCalcsByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        salesJaniCalc.setGrouhKhodro(grouhKhodro);
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the salesJaniCalcList where grouhKhodro equals to grouhKhodroId
        defaultSalesJaniCalcShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the salesJaniCalcList where grouhKhodro equals to grouhKhodroId + 1
        defaultSalesJaniCalcShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSalesJaniCalcShouldBeFound(String filter) throws Exception {
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesJaniCalc.getId().intValue())))
            .andExpect(jsonPath("$.[*].mablaghJani").value(hasItem(DEFAULT_MABLAGH_JANI.doubleValue())))
            .andExpect(jsonPath("$.[*].mablaghMaliEjbari").value(hasItem(DEFAULT_MABLAGH_MALI_EJBARI.doubleValue())))
            .andExpect(jsonPath("$.[*].tedadRooz").value(hasItem(DEFAULT_TEDAD_ROOZ)))
            .andExpect(jsonPath("$.[*].tarikhShoroFaaliat").value(hasItem(DEFAULT_TARIKH_SHORO_FAALIAT.toString())))
            .andExpect(jsonPath("$.[*].tarikhPayanFaaliat").value(hasItem(DEFAULT_TARIKH_PAYAN_FAALIAT.toString())))
            .andExpect(jsonPath("$.[*].naamSherkat").value(hasItem(DEFAULT_NAAM_SHERKAT)))
            .andExpect(jsonPath("$.[*].haghbime").value(hasItem(DEFAULT_HAGHBIME.doubleValue())));

        // Check, that the count call also returns 1
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSalesJaniCalcShouldNotBeFound(String filter) throws Exception {
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSalesJaniCalc() throws Exception {
        // Get the salesJaniCalc
        restSalesJaniCalcMockMvc.perform(get("/api/sales-jani-calcs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesJaniCalc() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        int databaseSizeBeforeUpdate = salesJaniCalcRepository.findAll().size();

        // Update the salesJaniCalc
        SalesJaniCalc updatedSalesJaniCalc = salesJaniCalcRepository.findById(salesJaniCalc.getId()).get();
        // Disconnect from session so that the updates on updatedSalesJaniCalc are not directly saved in db
        em.detach(updatedSalesJaniCalc);
        updatedSalesJaniCalc
            .mablaghJani(UPDATED_MABLAGH_JANI)
            .mablaghMaliEjbari(UPDATED_MABLAGH_MALI_EJBARI)
            .tedadRooz(UPDATED_TEDAD_ROOZ)
            .tarikhShoroFaaliat(UPDATED_TARIKH_SHORO_FAALIAT)
            .tarikhPayanFaaliat(UPDATED_TARIKH_PAYAN_FAALIAT)
            .naamSherkat(UPDATED_NAAM_SHERKAT)
            .haghbime(UPDATED_HAGHBIME);
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(updatedSalesJaniCalc);

        restSalesJaniCalcMockMvc.perform(put("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isOk());

        // Validate the SalesJaniCalc in the database
        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeUpdate);
        SalesJaniCalc testSalesJaniCalc = salesJaniCalcList.get(salesJaniCalcList.size() - 1);
        assertThat(testSalesJaniCalc.getMablaghJani()).isEqualTo(UPDATED_MABLAGH_JANI);
        assertThat(testSalesJaniCalc.getMablaghMaliEjbari()).isEqualTo(UPDATED_MABLAGH_MALI_EJBARI);
        assertThat(testSalesJaniCalc.getTedadRooz()).isEqualTo(UPDATED_TEDAD_ROOZ);
        assertThat(testSalesJaniCalc.getTarikhShoroFaaliat()).isEqualTo(UPDATED_TARIKH_SHORO_FAALIAT);
        assertThat(testSalesJaniCalc.getTarikhPayanFaaliat()).isEqualTo(UPDATED_TARIKH_PAYAN_FAALIAT);
        assertThat(testSalesJaniCalc.getNaamSherkat()).isEqualTo(UPDATED_NAAM_SHERKAT);
        assertThat(testSalesJaniCalc.getHaghbime()).isEqualTo(UPDATED_HAGHBIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesJaniCalc() throws Exception {
        int databaseSizeBeforeUpdate = salesJaniCalcRepository.findAll().size();

        // Create the SalesJaniCalc
        SalesJaniCalcDTO salesJaniCalcDTO = salesJaniCalcMapper.toDto(salesJaniCalc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesJaniCalcMockMvc.perform(put("/api/sales-jani-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesJaniCalcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesJaniCalc in the database
        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesJaniCalc() throws Exception {
        // Initialize the database
        salesJaniCalcRepository.saveAndFlush(salesJaniCalc);

        int databaseSizeBeforeDelete = salesJaniCalcRepository.findAll().size();

        // Delete the salesJaniCalc
        restSalesJaniCalcMockMvc.perform(delete("/api/sales-jani-calcs/{id}", salesJaniCalc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SalesJaniCalc> salesJaniCalcList = salesJaniCalcRepository.findAll();
        assertThat(salesJaniCalcList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesJaniCalc.class);
        SalesJaniCalc salesJaniCalc1 = new SalesJaniCalc();
        salesJaniCalc1.setId(1L);
        SalesJaniCalc salesJaniCalc2 = new SalesJaniCalc();
        salesJaniCalc2.setId(salesJaniCalc1.getId());
        assertThat(salesJaniCalc1).isEqualTo(salesJaniCalc2);
        salesJaniCalc2.setId(2L);
        assertThat(salesJaniCalc1).isNotEqualTo(salesJaniCalc2);
        salesJaniCalc1.setId(null);
        assertThat(salesJaniCalc1).isNotEqualTo(salesJaniCalc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesJaniCalcDTO.class);
        SalesJaniCalcDTO salesJaniCalcDTO1 = new SalesJaniCalcDTO();
        salesJaniCalcDTO1.setId(1L);
        SalesJaniCalcDTO salesJaniCalcDTO2 = new SalesJaniCalcDTO();
        assertThat(salesJaniCalcDTO1).isNotEqualTo(salesJaniCalcDTO2);
        salesJaniCalcDTO2.setId(salesJaniCalcDTO1.getId());
        assertThat(salesJaniCalcDTO1).isEqualTo(salesJaniCalcDTO2);
        salesJaniCalcDTO2.setId(2L);
        assertThat(salesJaniCalcDTO1).isNotEqualTo(salesJaniCalcDTO2);
        salesJaniCalcDTO1.setId(null);
        assertThat(salesJaniCalcDTO1).isNotEqualTo(salesJaniCalcDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(salesJaniCalcMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(salesJaniCalcMapper.fromId(null)).isNull();
    }
}
