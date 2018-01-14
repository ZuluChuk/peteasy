package com.peteasy.store.repository.search;

import com.peteasy.store.domain.OrderEntry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the OrderEntry entity.
 */
public interface OrderEntrySearchRepository extends ElasticsearchRepository<OrderEntry, Long> {
}
