package com.peteasy.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A AbstractOrder.
 */
@Entity
@Table(name = "abstract_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "abstractorder")
public class AbstractOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_total", precision=10, scale=2)
    private BigDecimal subTotal;

    @Column(name = "total", precision=10, scale=2)
    private BigDecimal total;

    @Column(name = "tax", precision=10, scale=2)
    private BigDecimal tax;

    @Column(name = "shipping_cost", precision=10, scale=2)
    private BigDecimal shippingCost;

    @OneToOne
    @JoinColumn(unique = true)
    private Address shippingAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private Address billingAddress;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderEntry> entries = new HashSet<>();

    @OneToOne(mappedBy = "abstractOrder")
    @JsonIgnore
    private Order order;

    @OneToOne(mappedBy = "abstractOrder")
    @JsonIgnore
    private Cart cart;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public AbstractOrder subTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public AbstractOrder total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public AbstractOrder tax(BigDecimal tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public AbstractOrder shippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public AbstractOrder shippingAddress(Address address) {
        this.shippingAddress = address;
        return this;
    }

    public void setShippingAddress(Address address) {
        this.shippingAddress = address;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public AbstractOrder billingAddress(Address address) {
        this.billingAddress = address;
        return this;
    }

    public void setBillingAddress(Address address) {
        this.billingAddress = address;
    }

    public Set<OrderEntry> getEntries() {
        return entries;
    }

    public AbstractOrder entries(Set<OrderEntry> orderEntries) {
        this.entries = orderEntries;
        return this;
    }

    public AbstractOrder addEntries(OrderEntry orderEntry) {
        this.entries.add(orderEntry);
        orderEntry.setOrder(this);
        return this;
    }

    public AbstractOrder removeEntries(OrderEntry orderEntry) {
        this.entries.remove(orderEntry);
        orderEntry.setOrder(null);
        return this;
    }

    public void setEntries(Set<OrderEntry> orderEntries) {
        this.entries = orderEntries;
    }

    public Order getOrder() {
        return order;
    }

    public AbstractOrder order(Order order) {
        this.order = order;
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public AbstractOrder cart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
        AbstractOrder abstractOrder = (AbstractOrder) o;
        if (abstractOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abstractOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbstractOrder{" +
            "id=" + getId() +
            ", subTotal='" + getSubTotal() + "'" +
            ", total='" + getTotal() + "'" +
            ", tax='" + getTax() + "'" +
            ", shippingCost='" + getShippingCost() + "'" +
            "}";
    }
}
