/**
 *  EJB 3 in Action
 *  Book: http://www.manning.com/panda/
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
package com.actionbazaar.buslogic;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import com.actionbazaar.persistence.Item;

/**
 *
 * @author Ryan Cuprak
 */
@Singleton
@Startup
@DependsOn("SystemInitializer")
public class FeaturedItemBean implements FeaturedItem {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("FeaturedItem");

    /**
     * Currently featured item
     */
    private Item featuredItem;

    /**
     * Initializes the featured item
     */
    @PostConstruct
    protected synchronized void init() {
        featuredItem = loadFeaturedItem();
    }

    protected Item loadFeaturedItem() {
        return null;
    }

    @Override
    public Item getFeaturedItem() {
        return featuredItem;
    }

}
