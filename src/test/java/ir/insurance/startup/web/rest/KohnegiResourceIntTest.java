package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.Kohnegi;
import ir.insurance.startup.repository.KohnegiRepository;
import ir.insurance.startup.service.KohnegiService;
import ir.insurance.startup.service.dto.KohnegiDTO;
import ir.insurance.startup.service.mapper.KohnegiMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.KohnegiCriteria;
import ir.insurance.startup.service.KohnegiQueryService;

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
 * Test class for the KohnegiResource REST controller.
 *
 * @see KohnegiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class KohnegiResourceIntTest {

    private static final Float DEFAULT_DARSAD_HAR_SAL = 1F;
    private static final Float UPDATED_DARSAD_HAR_SAL = 2F;

    private static final Float DEFAULT_MAX_DARSAD = 0F;
    private static final Float UPDATED_MAX_DARSAD = 1F;

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private KohnegiRepository kohnegiRepository;

    @Autowired
    private KohnegiMapper kohnegiMapper;

    @Autowired
    private KohnegiService kohnegiService;

    @Autowired
    private KohnegiQueryService kohnegiQueryService;

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

    private MockMvc restKohnegiMockMvc;

    private Kohnegi kohnegi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KohnegiResource kohnegiResource = new KohnegiResource(kohnegiService, kohnegiQueryService);
        this.restKohnegiMockMvc = MockMvcBuilders.standaloneSetup(kohnegiResource)
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
    public static Kohnegi createEntity(EntityManager em) {
        Kohnegi kohnegi = new Kohnegi()
            .darsadHarSal(DEFAULT_DARSAD_HAR_SAL)
            .maxDarsad(DEFAULT_MAX_DARSAD)
            .sharh(DEFAULT_SHARH)
            .faal(DEFAULT_FAAL);
        return kohnegi;
    }

    @Before
    public void initTest() {
        kohnegi = createEntity(em);
    }

    @Test
    @Transactional
    public void createKohnegi() throws Exception {
        int databaseSizeBeforeCreate = kohnegiRepository.findAll().size();

        // Create the Kohnegi
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(kohnegi);
        restKohnegiMockMvc.perform(post("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isCreated());

        // Validate the Kohnegi in the database
        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeCreate + 1);
        Kohnegi testKohnegi = kohnegiList.get(kohnegiList.size() - 1);
        assertThat(testKohnegi.getDarsadHarSal()).isEqualTo(DEFAULT_DARSAD_HAR_SAL);
        assertThat(testKohnegi.getMaxDarsad()).isEqualTo(DEFAULT_MAX_DARSAD);
        assertThat(testKohnegi.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testKohnegi.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createKohnegiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kohnegiRepository.findAll().size();

        // Create the Kohnegi with an existing ID
        kohnegi.setId(1L);
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(kohnegi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKohnegiMockMvc.perform(post("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Kohnegi in the database
        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDarsadHarSalIsRequired() throws Exception {
        int databaseSizeBeforeTest = kohnegiRepository.findAll().size();
        // set the field null
        kohnegi.setDarsadHarSal(null);

        // Create the Kohnegi, which fails.
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(kohnegi);

        restKohnegiMockMvc.perform(post("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isBadRequest());

        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxDarsadIsRequired() throws Exception {
        int databaseSizeBeforeTest = kohnegiRepository.findAll().size();
        // set the field null
        kohnegi.setMaxDarsad(null);

        // Create the Kohnegi, which fails.
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(kohnegi);

        restKohnegiMockMvc.perform(post("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isBadRequest());

        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = kohnegiRepository.findAll().size();
        // set the field null
        kohnegi.setFaal(null);

        // Create the Kohnegi, which fails.
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(kohnegi);

        restKohnegiMockMvc.perform(post("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isBadRequest());

        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKohnegis() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList
        restKohnegiMockMvc.perform(get("/api/kohnegis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kohnegi.getId().intValue())))
            .andExpect(jsonPath("$.[*].darsadHarSal").value(hasItem(DEFAULT_DARSAD_HAR_SAL.doubleValue())))
            .andExpect(jsonPath("$.[*].maxDarsad").value(hasItem(DEFAULT_MAX_DARSAD.doubleValue())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKohnegi() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get the kohnegi
        restKohnegiMockMvc.perform(get("/api/kohnegis/{id}", kohnegi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kohnegi.getId().intValue()))
            .andExpect(jsonPath("$.darsadHarSal").value(DEFAULT_DARSAD_HAR_SAL.doubleValue()))
            .andExpect(jsonPath("$.maxDarsad").value(DEFAULT_MAX_DARSAD.doubleValue()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllKohnegisByDarsadHarSalIsEqualToSomething() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where darsadHarSal equals to DEFAULT_DARSAD_HAR_SAL
        defaultKohnegiShouldBeFound("darsadHarSal.equals=" + DEFAULT_DARSAD_HAR_SAL);

        // Get all the kohnegiList where darsadHarSal equals to UPDATED_DARSAD_HAR_SAL
        defaultKohnegiShouldNotBeFound("darsadHarSal.equals=" + UPDATED_DARSAD_HAR_SAL);
    }

    @Test
    @Transactional
    public void getAllKohnegisByDarsadHarSalIsInShouldWork() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where darsadHarSal in DEFAULT_DARSAD_HAR_SAL or UPDATED_DARSAD_HAR_SAL
        defaultKohnegiShouldBeFound("darsadHarSal.in=" + DEFAULT_DARSAD_HAR_SAL + "," + UPDATED_DARSAD_HAR_SAL);

        // Get all the kohnegiList where darsadHarSal equals to UPDATED_DARSAD_HAR_SAL
        defaultKohnegiShouldNotBeFound("darsadHarSal.in=" + UPDATED_DARSAD_HAR_SAL);
    }

    @Test
    @Transactional
    public void getAllKohnegisByDarsadHarSalIsNullOrNotNull() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where darsadHarSal is not null
        defaultKohnegiShouldBeFound("darsadHarSal.specified=true");

        // Get all the kohnegiList where darsadHarSal is null
        defaultKohnegiShouldNotBeFound("darsadHarSal.specified=false");
    }

    @Test
    @Transactional
    public void getAllKohnegisByMaxDarsadIsEqualToSomething() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where maxDarsad equals to DEFAULT_MAX_DARSAD
        defaultKohnegiShouldBeFound("maxDarsad.equals=" + DEFAULT_MAX_DARSAD);

        // Get all the kohnegiList where maxDarsad equals to UPDATED_MAX_DARSAD
        defaultKohnegiShouldNotBeFound("maxDarsad.equals=" + UPDATED_MAX_DARSAD);
    }

    @Test
    @Transactional
    public void getAllKohnegisByMaxDarsadIsInShouldWork() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where maxDarsad in DEFAULT_MAX_DARSAD or UPDATED_MAX_DARSAD
        defaultKohnegiShouldBeFound("maxDarsad.in=" + DEFAULT_MAX_DARSAD + "," + UPDATED_MAX_DARSAD);

        // Get all the kohnegiList where maxDarsad equals to UPDATED_MAX_DARSAD
        defaultKohnegiShouldNotBeFound("maxDarsad.in=" + UPDATED_MAX_DARSAD);
    }

    @Test
    @Transactional
    public void getAllKohnegisByMaxDarsadIsNullOrNotNull() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where maxDarsad is not null
        defaultKohnegiShouldBeFound("maxDarsad.specified=true");

        // Get all the kohnegiList where maxDarsad is null
        defaultKohnegiShouldNotBeFound("maxDarsad.specified=false");
    }

    @Test
    @Transactional
    public void getAllKohnegisByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where faal equals to DEFAULT_FAAL
        defaultKohnegiShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the kohnegiList where faal equals to UPDATED_FAAL
        defaultKohnegiShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKohnegisByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultKohnegiShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the kohnegiList where faal equals to UPDATED_FAAL
        defaultKohnegiShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllKohnegisByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        // Get all the kohnegiList where faal is not null
        defaultKohnegiShouldBeFound("faal.specified=true");

        // Get all the kohnegiList where faal is null
        defaultKohnegiShouldNotBeFound("faal.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultKohnegiShouldBeFound(String filter) throws Exception {
        restKohnegiMockMvc.perform(get("/api/kohnegis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kohnegi.getId().intValue())))
            .andExpect(jsonPath("$.[*].darsadHarSal").value(hasItem(DEFAULT_DARSAD_HAR_SAL.doubleValue())))
            .andExpect(jsonPath("$.[*].maxDarsad").value(hasItem(DEFAULT_MAX_DARSAD.doubleValue())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restKohnegiMockMvc.perform(get("/api/kohnegis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultKohnegiShouldNotBeFound(String filter) throws Exception {
        restKohnegiMockMvc.perform(get("/api/kohnegis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restKohnegiMockMvc.perform(get("/api/kohnegis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingKohnegi() throws Exception {
        // Get the kohnegi
        restKohnegiMockMvc.perform(get("/api/kohnegis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKohnegi() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        int databaseSizeBeforeUpdate = kohnegiRepository.findAll().size();

        // Update the kohnegi
        Kohnegi updatedKohnegi = kohnegiRepository.findById(kohnegi.getId()).get();
        // Disconnect from session so that the updates on updatedKohnegi are not directly saved in db
        em.detach(updatedKohnegi);
        updatedKohnegi
            .darsadHarSal(UPDATED_DARSAD_HAR_SAL)
            .maxDarsad(UPDATED_MAX_DARSAD)
            .sharh(UPDATED_SHARH)
            .faal(UPDATED_FAAL);
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(updatedKohnegi);

        restKohnegiMockMvc.perform(put("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isOk());

        // Validate the Kohnegi in the database
        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeUpdate);
        Kohnegi testKohnegi = kohnegiList.get(kohnegiList.size() - 1);
        assertThat(testKohnegi.getDarsadHarSal()).isEqualTo(UPDATED_DARSAD_HAR_SAL);
        assertThat(testKohnegi.getMaxDarsad()).isEqualTo(UPDATED_MAX_DARSAD);
        assertThat(testKohnegi.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testKohnegi.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingKohnegi() throws Exception {
        int databaseSizeBeforeUpdate = kohnegiRepository.findAll().size();

        // Create the Kohnegi
        KohnegiDTO kohnegiDTO = kohnegiMapper.toDto(kohnegi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKohnegiMockMvc.perform(put("/api/kohnegis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kohnegiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Kohnegi in the database
        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKohnegi() throws Exception {
        // Initialize the database
        kohnegiRepository.saveAndFlush(kohnegi);

        int databaseSizeBeforeDelete = kohnegiRepository.findAll().size();

        // Get the kohnegi
        restKohnegiMockMvc.perform(delete("/api/kohnegis/{id}", kohnegi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Kohnegi> kohnegiList = kohnegiRepository.findAll();
        assertThat(kohnegiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kohnegi.class);
        Kohnegi kohnegi1 = new Kohnegi();
        kohnegi1.setId(1L);
        Kohnegi kohnegi2 = new Kohnegi();
        kohnegi2.setId(kohnegi1.getId());
        assertThat(kohnegi1).isEqualTo(kohnegi2);
        kohnegi2.setId(2L);
        assertThat(kohnegi1).isNotEqualTo(kohnegi2);
        kohnegi1.setId(null);
        assertThat(kohnegi1).isNotEqualTo(kohnegi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KohnegiDTO.class);
        KohnegiDTO kohnegiDTO1 = new KohnegiDTO();
        kohnegiDTO1.setId(1L);
        KohnegiDTO kohnegiDTO2 = new KohnegiDTO();
        assertThat(kohnegiDTO1).isNotEqualTo(kohnegiDTO2);
        kohnegiDTO2.setId(kohnegiDTO1.getId());
        assertThat(kohnegiDTO1).isEqualTo(kohnegiDTO2);
        kohnegiDTO2.setId(2L);
        assertThat(kohnegiDTO1).isNotEqualTo(kohnegiDTO2);
        kohnegiDTO1.setId(null);
        assertThat(kohnegiDTO1).isNotEqualTo(kohnegiDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(kohnegiMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(kohnegiMapper.fromId(null)).isNull();
    }
}
