package com.actionbazaar.ejb;

import com.actionbazaar.entity.Member;
import com.actionbazaar.entity.MembershipLevel;
import com.actionbazaar.entity.MembershipLevelType;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DiscountManagerBeanArquillianTest {

  @Deployment
  public static JavaArchive createDeployment() {
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
      .addPackage(DiscountManagerBean.class.getPackage())
      .addPackage(Member.class.getPackage())
      .addAsResource("META-INF/persistence.xml");
    System.out.println(jar.toString(true));
    return jar;
  }

  @EJB
  MemberManager memberManager;
  @EJB
  MembershipLevelManager membershipLevelManager;
  @EJB
  DiscountManager discountManager;

  @Before
  public void insertTestData() {
    Member member = new Member();
    member.setId(1L);
    member.setUsername("junitGoldMember");
    memberManager.insert(member);

    MembershipLevel membershipLevel = new MembershipLevel();
    membershipLevel.setMember(member);
    membershipLevel.setType(MembershipLevelType.GOLD);
    membershipLevelManager.insert(membershipLevel);
  }

  @Test
  public void userGetsGoldDiscount() {
    Member member = memberManager.find(1L);
    double discount = discountManager.findDiscount(member);
    assertEquals(0.10, discount, 0.0);
  }
}