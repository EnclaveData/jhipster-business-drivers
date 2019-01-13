package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterBusinessDriversApp;

import io.github.jhipster.application.domain.Control;
import io.github.jhipster.application.repository.ControlRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ControlResource REST controller.
 *
 * @see ControlResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterBusinessDriversApp.class)
public class ControlResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ControlRepository controlRepository;

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

    private MockMvc restControlMockMvc;

    private Control control;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ControlResource controlResource = new ControlResource(controlRepository);
        this.restControlMockMvc = MockMvcBuilders.standaloneSetup(controlResource)
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
    public static Control createEntity(EntityManager em) {
        Control control = new Control()
            .name(DEFAULT_NAME);
        return control;
    }

    @Before
    public void initTest() {
        control = createEntity(em);
    }

    @Test
    @Transactional
    public void createControl() throws Exception {
        int databaseSizeBeforeCreate = controlRepository.findAll().size();

        // Create the Control
        restControlMockMvc.perform(post("/api/controls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(control)))
            .andExpect(status().isCreated());

        // Validate the Control in the database
        List<Control> controlList = controlRepository.findAll();
        assertThat(controlList).hasSize(databaseSizeBeforeCreate + 1);
        Control testControl = controlList.get(controlList.size() - 1);
        assertThat(testControl.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createControlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = controlRepository.findAll().size();

        // Create the Control with an existing ID
        control.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restControlMockMvc.perform(post("/api/controls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(control)))
            .andExpect(status().isBadRequest());

        // Validate the Control in the database
        List<Control> controlList = controlRepository.findAll();
        assertThat(controlList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllControls() throws Exception {
        // Initialize the database
        controlRepository.saveAndFlush(control);

        // Get all the controlList
        restControlMockMvc.perform(get("/api/controls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(control.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getControl() throws Exception {
        // Initialize the database
        controlRepository.saveAndFlush(control);

        // Get the control
        restControlMockMvc.perform(get("/api/controls/{id}", control.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(control.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingControl() throws Exception {
        // Get the control
        restControlMockMvc.perform(get("/api/controls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateControl() throws Exception {
        // Initialize the database
        controlRepository.saveAndFlush(control);

        int databaseSizeBeforeUpdate = controlRepository.findAll().size();

        // Update the control
        Control updatedControl = controlRepository.findById(control.getId()).get();
        // Disconnect from session so that the updates on updatedControl are not directly saved in db
        em.detach(updatedControl);
        updatedControl
            .name(UPDATED_NAME);

        restControlMockMvc.perform(put("/api/controls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedControl)))
            .andExpect(status().isOk());

        // Validate the Control in the database
        List<Control> controlList = controlRepository.findAll();
        assertThat(controlList).hasSize(databaseSizeBeforeUpdate);
        Control testControl = controlList.get(controlList.size() - 1);
        assertThat(testControl.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingControl() throws Exception {
        int databaseSizeBeforeUpdate = controlRepository.findAll().size();

        // Create the Control

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restControlMockMvc.perform(put("/api/controls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(control)))
            .andExpect(status().isBadRequest());

        // Validate the Control in the database
        List<Control> controlList = controlRepository.findAll();
        assertThat(controlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteControl() throws Exception {
        // Initialize the database
        controlRepository.saveAndFlush(control);

        int databaseSizeBeforeDelete = controlRepository.findAll().size();

        // Get the control
        restControlMockMvc.perform(delete("/api/controls/{id}", control.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Control> controlList = controlRepository.findAll();
        assertThat(controlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Control.class);
        Control control1 = new Control();
        control1.setId(1L);
        Control control2 = new Control();
        control2.setId(control1.getId());
        assertThat(control1).isEqualTo(control2);
        control2.setId(2L);
        assertThat(control1).isNotEqualTo(control2);
        control1.setId(null);
        assertThat(control1).isNotEqualTo(control2);
    }
}
