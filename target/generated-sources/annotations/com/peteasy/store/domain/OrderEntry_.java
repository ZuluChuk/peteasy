package com.peteasy.store.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderEntry.class)
public abstract class OrderEntry_ {

	public static volatile SingularAttribute<OrderEntry, Integer> no;
	public static volatile SingularAttribute<OrderEntry, Product> product;
	public static volatile SingularAttribute<OrderEntry, Integer> amont;
	public static volatile SingularAttribute<OrderEntry, BigDecimal> totalPrice;
	public static volatile SingularAttribute<OrderEntry, Long> id;
	public static volatile SingularAttribute<OrderEntry, BigDecimal> basePrice;
	public static volatile SingularAttribute<OrderEntry, AbstractOrder> order;

}

