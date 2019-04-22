package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.SalesSarneshinCalc;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.SalesSarneshinCalcRepository;
import ir.insurance.startup.service.SalesSarneshinCalcService;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;
import ir.insurance.startup.service.mapper.SalesSarneshinCalcMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.SalesSarneshinCalcCriteria;
import ir.insurance.startup.service.SalesSarneshinCalcQueryService;

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
 * Test class for the SalesSarneshinCalcResource REST controller.
 *
 * @see SalesSarneshinCalcResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SalesSarneshinCalcResourceIntTest {

    private static final Float DEFAULT_MABLAGH_SALES_MAZAD = 0F;
    private static final Float UPDATED_MABLAGH_SALES_MAZAD = 1F;

    private static final Integer DEFAULT_TEDAD_ROOZ = 0;
    private static final Integer UPDATED_TEDAD_ROOZ = 1;

    private static final Float DEFAULT_MABLAGH_HAGH_BIME = 1F;
    private static final Float UPDATED_MABLAGH_HAGH_BIME = 2F;

    private static final LocalDate DEFAULT_TARIKH_SHOROO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH_SHOROO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TARIKH_PAYAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH_PAYAN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SalesSarneshinCalcRepository salesSarneshinCalcRepository;

    @Autowired
    private SalesSarneshinCalcMapper salesSarneshinCalcMapper;

    @Autowired
    private SalesSarneshinCalcService salesSarneshinCalcService;

    @Autowired
    private SalesSarneshinCalcQueryService salesSarneshinCalcQueryService;

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

    private MockMvc restSalesSarneshinCalcMockMvc;

    private SalesSarneshinCalc salesSarneshinCalc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SalesSarneshinCalcResource salesSarneshinCalcResource = new SalesSarneshinCalcResource(salesSarneshinCalcService, salesSarneshinCalcQueryService);
        this.restSalesSarneshinCalcMockMvc = MockMvcBuilders.standaloneSetup(salesSarneshinCalcResource)
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
    public static SalesSarneshinCalc createEntity(EntityManager em) {
        SalesSarneshinCalc salesSarneshinCalc = new SalesSarneshinCalc()
            .mablaghSalesMazad(DEFAULT_MABLAGH_SALES_MAZAD)
            .tedadRooz(DEFAULT_TEDAD_ROOZ)
            .mablaghHaghBime(DEFAULT_MABLAGH_HAGH_BIME)
            .tarikhShoroo(DEFAULT_TARIKH_SHOROO)
            .tarikhPayan(DEFAULT_TARIKH_PAYAN);
        // Add required entity
        SherkatBime sherkatBime = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(sherkatBime);
        em.flush();
        salesSarneshinCalc.setNamesherkat(sherkatBime);
        // Add required entity
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        salesSarneshinCalc.setGrouhKhodro(grouhKhodro);
        return salesSarneshinCalc;
    }

    @Before
    public void initTest() {
        salesSarneshinCalc = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesSarneshinCalc() throws Exception {
        int databaseSizeBeforeCreate = salesSarneshinCalcRepository.findAll().size();

        // Create the SalesSarneshinCalc
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);
        restSalesSarneshinCalcMockMvc.perform(post("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesSarneshinCalc in the database
        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeCreate + 1);
        SalesSarneshinCalc testSalesSarneshinCalc = salesSarneshinCalcList.get(salesSarneshinCalcList.size() - 1);
        assertThat(testSalesSarneshinCalc.getMablaghSalesMazad()).isEqualTo(DEFAULT_MABLAGH_SALES_MAZAD);
        assertThat(testSalesSarneshinCalc.getTedadRooz()).isEqualTo(DEFAULT_TEDAD_ROOZ);
        assertThat(testSalesSarneshinCalc.getMablaghHaghBime()).isEqualTo(DEFAULT_MABLAGH_HAGH_BIME);
        assertThat(testSalesSarneshinCalc.getTarikhShoroo()).isEqualTo(DEFAULT_TARIKH_SHOROO);
        assertThat(testSalesSarneshinCalc.getTarikhPayan()).isEqualTo(DEFAULT_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void createSalesSarneshinCalcWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesSarneshinCalcRepository.findAll().size();

        // Create the SalesSarneshinCalc with an existing ID
        salesSarneshinCalc.setId(1L);
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesSarneshinCalcMockMvc.perform(post("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesSarneshinCalc in the database
        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMablaghSalesMazadIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesSarneshinCalcRepository.findAll().size();
        // set the field null
        salesSarneshinCalc.setMablaghSalesMazad(null);

        // Create the SalesSarneshinCalc, which fails.
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);

        restSalesSarneshinCalcMockMvc.perform(post("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTedadRoozIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesSarneshinCalcRepository.findAll().size();
        // set the field null
        salesSarneshinCalc.setTedadRooz(null);

        // Create the SalesSarneshinCalc, which fails.
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);

        restSalesSarneshinCalcMockMvc.perform(post("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMablaghHaghBimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesSarneshinCalcRepository.findAll().size();
        // set the field null
        salesSarneshinCalc.setMablaghHaghBime(null);

        // Create the SalesSarneshinCalc, which fails.
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);

        restSalesSarneshinCalcMockMvc.perform(post("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTarikhShorooIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesSarneshinCalcRepository.findAll().size();
        // set the field null
        salesSarneshinCalc.setTarikhShoroo(null);

        // Create the SalesSarneshinCalc, which fails.
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);

        restSalesSarneshinCalcMockMvc.perform(post("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isBadRequest());

        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcs() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesSarneshinCalc.getId().intValue())))
            .andExpect(jsonPath("$.[*].mablaghSalesMazad").value(hasItem(DEFAULT_MABLAGH_SALES_MAZAD.doubleValue())))
            .andExpect(jsonPath("$.[*].tedadRooz").value(hasItem(DEFAULT_TEDAD_ROOZ)))
            .andExpect(jsonPath("$.[*].mablaghHaghBime").value(hasItem(DEFAULT_MABLAGH_HAGH_BIME.doubleValue())))
            .andExpect(jsonPath("$.[*].tarikhShoroo").value(hasItem(DEFAULT_TARIKH_SHOROO.toString())))
            .andExpect(jsonPath("$.[*].tarikhPayan").value(hasItem(DEFAULT_TARIKH_PAYAN.toString())));
    }
    
    @Test
    @Transactional
    public void getSalesSarneshinCalc() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get the salesSarneshinCalc
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs/{id}", salesSarneshinCalc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(salesSarneshinCalc.getId().intValue()))
            .andExpect(jsonPath("$.mablaghSalesMazad").value(DEFAULT_MABLAGH_SALES_MAZAD.doubleValue()))
            .andExpect(jsonPath("$.tedadRooz").value(DEFAULT_TEDAD_ROOZ))
            .andExpect(jsonPath("$.mablaghHaghBime").value(DEFAULT_MABLAGH_HAGH_BIME.doubleValue()))
            .andExpect(jsonPath("$.tarikhShoroo").value(DEFAULT_TARIKH_SHOROO.toString()))
            .andExpect(jsonPath("$.tarikhPayan").value(DEFAULT_TARIKH_PAYAN.toString()));
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByMablaghSalesMazadIsEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where mablaghSalesMazad equals to DEFAULT_MABLAGH_SALES_MAZAD
        defaultSalesSarneshinCalcShouldBeFound("mablaghSalesMazad.equals=" + DEFAULT_MABLAGH_SALES_MAZAD);

        // Get all the salesSarneshinCalcList where mablaghSalesMazad equals to UPDATED_MABLAGH_SALES_MAZAD
        defaultSalesSarneshinCalcShouldNotBeFound("mablaghSalesMazad.equals=" + UPDATED_MABLAGH_SALES_MAZAD);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByMablaghSalesMazadIsInShouldWork() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where mablaghSalesMazad in DEFAULT_MABLAGH_SALES_MAZAD or UPDATED_MABLAGH_SALES_MAZAD
        defaultSalesSarneshinCalcShouldBeFound("mablaghSalesMazad.in=" + DEFAULT_MABLAGH_SALES_MAZAD + "," + UPDATED_MABLAGH_SALES_MAZAD);

        // Get all the salesSarneshinCalcList where mablaghSalesMazad equals to UPDATED_MABLAGH_SALES_MAZAD
        defaultSalesSarneshinCalcShouldNotBeFound("mablaghSalesMazad.in=" + UPDATED_MABLAGH_SALES_MAZAD);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByMablaghSalesMazadIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where mablaghSalesMazad is not null
        defaultSalesSarneshinCalcShouldBeFound("mablaghSalesMazad.specified=true");

        // Get all the salesSarneshinCalcList where mablaghSalesMazad is null
        defaultSalesSarneshinCalcShouldNotBeFound("mablaghSalesMazad.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTedadRoozIsEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tedadRooz equals to DEFAULT_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldBeFound("tedadRooz.equals=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesSarneshinCalcList where tedadRooz equals to UPDATED_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldNotBeFound("tedadRooz.equals=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTedadRoozIsInShouldWork() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tedadRooz in DEFAULT_TEDAD_ROOZ or UPDATED_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldBeFound("tedadRooz.in=" + DEFAULT_TEDAD_ROOZ + "," + UPDATED_TEDAD_ROOZ);

        // Get all the salesSarneshinCalcList where tedadRooz equals to UPDATED_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldNotBeFound("tedadRooz.in=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTedadRoozIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tedadRooz is not null
        defaultSalesSarneshinCalcShouldBeFound("tedadRooz.specified=true");

        // Get all the salesSarneshinCalcList where tedadRooz is null
        defaultSalesSarneshinCalcShouldNotBeFound("tedadRooz.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTedadRoozIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tedadRooz greater than or equals to DEFAULT_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldBeFound("tedadRooz.greaterOrEqualThan=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesSarneshinCalcList where tedadRooz greater than or equals to UPDATED_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldNotBeFound("tedadRooz.greaterOrEqualThan=" + UPDATED_TEDAD_ROOZ);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTedadRoozIsLessThanSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tedadRooz less than or equals to DEFAULT_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldNotBeFound("tedadRooz.lessThan=" + DEFAULT_TEDAD_ROOZ);

        // Get all the salesSarneshinCalcList where tedadRooz less than or equals to UPDATED_TEDAD_ROOZ
        defaultSalesSarneshinCalcShouldBeFound("tedadRooz.lessThan=" + UPDATED_TEDAD_ROOZ);
    }


    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByMablaghHaghBimeIsEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where mablaghHaghBime equals to DEFAULT_MABLAGH_HAGH_BIME
        defaultSalesSarneshinCalcShouldBeFound("mablaghHaghBime.equals=" + DEFAULT_MABLAGH_HAGH_BIME);

        // Get all the salesSarneshinCalcList where mablaghHaghBime equals to UPDATED_MABLAGH_HAGH_BIME
        defaultSalesSarneshinCalcShouldNotBeFound("mablaghHaghBime.equals=" + UPDATED_MABLAGH_HAGH_BIME);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByMablaghHaghBimeIsInShouldWork() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where mablaghHaghBime in DEFAULT_MABLAGH_HAGH_BIME or UPDATED_MABLAGH_HAGH_BIME
        defaultSalesSarneshinCalcShouldBeFound("mablaghHaghBime.in=" + DEFAULT_MABLAGH_HAGH_BIME + "," + UPDATED_MABLAGH_HAGH_BIME);

        // Get all the salesSarneshinCalcList where mablaghHaghBime equals to UPDATED_MABLAGH_HAGH_BIME
        defaultSalesSarneshinCalcShouldNotBeFound("mablaghHaghBime.in=" + UPDATED_MABLAGH_HAGH_BIME);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByMablaghHaghBimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where mablaghHaghBime is not null
        defaultSalesSarneshinCalcShouldBeFound("mablaghHaghBime.specified=true");

        // Get all the salesSarneshinCalcList where mablaghHaghBime is null
        defaultSalesSarneshinCalcShouldNotBeFound("mablaghHaghBime.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhShorooIsEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhShoroo equals to DEFAULT_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldBeFound("tarikhShoroo.equals=" + DEFAULT_TARIKH_SHOROO);

        // Get all the salesSarneshinCalcList where tarikhShoroo equals to UPDATED_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhShoroo.equals=" + UPDATED_TARIKH_SHOROO);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhShorooIsInShouldWork() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhShoroo in DEFAULT_TARIKH_SHOROO or UPDATED_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldBeFound("tarikhShoroo.in=" + DEFAULT_TARIKH_SHOROO + "," + UPDATED_TARIKH_SHOROO);

        // Get all the salesSarneshinCalcList where tarikhShoroo equals to UPDATED_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhShoroo.in=" + UPDATED_TARIKH_SHOROO);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhShorooIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhShoroo is not null
        defaultSalesSarneshinCalcShouldBeFound("tarikhShoroo.specified=true");

        // Get all the salesSarneshinCalcList where tarikhShoroo is null
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhShoroo.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhShorooIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhShoroo greater than or equals to DEFAULT_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldBeFound("tarikhShoroo.greaterOrEqualThan=" + DEFAULT_TARIKH_SHOROO);

        // Get all the salesSarneshinCalcList where tarikhShoroo greater than or equals to UPDATED_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhShoroo.greaterOrEqualThan=" + UPDATED_TARIKH_SHOROO);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhShorooIsLessThanSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhShoroo less than or equals to DEFAULT_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhShoroo.lessThan=" + DEFAULT_TARIKH_SHOROO);

        // Get all the salesSarneshinCalcList where tarikhShoroo less than or equals to UPDATED_TARIKH_SHOROO
        defaultSalesSarneshinCalcShouldBeFound("tarikhShoroo.lessThan=" + UPDATED_TARIKH_SHOROO);
    }


    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhPayanIsEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhPayan equals to DEFAULT_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldBeFound("tarikhPayan.equals=" + DEFAULT_TARIKH_PAYAN);

        // Get all the salesSarneshinCalcList where tarikhPayan equals to UPDATED_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhPayan.equals=" + UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhPayanIsInShouldWork() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhPayan in DEFAULT_TARIKH_PAYAN or UPDATED_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldBeFound("tarikhPayan.in=" + DEFAULT_TARIKH_PAYAN + "," + UPDATED_TARIKH_PAYAN);

        // Get all the salesSarneshinCalcList where tarikhPayan equals to UPDATED_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhPayan.in=" + UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhPayanIsNullOrNotNull() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhPayan is not null
        defaultSalesSarneshinCalcShouldBeFound("tarikhPayan.specified=true");

        // Get all the salesSarneshinCalcList where tarikhPayan is null
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhPayan.specified=false");
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhPayanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhPayan greater than or equals to DEFAULT_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldBeFound("tarikhPayan.greaterOrEqualThan=" + DEFAULT_TARIKH_PAYAN);

        // Get all the salesSarneshinCalcList where tarikhPayan greater than or equals to UPDATED_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhPayan.greaterOrEqualThan=" + UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByTarikhPayanIsLessThanSomething() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        // Get all the salesSarneshinCalcList where tarikhPayan less than or equals to DEFAULT_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldNotBeFound("tarikhPayan.lessThan=" + DEFAULT_TARIKH_PAYAN);

        // Get all the salesSarneshinCalcList where tarikhPayan less than or equals to UPDATED_TARIKH_PAYAN
        defaultSalesSarneshinCalcShouldBeFound("tarikhPayan.lessThan=" + UPDATED_TARIKH_PAYAN);
    }


    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByNamesherkatIsEqualToSomething() throws Exception {
        // Initialize the database
        SherkatBime namesherkat = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(namesherkat);
        em.flush();
        salesSarneshinCalc.setNamesherkat(namesherkat);
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);
        Long namesherkatId = namesherkat.getId();

        // Get all the salesSarneshinCalcList where namesherkat equals to namesherkatId
        defaultSalesSarneshinCalcShouldBeFound("namesherkatId.equals=" + namesherkatId);

        // Get all the salesSarneshinCalcList where namesherkat equals to namesherkatId + 1
        defaultSalesSarneshinCalcShouldNotBeFound("namesherkatId.equals=" + (namesherkatId + 1));
    }


    @Test
    @Transactional
    public void getAllSalesSarneshinCalcsByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        salesSarneshinCalc.setGrouhKhodro(grouhKhodro);
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the salesSarneshinCalcList where grouhKhodro equals to grouhKhodroId
        defaultSalesSarneshinCalcShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the salesSarneshinCalcList where grouhKhodro equals to grouhKhodroId + 1
        defaultSalesSarneshinCalcShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSalesSarneshinCalcShouldBeFound(String filter) throws Exception {
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesSarneshinCalc.getId().intValue())))
            .andExpect(jsonPath("$.[*].mablaghSalesMazad").value(hasItem(DEFAULT_MABLAGH_SALES_MAZAD.doubleValue())))
            .andExpect(jsonPath("$.[*].tedadRooz").value(hasItem(DEFAULT_TEDAD_ROOZ)))
            .andExpect(jsonPath("$.[*].mablaghHaghBime").value(hasItem(DEFAULT_MABLAGH_HAGH_BIME.doubleValue())))
            .andExpect(jsonPath("$.[*].tarikhShoroo").value(hasItem(DEFAULT_TARIKH_SHOROO.toString())))
            .andExpect(jsonPath("$.[*].tarikhPayan").value(hasItem(DEFAULT_TARIKH_PAYAN.toString())));

        // Check, that the count call also returns 1
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSalesSarneshinCalcShouldNotBeFound(String filter) throws Exception {
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSalesSarneshinCalc() throws Exception {
        // Get the salesSarneshinCalc
        restSalesSarneshinCalcMockMvc.perform(get("/api/sales-sarneshin-calcs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesSarneshinCalc() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        int databaseSizeBeforeUpdate = salesSarneshinCalcRepository.findAll().size();

        // Update the salesSarneshinCalc
        SalesSarneshinCalc updatedSalesSarneshinCalc = salesSarneshinCalcRepository.findById(salesSarneshinCalc.getId()).get();
        // Disconnect from session so that the updates on updatedSalesSarneshinCalc are not directly saved in db
        em.detach(updatedSalesSarneshinCalc);
        updatedSalesSarneshinCalc
            .mablaghSalesMazad(UPDATED_MABLAGH_SALES_MAZAD)
            .tedadRooz(UPDATED_TEDAD_ROOZ)
            .mablaghHaghBime(UPDATED_MABLAGH_HAGH_BIME)
            .tarikhShoroo(UPDATED_TARIKH_SHOROO)
            .tarikhPayan(UPDATED_TARIKH_PAYAN);
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(updatedSalesSarneshinCalc);

        restSalesSarneshinCalcMockMvc.perform(put("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isOk());

        // Validate the SalesSarneshinCalc in the database
        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeUpdate);
        SalesSarneshinCalc testSalesSarneshinCalc = salesSarneshinCalcList.get(salesSarneshinCalcList.size() - 1);
        assertThat(testSalesSarneshinCalc.getMablaghSalesMazad()).isEqualTo(UPDATED_MABLAGH_SALES_MAZAD);
        assertThat(testSalesSarneshinCalc.getTedadRooz()).isEqualTo(UPDATED_TEDAD_ROOZ);
        assertThat(testSalesSarneshinCalc.getMablaghHaghBime()).isEqualTo(UPDATED_MABLAGH_HAGH_BIME);
        assertThat(testSalesSarneshinCalc.getTarikhShoroo()).isEqualTo(UPDATED_TARIKH_SHOROO);
        assertThat(testSalesSarneshinCalc.getTarikhPayan()).isEqualTo(UPDATED_TARIKH_PAYAN);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesSarneshinCalc() throws Exception {
        int databaseSizeBeforeUpdate = salesSarneshinCalcRepository.findAll().size();

        // Create the SalesSarneshinCalc
        SalesSarneshinCalcDTO salesSarneshinCalcDTO = salesSarneshinCalcMapper.toDto(salesSarneshinCalc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesSarneshinCalcMockMvc.perform(put("/api/sales-sarneshin-calcs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(salesSarneshinCalcDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesSarneshinCalc in the database
        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesSarneshinCalc() throws Exception {
        // Initialize the database
        salesSarneshinCalcRepository.saveAndFlush(salesSarneshinCalc);

        int databaseSizeBeforeDelete = salesSarneshinCalcRepository.findAll().size();

        // Delete the salesSarneshinCalc
        restSalesSarneshinCalcMockMvc.perform(delete("/api/sales-sarneshin-calcs/{id}", salesSarneshinCalc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SalesSarneshinCalc> salesSarneshinCalcList = salesSarneshinCalcRepository.findAll();
        assertThat(salesSarneshinCalcList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesSarneshinCalc.class);
        SalesSarneshinCalc salesSarneshinCalc1 = new SalesSarneshinCalc();
        salesSarneshinCalc1.setId(1L);
        SalesSarneshinCalc salesSarneshinCalc2 = new SalesSarneshinCalc();
        salesSarneshinCalc2.setId(salesSarneshinCalc1.getId());
        assertThat(salesSarneshinCalc1).isEqualTo(salesSarneshinCalc2);
        salesSarneshinCalc2.setId(2L);
        assertThat(salesSarneshinCalc1).isNotEqualTo(salesSarneshinCalc2);
        salesSarneshinCalc1.setId(null);
        assertThat(salesSarneshinCalc1).isNotEqualTo(salesSarneshinCalc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesSarneshinCalcDTO.class);
        SalesSarneshinCalcDTO salesSarneshinCalcDTO1 = new SalesSarneshinCalcDTO();
        salesSarneshinCalcDTO1.setId(1L);
        SalesSarneshinCalcDTO salesSarneshinCalcDTO2 = new SalesSarneshinCalcDTO();
        assertThat(salesSarneshinCalcDTO1).isNotEqualTo(salesSarneshinCalcDTO2);
        salesSarneshinCalcDTO2.setId(salesSarneshinCalcDTO1.getId());
        assertThat(salesSarneshinCalcDTO1).isEqualTo(salesSarneshinCalcDTO2);
        salesSarneshinCalcDTO2.setId(2L);
        assertThat(salesSarneshinCalcDTO1).isNotEqualTo(salesSarneshinCalcDTO2);
        salesSarneshinCalcDTO1.setId(null);
        assertThat(salesSarneshinCalcDTO1).isNotEqualTo(salesSarneshinCalcDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(salesSarneshinCalcMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(salesSarneshinCalcMapper.fromId(null)).isNull();
    }
}
