/**
 *  Bootstrap.java
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
package com.actionbazaar.setup;

import com.actionbazaar.account.Employee;
import com.actionbazaar.account.UserService;
import com.actionbazaar.buslogic.ItemManager;
import com.actionbazaar.model.Category;
import com.actionbazaar.model.Item;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Boostraps the application by adding an initial user.
 */
@Singleton
@Startup
public class Bootstrap {
    
    private static final Logger logger = Logger.getLogger("Bootstrap");
    
    /**
     * System startup
     */
    @Inject
    private SystemStartup systemStartup;
    
    /**
     * Post-construct
     */
    @PostConstruct
    public void postConstruct() {
        systemStartup.init();
    }
}
