package com.peteasy.store.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, Country> country;
	public static volatile SingularAttribute<Address, Customer> shippingCustomer;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, Customer> billingCustomer;
	public static volatile SingularAttribute<Address, String> addressLine1;
	public static volatile SingularAttribute<Address, String> addressLine2;
	public static volatile SingularAttribute<Address, Long> id;

}

