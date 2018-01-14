package com.peteasy.store.service.impl;

import com.peteasy.store.service.OrderEntryService;
import com.peteasy.store.domain.OrderEntry;
import com.peteasy.store.repository.OrderEntryRepository;
import com.peteasy.store.repository.search.OrderEntrySearchRepository;
import com.peteasy.store.service.dto.OrderEntryDTO;
import com.peteasy.store.service.mapper.OrderEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderEntry.
 */
@Service
@Transactional
public class OrderEntryServiceImpl implements OrderEntryService{

    private final Logger log = LoggerFactory.getLogger(OrderEntryServiceImpl.class);

    private final OrderEntryRepository orderEntryRepository;

    private final OrderEntryMapper orderEntryMapper;

    private final OrderEntrySearchRepository orderEntrySearchRepository;
    public OrderEntryServiceImpl(OrderEntryRepository orderEntryRepository, OrderEntryMapper orderEntryMapper, OrderEntrySearchRepository orderEntrySearchRepository) {
        this.orderEntryRepository = orderEntryRepository;
        this.orderEntryMapper = orderEntryMapper;
        this.orderEntrySearchRepository = orderEntrySearchRepository;
    }

    /**
     * Save a orderEntry.
     *
     * @param orderEntryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderEntryDTO save(OrderEntryDTO orderEntryDTO) {
        log.debug("Request to save OrderEntry : {}", orderEntryDTO);
        OrderEntry orderEntry = orderEntryMapper.toEntity(orderEntryDTO);
        orderEntry = orderEntryRepository.save(orderEntry);
        OrderEntryDTO result = orderEntryMapper.toDto(orderEntry);
        orderEntrySearchRepository.save(orderEntry);
        return result;
    }

    /**
     *  Get all the orderEntries.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderEntryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderEntries");
        return orderEntryRepository.findAll(pageable)
            .map(orderEntryMapper::toDto);
    }

    /**
     *  Get one orderEntry by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderEntryDTO findOne(Long id) {
        log.debug("Request to get OrderEntry : {}", id);
        OrderEntry orderEntry = orderEntryRepository.findOne(id);
        return orderEntryMapper.toDto(orderEntry);
    }

    /**
     *  Delete the  orderEntry by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderEntry : {}", id);
        orderEntryRepository.delete(id);
        orderEntrySearchRepository.delete(id);
    }

    /**
     * Search for the orderEntry corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderEntryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderEntries for query {}", query);
        Page<OrderEntry> result = orderEntrySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(orderEntryMapper::toDto);
    }
}
