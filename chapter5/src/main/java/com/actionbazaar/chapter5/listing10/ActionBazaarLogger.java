package com.actionbazaar.chapter5.listing10;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ActionBazaarLogger {
	@AroundInvoke
	public Object logMethodEntry(InvocationContext invocationContext)
			throws Exception {
		System.out.println("Entering method: "
				+ invocationContext.getMethod().getName());
		return invocationContext.proceed();
	}
}
