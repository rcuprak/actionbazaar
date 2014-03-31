package com.actionbazaar.ejb;

import com.actionbazaar.entity.Member;
import javax.ejb.Local;

@Local
public interface DiscountManager {

	double findDiscount(Member user);
}
