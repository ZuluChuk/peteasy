package com.peteasy.store.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, String> code;
	public static volatile SingularAttribute<Order, AbstractOrder> abstractOrder;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, Customer> customer;

}

