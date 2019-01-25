package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.Khodro;
import ir.insurance.startup.repository.KhodroRepository;
import ir.insurance.startup.service.KhodroService;
import ir.insurance.startup.service.dto.KhodroDTO;
import ir.insurance.startup.service.mapper.KhodroMapper;
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

import ir.insurance.startup.domain.enumeration.NoeKhodro;
/**
 * Test class for the KhodroResource REST controller.
 *
 * @see KhodroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class KhodroResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    private static final NoeKhodro DEFAULT_NOE = NoeKhodro.SAVARI;
    private static final NoeKhodro UPDATED_NOE = NoeKhodro.TAXI;

    @Autowired
    private KhodroRepository khodroRepository;

    @Autowired
    private KhodroMapper khodroMapper;

    @Autowired
    private KhodroService khodroService;

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

    private MockMvc restKhodroMockMvc;

    private Khodro khodro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KhodroResource khodroResource = new KhodroResource(khodroService);
        this.restKhodroMockMvc = MockMvcBuilders.standaloneSetup(khodroResource)
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
    public static Khodro createEntity(EntityManager em) {
        Khodro khodro = new Khodro()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .faal(DEFAULT_FAAL)
            .noe(DEFAULT_NOE);
        return khodro;
    }

    @Before
    public void initTest() {
        khodro = createEntity(em);
    }

    @Test
    @Transactional
    public void createKhodro() throws Exception {
        int databaseSizeBeforeCreate = khodroRepository.findAll().size();

        // Create the Khodro
        KhodroDTO khodroDTO = khodroMapper.toDto(khodro);
        restKhodroMockMvc.perform(post("/api/khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khodroDTO)))
            .andExpect(status().isCreated());

        // Validate the Khodro in the database
        List<Khodro> khodroList = khodroRepository.findAll();
        assertThat(khodroList).hasSize(databaseSizeBeforeCreate + 1);
        Khodro testKhodro = khodroList.get(khodroList.size() - 1);
        assertThat(testKhodro.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testKhodro.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testKhodro.isFaal()).isEqualTo(DEFAULT_FAAL);
        assertThat(testKhodro.getNoe()).isEqualTo(DEFAULT_NOE);
    }

    @Test
    @Transactional
    public void createKhodroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = khodroRepository.findAll().size();

        // Create the Khodro with an existing ID
        khodro.setId(1L);
        KhodroDTO khodroDTO = khodroMapper.toDto(khodro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKhodroMockMvc.perform(post("/api/khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Khodro in the database
        List<Khodro> khodroList = khodroRepository.findAll();
        assertThat(khodroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKhodros() throws Exception {
        // Initialize the database
        khodroRepository.saveAndFlush(khodro);

        // Get all the khodroList
        restKhodroMockMvc.perform(get("/api/khodros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())))
            .andExpect(jsonPath("$.[*].noe").value(hasItem(DEFAULT_NOE.toString())));
    }
    
    @Test
    @Transactional
    public void getKhodro() throws Exception {
        // Initialize the database
        khodroRepository.saveAndFlush(khodro);

        // Get the khodro
        restKhodroMockMvc.perform(get("/api/khodros/{id}", khodro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(khodro.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()))
            .andExpect(jsonPath("$.noe").value(DEFAULT_NOE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKhodro() throws Exception {
        // Get the khodro
        restKhodroMockMvc.perform(get("/api/khodros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKhodro() throws Exception {
        // Initialize the database
        khodroRepository.saveAndFlush(khodro);

        int databaseSizeBeforeUpdate = khodroRepository.findAll().size();

        // Update the khodro
        Khodro updatedKhodro = khodroRepository.findById(khodro.getId()).get();
        // Disconnect from session so that the updates on updatedKhodro are not directly saved in db
        em.detach(updatedKhodro);
        updatedKhodro
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .faal(UPDATED_FAAL)
            .noe(UPDATED_NOE);
        KhodroDTO khodroDTO = khodroMapper.toDto(updatedKhodro);

        restKhodroMockMvc.perform(put("/api/khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khodroDTO)))
            .andExpect(status().isOk());

        // Validate the Khodro in the database
        List<Khodro> khodroList = khodroRepository.findAll();
        assertThat(khodroList).hasSize(databaseSizeBeforeUpdate);
        Khodro testKhodro = khodroList.get(khodroList.size() - 1);
        assertThat(testKhodro.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testKhodro.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testKhodro.isFaal()).isEqualTo(UPDATED_FAAL);
        assertThat(testKhodro.getNoe()).isEqualTo(UPDATED_NOE);
    }

    @Test
    @Transactional
    public void updateNonExistingKhodro() throws Exception {
        int databaseSizeBeforeUpdate = khodroRepository.findAll().size();

        // Create the Khodro
        KhodroDTO khodroDTO = khodroMapper.toDto(khodro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKhodroMockMvc.perform(put("/api/khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Khodro in the database
        List<Khodro> khodroList = khodroRepository.findAll();
        assertThat(khodroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKhodro() throws Exception {
        // Initialize the database
        khodroRepository.saveAndFlush(khodro);

        int databaseSizeBeforeDelete = khodroRepository.findAll().size();

        // Get the khodro
        restKhodroMockMvc.perform(delete("/api/khodros/{id}", khodro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Khodro> khodroList = khodroRepository.findAll();
        assertThat(khodroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Khodro.class);
        Khodro khodro1 = new Khodro();
        khodro1.setId(1L);
        Khodro khodro2 = new Khodro();
        khodro2.setId(khodro1.getId());
        assertThat(khodro1).isEqualTo(khodro2);
        khodro2.setId(2L);
        assertThat(khodro1).isNotEqualTo(khodro2);
        khodro1.setId(null);
        assertThat(khodro1).isNotEqualTo(khodro2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhodroDTO.class);
        KhodroDTO khodroDTO1 = new KhodroDTO();
        khodroDTO1.setId(1L);
        KhodroDTO khodroDTO2 = new KhodroDTO();
        assertThat(khodroDTO1).isNotEqualTo(khodroDTO2);
        khodroDTO2.setId(khodroDTO1.getId());
        assertThat(khodroDTO1).isEqualTo(khodroDTO2);
        khodroDTO2.setId(2L);
        assertThat(khodroDTO1).isNotEqualTo(khodroDTO2);
        khodroDTO1.setId(null);
        assertThat(khodroDTO1).isNotEqualTo(khodroDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(khodroMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(khodroMapper.fromId(null)).isNull();
    }
}
