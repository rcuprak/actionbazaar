package com.actionbazaar.ejb;

import com.actionbazaar.entity.Member;
import com.actionbazaar.entity.MembershipLevel;
import com.actionbazaar.entity.MembershipLevelType;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiscountManagerBeanEmbeddedTest {

  private static Context ctx;
  private static EJBContainer ejbContainer;
  private static String moduleName = System.getProperty("moduleName");

  @BeforeClass
  public static void setUpClass() {
    Map<String, Object> properties = new HashMap<>();
    properties.put("org.glassfish.ejb.embedded.glassfish.instance.root",
            "./src/test/domain");
    ejbContainer = EJBContainer.createEJBContainer(properties);
    ctx = ejbContainer.getContext();	
  }

  @AfterClass
  public static void tearDownClass() {
    if (ejbContainer != null) {
      ejbContainer.close();
    }
  }

  @Test
  public void userGetsGoldDiscount() throws NamingException {			
    Member member = new Member();
    member.setId(1L);
    member.setUsername("junitGoldMember");
    lookupMemberManager().insert(member);

    MembershipLevel membershipLevel = new MembershipLevel();
    membershipLevel.setMember(member);
    membershipLevel.setType(MembershipLevelType.GOLD);
    lookupMembershipLevelManager().insert(membershipLevel);

    DiscountManager discountManager = lookupDiscountManager();
    double discount = discountManager.findDiscount(member);
    assertEquals(0.10, discount, 0.0);
  }

  static DiscountManager lookupDiscountManager() 
  throws NamingException {
    return (DiscountManager)lookupEjb(EjbName.DiscountManagerBean);
  }

  static MemberManager lookupMemberManager() 
  throws NamingException {
    return (MemberManager)lookupEjb(EjbName.MemberManagerBean);
  }

  static MembershipLevelManager lookupMembershipLevelManager()
  throws NamingException {
    return (MembershipLevelManager)lookupEjb(
      EjbName.MembershipLevelManagerBean);
  }
  
  /** Common method for all JNDI lookup */
  static enum EjbName {
    DiscountManagerBean, MemberManagerBean, MembershipLevelManagerBean
  }
  static Object lookupEjb(EjbName ejbName) throws NamingException {
    try {
      return ctx.lookup("java:global/"+moduleName+"/"+ejbName);
    } catch (Throwable ignore) {
      moduleName = "classes";
      return ctx.lookup("java:global/"+moduleName+"/"+ejbName);
    }
  }
}