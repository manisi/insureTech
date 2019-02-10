package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.repository.SabegheRepository;
import ir.insurance.startup.service.SabegheService;
import ir.insurance.startup.service.dto.SabegheDTO;
import ir.insurance.startup.service.mapper.SabegheMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.SabegheCriteria;
import ir.insurance.startup.service.SabegheQueryService;

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
 * Test class for the SabegheResource REST controller.
 *
 * @see SabegheResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class SabegheResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    private static final LocalDate DEFAULT_TARIKH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TARIKH = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SabegheRepository sabegheRepository;

    @Autowired
    private SabegheMapper sabegheMapper;

    @Autowired
    private SabegheService sabegheService;

    @Autowired
    private SabegheQueryService sabegheQueryService;

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

    private MockMvc restSabegheMockMvc;

    private Sabeghe sabeghe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SabegheResource sabegheResource = new SabegheResource(sabegheService, sabegheQueryService);
        this.restSabegheMockMvc = MockMvcBuilders.standaloneSetup(sabegheResource)
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
    public static Sabeghe createEntity(EntityManager em) {
        Sabeghe sabeghe = new Sabeghe()
            .name(DEFAULT_NAME)
            .sharh(DEFAULT_SHARH)
            .faal(DEFAULT_FAAL)
            .tarikh(DEFAULT_TARIKH);
        return sabeghe;
    }

    @Before
    public void initTest() {
        sabeghe = createEntity(em);
    }

    @Test
    @Transactional
    public void createSabeghe() throws Exception {
        int databaseSizeBeforeCreate = sabegheRepository.findAll().size();

        // Create the Sabeghe
        SabegheDTO sabegheDTO = sabegheMapper.toDto(sabeghe);
        restSabegheMockMvc.perform(post("/api/sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheDTO)))
            .andExpect(status().isCreated());

        // Validate the Sabeghe in the database
        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeCreate + 1);
        Sabeghe testSabeghe = sabegheList.get(sabegheList.size() - 1);
        assertThat(testSabeghe.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSabeghe.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testSabeghe.isFaal()).isEqualTo(DEFAULT_FAAL);
        assertThat(testSabeghe.getTarikh()).isEqualTo(DEFAULT_TARIKH);
    }

    @Test
    @Transactional
    public void createSabegheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sabegheRepository.findAll().size();

        // Create the Sabeghe with an existing ID
        sabeghe.setId(1L);
        SabegheDTO sabegheDTO = sabegheMapper.toDto(sabeghe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSabegheMockMvc.perform(post("/api/sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sabeghe in the database
        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sabegheRepository.findAll().size();
        // set the field null
        sabeghe.setName(null);

        // Create the Sabeghe, which fails.
        SabegheDTO sabegheDTO = sabegheMapper.toDto(sabeghe);

        restSabegheMockMvc.perform(post("/api/sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheDTO)))
            .andExpect(status().isBadRequest());

        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = sabegheRepository.findAll().size();
        // set the field null
        sabeghe.setFaal(null);

        // Create the Sabeghe, which fails.
        SabegheDTO sabegheDTO = sabegheMapper.toDto(sabeghe);

        restSabegheMockMvc.perform(post("/api/sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheDTO)))
            .andExpect(status().isBadRequest());

        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSabeghes() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList
        restSabegheMockMvc.perform(get("/api/sabeghes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sabeghe.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())))
            .andExpect(jsonPath("$.[*].tarikh").value(hasItem(DEFAULT_TARIKH.toString())));
    }
    
    @Test
    @Transactional
    public void getSabeghe() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get the sabeghe
        restSabegheMockMvc.perform(get("/api/sabeghes/{id}", sabeghe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sabeghe.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()))
            .andExpect(jsonPath("$.tarikh").value(DEFAULT_TARIKH.toString()));
    }

    @Test
    @Transactional
    public void getAllSabeghesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where name equals to DEFAULT_NAME
        defaultSabegheShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the sabegheList where name equals to UPDATED_NAME
        defaultSabegheShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllSabeghesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where name in DEFAULT_NAME or UPDATED_NAME
        defaultSabegheShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the sabegheList where name equals to UPDATED_NAME
        defaultSabegheShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllSabeghesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where name is not null
        defaultSabegheShouldBeFound("name.specified=true");

        // Get all the sabegheList where name is null
        defaultSabegheShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllSabeghesBySharhIsEqualToSomething() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where sharh equals to DEFAULT_SHARH
        defaultSabegheShouldBeFound("sharh.equals=" + DEFAULT_SHARH);

        // Get all the sabegheList where sharh equals to UPDATED_SHARH
        defaultSabegheShouldNotBeFound("sharh.equals=" + UPDATED_SHARH);
    }

    @Test
    @Transactional
    public void getAllSabeghesBySharhIsInShouldWork() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where sharh in DEFAULT_SHARH or UPDATED_SHARH
        defaultSabegheShouldBeFound("sharh.in=" + DEFAULT_SHARH + "," + UPDATED_SHARH);

        // Get all the sabegheList where sharh equals to UPDATED_SHARH
        defaultSabegheShouldNotBeFound("sharh.in=" + UPDATED_SHARH);
    }

    @Test
    @Transactional
    public void getAllSabeghesBySharhIsNullOrNotNull() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where sharh is not null
        defaultSabegheShouldBeFound("sharh.specified=true");

        // Get all the sabegheList where sharh is null
        defaultSabegheShouldNotBeFound("sharh.specified=false");
    }

    @Test
    @Transactional
    public void getAllSabeghesByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where faal equals to DEFAULT_FAAL
        defaultSabegheShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the sabegheList where faal equals to UPDATED_FAAL
        defaultSabegheShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllSabeghesByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultSabegheShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the sabegheList where faal equals to UPDATED_FAAL
        defaultSabegheShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllSabeghesByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where faal is not null
        defaultSabegheShouldBeFound("faal.specified=true");

        // Get all the sabegheList where faal is null
        defaultSabegheShouldNotBeFound("faal.specified=false");
    }

    @Test
    @Transactional
    public void getAllSabeghesByTarikhIsEqualToSomething() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where tarikh equals to DEFAULT_TARIKH
        defaultSabegheShouldBeFound("tarikh.equals=" + DEFAULT_TARIKH);

        // Get all the sabegheList where tarikh equals to UPDATED_TARIKH
        defaultSabegheShouldNotBeFound("tarikh.equals=" + UPDATED_TARIKH);
    }

    @Test
    @Transactional
    public void getAllSabeghesByTarikhIsInShouldWork() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where tarikh in DEFAULT_TARIKH or UPDATED_TARIKH
        defaultSabegheShouldBeFound("tarikh.in=" + DEFAULT_TARIKH + "," + UPDATED_TARIKH);

        // Get all the sabegheList where tarikh equals to UPDATED_TARIKH
        defaultSabegheShouldNotBeFound("tarikh.in=" + UPDATED_TARIKH);
    }

    @Test
    @Transactional
    public void getAllSabeghesByTarikhIsNullOrNotNull() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where tarikh is not null
        defaultSabegheShouldBeFound("tarikh.specified=true");

        // Get all the sabegheList where tarikh is null
        defaultSabegheShouldNotBeFound("tarikh.specified=false");
    }

    @Test
    @Transactional
    public void getAllSabeghesByTarikhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where tarikh greater than or equals to DEFAULT_TARIKH
        defaultSabegheShouldBeFound("tarikh.greaterOrEqualThan=" + DEFAULT_TARIKH);

        // Get all the sabegheList where tarikh greater than or equals to UPDATED_TARIKH
        defaultSabegheShouldNotBeFound("tarikh.greaterOrEqualThan=" + UPDATED_TARIKH);
    }

    @Test
    @Transactional
    public void getAllSabeghesByTarikhIsLessThanSomething() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        // Get all the sabegheList where tarikh less than or equals to DEFAULT_TARIKH
        defaultSabegheShouldNotBeFound("tarikh.lessThan=" + DEFAULT_TARIKH);

        // Get all the sabegheList where tarikh less than or equals to UPDATED_TARIKH
        defaultSabegheShouldBeFound("tarikh.lessThan=" + UPDATED_TARIKH);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultSabegheShouldBeFound(String filter) throws Exception {
        restSabegheMockMvc.perform(get("/api/sabeghes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sabeghe.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH)))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())))
            .andExpect(jsonPath("$.[*].tarikh").value(hasItem(DEFAULT_TARIKH.toString())));

        // Check, that the count call also returns 1
        restSabegheMockMvc.perform(get("/api/sabeghes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultSabegheShouldNotBeFound(String filter) throws Exception {
        restSabegheMockMvc.perform(get("/api/sabeghes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSabegheMockMvc.perform(get("/api/sabeghes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSabeghe() throws Exception {
        // Get the sabeghe
        restSabegheMockMvc.perform(get("/api/sabeghes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSabeghe() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        int databaseSizeBeforeUpdate = sabegheRepository.findAll().size();

        // Update the sabeghe
        Sabeghe updatedSabeghe = sabegheRepository.findById(sabeghe.getId()).get();
        // Disconnect from session so that the updates on updatedSabeghe are not directly saved in db
        em.detach(updatedSabeghe);
        updatedSabeghe
            .name(UPDATED_NAME)
            .sharh(UPDATED_SHARH)
            .faal(UPDATED_FAAL)
            .tarikh(UPDATED_TARIKH);
        SabegheDTO sabegheDTO = sabegheMapper.toDto(updatedSabeghe);

        restSabegheMockMvc.perform(put("/api/sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheDTO)))
            .andExpect(status().isOk());

        // Validate the Sabeghe in the database
        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeUpdate);
        Sabeghe testSabeghe = sabegheList.get(sabegheList.size() - 1);
        assertThat(testSabeghe.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSabeghe.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testSabeghe.isFaal()).isEqualTo(UPDATED_FAAL);
        assertThat(testSabeghe.getTarikh()).isEqualTo(UPDATED_TARIKH);
    }

    @Test
    @Transactional
    public void updateNonExistingSabeghe() throws Exception {
        int databaseSizeBeforeUpdate = sabegheRepository.findAll().size();

        // Create the Sabeghe
        SabegheDTO sabegheDTO = sabegheMapper.toDto(sabeghe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSabegheMockMvc.perform(put("/api/sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sabegheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sabeghe in the database
        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSabeghe() throws Exception {
        // Initialize the database
        sabegheRepository.saveAndFlush(sabeghe);

        int databaseSizeBeforeDelete = sabegheRepository.findAll().size();

        // Delete the sabeghe
        restSabegheMockMvc.perform(delete("/api/sabeghes/{id}", sabeghe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sabeghe> sabegheList = sabegheRepository.findAll();
        assertThat(sabegheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sabeghe.class);
        Sabeghe sabeghe1 = new Sabeghe();
        sabeghe1.setId(1L);
        Sabeghe sabeghe2 = new Sabeghe();
        sabeghe2.setId(sabeghe1.getId());
        assertThat(sabeghe1).isEqualTo(sabeghe2);
        sabeghe2.setId(2L);
        assertThat(sabeghe1).isNotEqualTo(sabeghe2);
        sabeghe1.setId(null);
        assertThat(sabeghe1).isNotEqualTo(sabeghe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SabegheDTO.class);
        SabegheDTO sabegheDTO1 = new SabegheDTO();
        sabegheDTO1.setId(1L);
        SabegheDTO sabegheDTO2 = new SabegheDTO();
        assertThat(sabegheDTO1).isNotEqualTo(sabegheDTO2);
        sabegheDTO2.setId(sabegheDTO1.getId());
        assertThat(sabegheDTO1).isEqualTo(sabegheDTO2);
        sabegheDTO2.setId(2L);
        assertThat(sabegheDTO1).isNotEqualTo(sabegheDTO2);
        sabegheDTO1.setId(null);
        assertThat(sabegheDTO1).isNotEqualTo(sabegheDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sabegheMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sabegheMapper.fromId(null)).isNull();
    }
}
