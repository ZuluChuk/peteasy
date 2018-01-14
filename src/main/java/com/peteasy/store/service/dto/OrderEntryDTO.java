package com.peteasy.store.service.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the OrderEntry entity.
 */
public class OrderEntryDTO implements Serializable {

    private Long id;

    private BigDecimal basePrice;

    private BigDecimal totalPrice;

    private Integer amont;

    private Integer no;

    private Long productId;

    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAmont() {
        return amont;
    }

    public void setAmont(Integer amont) {
        this.amont = amont;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long abstractOrderId) {
        this.orderId = abstractOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderEntryDTO orderEntryDTO = (OrderEntryDTO) o;
        if(orderEntryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderEntryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderEntryDTO{" +
            "id=" + getId() +
            ", basePrice='" + getBasePrice() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", amont='" + getAmont() + "'" +
            ", no='" + getNo() + "'" +
            "}";
    }
}
