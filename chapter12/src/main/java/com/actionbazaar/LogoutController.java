/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rcuprak
 */
@Named
@RequestScoped
public class LogoutController {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("LogoutController");
    
    /**
     * Injects the FacesContext
     */
    @Inject
    private FacesContext facesContext;
    
    /**
     * Invalidates the current session
     * @return login page
     */
    public String logout() {
        logger.info("Performing logout..");
        facesContext.getExternalContext().invalidateSession();
        return NavigationRules.HOME.getRule();
    }
    
}
