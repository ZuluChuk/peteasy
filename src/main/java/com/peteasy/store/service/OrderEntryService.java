package com.peteasy.store.service;

import com.peteasy.store.service.dto.OrderEntryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderEntry.
 */
public interface OrderEntryService {

    /**
     * Save a orderEntry.
     *
     * @param orderEntryDTO the entity to save
     * @return the persisted entity
     */
    OrderEntryDTO save(OrderEntryDTO orderEntryDTO);

    /**
     *  Get all the orderEntries.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OrderEntryDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" orderEntry.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OrderEntryDTO findOne(Long id);

    /**
     *  Delete the "id" orderEntry.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the orderEntry corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<OrderEntryDTO> search(String query, Pageable pageable);
}
