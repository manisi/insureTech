package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.Ashkhas;
import ir.insurance.startup.repository.AshkhasRepository;
import ir.insurance.startup.service.AshkhasService;
import ir.insurance.startup.service.dto.AshkhasDTO;
import ir.insurance.startup.service.mapper.AshkhasMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ir.insurance.startup.domain.enumeration.NoeShakhs;
/**
 * Test class for the AshkhasResource REST controller.
 *
 * @see AshkhasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class AshkhasResourceIntTest {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_HIRE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HIRE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final NoeShakhs DEFAULT_NOE_SHAKHS = NoeShakhs.HAGHIGHI;
    private static final NoeShakhs UPDATED_NOE_SHAKHS = NoeShakhs.HOGHOOGHI;

    @Autowired
    private AshkhasRepository ashkhasRepository;

    @Autowired
    private AshkhasMapper ashkhasMapper;

    @Autowired
    private AshkhasService ashkhasService;

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

    private MockMvc restAshkhasMockMvc;

    private Ashkhas ashkhas;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AshkhasResource ashkhasResource = new AshkhasResource(ashkhasService);
        this.restAshkhasMockMvc = MockMvcBuilders.standaloneSetup(ashkhasResource)
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
    public static Ashkhas createEntity(EntityManager em) {
        Ashkhas ashkhas = new Ashkhas()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .hireDate(DEFAULT_HIRE_DATE)
            .noeShakhs(DEFAULT_NOE_SHAKHS);
        return ashkhas;
    }

    @Before
    public void initTest() {
        ashkhas = createEntity(em);
    }

    @Test
    @Transactional
    public void createAshkhas() throws Exception {
        int databaseSizeBeforeCreate = ashkhasRepository.findAll().size();

        // Create the Ashkhas
        AshkhasDTO ashkhasDTO = ashkhasMapper.toDto(ashkhas);
        restAshkhasMockMvc.perform(post("/api/ashkhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ashkhasDTO)))
            .andExpect(status().isCreated());

        // Validate the Ashkhas in the database
        List<Ashkhas> ashkhasList = ashkhasRepository.findAll();
        assertThat(ashkhasList).hasSize(databaseSizeBeforeCreate + 1);
        Ashkhas testAshkhas = ashkhasList.get(ashkhasList.size() - 1);
        assertThat(testAshkhas.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAshkhas.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAshkhas.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAshkhas.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAshkhas.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(testAshkhas.getNoeShakhs()).isEqualTo(DEFAULT_NOE_SHAKHS);
    }

    @Test
    @Transactional
    public void createAshkhasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ashkhasRepository.findAll().size();

        // Create the Ashkhas with an existing ID
        ashkhas.setId(1L);
        AshkhasDTO ashkhasDTO = ashkhasMapper.toDto(ashkhas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAshkhasMockMvc.perform(post("/api/ashkhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ashkhasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ashkhas in the database
        List<Ashkhas> ashkhasList = ashkhasRepository.findAll();
        assertThat(ashkhasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAshkhas() throws Exception {
        // Initialize the database
        ashkhasRepository.saveAndFlush(ashkhas);

        // Get all the ashkhasList
        restAshkhasMockMvc.perform(get("/api/ashkhas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ashkhas.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].noeShakhs").value(hasItem(DEFAULT_NOE_SHAKHS.toString())));
    }
    
    @Test
    @Transactional
    public void getAshkhas() throws Exception {
        // Initialize the database
        ashkhasRepository.saveAndFlush(ashkhas);

        // Get the ashkhas
        restAshkhasMockMvc.perform(get("/api/ashkhas/{id}", ashkhas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ashkhas.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()))
            .andExpect(jsonPath("$.noeShakhs").value(DEFAULT_NOE_SHAKHS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAshkhas() throws Exception {
        // Get the ashkhas
        restAshkhasMockMvc.perform(get("/api/ashkhas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAshkhas() throws Exception {
        // Initialize the database
        ashkhasRepository.saveAndFlush(ashkhas);

        int databaseSizeBeforeUpdate = ashkhasRepository.findAll().size();

        // Update the ashkhas
        Ashkhas updatedAshkhas = ashkhasRepository.findById(ashkhas.getId()).get();
        // Disconnect from session so that the updates on updatedAshkhas are not directly saved in db
        em.detach(updatedAshkhas);
        updatedAshkhas
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .hireDate(UPDATED_HIRE_DATE)
            .noeShakhs(UPDATED_NOE_SHAKHS);
        AshkhasDTO ashkhasDTO = ashkhasMapper.toDto(updatedAshkhas);

        restAshkhasMockMvc.perform(put("/api/ashkhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ashkhasDTO)))
            .andExpect(status().isOk());

        // Validate the Ashkhas in the database
        List<Ashkhas> ashkhasList = ashkhasRepository.findAll();
        assertThat(ashkhasList).hasSize(databaseSizeBeforeUpdate);
        Ashkhas testAshkhas = ashkhasList.get(ashkhasList.size() - 1);
        assertThat(testAshkhas.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAshkhas.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAshkhas.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAshkhas.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAshkhas.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
        assertThat(testAshkhas.getNoeShakhs()).isEqualTo(UPDATED_NOE_SHAKHS);
    }

    @Test
    @Transactional
    public void updateNonExistingAshkhas() throws Exception {
        int databaseSizeBeforeUpdate = ashkhasRepository.findAll().size();

        // Create the Ashkhas
        AshkhasDTO ashkhasDTO = ashkhasMapper.toDto(ashkhas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAshkhasMockMvc.perform(put("/api/ashkhas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ashkhasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ashkhas in the database
        List<Ashkhas> ashkhasList = ashkhasRepository.findAll();
        assertThat(ashkhasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAshkhas() throws Exception {
        // Initialize the database
        ashkhasRepository.saveAndFlush(ashkhas);

        int databaseSizeBeforeDelete = ashkhasRepository.findAll().size();

        // Delete the ashkhas
        restAshkhasMockMvc.perform(delete("/api/ashkhas/{id}", ashkhas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ashkhas> ashkhasList = ashkhasRepository.findAll();
        assertThat(ashkhasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ashkhas.class);
        Ashkhas ashkhas1 = new Ashkhas();
        ashkhas1.setId(1L);
        Ashkhas ashkhas2 = new Ashkhas();
        ashkhas2.setId(ashkhas1.getId());
        assertThat(ashkhas1).isEqualTo(ashkhas2);
        ashkhas2.setId(2L);
        assertThat(ashkhas1).isNotEqualTo(ashkhas2);
        ashkhas1.setId(null);
        assertThat(ashkhas1).isNotEqualTo(ashkhas2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AshkhasDTO.class);
        AshkhasDTO ashkhasDTO1 = new AshkhasDTO();
        ashkhasDTO1.setId(1L);
        AshkhasDTO ashkhasDTO2 = new AshkhasDTO();
        assertThat(ashkhasDTO1).isNotEqualTo(ashkhasDTO2);
        ashkhasDTO2.setId(ashkhasDTO1.getId());
        assertThat(ashkhasDTO1).isEqualTo(ashkhasDTO2);
        ashkhasDTO2.setId(2L);
        assertThat(ashkhasDTO1).isNotEqualTo(ashkhasDTO2);
        ashkhasDTO1.setId(null);
        assertThat(ashkhasDTO1).isNotEqualTo(ashkhasDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ashkhasMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ashkhasMapper.fromId(null)).isNull();
    }
}
