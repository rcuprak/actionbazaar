package com.actionbazaar.chapter5.listing05;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.criteria.Order;

import com.actionbazaar.chapter5.listing04.SayHelloInterceptor;

@Stateless
public class OrderBean {
  @Interceptors(SayHelloInterceptor.class)
  public Order findOrderById(String id) { return null; }
}
