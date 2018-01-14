package com.peteasy.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "registration_date")
    private ZonedDateTime registrationDate;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "shippingCustomer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Address> shippingAddresses = new HashSet<>();

    @OneToMany(mappedBy = "billingCustomer")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Address> billingAddresses = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Customer registrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Customer orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Customer addOrders(Order order) {
        this.orders.add(order);
        order.setCustomer(this);
        return this;
    }

    public Customer removeOrders(Order order) {
        this.orders.remove(order);
        order.setCustomer(null);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public Customer carts(Set<Cart> carts) {
        this.carts = carts;
        return this;
    }

    public Customer addCarts(Cart cart) {
        this.carts.add(cart);
        cart.setCustomer(this);
        return this;
    }

    public Customer removeCarts(Cart cart) {
        this.carts.remove(cart);
        cart.setCustomer(null);
        return this;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Address> getShippingAddresses() {
        return shippingAddresses;
    }

    public Customer shippingAddresses(Set<Address> addresses) {
        this.shippingAddresses = addresses;
        return this;
    }

    public Customer addShippingAddresses(Address address) {
        this.shippingAddresses.add(address);
        address.setShippingCustomer(this);
        return this;
    }

    public Customer removeShippingAddresses(Address address) {
        this.shippingAddresses.remove(address);
        address.setShippingCustomer(null);
        return this;
    }

    public void setShippingAddresses(Set<Address> addresses) {
        this.shippingAddresses = addresses;
    }

    public Set<Address> getBillingAddresses() {
        return billingAddresses;
    }

    public Customer billingAddresses(Set<Address> addresses) {
        this.billingAddresses = addresses;
        return this;
    }

    public Customer addBillingAddresses(Address address) {
        this.billingAddresses.add(address);
        address.setBillingCustomer(this);
        return this;
    }

    public Customer removeBillingAddresses(Address address) {
        this.billingAddresses.remove(address);
        address.setBillingCustomer(null);
        return this;
    }

    public void setBillingAddresses(Set<Address> addresses) {
        this.billingAddresses = addresses;
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
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            "}";
    }
}
