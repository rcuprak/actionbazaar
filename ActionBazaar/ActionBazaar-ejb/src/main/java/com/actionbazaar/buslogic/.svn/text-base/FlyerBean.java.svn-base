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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.jms.ConnectionFactory;
import com.actionbazaar.persistence.Email;

/**
 * This bean is responsible for scheduling fliers to be sent.
 * @author Ryan Cuprak
 */
@Stateless
public class FlyerBean {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("FlyerBean");

    /**
     * Connection Factory
     */
    @Resource(name="jms/QueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    /**
     * Timer Service
     */
    @Resource
    private TimerService timerService;
    
    public List<Timer> getScheduledFlyers() {
        Collection<Timer> timers = timerService.getTimers();
        return new ArrayList<Timer>(timers);
    }

    public void scheduleFlyer(ScheduleExpression se, Email email) {
        TimerConfig tc = new TimerConfig(email,true);
        Timer timer = timerService.createCalendarTimer(se,tc);
        logger.info("Flyer will be sent at: "+ timer.getNextTimeout());
    }

    @Timeout
    public void send(Timer timer) {
        if(timer.getInfo() instanceof Email) {
            Email email = (Email)timer.getInfo();
        }
    }
}
