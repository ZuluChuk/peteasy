package com.peteasy.store.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.peteasy.store.service.OrderEntryService;
import com.peteasy.store.web.rest.util.HeaderUtil;
import com.peteasy.store.web.rest.util.PaginationUtil;
import com.peteasy.store.service.dto.OrderEntryDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing OrderEntry.
 */
@RestController
@RequestMapping("/api")
public class OrderEntryResource {

    private final Logger log = LoggerFactory.getLogger(OrderEntryResource.class);

    private static final String ENTITY_NAME = "orderEntry";

    private final OrderEntryService orderEntryService;

    public OrderEntryResource(OrderEntryService orderEntryService) {
        this.orderEntryService = orderEntryService;
    }

    /**
     * POST  /order-entries : Create a new orderEntry.
     *
     * @param orderEntryDTO the orderEntryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderEntryDTO, or with status 400 (Bad Request) if the orderEntry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-entries")
    @Timed
    public ResponseEntity<OrderEntryDTO> createOrderEntry(@RequestBody OrderEntryDTO orderEntryDTO) throws URISyntaxException {
        log.debug("REST request to save OrderEntry : {}", orderEntryDTO);
        if (orderEntryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new orderEntry cannot already have an ID")).body(null);
        }
        OrderEntryDTO result = orderEntryService.save(orderEntryDTO);
        return ResponseEntity.created(new URI("/api/order-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-entries : Updates an existing orderEntry.
     *
     * @param orderEntryDTO the orderEntryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderEntryDTO,
     * or with status 400 (Bad Request) if the orderEntryDTO is not valid,
     * or with status 500 (Internal Server Error) if the orderEntryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-entries")
    @Timed
    public ResponseEntity<OrderEntryDTO> updateOrderEntry(@RequestBody OrderEntryDTO orderEntryDTO) throws URISyntaxException {
        log.debug("REST request to update OrderEntry : {}", orderEntryDTO);
        if (orderEntryDTO.getId() == null) {
            return createOrderEntry(orderEntryDTO);
        }
        OrderEntryDTO result = orderEntryService.save(orderEntryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderEntryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-entries : get all the orderEntries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderEntries in body
     */
    @GetMapping("/order-entries")
    @Timed
    public ResponseEntity<List<OrderEntryDTO>> getAllOrderEntries(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of OrderEntries");
        Page<OrderEntryDTO> page = orderEntryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-entries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-entries/:id : get the "id" orderEntry.
     *
     * @param id the id of the orderEntryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderEntryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/order-entries/{id}")
    @Timed
    public ResponseEntity<OrderEntryDTO> getOrderEntry(@PathVariable Long id) {
        log.debug("REST request to get OrderEntry : {}", id);
        OrderEntryDTO orderEntryDTO = orderEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderEntryDTO));
    }

    /**
     * DELETE  /order-entries/:id : delete the "id" orderEntry.
     *
     * @param id the id of the orderEntryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-entries/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderEntry(@PathVariable Long id) {
        log.debug("REST request to delete OrderEntry : {}", id);
        orderEntryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-entries?query=:query : search for the orderEntry corresponding
     * to the query.
     *
     * @param query the query of the orderEntry search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/order-entries")
    @Timed
    public ResponseEntity<List<OrderEntryDTO>> searchOrderEntries(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of OrderEntries for query {}", query);
        Page<OrderEntryDTO> page = orderEntryService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/order-entries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
