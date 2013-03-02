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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.validator.CreditCardValidator;

/**
 * Validator that verifies the credit card number.
 * @author Ryan Cuprak
 */

public class CreditCardBeanValidator implements ConstraintValidator<CreditCardCheck,String> {

    /**
     * Verifies the credit card number
     * @param constraintAnnotation - annotation
     */
    @Override
    public void initialize(CreditCardCheck constraintAnnotation) {
        // Nothing to initialize
    }

    /**
     * Performs validation on the credit card.
     * @param value - value we are checking
     * @param context - context
     * @return true if the value is valid
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        CreditCardValidator c = new CreditCardValidator();
        return c.isValid(value);
    }
}
