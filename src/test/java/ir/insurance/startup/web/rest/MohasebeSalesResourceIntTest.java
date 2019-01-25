package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.MohasebeSales;
import ir.insurance.startup.repository.MohasebeSalesRepository;
import ir.insurance.startup.service.MohasebeSalesService;
import ir.insurance.startup.service.dto.MohasebeSalesDTO;
import ir.insurance.startup.service.mapper.MohasebeSalesMapper;
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
 * Test class for the MohasebeSalesResource REST controller.
 *
 * @see MohasebeSalesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class MohasebeSalesResourceIntTest {

    private static final String DEFAULT_NAME_SHERKAT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_SHERKAT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SALTAKHFIF = 1;
    private static final Integer UPDATED_SALTAKHFIF = 2;

    @Autowired
    private MohasebeSalesRepository mohasebeSalesRepository;

    @Autowired
    private MohasebeSalesMapper mohasebeSalesMapper;

    @Autowired
    private MohasebeSalesService mohasebeSalesService;

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

    private MockMvc restMohasebeSalesMockMvc;

    private MohasebeSales mohasebeSales;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MohasebeSalesResource mohasebeSalesResource = new MohasebeSalesResource(mohasebeSalesService);
        this.restMohasebeSalesMockMvc = MockMvcBuilders.standaloneSetup(mohasebeSalesResource)
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
    public static MohasebeSales createEntity(EntityManager em) {
        MohasebeSales mohasebeSales = new MohasebeSales()
            .nameSherkat(DEFAULT_NAME_SHERKAT)
            .saltakhfif(DEFAULT_SALTAKHFIF);
        return mohasebeSales;
    }

    @Before
    public void initTest() {
        mohasebeSales = createEntity(em);
    }

    @Test
    @Transactional
    public void createMohasebeSales() throws Exception {
        int databaseSizeBeforeCreate = mohasebeSalesRepository.findAll().size();

        // Create the MohasebeSales
        MohasebeSalesDTO mohasebeSalesDTO = mohasebeSalesMapper.toDto(mohasebeSales);
        restMohasebeSalesMockMvc.perform(post("/api/mohasebe-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeSalesDTO)))
            .andExpect(status().isCreated());

        // Validate the MohasebeSales in the database
        List<MohasebeSales> mohasebeSalesList = mohasebeSalesRepository.findAll();
        assertThat(mohasebeSalesList).hasSize(databaseSizeBeforeCreate + 1);
        MohasebeSales testMohasebeSales = mohasebeSalesList.get(mohasebeSalesList.size() - 1);
        assertThat(testMohasebeSales.getNameSherkat()).isEqualTo(DEFAULT_NAME_SHERKAT);
        assertThat(testMohasebeSales.getSaltakhfif()).isEqualTo(DEFAULT_SALTAKHFIF);
    }

    @Test
    @Transactional
    public void createMohasebeSalesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mohasebeSalesRepository.findAll().size();

        // Create the MohasebeSales with an existing ID
        mohasebeSales.setId(1L);
        MohasebeSalesDTO mohasebeSalesDTO = mohasebeSalesMapper.toDto(mohasebeSales);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMohasebeSalesMockMvc.perform(post("/api/mohasebe-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MohasebeSales in the database
        List<MohasebeSales> mohasebeSalesList = mohasebeSalesRepository.findAll();
        assertThat(mohasebeSalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMohasebeSales() throws Exception {
        // Initialize the database
        mohasebeSalesRepository.saveAndFlush(mohasebeSales);

        // Get all the mohasebeSalesList
        restMohasebeSalesMockMvc.perform(get("/api/mohasebe-sales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mohasebeSales.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameSherkat").value(hasItem(DEFAULT_NAME_SHERKAT.toString())))
            .andExpect(jsonPath("$.[*].saltakhfif").value(hasItem(DEFAULT_SALTAKHFIF)));
    }
    
    @Test
    @Transactional
    public void getMohasebeSales() throws Exception {
        // Initialize the database
        mohasebeSalesRepository.saveAndFlush(mohasebeSales);

        // Get the mohasebeSales
        restMohasebeSalesMockMvc.perform(get("/api/mohasebe-sales/{id}", mohasebeSales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mohasebeSales.getId().intValue()))
            .andExpect(jsonPath("$.nameSherkat").value(DEFAULT_NAME_SHERKAT.toString()))
            .andExpect(jsonPath("$.saltakhfif").value(DEFAULT_SALTAKHFIF));
    }

    @Test
    @Transactional
    public void getNonExistingMohasebeSales() throws Exception {
        // Get the mohasebeSales
        restMohasebeSalesMockMvc.perform(get("/api/mohasebe-sales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMohasebeSales() throws Exception {
        // Initialize the database
        mohasebeSalesRepository.saveAndFlush(mohasebeSales);

        int databaseSizeBeforeUpdate = mohasebeSalesRepository.findAll().size();

        // Update the mohasebeSales
        MohasebeSales updatedMohasebeSales = mohasebeSalesRepository.findById(mohasebeSales.getId()).get();
        // Disconnect from session so that the updates on updatedMohasebeSales are not directly saved in db
        em.detach(updatedMohasebeSales);
        updatedMohasebeSales
            .nameSherkat(UPDATED_NAME_SHERKAT)
            .saltakhfif(UPDATED_SALTAKHFIF);
        MohasebeSalesDTO mohasebeSalesDTO = mohasebeSalesMapper.toDto(updatedMohasebeSales);

        restMohasebeSalesMockMvc.perform(put("/api/mohasebe-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeSalesDTO)))
            .andExpect(status().isOk());

        // Validate the MohasebeSales in the database
        List<MohasebeSales> mohasebeSalesList = mohasebeSalesRepository.findAll();
        assertThat(mohasebeSalesList).hasSize(databaseSizeBeforeUpdate);
        MohasebeSales testMohasebeSales = mohasebeSalesList.get(mohasebeSalesList.size() - 1);
        assertThat(testMohasebeSales.getNameSherkat()).isEqualTo(UPDATED_NAME_SHERKAT);
        assertThat(testMohasebeSales.getSaltakhfif()).isEqualTo(UPDATED_SALTAKHFIF);
    }

    @Test
    @Transactional
    public void updateNonExistingMohasebeSales() throws Exception {
        int databaseSizeBeforeUpdate = mohasebeSalesRepository.findAll().size();

        // Create the MohasebeSales
        MohasebeSalesDTO mohasebeSalesDTO = mohasebeSalesMapper.toDto(mohasebeSales);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMohasebeSalesMockMvc.perform(put("/api/mohasebe-sales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mohasebeSalesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MohasebeSales in the database
        List<MohasebeSales> mohasebeSalesList = mohasebeSalesRepository.findAll();
        assertThat(mohasebeSalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMohasebeSales() throws Exception {
        // Initialize the database
        mohasebeSalesRepository.saveAndFlush(mohasebeSales);

        int databaseSizeBeforeDelete = mohasebeSalesRepository.findAll().size();

        // Get the mohasebeSales
        restMohasebeSalesMockMvc.perform(delete("/api/mohasebe-sales/{id}", mohasebeSales.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MohasebeSales> mohasebeSalesList = mohasebeSalesRepository.findAll();
        assertThat(mohasebeSalesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MohasebeSales.class);
        MohasebeSales mohasebeSales1 = new MohasebeSales();
        mohasebeSales1.setId(1L);
        MohasebeSales mohasebeSales2 = new MohasebeSales();
        mohasebeSales2.setId(mohasebeSales1.getId());
        assertThat(mohasebeSales1).isEqualTo(mohasebeSales2);
        mohasebeSales2.setId(2L);
        assertThat(mohasebeSales1).isNotEqualTo(mohasebeSales2);
        mohasebeSales1.setId(null);
        assertThat(mohasebeSales1).isNotEqualTo(mohasebeSales2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MohasebeSalesDTO.class);
        MohasebeSalesDTO mohasebeSalesDTO1 = new MohasebeSalesDTO();
        mohasebeSalesDTO1.setId(1L);
        MohasebeSalesDTO mohasebeSalesDTO2 = new MohasebeSalesDTO();
        assertThat(mohasebeSalesDTO1).isNotEqualTo(mohasebeSalesDTO2);
        mohasebeSalesDTO2.setId(mohasebeSalesDTO1.getId());
        assertThat(mohasebeSalesDTO1).isEqualTo(mohasebeSalesDTO2);
        mohasebeSalesDTO2.setId(2L);
        assertThat(mohasebeSalesDTO1).isNotEqualTo(mohasebeSalesDTO2);
        mohasebeSalesDTO1.setId(null);
        assertThat(mohasebeSalesDTO1).isNotEqualTo(mohasebeSalesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mohasebeSalesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mohasebeSalesMapper.fromId(null)).isNull();
    }
}
