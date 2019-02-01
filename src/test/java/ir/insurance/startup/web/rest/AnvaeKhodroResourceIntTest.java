package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.AnvaeKhodro;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.AnvaeKhodroRepository;
import ir.insurance.startup.service.AnvaeKhodroService;
import ir.insurance.startup.service.dto.AnvaeKhodroDTO;
import ir.insurance.startup.service.mapper.AnvaeKhodroMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.AnvaeKhodroCriteria;
import ir.insurance.startup.service.AnvaeKhodroQueryService;

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
 * Test class for the AnvaeKhodroResource REST controller.
 *
 * @see AnvaeKhodroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class AnvaeKhodroResourceIntTest {

    private static final String DEFAULT_GROUH_VASILE = "AAAAAAAAAA";
    private static final String UPDATED_GROUH_VASILE = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_VASILE = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_VASILE = "BBBBBBBBBB";

    private static final String DEFAULT_ONVAN = "AAAAAAAAAA";
    private static final String UPDATED_ONVAN = "BBBBBBBBBB";

    private static final String DEFAULT_TONAZH = "AAAAAAAAAA";
    private static final String UPDATED_TONAZH = "BBBBBBBBBB";

    private static final String DEFAULT_TEDAD_SARNESHIN = "AAAAAAAAAA";
    private static final String UPDATED_TEDAD_SARNESHIN = "BBBBBBBBBB";

    private static final String DEFAULT_TEDAD_SILANDRE = "AAAAAAAAAA";
    private static final String UPDATED_TEDAD_SILANDRE = "BBBBBBBBBB";

    private static final String DEFAULT_DASTE_BANDI = "AAAAAAAAAA";
    private static final String UPDATED_DASTE_BANDI = "BBBBBBBBBB";

    private static final String DEFAULT_SAVARI_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SAVARI_TYPE = "BBBBBBBBBB";

    @Autowired
    private AnvaeKhodroRepository anvaeKhodroRepository;

    @Autowired
    private AnvaeKhodroMapper anvaeKhodroMapper;

    @Autowired
    private AnvaeKhodroService anvaeKhodroService;

    @Autowired
    private AnvaeKhodroQueryService anvaeKhodroQueryService;

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

    private MockMvc restAnvaeKhodroMockMvc;

    private AnvaeKhodro anvaeKhodro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnvaeKhodroResource anvaeKhodroResource = new AnvaeKhodroResource(anvaeKhodroService, anvaeKhodroQueryService);
        this.restAnvaeKhodroMockMvc = MockMvcBuilders.standaloneSetup(anvaeKhodroResource)
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
    public static AnvaeKhodro createEntity(EntityManager em) {
        AnvaeKhodro anvaeKhodro = new AnvaeKhodro()
            .grouhVasile(DEFAULT_GROUH_VASILE)
            .systemVasile(DEFAULT_SYSTEM_VASILE)
            .onvan(DEFAULT_ONVAN)
            .tonazh(DEFAULT_TONAZH)
            .tedadSarneshin(DEFAULT_TEDAD_SARNESHIN)
            .tedadSilandre(DEFAULT_TEDAD_SILANDRE)
            .dasteBandi(DEFAULT_DASTE_BANDI)
            .savariType(DEFAULT_SAVARI_TYPE);
        return anvaeKhodro;
    }

    @Before
    public void initTest() {
        anvaeKhodro = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnvaeKhodro() throws Exception {
        int databaseSizeBeforeCreate = anvaeKhodroRepository.findAll().size();

        // Create the AnvaeKhodro
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);
        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isCreated());

        // Validate the AnvaeKhodro in the database
        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeCreate + 1);
        AnvaeKhodro testAnvaeKhodro = anvaeKhodroList.get(anvaeKhodroList.size() - 1);
        assertThat(testAnvaeKhodro.getGrouhVasile()).isEqualTo(DEFAULT_GROUH_VASILE);
        assertThat(testAnvaeKhodro.getSystemVasile()).isEqualTo(DEFAULT_SYSTEM_VASILE);
        assertThat(testAnvaeKhodro.getOnvan()).isEqualTo(DEFAULT_ONVAN);
        assertThat(testAnvaeKhodro.getTonazh()).isEqualTo(DEFAULT_TONAZH);
        assertThat(testAnvaeKhodro.getTedadSarneshin()).isEqualTo(DEFAULT_TEDAD_SARNESHIN);
        assertThat(testAnvaeKhodro.getTedadSilandre()).isEqualTo(DEFAULT_TEDAD_SILANDRE);
        assertThat(testAnvaeKhodro.getDasteBandi()).isEqualTo(DEFAULT_DASTE_BANDI);
        assertThat(testAnvaeKhodro.getSavariType()).isEqualTo(DEFAULT_SAVARI_TYPE);
    }

    @Test
    @Transactional
    public void createAnvaeKhodroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anvaeKhodroRepository.findAll().size();

        // Create the AnvaeKhodro with an existing ID
        anvaeKhodro.setId(1L);
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnvaeKhodro in the database
        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGrouhVasileIsRequired() throws Exception {
        int databaseSizeBeforeTest = anvaeKhodroRepository.findAll().size();
        // set the field null
        anvaeKhodro.setGrouhVasile(null);

        // Create the AnvaeKhodro, which fails.
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSystemVasileIsRequired() throws Exception {
        int databaseSizeBeforeTest = anvaeKhodroRepository.findAll().size();
        // set the field null
        anvaeKhodro.setSystemVasile(null);

        // Create the AnvaeKhodro, which fails.
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOnvanIsRequired() throws Exception {
        int databaseSizeBeforeTest = anvaeKhodroRepository.findAll().size();
        // set the field null
        anvaeKhodro.setOnvan(null);

        // Create the AnvaeKhodro, which fails.
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTedadSarneshinIsRequired() throws Exception {
        int databaseSizeBeforeTest = anvaeKhodroRepository.findAll().size();
        // set the field null
        anvaeKhodro.setTedadSarneshin(null);

        // Create the AnvaeKhodro, which fails.
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTedadSilandreIsRequired() throws Exception {
        int databaseSizeBeforeTest = anvaeKhodroRepository.findAll().size();
        // set the field null
        anvaeKhodro.setTedadSilandre(null);

        // Create the AnvaeKhodro, which fails.
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSavariTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = anvaeKhodroRepository.findAll().size();
        // set the field null
        anvaeKhodro.setSavariType(null);

        // Create the AnvaeKhodro, which fails.
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        restAnvaeKhodroMockMvc.perform(post("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodros() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anvaeKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].grouhVasile").value(hasItem(DEFAULT_GROUH_VASILE.toString())))
            .andExpect(jsonPath("$.[*].systemVasile").value(hasItem(DEFAULT_SYSTEM_VASILE.toString())))
            .andExpect(jsonPath("$.[*].onvan").value(hasItem(DEFAULT_ONVAN.toString())))
            .andExpect(jsonPath("$.[*].tonazh").value(hasItem(DEFAULT_TONAZH.toString())))
            .andExpect(jsonPath("$.[*].tedadSarneshin").value(hasItem(DEFAULT_TEDAD_SARNESHIN.toString())))
            .andExpect(jsonPath("$.[*].tedadSilandre").value(hasItem(DEFAULT_TEDAD_SILANDRE.toString())))
            .andExpect(jsonPath("$.[*].dasteBandi").value(hasItem(DEFAULT_DASTE_BANDI.toString())))
            .andExpect(jsonPath("$.[*].savariType").value(hasItem(DEFAULT_SAVARI_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getAnvaeKhodro() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get the anvaeKhodro
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros/{id}", anvaeKhodro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(anvaeKhodro.getId().intValue()))
            .andExpect(jsonPath("$.grouhVasile").value(DEFAULT_GROUH_VASILE.toString()))
            .andExpect(jsonPath("$.systemVasile").value(DEFAULT_SYSTEM_VASILE.toString()))
            .andExpect(jsonPath("$.onvan").value(DEFAULT_ONVAN.toString()))
            .andExpect(jsonPath("$.tonazh").value(DEFAULT_TONAZH.toString()))
            .andExpect(jsonPath("$.tedadSarneshin").value(DEFAULT_TEDAD_SARNESHIN.toString()))
            .andExpect(jsonPath("$.tedadSilandre").value(DEFAULT_TEDAD_SILANDRE.toString()))
            .andExpect(jsonPath("$.dasteBandi").value(DEFAULT_DASTE_BANDI.toString()))
            .andExpect(jsonPath("$.savariType").value(DEFAULT_SAVARI_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByGrouhVasileIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where grouhVasile equals to DEFAULT_GROUH_VASILE
        defaultAnvaeKhodroShouldBeFound("grouhVasile.equals=" + DEFAULT_GROUH_VASILE);

        // Get all the anvaeKhodroList where grouhVasile equals to UPDATED_GROUH_VASILE
        defaultAnvaeKhodroShouldNotBeFound("grouhVasile.equals=" + UPDATED_GROUH_VASILE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByGrouhVasileIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where grouhVasile in DEFAULT_GROUH_VASILE or UPDATED_GROUH_VASILE
        defaultAnvaeKhodroShouldBeFound("grouhVasile.in=" + DEFAULT_GROUH_VASILE + "," + UPDATED_GROUH_VASILE);

        // Get all the anvaeKhodroList where grouhVasile equals to UPDATED_GROUH_VASILE
        defaultAnvaeKhodroShouldNotBeFound("grouhVasile.in=" + UPDATED_GROUH_VASILE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByGrouhVasileIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where grouhVasile is not null
        defaultAnvaeKhodroShouldBeFound("grouhVasile.specified=true");

        // Get all the anvaeKhodroList where grouhVasile is null
        defaultAnvaeKhodroShouldNotBeFound("grouhVasile.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosBySystemVasileIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where systemVasile equals to DEFAULT_SYSTEM_VASILE
        defaultAnvaeKhodroShouldBeFound("systemVasile.equals=" + DEFAULT_SYSTEM_VASILE);

        // Get all the anvaeKhodroList where systemVasile equals to UPDATED_SYSTEM_VASILE
        defaultAnvaeKhodroShouldNotBeFound("systemVasile.equals=" + UPDATED_SYSTEM_VASILE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosBySystemVasileIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where systemVasile in DEFAULT_SYSTEM_VASILE or UPDATED_SYSTEM_VASILE
        defaultAnvaeKhodroShouldBeFound("systemVasile.in=" + DEFAULT_SYSTEM_VASILE + "," + UPDATED_SYSTEM_VASILE);

        // Get all the anvaeKhodroList where systemVasile equals to UPDATED_SYSTEM_VASILE
        defaultAnvaeKhodroShouldNotBeFound("systemVasile.in=" + UPDATED_SYSTEM_VASILE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosBySystemVasileIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where systemVasile is not null
        defaultAnvaeKhodroShouldBeFound("systemVasile.specified=true");

        // Get all the anvaeKhodroList where systemVasile is null
        defaultAnvaeKhodroShouldNotBeFound("systemVasile.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByOnvanIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where onvan equals to DEFAULT_ONVAN
        defaultAnvaeKhodroShouldBeFound("onvan.equals=" + DEFAULT_ONVAN);

        // Get all the anvaeKhodroList where onvan equals to UPDATED_ONVAN
        defaultAnvaeKhodroShouldNotBeFound("onvan.equals=" + UPDATED_ONVAN);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByOnvanIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where onvan in DEFAULT_ONVAN or UPDATED_ONVAN
        defaultAnvaeKhodroShouldBeFound("onvan.in=" + DEFAULT_ONVAN + "," + UPDATED_ONVAN);

        // Get all the anvaeKhodroList where onvan equals to UPDATED_ONVAN
        defaultAnvaeKhodroShouldNotBeFound("onvan.in=" + UPDATED_ONVAN);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByOnvanIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where onvan is not null
        defaultAnvaeKhodroShouldBeFound("onvan.specified=true");

        // Get all the anvaeKhodroList where onvan is null
        defaultAnvaeKhodroShouldNotBeFound("onvan.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTonazhIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tonazh equals to DEFAULT_TONAZH
        defaultAnvaeKhodroShouldBeFound("tonazh.equals=" + DEFAULT_TONAZH);

        // Get all the anvaeKhodroList where tonazh equals to UPDATED_TONAZH
        defaultAnvaeKhodroShouldNotBeFound("tonazh.equals=" + UPDATED_TONAZH);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTonazhIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tonazh in DEFAULT_TONAZH or UPDATED_TONAZH
        defaultAnvaeKhodroShouldBeFound("tonazh.in=" + DEFAULT_TONAZH + "," + UPDATED_TONAZH);

        // Get all the anvaeKhodroList where tonazh equals to UPDATED_TONAZH
        defaultAnvaeKhodroShouldNotBeFound("tonazh.in=" + UPDATED_TONAZH);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTonazhIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tonazh is not null
        defaultAnvaeKhodroShouldBeFound("tonazh.specified=true");

        // Get all the anvaeKhodroList where tonazh is null
        defaultAnvaeKhodroShouldNotBeFound("tonazh.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTedadSarneshinIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tedadSarneshin equals to DEFAULT_TEDAD_SARNESHIN
        defaultAnvaeKhodroShouldBeFound("tedadSarneshin.equals=" + DEFAULT_TEDAD_SARNESHIN);

        // Get all the anvaeKhodroList where tedadSarneshin equals to UPDATED_TEDAD_SARNESHIN
        defaultAnvaeKhodroShouldNotBeFound("tedadSarneshin.equals=" + UPDATED_TEDAD_SARNESHIN);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTedadSarneshinIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tedadSarneshin in DEFAULT_TEDAD_SARNESHIN or UPDATED_TEDAD_SARNESHIN
        defaultAnvaeKhodroShouldBeFound("tedadSarneshin.in=" + DEFAULT_TEDAD_SARNESHIN + "," + UPDATED_TEDAD_SARNESHIN);

        // Get all the anvaeKhodroList where tedadSarneshin equals to UPDATED_TEDAD_SARNESHIN
        defaultAnvaeKhodroShouldNotBeFound("tedadSarneshin.in=" + UPDATED_TEDAD_SARNESHIN);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTedadSarneshinIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tedadSarneshin is not null
        defaultAnvaeKhodroShouldBeFound("tedadSarneshin.specified=true");

        // Get all the anvaeKhodroList where tedadSarneshin is null
        defaultAnvaeKhodroShouldNotBeFound("tedadSarneshin.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTedadSilandreIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tedadSilandre equals to DEFAULT_TEDAD_SILANDRE
        defaultAnvaeKhodroShouldBeFound("tedadSilandre.equals=" + DEFAULT_TEDAD_SILANDRE);

        // Get all the anvaeKhodroList where tedadSilandre equals to UPDATED_TEDAD_SILANDRE
        defaultAnvaeKhodroShouldNotBeFound("tedadSilandre.equals=" + UPDATED_TEDAD_SILANDRE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTedadSilandreIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tedadSilandre in DEFAULT_TEDAD_SILANDRE or UPDATED_TEDAD_SILANDRE
        defaultAnvaeKhodroShouldBeFound("tedadSilandre.in=" + DEFAULT_TEDAD_SILANDRE + "," + UPDATED_TEDAD_SILANDRE);

        // Get all the anvaeKhodroList where tedadSilandre equals to UPDATED_TEDAD_SILANDRE
        defaultAnvaeKhodroShouldNotBeFound("tedadSilandre.in=" + UPDATED_TEDAD_SILANDRE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByTedadSilandreIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where tedadSilandre is not null
        defaultAnvaeKhodroShouldBeFound("tedadSilandre.specified=true");

        // Get all the anvaeKhodroList where tedadSilandre is null
        defaultAnvaeKhodroShouldNotBeFound("tedadSilandre.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByDasteBandiIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where dasteBandi equals to DEFAULT_DASTE_BANDI
        defaultAnvaeKhodroShouldBeFound("dasteBandi.equals=" + DEFAULT_DASTE_BANDI);

        // Get all the anvaeKhodroList where dasteBandi equals to UPDATED_DASTE_BANDI
        defaultAnvaeKhodroShouldNotBeFound("dasteBandi.equals=" + UPDATED_DASTE_BANDI);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByDasteBandiIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where dasteBandi in DEFAULT_DASTE_BANDI or UPDATED_DASTE_BANDI
        defaultAnvaeKhodroShouldBeFound("dasteBandi.in=" + DEFAULT_DASTE_BANDI + "," + UPDATED_DASTE_BANDI);

        // Get all the anvaeKhodroList where dasteBandi equals to UPDATED_DASTE_BANDI
        defaultAnvaeKhodroShouldNotBeFound("dasteBandi.in=" + UPDATED_DASTE_BANDI);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByDasteBandiIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where dasteBandi is not null
        defaultAnvaeKhodroShouldBeFound("dasteBandi.specified=true");

        // Get all the anvaeKhodroList where dasteBandi is null
        defaultAnvaeKhodroShouldNotBeFound("dasteBandi.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosBySavariTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where savariType equals to DEFAULT_SAVARI_TYPE
        defaultAnvaeKhodroShouldBeFound("savariType.equals=" + DEFAULT_SAVARI_TYPE);

        // Get all the anvaeKhodroList where savariType equals to UPDATED_SAVARI_TYPE
        defaultAnvaeKhodroShouldNotBeFound("savariType.equals=" + UPDATED_SAVARI_TYPE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosBySavariTypeIsInShouldWork() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where savariType in DEFAULT_SAVARI_TYPE or UPDATED_SAVARI_TYPE
        defaultAnvaeKhodroShouldBeFound("savariType.in=" + DEFAULT_SAVARI_TYPE + "," + UPDATED_SAVARI_TYPE);

        // Get all the anvaeKhodroList where savariType equals to UPDATED_SAVARI_TYPE
        defaultAnvaeKhodroShouldNotBeFound("savariType.in=" + UPDATED_SAVARI_TYPE);
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosBySavariTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        // Get all the anvaeKhodroList where savariType is not null
        defaultAnvaeKhodroShouldBeFound("savariType.specified=true");

        // Get all the anvaeKhodroList where savariType is null
        defaultAnvaeKhodroShouldNotBeFound("savariType.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnvaeKhodrosByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        anvaeKhodro.setGrouhKhodro(grouhKhodro);
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the anvaeKhodroList where grouhKhodro equals to grouhKhodroId
        defaultAnvaeKhodroShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the anvaeKhodroList where grouhKhodro equals to grouhKhodroId + 1
        defaultAnvaeKhodroShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAnvaeKhodroShouldBeFound(String filter) throws Exception {
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anvaeKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].grouhVasile").value(hasItem(DEFAULT_GROUH_VASILE.toString())))
            .andExpect(jsonPath("$.[*].systemVasile").value(hasItem(DEFAULT_SYSTEM_VASILE.toString())))
            .andExpect(jsonPath("$.[*].onvan").value(hasItem(DEFAULT_ONVAN.toString())))
            .andExpect(jsonPath("$.[*].tonazh").value(hasItem(DEFAULT_TONAZH.toString())))
            .andExpect(jsonPath("$.[*].tedadSarneshin").value(hasItem(DEFAULT_TEDAD_SARNESHIN.toString())))
            .andExpect(jsonPath("$.[*].tedadSilandre").value(hasItem(DEFAULT_TEDAD_SILANDRE.toString())))
            .andExpect(jsonPath("$.[*].dasteBandi").value(hasItem(DEFAULT_DASTE_BANDI.toString())))
            .andExpect(jsonPath("$.[*].savariType").value(hasItem(DEFAULT_SAVARI_TYPE.toString())));

        // Check, that the count call also returns 1
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAnvaeKhodroShouldNotBeFound(String filter) throws Exception {
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAnvaeKhodro() throws Exception {
        // Get the anvaeKhodro
        restAnvaeKhodroMockMvc.perform(get("/api/anvae-khodros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnvaeKhodro() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        int databaseSizeBeforeUpdate = anvaeKhodroRepository.findAll().size();

        // Update the anvaeKhodro
        AnvaeKhodro updatedAnvaeKhodro = anvaeKhodroRepository.findById(anvaeKhodro.getId()).get();
        // Disconnect from session so that the updates on updatedAnvaeKhodro are not directly saved in db
        em.detach(updatedAnvaeKhodro);
        updatedAnvaeKhodro
            .grouhVasile(UPDATED_GROUH_VASILE)
            .systemVasile(UPDATED_SYSTEM_VASILE)
            .onvan(UPDATED_ONVAN)
            .tonazh(UPDATED_TONAZH)
            .tedadSarneshin(UPDATED_TEDAD_SARNESHIN)
            .tedadSilandre(UPDATED_TEDAD_SILANDRE)
            .dasteBandi(UPDATED_DASTE_BANDI)
            .savariType(UPDATED_SAVARI_TYPE);
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(updatedAnvaeKhodro);

        restAnvaeKhodroMockMvc.perform(put("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isOk());

        // Validate the AnvaeKhodro in the database
        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeUpdate);
        AnvaeKhodro testAnvaeKhodro = anvaeKhodroList.get(anvaeKhodroList.size() - 1);
        assertThat(testAnvaeKhodro.getGrouhVasile()).isEqualTo(UPDATED_GROUH_VASILE);
        assertThat(testAnvaeKhodro.getSystemVasile()).isEqualTo(UPDATED_SYSTEM_VASILE);
        assertThat(testAnvaeKhodro.getOnvan()).isEqualTo(UPDATED_ONVAN);
        assertThat(testAnvaeKhodro.getTonazh()).isEqualTo(UPDATED_TONAZH);
        assertThat(testAnvaeKhodro.getTedadSarneshin()).isEqualTo(UPDATED_TEDAD_SARNESHIN);
        assertThat(testAnvaeKhodro.getTedadSilandre()).isEqualTo(UPDATED_TEDAD_SILANDRE);
        assertThat(testAnvaeKhodro.getDasteBandi()).isEqualTo(UPDATED_DASTE_BANDI);
        assertThat(testAnvaeKhodro.getSavariType()).isEqualTo(UPDATED_SAVARI_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAnvaeKhodro() throws Exception {
        int databaseSizeBeforeUpdate = anvaeKhodroRepository.findAll().size();

        // Create the AnvaeKhodro
        AnvaeKhodroDTO anvaeKhodroDTO = anvaeKhodroMapper.toDto(anvaeKhodro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnvaeKhodroMockMvc.perform(put("/api/anvae-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anvaeKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnvaeKhodro in the database
        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnvaeKhodro() throws Exception {
        // Initialize the database
        anvaeKhodroRepository.saveAndFlush(anvaeKhodro);

        int databaseSizeBeforeDelete = anvaeKhodroRepository.findAll().size();

        // Delete the anvaeKhodro
        restAnvaeKhodroMockMvc.perform(delete("/api/anvae-khodros/{id}", anvaeKhodro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AnvaeKhodro> anvaeKhodroList = anvaeKhodroRepository.findAll();
        assertThat(anvaeKhodroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnvaeKhodro.class);
        AnvaeKhodro anvaeKhodro1 = new AnvaeKhodro();
        anvaeKhodro1.setId(1L);
        AnvaeKhodro anvaeKhodro2 = new AnvaeKhodro();
        anvaeKhodro2.setId(anvaeKhodro1.getId());
        assertThat(anvaeKhodro1).isEqualTo(anvaeKhodro2);
        anvaeKhodro2.setId(2L);
        assertThat(anvaeKhodro1).isNotEqualTo(anvaeKhodro2);
        anvaeKhodro1.setId(null);
        assertThat(anvaeKhodro1).isNotEqualTo(anvaeKhodro2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnvaeKhodroDTO.class);
        AnvaeKhodroDTO anvaeKhodroDTO1 = new AnvaeKhodroDTO();
        anvaeKhodroDTO1.setId(1L);
        AnvaeKhodroDTO anvaeKhodroDTO2 = new AnvaeKhodroDTO();
        assertThat(anvaeKhodroDTO1).isNotEqualTo(anvaeKhodroDTO2);
        anvaeKhodroDTO2.setId(anvaeKhodroDTO1.getId());
        assertThat(anvaeKhodroDTO1).isEqualTo(anvaeKhodroDTO2);
        anvaeKhodroDTO2.setId(2L);
        assertThat(anvaeKhodroDTO1).isNotEqualTo(anvaeKhodroDTO2);
        anvaeKhodroDTO1.setId(null);
        assertThat(anvaeKhodroDTO1).isNotEqualTo(anvaeKhodroDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(anvaeKhodroMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(anvaeKhodroMapper.fromId(null)).isNull();
    }
}
