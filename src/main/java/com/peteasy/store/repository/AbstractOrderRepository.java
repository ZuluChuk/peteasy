package com.peteasy.store.repository;

import com.peteasy.store.domain.AbstractOrder;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbstractOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbstractOrderRepository extends JpaRepository<AbstractOrder, Long> {

}
