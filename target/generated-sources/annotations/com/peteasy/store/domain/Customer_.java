package com.peteasy.store.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> firstName;
	public static volatile SingularAttribute<Customer, String> lastName;
	public static volatile SingularAttribute<Customer, String> phoneNumber;
	public static volatile SetAttribute<Customer, Cart> carts;
	public static volatile SingularAttribute<Customer, ZonedDateTime> registrationDate;
	public static volatile SetAttribute<Customer, Address> billingAddresses;
	public static volatile SetAttribute<Customer, Order> orders;
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, String> email;
	public static volatile SetAttribute<Customer, Address> shippingAddresses;

}

