package com.actionbazaar.ejb;

import com.actionbazaar.entity.MembershipLevel;
import javax.ejb.Local;

@Local
public interface MembershipLevelManager {

	MembershipLevel insert(MembershipLevel membershipLevel);

	MembershipLevel findByMemberId(Long memberId);
}
