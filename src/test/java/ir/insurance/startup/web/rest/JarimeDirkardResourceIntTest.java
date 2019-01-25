package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.JarimeDirkard;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.JarimeDirkardRepository;
import ir.insurance.startup.service.JarimeDirkardService;
import ir.insurance.startup.service.dto.JarimeDirkardDTO;
import ir.insurance.startup.service.mapper.JarimeDirkardMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.JarimeDirkardCriteria;
import ir.insurance.startup.service.JarimeDirkardQueryService;

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
 * Test class for the JarimeDirkardResource REST controller.
 *
 * @see JarimeDirkardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class JarimeDirkardResourceIntTest {

    private static final Float DEFAULT_ROOZANE = 0F;
    private static final Float UPDATED_ROOZANE = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private JarimeDirkardRepository jarimeDirkardRepository;

    @Autowired
    private JarimeDirkardMapper jarimeDirkardMapper;

    @Autowired
    private JarimeDirkardService jarimeDirkardService;

    @Autowired
    private JarimeDirkardQueryService jarimeDirkardQueryService;

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

    private MockMvc restJarimeDirkardMockMvc;

    private JarimeDirkard jarimeDirkard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JarimeDirkardResource jarimeDirkardResource = new JarimeDirkardResource(jarimeDirkardService, jarimeDirkardQueryService);
        this.restJarimeDirkardMockMvc = MockMvcBuilders.standaloneSetup(jarimeDirkardResource)
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
    public static JarimeDirkard createEntity(EntityManager em) {
        JarimeDirkard jarimeDirkard = new JarimeDirkard()
            .roozane(DEFAULT_ROOZANE)
            .faal(DEFAULT_FAAL);
        // Add required entity
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        jarimeDirkard.setGrouhKhodro(grouhKhodro);
        return jarimeDirkard;
    }

    @Before
    public void initTest() {
        jarimeDirkard = createEntity(em);
    }

    @Test
    @Transactional
    public void createJarimeDirkard() throws Exception {
        int databaseSizeBeforeCreate = jarimeDirkardRepository.findAll().size();

        // Create the JarimeDirkard
        JarimeDirkardDTO jarimeDirkardDTO = jarimeDirkardMapper.toDto(jarimeDirkard);
        restJarimeDirkardMockMvc.perform(post("/api/jarime-dirkards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jarimeDirkardDTO)))
            .andExpect(status().isCreated());

        // Validate the JarimeDirkard in the database
        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeCreate + 1);
        JarimeDirkard testJarimeDirkard = jarimeDirkardList.get(jarimeDirkardList.size() - 1);
        assertThat(testJarimeDirkard.getRoozane()).isEqualTo(DEFAULT_ROOZANE);
        assertThat(testJarimeDirkard.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createJarimeDirkardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jarimeDirkardRepository.findAll().size();

        // Create the JarimeDirkard with an existing ID
        jarimeDirkard.setId(1L);
        JarimeDirkardDTO jarimeDirkardDTO = jarimeDirkardMapper.toDto(jarimeDirkard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJarimeDirkardMockMvc.perform(post("/api/jarime-dirkards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jarimeDirkardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JarimeDirkard in the database
        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRoozaneIsRequired() throws Exception {
        int databaseSizeBeforeTest = jarimeDirkardRepository.findAll().size();
        // set the field null
        jarimeDirkard.setRoozane(null);

        // Create the JarimeDirkard, which fails.
        JarimeDirkardDTO jarimeDirkardDTO = jarimeDirkardMapper.toDto(jarimeDirkard);

        restJarimeDirkardMockMvc.perform(post("/api/jarime-dirkards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jarimeDirkardDTO)))
            .andExpect(status().isBadRequest());

        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = jarimeDirkardRepository.findAll().size();
        // set the field null
        jarimeDirkard.setFaal(null);

        // Create the JarimeDirkard, which fails.
        JarimeDirkardDTO jarimeDirkardDTO = jarimeDirkardMapper.toDto(jarimeDirkard);

        restJarimeDirkardMockMvc.perform(post("/api/jarime-dirkards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jarimeDirkardDTO)))
            .andExpect(status().isBadRequest());

        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJarimeDirkards() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jarimeDirkard.getId().intValue())))
            .andExpect(jsonPath("$.[*].roozane").value(hasItem(DEFAULT_ROOZANE.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getJarimeDirkard() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get the jarimeDirkard
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards/{id}", jarimeDirkard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jarimeDirkard.getId().intValue()))
            .andExpect(jsonPath("$.roozane").value(DEFAULT_ROOZANE.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByRoozaneIsEqualToSomething() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList where roozane equals to DEFAULT_ROOZANE
        defaultJarimeDirkardShouldBeFound("roozane.equals=" + DEFAULT_ROOZANE);

        // Get all the jarimeDirkardList where roozane equals to UPDATED_ROOZANE
        defaultJarimeDirkardShouldNotBeFound("roozane.equals=" + UPDATED_ROOZANE);
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByRoozaneIsInShouldWork() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList where roozane in DEFAULT_ROOZANE or UPDATED_ROOZANE
        defaultJarimeDirkardShouldBeFound("roozane.in=" + DEFAULT_ROOZANE + "," + UPDATED_ROOZANE);

        // Get all the jarimeDirkardList where roozane equals to UPDATED_ROOZANE
        defaultJarimeDirkardShouldNotBeFound("roozane.in=" + UPDATED_ROOZANE);
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByRoozaneIsNullOrNotNull() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList where roozane is not null
        defaultJarimeDirkardShouldBeFound("roozane.specified=true");

        // Get all the jarimeDirkardList where roozane is null
        defaultJarimeDirkardShouldNotBeFound("roozane.specified=false");
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList where faal equals to DEFAULT_FAAL
        defaultJarimeDirkardShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the jarimeDirkardList where faal equals to UPDATED_FAAL
        defaultJarimeDirkardShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultJarimeDirkardShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the jarimeDirkardList where faal equals to UPDATED_FAAL
        defaultJarimeDirkardShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        // Get all the jarimeDirkardList where faal is not null
        defaultJarimeDirkardShouldBeFound("faal.specified=true");

        // Get all the jarimeDirkardList where faal is null
        defaultJarimeDirkardShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllJarimeDirkardsByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        jarimeDirkard.setGrouhKhodro(grouhKhodro);
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the jarimeDirkardList where grouhKhodro equals to grouhKhodroId
        defaultJarimeDirkardShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the jarimeDirkardList where grouhKhodro equals to grouhKhodroId + 1
        defaultJarimeDirkardShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultJarimeDirkardShouldBeFound(String filter) throws Exception {
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jarimeDirkard.getId().intValue())))
            .andExpect(jsonPath("$.[*].roozane").value(hasItem(DEFAULT_ROOZANE.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultJarimeDirkardShouldNotBeFound(String filter) throws Exception {
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingJarimeDirkard() throws Exception {
        // Get the jarimeDirkard
        restJarimeDirkardMockMvc.perform(get("/api/jarime-dirkards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJarimeDirkard() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        int databaseSizeBeforeUpdate = jarimeDirkardRepository.findAll().size();

        // Update the jarimeDirkard
        JarimeDirkard updatedJarimeDirkard = jarimeDirkardRepository.findById(jarimeDirkard.getId()).get();
        // Disconnect from session so that the updates on updatedJarimeDirkard are not directly saved in db
        em.detach(updatedJarimeDirkard);
        updatedJarimeDirkard
            .roozane(UPDATED_ROOZANE)
            .faal(UPDATED_FAAL);
        JarimeDirkardDTO jarimeDirkardDTO = jarimeDirkardMapper.toDto(updatedJarimeDirkard);

        restJarimeDirkardMockMvc.perform(put("/api/jarime-dirkards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jarimeDirkardDTO)))
            .andExpect(status().isOk());

        // Validate the JarimeDirkard in the database
        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeUpdate);
        JarimeDirkard testJarimeDirkard = jarimeDirkardList.get(jarimeDirkardList.size() - 1);
        assertThat(testJarimeDirkard.getRoozane()).isEqualTo(UPDATED_ROOZANE);
        assertThat(testJarimeDirkard.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingJarimeDirkard() throws Exception {
        int databaseSizeBeforeUpdate = jarimeDirkardRepository.findAll().size();

        // Create the JarimeDirkard
        JarimeDirkardDTO jarimeDirkardDTO = jarimeDirkardMapper.toDto(jarimeDirkard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJarimeDirkardMockMvc.perform(put("/api/jarime-dirkards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jarimeDirkardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the JarimeDirkard in the database
        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJarimeDirkard() throws Exception {
        // Initialize the database
        jarimeDirkardRepository.saveAndFlush(jarimeDirkard);

        int databaseSizeBeforeDelete = jarimeDirkardRepository.findAll().size();

        // Get the jarimeDirkard
        restJarimeDirkardMockMvc.perform(delete("/api/jarime-dirkards/{id}", jarimeDirkard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JarimeDirkard> jarimeDirkardList = jarimeDirkardRepository.findAll();
        assertThat(jarimeDirkardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JarimeDirkard.class);
        JarimeDirkard jarimeDirkard1 = new JarimeDirkard();
        jarimeDirkard1.setId(1L);
        JarimeDirkard jarimeDirkard2 = new JarimeDirkard();
        jarimeDirkard2.setId(jarimeDirkard1.getId());
        assertThat(jarimeDirkard1).isEqualTo(jarimeDirkard2);
        jarimeDirkard2.setId(2L);
        assertThat(jarimeDirkard1).isNotEqualTo(jarimeDirkard2);
        jarimeDirkard1.setId(null);
        assertThat(jarimeDirkard1).isNotEqualTo(jarimeDirkard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JarimeDirkardDTO.class);
        JarimeDirkardDTO jarimeDirkardDTO1 = new JarimeDirkardDTO();
        jarimeDirkardDTO1.setId(1L);
        JarimeDirkardDTO jarimeDirkardDTO2 = new JarimeDirkardDTO();
        assertThat(jarimeDirkardDTO1).isNotEqualTo(jarimeDirkardDTO2);
        jarimeDirkardDTO2.setId(jarimeDirkardDTO1.getId());
        assertThat(jarimeDirkardDTO1).isEqualTo(jarimeDirkardDTO2);
        jarimeDirkardDTO2.setId(2L);
        assertThat(jarimeDirkardDTO1).isNotEqualTo(jarimeDirkardDTO2);
        jarimeDirkardDTO1.setId(null);
        assertThat(jarimeDirkardDTO1).isNotEqualTo(jarimeDirkardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jarimeDirkardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jarimeDirkardMapper.fromId(null)).isNull();
    }
}
