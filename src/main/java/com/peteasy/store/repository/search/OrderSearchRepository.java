package com.peteasy.store.repository.search;

import com.peteasy.store.domain.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Order entity.
 */
public interface OrderSearchRepository extends ElasticsearchRepository<Order, Long> {
}
