package com.actionbazaar.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.actionbazaar.account.Employee;
import com.actionbazaar.account.User;
import com.actionbazaar.buslogic.PickListBean;
import com.actionbazaar.persistence.Category;
import com.actionbazaar.service.UserService;

/**
 * Performs an initial load. Loads an initial user an email so that
 * this system is somewhat usable.
 * @author Ryan Cuprak
 */
@Startup
@Singleton
public class ApplicationBootstrap {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ApplicationBootstrap");

    /**
     * Persistence Context
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager entityManager;

    /**
     * User service
     */
    @EJB
    private UserService userService;

    @Inject
    private PickListBean pickListBean;

    /**
     * Initializes the system.
     */
    @PostConstruct
    public void initialize() {
        Query q = entityManager.createQuery("select count(u) from User u");
        Long l = (Long)q.getSingleResult();
        if(l == 0) {
            logger.info("Initializing with default user...");
            User user = new Employee("Admin","Admin","admin","password",new Date(),"Default Administrator");
            user.addGroup("admin");
            userService.createUser(user);
            logger.info("Loading categories...");
            List<Category> categories = new LinkedList<Category>();
            categories.add(new Category("Books", new String[] {"textbook","manual"}));
            categories.add(new Category("Cameras", new String[] {"canon","nikon","kodak","leica"}));
            categories.add(new Category("Computers", new String[] {"apple","dell","hp"}));
            categories.add(new Category("Crafts", new String[] {"knitting",""}));
            categories.add(new Category("Dolls", new String[] {"barbie","garbage pale kids","bratz"}));
            categories.add(new Category("Music", new String[] {"classical","alternative","pop"}));
            categories.add(new Category("Instruments", new String[] {"trombone","trumpet","cello","violin"}));
            
            Category ford = new Category("Ford",new String[] {"car","ford"});
            ford.addCategory(new Category("Fusion",new String[] {"v6","hydbrid","sedan",""}));
            
            Category cars = new Category("car",new String[]{"vehicle","transportation","property"});
            cars.addCategory(ford);
            
            categories.add(cars);
            
            pickListBean.addCategories(categories);
        }
       
    }
}
