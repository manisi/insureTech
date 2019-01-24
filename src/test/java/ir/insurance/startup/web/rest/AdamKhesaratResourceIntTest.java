package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.AdamKhesarat;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.repository.AdamKhesaratRepository;
import ir.insurance.startup.service.AdamKhesaratService;
import ir.insurance.startup.service.dto.AdamKhesaratDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.AdamKhesaratCriteria;
import ir.insurance.startup.service.AdamKhesaratQueryService;

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
 * Test class for the AdamKhesaratResource REST controller.
 *
 * @see AdamKhesaratResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class AdamKhesaratResourceIntTest {

    private static final Float DEFAULT_SALES = 0F;
    private static final Float UPDATED_SALES = 1F;

    private static final Float DEFAULT_MAZAD = 0F;
    private static final Float UPDATED_MAZAD = 1F;

    private static final Float DEFAULT_SARNESHIN = 0F;
    private static final Float UPDATED_SARNESHIN = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private AdamKhesaratRepository adamKhesaratRepository;

    @Autowired
    private AdamKhesaratMapper adamKhesaratMapper;

    @Autowired
    private AdamKhesaratService adamKhesaratService;

    @Autowired
    private AdamKhesaratQueryService adamKhesaratQueryService;

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

    private MockMvc restAdamKhesaratMockMvc;

    private AdamKhesarat adamKhesarat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdamKhesaratResource adamKhesaratResource = new AdamKhesaratResource(adamKhesaratService, adamKhesaratQueryService);
        this.restAdamKhesaratMockMvc = MockMvcBuilders.standaloneSetup(adamKhesaratResource)
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
    public static AdamKhesarat createEntity(EntityManager em) {
        AdamKhesarat adamKhesarat = new AdamKhesarat()
            .sales(DEFAULT_SALES)
            .mazad(DEFAULT_MAZAD)
            .sarneshin(DEFAULT_SARNESHIN)
            .faal(DEFAULT_FAAL);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesarat.setSabeghe(sabeghe);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesarat.setNoeSabeghe(noeSabeghe);
        return adamKhesarat;
    }

    @Before
    public void initTest() {
        adamKhesarat = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdamKhesarat() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratRepository.findAll().size();

        // Create the AdamKhesarat
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);
        restAdamKhesaratMockMvc.perform(post("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isCreated());

        // Validate the AdamKhesarat in the database
        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeCreate + 1);
        AdamKhesarat testAdamKhesarat = adamKhesaratList.get(adamKhesaratList.size() - 1);
        assertThat(testAdamKhesarat.getSales()).isEqualTo(DEFAULT_SALES);
        assertThat(testAdamKhesarat.getMazad()).isEqualTo(DEFAULT_MAZAD);
        assertThat(testAdamKhesarat.getSarneshin()).isEqualTo(DEFAULT_SARNESHIN);
        assertThat(testAdamKhesarat.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createAdamKhesaratWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratRepository.findAll().size();

        // Create the AdamKhesarat with an existing ID
        adamKhesarat.setId(1L);
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdamKhesaratMockMvc.perform(post("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesarat in the database
        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSalesIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratRepository.findAll().size();
        // set the field null
        adamKhesarat.setSales(null);

        // Create the AdamKhesarat, which fails.
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);

        restAdamKhesaratMockMvc.perform(post("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMazadIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratRepository.findAll().size();
        // set the field null
        adamKhesarat.setMazad(null);

        // Create the AdamKhesarat, which fails.
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);

        restAdamKhesaratMockMvc.perform(post("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSarneshinIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratRepository.findAll().size();
        // set the field null
        adamKhesarat.setSarneshin(null);

        // Create the AdamKhesarat, which fails.
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);

        restAdamKhesaratMockMvc.perform(post("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratRepository.findAll().size();
        // set the field null
        adamKhesarat.setFaal(null);

        // Create the AdamKhesarat, which fails.
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);

        restAdamKhesaratMockMvc.perform(post("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdamKhesarats() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesarat.getId().intValue())))
            .andExpect(jsonPath("$.[*].sales").value(hasItem(DEFAULT_SALES.doubleValue())))
            .andExpect(jsonPath("$.[*].mazad").value(hasItem(DEFAULT_MAZAD.doubleValue())))
            .andExpect(jsonPath("$.[*].sarneshin").value(hasItem(DEFAULT_SARNESHIN.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdamKhesarat() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get the adamKhesarat
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats/{id}", adamKhesarat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adamKhesarat.getId().intValue()))
            .andExpect(jsonPath("$.sales").value(DEFAULT_SALES.doubleValue()))
            .andExpect(jsonPath("$.mazad").value(DEFAULT_MAZAD.doubleValue()))
            .andExpect(jsonPath("$.sarneshin").value(DEFAULT_SARNESHIN.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySalesIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where sales equals to DEFAULT_SALES
        defaultAdamKhesaratShouldBeFound("sales.equals=" + DEFAULT_SALES);

        // Get all the adamKhesaratList where sales equals to UPDATED_SALES
        defaultAdamKhesaratShouldNotBeFound("sales.equals=" + UPDATED_SALES);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySalesIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where sales in DEFAULT_SALES or UPDATED_SALES
        defaultAdamKhesaratShouldBeFound("sales.in=" + DEFAULT_SALES + "," + UPDATED_SALES);

        // Get all the adamKhesaratList where sales equals to UPDATED_SALES
        defaultAdamKhesaratShouldNotBeFound("sales.in=" + UPDATED_SALES);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySalesIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where sales is not null
        defaultAdamKhesaratShouldBeFound("sales.specified=true");

        // Get all the adamKhesaratList where sales is null
        defaultAdamKhesaratShouldNotBeFound("sales.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsByMazadIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where mazad equals to DEFAULT_MAZAD
        defaultAdamKhesaratShouldBeFound("mazad.equals=" + DEFAULT_MAZAD);

        // Get all the adamKhesaratList where mazad equals to UPDATED_MAZAD
        defaultAdamKhesaratShouldNotBeFound("mazad.equals=" + UPDATED_MAZAD);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsByMazadIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where mazad in DEFAULT_MAZAD or UPDATED_MAZAD
        defaultAdamKhesaratShouldBeFound("mazad.in=" + DEFAULT_MAZAD + "," + UPDATED_MAZAD);

        // Get all the adamKhesaratList where mazad equals to UPDATED_MAZAD
        defaultAdamKhesaratShouldNotBeFound("mazad.in=" + UPDATED_MAZAD);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsByMazadIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where mazad is not null
        defaultAdamKhesaratShouldBeFound("mazad.specified=true");

        // Get all the adamKhesaratList where mazad is null
        defaultAdamKhesaratShouldNotBeFound("mazad.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySarneshinIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where sarneshin equals to DEFAULT_SARNESHIN
        defaultAdamKhesaratShouldBeFound("sarneshin.equals=" + DEFAULT_SARNESHIN);

        // Get all the adamKhesaratList where sarneshin equals to UPDATED_SARNESHIN
        defaultAdamKhesaratShouldNotBeFound("sarneshin.equals=" + UPDATED_SARNESHIN);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySarneshinIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where sarneshin in DEFAULT_SARNESHIN or UPDATED_SARNESHIN
        defaultAdamKhesaratShouldBeFound("sarneshin.in=" + DEFAULT_SARNESHIN + "," + UPDATED_SARNESHIN);

        // Get all the adamKhesaratList where sarneshin equals to UPDATED_SARNESHIN
        defaultAdamKhesaratShouldNotBeFound("sarneshin.in=" + UPDATED_SARNESHIN);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySarneshinIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where sarneshin is not null
        defaultAdamKhesaratShouldBeFound("sarneshin.specified=true");

        // Get all the adamKhesaratList where sarneshin is null
        defaultAdamKhesaratShouldNotBeFound("sarneshin.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where faal equals to DEFAULT_FAAL
        defaultAdamKhesaratShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the adamKhesaratList where faal equals to UPDATED_FAAL
        defaultAdamKhesaratShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultAdamKhesaratShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the adamKhesaratList where faal equals to UPDATED_FAAL
        defaultAdamKhesaratShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        // Get all the adamKhesaratList where faal is not null
        defaultAdamKhesaratShouldBeFound("faal.specified=true");

        // Get all the adamKhesaratList where faal is null
        defaultAdamKhesaratShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratsBySabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesarat.setSabeghe(sabeghe);
        adamKhesaratRepository.saveAndFlush(adamKhesarat);
        Long sabegheId = sabeghe.getId();

        // Get all the adamKhesaratList where sabeghe equals to sabegheId
        defaultAdamKhesaratShouldBeFound("sabegheId.equals=" + sabegheId);

        // Get all the adamKhesaratList where sabeghe equals to sabegheId + 1
        defaultAdamKhesaratShouldNotBeFound("sabegheId.equals=" + (sabegheId + 1));
    }


    @Test
    @Transactional
    public void getAllAdamKhesaratsByNoeSabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesarat.setNoeSabeghe(noeSabeghe);
        adamKhesaratRepository.saveAndFlush(adamKhesarat);
        Long noeSabegheId = noeSabeghe.getId();

        // Get all the adamKhesaratList where noeSabeghe equals to noeSabegheId
        defaultAdamKhesaratShouldBeFound("noeSabegheId.equals=" + noeSabegheId);

        // Get all the adamKhesaratList where noeSabeghe equals to noeSabegheId + 1
        defaultAdamKhesaratShouldNotBeFound("noeSabegheId.equals=" + (noeSabegheId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAdamKhesaratShouldBeFound(String filter) throws Exception {
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesarat.getId().intValue())))
            .andExpect(jsonPath("$.[*].sales").value(hasItem(DEFAULT_SALES.doubleValue())))
            .andExpect(jsonPath("$.[*].mazad").value(hasItem(DEFAULT_MAZAD.doubleValue())))
            .andExpect(jsonPath("$.[*].sarneshin").value(hasItem(DEFAULT_SARNESHIN.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAdamKhesaratShouldNotBeFound(String filter) throws Exception {
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAdamKhesarat() throws Exception {
        // Get the adamKhesarat
        restAdamKhesaratMockMvc.perform(get("/api/adam-khesarats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdamKhesarat() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        int databaseSizeBeforeUpdate = adamKhesaratRepository.findAll().size();

        // Update the adamKhesarat
        AdamKhesarat updatedAdamKhesarat = adamKhesaratRepository.findById(adamKhesarat.getId()).get();
        // Disconnect from session so that the updates on updatedAdamKhesarat are not directly saved in db
        em.detach(updatedAdamKhesarat);
        updatedAdamKhesarat
            .sales(UPDATED_SALES)
            .mazad(UPDATED_MAZAD)
            .sarneshin(UPDATED_SARNESHIN)
            .faal(UPDATED_FAAL);
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(updatedAdamKhesarat);

        restAdamKhesaratMockMvc.perform(put("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isOk());

        // Validate the AdamKhesarat in the database
        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeUpdate);
        AdamKhesarat testAdamKhesarat = adamKhesaratList.get(adamKhesaratList.size() - 1);
        assertThat(testAdamKhesarat.getSales()).isEqualTo(UPDATED_SALES);
        assertThat(testAdamKhesarat.getMazad()).isEqualTo(UPDATED_MAZAD);
        assertThat(testAdamKhesarat.getSarneshin()).isEqualTo(UPDATED_SARNESHIN);
        assertThat(testAdamKhesarat.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAdamKhesarat() throws Exception {
        int databaseSizeBeforeUpdate = adamKhesaratRepository.findAll().size();

        // Create the AdamKhesarat
        AdamKhesaratDTO adamKhesaratDTO = adamKhesaratMapper.toDto(adamKhesarat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdamKhesaratMockMvc.perform(put("/api/adam-khesarats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesarat in the database
        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdamKhesarat() throws Exception {
        // Initialize the database
        adamKhesaratRepository.saveAndFlush(adamKhesarat);

        int databaseSizeBeforeDelete = adamKhesaratRepository.findAll().size();

        // Get the adamKhesarat
        restAdamKhesaratMockMvc.perform(delete("/api/adam-khesarats/{id}", adamKhesarat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdamKhesarat> adamKhesaratList = adamKhesaratRepository.findAll();
        assertThat(adamKhesaratList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesarat.class);
        AdamKhesarat adamKhesarat1 = new AdamKhesarat();
        adamKhesarat1.setId(1L);
        AdamKhesarat adamKhesarat2 = new AdamKhesarat();
        adamKhesarat2.setId(adamKhesarat1.getId());
        assertThat(adamKhesarat1).isEqualTo(adamKhesarat2);
        adamKhesarat2.setId(2L);
        assertThat(adamKhesarat1).isNotEqualTo(adamKhesarat2);
        adamKhesarat1.setId(null);
        assertThat(adamKhesarat1).isNotEqualTo(adamKhesarat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratDTO.class);
        AdamKhesaratDTO adamKhesaratDTO1 = new AdamKhesaratDTO();
        adamKhesaratDTO1.setId(1L);
        AdamKhesaratDTO adamKhesaratDTO2 = new AdamKhesaratDTO();
        assertThat(adamKhesaratDTO1).isNotEqualTo(adamKhesaratDTO2);
        adamKhesaratDTO2.setId(adamKhesaratDTO1.getId());
        assertThat(adamKhesaratDTO1).isEqualTo(adamKhesaratDTO2);
        adamKhesaratDTO2.setId(2L);
        assertThat(adamKhesaratDTO1).isNotEqualTo(adamKhesaratDTO2);
        adamKhesaratDTO1.setId(null);
        assertThat(adamKhesaratDTO1).isNotEqualTo(adamKhesaratDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adamKhesaratMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adamKhesaratMapper.fromId(null)).isNull();
    }
}
