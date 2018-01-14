package com.peteasy.store.web.rest;

import com.peteasy.store.PeteasyApp;

import com.peteasy.store.domain.AbstractOrder;
import com.peteasy.store.repository.AbstractOrderRepository;
import com.peteasy.store.service.AbstractOrderService;
import com.peteasy.store.repository.search.AbstractOrderSearchRepository;
import com.peteasy.store.service.dto.AbstractOrderDTO;
import com.peteasy.store.service.mapper.AbstractOrderMapper;
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
 * Test class for the AbstractOrderResource REST controller.
 *
 * @see AbstractOrderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeteasyApp.class)
public class AbstractOrderResourceIntTest {

    private static final BigDecimal DEFAULT_SUB_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUB_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAX = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAX = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SHIPPING_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_SHIPPING_COST = new BigDecimal(2);

    @Autowired
    private AbstractOrderRepository abstractOrderRepository;

    @Autowired
    private AbstractOrderMapper abstractOrderMapper;

    @Autowired
    private AbstractOrderService abstractOrderService;

    @Autowired
    private AbstractOrderSearchRepository abstractOrderSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAbstractOrderMockMvc;

    private AbstractOrder abstractOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AbstractOrderResource abstractOrderResource = new AbstractOrderResource(abstractOrderService);
        this.restAbstractOrderMockMvc = MockMvcBuilders.standaloneSetup(abstractOrderResource)
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
    public static AbstractOrder createEntity(EntityManager em) {
        AbstractOrder abstractOrder = new AbstractOrder()
            .subTotal(DEFAULT_SUB_TOTAL)
            .total(DEFAULT_TOTAL)
            .tax(DEFAULT_TAX)
            .shippingCost(DEFAULT_SHIPPING_COST);
        return abstractOrder;
    }

