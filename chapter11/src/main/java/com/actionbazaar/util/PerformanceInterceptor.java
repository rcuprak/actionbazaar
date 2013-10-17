/**
 *  PerformanceInterceptor.java
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
package com.actionbazaar.util;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Performance Interceptor
 * @author Ryan Cuprak
 */
@PerformanceMonitor @Interceptor
public class PerformanceInterceptor {
    
    /**
     * Default constructor - logs a message so that we know the interceptor has been instantiated.
     */
    public PerformanceInterceptor() {
        Logger.getLogger("PerformanceInterceptor").log(Level.INFO, "Performance Interceptor Instantiated.");
    }
    
    @AroundInvoke
    public Object monitor(InvocationContext ctx) throws Exception {
        long start = new Date().getTime();
        try {
            return ctx.proceed();
        } finally {
            long elapsed = new Date().getTime() - start;
            Logger.getLogger("PerformanceInterceptor").log(Level.INFO, "Elapsed time: {0}", elapsed);
        }
    } 
}
