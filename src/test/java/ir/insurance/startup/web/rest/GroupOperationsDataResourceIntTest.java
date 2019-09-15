package ir.insurance.startup.web.rest;

import ir.insurance.startup.InsurancestartApp;

import ir.insurance.startup.domain.GroupOperationsData;
import ir.insurance.startup.repository.GroupOperationsDataRepository;
import ir.insurance.startup.service.GroupOperationsDataService;
import ir.insurance.startup.service.dto.GroupOperationsDataDTO;
import ir.insurance.startup.service.mapper.GroupOperationsDataMapper;
import ir.insurance.startup.web.rest.errors.ExceptionTranslator;
import ir.insurance.startup.service.dto.GroupOperationsDataCriteria;
import ir.insurance.startup.service.GroupOperationsDataQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static ir.insurance.startup.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ir.insurance.startup.domain.enumeration.EntityEnumName;
import ir.insurance.startup.domain.enumeration.EstateFileItem;
/**
 * Test class for the GroupOperationsDataResource REST controller.
 *
 * @see GroupOperationsDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsurancestartApp.class)
public class GroupOperationsDataResourceIntTest {

    private static final EntityEnumName DEFAULT_ENTITY_NAME = EntityEnumName.CARGROUP;
    private static final EntityEnumName UPDATED_ENTITY_NAME = EntityEnumName.CARKIND;

    private static final Boolean DEFAULT_ACTING = false;
    private static final Boolean UPDATED_ACTING = true;

    private static final EstateFileItem DEFAULT_ESTATE = EstateFileItem.UPLOADED;
    private static final EstateFileItem UPDATED_ESTATE = EstateFileItem.CONFIRMED;

    private static final byte[] DEFAULT_UPLOADFILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_UPLOADFILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_UPLOADFILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_UPLOADFILE_CONTENT_TYPE = "image/png";

    @Autowired
    private GroupOperationsDataRepository groupOperationsDataRepository;

    @Autowired
    private GroupOperationsDataMapper groupOperationsDataMapper;

    @Autowired
    private GroupOperationsDataService groupOperationsDataService;

    @Autowired
    private GroupOperationsDataQueryService groupOperationsDataQueryService;

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

    private MockMvc restGroupOperationsDataMockMvc;

    private GroupOperationsData groupOperationsData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupOperationsDataResource groupOperationsDataResource = new GroupOperationsDataResource(groupOperationsDataService, groupOperationsDataQueryService);
        this.restGroupOperationsDataMockMvc = MockMvcBuilders.standaloneSetup(groupOperationsDataResource)
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
    public static GroupOperationsData createEntity(EntityManager em) {
        GroupOperationsData groupOperationsData = new GroupOperationsData()
            .entityName(DEFAULT_ENTITY_NAME)
            .acting(DEFAULT_ACTING)
            .estate(DEFAULT_ESTATE)
            .uploadfile(DEFAULT_UPLOADFILE)
            .uploadfileContentType(DEFAULT_UPLOADFILE_CONTENT_TYPE);
        return groupOperationsData;
    }

    @Before
    public void initTest() {
        groupOperationsData = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupOperationsData() throws Exception {
        int databaseSizeBeforeCreate = groupOperationsDataRepository.findAll().size();

        // Create the GroupOperationsData
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(groupOperationsData);
        restGroupOperationsDataMockMvc.perform(post("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isCreated());

        // Validate the GroupOperationsData in the database
        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeCreate + 1);
        GroupOperationsData testGroupOperationsData = groupOperationsDataList.get(groupOperationsDataList.size() - 1);
        assertThat(testGroupOperationsData.getEntityName()).isEqualTo(DEFAULT_ENTITY_NAME);
        assertThat(testGroupOperationsData.isActing()).isEqualTo(DEFAULT_ACTING);
        assertThat(testGroupOperationsData.getEstate()).isEqualTo(DEFAULT_ESTATE);
        assertThat(testGroupOperationsData.getUploadfile()).isEqualTo(DEFAULT_UPLOADFILE);
        assertThat(testGroupOperationsData.getUploadfileContentType()).isEqualTo(DEFAULT_UPLOADFILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createGroupOperationsDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupOperationsDataRepository.findAll().size();

        // Create the GroupOperationsData with an existing ID
        groupOperationsData.setId(1L);
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(groupOperationsData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupOperationsDataMockMvc.perform(post("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupOperationsData in the database
        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEntityNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupOperationsDataRepository.findAll().size();
        // set the field null
        groupOperationsData.setEntityName(null);

        // Create the GroupOperationsData, which fails.
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(groupOperationsData);

        restGroupOperationsDataMockMvc.perform(post("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isBadRequest());

        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActingIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupOperationsDataRepository.findAll().size();
        // set the field null
        groupOperationsData.setActing(null);

        // Create the GroupOperationsData, which fails.
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(groupOperationsData);

        restGroupOperationsDataMockMvc.perform(post("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isBadRequest());

        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstateIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupOperationsDataRepository.findAll().size();
        // set the field null
        groupOperationsData.setEstate(null);

        // Create the GroupOperationsData, which fails.
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(groupOperationsData);

        restGroupOperationsDataMockMvc.perform(post("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isBadRequest());

        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsData() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupOperationsData.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityName").value(hasItem(DEFAULT_ENTITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].acting").value(hasItem(DEFAULT_ACTING.booleanValue())))
            .andExpect(jsonPath("$.[*].estate").value(hasItem(DEFAULT_ESTATE.toString())))
            .andExpect(jsonPath("$.[*].uploadfileContentType").value(hasItem(DEFAULT_UPLOADFILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].uploadfile").value(hasItem(Base64Utils.encodeToString(DEFAULT_UPLOADFILE))));
    }
    
    @Test
    @Transactional
    public void getGroupOperationsData() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get the groupOperationsData
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data/{id}", groupOperationsData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groupOperationsData.getId().intValue()))
            .andExpect(jsonPath("$.entityName").value(DEFAULT_ENTITY_NAME.toString()))
            .andExpect(jsonPath("$.acting").value(DEFAULT_ACTING.booleanValue()))
            .andExpect(jsonPath("$.estate").value(DEFAULT_ESTATE.toString()))
            .andExpect(jsonPath("$.uploadfileContentType").value(DEFAULT_UPLOADFILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.uploadfile").value(Base64Utils.encodeToString(DEFAULT_UPLOADFILE)));
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByEntityNameIsEqualToSomething() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where entityName equals to DEFAULT_ENTITY_NAME
        defaultGroupOperationsDataShouldBeFound("entityName.equals=" + DEFAULT_ENTITY_NAME);

        // Get all the groupOperationsDataList where entityName equals to UPDATED_ENTITY_NAME
        defaultGroupOperationsDataShouldNotBeFound("entityName.equals=" + UPDATED_ENTITY_NAME);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByEntityNameIsInShouldWork() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where entityName in DEFAULT_ENTITY_NAME or UPDATED_ENTITY_NAME
        defaultGroupOperationsDataShouldBeFound("entityName.in=" + DEFAULT_ENTITY_NAME + "," + UPDATED_ENTITY_NAME);

        // Get all the groupOperationsDataList where entityName equals to UPDATED_ENTITY_NAME
        defaultGroupOperationsDataShouldNotBeFound("entityName.in=" + UPDATED_ENTITY_NAME);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByEntityNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where entityName is not null
        defaultGroupOperationsDataShouldBeFound("entityName.specified=true");

        // Get all the groupOperationsDataList where entityName is null
        defaultGroupOperationsDataShouldNotBeFound("entityName.specified=false");
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByActingIsEqualToSomething() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where acting equals to DEFAULT_ACTING
        defaultGroupOperationsDataShouldBeFound("acting.equals=" + DEFAULT_ACTING);

        // Get all the groupOperationsDataList where acting equals to UPDATED_ACTING
        defaultGroupOperationsDataShouldNotBeFound("acting.equals=" + UPDATED_ACTING);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByActingIsInShouldWork() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where acting in DEFAULT_ACTING or UPDATED_ACTING
        defaultGroupOperationsDataShouldBeFound("acting.in=" + DEFAULT_ACTING + "," + UPDATED_ACTING);

        // Get all the groupOperationsDataList where acting equals to UPDATED_ACTING
        defaultGroupOperationsDataShouldNotBeFound("acting.in=" + UPDATED_ACTING);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByActingIsNullOrNotNull() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where acting is not null
        defaultGroupOperationsDataShouldBeFound("acting.specified=true");

        // Get all the groupOperationsDataList where acting is null
        defaultGroupOperationsDataShouldNotBeFound("acting.specified=false");
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByEstateIsEqualToSomething() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where estate equals to DEFAULT_ESTATE
        defaultGroupOperationsDataShouldBeFound("estate.equals=" + DEFAULT_ESTATE);

        // Get all the groupOperationsDataList where estate equals to UPDATED_ESTATE
        defaultGroupOperationsDataShouldNotBeFound("estate.equals=" + UPDATED_ESTATE);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByEstateIsInShouldWork() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where estate in DEFAULT_ESTATE or UPDATED_ESTATE
        defaultGroupOperationsDataShouldBeFound("estate.in=" + DEFAULT_ESTATE + "," + UPDATED_ESTATE);

        // Get all the groupOperationsDataList where estate equals to UPDATED_ESTATE
        defaultGroupOperationsDataShouldNotBeFound("estate.in=" + UPDATED_ESTATE);
    }

    @Test
    @Transactional
    public void getAllGroupOperationsDataByEstateIsNullOrNotNull() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        // Get all the groupOperationsDataList where estate is not null
        defaultGroupOperationsDataShouldBeFound("estate.specified=true");

        // Get all the groupOperationsDataList where estate is null
        defaultGroupOperationsDataShouldNotBeFound("estate.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultGroupOperationsDataShouldBeFound(String filter) throws Exception {
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupOperationsData.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityName").value(hasItem(DEFAULT_ENTITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].acting").value(hasItem(DEFAULT_ACTING.booleanValue())))
            .andExpect(jsonPath("$.[*].estate").value(hasItem(DEFAULT_ESTATE.toString())))
            .andExpect(jsonPath("$.[*].uploadfileContentType").value(hasItem(DEFAULT_UPLOADFILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].uploadfile").value(hasItem(Base64Utils.encodeToString(DEFAULT_UPLOADFILE))));

        // Check, that the count call also returns 1
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultGroupOperationsDataShouldNotBeFound(String filter) throws Exception {
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingGroupOperationsData() throws Exception {
        // Get the groupOperationsData
        restGroupOperationsDataMockMvc.perform(get("/api/group-operations-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupOperationsData() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        int databaseSizeBeforeUpdate = groupOperationsDataRepository.findAll().size();

        // Update the groupOperationsData
        GroupOperationsData updatedGroupOperationsData = groupOperationsDataRepository.findById(groupOperationsData.getId()).get();
        // Disconnect from session so that the updates on updatedGroupOperationsData are not directly saved in db
        em.detach(updatedGroupOperationsData);
        updatedGroupOperationsData
            .entityName(UPDATED_ENTITY_NAME)
            .acting(UPDATED_ACTING)
            .estate(UPDATED_ESTATE)
            .uploadfile(UPDATED_UPLOADFILE)
            .uploadfileContentType(UPDATED_UPLOADFILE_CONTENT_TYPE);
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(updatedGroupOperationsData);

        restGroupOperationsDataMockMvc.perform(put("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isOk());

        // Validate the GroupOperationsData in the database
        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeUpdate);
        GroupOperationsData testGroupOperationsData = groupOperationsDataList.get(groupOperationsDataList.size() - 1);
        assertThat(testGroupOperationsData.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
        assertThat(testGroupOperationsData.isActing()).isEqualTo(UPDATED_ACTING);
        assertThat(testGroupOperationsData.getEstate()).isEqualTo(UPDATED_ESTATE);
        assertThat(testGroupOperationsData.getUploadfile()).isEqualTo(UPDATED_UPLOADFILE);
        assertThat(testGroupOperationsData.getUploadfileContentType()).isEqualTo(UPDATED_UPLOADFILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupOperationsData() throws Exception {
        int databaseSizeBeforeUpdate = groupOperationsDataRepository.findAll().size();

        // Create the GroupOperationsData
        GroupOperationsDataDTO groupOperationsDataDTO = groupOperationsDataMapper.toDto(groupOperationsData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupOperationsDataMockMvc.perform(put("/api/group-operations-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupOperationsDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupOperationsData in the database
        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupOperationsData() throws Exception {
        // Initialize the database
        groupOperationsDataRepository.saveAndFlush(groupOperationsData);

        int databaseSizeBeforeDelete = groupOperationsDataRepository.findAll().size();

        // Delete the groupOperationsData
        restGroupOperationsDataMockMvc.perform(delete("/api/group-operations-data/{id}", groupOperationsData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GroupOperationsData> groupOperationsDataList = groupOperationsDataRepository.findAll();
        assertThat(groupOperationsDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupOperationsData.class);
        GroupOperationsData groupOperationsData1 = new GroupOperationsData();
        groupOperationsData1.setId(1L);
        GroupOperationsData groupOperationsData2 = new GroupOperationsData();
        groupOperationsData2.setId(groupOperationsData1.getId());
        assertThat(groupOperationsData1).isEqualTo(groupOperationsData2);
        groupOperationsData2.setId(2L);
        assertThat(groupOperationsData1).isNotEqualTo(groupOperationsData2);
        groupOperationsData1.setId(null);
        assertThat(groupOperationsData1).isNotEqualTo(groupOperationsData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupOperationsDataDTO.class);
        GroupOperationsDataDTO groupOperationsDataDTO1 = new GroupOperationsDataDTO();
        groupOperationsDataDTO1.setId(1L);
        GroupOperationsDataDTO groupOperationsDataDTO2 = new GroupOperationsDataDTO();
        assertThat(groupOperationsDataDTO1).isNotEqualTo(groupOperationsDataDTO2);
        groupOperationsDataDTO2.setId(groupOperationsDataDTO1.getId());
        assertThat(groupOperationsDataDTO1).isEqualTo(groupOperationsDataDTO2);
        groupOperationsDataDTO2.setId(2L);
        assertThat(groupOperationsDataDTO1).isNotEqualTo(groupOperationsDataDTO2);
        groupOperationsDataDTO1.setId(null);
        assertThat(groupOperationsDataDTO1).isNotEqualTo(groupOperationsDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(groupOperationsDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(groupOperationsDataMapper.fromId(null)).isNull();
    }
}
