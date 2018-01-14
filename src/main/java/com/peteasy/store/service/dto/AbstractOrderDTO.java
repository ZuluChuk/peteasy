package com.peteasy.store.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AbstractOrder entity.
 */
public class AbstractOrderDTO implements Serializable {

    private Long id;

    private BigDecimal subTotal;

    private BigDecimal total;

    private BigDecimal tax;

    private BigDecimal shippingCost;

    private Long shippingAddressId;

    private Long billingAddressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long addressId) {
        this.shippingAddressId = addressId;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long addressId) {
        this.billingAddressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractOrderDTO abstractOrderDTO = (AbstractOrderDTO) o;
        if(abstractOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abstractOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbstractOrderDTO{" +
            "id=" + getId() +
            ", subTotal='" + getSubTotal() + "'" +
            ", total='" + getTotal() + "'" +
            ", tax='" + getTax() + "'" +
            ", shippingCost='" + getShippingCost() + "'" +
            "}";
    }
}
