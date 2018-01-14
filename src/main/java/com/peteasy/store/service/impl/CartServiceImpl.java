package com.peteasy.store.service.impl;

import com.peteasy.store.service.CartService;
import com.peteasy.store.domain.Cart;
import com.peteasy.store.repository.CartRepository;
import com.peteasy.store.repository.search.CartSearchRepository;
import com.peteasy.store.service.dto.CartDTO;
import com.peteasy.store.service.mapper.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Cart.
 */
@Service
@Transactional
public class CartServiceImpl implements CartService{

    private final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    private final CartSearchRepository cartSearchRepository;
    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, CartSearchRepository cartSearchRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartSearchRepository = cartSearchRepository;
    }

    /**
     * Save a cart.
     *
     * @param cartDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CartDTO save(CartDTO cartDTO) {
        log.debug("Request to save Cart : {}", cartDTO);
        Cart cart = cartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        CartDTO result = cartMapper.toDto(cart);
        cartSearchRepository.save(cart);
        return result;
    }

    /**
     *  Get all the carts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Carts");
        return cartRepository.findAll(pageable)
            .map(cartMapper::toDto);
    }

    /**
     *  Get one cart by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CartDTO findOne(Long id) {
        log.debug("Request to get Cart : {}", id);
        Cart cart = cartRepository.findOne(id);
        return cartMapper.toDto(cart);
    }

    /**
     *  Delete the  cart by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cart : {}", id);
        cartRepository.delete(id);
        cartSearchRepository.delete(id);
    }

    /**
     * Search for the cart corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CartDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Carts for query {}", query);
        Page<Cart> result = cartSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(cartMapper::toDto);
    }
}
