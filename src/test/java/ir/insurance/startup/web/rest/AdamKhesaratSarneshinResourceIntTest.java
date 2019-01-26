package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.AdamKhesaratSarneshin;
import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.repository.AdamKhesaratSarneshinRepository;
import ir.insurance.startup.service.AdamKhesaratSarneshinService;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratSarneshinMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinCriteria;
import ir.insurance.startup.service.AdamKhesaratSarneshinQueryService;

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
 * Test class for the AdamKhesaratSarneshinResource REST controller.
 *
 * @see AdamKhesaratSarneshinResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class AdamKhesaratSarneshinResourceIntTest {

    private static final Float DEFAULT_SARNESHIN = 0F;
    private static final Float UPDATED_SARNESHIN = 1F;

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private AdamKhesaratSarneshinRepository adamKhesaratSarneshinRepository;

    @Autowired
    private AdamKhesaratSarneshinMapper adamKhesaratSarneshinMapper;

    @Autowired
    private AdamKhesaratSarneshinService adamKhesaratSarneshinService;

    @Autowired
    private AdamKhesaratSarneshinQueryService adamKhesaratSarneshinQueryService;

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

    private MockMvc restAdamKhesaratSarneshinMockMvc;

    private AdamKhesaratSarneshin adamKhesaratSarneshin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdamKhesaratSarneshinResource adamKhesaratSarneshinResource = new AdamKhesaratSarneshinResource(adamKhesaratSarneshinService, adamKhesaratSarneshinQueryService);
        this.restAdamKhesaratSarneshinMockMvc = MockMvcBuilders.standaloneSetup(adamKhesaratSarneshinResource)
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
    public static AdamKhesaratSarneshin createEntity(EntityManager em) {
        AdamKhesaratSarneshin adamKhesaratSarneshin = new AdamKhesaratSarneshin()
            .sarneshin(DEFAULT_SARNESHIN)
            .faal(DEFAULT_FAAL);
        // Add required entity
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesaratSarneshin.setNoeSabeghe(noeSabeghe);
        // Add required entity
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesaratSarneshin.setSabeghe(sabeghe);
        return adamKhesaratSarneshin;
    }

    @Before
    public void initTest() {
        adamKhesaratSarneshin = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdamKhesaratSarneshin() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratSarneshinRepository.findAll().size();

        // Create the AdamKhesaratSarneshin
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshin);
        restAdamKhesaratSarneshinMockMvc.perform(post("/api/adam-khesarat-sarneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSarneshinDTO)))
            .andExpect(status().isCreated());

        // Validate the AdamKhesaratSarneshin in the database
        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeCreate + 1);
        AdamKhesaratSarneshin testAdamKhesaratSarneshin = adamKhesaratSarneshinList.get(adamKhesaratSarneshinList.size() - 1);
        assertThat(testAdamKhesaratSarneshin.getSarneshin()).isEqualTo(DEFAULT_SARNESHIN);
        assertThat(testAdamKhesaratSarneshin.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createAdamKhesaratSarneshinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adamKhesaratSarneshinRepository.findAll().size();

        // Create the AdamKhesaratSarneshin with an existing ID
        adamKhesaratSarneshin.setId(1L);
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdamKhesaratSarneshinMockMvc.perform(post("/api/adam-khesarat-sarneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSarneshinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesaratSarneshin in the database
        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSarneshinIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratSarneshinRepository.findAll().size();
        // set the field null
        adamKhesaratSarneshin.setSarneshin(null);

        // Create the AdamKhesaratSarneshin, which fails.
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshin);

        restAdamKhesaratSarneshinMockMvc.perform(post("/api/adam-khesarat-sarneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSarneshinDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = adamKhesaratSarneshinRepository.findAll().size();
        // set the field null
        adamKhesaratSarneshin.setFaal(null);

        // Create the AdamKhesaratSarneshin, which fails.
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshin);

        restAdamKhesaratSarneshinMockMvc.perform(post("/api/adam-khesarat-sarneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSarneshinDTO)))
            .andExpect(status().isBadRequest());

        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshins() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesaratSarneshin.getId().intValue())))
            .andExpect(jsonPath("$.[*].sarneshin").value(hasItem(DEFAULT_SARNESHIN.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAdamKhesaratSarneshin() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get the adamKhesaratSarneshin
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins/{id}", adamKhesaratSarneshin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adamKhesaratSarneshin.getId().intValue()))
            .andExpect(jsonPath("$.sarneshin").value(DEFAULT_SARNESHIN.doubleValue()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsBySarneshinIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList where sarneshin equals to DEFAULT_SARNESHIN
        defaultAdamKhesaratSarneshinShouldBeFound("sarneshin.equals=" + DEFAULT_SARNESHIN);

        // Get all the adamKhesaratSarneshinList where sarneshin equals to UPDATED_SARNESHIN
        defaultAdamKhesaratSarneshinShouldNotBeFound("sarneshin.equals=" + UPDATED_SARNESHIN);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsBySarneshinIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList where sarneshin in DEFAULT_SARNESHIN or UPDATED_SARNESHIN
        defaultAdamKhesaratSarneshinShouldBeFound("sarneshin.in=" + DEFAULT_SARNESHIN + "," + UPDATED_SARNESHIN);

        // Get all the adamKhesaratSarneshinList where sarneshin equals to UPDATED_SARNESHIN
        defaultAdamKhesaratSarneshinShouldNotBeFound("sarneshin.in=" + UPDATED_SARNESHIN);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsBySarneshinIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList where sarneshin is not null
        defaultAdamKhesaratSarneshinShouldBeFound("sarneshin.specified=true");

        // Get all the adamKhesaratSarneshinList where sarneshin is null
        defaultAdamKhesaratSarneshinShouldNotBeFound("sarneshin.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList where faal equals to DEFAULT_FAAL
        defaultAdamKhesaratSarneshinShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the adamKhesaratSarneshinList where faal equals to UPDATED_FAAL
        defaultAdamKhesaratSarneshinShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultAdamKhesaratSarneshinShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the adamKhesaratSarneshinList where faal equals to UPDATED_FAAL
        defaultAdamKhesaratSarneshinShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        // Get all the adamKhesaratSarneshinList where faal is not null
        defaultAdamKhesaratSarneshinShouldBeFound("faal.specified=true");

        // Get all the adamKhesaratSarneshinList where faal is null
        defaultAdamKhesaratSarneshinShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsByNoeSabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        NoeSabeghe noeSabeghe = NoeSabegheResourceIntTest.createEntity(em);
        em.persist(noeSabeghe);
        em.flush();
        adamKhesaratSarneshin.setNoeSabeghe(noeSabeghe);
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);
        Long noeSabegheId = noeSabeghe.getId();

        // Get all the adamKhesaratSarneshinList where noeSabeghe equals to noeSabegheId
        defaultAdamKhesaratSarneshinShouldBeFound("noeSabegheId.equals=" + noeSabegheId);

        // Get all the adamKhesaratSarneshinList where noeSabeghe equals to noeSabegheId + 1
        defaultAdamKhesaratSarneshinShouldNotBeFound("noeSabegheId.equals=" + (noeSabegheId + 1));
    }


    @Test
    @Transactional
    public void getAllAdamKhesaratSarneshinsBySabegheIsEqualToSomething() throws Exception {
        // Initialize the database
        Sabeghe sabeghe = SabegheResourceIntTest.createEntity(em);
        em.persist(sabeghe);
        em.flush();
        adamKhesaratSarneshin.setSabeghe(sabeghe);
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);
        Long sabegheId = sabeghe.getId();

        // Get all the adamKhesaratSarneshinList where sabeghe equals to sabegheId
        defaultAdamKhesaratSarneshinShouldBeFound("sabegheId.equals=" + sabegheId);

        // Get all the adamKhesaratSarneshinList where sabeghe equals to sabegheId + 1
        defaultAdamKhesaratSarneshinShouldNotBeFound("sabegheId.equals=" + (sabegheId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAdamKhesaratSarneshinShouldBeFound(String filter) throws Exception {
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adamKhesaratSarneshin.getId().intValue())))
            .andExpect(jsonPath("$.[*].sarneshin").value(hasItem(DEFAULT_SARNESHIN.doubleValue())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAdamKhesaratSarneshinShouldNotBeFound(String filter) throws Exception {
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAdamKhesaratSarneshin() throws Exception {
        // Get the adamKhesaratSarneshin
        restAdamKhesaratSarneshinMockMvc.perform(get("/api/adam-khesarat-sarneshins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdamKhesaratSarneshin() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        int databaseSizeBeforeUpdate = adamKhesaratSarneshinRepository.findAll().size();

        // Update the adamKhesaratSarneshin
        AdamKhesaratSarneshin updatedAdamKhesaratSarneshin = adamKhesaratSarneshinRepository.findById(adamKhesaratSarneshin.getId()).get();
        // Disconnect from session so that the updates on updatedAdamKhesaratSarneshin are not directly saved in db
        em.detach(updatedAdamKhesaratSarneshin);
        updatedAdamKhesaratSarneshin
            .sarneshin(UPDATED_SARNESHIN)
            .faal(UPDATED_FAAL);
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = adamKhesaratSarneshinMapper.toDto(updatedAdamKhesaratSarneshin);

        restAdamKhesaratSarneshinMockMvc.perform(put("/api/adam-khesarat-sarneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSarneshinDTO)))
            .andExpect(status().isOk());

        // Validate the AdamKhesaratSarneshin in the database
        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeUpdate);
        AdamKhesaratSarneshin testAdamKhesaratSarneshin = adamKhesaratSarneshinList.get(adamKhesaratSarneshinList.size() - 1);
        assertThat(testAdamKhesaratSarneshin.getSarneshin()).isEqualTo(UPDATED_SARNESHIN);
        assertThat(testAdamKhesaratSarneshin.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAdamKhesaratSarneshin() throws Exception {
        int databaseSizeBeforeUpdate = adamKhesaratSarneshinRepository.findAll().size();

        // Create the AdamKhesaratSarneshin
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdamKhesaratSarneshinMockMvc.perform(put("/api/adam-khesarat-sarneshins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adamKhesaratSarneshinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdamKhesaratSarneshin in the database
        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdamKhesaratSarneshin() throws Exception {
        // Initialize the database
        adamKhesaratSarneshinRepository.saveAndFlush(adamKhesaratSarneshin);

        int databaseSizeBeforeDelete = adamKhesaratSarneshinRepository.findAll().size();

        // Delete the adamKhesaratSarneshin
        restAdamKhesaratSarneshinMockMvc.perform(delete("/api/adam-khesarat-sarneshins/{id}", adamKhesaratSarneshin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdamKhesaratSarneshin> adamKhesaratSarneshinList = adamKhesaratSarneshinRepository.findAll();
        assertThat(adamKhesaratSarneshinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratSarneshin.class);
        AdamKhesaratSarneshin adamKhesaratSarneshin1 = new AdamKhesaratSarneshin();
        adamKhesaratSarneshin1.setId(1L);
        AdamKhesaratSarneshin adamKhesaratSarneshin2 = new AdamKhesaratSarneshin();
        adamKhesaratSarneshin2.setId(adamKhesaratSarneshin1.getId());
        assertThat(adamKhesaratSarneshin1).isEqualTo(adamKhesaratSarneshin2);
        adamKhesaratSarneshin2.setId(2L);
        assertThat(adamKhesaratSarneshin1).isNotEqualTo(adamKhesaratSarneshin2);
        adamKhesaratSarneshin1.setId(null);
        assertThat(adamKhesaratSarneshin1).isNotEqualTo(adamKhesaratSarneshin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdamKhesaratSarneshinDTO.class);
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO1 = new AdamKhesaratSarneshinDTO();
        adamKhesaratSarneshinDTO1.setId(1L);
        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO2 = new AdamKhesaratSarneshinDTO();
        assertThat(adamKhesaratSarneshinDTO1).isNotEqualTo(adamKhesaratSarneshinDTO2);
        adamKhesaratSarneshinDTO2.setId(adamKhesaratSarneshinDTO1.getId());
        assertThat(adamKhesaratSarneshinDTO1).isEqualTo(adamKhesaratSarneshinDTO2);
        adamKhesaratSarneshinDTO2.setId(2L);
        assertThat(adamKhesaratSarneshinDTO1).isNotEqualTo(adamKhesaratSarneshinDTO2);
        adamKhesaratSarneshinDTO1.setId(null);
        assertThat(adamKhesaratSarneshinDTO1).isNotEqualTo(adamKhesaratSarneshinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(adamKhesaratSarneshinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(adamKhesaratSarneshinMapper.fromId(null)).isNull();
    }
}
