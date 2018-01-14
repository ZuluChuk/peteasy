package com.peteasy.store.repository.search;

import com.peteasy.store.domain.AbstractOrder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AbstractOrder entity.
 */
public interface AbstractOrderSearchRepository extends ElasticsearchRepository<AbstractOrder, Long> {
}