    @Before
    public void initTest() {
        abstractOrderSearchRepository.deleteAll();
        abstractOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbstractOrder() throws Exception {
        int databaseSizeBeforeCreate = abstractOrderRepository.findAll().size();

        // Create the AbstractOrder
        AbstractOrderDTO abstractOrderDTO = abstractOrderMapper.toDto(abstractOrder);
        restAbstractOrderMockMvc.perform(post("/api/abstract-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the AbstractOrder in the database
        List<AbstractOrder> abstractOrderList = abstractOrderRepository.findAll();
        assertThat(abstractOrderList).hasSize(databaseSizeBeforeCreate + 1);
        AbstractOrder testAbstractOrder = abstractOrderList.get(abstractOrderList.size() - 1);
        assertThat(testAbstractOrder.getSubTotal()).isEqualTo(DEFAULT_SUB_TOTAL);
        assertThat(testAbstractOrder.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testAbstractOrder.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testAbstractOrder.getShippingCost()).isEqualTo(DEFAULT_SHIPPING_COST);

        // Validate the AbstractOrder in Elasticsearch
        AbstractOrder abstractOrderEs = abstractOrderSearchRepository.findOne(testAbstractOrder.getId());
        assertThat(abstractOrderEs).isEqualToComparingFieldByField(testAbstractOrder);
    }

    @Test
    @Transactional
    public void createAbstractOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abstractOrderRepository.findAll().size();

        // Create the AbstractOrder with an existing ID
        abstractOrder.setId(1L);
        AbstractOrderDTO abstractOrderDTO = abstractOrderMapper.toDto(abstractOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbstractOrderMockMvc.perform(post("/api/abstract-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AbstractOrder> abstractOrderList = abstractOrderRepository.findAll();
        assertThat(abstractOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAbstractOrders() throws Exception {
        // Initialize the database
        abstractOrderRepository.saveAndFlush(abstractOrder);

        // Get all the abstractOrderList
        restAbstractOrderMockMvc.perform(get("/api/abstract-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abstractOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.intValue())))
            .andExpect(jsonPath("$.[*].shippingCost").value(hasItem(DEFAULT_SHIPPING_COST.intValue())));
    }

    @Test
    @Transactional
    public void getAbstractOrder() throws Exception {
        // Initialize the database
        abstractOrderRepository.saveAndFlush(abstractOrder);

        // Get the abstractOrder
        restAbstractOrderMockMvc.perform(get("/api/abstract-orders/{id}", abstractOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(abstractOrder.getId().intValue()))
            .andExpect(jsonPath("$.subTotal").value(DEFAULT_SUB_TOTAL.intValue()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.intValue()))
            .andExpect(jsonPath("$.shippingCost").value(DEFAULT_SHIPPING_COST.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAbstractOrder() throws Exception {
        // Get the abstractOrder
        restAbstractOrderMockMvc.perform(get("/api/abstract-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbstractOrder() throws Exception {
        // Initialize the database
        abstractOrderRepository.saveAndFlush(abstractOrder);
        abstractOrderSearchRepository.save(abstractOrder);
        int databaseSizeBeforeUpdate = abstractOrderRepository.findAll().size();

        // Update the abstractOrder
        AbstractOrder updatedAbstractOrder = abstractOrderRepository.findOne(abstractOrder.getId());
        updatedAbstractOrder
            .subTotal(UPDATED_SUB_TOTAL)
            .total(UPDATED_TOTAL)
            .tax(UPDATED_TAX)
            .shippingCost(UPDATED_SHIPPING_COST);
        AbstractOrderDTO abstractOrderDTO = abstractOrderMapper.toDto(updatedAbstractOrder);

        restAbstractOrderMockMvc.perform(put("/api/abstract-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractOrderDTO)))
            .andExpect(status().isOk());

        // Validate the AbstractOrder in the database
        List<AbstractOrder> abstractOrderList = abstractOrderRepository.findAll();
        assertThat(abstractOrderList).hasSize(databaseSizeBeforeUpdate);
        AbstractOrder testAbstractOrder = abstractOrderList.get(abstractOrderList.size() - 1);
        assertThat(testAbstractOrder.getSubTotal()).isEqualTo(UPDATED_SUB_TOTAL);
        assertThat(testAbstractOrder.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testAbstractOrder.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testAbstractOrder.getShippingCost()).isEqualTo(UPDATED_SHIPPING_COST);

        // Validate the AbstractOrder in Elasticsearch
        AbstractOrder abstractOrderEs = abstractOrderSearchRepository.findOne(testAbstractOrder.getId());
        assertThat(abstractOrderEs).isEqualToComparingFieldByField(testAbstractOrder);
    }

    @Test
    @Transactional
    public void updateNonExistingAbstractOrder() throws Exception {
        int databaseSizeBeforeUpdate = abstractOrderRepository.findAll().size();

        // Create the AbstractOrder
        AbstractOrderDTO abstractOrderDTO = abstractOrderMapper.toDto(abstractOrder);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAbstractOrderMockMvc.perform(put("/api/abstract-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abstractOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the AbstractOrder in the database
        List<AbstractOrder> abstractOrderList = abstractOrderRepository.findAll();
        assertThat(abstractOrderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAbstractOrder() throws Exception {
        // Initialize the database
        abstractOrderRepository.saveAndFlush(abstractOrder);
        abstractOrderSearchRepository.save(abstractOrder);
        int databaseSizeBeforeDelete = abstractOrderRepository.findAll().size();

        // Get the abstractOrder
        restAbstractOrderMockMvc.perform(delete("/api/abstract-orders/{id}", abstractOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean abstractOrderExistsInEs = abstractOrderSearchRepository.exists(abstractOrder.getId());
        assertThat(abstractOrderExistsInEs).isFalse();

        // Validate the database is empty
        List<AbstractOrder> abstractOrderList = abstractOrderRepository.findAll();
        assertThat(abstractOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAbstractOrder() throws Exception {
        // Initialize the database
        abstractOrderRepository.saveAndFlush(abstractOrder);
        abstractOrderSearchRepository.save(abstractOrder);

        // Search the abstractOrder
        restAbstractOrderMockMvc.perform(get("/api/_search/abstract-orders?query=id:" + abstractOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abstractOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.intValue())))
            .andExpect(jsonPath("$.[*].shippingCost").value(hasItem(DEFAULT_SHIPPING_COST.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbstractOrder.class);
        AbstractOrder abstractOrder1 = new AbstractOrder();
        abstractOrder1.setId(1L);
        AbstractOrder abstractOrder2 = new AbstractOrder();
        abstractOrder2.setId(abstractOrder1.getId());
        assertThat(abstractOrder1).isEqualTo(abstractOrder2);
        abstractOrder2.setId(2L);
        assertThat(abstractOrder1).isNotEqualTo(abstractOrder2);
        abstractOrder1.setId(null);
        assertThat(abstractOrder1).isNotEqualTo(abstractOrder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AbstractOrderDTO.class);
        AbstractOrderDTO abstractOrderDTO1 = new AbstractOrderDTO();
        abstractOrderDTO1.setId(1L);
        AbstractOrderDTO abstractOrderDTO2 = new AbstractOrderDTO();
        assertThat(abstractOrderDTO1).isNotEqualTo(abstractOrderDTO2);
        abstractOrderDTO2.setId(abstractOrderDTO1.getId());
        assertThat(abstractOrderDTO1).isEqualTo(abstractOrderDTO2);
        abstractOrderDTO2.setId(2L);
        assertThat(abstractOrderDTO1).isNotEqualTo(abstractOrderDTO2);
        abstractOrderDTO1.setId(null);
        assertThat(abstractOrderDTO1).isNotEqualTo(abstractOrderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(abstractOrderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(abstractOrderMapper.fromId(null)).isNull();
    }
}
