package com.peteasy.store.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.peteasy.store.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Product.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.AbstractOrder.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.AbstractOrder.class.getName() + ".entries", jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Order.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Cart.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.OrderEntry.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Customer.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Customer.class.getName() + ".orders", jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Customer.class.getName() + ".carts", jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Customer.class.getName() + ".shippingAddresses", jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Customer.class.getName() + ".billingAddresses", jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Address.class.getName(), jcacheConfiguration);
            cm.createCache(com.peteasy.store.domain.Country.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
