package com.actionbazaar.ejb;

import com.actionbazaar.entity.Member;
import javax.ejb.Local;

@Local
public interface MemberManager {

	Member insert(Member u);

	Member find(Long id);
}
