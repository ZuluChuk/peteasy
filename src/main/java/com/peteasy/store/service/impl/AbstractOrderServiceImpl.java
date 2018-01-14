package com.peteasy.store.service.impl;

import com.peteasy.store.service.AbstractOrderService;
import com.peteasy.store.domain.AbstractOrder;
import com.peteasy.store.repository.AbstractOrderRepository;
import com.peteasy.store.repository.search.AbstractOrderSearchRepository;
import com.peteasy.store.service.dto.AbstractOrderDTO;
import com.peteasy.store.service.mapper.AbstractOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AbstractOrder.
 */
@Service
@Transactional
public class AbstractOrderServiceImpl implements AbstractOrderService{

    private final Logger log = LoggerFactory.getLogger(AbstractOrderServiceImpl.class);

    private final AbstractOrderRepository abstractOrderRepository;

    private final AbstractOrderMapper abstractOrderMapper;

    private final AbstractOrderSearchRepository abstractOrderSearchRepository;
    public AbstractOrderServiceImpl(AbstractOrderRepository abstractOrderRepository, AbstractOrderMapper abstractOrderMapper, AbstractOrderSearchRepository abstractOrderSearchRepository) {
        this.abstractOrderRepository = abstractOrderRepository;
        this.abstractOrderMapper = abstractOrderMapper;
        this.abstractOrderSearchRepository = abstractOrderSearchRepository;
    }

    /**
     * Save a abstractOrder.
     *
     * @param abstractOrderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AbstractOrderDTO save(AbstractOrderDTO abstractOrderDTO) {
        log.debug("Request to save AbstractOrder : {}", abstractOrderDTO);
        AbstractOrder abstractOrder = abstractOrderMapper.toEntity(abstractOrderDTO);
        abstractOrder = abstractOrderRepository.save(abstractOrder);
        AbstractOrderDTO result = abstractOrderMapper.toDto(abstractOrder);
        abstractOrderSearchRepository.save(abstractOrder);
        return result;
    }

    /**
     *  Get all the abstractOrders.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AbstractOrderDTO> findAll() {
        log.debug("Request to get all AbstractOrders");
        return abstractOrderRepository.findAll().stream()
            .map(abstractOrderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the abstractOrders where Order is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<AbstractOrderDTO> findAllWhereOrderIsNull() {
        log.debug("Request to get all abstractOrders where Order is null");
        return StreamSupport
            .stream(abstractOrderRepository.findAll().spliterator(), false)
            .filter(abstractOrder -> abstractOrder.getOrder() == null)
            .map(abstractOrderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the abstractOrders where Cart is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<AbstractOrderDTO> findAllWhereCartIsNull() {
        log.debug("Request to get all abstractOrders where Cart is null");
        return StreamSupport
            .stream(abstractOrderRepository.findAll().spliterator(), false)
            .filter(abstractOrder -> abstractOrder.getCart() == null)
            .map(abstractOrderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one abstractOrder by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AbstractOrderDTO findOne(Long id) {
        log.debug("Request to get AbstractOrder : {}", id);
        AbstractOrder abstractOrder = abstractOrderRepository.findOne(id);
        return abstractOrderMapper.toDto(abstractOrder);
    }

    /**
     *  Delete the  abstractOrder by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AbstractOrder : {}", id);
        abstractOrderRepository.delete(id);
        abstractOrderSearchRepository.delete(id);
    }

    /**
     * Search for the abstractOrder corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AbstractOrderDTO> search(String query) {
        log.debug("Request to search AbstractOrders for query {}", query);
        return StreamSupport
            .stream(abstractOrderSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(abstractOrderMapper::toDto)
            .collect(Collectors.toList());
    }
}
