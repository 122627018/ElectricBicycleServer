package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.Option;
import com.wxxiaomi.ebs.dao.bean.Option2;


public interface OptionService {

	boolean insertOption(Option option);
	
	List<Option> getUserOptions(int userid);
	
	List<Option2> getUserOptions2(int userid);
}
