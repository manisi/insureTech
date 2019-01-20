package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.MohasebeBadane;
import ir.insurance.startup.repository.MohasebeBadaneRepository;
import ir.insurance.startup.service.MohasebeBadaneService;
import ir.insurance.startup.service.dto.MohasebeBadaneDTO;
import ir.insurance.startup.service.mapper.MohasebeBadaneMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;

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
 * Test class for the MohasebeBadaneResource REST controller.
 *
 * @see MohasebeBadaneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class MohasebeBadaneResourceIntTest {

    private static final String DEFAULT_NAME_SHERKAT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SHERKAT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SALTAKHFIF = 1;
    private static final Integer UPDATED_SALTAKHFIF = 2;

    @Autowired
    private MohasebeBadaneRepository mohasebeBadaneRepository;

    @Autowired
    private MohasebeBadaneMapper mohasebeBadaneMapper;

    @Autowired
    private MohasebeBadaneService mohasebeBadaneService;

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

    private MockMvc restMohasebeBadaneMockMvc;

    private MohasebeBadane mohasebeBadane;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MohasebeBadaneResource mohasebeBadaneResource = new MohasebeBadaneResource(mohasebeBadaneService);
        this.restMohasebeBadaneMockMvc = MockMvcBuilders.standaloneSetup(mohasebeBadaneResource)
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
    public static MohasebeBadane createEntity(EntityManager em) {
        MohasebeBadane mohasebeBadane = new MohasebeBadane()
            .nameSherkat(DEFAULT_NAME_SHERKAT)
            .saltakhfif(DEFAULT_SALTAKHFIF);
        return mohasebeBadane;
    }

    @Before
    public void initTest() {
        mohasebeBadane = createEntity(em);
    }

    @Test
    @Transactional
    public void createMohasebeBadane() throws Exception {
        int databaseSizeBeforeCreate = mohasebeBadaneRepository.findAll().size();

        // Create the MohasebeBadane
        MohasebeBadaneDTO mohasebeBadaneDTO = mohasebeBadaneMapper.toDto(mohasebeBadane);
        restMohasebeBadaneMockMvc.perform(post("/api/mohasebe-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeBadaneDTO)))
            .andExpect(status().isCreated());

        // Validate the MohasebeBadane in the database
        List<MohasebeBadane> mohasebeBadaneList = mohasebeBadaneRepository.findAll();
        assertThat(mohasebeBadaneList).hasSize(databaseSizeBeforeCreate + 1);
        MohasebeBadane testMohasebeBadane = mohasebeBadaneList.get(mohasebeBadaneList.size() - 1);
        assertThat(testMohasebeBadane.getNameSherkat()).isEqualTo(DEFAULT_NAME_SHERKAT);
        assertThat(testMohasebeBadane.getSaltakhfif()).isEqualTo(DEFAULT_SALTAKHFIF);
    }

    @Test
    @Transactional
    public void createMohasebeBadaneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mohasebeBadaneRepository.findAll().size();

        // Create the MohasebeBadane with an existing ID
        mohasebeBadane.setId(1L);
        MohasebeBadaneDTO mohasebeBadaneDTO = mohasebeBadaneMapper.toDto(mohasebeBadane);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMohasebeBadaneMockMvc.perform(post("/api/mohasebe-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeBadaneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MohasebeBadane in the database
        List<MohasebeBadane> mohasebeBadaneList = mohasebeBadaneRepository.findAll();
        assertThat(mohasebeBadaneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMohasebeBadanes() throws Exception {
        // Initialize the database
        mohasebeBadaneRepository.saveAndFlush(mohasebeBadane);

        // Get all the mohasebeBadaneList
        restMohasebeBadaneMockMvc.perform(get("/api/mohasebe-badanes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mohasebeBadane.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameSherkat").value(hasItem(DEFAULT_NAME_SHERKAT.toString())))
            .andExpect(jsonPath("$.[*].saltakhfif").value(hasItem(DEFAULT_SALTAKHFIF)));
    }
    
    @Test
    @Transactional
    public void getMohasebeBadane() throws Exception {
        // Initialize the database
        mohasebeBadaneRepository.saveAndFlush(mohasebeBadane);

        // Get the mohasebeBadane
        restMohasebeBadaneMockMvc.perform(get("/api/mohasebe-badanes/{id}", mohasebeBadane.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mohasebeBadane.getId().intValue()))
            .andExpect(jsonPath("$.nameSherkat").value(DEFAULT_NAME_SHERKAT.toString()))
            .andExpect(jsonPath("$.saltakhfif").value(DEFAULT_SALTAKHFIF));
    }

    @Test
    @Transactional
    public void getNonExistingMohasebeBadane() throws Exception {
        // Get the mohasebeBadane
        restMohasebeBadaneMockMvc.perform(get("/api/mohasebe-badanes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMohasebeBadane() throws Exception {
        // Initialize the database
        mohasebeBadaneRepository.saveAndFlush(mohasebeBadane);

        int databaseSizeBeforeUpdate = mohasebeBadaneRepository.findAll().size();

        // Update the mohasebeBadane
        MohasebeBadane updatedMohasebeBadane = mohasebeBadaneRepository.findById(mohasebeBadane.getId()).get();
        // Disconnect from session so that the updates on updatedMohasebeBadane are not directly saved in db
        em.detach(updatedMohasebeBadane);
        updatedMohasebeBadane
            .nameSherkat(UPDATED_NAME_SHERKAT)
            .saltakhfif(UPDATED_SALTAKHFIF);
        MohasebeBadaneDTO mohasebeBadaneDTO = mohasebeBadaneMapper.toDto(updatedMohasebeBadane);

        restMohasebeBadaneMockMvc.perform(put("/api/mohasebe-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeBadaneDTO)))
            .andExpect(status().isOk());

        // Validate the MohasebeBadane in the database
        List<MohasebeBadane> mohasebeBadaneList = mohasebeBadaneRepository.findAll();
        assertThat(mohasebeBadaneList).hasSize(databaseSizeBeforeUpdate);
        MohasebeBadane testMohasebeBadane = mohasebeBadaneList.get(mohasebeBadaneList.size() - 1);
        assertThat(testMohasebeBadane.getNameSherkat()).isEqualTo(UPDATED_NAME_SHERKAT);
        assertThat(testMohasebeBadane.getSaltakhfif()).isEqualTo(UPDATED_SALTAKHFIF);
    }

    @Test
    @Transactional
    public void updateNonExistingMohasebeBadane() throws Exception {
        int databaseSizeBeforeUpdate = mohasebeBadaneRepository.findAll().size();

        // Create the MohasebeBadane
        MohasebeBadaneDTO mohasebeBadaneDTO = mohasebeBadaneMapper.toDto(mohasebeBadane);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMohasebeBadaneMockMvc.perform(put("/api/mohasebe-badanes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeBadaneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MohasebeBadane in the database
        List<MohasebeBadane> mohasebeBadaneList = mohasebeBadaneRepository.findAll();
        assertThat(mohasebeBadaneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMohasebeBadane() throws Exception {
        // Initialize the database
        mohasebeBadaneRepository.saveAndFlush(mohasebeBadane);

        int databaseSizeBeforeDelete = mohasebeBadaneRepository.findAll().size();

        // Get the mohasebeBadane
        restMohasebeBadaneMockMvc.perform(delete("/api/mohasebe-badanes/{id}", mohasebeBadane.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MohasebeBadane> mohasebeBadaneList = mohasebeBadaneRepository.findAll();
        assertThat(mohasebeBadaneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MohasebeBadane.class);
        MohasebeBadane mohasebeBadane1 = new MohasebeBadane();
        mohasebeBadane1.setId(1L);
        MohasebeBadane mohasebeBadane2 = new MohasebeBadane();
        mohasebeBadane2.setId(mohasebeBadane1.getId());
        assertThat(mohasebeBadane1).isEqualTo(mohasebeBadane2);
        mohasebeBadane2.setId(2L);
        assertThat(mohasebeBadane1).isNotEqualTo(mohasebeBadane2);
        mohasebeBadane1.setId(null);
        assertThat(mohasebeBadane1).isNotEqualTo(mohasebeBadane2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MohasebeBadaneDTO.class);
        MohasebeBadaneDTO mohasebeBadaneDTO1 = new MohasebeBadaneDTO();
        mohasebeBadaneDTO1.setId(1L);
        MohasebeBadaneDTO mohasebeBadaneDTO2 = new MohasebeBadaneDTO();
        assertThat(mohasebeBadaneDTO1).isNotEqualTo(mohasebeBadaneDTO2);
        mohasebeBadaneDTO2.setId(mohasebeBadaneDTO1.getId());
        assertThat(mohasebeBadaneDTO1).isEqualTo(mohasebeBadaneDTO2);
        mohasebeBadaneDTO2.setId(2L);
        assertThat(mohasebeBadaneDTO1).isNotEqualTo(mohasebeBadaneDTO2);
        mohasebeBadaneDTO1.setId(null);
        assertThat(mohasebeBadaneDTO1).isNotEqualTo(mohasebeBadaneDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mohasebeBadaneMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mohasebeBadaneMapper.fromId(null)).isNull();
    }
}
