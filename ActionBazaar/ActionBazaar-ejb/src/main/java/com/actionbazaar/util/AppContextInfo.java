/**
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

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class - retrieves the current application URL which is used
 * for creating things like pop-up windows.
 * @author Ryan Cuprak
 */
@Named
@ApplicationScoped
public class AppContextInfo {

    /**
     * External context - cached
     */
    private String externalContextUrl;
    
    /**
     * Returns the application URL (server+port+context path)
     * @return server url
     */
    public String getApplicationURL() {
        synchronized (this) {
            if(externalContextUrl == null) {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
                externalContextUrl = request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "") + request.getContextPath();
            }
        }
        System.out.println("URL: " + externalContextUrl);
        return externalContextUrl;
    }
}
