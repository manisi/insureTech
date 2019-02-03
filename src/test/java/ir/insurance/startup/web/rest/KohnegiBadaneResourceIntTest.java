package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.KohnegiBadane;
import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.KohnegiBadaneRepository;
import ir.insurance.startup.service.KohnegiBadaneService;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;
import ir.insurance.startup.service.mapper.KohnegiBadaneMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.KohnegiBadaneCriteria;
import ir.insurance.startup.service.KohnegiBadaneQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the KohnegiBadaneResource REST controller.
 *
 * @see KohnegiBadaneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class KohnegiBadaneResourceIntTest {

    private static final Float DEFAULT_DARSAD_HAR_SAL = 0F;
    private static final Float UPDATED_DARSAD_HAR_SAL = 1F;

    private static final Float DEFAULT_MAX_DARSAD = 0F;
    private static final Float UPDATED_MAX_DARSAD = 1F;

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private KohnegiBadaneRepository kohnegiBadaneRepository;

    @Autowired
    private KohnegiBadaneMapper kohnegiBadaneMapper;

    @Autowired
    private KohnegiBadaneService kohnegiBadaneService;

    @Autowired
    private KohnegiBadaneQueryService kohnegiBadaneQueryService;

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

    private MockMvc restKohnegiBadaneMockMvc;

    private KohnegiBadane kohnegiBadane;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KohnegiBadaneResource kohnegiBadaneResource = new KohnegiBadaneResource(kohnegiBadaneService, kohnegiBadaneQueryService);
        this.restKohnegiBadaneMockMvc = MockMvcBuilders.standaloneSetup(kohnegiBadaneResource)
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
    public static KohnegiBadane createEntity(EntityManager em) {
        KohnegiBadane kohnegiBadane = new KohnegiBadane()
            .darsadHarSal(DEFAULT_DARSAD_HAR_SAL)
            .maxDarsad(DEFAULT_MAX_DARSAD)
            .sharh(DEFAULT_SHARH)
            .faal(DEFAULT_FAAL);
        // Add required entity
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        kohnegiBadane.setGrouhKhodro(grouhKhodro);
        return kohnegiBadane;
    }

    @Before
    public void initTest() {
        kohnegiBadane = createEntity(em);
    }

    @Test
    @Transactional
    public void createKohnegiBadane() throws Exception {
        int databaseSizeBeforeCreate = kohnegiBadaneRepository.findAll().size();

        // Create the KohnegiBadane
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(kohnegiBadane);
        restKohnegiBadaneMockMvc.perform(post("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isCreated());

        // Validate the KohnegiBadane in the database
        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeCreate + 1);
        KohnegiBadane testKohnegiBadane = kohnegiBadaneList.get(kohnegiBadaneList.size() - 1);
        assertThat(testKohnegiBadane.getDarsadHarSal()).isEqualTo(DEFAULT_DARSAD_HAR_SAL);
        assertThat(testKohnegiBadane.getMaxDarsad()).isEqualTo(DEFAULT_MAX_DARSAD);
        assertThat(testKohnegiBadane.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testKohnegiBadane.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createKohnegiBadaneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kohnegiBadaneRepository.findAll().size();

        // Create the KohnegiBadane with an existing ID
        kohnegiBadane.setId(1L);
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(kohnegiBadane);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKohnegiBadaneMockMvc.perform(post("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KohnegiBadane in the database
        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDarsadHarSalIsRequired() throws Exception {
        int databaseSizeBeforeTest = kohnegiBadaneRepository.findAll().size();
        // set the field null
        kohnegiBadane.setDarsadHarSal(null);

        // Create the KohnegiBadane, which fails.
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(kohnegiBadane);

        restKohnegiBadaneMockMvc.perform(post("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isBadRequest());

        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxDarsadIsRequired() throws Exception {
        int databaseSizeBeforeTest = kohnegiBadaneRepository.findAll().size();
        // set the field null
        kohnegiBadane.setMaxDarsad(null);

        // Create the KohnegiBadane, which fails.
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(kohnegiBadane);

        restKohnegiBadaneMockMvc.perform(post("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isBadRequest());

        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = kohnegiBadaneRepository.findAll().size();
        // set the field null
        kohnegiBadane.setFaal(null);

        // Create the KohnegiBadane, which fails.
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(kohnegiBadane);

        restKohnegiBadaneMockMvc.perform(post("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isBadRequest());

        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanes() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kohnegiBadane.getId().intValue())))
            .andExpect(jsonPath("$.[*].darsadHarSal").value(hasItem(DEFAULT_DARSAD_HAR_SAL.doubleValue())))
            .andExpect(jsonPath("$.[*].maxDarsad").value(hasItem(DEFAULT_MAX_DARSAD.doubleValue())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKohnegiBadane() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get the kohnegiBadane
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes/{id}", kohnegiBadane.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kohnegiBadane.getId().intValue()))
            .andExpect(jsonPath("$.darsadHarSal").value(DEFAULT_DARSAD_HAR_SAL.doubleValue()))
            .andExpect(jsonPath("$.maxDarsad").value(DEFAULT_MAX_DARSAD.doubleValue()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByDarsadHarSalIsEqualToSomething() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where darsadHarSal equals to DEFAULT_DARSAD_HAR_SAL
        defaultKohnegiBadaneShouldBeFound("darsadHarSal.equals=" + DEFAULT_DARSAD_HAR_SAL);

        // Get all the kohnegiBadaneList where darsadHarSal equals to UPDATED_DARSAD_HAR_SAL
        defaultKohnegiBadaneShouldNotBeFound("darsadHarSal.equals=" + UPDATED_DARSAD_HAR_SAL);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByDarsadHarSalIsInShouldWork() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where darsadHarSal in DEFAULT_DARSAD_HAR_SAL or UPDATED_DARSAD_HAR_SAL
        defaultKohnegiBadaneShouldBeFound("darsadHarSal.in=" + DEFAULT_DARSAD_HAR_SAL + "," + UPDATED_DARSAD_HAR_SAL);

        // Get all the kohnegiBadaneList where darsadHarSal equals to UPDATED_DARSAD_HAR_SAL
        defaultKohnegiBadaneShouldNotBeFound("darsadHarSal.in=" + UPDATED_DARSAD_HAR_SAL);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByDarsadHarSalIsNullOrNotNull() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where darsadHarSal is not null
        defaultKohnegiBadaneShouldBeFound("darsadHarSal.specified=true");

        // Get all the kohnegiBadaneList where darsadHarSal is null
        defaultKohnegiBadaneShouldNotBeFound("darsadHarSal.specified=false");
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByMaxDarsadIsEqualToSomething() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where maxDarsad equals to DEFAULT_MAX_DARSAD
        defaultKohnegiBadaneShouldBeFound("maxDarsad.equals=" + DEFAULT_MAX_DARSAD);

        // Get all the kohnegiBadaneList where maxDarsad equals to UPDATED_MAX_DARSAD
        defaultKohnegiBadaneShouldNotBeFound("maxDarsad.equals=" + UPDATED_MAX_DARSAD);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByMaxDarsadIsInShouldWork() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where maxDarsad in DEFAULT_MAX_DARSAD or UPDATED_MAX_DARSAD
        defaultKohnegiBadaneShouldBeFound("maxDarsad.in=" + DEFAULT_MAX_DARSAD + "," + UPDATED_MAX_DARSAD);

        // Get all the kohnegiBadaneList where maxDarsad equals to UPDATED_MAX_DARSAD
        defaultKohnegiBadaneShouldNotBeFound("maxDarsad.in=" + UPDATED_MAX_DARSAD);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByMaxDarsadIsNullOrNotNull() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where maxDarsad is not null
        defaultKohnegiBadaneShouldBeFound("maxDarsad.specified=true");

        // Get all the kohnegiBadaneList where maxDarsad is null
        defaultKohnegiBadaneShouldNotBeFound("maxDarsad.specified=false");
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where faal equals to DEFAULT_FAAL
        defaultKohnegiBadaneShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the kohnegiBadaneList where faal equals to UPDATED_FAAL
        defaultKohnegiBadaneShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultKohnegiBadaneShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the kohnegiBadaneList where faal equals to UPDATED_FAAL
        defaultKohnegiBadaneShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        // Get all the kohnegiBadaneList where faal is not null
        defaultKohnegiBadaneShouldBeFound("faal.specified=true");

        // Get all the kohnegiBadaneList where faal is null
        defaultKohnegiBadaneShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllKohnegiBadanesByGrouhKhodroIsEqualToSomething() throws Exception {
        // Initialize the database
        GrouhKhodro grouhKhodro = GrouhKhodroResourceIntTest.createEntity(em);
        em.persist(grouhKhodro);
        em.flush();
        kohnegiBadane.setGrouhKhodro(grouhKhodro);
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);
        Long grouhKhodroId = grouhKhodro.getId();

        // Get all the kohnegiBadaneList where grouhKhodro equals to grouhKhodroId
        defaultKohnegiBadaneShouldBeFound("grouhKhodroId.equals=" + grouhKhodroId);

        // Get all the kohnegiBadaneList where grouhKhodro equals to grouhKhodroId + 1
        defaultKohnegiBadaneShouldNotBeFound("grouhKhodroId.equals=" + (grouhKhodroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultKohnegiBadaneShouldBeFound(String filter) throws Exception {
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kohnegiBadane.getId().intValue())))
            .andExpect(jsonPath("$.[*].darsadHarSal").value(hasItem(DEFAULT_DARSAD_HAR_SAL.doubleValue())))
            .andExpect(jsonPath("$.[*].maxDarsad").value(hasItem(DEFAULT_MAX_DARSAD.doubleValue())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultKohnegiBadaneShouldNotBeFound(String filter) throws Exception {
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingKohnegiBadane() throws Exception {
        // Get the kohnegiBadane
        restKohnegiBadaneMockMvc.perform(get("/api/kohnegi-badanes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKohnegiBadane() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        int databaseSizeBeforeUpdate = kohnegiBadaneRepository.findAll().size();

        // Update the kohnegiBadane
        KohnegiBadane updatedKohnegiBadane = kohnegiBadaneRepository.findById(kohnegiBadane.getId()).get();
        // Disconnect from session so that the updates on updatedKohnegiBadane are not directly saved in db
        em.detach(updatedKohnegiBadane);
        updatedKohnegiBadane
            .darsadHarSal(UPDATED_DARSAD_HAR_SAL)
            .maxDarsad(UPDATED_MAX_DARSAD)
            .sharh(UPDATED_SHARH)
            .faal(UPDATED_FAAL);
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(updatedKohnegiBadane);

        restKohnegiBadaneMockMvc.perform(put("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isOk());

        // Validate the KohnegiBadane in the database
        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeUpdate);
        KohnegiBadane testKohnegiBadane = kohnegiBadaneList.get(kohnegiBadaneList.size() - 1);
        assertThat(testKohnegiBadane.getDarsadHarSal()).isEqualTo(UPDATED_DARSAD_HAR_SAL);
        assertThat(testKohnegiBadane.getMaxDarsad()).isEqualTo(UPDATED_MAX_DARSAD);
        assertThat(testKohnegiBadane.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testKohnegiBadane.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingKohnegiBadane() throws Exception {
        int databaseSizeBeforeUpdate = kohnegiBadaneRepository.findAll().size();

        // Create the KohnegiBadane
        KohnegiBadaneDTO kohnegiBadaneDTO = kohnegiBadaneMapper.toDto(kohnegiBadane);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKohnegiBadaneMockMvc.perform(put("/api/kohnegi-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiBadaneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KohnegiBadane in the database
        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKohnegiBadane() throws Exception {
        // Initialize the database
        kohnegiBadaneRepository.saveAndFlush(kohnegiBadane);

        int databaseSizeBeforeDelete = kohnegiBadaneRepository.findAll().size();

        // Delete the kohnegiBadane
        restKohnegiBadaneMockMvc.perform(delete("/api/kohnegi-badanes/{id}", kohnegiBadane.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KohnegiBadane> kohnegiBadaneList = kohnegiBadaneRepository.findAll();
        assertThat(kohnegiBadaneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KohnegiBadane.class);
        KohnegiBadane kohnegiBadane1 = new KohnegiBadane();
        kohnegiBadane1.setId(1L);
        KohnegiBadane kohnegiBadane2 = new KohnegiBadane();
        kohnegiBadane2.setId(kohnegiBadane1.getId());
        assertThat(kohnegiBadane1).isEqualTo(kohnegiBadane2);
        kohnegiBadane2.setId(2L);
        assertThat(kohnegiBadane1).isNotEqualTo(kohnegiBadane2);
        kohnegiBadane1.setId(null);
        assertThat(kohnegiBadane1).isNotEqualTo(kohnegiBadane2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KohnegiBadaneDTO.class);
        KohnegiBadaneDTO kohnegiBadaneDTO1 = new KohnegiBadaneDTO();
        kohnegiBadaneDTO1.setId(1L);
        KohnegiBadaneDTO kohnegiBadaneDTO2 = new KohnegiBadaneDTO();
        assertThat(kohnegiBadaneDTO1).isNotEqualTo(kohnegiBadaneDTO2);
        kohnegiBadaneDTO2.setId(kohnegiBadaneDTO1.getId());
        assertThat(kohnegiBadaneDTO1).isEqualTo(kohnegiBadaneDTO2);
        kohnegiBadaneDTO2.setId(2L);
        assertThat(kohnegiBadaneDTO1).isNotEqualTo(kohnegiBadaneDTO2);
        kohnegiBadaneDTO1.setId(null);
        assertThat(kohnegiBadaneDTO1).isNotEqualTo(kohnegiBadaneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(kohnegiBadaneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(kohnegiBadaneMapper.fromId(null)).isNull();
    }
}
