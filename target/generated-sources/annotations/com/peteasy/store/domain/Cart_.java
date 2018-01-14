package com.peteasy.store.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cart.class)
public abstract class Cart_ {

	public static volatile SingularAttribute<Cart, String> code;
	public static volatile SingularAttribute<Cart, AbstractOrder> abstractOrder;
	public static volatile SingularAttribute<Cart, Long> id;
	public static volatile SingularAttribute<Cart, Customer> customer;

}

