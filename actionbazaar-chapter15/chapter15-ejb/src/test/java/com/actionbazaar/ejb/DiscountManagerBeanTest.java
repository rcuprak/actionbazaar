package com.actionbazaar.ejb;

import com.actionbazaar.entity.Member;
import com.actionbazaar.entity.MembershipLevel;
import com.actionbazaar.entity.MembershipLevelType;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiscountManagerBeanTest {

  private Member member;
  private MembershipLevel membershipLevel;
  private MembershipLevelManager membershipLevelManagerMock;
  private DiscountManagerBean bean;

  @Before
  public void setUp() {
    member = new Member();
    member.setId(11L);
    member.setUsername("junit123");

    membershipLevel = new MembershipLevel();
    membershipLevel.setId(44L);
    membershipLevel.setMember(member);

    membershipLevelManagerMock = mock(MembershipLevelManager.class);
    when(membershipLevelManagerMock.findByMemberId(
      member.getId())).thenReturn(membershipLevel);

    bean = new DiscountManagerBean(membershipLevelManagerMock);
  }

  @Test
  public void userGetsGoldDiscount() {
    membershipLevel.setType(MembershipLevelType.GOLD);
    double discount = bean.findDiscount(member);
    assertEquals(0.10, discount, 0.0);
  }

  @Test
  public void userGetsSilverDiscount() {
    membershipLevel.setType(MembershipLevelType.SILVER);
    double discount = bean.findDiscount(member);
    assertEquals(0.05, discount, 0.0);
  }

  @Test
  public void userGetsPlatinumDiscount() {
    membershipLevel.setType(MembershipLevelType.PLATINUM);
    double discount = bean.findDiscount(member);
    assertEquals(0.12, discount, 0.0);
  }

  @Test
  public void userGetsNoDiscountBecauseNotGoldSilverOrPlatinum() {
    membershipLevel.setType(MembershipLevelType.BRONZE);
    double discount = bean.findDiscount(member);
    assertEquals(0.0, discount, 0.0);
  }

  @Test
  public void userGetsNoDiscountBecauseNotAMember() {
    when(membershipLevelManagerMock.findByMemberId(member.getId())).thenReturn(null);
    double discount = bean.findDiscount(member);
    assertEquals(0.0, discount, 0.0);
  }
  
}