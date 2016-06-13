/**
 *  SecurityInterceptor.java
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.actionbazaar.security;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * SecurityInterceptor - Listing 6.8
 * To use this, change the BidManagerBean cancelBid method as follows:
 *  @Interceptors(SecurityInterceptor.class)	
 *  public void cancelBid(Bid bid) {…}
 */
public class SecurityInterceptor {

    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object checkUserRole(InvocationContext context)
            throws Exception {
        if (!sessionContext.isCallerInRole("CSR")) {
            throw new SecurityException("No permission to cancel bid.");
        }
        return context.proceed();
    }
}
