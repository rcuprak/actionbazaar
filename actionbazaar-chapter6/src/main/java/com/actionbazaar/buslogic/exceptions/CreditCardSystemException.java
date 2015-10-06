/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.buslogic.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author rcuprak
 */
@ApplicationException(rollback=true)
public class CreditCardSystemException extends Exception {
    
}
