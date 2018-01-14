package com.peteasy.store.service;

import com.peteasy.store.service.dto.AbstractOrderDTO;
import java.util.List;

/**
 * Service Interface for managing AbstractOrder.
 */
public interface AbstractOrderService {

    /**
     * Save a abstractOrder.
     *
     * @param abstractOrderDTO the entity to save
     * @return the persisted entity
     */
    AbstractOrderDTO save(AbstractOrderDTO abstractOrderDTO);

    /**
     *  Get all the abstractOrders.
     *
     *  @return the list of entities
     */
    List<AbstractOrderDTO> findAll();
    /**
     *  Get all the AbstractOrderDTO where Order is null.
     *
     *  @return the list of entities
     */
    List<AbstractOrderDTO> findAllWhereOrderIsNull();
    /**
     *  Get all the AbstractOrderDTO where Cart is null.
     *
     *  @return the list of entities
     */
    List<AbstractOrderDTO> findAllWhereCartIsNull();

    /**
     *  Get the "id" abstractOrder.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AbstractOrderDTO findOne(Long id);

    /**
     *  Delete the "id" abstractOrder.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the abstractOrder corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @return the list of entities
     */
    List<AbstractOrderDTO> search(String query);
}
