package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.ModateBimename;
import ir.insurance.startup.repository.ModateBimenameRepository;
import ir.insurance.startup.service.ModateBimenameService;
import ir.insurance.startup.service.dto.ModateBimenameDTO;
import ir.insurance.startup.service.mapper.ModateBimenameMapper;
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
 * Test class for the ModateBimenameResource REST controller.
 *
 * @see ModateBimenameResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class ModateBimenameResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHARH = "AAAAAAAAAA";
    private static final String UPDATED_SHARH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    @Autowired
    private ModateBimenameRepository modateBimenameRepository;

    @Autowired
    private ModateBimenameMapper modateBimenameMapper;

    @Autowired
    private ModateBimenameService modateBimenameService;

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

    private MockMvc restModateBimenameMockMvc;

    private ModateBimename modateBimename;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModateBimenameResource modateBimenameResource = new ModateBimenameResource(modateBimenameService);
        this.restModateBimenameMockMvc = MockMvcBuilders.standaloneSetup(modateBimenameResource)
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
    public static ModateBimename createEntity(EntityManager em) {
        ModateBimename modateBimename = new ModateBimename()
            .name(DEFAULT_NAME)
            .sharh(DEFAULT_SHARH)
            .faal(DEFAULT_FAAL);
        return modateBimename;
    }

    @Before
    public void initTest() {
        modateBimename = createEntity(em);
    }

    @Test
    @Transactional
    public void createModateBimename() throws Exception {
        int databaseSizeBeforeCreate = modateBimenameRepository.findAll().size();

        // Create the ModateBimename
        ModateBimenameDTO modateBimenameDTO = modateBimenameMapper.toDto(modateBimename);
        restModateBimenameMockMvc.perform(post("/api/modate-bimenames")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modateBimenameDTO)))
            .andExpect(status().isCreated());

        // Validate the ModateBimename in the database
        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeCreate + 1);
        ModateBimename testModateBimename = modateBimenameList.get(modateBimenameList.size() - 1);
        assertThat(testModateBimename.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testModateBimename.getSharh()).isEqualTo(DEFAULT_SHARH);
        assertThat(testModateBimename.isFaal()).isEqualTo(DEFAULT_FAAL);
    }

    @Test
    @Transactional
    public void createModateBimenameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modateBimenameRepository.findAll().size();

        // Create the ModateBimename with an existing ID
        modateBimename.setId(1L);
        ModateBimenameDTO modateBimenameDTO = modateBimenameMapper.toDto(modateBimename);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModateBimenameMockMvc.perform(post("/api/modate-bimenames")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modateBimenameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModateBimename in the database
        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = modateBimenameRepository.findAll().size();
        // set the field null
        modateBimename.setName(null);

        // Create the ModateBimename, which fails.
        ModateBimenameDTO modateBimenameDTO = modateBimenameMapper.toDto(modateBimename);

        restModateBimenameMockMvc.perform(post("/api/modate-bimenames")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modateBimenameDTO)))
            .andExpect(status().isBadRequest());

        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFaalIsRequired() throws Exception {
        int databaseSizeBeforeTest = modateBimenameRepository.findAll().size();
        // set the field null
        modateBimename.setFaal(null);

        // Create the ModateBimename, which fails.
        ModateBimenameDTO modateBimenameDTO = modateBimenameMapper.toDto(modateBimename);

        restModateBimenameMockMvc.perform(post("/api/modate-bimenames")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modateBimenameDTO)))
            .andExpect(status().isBadRequest());

        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModateBimenames() throws Exception {
        // Initialize the database
        modateBimenameRepository.saveAndFlush(modateBimename);

        // Get all the modateBimenameList
        restModateBimenameMockMvc.perform(get("/api/modate-bimenames?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modateBimename.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sharh").value(hasItem(DEFAULT_SHARH.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getModateBimename() throws Exception {
        // Initialize the database
        modateBimenameRepository.saveAndFlush(modateBimename);

        // Get the modateBimename
        restModateBimenameMockMvc.perform(get("/api/modate-bimenames/{id}", modateBimename.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(modateBimename.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sharh").value(DEFAULT_SHARH.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingModateBimename() throws Exception {
        // Get the modateBimename
        restModateBimenameMockMvc.perform(get("/api/modate-bimenames/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModateBimename() throws Exception {
        // Initialize the database
        modateBimenameRepository.saveAndFlush(modateBimename);

        int databaseSizeBeforeUpdate = modateBimenameRepository.findAll().size();

        // Update the modateBimename
        ModateBimename updatedModateBimename = modateBimenameRepository.findById(modateBimename.getId()).get();
        // Disconnect from session so that the updates on updatedModateBimename are not directly saved in db
        em.detach(updatedModateBimename);
        updatedModateBimename
            .name(UPDATED_NAME)
            .sharh(UPDATED_SHARH)
            .faal(UPDATED_FAAL);
        ModateBimenameDTO modateBimenameDTO = modateBimenameMapper.toDto(updatedModateBimename);

        restModateBimenameMockMvc.perform(put("/api/modate-bimenames")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modateBimenameDTO)))
            .andExpect(status().isOk());

        // Validate the ModateBimename in the database
        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeUpdate);
        ModateBimename testModateBimename = modateBimenameList.get(modateBimenameList.size() - 1);
        assertThat(testModateBimename.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testModateBimename.getSharh()).isEqualTo(UPDATED_SHARH);
        assertThat(testModateBimename.isFaal()).isEqualTo(UPDATED_FAAL);
    }

    @Test
    @Transactional
    public void updateNonExistingModateBimename() throws Exception {
        int databaseSizeBeforeUpdate = modateBimenameRepository.findAll().size();

        // Create the ModateBimename
        ModateBimenameDTO modateBimenameDTO = modateBimenameMapper.toDto(modateBimename);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModateBimenameMockMvc.perform(put("/api/modate-bimenames")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modateBimenameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ModateBimename in the database
        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModateBimename() throws Exception {
        // Initialize the database
        modateBimenameRepository.saveAndFlush(modateBimename);

        int databaseSizeBeforeDelete = modateBimenameRepository.findAll().size();

        // Delete the modateBimename
        restModateBimenameMockMvc.perform(delete("/api/modate-bimenames/{id}", modateBimename.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ModateBimename> modateBimenameList = modateBimenameRepository.findAll();
        assertThat(modateBimenameList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModateBimename.class);
        ModateBimename modateBimename1 = new ModateBimename();
        modateBimename1.setId(1L);
        ModateBimename modateBimename2 = new ModateBimename();
        modateBimename2.setId(modateBimename1.getId());
        assertThat(modateBimename1).isEqualTo(modateBimename2);
        modateBimename2.setId(2L);
        assertThat(modateBimename1).isNotEqualTo(modateBimename2);
        modateBimename1.setId(null);
        assertThat(modateBimename1).isNotEqualTo(modateBimename2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModateBimenameDTO.class);
        ModateBimenameDTO modateBimenameDTO1 = new ModateBimenameDTO();
        modateBimenameDTO1.setId(1L);
        ModateBimenameDTO modateBimenameDTO2 = new ModateBimenameDTO();
        assertThat(modateBimenameDTO1).isNotEqualTo(modateBimenameDTO2);
        modateBimenameDTO2.setId(modateBimenameDTO1.getId());
        assertThat(modateBimenameDTO1).isEqualTo(modateBimenameDTO2);
        modateBimenameDTO2.setId(2L);
        assertThat(modateBimenameDTO1).isNotEqualTo(modateBimenameDTO2);
        modateBimenameDTO1.setId(null);
        assertThat(modateBimenameDTO1).isNotEqualTo(modateBimenameDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(modateBimenameMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(modateBimenameMapper.fromId(null)).isNull();
    }
}
