package com.actionbazaar.chapter5.listing04;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class SayHelloInterceptor {
  @AroundInvoke
  public Object sayHello(InvocationContext ctx) throws Exception {
    System.out.println("Hello Interceptor!");
    return ctx.proceed();
  }
}

