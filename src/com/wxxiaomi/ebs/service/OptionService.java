package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.bean.Option;

public interface OptionService {

	boolean insertOption(Option option);
	
	List<Option> getUserOptions(int userid);
}
