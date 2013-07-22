package com.actionbazaar.ejb;

import com.actionbazaar.entity.MembershipLevel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MembershipLevelManagerBean implements MembershipLevelManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	public MembershipLevel insert(MembershipLevel membershipLevel) {
		System.out.printf("ENTER: insert()");
		System.out.printf(
			"Before insert: %s, %s", String.valueOf(
			  membershipLevel.getId()), membershipLevel.getType());
		em.persist(membershipLevel);
		System.out.printf(
		  "Before insert: %s, %s", String.valueOf(
				membershipLevel.getId()), membershipLevel.getType());
		return membershipLevel;
	}

	@Override
	public MembershipLevel findByMemberId(Long memberId) {
		TypedQuery<MembershipLevel> query 
		  = em.createQuery(
				"select ml from MembershipLevel ml JOIN ml.member m where m.id = ?1", 
				  MembershipLevel.class);
		query.setParameter(1, memberId);
		MembershipLevel result = null;
		List<MembershipLevel> list = query.getResultList();
		if (!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}
}
