package com.actionbazaar.ejb;

import com.actionbazaar.entity.Member;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemberManagerBean implements MemberManager {

	@PersistenceContext
	private EntityManager em;

	public Member insert(Member u) {
		System.out.printf(
			"ENTER: insert()");
		System.out.printf(
			"Before persist: %s, %s", String.valueOf(u.getId()), u.getUsername());
		em.persist(u);
		System.out.printf(
			"After persist: %s, %s", String.valueOf(u.getId()), u.getUsername());
		return u;
	}

	public Member find(Long id) {
		System.out.printf("ENTER: find()");
		Member found = null;
		try {
			found = em.find(Member.class, id);
		} finally {
		}
		return found;
	}
}
