package com.actionbazaar.ejb;

import static com.actionbazaar.entity.MembershipLevelType.*;
import com.actionbazaar.entity.Member;
import com.actionbazaar.entity.MembershipLevel;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DiscountManagerBean implements DiscountManager {

	@EJB
	MembershipLevelManager membershipLevelManager;

	public DiscountManagerBean() {
	}

	public DiscountManagerBean(MembershipLevelManager membershipLevelManagerMock) {
		this.membershipLevelManager = membershipLevelManagerMock;
	}

	@Override
	public double findDiscount(Member member) {
		double discount = 0.0;
		MembershipLevel ml = membershipLevelManager.findByMemberId(member.getId());
		if (ml != null) {
			switch (ml.getType()) {
				case SILVER:
					discount = 0.05;
					break;
				case GOLD:
					discount = 0.10;
					break;
				case PLATINUM:
					discount = 0.12;
					break;
			}
		}
		return discount;
	}
}
