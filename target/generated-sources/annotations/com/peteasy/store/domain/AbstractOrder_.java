package com.peteasy.store.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractOrder.class)
public abstract class AbstractOrder_ {

	public static volatile SingularAttribute<AbstractOrder, BigDecimal> total;
	public static volatile SetAttribute<AbstractOrder, OrderEntry> entries;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> shippingCost;
	public static volatile SingularAttribute<AbstractOrder, Address> shippingAddress;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> tax;
	public static volatile SingularAttribute<AbstractOrder, Long> id;
	public static volatile SingularAttribute<AbstractOrder, BigDecimal> subTotal;
	public static volatile SingularAttribute<AbstractOrder, Address> billingAddress;
	public static volatile SingularAttribute<AbstractOrder, Cart> cart;
	public static volatile SingularAttribute<AbstractOrder, Order> order;

}

