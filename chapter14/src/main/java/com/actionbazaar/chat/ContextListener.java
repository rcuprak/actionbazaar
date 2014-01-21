/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.actionbazaar.chat;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener that shuts-down running websockets.
 * @author Ryan Cuprak
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Chat Server
     */
    @EJB
    private ChatServer chatServer;
    
    /**
     * Context initialized
     * @param sce - servlet context
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        chatServer.shutdown();
    }
    
}
