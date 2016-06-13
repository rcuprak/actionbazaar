package com.actionbazaar.chapter5.listing06;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.actionbazaar.chapter5.listing04.SayHelloInterceptor;

@Stateless
@Interceptors(SayHelloInterceptor.class)
public class OrderBean {  }
