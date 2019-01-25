package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.TipKhodro;
import ir.insurance.startup.repository.TipKhodroRepository;
import ir.insurance.startup.service.TipKhodroService;
import ir.insurance.startup.service.dto.TipKhodroDTO;
import ir.insurance.startup.service.mapper.TipKhodroMapper;
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
 * Test class for the TipKhodroResource REST controller.
 *
 * @see TipKhodroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class TipKhodroResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    private static final NoeKhodro DEFAULT_NOE = NoeKhodro.SAVARI;
    private static final NoeKhodro UPDATED_NOE = NoeKhodro.TAXI;

    @Autowired
    private TipKhodroRepository tipKhodroRepository;

    @Autowired
    private TipKhodroMapper tipKhodroMapper;

    @Autowired
    private TipKhodroService tipKhodroService;

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

    private MockMvc restTipKhodroMockMvc;

    private TipKhodro tipKhodro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipKhodroResource tipKhodroResource = new TipKhodroResource(tipKhodroService);
        this.restTipKhodroMockMvc = MockMvcBuilders.standaloneSetup(tipKhodroResource)
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
    public static TipKhodro createEntity(EntityManager em) {
        TipKhodro tipKhodro = new TipKhodro()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .faal(DEFAULT_FAAL)
            .noe(DEFAULT_NOE);
        return tipKhodro;
    }

    @Before
    public void initTest() {
        tipKhodro = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipKhodro() throws Exception {
        int databaseSizeBeforeCreate = tipKhodroRepository.findAll().size();

        // Create the TipKhodro
        TipKhodroDTO tipKhodroDTO = tipKhodroMapper.toDto(tipKhodro);
        restTipKhodroMockMvc.perform(post("/api/tip-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipKhodroDTO)))
            .andExpect(status().isCreated());

        // Validate the TipKhodro in the database
        List<TipKhodro> tipKhodroList = tipKhodroRepository.findAll();
        assertThat(tipKhodroList).hasSize(databaseSizeBeforeCreate + 1);
        TipKhodro testTipKhodro = tipKhodroList.get(tipKhodroList.size() - 1);
        assertThat(testTipKhodro.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTipKhodro.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testTipKhodro.isFaal()).isEqualTo(DEFAULT_FAAL);
        assertThat(testTipKhodro.getNoe()).isEqualTo(DEFAULT_NOE);
    }

    @Test
    @Transactional
    public void createTipKhodroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipKhodroRepository.findAll().size();

        // Create the TipKhodro with an existing ID
        tipKhodro.setId(1L);
        TipKhodroDTO tipKhodroDTO = tipKhodroMapper.toDto(tipKhodro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipKhodroMockMvc.perform(post("/api/tip-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipKhodro in the database
        List<TipKhodro> tipKhodroList = tipKhodroRepository.findAll();
        assertThat(tipKhodroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipKhodros() throws Exception {
        // Initialize the database
        tipKhodroRepository.saveAndFlush(tipKhodro);

        // Get all the tipKhodroList
        restTipKhodroMockMvc.perform(get("/api/tip-khodros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipKhodro.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())))
            .andExpect(jsonPath("$.[*].noe").value(hasItem(DEFAULT_NOE.toString())));
    }
    
    @Test
    @Transactional
    public void getTipKhodro() throws Exception {
        // Initialize the database
        tipKhodroRepository.saveAndFlush(tipKhodro);

        // Get the tipKhodro
        restTipKhodroMockMvc.perform(get("/api/tip-khodros/{id}", tipKhodro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipKhodro.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()))
            .andExpect(jsonPath("$.noe").value(DEFAULT_NOE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipKhodro() throws Exception {
        // Get the tipKhodro
        restTipKhodroMockMvc.perform(get("/api/tip-khodros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipKhodro() throws Exception {
        // Initialize the database
        tipKhodroRepository.saveAndFlush(tipKhodro);

        int databaseSizeBeforeUpdate = tipKhodroRepository.findAll().size();

        // Update the tipKhodro
        TipKhodro updatedTipKhodro = tipKhodroRepository.findById(tipKhodro.getId()).get();
        // Disconnect from session so that the updates on updatedTipKhodro are not directly saved in db
        em.detach(updatedTipKhodro);
        updatedTipKhodro
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .faal(UPDATED_FAAL)
            .noe(UPDATED_NOE);
        TipKhodroDTO tipKhodroDTO = tipKhodroMapper.toDto(updatedTipKhodro);

        restTipKhodroMockMvc.perform(put("/api/tip-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipKhodroDTO)))
            .andExpect(status().isOk());

        // Validate the TipKhodro in the database
        List<TipKhodro> tipKhodroList = tipKhodroRepository.findAll();
        assertThat(tipKhodroList).hasSize(databaseSizeBeforeUpdate);
        TipKhodro testTipKhodro = tipKhodroList.get(tipKhodroList.size() - 1);
        assertThat(testTipKhodro.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTipKhodro.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testTipKhodro.isFaal()).isEqualTo(UPDATED_FAAL);
        assertThat(testTipKhodro.getNoe()).isEqualTo(UPDATED_NOE);
    }

    @Test
    @Transactional
    public void updateNonExistingTipKhodro() throws Exception {
        int databaseSizeBeforeUpdate = tipKhodroRepository.findAll().size();

        // Create the TipKhodro
        TipKhodroDTO tipKhodroDTO = tipKhodroMapper.toDto(tipKhodro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipKhodroMockMvc.perform(put("/api/tip-khodros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipKhodroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipKhodro in the database
        List<TipKhodro> tipKhodroList = tipKhodroRepository.findAll();
        assertThat(tipKhodroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipKhodro() throws Exception {
        // Initialize the database
        tipKhodroRepository.saveAndFlush(tipKhodro);

        int databaseSizeBeforeDelete = tipKhodroRepository.findAll().size();

        // Delete the tipKhodro
        restTipKhodroMockMvc.perform(delete("/api/tip-khodros/{id}", tipKhodro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipKhodro> tipKhodroList = tipKhodroRepository.findAll();
        assertThat(tipKhodroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipKhodro.class);
        TipKhodro tipKhodro1 = new TipKhodro();
        tipKhodro1.setId(1L);
        TipKhodro tipKhodro2 = new TipKhodro();
        tipKhodro2.setId(tipKhodro1.getId());
        assertThat(tipKhodro1).isEqualTo(tipKhodro2);
        tipKhodro2.setId(2L);
        assertThat(tipKhodro1).isNotEqualTo(tipKhodro2);
        tipKhodro1.setId(null);
        assertThat(tipKhodro1).isNotEqualTo(tipKhodro2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipKhodroDTO.class);
        TipKhodroDTO tipKhodroDTO1 = new TipKhodroDTO();
        tipKhodroDTO1.setId(1L);
        TipKhodroDTO tipKhodroDTO2 = new TipKhodroDTO();
        assertThat(tipKhodroDTO1).isNotEqualTo(tipKhodroDTO2);
        tipKhodroDTO2.setId(tipKhodroDTO1.getId());
        assertThat(tipKhodroDTO1).isEqualTo(tipKhodroDTO2);
        tipKhodroDTO2.setId(2L);
        assertThat(tipKhodroDTO1).isNotEqualTo(tipKhodroDTO2);
        tipKhodroDTO1.setId(null);
        assertThat(tipKhodroDTO1).isNotEqualTo(tipKhodroDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipKhodroMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipKhodroMapper.fromId(null)).isNull();
    }
}
