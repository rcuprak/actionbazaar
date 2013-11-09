/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.miscellaneous;

import com.actionbazaar.listing02.TurtleShippingRequestMessageBean;
import java.io.File;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Michael Remijan</a>
 */
public class MessageBeanLifecycleMethods {
  PrintWriter printWriter;
  
  @PostConstruct
  void createPrintWriter() {   
    File f = new File(new File(System.getProperty("java.io.tmpdir"))
      , "ShippingMessages.txt");    
    try {
      printWriter = new PrintWriter(f);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    Logger.getLogger(TurtleShippingRequestMessageBean.class.getName())
      .log(Level.INFO, 
      String.format("Write to file: %s", f.getAbsolutePath()));
  }
  
  @PreDestroy
  void closePrintWriter() {
    printWriter.flush();
    printWriter.close();
  }
}
