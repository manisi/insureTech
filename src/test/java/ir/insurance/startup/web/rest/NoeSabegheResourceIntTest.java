package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.repository.NoeSabegheRepository;
import ir.insurance.startup.service.NoeSabegheService;
import ir.insurance.startup.service.dto.NoeSabegheDTO;
import ir.insurance.startup.service.mapper.NoeSabegheMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.NoeSabegheCriteria;
import ir.insurance.startup.service.NoeSabegheQueryService;

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
 * Test class for the NoeSabegheResource REST controller.
 *
 * @see NoeSabegheResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class NoeSabegheResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private NoeSabegheRepository noeSabegheRepository;

    @Autowired
    private NoeSabegheMapper noeSabegheMapper;

    @Autowired
    private NoeSabegheService noeSabegheService;

    @Autowired
    private NoeSabegheQueryService noeSabegheQueryService;

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

    private MockMvc restNoeSabegheMockMvc;

    private NoeSabeghe noeSabeghe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NoeSabegheResource noeSabegheResource = new NoeSabegheResource(noeSabegheService, noeSabegheQueryService);
        this.restNoeSabegheMockMvc = MockMvcBuilders.standaloneSetup(noeSabegheResource)
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
    public static NoeSabeghe createEntity(EntityManager em) {
        NoeSabeghe noeSabeghe = new NoeSabeghe()
            .name(DEFAULT_NAME)
            .sharh(DEFAULT_SHARH)
            .faal(DEFAULT_FAAL);
        return noeSabeghe;
    }

    @Before
    public void initTest() {
        noeSabeghe = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoeSabeghe() throws Exception {
        int databaseSizeBeforeCreate = noeSabegheRepository.findAll().size();

        // Create the NoeSabeghe
        NoeSabegheDTO noeSabegheDTO = noeSabegheMapper.toDto(noeSabeghe);
        restNoeSabegheMockMvc.perform(post("/api/noe-sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noeSabegheDTO)))
            .andExpect(status().isCreated());

        // Validate the NoeSabeghe in the database
        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeCreate + 1);
        NoeSabeghe testNoeSabeghe = noeSabegheList.get(noeSabegheList.size() - 1);
        assertThat(testNoeSabeghe.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNoeSabeghe.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testNoeSabeghe.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createNoeSabegheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noeSabegheRepository.findAll().size();

        // Create the NoeSabeghe with an existing ID
        noeSabeghe.setId(1L);
        NoeSabegheDTO noeSabegheDTO = noeSabegheMapper.toDto(noeSabeghe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoeSabegheMockMvc.perform(post("/api/noe-sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noeSabegheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoeSabeghe in the database
        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = noeSabegheRepository.findAll().size();
        // set the field null
        noeSabeghe.setName(null);

        // Create the NoeSabeghe, which fails.
        NoeSabegheDTO noeSabegheDTO = noeSabegheMapper.toDto(noeSabeghe);

        restNoeSabegheMockMvc.perform(post("/api/noe-sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noeSabegheDTO)))
            .andExpect(status().isBadRequest());

        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = noeSabegheRepository.findAll().size();
        // set the field null
        noeSabeghe.setFaal(null);

        // Create the NoeSabeghe, which fails.
        NoeSabegheDTO noeSabegheDTO = noeSabegheMapper.toDto(noeSabeghe);

        restNoeSabegheMockMvc.perform(post("/api/noe-sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noeSabegheDTO)))
            .andExpect(status().isBadRequest());

        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghes() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noeSabeghe.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getNoeSabeghe() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get the noeSabeghe
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes/{id}", noeSabeghe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noeSabeghe.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where name equals to DEFAULT_NAME
        defaultNoeSabegheShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the noeSabegheList where name equals to UPDATED_NAME
        defaultNoeSabegheShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where name in DEFAULT_NAME or UPDATED_NAME
        defaultNoeSabegheShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the noeSabegheList where name equals to UPDATED_NAME
        defaultNoeSabegheShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where name is not null
        defaultNoeSabegheShouldBeFound("name.specified=true");

        // Get all the noeSabegheList where name is null
        defaultNoeSabegheShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesBySharhIsEqualToSomething() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where sharh equals to DEFAULT_SHARH
        defaultNoeSabegheShouldBeFound("sharh.equals=" + DEFAULT_SHARH);

        // Get all the noeSabegheList where sharh equals to UPDATED_SHARH
        defaultNoeSabegheShouldNotBeFound("sharh.equals=" + UPDATED_SHARH);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesBySharhIsInShouldWork() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where sharh in DEFAULT_SHARH or UPDATED_SHARH
        defaultNoeSabegheShouldBeFound("sharh.in=" + DEFAULT_SHARH + "," + UPDATED_SHARH);

        // Get all the noeSabegheList where sharh equals to UPDATED_SHARH
        defaultNoeSabegheShouldNotBeFound("sharh.in=" + UPDATED_SHARH);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesBySharhIsNullOrNotNull() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where sharh is not null
        defaultNoeSabegheShouldBeFound("sharh.specified=true");

        // Get all the noeSabegheList where sharh is null
        defaultNoeSabegheShouldNotBeFound("sharh.specified=false");
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where faal equals to DEFAULT_FAAL
        defaultNoeSabegheShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the noeSabegheList where faal equals to UPDATED_FAAL
        defaultNoeSabegheShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultNoeSabegheShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the noeSabegheList where faal equals to UPDATED_FAAL
        defaultNoeSabegheShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllNoeSabeghesByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        // Get all the noeSabegheList where faal is not null
        defaultNoeSabegheShouldBeFound("faal.specified=true");

        // Get all the noeSabegheList where faal is null
        defaultNoeSabegheShouldNotBeFound("faal.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNoeSabegheShouldBeFound(String filter) throws Exception {
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noeSabeghe.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNoeSabegheShouldNotBeFound(String filter) throws Exception {
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingNoeSabeghe() throws Exception {
        // Get the noeSabeghe
        restNoeSabegheMockMvc.perform(get("/api/noe-sabeghes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoeSabeghe() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        int databaseSizeBeforeUpdate = noeSabegheRepository.findAll().size();

        // Update the noeSabeghe
        NoeSabeghe updatedNoeSabeghe = noeSabegheRepository.findById(noeSabeghe.getId()).get();
        // Disconnect from session so that the updates on updatedNoeSabeghe are not directly saved in db
        em.detach(updatedNoeSabeghe);
        updatedNoeSabeghe
            .name(UPDATED_NAME)
            .sharh(UPDATED_SHARH)
            .faal(UPDATED_FAAL);
        NoeSabegheDTO noeSabegheDTO = noeSabegheMapper.toDto(updatedNoeSabeghe);

        restNoeSabegheMockMvc.perform(put("/api/noe-sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noeSabegheDTO)))
            .andExpect(status().isOk());

        // Validate the NoeSabeghe in the database
        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeUpdate);
        NoeSabeghe testNoeSabeghe = noeSabegheList.get(noeSabegheList.size() - 1);
        assertThat(testNoeSabeghe.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNoeSabeghe.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testNoeSabeghe.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingNoeSabeghe() throws Exception {
        int databaseSizeBeforeUpdate = noeSabegheRepository.findAll().size();

        // Create the NoeSabeghe
        NoeSabegheDTO noeSabegheDTO = noeSabegheMapper.toDto(noeSabeghe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoeSabegheMockMvc.perform(put("/api/noe-sabeghes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noeSabegheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoeSabeghe in the database
        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNoeSabeghe() throws Exception {
        // Initialize the database
        noeSabegheRepository.saveAndFlush(noeSabeghe);

        int databaseSizeBeforeDelete = noeSabegheRepository.findAll().size();

        // Delete the noeSabeghe
        restNoeSabegheMockMvc.perform(delete("/api/noe-sabeghes/{id}", noeSabeghe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NoeSabeghe> noeSabegheList = noeSabegheRepository.findAll();
        assertThat(noeSabegheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoeSabeghe.class);
        NoeSabeghe noeSabeghe1 = new NoeSabeghe();
        noeSabeghe1.setId(1L);
        NoeSabeghe noeSabeghe2 = new NoeSabeghe();
        noeSabeghe2.setId(noeSabeghe1.getId());
        assertThat(noeSabeghe1).isEqualTo(noeSabeghe2);
        noeSabeghe2.setId(2L);
        assertThat(noeSabeghe1).isNotEqualTo(noeSabeghe2);
        noeSabeghe1.setId(null);
        assertThat(noeSabeghe1).isNotEqualTo(noeSabeghe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoeSabegheDTO.class);
        NoeSabegheDTO noeSabegheDTO1 = new NoeSabegheDTO();
        noeSabegheDTO1.setId(1L);
        NoeSabegheDTO noeSabegheDTO2 = new NoeSabegheDTO();
        assertThat(noeSabegheDTO1).isNotEqualTo(noeSabegheDTO2);
        noeSabegheDTO2.setId(noeSabegheDTO1.getId());
        assertThat(noeSabegheDTO1).isEqualTo(noeSabegheDTO2);
        noeSabegheDTO2.setId(2L);
        assertThat(noeSabegheDTO1).isNotEqualTo(noeSabegheDTO2);
        noeSabegheDTO1.setId(null);
        assertThat(noeSabegheDTO1).isNotEqualTo(noeSabegheDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(noeSabegheMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(noeSabegheMapper.fromId(null)).isNull();
    }
}
