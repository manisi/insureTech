package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.MoredEstefadeSales;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.domain.OnvanKhodro;
import ir.insurance.startup.repository.MoredEstefadeSalesRepository;
import ir.insurance.startup.service.MoredEstefadeSalesService;
import ir.insurance.startup.service.dto.MoredEstefadeSalesDTO;
import ir.insurance.startup.service.mapper.MoredEstefadeSalesMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.MoredEstefadeSalesCriteria;
import ir.insurance.startup.service.MoredEstefadeSalesQueryService;

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
 * Test class for the MoredEstefadeSalesResource REST controller.
 *
 * @see MoredEstefadeSalesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class MoredEstefadeSalesResourceIntTest {

    private static final Float DEFAULT_DARSAD_EZAFE_NERKH = 0F;
    private static final Float UPDATED_DARSAD_EZAFE_NERKH = 1F;

    private static final LocalDate DEFAULT_AZ_TARIKH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AZ_TARIKH = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TA_TARIKH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TA_TARIKH = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private MoredEstefadeSalesRepository moredEstefadeSalesRepository;

    @Autowired
    private MoredEstefadeSalesMapper moredEstefadeSalesMapper;

    @Autowired
    private MoredEstefadeSalesService moredEstefadeSalesService;

    @Autowired
    private MoredEstefadeSalesQueryService moredEstefadeSalesQueryService;

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

    private MockMvc restMoredEstefadeSalesMockMvc;

    private MoredEstefadeSales moredEstefadeSales;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MoredEstefadeSalesResource moredEstefadeSalesResource = new MoredEstefadeSalesResource(moredEstefadeSalesService, moredEstefadeSalesQueryService);
        this.restMoredEstefadeSalesMockMvc = MockMvcBuilders.standaloneSetup(moredEstefadeSalesResource)
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
    public static MoredEstefadeSales createEntity(EntityManager em) {
        MoredEstefadeSales moredEstefadeSales = new MoredEstefadeSales()
            .darsadEzafeNerkh(DEFAULT_DARSAD_EZAFE_NERKH)
            .azTarikh(DEFAULT_AZ_TARIKH)
            .taTarikh(DEFAULT_TA_TARIKH)
            .faal(DEFAULT_FAAL);
        // Add required entity
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        moredEstefadeSales.setGrouhKhodro(grouhKhodro);
        // Add required entity
        SherkatBime sherkatBime = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(sherkatBime);
        em.flush();
        moredEstefadeSales.setSherkatBime(sherkatBime);
        // Add required entity
        OnvanKhodro onvanKhodro = OnvanKhodroResourceIntTest.createEntity(em);
        em.persist(onvanKhodro);
        em.flush();
        moredEstefadeSales.setOnvanKhodro(onvanKhodro);
        return moredEstefadeSales;
    }

    @Before
    public void initTest() {
        moredEstefadeSales = createEntity(em);
    }

    @Test
    @Transactional
    public void createMoredEstefadeSales() throws Exception {
        int databaseSizeBeforeCreate = moredEstefadeSalesRepository.findAll().size();

        // Create the MoredEstefadeSales
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);
        restMoredEstefadeSalesMockMvc.perform(post("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isCreated());

        // Validate the MoredEstefadeSales in the database
        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeCreate + 1);
        MoredEstefadeSales testMoredEstefadeSales = moredEstefadeSalesList.get(moredEstefadeSalesList.size() - 1);
        assertThat(testMoredEstefadeSales.getDarsadEzafeNerkh()).isEqualTo(DEFAULT_DARSAD_EZAFE_NERKH);
        assertThat(testMoredEstefadeSales.getAzTarikh()).isEqualTo(DEFAULT_AZ_TARIKH);
        assertThat(testMoredEstefadeSales.getTaTarikh()).isEqualTo(DEFAULT_TA_TARIKH);
        assertThat(testMoredEstefadeSales.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createMoredEstefadeSalesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moredEstefadeSalesRepository.findAll().size();

        // Create the MoredEstefadeSales with an existing ID
        moredEstefadeSales.setId(1L);
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMoredEstefadeSalesMockMvc.perform(post("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MoredEstefadeSales in the database
        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDarsadEzafeNerkhIsRequired() throws Exception {
        int databaseSizeBeforeTest = moredEstefadeSalesRepository.findAll().size();
        // set the field null
        moredEstefadeSales.setDarsadEzafeNerkh(null);

        // Create the MoredEstefadeSales, which fails.
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);

        restMoredEstefadeSalesMockMvc.perform(post("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isBadRequest());

        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAzTarikhIsRequired() throws Exception {
        int databaseSizeBeforeTest = moredEstefadeSalesRepository.findAll().size();
        // set the field null
        moredEstefadeSales.setAzTarikh(null);

        // Create the MoredEstefadeSales, which fails.
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);

        restMoredEstefadeSalesMockMvc.perform(post("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isBadRequest());

        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTaTarikhIsRequired() throws Exception {
        int databaseSizeBeforeTest = moredEstefadeSalesRepository.findAll().size();
        // set the field null
        moredEstefadeSales.setTaTarikh(null);

        // Create the MoredEstefadeSales, which fails.
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);

        restMoredEstefadeSalesMockMvc.perform(post("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isBadRequest());

        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = moredEstefadeSalesRepository.findAll().size();
        // set the field null
        moredEstefadeSales.setFaal(null);

        // Create the MoredEstefadeSales, which fails.
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);

        restMoredEstefadeSalesMockMvc.perform(post("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isBadRequest());

        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSales() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(moredEstefadeSales.getId().intValue())))
            .andExpect(jsonPath("$.[*].darsadEzafeNerkh").value(hasItem(DEFAULT_DARSAD_EZAFE_NERKH.doubleValue())))
            .andExpect(jsonPath("$.[*].azTarikh").value(hasItem(DEFAULT_AZ_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].taTarikh").value(hasItem(DEFAULT_TA_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getMoredEstefadeSales() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get the moredEstefadeSales
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales/{id}", moredEstefadeSales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(moredEstefadeSales.getId().intValue()))
            .andExpect(jsonPath("$.darsadEzafeNerkh").value(DEFAULT_DARSAD_EZAFE_NERKH.doubleValue()))
            .andExpect(jsonPath("$.azTarikh").value(DEFAULT_AZ_TARIKH.toString()))
            .andExpect(jsonPath("$.taTarikh").value(DEFAULT_TA_TARIKH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByDarsadEzafeNerkhIsEqualToSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where darsadEzafeNerkh equals to DEFAULT_DARSAD_EZAFE_NERKH
        defaultMoredEstefadeSalesShouldBeFound("darsadEzafeNerkh.equals=" + DEFAULT_DARSAD_EZAFE_NERKH);

        // Get all the moredEstefadeSalesList where darsadEzafeNerkh equals to UPDATED_DARSAD_EZAFE_NERKH
        defaultMoredEstefadeSalesShouldNotBeFound("darsadEzafeNerkh.equals=" + UPDATED_DARSAD_EZAFE_NERKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByDarsadEzafeNerkhIsInShouldWork() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where darsadEzafeNerkh in DEFAULT_DARSAD_EZAFE_NERKH or UPDATED_DARSAD_EZAFE_NERKH
        defaultMoredEstefadeSalesShouldBeFound("darsadEzafeNerkh.in=" + DEFAULT_DARSAD_EZAFE_NERKH + "," + UPDATED_DARSAD_EZAFE_NERKH);

        // Get all the moredEstefadeSalesList where darsadEzafeNerkh equals to UPDATED_DARSAD_EZAFE_NERKH
        defaultMoredEstefadeSalesShouldNotBeFound("darsadEzafeNerkh.in=" + UPDATED_DARSAD_EZAFE_NERKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByDarsadEzafeNerkhIsNullOrNotNull() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where darsadEzafeNerkh is not null
        defaultMoredEstefadeSalesShouldBeFound("darsadEzafeNerkh.specified=true");

        // Get all the moredEstefadeSalesList where darsadEzafeNerkh is null
        defaultMoredEstefadeSalesShouldNotBeFound("darsadEzafeNerkh.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByAzTarikhIsEqualToSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where azTarikh equals to DEFAULT_AZ_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("azTarikh.equals=" + DEFAULT_AZ_TARIKH);

        // Get all the moredEstefadeSalesList where azTarikh equals to UPDATED_AZ_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("azTarikh.equals=" + UPDATED_AZ_TARIKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByAzTarikhIsInShouldWork() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where azTarikh in DEFAULT_AZ_TARIKH or UPDATED_AZ_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("azTarikh.in=" + DEFAULT_AZ_TARIKH + "," + UPDATED_AZ_TARIKH);

        // Get all the moredEstefadeSalesList where azTarikh equals to UPDATED_AZ_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("azTarikh.in=" + UPDATED_AZ_TARIKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByAzTarikhIsNullOrNotNull() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where azTarikh is not null
        defaultMoredEstefadeSalesShouldBeFound("azTarikh.specified=true");

        // Get all the moredEstefadeSalesList where azTarikh is null
        defaultMoredEstefadeSalesShouldNotBeFound("azTarikh.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByAzTarikhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where azTarikh greater than or equals to DEFAULT_AZ_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("azTarikh.greaterOrEqualThan=" + DEFAULT_AZ_TARIKH);

        // Get all the moredEstefadeSalesList where azTarikh greater than or equals to UPDATED_AZ_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("azTarikh.greaterOrEqualThan=" + UPDATED_AZ_TARIKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByAzTarikhIsLessThanSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where azTarikh less than or equals to DEFAULT_AZ_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("azTarikh.lessThan=" + DEFAULT_AZ_TARIKH);

        // Get all the moredEstefadeSalesList where azTarikh less than or equals to UPDATED_AZ_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("azTarikh.lessThan=" + UPDATED_AZ_TARIKH);
    }


    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByTaTarikhIsEqualToSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where taTarikh equals to DEFAULT_TA_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("taTarikh.equals=" + DEFAULT_TA_TARIKH);

        // Get all the moredEstefadeSalesList where taTarikh equals to UPDATED_TA_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("taTarikh.equals=" + UPDATED_TA_TARIKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByTaTarikhIsInShouldWork() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where taTarikh in DEFAULT_TA_TARIKH or UPDATED_TA_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("taTarikh.in=" + DEFAULT_TA_TARIKH + "," + UPDATED_TA_TARIKH);

        // Get all the moredEstefadeSalesList where taTarikh equals to UPDATED_TA_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("taTarikh.in=" + UPDATED_TA_TARIKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByTaTarikhIsNullOrNotNull() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where taTarikh is not null
        defaultMoredEstefadeSalesShouldBeFound("taTarikh.specified=true");

        // Get all the moredEstefadeSalesList where taTarikh is null
        defaultMoredEstefadeSalesShouldNotBeFound("taTarikh.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByTaTarikhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where taTarikh greater than or equals to DEFAULT_TA_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("taTarikh.greaterOrEqualThan=" + DEFAULT_TA_TARIKH);

        // Get all the moredEstefadeSalesList where taTarikh greater than or equals to UPDATED_TA_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("taTarikh.greaterOrEqualThan=" + UPDATED_TA_TARIKH);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByTaTarikhIsLessThanSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where taTarikh less than or equals to DEFAULT_TA_TARIKH
        defaultMoredEstefadeSalesShouldNotBeFound("taTarikh.lessThan=" + DEFAULT_TA_TARIKH);

        // Get all the moredEstefadeSalesList where taTarikh less than or equals to UPDATED_TA_TARIKH
        defaultMoredEstefadeSalesShouldBeFound("taTarikh.lessThan=" + UPDATED_TA_TARIKH);
    }


    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where faal equals to DEFAULT_FAAL
        defaultMoredEstefadeSalesShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the moredEstefadeSalesList where faal equals to UPDATED_FAAL
        defaultMoredEstefadeSalesShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultMoredEstefadeSalesShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the moredEstefadeSalesList where faal equals to UPDATED_FAAL
        defaultMoredEstefadeSalesShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        // Get all the moredEstefadeSalesList where faal is not null
        defaultMoredEstefadeSalesShouldBeFound("faal.specified=true");

        // Get all the moredEstefadeSalesList where faal is null
        defaultMoredEstefadeSalesShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        moredEstefadeSales.setGrouhKhodro(grouhKhodro);
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the moredEstefadeSalesList where grouhKhodro equals to grouhKhodroId
        defaultMoredEstefadeSalesShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the moredEstefadeSalesList where grouhKhodro equals to grouhKhodroId + 1
        defaultMoredEstefadeSalesShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }


    @Test
    @Transactional
    public void getAllMoredEstefadeSalesBySherkatBimeIsEqualToSomething() throws Exception {
        // Initialize the database
        SherkatBime sherkatBime = SherkatBimeResourceIntTest.createEntity(em);
        em.persist(sherkatBime);
        em.flush();
        moredEstefadeSales.setSherkatBime(sherkatBime);
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);
        Long sherkatBimeId = sherkatBime.getId();

        // Get all the moredEstefadeSalesList where sherkatBime equals to sherkatBimeId
        defaultMoredEstefadeSalesShouldBeFound("sherkatBimeId.equals=" + sherkatBimeId);

        // Get all the moredEstefadeSalesList where sherkatBime equals to sherkatBimeId + 1
        defaultMoredEstefadeSalesShouldNotBeFound("sherkatBimeId.equals=" + (sherkatBimeId + 1));
    }


    @Test
    @Transactional
    public void getAllMoredEstefadeSalesByOnvanKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        OnvanKhodro onvanKhodro = OnvanKhodroResourceIntTest.createEntity(em);
        em.persist(onvanKhodro);
        em.flush();
        moredEstefadeSales.setOnvanKhodro(onvanKhodro);
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);
        Long onvanKhodroId = onvanKhodro.getId();

        // Get all the moredEstefadeSalesList where onvanKhodro equals to onvanKhodroId
        defaultMoredEstefadeSalesShouldBeFound("onvanKhodroId.equals=" + onvanKhodroId);

        // Get all the moredEstefadeSalesList where onvanKhodro equals to onvanKhodroId + 1
        defaultMoredEstefadeSalesShouldNotBeFound("onvanKhodroId.equals=" + (onvanKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMoredEstefadeSalesShouldBeFound(String filter) throws Exception {
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(moredEstefadeSales.getId().intValue())))
            .andExpect(jsonPath("$.[*].darsadEzafeNerkh").value(hasItem(DEFAULT_DARSAD_EZAFE_NERKH.doubleValue())))
            .andExpect(jsonPath("$.[*].azTarikh").value(hasItem(DEFAULT_AZ_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].taTarikh").value(hasItem(DEFAULT_TA_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMoredEstefadeSalesShouldNotBeFound(String filter) throws Exception {
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMoredEstefadeSales() throws Exception {
        // Get the moredEstefadeSales
        restMoredEstefadeSalesMockMvc.perform(get("/api/mored-estefade-sales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMoredEstefadeSales() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        int databaseSizeBeforeUpdate = moredEstefadeSalesRepository.findAll().size();

        // Update the moredEstefadeSales
        MoredEstefadeSales updatedMoredEstefadeSales = moredEstefadeSalesRepository.findById(moredEstefadeSales.getId()).get();
        // Disconnect from session so that the updates on updatedMoredEstefadeSales are not directly saved in db
        em.detach(updatedMoredEstefadeSales);
        updatedMoredEstefadeSales
            .darsadEzafeNerkh(UPDATED_DARSAD_EZAFE_NERKH)
            .azTarikh(UPDATED_AZ_TARIKH)
            .taTarikh(UPDATED_TA_TARIKH)
            .faal(UPDATED_FAAL);
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(updatedMoredEstefadeSales);

        restMoredEstefadeSalesMockMvc.perform(put("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isOk());

        // Validate the MoredEstefadeSales in the database
        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeUpdate);
        MoredEstefadeSales testMoredEstefadeSales = moredEstefadeSalesList.get(moredEstefadeSalesList.size() - 1);
        assertThat(testMoredEstefadeSales.getDarsadEzafeNerkh()).isEqualTo(UPDATED_DARSAD_EZAFE_NERKH);
        assertThat(testMoredEstefadeSales.getAzTarikh()).isEqualTo(UPDATED_AZ_TARIKH);
        assertThat(testMoredEstefadeSales.getTaTarikh()).isEqualTo(UPDATED_TA_TARIKH);
        assertThat(testMoredEstefadeSales.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingMoredEstefadeSales() throws Exception {
        int databaseSizeBeforeUpdate = moredEstefadeSalesRepository.findAll().size();

        // Create the MoredEstefadeSales
        MoredEstefadeSalesDTO moredEstefadeSalesDTO = moredEstefadeSalesMapper.toDto(moredEstefadeSales);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMoredEstefadeSalesMockMvc.perform(put("/api/mored-estefade-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moredEstefadeSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MoredEstefadeSales in the database
        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMoredEstefadeSales() throws Exception {
        // Initialize the database
        moredEstefadeSalesRepository.saveAndFlush(moredEstefadeSales);

        int databaseSizeBeforeDelete = moredEstefadeSalesRepository.findAll().size();

        // Delete the moredEstefadeSales
        restMoredEstefadeSalesMockMvc.perform(delete("/api/mored-estefade-sales/{id}", moredEstefadeSales.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MoredEstefadeSales> moredEstefadeSalesList = moredEstefadeSalesRepository.findAll();
        assertThat(moredEstefadeSalesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MoredEstefadeSales.class);
        MoredEstefadeSales moredEstefadeSales1 = new MoredEstefadeSales();
        moredEstefadeSales1.setId(1L);
        MoredEstefadeSales moredEstefadeSales2 = new MoredEstefadeSales();
        moredEstefadeSales2.setId(moredEstefadeSales1.getId());
        assertThat(moredEstefadeSales1).isEqualTo(moredEstefadeSales2);
        moredEstefadeSales2.setId(2L);
        assertThat(moredEstefadeSales1).isNotEqualTo(moredEstefadeSales2);
        moredEstefadeSales1.setId(null);
        assertThat(moredEstefadeSales1).isNotEqualTo(moredEstefadeSales2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MoredEstefadeSalesDTO.class);
        MoredEstefadeSalesDTO moredEstefadeSalesDTO1 = new MoredEstefadeSalesDTO();
        moredEstefadeSalesDTO1.setId(1L);
        MoredEstefadeSalesDTO moredEstefadeSalesDTO2 = new MoredEstefadeSalesDTO();
        assertThat(moredEstefadeSalesDTO1).isNotEqualTo(moredEstefadeSalesDTO2);
        moredEstefadeSalesDTO2.setId(moredEstefadeSalesDTO1.getId());
        assertThat(moredEstefadeSalesDTO1).isEqualTo(moredEstefadeSalesDTO2);
        moredEstefadeSalesDTO2.setId(2L);
        assertThat(moredEstefadeSalesDTO1).isNotEqualTo(moredEstefadeSalesDTO2);
        moredEstefadeSalesDTO1.setId(null);
        assertThat(moredEstefadeSalesDTO1).isNotEqualTo(moredEstefadeSalesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(moredEstefadeSalesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(moredEstefadeSalesMapper.fromId(null)).isNull();
    }
}
