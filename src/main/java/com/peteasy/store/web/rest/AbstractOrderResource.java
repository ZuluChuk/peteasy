package com.peteasy.store.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.peteasy.store.service.AbstractOrderService;
import com.peteasy.store.web.rest.util.HeaderUtil;
import com.peteasy.store.service.dto.AbstractOrderDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing AbstractOrder.
 */
@RestController
@RequestMapping("/api")
public class AbstractOrderResource {

    private final Logger log = LoggerFactory.getLogger(AbstractOrderResource.class);

    private static final String ENTITY_NAME = "abstractOrder";

    private final AbstractOrderService abstractOrderService;

    public AbstractOrderResource(AbstractOrderService abstractOrderService) {
        this.abstractOrderService = abstractOrderService;
    }

    /**
     * POST  /abstract-orders : Create a new abstractOrder.
     *
     * @param abstractOrderDTO the abstractOrderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abstractOrderDTO, or with status 400 (Bad Request) if the abstractOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/abstract-orders")
    @Timed
    public ResponseEntity<AbstractOrderDTO> createAbstractOrder(@RequestBody AbstractOrderDTO abstractOrderDTO) throws URISyntaxException {
        log.debug("REST request to save AbstractOrder : {}", abstractOrderDTO);
        if (abstractOrderDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new abstractOrder cannot already have an ID")).body(null);
        }
        AbstractOrderDTO result = abstractOrderService.save(abstractOrderDTO);
        return ResponseEntity.created(new URI("/api/abstract-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /abstract-orders : Updates an existing abstractOrder.
     *
     * @param abstractOrderDTO the abstractOrderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abstractOrderDTO,
     * or with status 400 (Bad Request) if the abstractOrderDTO is not valid,
     * or with status 500 (Internal Server Error) if the abstractOrderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/abstract-orders")
    @Timed
    public ResponseEntity<AbstractOrderDTO> updateAbstractOrder(@RequestBody AbstractOrderDTO abstractOrderDTO) throws URISyntaxException {
        log.debug("REST request to update AbstractOrder : {}", abstractOrderDTO);
        if (abstractOrderDTO.getId() == null) {
            return createAbstractOrder(abstractOrderDTO);
        }
        AbstractOrderDTO result = abstractOrderService.save(abstractOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abstractOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /abstract-orders : get all the abstractOrders.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of abstractOrders in body
     */
    @GetMapping("/abstract-orders")
    @Timed
    public List<AbstractOrderDTO> getAllAbstractOrders(@RequestParam(required = false) String filter) {
        if ("order-is-null".equals(filter)) {
            log.debug("REST request to get all AbstractOrders where order is null");
            return abstractOrderService.findAllWhereOrderIsNull();
        }
        if ("cart-is-null".equals(filter)) {
            log.debug("REST request to get all AbstractOrders where cart is null");
            return abstractOrderService.findAllWhereCartIsNull();
        }
        log.debug("REST request to get all AbstractOrders");
        return abstractOrderService.findAll();
        }

    /**
     * GET  /abstract-orders/:id : get the "id" abstractOrder.
     *
     * @param id the id of the abstractOrderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abstractOrderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/abstract-orders/{id}")
    @Timed
    public ResponseEntity<AbstractOrderDTO> getAbstractOrder(@PathVariable Long id) {
        log.debug("REST request to get AbstractOrder : {}", id);
        AbstractOrderDTO abstractOrderDTO = abstractOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abstractOrderDTO));
    }

    /**
     * DELETE  /abstract-orders/:id : delete the "id" abstractOrder.
     *
     * @param id the id of the abstractOrderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/abstract-orders/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbstractOrder(@PathVariable Long id) {
        log.debug("REST request to delete AbstractOrder : {}", id);
        abstractOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/abstract-orders?query=:query : search for the abstractOrder corresponding
     * to the query.
     *
     * @param query the query of the abstractOrder search
     * @return the result of the search
     */
    @GetMapping("/_search/abstract-orders")
    @Timed
    public List<AbstractOrderDTO> searchAbstractOrders(@RequestParam String query) {
        log.debug("REST request to search AbstractOrders for query {}", query);
        return abstractOrderService.search(query);
    }

}
