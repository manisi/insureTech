package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.GrouhKhodroRepository;
import ir.insurance.startup.service.GrouhKhodroService;
import ir.insurance.startup.service.dto.GrouhKhodroDTO;
import ir.insurance.startup.service.mapper.GrouhKhodroMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.GrouhKhodroCriteria;
import ir.insurance.startup.service.GrouhKhodroQueryService;

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
 * Test class for the GrouhKhodroResource REST controller.
 *
 * @see GrouhKhodroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class GrouhKhodroResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private GrouhKhodroRepository grouhKhodroRepository;

    @Autowired
    private GrouhKhodroMapper grouhKhodroMapper;

    @Autowired
    private GrouhKhodroService grouhKhodroService;

    @Autowired
    private GrouhKhodroQueryService grouhKhodroQueryService;

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

    private MockMvc restGrouhKhodroMockMvc;

    private GrouhKhodro grouhKhodro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrouhKhodroResource grouhKhodroResource = new GrouhKhodroResource(grouhKhodroService, grouhKhodroQueryService);
        this.restGrouhKhodroMockMvc = MockMvcBuilders.standaloneSetup(grouhKhodroResource)
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
    public static GrouhKhodro createEntity(EntityManager em) {
        GrouhKhodro grouhKhodro = new GrouhKhodro()
            .name(DEFAULT_NAME)
            .faal(DEFAULT_FAAL);
        return grouhKhodro;
    }

    @Before
    public void initTest() {
        grouhKhodro = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrouhKhodro() throws Exception {
        int databaseSizeBeforeCreate = grouhKhodroRepository.findAll().size();

        // Create the GrouhKhodro
        GrouhKhodroDTO grouhKhodroDTO = grouhKhodroMapper.toDto(grouhKhodro);
        restGrouhKhodroMockMvc.perform(post("/api/grouh-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grouhKhodroDTO)))
            .andExpect(status().isCreated());

        // Validate the GrouhKhodro in the database
        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeCreate + 1);
        GrouhKhodro testGrouhKhodro = grouhKhodroList.get(grouhKhodroList.size() - 1);
        assertThat(testGrouhKhodro.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGrouhKhodro.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createGrouhKhodroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grouhKhodroRepository.findAll().size();

        // Create the GrouhKhodro with an existing ID
        grouhKhodro.setId(1L);
        GrouhKhodroDTO grouhKhodroDTO = grouhKhodroMapper.toDto(grouhKhodro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrouhKhodroMockMvc.perform(post("/api/grouh-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grouhKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrouhKhodro in the database
        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = grouhKhodroRepository.findAll().size();
        // set the field null
        grouhKhodro.setName(null);

        // Create the GrouhKhodro, which fails.
        GrouhKhodroDTO grouhKhodroDTO = grouhKhodroMapper.toDto(grouhKhodro);

        restGrouhKhodroMockMvc.perform(post("/api/grouh-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grouhKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = grouhKhodroRepository.findAll().size();
        // set the field null
        grouhKhodro.setFaal(null);

        // Create the GrouhKhodro, which fails.
        GrouhKhodroDTO grouhKhodroDTO = grouhKhodroMapper.toDto(grouhKhodro);

        restGrouhKhodroMockMvc.perform(post("/api/grouh-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grouhKhodroDTO)))
            .andExpect(status().isBadRequest());

        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGrouhKhodros() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grouhKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getGrouhKhodro() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get the grouhKhodro
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros/{id}", grouhKhodro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grouhKhodro.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getAllGrouhKhodrosByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList where name equals to DEFAULT_NAME
        defaultGrouhKhodroShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the grouhKhodroList where name equals to UPDATED_NAME
        defaultGrouhKhodroShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGrouhKhodrosByNameIsInShouldWork() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList where name in DEFAULT_NAME or UPDATED_NAME
        defaultGrouhKhodroShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the grouhKhodroList where name equals to UPDATED_NAME
        defaultGrouhKhodroShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllGrouhKhodrosByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList where name is not null
        defaultGrouhKhodroShouldBeFound("name.specified=true");

        // Get all the grouhKhodroList where name is null
        defaultGrouhKhodroShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllGrouhKhodrosByFaalIsEqualToSomething() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList where faal equals to DEFAULT_FAAL
        defaultGrouhKhodroShouldBeFound("faal.equals=" + DEFAULT_FAAL);

        // Get all the grouhKhodroList where faal equals to UPDATED_FAAL
        defaultGrouhKhodroShouldNotBeFound("faal.equals=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllGrouhKhodrosByFaalIsInShouldWork() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList where faal in DEFAULT_FAAL or UPDATED_FAAL
        defaultGrouhKhodroShouldBeFound("faal.in=" + DEFAULT_FAAL + "," + UPDATED_FAAL);

        // Get all the grouhKhodroList where faal equals to UPDATED_FAAL
        defaultGrouhKhodroShouldNotBeFound("faal.in=" + UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void getAllGrouhKhodrosByFaalIsNullOrNotNull() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        // Get all the grouhKhodroList where faal is not null
        defaultGrouhKhodroShouldBeFound("faal.specified=true");

        // Get all the grouhKhodroList where faal is null
        defaultGrouhKhodroShouldNotBeFound("faal.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultGrouhKhodroShouldBeFound(String filter) throws Exception {
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grouhKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));

        // Check, that the count call also returns 1
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultGrouhKhodroShouldNotBeFound(String filter) throws Exception {
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingGrouhKhodro() throws Exception {
        // Get the grouhKhodro
        restGrouhKhodroMockMvc.perform(get("/api/grouh-khodros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrouhKhodro() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        int databaseSizeBeforeUpdate = grouhKhodroRepository.findAll().size();

        // Update the grouhKhodro
        GrouhKhodro updatedGrouhKhodro = grouhKhodroRepository.findById(grouhKhodro.getId()).get();
        // Disconnect from session so that the updates on updatedGrouhKhodro are not directly saved in db
        em.detach(updatedGrouhKhodro);
        updatedGrouhKhodro
            .name(UPDATED_NAME)
            .faal(UPDATED_FAAL);
        GrouhKhodroDTO grouhKhodroDTO = grouhKhodroMapper.toDto(updatedGrouhKhodro);

        restGrouhKhodroMockMvc.perform(put("/api/grouh-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grouhKhodroDTO)))
            .andExpect(status().isOk());

        // Validate the GrouhKhodro in the database
        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeUpdate);
        GrouhKhodro testGrouhKhodro = grouhKhodroList.get(grouhKhodroList.size() - 1);
        assertThat(testGrouhKhodro.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGrouhKhodro.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingGrouhKhodro() throws Exception {
        int databaseSizeBeforeUpdate = grouhKhodroRepository.findAll().size();

        // Create the GrouhKhodro
        GrouhKhodroDTO grouhKhodroDTO = grouhKhodroMapper.toDto(grouhKhodro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrouhKhodroMockMvc.perform(put("/api/grouh-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grouhKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrouhKhodro in the database
        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrouhKhodro() throws Exception {
        // Initialize the database
        grouhKhodroRepository.saveAndFlush(grouhKhodro);

        int databaseSizeBeforeDelete = grouhKhodroRepository.findAll().size();

        // Delete the grouhKhodro
        restGrouhKhodroMockMvc.perform(delete("/api/grouh-khodros/{id}", grouhKhodro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GrouhKhodro> grouhKhodroList = grouhKhodroRepository.findAll();
        assertThat(grouhKhodroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrouhKhodro.class);
        GrouhKhodro grouhKhodro1 = new GrouhKhodro();
        grouhKhodro1.setId(1L);
        GrouhKhodro grouhKhodro2 = new GrouhKhodro();
        grouhKhodro2.setId(grouhKhodro1.getId());
        assertThat(grouhKhodro1).isEqualTo(grouhKhodro2);
        grouhKhodro2.setId(2L);
        assertThat(grouhKhodro1).isNotEqualTo(grouhKhodro2);
        grouhKhodro1.setId(null);
        assertThat(grouhKhodro1).isNotEqualTo(grouhKhodro2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrouhKhodroDTO.class);
        GrouhKhodroDTO grouhKhodroDTO1 = new GrouhKhodroDTO();
        grouhKhodroDTO1.setId(1L);
        GrouhKhodroDTO grouhKhodroDTO2 = new GrouhKhodroDTO();
        assertThat(grouhKhodroDTO1).isNotEqualTo(grouhKhodroDTO2);
        grouhKhodroDTO2.setId(grouhKhodroDTO1.getId());
        assertThat(grouhKhodroDTO1).isEqualTo(grouhKhodroDTO2);
        grouhKhodroDTO2.setId(2L);
        assertThat(grouhKhodroDTO1).isNotEqualTo(grouhKhodroDTO2);
        grouhKhodroDTO1.setId(null);
        assertThat(grouhKhodroDTO1).isNotEqualTo(grouhKhodroDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(grouhKhodroMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(grouhKhodroMapper.fromId(null)).isNull();
    }
}
