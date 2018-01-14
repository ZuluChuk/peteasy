package com.peteasy.store.web.rest;

import com.peteasy.store.PeteasyApp;

import com.peteasy.store.domain.OrderEntry;
import com.peteasy.store.repository.OrderEntryRepository;
import com.peteasy.store.service.OrderEntryService;
import com.peteasy.store.repository.search.OrderEntrySearchRepository;
import com.peteasy.store.service.dto.OrderEntryDTO;
import com.peteasy.store.service.mapper.OrderEntryMapper;
import com.peteasy.store.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrderEntryResource REST controller.
 *
 * @see OrderEntryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeteasyApp.class)
public class OrderEntryResourceIntTest {

    private static final BigDecimal DEFAULT_BASE_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BASE_PRICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_AMONT = 1;
    private static final Integer UPDATED_AMONT = 2;

    private static final Integer DEFAULT_NO = 1;
    private static final Integer UPDATED_NO = 2;

    @Autowired
    private OrderEntryRepository orderEntryRepository;

    @Autowired
    private OrderEntryMapper orderEntryMapper;

    @Autowired
    private OrderEntryService orderEntryService;

    @Autowired
    private OrderEntrySearchRepository orderEntrySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrderEntryMockMvc;

    private OrderEntry orderEntry;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderEntryResource orderEntryResource = new OrderEntryResource(orderEntryService);
        this.restOrderEntryMockMvc = MockMvcBuilders.standaloneSetup(orderEntryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderEntry createEntity(EntityManager em) {
        OrderEntry orderEntry = new OrderEntry()
            .basePrice(DEFAULT_BASE_PRICE)
            .totalPrice(DEFAULT_TOTAL_PRICE)
            .amont(DEFAULT_AMONT)
            .no(DEFAULT_NO);
        return orderEntry;
    }

    @Before
    public void initTest() {
        orderEntrySearchRepository.deleteAll();
        orderEntry = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderEntry() throws Exception {
        int databaseSizeBeforeCreate = orderEntryRepository.findAll().size();

        // Create the OrderEntry
        OrderEntryDTO orderEntryDTO = orderEntryMapper.toDto(orderEntry);
        restOrderEntryMockMvc.perform(post("/api/order-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderEntry in the database
        List<OrderEntry> orderEntryList = orderEntryRepository.findAll();
        assertThat(orderEntryList).hasSize(databaseSizeBeforeCreate + 1);
        OrderEntry testOrderEntry = orderEntryList.get(orderEntryList.size() - 1);
        assertThat(testOrderEntry.getBasePrice()).isEqualTo(DEFAULT_BASE_PRICE);
        assertThat(testOrderEntry.getTotalPrice()).isEqualTo(DEFAULT_TOTAL_PRICE);
        assertThat(testOrderEntry.getAmont()).isEqualTo(DEFAULT_AMONT);
        assertThat(testOrderEntry.getNo()).isEqualTo(DEFAULT_NO);

        // Validate the OrderEntry in Elasticsearch
        OrderEntry orderEntryEs = orderEntrySearchRepository.findOne(testOrderEntry.getId());
        assertThat(orderEntryEs).isEqualToComparingFieldByField(testOrderEntry);
    }

    @Test
    @Transactional
    public void createOrderEntryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderEntryRepository.findAll().size();

        // Create the OrderEntry with an existing ID
        orderEntry.setId(1L);
        OrderEntryDTO orderEntryDTO = orderEntryMapper.toDto(orderEntry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderEntryMockMvc.perform(post("/api/order-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderEntryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<OrderEntry> orderEntryList = orderEntryRepository.findAll();
        assertThat(orderEntryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrderEntries() throws Exception {
        // Initialize the database
        orderEntryRepository.saveAndFlush(orderEntry);

        // Get all the orderEntryList
        restOrderEntryMockMvc.perform(get("/api/order-entries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].basePrice").value(hasItem(DEFAULT_BASE_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].totalPrice").value(hasItem(DEFAULT_TOTAL_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].amont").value(hasItem(DEFAULT_AMONT)))
            .andExpect(jsonPath("$.[*].no").value(hasItem(DEFAULT_NO)));
    }

    @Test
    @Transactional
    public void getOrderEntry() throws Exception {
        // Initialize the database
        orderEntryRepository.saveAndFlush(orderEntry);

        // Get the orderEntry
        restOrderEntryMockMvc.perform(get("/api/order-entries/{id}", orderEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderEntry.getId().intValue()))
            .andExpect(jsonPath("$.basePrice").value(DEFAULT_BASE_PRICE.intValue()))
            .andExpect(jsonPath("$.totalPrice").value(DEFAULT_TOTAL_PRICE.intValue()))
            .andExpect(jsonPath("$.amont").value(DEFAULT_AMONT))
            .andExpect(jsonPath("$.no").value(DEFAULT_NO));
    }

    @Test
    @Transactional
    public void getNonExistingOrderEntry() throws Exception {
        // Get the orderEntry
        restOrderEntryMockMvc.perform(get("/api/order-entries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderEntry() throws Exception {
        // Initialize the database
        orderEntryRepository.saveAndFlush(orderEntry);
        orderEntrySearchRepository.save(orderEntry);
        int databaseSizeBeforeUpdate = orderEntryRepository.findAll().size();

        // Update the orderEntry
        OrderEntry updatedOrderEntry = orderEntryRepository.findOne(orderEntry.getId());
        updatedOrderEntry
            .basePrice(UPDATED_BASE_PRICE)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .amont(UPDATED_AMONT)
            .no(UPDATED_NO);
        OrderEntryDTO orderEntryDTO = orderEntryMapper.toDto(updatedOrderEntry);

        restOrderEntryMockMvc.perform(put("/api/order-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderEntryDTO)))
            .andExpect(status().isOk());

        // Validate the OrderEntry in the database
        List<OrderEntry> orderEntryList = orderEntryRepository.findAll();
        assertThat(orderEntryList).hasSize(databaseSizeBeforeUpdate);
        OrderEntry testOrderEntry = orderEntryList.get(orderEntryList.size() - 1);
        assertThat(testOrderEntry.getBasePrice()).isEqualTo(UPDATED_BASE_PRICE);
        assertThat(testOrderEntry.getTotalPrice()).isEqualTo(UPDATED_TOTAL_PRICE);
        assertThat(testOrderEntry.getAmont()).isEqualTo(UPDATED_AMONT);
        assertThat(testOrderEntry.getNo()).isEqualTo(UPDATED_NO);

        // Validate the OrderEntry in Elasticsearch
        OrderEntry orderEntryEs = orderEntrySearchRepository.findOne(testOrderEntry.getId());
        assertThat(orderEntryEs).isEqualToComparingFieldByField(testOrderEntry);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderEntry() throws Exception {
        int databaseSizeBeforeUpdate = orderEntryRepository.findAll().size();

        // Create the OrderEntry
        OrderEntryDTO orderEntryDTO = orderEntryMapper.toDto(orderEntry);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrderEntryMockMvc.perform(put("/api/order-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderEntry in the database
        List<OrderEntry> orderEntryList = orderEntryRepository.findAll();
        assertThat(orderEntryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrderEntry() throws Exception {
        // Initialize the database
        orderEntryRepository.saveAndFlush(orderEntry);
        orderEntrySearchRepository.save(orderEntry);
        int databaseSizeBeforeDelete = orderEntryRepository.findAll().size();

        // Get the orderEntry
        restOrderEntryMockMvc.perform(delete("/api/order-entries/{id}", orderEntry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean orderEntryExistsInEs = orderEntrySearchRepository.exists(orderEntry.getId());
        assertThat(orderEntryExistsInEs).isFalse();

        // Validate the database is empty
        List<OrderEntry> orderEntryList = orderEntryRepository.findAll();
        assertThat(orderEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchOrderEntry() throws Exception {
        // Initialize the database
        orderEntryRepository.saveAndFlush(orderEntry);
        orderEntrySearchRepository.save(orderEntry);

        // Search the orderEntry
        restOrderEntryMockMvc.perform(get("/api/_search/order-entries?query=id:" + orderEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].basePrice").value(hasItem(DEFAULT_BASE_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].totalPrice").value(hasItem(DEFAULT_TOTAL_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].amont").value(hasItem(DEFAULT_AMONT)))
            .andExpect(jsonPath("$.[*].no").value(hasItem(DEFAULT_NO)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderEntry.class);
        OrderEntry orderEntry1 = new OrderEntry();
        orderEntry1.setId(1L);
        OrderEntry orderEntry2 = new OrderEntry();
        orderEntry2.setId(orderEntry1.getId());
        assertThat(orderEntry1).isEqualTo(orderEntry2);
        orderEntry2.setId(2L);
        assertThat(orderEntry1).isNotEqualTo(orderEntry2);
        orderEntry1.setId(null);
        assertThat(orderEntry1).isNotEqualTo(orderEntry2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderEntryDTO.class);
        OrderEntryDTO orderEntryDTO1 = new OrderEntryDTO();
        orderEntryDTO1.setId(1L);
        OrderEntryDTO orderEntryDTO2 = new OrderEntryDTO();
        assertThat(orderEntryDTO1).isNotEqualTo(orderEntryDTO2);
        orderEntryDTO2.setId(orderEntryDTO1.getId());
        assertThat(orderEntryDTO1).isEqualTo(orderEntryDTO2);
        orderEntryDTO2.setId(2L);
        assertThat(orderEntryDTO1).isNotEqualTo(orderEntryDTO2);
        orderEntryDTO1.setId(null);
        assertThat(orderEntryDTO1).isNotEqualTo(orderEntryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(orderEntryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(orderEntryMapper.fromId(null)).isNull();
    }
}
