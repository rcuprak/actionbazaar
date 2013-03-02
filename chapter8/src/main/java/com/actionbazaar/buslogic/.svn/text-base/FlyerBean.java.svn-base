/**
 *  FlyerBean.java
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
package com.actionbazaar.buslogic;

import com.actionbazaar.model.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 * FlyerBean - demonstrtes an ad-hoc timer.
 */
@Stateless
public class FlyerBean {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("FlyerBean");
    
    /**
     * Reference to the timer service
     */
    @Resource
    private TimerService timerService;

    /**
     * Returns a list of scheduled timers
     * @return timers
     */
    public List<Timer> getScheduledFlyers() {
        Collection<Timer> timers = timerService.getTimers();
        return new ArrayList<Timer>(timers);
    }

    /**
     * Schedules a flyer to be sent out.
     * @param se - schedule expression
     * @param email - email
     */
    public void scheduleFlyer(ScheduleExpression se, Email email) {
        TimerConfig tc = new TimerConfig(email, true);
        Timer timer = timerService.createCalendarTimer(se, tc);
        logger.log(Level.INFO, "Flyer will be sent at: {0}", timer.getNextTimeout());
    }

    /**
     * Sends the message out...
     * @param timer - timer
     */
    @Timeout
    public void send(Timer timer) {
        if (timer.getInfo() instanceof Email) {
            Email email = (Email) timer.getInfo();
            // Retrieve bidders/sellers and email…
        }
    }
}
