package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.SalesMazadCalc;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.SalesMazadCalcRepository;
import ir.insurance.startup.service.SalesMazadCalcService;
import ir.insurance.startup.service.dto.SalesMazadCalcDTO;
import ir.insurance.startup.service.mapper.SalesMazadCalcMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.SalesMazadCalcCriteria;
import ir.insurance.startup.service.SalesMazadCalcQueryService;

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
 * Test class for the SalesMazadCalcResource REST controller.
 *
 * @see SalesMazadCalcResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SalesMazadCalcResourceIntTest {

    private static final Float DEFAULT_MABLAGH_SALES_MAZAD = 0F;
    private static final Float UPDATED_MABLAGH_SALES_MAZAD = 1F;

    private static final Integer DEFAULT_TEDAD_ROOZ = 0;
    private static final Integer UPDATED_TEDAD_ROOZ = 1;

    private static final Float DEFAULT_HAGH_BIME = 0F;
    private static final Float UPDATED_HAGH_BIME = 1F;

    private static final LocalDate DEFAULT_TARIKH_SHOROO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH_SHOROO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TARIKH_PAYAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH_PAYAN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SalesMazadCalcRepository salesMazadCalcRepository;

    @Autowired
    private SalesMazadCalcMapper salesMazadCalcMapper;

    @Autowired
    private SalesMazadCalcService salesMazadCalcService;

    @Autowired
    private SalesMazadCalcQueryService salesMazadCalcQueryService;

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

    private MockMvc restSalesMazadCalcMockMvc;

    private SalesMazadCalc salesMazadCalc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SalesMazadCalcResource salesMazadCalcResource = new SalesMazadCalcResource(salesMazadCalcService, salesMazadCalcQueryService);
        this.restSalesMazadCalcMockMvc = MockMvcBuilders.standaloneSetup(salesMazadCalcResource)
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
    public static SalesMazadCalc createEntity(EntityManager em) {
        SalesMazadCalc salesMazadCalc = new SalesMazadCalc()
            .mablaghSalesMazad(DEFAULT_MABLAGH_SALES_MAZAD)
            .tedadRooz(DEFAULT_TEDAD_ROOZ)
            .haghBime(DEFAULT_HAGH_BIME)
            .tarikhShoroo(DEFAULT_TARIKH_SHOROO)
            .tarikhPayan(DEFAULT_TARIKH_PAYAN);
        // Add required entity
        SherkatBime sherkatBime = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(sherkatBime);
        em.flush();
        salesMazadCalc.setNamesherkat(sherkatBime);
        // Add required entity
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        salesMazadCalc.setGrouhKhodro(grouhKhodro);
        return salesMazadCalc;
    }

    @Before
    public void initTest() {
        salesMazadCalc = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesMazadCalc() throws Exception {
        int databaseSizeBeforeCreate = salesMazadCalcRepository.findAll().size();

        // Create the SalesMazadCalc
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);
        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesMazadCalc in the database
        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeCreate + 1);
        SalesMazadCalc testSalesMazadCalc = salesMazadCalcList.get(salesMazadCalcList.size() - 1);
        assertThat(testSalesMazadCalc.getMablaghSalesMazad()).isEqualTo(DEFAULT_MABLAGH_SALES_MAZAD);
        assertThat(testSalesMazadCalc.getTedadRooz()).isEqualTo(DEFAULT_TEDAD_ROOZ);
        assertThat(testSalesMazadCalc.getHaghBime()).isEqualTo(DEFAULT_HAGH_BIME);
        assertThat(testSalesMazadCalc.getTarikhShoroo()).isEqualTo(DEFAULT_TARIKH_SHOROO);
        assertThat(testSalesMazadCalc.getTarikhPayan()).isEqualTo(DEFAULT_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void createSalesMazadCalcWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesMazadCalcRepository.findAll().size();

        // Create the SalesMazadCalc with an existing ID
        salesMazadCalc.setId(1L);
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesMazadCalc in the database
        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMablaghSalesMazadIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesMazadCalcRepository.findAll().size();
        // set the field null
        salesMazadCalc.setMablaghSalesMazad(null);

        // Create the SalesMazadCalc, which fails.
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTedadRoozIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesMazadCalcRepository.findAll().size();
        // set the field null
        salesMazadCalc.setTedadRooz(null);

        // Create the SalesMazadCalc, which fails.
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHaghBimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesMazadCalcRepository.findAll().size();
        // set the field null
        salesMazadCalc.setHaghBime(null);

        // Create the SalesMazadCalc, which fails.
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarikhShorooIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesMazadCalcRepository.findAll().size();
        // set the field null
        salesMazadCalc.setTarikhShoroo(null);

        // Create the SalesMazadCalc, which fails.
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarikhPayanIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesMazadCalcRepository.findAll().size();
        // set the field null
        salesMazadCalc.setTarikhPayan(null);

        // Create the SalesMazadCalc, which fails.
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        restSalesMazadCalcMockMvc.perform(post("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcs() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesMazadCalc.getId().intValue())))
            .andExpect(jsonPath("$.[*].mablaghSalesMazad").value(hasItem(DEFAULT_MABLAGH_SALES_MAZAD.doubleValue())))
            .andExpect(jsonPath("$.[*].tedadRooz").value(hasItem(DEFAULT_TEDAD_ROOZ)))
            .andExpect(jsonPath("$.[*].haghBime").value(hasItem(DEFAULT_HAGH_BIME.doubleValue())))
            .andExpect(jsonPath("$.[*].tarikhShoroo").value(hasItem(DEFAULT_TARIKH_SHOROO.toString())))
            .andExpect(jsonPath("$.[*].tarikhPayan").value(hasItem(DEFAULT_TARIKH_PAYAN.toString())));
    }
    
    @Test
    @Transactional
    public void getSalesMazadCalc() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get the salesMazadCalc
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs/{id}", salesMazadCalc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(salesMazadCalc.getId().intValue()))
            .andExpect(jsonPath("$.mablaghSalesMazad").value(DEFAULT_MABLAGH_SALES_MAZAD.doubleValue()))
            .andExpect(jsonPath("$.tedadRooz").value(DEFAULT_TEDAD_ROOZ))
            .andExpect(jsonPath("$.haghBime").value(DEFAULT_HAGH_BIME.doubleValue()))
            .andExpect(jsonPath("$.tarikhShoroo").value(DEFAULT_TARIKH_SHOROO.toString()))
            .andExpect(jsonPath("$.tarikhPayan").value(DEFAULT_TARIKH_PAYAN.toString()));
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByMablaghSalesMazadIsEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where mablaghSalesMazad equals to DEFAULT_MABLAGH_SALES_MAZAD
        defaultSalesMazadCalcShouldBeFound("mablaghSalesMazad.equals=" + DEFAULT_MABLAGH_SALES_MAZAD);

        // Get all the salesMazadCalcList where mablaghSalesMazad equals to UPDATED_MABLAGH_SALES_MAZAD
        defaultSalesMazadCalcShouldNotBeFound("mablaghSalesMazad.equals=" + UPDATED_MABLAGH_SALES_MAZAD);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByMablaghSalesMazadIsInShouldWork() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where mablaghSalesMazad in DEFAULT_MABLAGH_SALES_MAZAD or UPDATED_MABLAGH_SALES_MAZAD
        defaultSalesMazadCalcShouldBeFound("mablaghSalesMazad.in=" + DEFAULT_MABLAGH_SALES_MAZAD + "," + UPDATED_MABLAGH_SALES_MAZAD);

        // Get all the salesMazadCalcList where mablaghSalesMazad equals to UPDATED_MABLAGH_SALES_MAZAD
        defaultSalesMazadCalcShouldNotBeFound("mablaghSalesMazad.in=" + UPDATED_MABLAGH_SALES_MAZAD);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByMablaghSalesMazadIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where mablaghSalesMazad is not null
        defaultSalesMazadCalcShouldBeFound("mablaghSalesMazad.specified=true");

        // Get all the salesMazadCalcList where mablaghSalesMazad is null
        defaultSalesMazadCalcShouldNotBeFound("mablaghSalesMazad.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTedadRoozIsEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tedadRooz equals to DEFAULT_TEDAD_ROOZ
        defaultSalesMazadCalcShouldBeFound("tedadRooz.equals=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesMazadCalcList where tedadRooz equals to UPDATED_TEDAD_ROOZ
        defaultSalesMazadCalcShouldNotBeFound("tedadRooz.equals=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTedadRoozIsInShouldWork() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tedadRooz in DEFAULT_TEDAD_ROOZ or UPDATED_TEDAD_ROOZ
        defaultSalesMazadCalcShouldBeFound("tedadRooz.in=" + DEFAULT_TEDAD_ROOZ + "," + UPDATED_TEDAD_ROOZ);

        // Get all the salesMazadCalcList where tedadRooz equals to UPDATED_TEDAD_ROOZ
        defaultSalesMazadCalcShouldNotBeFound("tedadRooz.in=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTedadRoozIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tedadRooz is not null
        defaultSalesMazadCalcShouldBeFound("tedadRooz.specified=true");

        // Get all the salesMazadCalcList where tedadRooz is null
        defaultSalesMazadCalcShouldNotBeFound("tedadRooz.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTedadRoozIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tedadRooz greater than or equals to DEFAULT_TEDAD_ROOZ
        defaultSalesMazadCalcShouldBeFound("tedadRooz.greaterOrEqualThan=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesMazadCalcList where tedadRooz greater than or equals to UPDATED_TEDAD_ROOZ
        defaultSalesMazadCalcShouldNotBeFound("tedadRooz.greaterOrEqualThan=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTedadRoozIsLessThanSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tedadRooz less than or equals to DEFAULT_TEDAD_ROOZ
        defaultSalesMazadCalcShouldNotBeFound("tedadRooz.lessThan=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesMazadCalcList where tedadRooz less than or equals to UPDATED_TEDAD_ROOZ
        defaultSalesMazadCalcShouldBeFound("tedadRooz.lessThan=" + UPDATED_TEDAD_ROOZ);
    }


    @Test
    @Transactional
    public void getAllSalesMazadCalcsByHaghBimeIsEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where haghBime equals to DEFAULT_HAGH_BIME
        defaultSalesMazadCalcShouldBeFound("haghBime.equals=" + DEFAULT_HAGH_BIME);

        // Get all the salesMazadCalcList where haghBime equals to UPDATED_HAGH_BIME
        defaultSalesMazadCalcShouldNotBeFound("haghBime.equals=" + UPDATED_HAGH_BIME);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByHaghBimeIsInShouldWork() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where haghBime in DEFAULT_HAGH_BIME or UPDATED_HAGH_BIME
        defaultSalesMazadCalcShouldBeFound("haghBime.in=" + DEFAULT_HAGH_BIME + "," + UPDATED_HAGH_BIME);

        // Get all the salesMazadCalcList where haghBime equals to UPDATED_HAGH_BIME
        defaultSalesMazadCalcShouldNotBeFound("haghBime.in=" + UPDATED_HAGH_BIME);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByHaghBimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where haghBime is not null
        defaultSalesMazadCalcShouldBeFound("haghBime.specified=true");

        // Get all the salesMazadCalcList where haghBime is null
        defaultSalesMazadCalcShouldNotBeFound("haghBime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhShorooIsEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhShoroo equals to DEFAULT_TARIKH_SHOROO
        defaultSalesMazadCalcShouldBeFound("tarikhShoroo.equals=" + DEFAULT_TARIKH_SHOROO);

        // Get all the salesMazadCalcList where tarikhShoroo equals to UPDATED_TARIKH_SHOROO
        defaultSalesMazadCalcShouldNotBeFound("tarikhShoroo.equals=" + UPDATED_TARIKH_SHOROO);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhShorooIsInShouldWork() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhShoroo in DEFAULT_TARIKH_SHOROO or UPDATED_TARIKH_SHOROO
        defaultSalesMazadCalcShouldBeFound("tarikhShoroo.in=" + DEFAULT_TARIKH_SHOROO + "," + UPDATED_TARIKH_SHOROO);

        // Get all the salesMazadCalcList where tarikhShoroo equals to UPDATED_TARIKH_SHOROO
        defaultSalesMazadCalcShouldNotBeFound("tarikhShoroo.in=" + UPDATED_TARIKH_SHOROO);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhShorooIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhShoroo is not null
        defaultSalesMazadCalcShouldBeFound("tarikhShoroo.specified=true");

        // Get all the salesMazadCalcList where tarikhShoroo is null
        defaultSalesMazadCalcShouldNotBeFound("tarikhShoroo.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhShorooIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhShoroo greater than or equals to DEFAULT_TARIKH_SHOROO
        defaultSalesMazadCalcShouldBeFound("tarikhShoroo.greaterOrEqualThan=" + DEFAULT_TARIKH_SHOROO);

        // Get all the salesMazadCalcList where tarikhShoroo greater than or equals to UPDATED_TARIKH_SHOROO
        defaultSalesMazadCalcShouldNotBeFound("tarikhShoroo.greaterOrEqualThan=" + UPDATED_TARIKH_SHOROO);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhShorooIsLessThanSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhShoroo less than or equals to DEFAULT_TARIKH_SHOROO
        defaultSalesMazadCalcShouldNotBeFound("tarikhShoroo.lessThan=" + DEFAULT_TARIKH_SHOROO);

        // Get all the salesMazadCalcList where tarikhShoroo less than or equals to UPDATED_TARIKH_SHOROO
        defaultSalesMazadCalcShouldBeFound("tarikhShoroo.lessThan=" + UPDATED_TARIKH_SHOROO);
    }


    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhPayanIsEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhPayan equals to DEFAULT_TARIKH_PAYAN
        defaultSalesMazadCalcShouldBeFound("tarikhPayan.equals=" + DEFAULT_TARIKH_PAYAN);

        // Get all the salesMazadCalcList where tarikhPayan equals to UPDATED_TARIKH_PAYAN
        defaultSalesMazadCalcShouldNotBeFound("tarikhPayan.equals=" + UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhPayanIsInShouldWork() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhPayan in DEFAULT_TARIKH_PAYAN or UPDATED_TARIKH_PAYAN
        defaultSalesMazadCalcShouldBeFound("tarikhPayan.in=" + DEFAULT_TARIKH_PAYAN + "," + UPDATED_TARIKH_PAYAN);

        // Get all the salesMazadCalcList where tarikhPayan equals to UPDATED_TARIKH_PAYAN
        defaultSalesMazadCalcShouldNotBeFound("tarikhPayan.in=" + UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhPayanIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhPayan is not null
        defaultSalesMazadCalcShouldBeFound("tarikhPayan.specified=true");

        // Get all the salesMazadCalcList where tarikhPayan is null
        defaultSalesMazadCalcShouldNotBeFound("tarikhPayan.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhPayanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhPayan greater than or equals to DEFAULT_TARIKH_PAYAN
        defaultSalesMazadCalcShouldBeFound("tarikhPayan.greaterOrEqualThan=" + DEFAULT_TARIKH_PAYAN);

        // Get all the salesMazadCalcList where tarikhPayan greater than or equals to UPDATED_TARIKH_PAYAN
        defaultSalesMazadCalcShouldNotBeFound("tarikhPayan.greaterOrEqualThan=" + UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void getAllSalesMazadCalcsByTarikhPayanIsLessThanSomething() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        // Get all the salesMazadCalcList where tarikhPayan less than or equals to DEFAULT_TARIKH_PAYAN
        defaultSalesMazadCalcShouldNotBeFound("tarikhPayan.lessThan=" + DEFAULT_TARIKH_PAYAN);

        // Get all the salesMazadCalcList where tarikhPayan less than or equals to UPDATED_TARIKH_PAYAN
        defaultSalesMazadCalcShouldBeFound("tarikhPayan.lessThan=" + UPDATED_TARIKH_PAYAN);
    }


    @Test
    @Transactional
    public void getAllSalesMazadCalcsByNamesherkatIsEqualToSomething() throws Exception {
        // Initialize the database
        SherkatBime namesherkat = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(namesherkat);
        em.flush();
        salesMazadCalc.setNamesherkat(namesherkat);
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);
        Long namesherkatId = namesherkat.getId();

        // Get all the salesMazadCalcList where namesherkat equals to namesherkatId
        defaultSalesMazadCalcShouldBeFound("namesherkatId.equals=" + namesherkatId);

        // Get all the salesMazadCalcList where namesherkat equals to namesherkatId + 1
        defaultSalesMazadCalcShouldNotBeFound("namesherkatId.equals=" + (namesherkatId + 1));
    }


    @Test
    @Transactional
    public void getAllSalesMazadCalcsByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        salesMazadCalc.setGrouhKhodro(grouhKhodro);
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the salesMazadCalcList where grouhKhodro equals to grouhKhodroId
        defaultSalesMazadCalcShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the salesMazadCalcList where grouhKhodro equals to grouhKhodroId + 1
        defaultSalesMazadCalcShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSalesMazadCalcShouldBeFound(String filter) throws Exception {
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesMazadCalc.getId().intValue())))
            .andExpect(jsonPath("$.[*].mablaghSalesMazad").value(hasItem(DEFAULT_MABLAGH_SALES_MAZAD.doubleValue())))
            .andExpect(jsonPath("$.[*].tedadRooz").value(hasItem(DEFAULT_TEDAD_ROOZ)))
            .andExpect(jsonPath("$.[*].haghBime").value(hasItem(DEFAULT_HAGH_BIME.doubleValue())))
            .andExpect(jsonPath("$.[*].tarikhShoroo").value(hasItem(DEFAULT_TARIKH_SHOROO.toString())))
            .andExpect(jsonPath("$.[*].tarikhPayan").value(hasItem(DEFAULT_TARIKH_PAYAN.toString())));

        // Check, that the count call also returns 1
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSalesMazadCalcShouldNotBeFound(String filter) throws Exception {
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSalesMazadCalc() throws Exception {
        // Get the salesMazadCalc
        restSalesMazadCalcMockMvc.perform(get("/api/sales-mazad-calcs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesMazadCalc() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        int databaseSizeBeforeUpdate = salesMazadCalcRepository.findAll().size();

        // Update the salesMazadCalc
        SalesMazadCalc updatedSalesMazadCalc = salesMazadCalcRepository.findById(salesMazadCalc.getId()).get();
        // Disconnect from session so that the updates on updatedSalesMazadCalc are not directly saved in db
        em.detach(updatedSalesMazadCalc);
        updatedSalesMazadCalc
            .mablaghSalesMazad(UPDATED_MABLAGH_SALES_MAZAD)
            .tedadRooz(UPDATED_TEDAD_ROOZ)
            .haghBime(UPDATED_HAGH_BIME)
            .tarikhShoroo(UPDATED_TARIKH_SHOROO)
            .tarikhPayan(UPDATED_TARIKH_PAYAN);
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(updatedSalesMazadCalc);

        restSalesMazadCalcMockMvc.perform(put("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isOk());

        // Validate the SalesMazadCalc in the database
        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeUpdate);
        SalesMazadCalc testSalesMazadCalc = salesMazadCalcList.get(salesMazadCalcList.size() - 1);
        assertThat(testSalesMazadCalc.getMablaghSalesMazad()).isEqualTo(UPDATED_MABLAGH_SALES_MAZAD);
        assertThat(testSalesMazadCalc.getTedadRooz()).isEqualTo(UPDATED_TEDAD_ROOZ);
        assertThat(testSalesMazadCalc.getHaghBime()).isEqualTo(UPDATED_HAGH_BIME);
        assertThat(testSalesMazadCalc.getTarikhShoroo()).isEqualTo(UPDATED_TARIKH_SHOROO);
        assertThat(testSalesMazadCalc.getTarikhPayan()).isEqualTo(UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesMazadCalc() throws Exception {
        int databaseSizeBeforeUpdate = salesMazadCalcRepository.findAll().size();

        // Create the SalesMazadCalc
        SalesMazadCalcDTO salesMazadCalcDTO = salesMazadCalcMapper.toDto(salesMazadCalc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesMazadCalcMockMvc.perform(put("/api/sales-mazad-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesMazadCalcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesMazadCalc in the database
        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesMazadCalc() throws Exception {
        // Initialize the database
        salesMazadCalcRepository.saveAndFlush(salesMazadCalc);

        int databaseSizeBeforeDelete = salesMazadCalcRepository.findAll().size();

        // Delete the salesMazadCalc
        restSalesMazadCalcMockMvc.perform(delete("/api/sales-mazad-calcs/{id}", salesMazadCalc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SalesMazadCalc> salesMazadCalcList = salesMazadCalcRepository.findAll();
        assertThat(salesMazadCalcList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesMazadCalc.class);
        SalesMazadCalc salesMazadCalc1 = new SalesMazadCalc();
        salesMazadCalc1.setId(1L);
        SalesMazadCalc salesMazadCalc2 = new SalesMazadCalc();
        salesMazadCalc2.setId(salesMazadCalc1.getId());
        assertThat(salesMazadCalc1).isEqualTo(salesMazadCalc2);
        salesMazadCalc2.setId(2L);
        assertThat(salesMazadCalc1).isNotEqualTo(salesMazadCalc2);
        salesMazadCalc1.setId(null);
        assertThat(salesMazadCalc1).isNotEqualTo(salesMazadCalc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesMazadCalcDTO.class);
        SalesMazadCalcDTO salesMazadCalcDTO1 = new SalesMazadCalcDTO();
        salesMazadCalcDTO1.setId(1L);
        SalesMazadCalcDTO salesMazadCalcDTO2 = new SalesMazadCalcDTO();
        assertThat(salesMazadCalcDTO1).isNotEqualTo(salesMazadCalcDTO2);
        salesMazadCalcDTO2.setId(salesMazadCalcDTO1.getId());
        assertThat(salesMazadCalcDTO1).isEqualTo(salesMazadCalcDTO2);
        salesMazadCalcDTO2.setId(2L);
        assertThat(salesMazadCalcDTO1).isNotEqualTo(salesMazadCalcDTO2);
        salesMazadCalcDTO1.setId(null);
        assertThat(salesMazadCalcDTO1).isNotEqualTo(salesMazadCalcDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(salesMazadCalcMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(salesMazadCalcMapper.fromId(null)).isNull();
    }
}
