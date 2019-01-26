package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.KhesaratSrneshin;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.repository.KhesaratSrneshinRepository;
import ir.insurance.startup.service.KhesaratSrneshinService;
import ir.insurance.startup.service.dto.KhesaratSrneshinDTO;
import ir.insurance.startup.service.mapper.KhesaratSrneshinMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.KhesaratSrneshinCriteria;
import ir.insurance.startup.service.KhesaratSrneshinQueryService;

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
 * Test class for the KhesaratSrneshinResource REST controller.
 *
 * @see KhesaratSrneshinResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class KhesaratSrneshinResourceIntTest {

    private static final Float DEFAULT_NERKH_KHESARAT = 0F;
    private static final Float UPDATED_NERKH_KHESARAT = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private KhesaratSrneshinRepository khesaratSrneshinRepository;

    @Autowired
    private KhesaratSrneshinMapper khesaratSrneshinMapper;

    @Autowired
    private KhesaratSrneshinService khesaratSrneshinService;

    @Autowired
    private KhesaratSrneshinQueryService khesaratSrneshinQueryService;

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

    private MockMvc restKhesaratSrneshinMockMvc;

    private KhesaratSrneshin khesaratSrneshin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KhesaratSrneshinResource khesaratSrneshinResource = new KhesaratSrneshinResource(khesaratSrneshinService, khesaratSrneshinQueryService);
        this.restKhesaratSrneshinMockMvc = MockMvcBuilders.standaloneSetup(khesaratSrneshinResource)
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
    public static KhesaratSrneshin createEntity(EntityManager em) {
        KhesaratSrneshin khesaratSrneshin = new KhesaratSrneshin()
            .nerkhKhesarat(DEFAULT_NERKH_KHESARAT)
            .faal(DEFAULT_FAAL);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        khesaratSrneshin.setNoeSabeghe(noeSabeghe);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        khesaratSrneshin.setSabeghe(sabeghe);
        return khesaratSrneshin;
    }

    @Before
    public void initTest() {
        khesaratSrneshin = createEntity(em);
    }

    @Test
    @Transactional
    public void createKhesaratSrneshin() throws Exception {
        int databaseSizeBeforeCreate = khesaratSrneshinRepository.findAll().size();

        // Create the KhesaratSrneshin
        KhesaratSrneshinDTO khesaratSrneshinDTO = khesaratSrneshinMapper.toDto(khesaratSrneshin);
        restKhesaratSrneshinMockMvc.perform(post("/api/khesarat-srneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSrneshinDTO)))
            .andExpect(status().isCreated());

        // Validate the KhesaratSrneshin in the database
        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeCreate + 1);
        KhesaratSrneshin testKhesaratSrneshin = khesaratSrneshinList.get(khesaratSrneshinList.size() - 1);
        assertThat(testKhesaratSrneshin.getNerkhKhesarat()).isEqualTo(DEFAULT_NERKH_KHESARAT);
        assertThat(testKhesaratSrneshin.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createKhesaratSrneshinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = khesaratSrneshinRepository.findAll().size();

        // Create the KhesaratSrneshin with an existing ID
        khesaratSrneshin.setId(1L);
        KhesaratSrneshinDTO khesaratSrneshinDTO = khesaratSrneshinMapper.toDto(khesaratSrneshin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKhesaratSrneshinMockMvc.perform(post("/api/khesarat-srneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSrneshinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhesaratSrneshin in the database
        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNerkhKhesaratIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSrneshinRepository.findAll().size();
        // set the field null
        khesaratSrneshin.setNerkhKhesarat(null);

        // Create the KhesaratSrneshin, which fails.
        KhesaratSrneshinDTO khesaratSrneshinDTO = khesaratSrneshinMapper.toDto(khesaratSrneshin);

        restKhesaratSrneshinMockMvc.perform(post("/api/khesarat-srneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSrneshinDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = khesaratSrneshinRepository.findAll().size();
        // set the field null
        khesaratSrneshin.setFaal(null);

        // Create the KhesaratSrneshin, which fails.
        KhesaratSrneshinDTO khesaratSrneshinDTO = khesaratSrneshinMapper.toDto(khesaratSrneshin);

        restKhesaratSrneshinMockMvc.perform(post("/api/khesarat-srneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSrneshinDTO)))
            .andExpect(status().isBadRequest());

        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshins() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khesaratSrneshin.getId().intValue())))
            .andExpect(jsonPath("$.[*].nerkhKhesarat").value(hasItem(DEFAULT_NERKH_KHESARAT.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKhesaratSrneshin() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get the khesaratSrneshin
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins/{id}", khesaratSrneshin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(khesaratSrneshin.getId().intValue()))
            .andExpect(jsonPath("$.nerkhKhesarat").value(DEFAULT_NERKH_KHESARAT.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByNerkhKhesaratIsEqualToSomething() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList where nerkhKhesarat equals to DEFAULT_NERKH_KHESARAT
        defaultKhesaratSrneshinShouldBeFound("nerkhKhesarat.equals=" + DEFAULT_NERKH_KHESARAT);

        // Get all the khesaratSrneshinList where nerkhKhesarat equals to UPDATED_NERKH_KHESARAT
        defaultKhesaratSrneshinShouldNotBeFound("nerkhKhesarat.equals=" + UPDATED_NERKH_KHESARAT);
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByNerkhKhesaratIsInShouldWork() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList where nerkhKhesarat in DEFAULT_NERKH_KHESARAT or UPDATED_NERKH_KHESARAT
        defaultKhesaratSrneshinShouldBeFound("nerkhKhesarat.in=" + DEFAULT_NERKH_KHESARAT + "," + UPDATED_NERKH_KHESARAT);

        // Get all the khesaratSrneshinList where nerkhKhesarat equals to UPDATED_NERKH_KHESARAT
        defaultKhesaratSrneshinShouldNotBeFound("nerkhKhesarat.in=" + UPDATED_NERKH_KHESARAT);
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByNerkhKhesaratIsNullOrNotNull() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList where nerkhKhesarat is not null
        defaultKhesaratSrneshinShouldBeFound("nerkhKhesarat.specified=true");

        // Get all the khesaratSrneshinList where nerkhKhesarat is null
        defaultKhesaratSrneshinShouldNotBeFound("nerkhKhesarat.specified=false");
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList where faal equals to DEFAULT_FAAL
        defaultKhesaratSrneshinShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the khesaratSrneshinList where faal equals to UPDATED_FAAL
        defaultKhesaratSrneshinShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultKhesaratSrneshinShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the khesaratSrneshinList where faal equals to UPDATED_FAAL
        defaultKhesaratSrneshinShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        // Get all the khesaratSrneshinList where faal is not null
        defaultKhesaratSrneshinShouldBeFound("faal.specified=true");

        // Get all the khesaratSrneshinList where faal is null
        defaultKhesaratSrneshinShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllKhesaratSrneshinsByNoeSabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        khesaratSrneshin.setNoeSabeghe(noeSabeghe);
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);
        Long noeSabegheId = noeSabeghe.getId();

        // Get all the khesaratSrneshinList where noeSabeghe equals to noeSabegheId
        defaultKhesaratSrneshinShouldBeFound("noeSabegheId.equals=" + noeSabegheId);

        // Get all the khesaratSrneshinList where noeSabeghe equals to noeSabegheId + 1
        defaultKhesaratSrneshinShouldNotBeFound("noeSabegheId.equals=" + (noeSabegheId + 1));
    }


    @Test
    @Transactional
    public void getAllKhesaratSrneshinsBySabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        khesaratSrneshin.setSabeghe(sabeghe);
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);
        Long sabegheId = sabeghe.getId();

        // Get all the khesaratSrneshinList where sabeghe equals to sabegheId
        defaultKhesaratSrneshinShouldBeFound("sabegheId.equals=" + sabegheId);

        // Get all the khesaratSrneshinList where sabeghe equals to sabegheId + 1
        defaultKhesaratSrneshinShouldNotBeFound("sabegheId.equals=" + (sabegheId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultKhesaratSrneshinShouldBeFound(String filter) throws Exception {
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khesaratSrneshin.getId().intValue())))
            .andExpect(jsonPath("$.[*].nerkhKhesarat").value(hasItem(DEFAULT_NERKH_KHESARAT.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultKhesaratSrneshinShouldNotBeFound(String filter) throws Exception {
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingKhesaratSrneshin() throws Exception {
        // Get the khesaratSrneshin
        restKhesaratSrneshinMockMvc.perform(get("/api/khesarat-srneshins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKhesaratSrneshin() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        int databaseSizeBeforeUpdate = khesaratSrneshinRepository.findAll().size();

        // Update the khesaratSrneshin
        KhesaratSrneshin updatedKhesaratSrneshin = khesaratSrneshinRepository.findById(khesaratSrneshin.getId()).get();
        // Disconnect from session so that the updates on updatedKhesaratSrneshin are not directly saved in db
        em.detach(updatedKhesaratSrneshin);
        updatedKhesaratSrneshin
            .nerkhKhesarat(UPDATED_NERKH_KHESARAT)
            .faal(UPDATED_FAAL);
        KhesaratSrneshinDTO khesaratSrneshinDTO = khesaratSrneshinMapper.toDto(updatedKhesaratSrneshin);

        restKhesaratSrneshinMockMvc.perform(put("/api/khesarat-srneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSrneshinDTO)))
            .andExpect(status().isOk());

        // Validate the KhesaratSrneshin in the database
        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeUpdate);
        KhesaratSrneshin testKhesaratSrneshin = khesaratSrneshinList.get(khesaratSrneshinList.size() - 1);
        assertThat(testKhesaratSrneshin.getNerkhKhesarat()).isEqualTo(UPDATED_NERKH_KHESARAT);
        assertThat(testKhesaratSrneshin.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingKhesaratSrneshin() throws Exception {
        int databaseSizeBeforeUpdate = khesaratSrneshinRepository.findAll().size();

        // Create the KhesaratSrneshin
        KhesaratSrneshinDTO khesaratSrneshinDTO = khesaratSrneshinMapper.toDto(khesaratSrneshin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKhesaratSrneshinMockMvc.perform(put("/api/khesarat-srneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khesaratSrneshinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhesaratSrneshin in the database
        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKhesaratSrneshin() throws Exception {
        // Initialize the database
        khesaratSrneshinRepository.saveAndFlush(khesaratSrneshin);

        int databaseSizeBeforeDelete = khesaratSrneshinRepository.findAll().size();

        // Delete the khesaratSrneshin
        restKhesaratSrneshinMockMvc.perform(delete("/api/khesarat-srneshins/{id}", khesaratSrneshin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<KhesaratSrneshin> khesaratSrneshinList = khesaratSrneshinRepository.findAll();
        assertThat(khesaratSrneshinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhesaratSrneshin.class);
        KhesaratSrneshin khesaratSrneshin1 = new KhesaratSrneshin();
        khesaratSrneshin1.setId(1L);
        KhesaratSrneshin khesaratSrneshin2 = new KhesaratSrneshin();
        khesaratSrneshin2.setId(khesaratSrneshin1.getId());
        assertThat(khesaratSrneshin1).isEqualTo(khesaratSrneshin2);
        khesaratSrneshin2.setId(2L);
        assertThat(khesaratSrneshin1).isNotEqualTo(khesaratSrneshin2);
        khesaratSrneshin1.setId(null);
        assertThat(khesaratSrneshin1).isNotEqualTo(khesaratSrneshin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhesaratSrneshinDTO.class);
        KhesaratSrneshinDTO khesaratSrneshinDTO1 = new KhesaratSrneshinDTO();
        khesaratSrneshinDTO1.setId(1L);
        KhesaratSrneshinDTO khesaratSrneshinDTO2 = new KhesaratSrneshinDTO();
        assertThat(khesaratSrneshinDTO1).isNotEqualTo(khesaratSrneshinDTO2);
        khesaratSrneshinDTO2.setId(khesaratSrneshinDTO1.getId());
        assertThat(khesaratSrneshinDTO1).isEqualTo(khesaratSrneshinDTO2);
        khesaratSrneshinDTO2.setId(2L);
        assertThat(khesaratSrneshinDTO1).isNotEqualTo(khesaratSrneshinDTO2);
        khesaratSrneshinDTO1.setId(null);
        assertThat(khesaratSrneshinDTO1).isNotEqualTo(khesaratSrneshinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(khesaratSrneshinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(khesaratSrneshinMapper.fromId(null)).isNull();
    }
}
