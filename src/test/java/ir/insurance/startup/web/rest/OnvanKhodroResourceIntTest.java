package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.OnvanKhodro;
import ir.insurance.startup.repository.OnvanKhodroRepository;
import ir.insurance.startup.service.OnvanKhodroService;
import ir.insurance.startup.service.dto.OnvanKhodroDTO;
import ir.insurance.startup.service.mapper.OnvanKhodroMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.OnvanKhodroCriteria;
import ir.insurance.startup.service.OnvanKhodroQueryService;

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
 * Test class for the OnvanKhodroResource REST controller.
 *
 * @see OnvanKhodroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class OnvanKhodroResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AZ_TARIKH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AZ_TARIKH = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TA_TARIKH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TA_TARIKH = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private OnvanKhodroRepository onvanKhodroRepository;

    @Autowired
    private OnvanKhodroMapper onvanKhodroMapper;

    @Autowired
    private OnvanKhodroService onvanKhodroService;

    @Autowired
    private OnvanKhodroQueryService onvanKhodroQueryService;

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

    private MockMvc restOnvanKhodroMockMvc;

    private OnvanKhodro onvanKhodro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OnvanKhodroResource onvanKhodroResource = new OnvanKhodroResource(onvanKhodroService, onvanKhodroQueryService);
        this.restOnvanKhodroMockMvc = MockMvcBuilders.standaloneSetup(onvanKhodroResource)
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
    public static OnvanKhodro createEntity(EntityManager em) {
        OnvanKhodro onvanKhodro = new OnvanKhodro()
            .name(DEFAULT_NAME)
            .sharh(DEFAULT_SHARH)
            .azTarikh(DEFAULT_AZ_TARIKH)
            .taTarikh(DEFAULT_TA_TARIKH)
            .faal(DEFAULT_FAAL);
        return onvanKhodro;
    }

    @Before
    public void initTest() {
        onvanKhodro = createEntity(em);
    }

    @Test
    @Transactional
    public void createOnvanKhodro() throws Exception {
        int databaseSizeBeforeCreate = onvanKhodroRepository.findAll().size();

        // Create the OnvanKhodro
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);
        restOnvanKhodroMockMvc.perform(post("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isCreated());

        // Validate the OnvanKhodro in the database
        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeCreate + 1);
        OnvanKhodro testOnvanKhodro = onvanKhodroList.get(onvanKhodroList.size() - 1);
        assertThat(testOnvanKhodro.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOnvanKhodro.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testOnvanKhodro.getAzTarikh()).isEqualTo(DEFAULT_AZ_TARIKH);
        assertThat(testOnvanKhodro.getTaTarikh()).isEqualTo(DEFAULT_TA_TARIKH);
        assertThat(testOnvanKhodro.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createOnvanKhodroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = onvanKhodroRepository.findAll().size();

        // Create the OnvanKhodro with an existing ID
        onvanKhodro.setId(1L);
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOnvanKhodroMockMvc.perform(post("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OnvanKhodro in the database
        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = onvanKhodroRepository.findAll().size();
        // set the field null
        onvanKhodro.setName(null);

        // Create the OnvanKhodro, which fails.
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);

        restOnvanKhodroMockMvc.perform(post("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAzTarikhIsRequired() throws Exception {
        int databaseSizeBeforeTest = onvanKhodroRepository.findAll().size();
        // set the field null
        onvanKhodro.setAzTarikh(null);

        // Create the OnvanKhodro, which fails.
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);

        restOnvanKhodroMockMvc.perform(post("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTaTarikhIsRequired() throws Exception {
        int databaseSizeBeforeTest = onvanKhodroRepository.findAll().size();
        // set the field null
        onvanKhodro.setTaTarikh(null);

        // Create the OnvanKhodro, which fails.
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);

        restOnvanKhodroMockMvc.perform(post("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = onvanKhodroRepository.findAll().size();
        // set the field null
        onvanKhodro.setFaal(null);

        // Create the OnvanKhodro, which fails.
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);

        restOnvanKhodroMockMvc.perform(post("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodros() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onvanKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].azTarikh").value(hasItem(DEFAULT_AZ_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].taTarikh").value(hasItem(DEFAULT_TA_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getOnvanKhodro() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get the onvanKhodro
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros/{id}", onvanKhodro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(onvanKhodro.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.azTarikh").value(DEFAULT_AZ_TARIKH.toString()))
            .andExpect(jsonPath("$.taTarikh").value(DEFAULT_TA_TARIKH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where name equals to DEFAULT_NAME
        defaultOnvanKhodroShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the onvanKhodroList where name equals to UPDATED_NAME
        defaultOnvanKhodroShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByNameIsInShouldWork() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where name in DEFAULT_NAME or UPDATED_NAME
        defaultOnvanKhodroShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the onvanKhodroList where name equals to UPDATED_NAME
        defaultOnvanKhodroShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where name is not null
        defaultOnvanKhodroShouldBeFound("name.specified=true");

        // Get all the onvanKhodroList where name is null
        defaultOnvanKhodroShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosBySharhIsEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where sharh equals to DEFAULT_SHARH
        defaultOnvanKhodroShouldBeFound("sharh.equals=" + DEFAULT_SHARH);

        // Get all the onvanKhodroList where sharh equals to UPDATED_SHARH
        defaultOnvanKhodroShouldNotBeFound("sharh.equals=" + UPDATED_SHARH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosBySharhIsInShouldWork() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where sharh in DEFAULT_SHARH or UPDATED_SHARH
        defaultOnvanKhodroShouldBeFound("sharh.in=" + DEFAULT_SHARH + "," + UPDATED_SHARH);

        // Get all the onvanKhodroList where sharh equals to UPDATED_SHARH
        defaultOnvanKhodroShouldNotBeFound("sharh.in=" + UPDATED_SHARH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosBySharhIsNullOrNotNull() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where sharh is not null
        defaultOnvanKhodroShouldBeFound("sharh.specified=true");

        // Get all the onvanKhodroList where sharh is null
        defaultOnvanKhodroShouldNotBeFound("sharh.specified=false");
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByAzTarikhIsEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where azTarikh equals to DEFAULT_AZ_TARIKH
        defaultOnvanKhodroShouldBeFound("azTarikh.equals=" + DEFAULT_AZ_TARIKH);

        // Get all the onvanKhodroList where azTarikh equals to UPDATED_AZ_TARIKH
        defaultOnvanKhodroShouldNotBeFound("azTarikh.equals=" + UPDATED_AZ_TARIKH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByAzTarikhIsInShouldWork() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where azTarikh in DEFAULT_AZ_TARIKH or UPDATED_AZ_TARIKH
        defaultOnvanKhodroShouldBeFound("azTarikh.in=" + DEFAULT_AZ_TARIKH + "," + UPDATED_AZ_TARIKH);

        // Get all the onvanKhodroList where azTarikh equals to UPDATED_AZ_TARIKH
        defaultOnvanKhodroShouldNotBeFound("azTarikh.in=" + UPDATED_AZ_TARIKH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByAzTarikhIsNullOrNotNull() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where azTarikh is not null
        defaultOnvanKhodroShouldBeFound("azTarikh.specified=true");

        // Get all the onvanKhodroList where azTarikh is null
        defaultOnvanKhodroShouldNotBeFound("azTarikh.specified=false");
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByAzTarikhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where azTarikh greater than or equals to DEFAULT_AZ_TARIKH
        defaultOnvanKhodroShouldBeFound("azTarikh.greaterOrEqualThan=" + DEFAULT_AZ_TARIKH);

        // Get all the onvanKhodroList where azTarikh greater than or equals to UPDATED_AZ_TARIKH
        defaultOnvanKhodroShouldNotBeFound("azTarikh.greaterOrEqualThan=" + UPDATED_AZ_TARIKH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByAzTarikhIsLessThanSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where azTarikh less than or equals to DEFAULT_AZ_TARIKH
        defaultOnvanKhodroShouldNotBeFound("azTarikh.lessThan=" + DEFAULT_AZ_TARIKH);

        // Get all the onvanKhodroList where azTarikh less than or equals to UPDATED_AZ_TARIKH
        defaultOnvanKhodroShouldBeFound("azTarikh.lessThan=" + UPDATED_AZ_TARIKH);
    }


    @Test
    @Transactional
    public void getAllOnvanKhodrosByTaTarikhIsEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where taTarikh equals to DEFAULT_TA_TARIKH
        defaultOnvanKhodroShouldBeFound("taTarikh.equals=" + DEFAULT_TA_TARIKH);

        // Get all the onvanKhodroList where taTarikh equals to UPDATED_TA_TARIKH
        defaultOnvanKhodroShouldNotBeFound("taTarikh.equals=" + UPDATED_TA_TARIKH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByTaTarikhIsInShouldWork() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where taTarikh in DEFAULT_TA_TARIKH or UPDATED_TA_TARIKH
        defaultOnvanKhodroShouldBeFound("taTarikh.in=" + DEFAULT_TA_TARIKH + "," + UPDATED_TA_TARIKH);

        // Get all the onvanKhodroList where taTarikh equals to UPDATED_TA_TARIKH
        defaultOnvanKhodroShouldNotBeFound("taTarikh.in=" + UPDATED_TA_TARIKH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByTaTarikhIsNullOrNotNull() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where taTarikh is not null
        defaultOnvanKhodroShouldBeFound("taTarikh.specified=true");

        // Get all the onvanKhodroList where taTarikh is null
        defaultOnvanKhodroShouldNotBeFound("taTarikh.specified=false");
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByTaTarikhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where taTarikh greater than or equals to DEFAULT_TA_TARIKH
        defaultOnvanKhodroShouldBeFound("taTarikh.greaterOrEqualThan=" + DEFAULT_TA_TARIKH);

        // Get all the onvanKhodroList where taTarikh greater than or equals to UPDATED_TA_TARIKH
        defaultOnvanKhodroShouldNotBeFound("taTarikh.greaterOrEqualThan=" + UPDATED_TA_TARIKH);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByTaTarikhIsLessThanSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where taTarikh less than or equals to DEFAULT_TA_TARIKH
        defaultOnvanKhodroShouldNotBeFound("taTarikh.lessThan=" + DEFAULT_TA_TARIKH);

        // Get all the onvanKhodroList where taTarikh less than or equals to UPDATED_TA_TARIKH
        defaultOnvanKhodroShouldBeFound("taTarikh.lessThan=" + UPDATED_TA_TARIKH);
    }


    @Test
    @Transactional
    public void getAllOnvanKhodrosByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where faal equals to DEFAULT_FAAL
        defaultOnvanKhodroShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the onvanKhodroList where faal equals to UPDATED_FAAL
        defaultOnvanKhodroShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultOnvanKhodroShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the onvanKhodroList where faal equals to UPDATED_FAAL
        defaultOnvanKhodroShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllOnvanKhodrosByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        // Get all the onvanKhodroList where faal is not null
        defaultOnvanKhodroShouldBeFound("faal.specified=true");

        // Get all the onvanKhodroList where faal is null
        defaultOnvanKhodroShouldNotBeFound("faal.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultOnvanKhodroShouldBeFound(String filter) throws Exception {
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onvanKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH)))
            .andExpect(jsonPath("$.[*].azTarikh").value(hasItem(DEFAULT_AZ_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].taTarikh").value(hasItem(DEFAULT_TA_TARIKH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultOnvanKhodroShouldNotBeFound(String filter) throws Exception {
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOnvanKhodro() throws Exception {
        // Get the onvanKhodro
        restOnvanKhodroMockMvc.perform(get("/api/onvan-khodros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnvanKhodro() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        int databaseSizeBeforeUpdate = onvanKhodroRepository.findAll().size();

        // Update the onvanKhodro
        OnvanKhodro updatedOnvanKhodro = onvanKhodroRepository.findById(onvanKhodro.getId()).get();
        // Disconnect from session so that the updates on updatedOnvanKhodro are not directly saved in db
        em.detach(updatedOnvanKhodro);
        updatedOnvanKhodro
            .name(UPDATED_NAME)
            .sharh(UPDATED_SHARH)
            .azTarikh(UPDATED_AZ_TARIKH)
            .taTarikh(UPDATED_TA_TARIKH)
            .faal(UPDATED_FAAL);
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(updatedOnvanKhodro);

        restOnvanKhodroMockMvc.perform(put("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isOk());

        // Validate the OnvanKhodro in the database
        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeUpdate);
        OnvanKhodro testOnvanKhodro = onvanKhodroList.get(onvanKhodroList.size() - 1);
        assertThat(testOnvanKhodro.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOnvanKhodro.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testOnvanKhodro.getAzTarikh()).isEqualTo(UPDATED_AZ_TARIKH);
        assertThat(testOnvanKhodro.getTaTarikh()).isEqualTo(UPDATED_TA_TARIKH);
        assertThat(testOnvanKhodro.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingOnvanKhodro() throws Exception {
        int databaseSizeBeforeUpdate = onvanKhodroRepository.findAll().size();

        // Create the OnvanKhodro
        OnvanKhodroDTO onvanKhodroDTO = onvanKhodroMapper.toDto(onvanKhodro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOnvanKhodroMockMvc.perform(put("/api/onvan-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(onvanKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OnvanKhodro in the database
        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOnvanKhodro() throws Exception {
        // Initialize the database
        onvanKhodroRepository.saveAndFlush(onvanKhodro);

        int databaseSizeBeforeDelete = onvanKhodroRepository.findAll().size();

        // Delete the onvanKhodro
        restOnvanKhodroMockMvc.perform(delete("/api/onvan-khodros/{id}", onvanKhodro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OnvanKhodro> onvanKhodroList = onvanKhodroRepository.findAll();
        assertThat(onvanKhodroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnvanKhodro.class);
        OnvanKhodro onvanKhodro1 = new OnvanKhodro();
        onvanKhodro1.setId(1L);
        OnvanKhodro onvanKhodro2 = new OnvanKhodro();
        onvanKhodro2.setId(onvanKhodro1.getId());
        assertThat(onvanKhodro1).isEqualTo(onvanKhodro2);
        onvanKhodro2.setId(2L);
        assertThat(onvanKhodro1).isNotEqualTo(onvanKhodro2);
        onvanKhodro1.setId(null);
        assertThat(onvanKhodro1).isNotEqualTo(onvanKhodro2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OnvanKhodroDTO.class);
        OnvanKhodroDTO onvanKhodroDTO1 = new OnvanKhodroDTO();
        onvanKhodroDTO1.setId(1L);
        OnvanKhodroDTO onvanKhodroDTO2 = new OnvanKhodroDTO();
        assertThat(onvanKhodroDTO1).isNotEqualTo(onvanKhodroDTO2);
        onvanKhodroDTO2.setId(onvanKhodroDTO1.getId());
        assertThat(onvanKhodroDTO1).isEqualTo(onvanKhodroDTO2);
        onvanKhodroDTO2.setId(2L);
        assertThat(onvanKhodroDTO1).isNotEqualTo(onvanKhodroDTO2);
        onvanKhodroDTO1.setId(null);
        assertThat(onvanKhodroDTO1).isNotEqualTo(onvanKhodroDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(onvanKhodroMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(onvanKhodroMapper.fromId(null)).isNull();
    }
}
