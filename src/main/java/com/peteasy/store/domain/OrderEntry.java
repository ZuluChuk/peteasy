package com.peteasy.store.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A OrderEntry.
 */
@Entity
@Table(name = "order_entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "orderentry")
public class OrderEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_price", precision=10, scale=2)
    private BigDecimal basePrice;

    @Column(name = "total_price", precision=10, scale=2)
    private BigDecimal totalPrice;

    @Column(name = "amont")
    private Integer amont;

    @Column(name = "no")
    private Integer no;

    @ManyToOne
    private Product product;

    @ManyToOne
    private AbstractOrder order;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public OrderEntry basePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderEntry totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmont() {
        return amont;
    }

    public OrderEntry amont(Integer amont) {
        this.amont = amont;
        return this;
    }

    public void setAmont(Integer amont) {
        this.amont = amont;
    }

    public Integer getNo() {
        return no;
    }

    public OrderEntry no(Integer no) {
        this.no = no;
        return this;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Product getProduct() {
        return product;
    }

    public OrderEntry product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AbstractOrder getOrder() {
        return order;
    }

    public OrderEntry order(AbstractOrder abstractOrder) {
        this.order = abstractOrder;
        return this;
    }

    public void setOrder(AbstractOrder abstractOrder) {
        this.order = abstractOrder;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderEntry orderEntry = (OrderEntry) o;
        if (orderEntry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderEntry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderEntry{" +
            "id=" + getId() +
            ", basePrice='" + getBasePrice() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", amont='" + getAmont() + "'" +
            ", no='" + getNo() + "'" +
            "}";
    }
}
