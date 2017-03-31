package com.wxxiaomi.ebs.dao.inter;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.Option;

public interface OptionDao {

	int insertOption(Option option);
	
	List<Option> getUserOptions(int userid);
}
