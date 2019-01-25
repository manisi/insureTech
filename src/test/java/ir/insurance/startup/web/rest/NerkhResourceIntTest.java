package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.Nerkh;
import ir.insurance.startup.repository.NerkhRepository;
import ir.insurance.startup.service.NerkhService;
import ir.insurance.startup.service.dto.NerkhDTO;
import ir.insurance.startup.service.mapper.NerkhMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NerkhResource REST controller.
 *
 * @see NerkhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class NerkhResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAAL = false;
    private static final Boolean UPDATED_FAAL = true;

    private static final Float DEFAULT_MABLAGH = 1F;
    private static final Float UPDATED_MABLAGH = 2F;

    @Autowired
    private NerkhRepository nerkhRepository;

    @Mock
    private NerkhRepository nerkhRepositoryMock;

    @Autowired
    private NerkhMapper nerkhMapper;

    @Mock
    private NerkhService nerkhServiceMock;

    @Autowired
    private NerkhService nerkhService;

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

    private MockMvc restNerkhMockMvc;

    private Nerkh nerkh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NerkhResource nerkhResource = new NerkhResource(nerkhService);
        this.restNerkhMockMvc = MockMvcBuilders.standaloneSetup(nerkhResource)
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
    public static Nerkh createEntity(EntityManager em) {
        Nerkh nerkh = new Nerkh()
            .name(DEFAULT_NAME)
            .faal(DEFAULT_FAAL)
            .mablagh(DEFAULT_MABLAGH);
        return nerkh;
    }

    @Before
    public void initTest() {
        nerkh = createEntity(em);
    }

    @Test
    @Transactional
    public void createNerkh() throws Exception {
        int databaseSizeBeforeCreate = nerkhRepository.findAll().size();

        // Create the Nerkh
        NerkhDTO nerkhDTO = nerkhMapper.toDto(nerkh);
        restNerkhMockMvc.perform(post("/api/nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nerkhDTO)))
            .andExpect(status().isCreated());

        // Validate the Nerkh in the database
        List<Nerkh> nerkhList = nerkhRepository.findAll();
        assertThat(nerkhList).hasSize(databaseSizeBeforeCreate + 1);
        Nerkh testNerkh = nerkhList.get(nerkhList.size() - 1);
        assertThat(testNerkh.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNerkh.isFaal()).isEqualTo(DEFAULT_FAAL);
        assertThat(testNerkh.getMablagh()).isEqualTo(DEFAULT_MABLAGH);
    }

    @Test
    @Transactional
    public void createNerkhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nerkhRepository.findAll().size();

        // Create the Nerkh with an existing ID
        nerkh.setId(1L);
        NerkhDTO nerkhDTO = nerkhMapper.toDto(nerkh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNerkhMockMvc.perform(post("/api/nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nerkhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nerkh in the database
        List<Nerkh> nerkhList = nerkhRepository.findAll();
        assertThat(nerkhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNerkhs() throws Exception {
        // Initialize the database
        nerkhRepository.saveAndFlush(nerkh);

        // Get all the nerkhList
        restNerkhMockMvc.perform(get("/api/nerkhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nerkh.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].faal").value(hasItem(DEFAULT_FAAL.booleanValue())))
            .andExpect(jsonPath("$.[*].mablagh").value(hasItem(DEFAULT_MABLAGH.doubleValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllNerkhsWithEagerRelationshipsIsEnabled() throws Exception {
        NerkhResource nerkhResource = new NerkhResource(nerkhServiceMock);
        when(nerkhServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restNerkhMockMvc = MockMvcBuilders.standaloneSetup(nerkhResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restNerkhMockMvc.perform(get("/api/nerkhs?eagerload=true"))
        .andExpect(status().isOk());

        verify(nerkhServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllNerkhsWithEagerRelationshipsIsNotEnabled() throws Exception {
        NerkhResource nerkhResource = new NerkhResource(nerkhServiceMock);
            when(nerkhServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restNerkhMockMvc = MockMvcBuilders.standaloneSetup(nerkhResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restNerkhMockMvc.perform(get("/api/nerkhs?eagerload=true"))
        .andExpect(status().isOk());

            verify(nerkhServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getNerkh() throws Exception {
        // Initialize the database
        nerkhRepository.saveAndFlush(nerkh);

        // Get the nerkh
        restNerkhMockMvc.perform(get("/api/nerkhs/{id}", nerkh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nerkh.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.faal").value(DEFAULT_FAAL.booleanValue()))
            .andExpect(jsonPath("$.mablagh").value(DEFAULT_MABLAGH.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNerkh() throws Exception {
        // Get the nerkh
        restNerkhMockMvc.perform(get("/api/nerkhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNerkh() throws Exception {
        // Initialize the database
        nerkhRepository.saveAndFlush(nerkh);

        int databaseSizeBeforeUpdate = nerkhRepository.findAll().size();

        // Update the nerkh
        Nerkh updatedNerkh = nerkhRepository.findById(nerkh.getId()).get();
        // Disconnect from session so that the updates on updatedNerkh are not directly saved in db
        em.detach(updatedNerkh);
        updatedNerkh
            .name(UPDATED_NAME)
            .faal(UPDATED_FAAL)
            .mablagh(UPDATED_MABLAGH);
        NerkhDTO nerkhDTO = nerkhMapper.toDto(updatedNerkh);

        restNerkhMockMvc.perform(put("/api/nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nerkhDTO)))
            .andExpect(status().isOk());

        // Validate the Nerkh in the database
        List<Nerkh> nerkhList = nerkhRepository.findAll();
        assertThat(nerkhList).hasSize(databaseSizeBeforeUpdate);
        Nerkh testNerkh = nerkhList.get(nerkhList.size() - 1);
        assertThat(testNerkh.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNerkh.isFaal()).isEqualTo(UPDATED_FAAL);
        assertThat(testNerkh.getMablagh()).isEqualTo(UPDATED_MABLAGH);
    }

    @Test
    @Transactional
    public void updateNonExistingNerkh() throws Exception {
        int databaseSizeBeforeUpdate = nerkhRepository.findAll().size();

        // Create the Nerkh
        NerkhDTO nerkhDTO = nerkhMapper.toDto(nerkh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNerkhMockMvc.perform(put("/api/nerkhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nerkhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nerkh in the database
        List<Nerkh> nerkhList = nerkhRepository.findAll();
        assertThat(nerkhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNerkh() throws Exception {
        // Initialize the database
        nerkhRepository.saveAndFlush(nerkh);

        int databaseSizeBeforeDelete = nerkhRepository.findAll().size();

        // Get the nerkh
        restNerkhMockMvc.perform(delete("/api/nerkhs/{id}", nerkh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nerkh> nerkhList = nerkhRepository.findAll();
        assertThat(nerkhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nerkh.class);
        Nerkh nerkh1 = new Nerkh();
        nerkh1.setId(1L);
        Nerkh nerkh2 = new Nerkh();
        nerkh2.setId(nerkh1.getId());
        assertThat(nerkh1).isEqualTo(nerkh2);
        nerkh2.setId(2L);
        assertThat(nerkh1).isNotEqualTo(nerkh2);
        nerkh1.setId(null);
        assertThat(nerkh1).isNotEqualTo(nerkh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NerkhDTO.class);
        NerkhDTO nerkhDTO1 = new NerkhDTO();
        nerkhDTO1.setId(1L);
        NerkhDTO nerkhDTO2 = new NerkhDTO();
        assertThat(nerkhDTO1).isNotEqualTo(nerkhDTO2);
        nerkhDTO2.setId(nerkhDTO1.getId());
        assertThat(nerkhDTO1).isEqualTo(nerkhDTO2);
        nerkhDTO2.setId(2L);
        assertThat(nerkhDTO1).isNotEqualTo(nerkhDTO2);
        nerkhDTO1.setId(null);
        assertThat(nerkhDTO1).isNotEqualTo(nerkhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nerkhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nerkhMapper.fromId(null)).isNull();
    }
}
