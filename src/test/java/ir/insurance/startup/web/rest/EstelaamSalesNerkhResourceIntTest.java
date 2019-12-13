package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.EstelaamSalesNerkh;
import ir.insurance.startup.repository.EstelaamSalesNerkhRepository;
import ir.insurance.startup.service.EstelaamSalesNerkhService;
import ir.insurance.startup.service.dto.EstelaamSalesNerkhDTO;
import ir.insurance.startup.service.mapper.EstelaamSalesNerkhMapper;
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
 * Test class for the EstelaamSalesNerkhResource REST controller.
 *
 * @see EstelaamSalesNerkhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class EstelaamSalesNerkhResourceIntTest {

    @Autowired
    private EstelaamSalesNerkhRepository estelaamSalesNerkhRepository;

    @Autowired
    private EstelaamSalesNerkhMapper estelaamSalesNerkhMapper;

    @Autowired
    private EstelaamSalesNerkhService estelaamSalesNerkhService;

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

    private MockMvc restEstelaamSalesNerkhMockMvc;

    private EstelaamSalesNerkh estelaamSalesNerkh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstelaamSalesNerkhResource estelaamSalesNerkhResource = new EstelaamSalesNerkhResource(estelaamSalesNerkhService);
        this.restEstelaamSalesNerkhMockMvc = MockMvcBuilders.standaloneSetup(estelaamSalesNerkhResource)
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
    public static EstelaamSalesNerkh createEntity(EntityManager em) {
        EstelaamSalesNerkh estelaamSalesNerkh = new EstelaamSalesNerkh();
        return estelaamSalesNerkh;
    }

    @Before
    public void initTest() {
        estelaamSalesNerkh = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstelaamSalesNerkh() throws Exception {
        int databaseSizeBeforeCreate = estelaamSalesNerkhRepository.findAll().size();

        // Create the EstelaamSalesNerkh
        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO = estelaamSalesNerkhMapper.toDto(estelaamSalesNerkh);
        restEstelaamSalesNerkhMockMvc.perform(post("/api/estelaam-sales-nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estelaamSalesNerkhDTO)))
            .andExpect(status().isCreated());

        // Validate the EstelaamSalesNerkh in the database
        List<EstelaamSalesNerkh> estelaamSalesNerkhList = estelaamSalesNerkhRepository.findAll();
        assertThat(estelaamSalesNerkhList).hasSize(databaseSizeBeforeCreate + 1);
        EstelaamSalesNerkh testEstelaamSalesNerkh = estelaamSalesNerkhList.get(estelaamSalesNerkhList.size() - 1);
    }

    @Test
    @Transactional
    public void createEstelaamSalesNerkhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estelaamSalesNerkhRepository.findAll().size();

        // Create the EstelaamSalesNerkh with an existing ID
        estelaamSalesNerkh.setId(1L);
        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO = estelaamSalesNerkhMapper.toDto(estelaamSalesNerkh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstelaamSalesNerkhMockMvc.perform(post("/api/estelaam-sales-nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estelaamSalesNerkhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstelaamSalesNerkh in the database
        List<EstelaamSalesNerkh> estelaamSalesNerkhList = estelaamSalesNerkhRepository.findAll();
        assertThat(estelaamSalesNerkhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEstelaamSalesNerkhs() throws Exception {
        // Initialize the database
        estelaamSalesNerkhRepository.saveAndFlush(estelaamSalesNerkh);

        // Get all the estelaamSalesNerkhList
        restEstelaamSalesNerkhMockMvc.perform(get("/api/estelaam-sales-nerkhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estelaamSalesNerkh.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getEstelaamSalesNerkh() throws Exception {
        // Initialize the database
        estelaamSalesNerkhRepository.saveAndFlush(estelaamSalesNerkh);

        // Get the estelaamSalesNerkh
        restEstelaamSalesNerkhMockMvc.perform(get("/api/estelaam-sales-nerkhs/{id}", estelaamSalesNerkh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estelaamSalesNerkh.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEstelaamSalesNerkh() throws Exception {
        // Get the estelaamSalesNerkh
        restEstelaamSalesNerkhMockMvc.perform(get("/api/estelaam-sales-nerkhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstelaamSalesNerkh() throws Exception {
        // Initialize the database
        estelaamSalesNerkhRepository.saveAndFlush(estelaamSalesNerkh);

        int databaseSizeBeforeUpdate = estelaamSalesNerkhRepository.findAll().size();

        // Update the estelaamSalesNerkh
        EstelaamSalesNerkh updatedEstelaamSalesNerkh = estelaamSalesNerkhRepository.findById(estelaamSalesNerkh.getId()).get();
        // Disconnect from session so that the updates on updatedEstelaamSalesNerkh are not directly saved in db
        em.detach(updatedEstelaamSalesNerkh);
        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO = estelaamSalesNerkhMapper.toDto(updatedEstelaamSalesNerkh);

        restEstelaamSalesNerkhMockMvc.perform(put("/api/estelaam-sales-nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estelaamSalesNerkhDTO)))
            .andExpect(status().isOk());

        // Validate the EstelaamSalesNerkh in the database
        List<EstelaamSalesNerkh> estelaamSalesNerkhList = estelaamSalesNerkhRepository.findAll();
        assertThat(estelaamSalesNerkhList).hasSize(databaseSizeBeforeUpdate);
        EstelaamSalesNerkh testEstelaamSalesNerkh = estelaamSalesNerkhList.get(estelaamSalesNerkhList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEstelaamSalesNerkh() throws Exception {
        int databaseSizeBeforeUpdate = estelaamSalesNerkhRepository.findAll().size();

        // Create the EstelaamSalesNerkh
        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO = estelaamSalesNerkhMapper.toDto(estelaamSalesNerkh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstelaamSalesNerkhMockMvc.perform(put("/api/estelaam-sales-nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estelaamSalesNerkhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstelaamSalesNerkh in the database
        List<EstelaamSalesNerkh> estelaamSalesNerkhList = estelaamSalesNerkhRepository.findAll();
        assertThat(estelaamSalesNerkhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstelaamSalesNerkh() throws Exception {
        // Initialize the database
        estelaamSalesNerkhRepository.saveAndFlush(estelaamSalesNerkh);

        int databaseSizeBeforeDelete = estelaamSalesNerkhRepository.findAll().size();

        // Delete the estelaamSalesNerkh
        restEstelaamSalesNerkhMockMvc.perform(delete("/api/estelaam-sales-nerkhs/{id}", estelaamSalesNerkh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EstelaamSalesNerkh> estelaamSalesNerkhList = estelaamSalesNerkhRepository.findAll();
        assertThat(estelaamSalesNerkhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstelaamSalesNerkh.class);
        EstelaamSalesNerkh estelaamSalesNerkh1 = new EstelaamSalesNerkh();
        estelaamSalesNerkh1.setId(1L);
        EstelaamSalesNerkh estelaamSalesNerkh2 = new EstelaamSalesNerkh();
        estelaamSalesNerkh2.setId(estelaamSalesNerkh1.getId());
        assertThat(estelaamSalesNerkh1).isEqualTo(estelaamSalesNerkh2);
        estelaamSalesNerkh2.setId(2L);
        assertThat(estelaamSalesNerkh1).isNotEqualTo(estelaamSalesNerkh2);
        estelaamSalesNerkh1.setId(null);
        assertThat(estelaamSalesNerkh1).isNotEqualTo(estelaamSalesNerkh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstelaamSalesNerkhDTO.class);
        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO1 = new EstelaamSalesNerkhDTO();
        estelaamSalesNerkhDTO1.setId(1L);
        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO2 = new EstelaamSalesNerkhDTO();
        assertThat(estelaamSalesNerkhDTO1).isNotEqualTo(estelaamSalesNerkhDTO2);
        estelaamSalesNerkhDTO2.setId(estelaamSalesNerkhDTO1.getId());
        assertThat(estelaamSalesNerkhDTO1).isEqualTo(estelaamSalesNerkhDTO2);
        estelaamSalesNerkhDTO2.setId(2L);
        assertThat(estelaamSalesNerkhDTO1).isNotEqualTo(estelaamSalesNerkhDTO2);
        estelaamSalesNerkhDTO1.setId(null);
        assertThat(estelaamSalesNerkhDTO1).isNotEqualTo(estelaamSalesNerkhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(estelaamSalesNerkhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(estelaamSalesNerkhMapper.fromId(null)).isNull();
    }
}
