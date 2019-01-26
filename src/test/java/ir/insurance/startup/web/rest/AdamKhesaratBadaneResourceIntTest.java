package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.AdamKhesaratBadane;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.repository.AdamKhesaratBadaneRepository;
import ir.insurance.startup.service.AdamKhesaratBadaneService;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratBadaneMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneCriteria;
import ir.insurance.startup.service.AdamKhesaratBadaneQueryService;

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
 * Test class for the AdamKhesaratBadaneResource REST controller.
 *
 * @see AdamKhesaratBadaneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class AdamKhesaratBadaneResourceIntTest {

    private static final Float DEFAULT_BADANE = 0F;
    private static final Float UPDATED_BADANE = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private AdamKhesaratBadaneRepository adamKhesaratBadaneRepository;

    @Autowired
    private AdamKhesaratBadaneMapper adamKhesaratBadaneMapper;

    @Autowired
    private AdamKhesaratBadaneService adamKhesaratBadaneService;

    @Autowired
    private AdamKhesaratBadaneQueryService adamKhesaratBadaneQueryService;

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

    private MockMvc restAdamKhesaratBadaneMockMvc;

    private AdamKhesaratBadane adamKhesaratBadane;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdamKhesaratBadaneResource adamKhesaratBadaneResource = new AdamKhesaratBadaneResource(adamKhesaratBadaneService, adamKhesaratBadaneQueryService);
        this.restAdamKhesaratBadaneMockMvc = MockMvcBuilders.standaloneSetup(adamKhesaratBadaneResource)
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
    public static AdamKhesaratBadane createEntity(EntityManager em) {
        AdamKhesaratBadane adamKhesaratBadane = new AdamKhesaratBadane()
            .badane(DEFAULT_BADANE)
            .faal(DEFAULT_FAAL);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesaratBadane.setNoeSabeghe(noeSabeghe);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesaratBadane.setSabeghe(sabeghe);
        return adamKhesaratBadane;
    }

    @Before
    public void initTest() {
        adamKhesaratBadane = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdamKhesaratBadane() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratBadaneRepository.findAll().size();

        // Create the AdamKhesaratBadane
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO = adamKhesaratBadaneMapper.toDto(adamKhesaratBadane);
        restAdamKhesaratBadaneMockMvc.perform(post("/api/adam-khesarat-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratBadaneDTO)))
            .andExpect(status().isCreated());

        // Validate the AdamKhesaratBadane in the database
        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeCreate + 1);
        AdamKhesaratBadane testAdamKhesaratBadane = adamKhesaratBadaneList.get(adamKhesaratBadaneList.size() - 1);
        assertThat(testAdamKhesaratBadane.getBadane()).isEqualTo(DEFAULT_BADANE);
        assertThat(testAdamKhesaratBadane.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createAdamKhesaratBadaneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratBadaneRepository.findAll().size();

        // Create the AdamKhesaratBadane with an existing ID
        adamKhesaratBadane.setId(1L);
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO = adamKhesaratBadaneMapper.toDto(adamKhesaratBadane);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdamKhesaratBadaneMockMvc.perform(post("/api/adam-khesarat-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratBadaneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesaratBadane in the database
        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBadaneIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratBadaneRepository.findAll().size();
        // set the field null
        adamKhesaratBadane.setBadane(null);

        // Create the AdamKhesaratBadane, which fails.
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO = adamKhesaratBadaneMapper.toDto(adamKhesaratBadane);

        restAdamKhesaratBadaneMockMvc.perform(post("/api/adam-khesarat-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratBadaneDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratBadaneRepository.findAll().size();
        // set the field null
        adamKhesaratBadane.setFaal(null);

        // Create the AdamKhesaratBadane, which fails.
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO = adamKhesaratBadaneMapper.toDto(adamKhesaratBadane);

        restAdamKhesaratBadaneMockMvc.perform(post("/api/adam-khesarat-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratBadaneDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanes() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesaratBadane.getId().intValue())))
            .andExpect(jsonPath("$.[*].badane").value(hasItem(DEFAULT_BADANE.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdamKhesaratBadane() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get the adamKhesaratBadane
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes/{id}", adamKhesaratBadane.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adamKhesaratBadane.getId().intValue()))
            .andExpect(jsonPath("$.badane").value(DEFAULT_BADANE.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByBadaneIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList where badane equals to DEFAULT_BADANE
        defaultAdamKhesaratBadaneShouldBeFound("badane.equals=" + DEFAULT_BADANE);

        // Get all the adamKhesaratBadaneList where badane equals to UPDATED_BADANE
        defaultAdamKhesaratBadaneShouldNotBeFound("badane.equals=" + UPDATED_BADANE);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByBadaneIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList where badane in DEFAULT_BADANE or UPDATED_BADANE
        defaultAdamKhesaratBadaneShouldBeFound("badane.in=" + DEFAULT_BADANE + "," + UPDATED_BADANE);

        // Get all the adamKhesaratBadaneList where badane equals to UPDATED_BADANE
        defaultAdamKhesaratBadaneShouldNotBeFound("badane.in=" + UPDATED_BADANE);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByBadaneIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList where badane is not null
        defaultAdamKhesaratBadaneShouldBeFound("badane.specified=true");

        // Get all the adamKhesaratBadaneList where badane is null
        defaultAdamKhesaratBadaneShouldNotBeFound("badane.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList where faal equals to DEFAULT_FAAL
        defaultAdamKhesaratBadaneShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the adamKhesaratBadaneList where faal equals to UPDATED_FAAL
        defaultAdamKhesaratBadaneShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultAdamKhesaratBadaneShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the adamKhesaratBadaneList where faal equals to UPDATED_FAAL
        defaultAdamKhesaratBadaneShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        // Get all the adamKhesaratBadaneList where faal is not null
        defaultAdamKhesaratBadaneShouldBeFound("faal.specified=true");

        // Get all the adamKhesaratBadaneList where faal is null
        defaultAdamKhesaratBadaneShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesByNoeSabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesaratBadane.setNoeSabeghe(noeSabeghe);
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);
        Long noeSabegheId = noeSabeghe.getId();

        // Get all the adamKhesaratBadaneList where noeSabeghe equals to noeSabegheId
        defaultAdamKhesaratBadaneShouldBeFound("noeSabegheId.equals=" + noeSabegheId);

        // Get all the adamKhesaratBadaneList where noeSabeghe equals to noeSabegheId + 1
        defaultAdamKhesaratBadaneShouldNotBeFound("noeSabegheId.equals=" + (noeSabegheId + 1));
    }


    @Test
    @Transactional
    public void getAllAdamKhesaratBadanesBySabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesaratBadane.setSabeghe(sabeghe);
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);
        Long sabegheId = sabeghe.getId();

        // Get all the adamKhesaratBadaneList where sabeghe equals to sabegheId
        defaultAdamKhesaratBadaneShouldBeFound("sabegheId.equals=" + sabegheId);

        // Get all the adamKhesaratBadaneList where sabeghe equals to sabegheId + 1
        defaultAdamKhesaratBadaneShouldNotBeFound("sabegheId.equals=" + (sabegheId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAdamKhesaratBadaneShouldBeFound(String filter) throws Exception {
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesaratBadane.getId().intValue())))
            .andExpect(jsonPath("$.[*].badane").value(hasItem(DEFAULT_BADANE.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAdamKhesaratBadaneShouldNotBeFound(String filter) throws Exception {
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAdamKhesaratBadane() throws Exception {
        // Get the adamKhesaratBadane
        restAdamKhesaratBadaneMockMvc.perform(get("/api/adam-khesarat-badanes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdamKhesaratBadane() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        int databaseSizeBeforeUpdate = adamKhesaratBadaneRepository.findAll().size();

        // Update the adamKhesaratBadane
        AdamKhesaratBadane updatedAdamKhesaratBadane = adamKhesaratBadaneRepository.findById(adamKhesaratBadane.getId()).get();
        // Disconnect from session so that the updates on updatedAdamKhesaratBadane are not directly saved in db
        em.detach(updatedAdamKhesaratBadane);
        updatedAdamKhesaratBadane
            .badane(UPDATED_BADANE)
            .faal(UPDATED_FAAL);
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO = adamKhesaratBadaneMapper.toDto(updatedAdamKhesaratBadane);

        restAdamKhesaratBadaneMockMvc.perform(put("/api/adam-khesarat-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratBadaneDTO)))
            .andExpect(status().isOk());

        // Validate the AdamKhesaratBadane in the database
        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeUpdate);
        AdamKhesaratBadane testAdamKhesaratBadane = adamKhesaratBadaneList.get(adamKhesaratBadaneList.size() - 1);
        assertThat(testAdamKhesaratBadane.getBadane()).isEqualTo(UPDATED_BADANE);
        assertThat(testAdamKhesaratBadane.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAdamKhesaratBadane() throws Exception {
        int databaseSizeBeforeUpdate = adamKhesaratBadaneRepository.findAll().size();

        // Create the AdamKhesaratBadane
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO = adamKhesaratBadaneMapper.toDto(adamKhesaratBadane);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdamKhesaratBadaneMockMvc.perform(put("/api/adam-khesarat-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratBadaneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesaratBadane in the database
        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdamKhesaratBadane() throws Exception {
        // Initialize the database
        adamKhesaratBadaneRepository.saveAndFlush(adamKhesaratBadane);

        int databaseSizeBeforeDelete = adamKhesaratBadaneRepository.findAll().size();

        // Delete the adamKhesaratBadane
        restAdamKhesaratBadaneMockMvc.perform(delete("/api/adam-khesarat-badanes/{id}", adamKhesaratBadane.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdamKhesaratBadane> adamKhesaratBadaneList = adamKhesaratBadaneRepository.findAll();
        assertThat(adamKhesaratBadaneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratBadane.class);
        AdamKhesaratBadane adamKhesaratBadane1 = new AdamKhesaratBadane();
        adamKhesaratBadane1.setId(1L);
        AdamKhesaratBadane adamKhesaratBadane2 = new AdamKhesaratBadane();
        adamKhesaratBadane2.setId(adamKhesaratBadane1.getId());
        assertThat(adamKhesaratBadane1).isEqualTo(adamKhesaratBadane2);
        adamKhesaratBadane2.setId(2L);
        assertThat(adamKhesaratBadane1).isNotEqualTo(adamKhesaratBadane2);
        adamKhesaratBadane1.setId(null);
        assertThat(adamKhesaratBadane1).isNotEqualTo(adamKhesaratBadane2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratBadaneDTO.class);
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO1 = new AdamKhesaratBadaneDTO();
        adamKhesaratBadaneDTO1.setId(1L);
        AdamKhesaratBadaneDTO adamKhesaratBadaneDTO2 = new AdamKhesaratBadaneDTO();
        assertThat(adamKhesaratBadaneDTO1).isNotEqualTo(adamKhesaratBadaneDTO2);
        adamKhesaratBadaneDTO2.setId(adamKhesaratBadaneDTO1.getId());
        assertThat(adamKhesaratBadaneDTO1).isEqualTo(adamKhesaratBadaneDTO2);
        adamKhesaratBadaneDTO2.setId(2L);
        assertThat(adamKhesaratBadaneDTO1).isNotEqualTo(adamKhesaratBadaneDTO2);
        adamKhesaratBadaneDTO1.setId(null);
        assertThat(adamKhesaratBadaneDTO1).isNotEqualTo(adamKhesaratBadaneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adamKhesaratBadaneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adamKhesaratBadaneMapper.fromId(null)).isNull();
    }
}
