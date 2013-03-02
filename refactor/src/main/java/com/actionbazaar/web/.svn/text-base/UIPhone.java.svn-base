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
package com.actionbazaar.web;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 * Custom JSF component for handling phone number input.
 * @author Ryan Cuprak
 */
@FacesComponent("ui-component")
public class UIPhone extends UIInput implements NamingContainer {

    /**
     * Component resource bundle
     */
    public static String COMPONENT_BUNDLE = "com.actionbazaar.component";

    /**
     * Area code constant
     */
    public static String AREA_CODE = "areaCode";

    /**
     * Prefix
     */
    public static String PREFIX = "prefix";

    /**
     * Line number
     */
    public static String LINE_NUMBER = "lineNumber";

    /**
     * Returns the identifier of the component's family.
     * @return family identifier
     */
    @Override
    public String getFamily() {
        return "javax.faces.NamingContainer";
    }

    /**
     * Performs the encoding - pulling the value out from the model and storing it in the UI
     * @param context - context
     * @throws IOException - IOException
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        PhoneNumber phone = (PhoneNumber)getValue();
        UIInput areaCode = (UIInput)findComponent(AREA_CODE);
        UIInput prefix = (UIInput)findComponent(PREFIX);
        UIInput lineNumber = (UIInput)findComponent(LINE_NUMBER);
        if(phone != null) {
            areaCode.setValue(phone.getAreaCode());
            prefix.setValue(phone.getPrefix());
            lineNumber.setValue(phone.getLineNumber());
        }
        super.encodeBegin(context);
    }

    /**
     * Validates the phone number field
     * @param context - context
     */
    @Override
    public void processValidators(FacesContext context) {
        Object value = getAttributes().get("required");
        boolean required = false;
        if(value != null && (Boolean)value) {
            required = (Boolean)value;
        }
        UIInput areaCode = (UIInput)findComponent(AREA_CODE);
        UIInput prefix = (UIInput)findComponent(PREFIX);
        UIInput lineNumber = (UIInput)findComponent(LINE_NUMBER);
        int provided = 0;
        String areaCodeStr = (String)areaCode.getSubmittedValue();
        if(areaCodeStr.length() < 3 && required) {
            areaCode.setValid(false);
        } else if (areaCodeStr.length() == 3) {
            provided = provided | 0x1;
        }
        String prefixStr = (String)prefix.getSubmittedValue();
        if(prefixStr.length() < 3 && required) {
            prefix.setValid(false);
        } else if(prefixStr.length()  == 3) {
            provided = provided | 0x2;
        }
        String lineNumberStr = (String)lineNumber.getSubmittedValue();
        if(lineNumberStr.length() < 4 && required) {
            lineNumber.setValid(false);
        } else if (lineNumberStr.length() == 4) {
            provided = provided | 0x4;
        }
        if(required && provided != 7) {
            String requiredMessage = (String)getAttributes().get("requiredMessage");
            context.addMessage(lineNumber.getClientId(),new FacesMessage(
                FacesMessage.SEVERITY_ERROR,requiredMessage,requiredMessage));
        }
        if(provided != 0 && provided != 7) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Locale locale = null;
            if(facesContext != null && facesContext.getViewRoot() != null) {
                locale = facesContext.getViewRoot().getLocale();

                if(locale == null)
                    locale = Locale.getDefault();
            }
            ResourceBundle bundle = ResourceBundle.getBundle(COMPONENT_BUNDLE,locale);
            context.addMessage(lineNumber.getClientId(),new FacesMessage(
                FacesMessage.SEVERITY_ERROR,bundle.getString("invalid_phone"),bundle.getString("invalid_phone")));
        }
        if(!required && provided > 0) {
            if((provided & 0x1) != 1) {
                areaCode.setValid(false);
            }
            if((provided & 0x2) != 2) {
                prefix.setValid(false);
            }
            if((provided & 0x4) != 4) {
                lineNumber.setValid(false);
            }

        }
        super.processValidators(context);
    }

    /**
     * Decodes the incoming request
     * @param context - faces context
     */
    @Override
    public void decode(FacesContext context) {
        super.decode(context);
        UIInput areaCode = (UIInput)findComponent(AREA_CODE);
        UIInput prefix = (UIInput)findComponent(PREFIX);
        UIInput lineNumber = (UIInput)findComponent(LINE_NUMBER);
        PhoneNumber pn = new PhoneNumber();
        pn.setAreaCode((String)areaCode.getSubmittedValue());
        pn.setPrefix((String) prefix.getSubmittedValue());
        pn.setLineNumber((String) lineNumber.getSubmittedValue());
        setValue(pn);
    }

}
