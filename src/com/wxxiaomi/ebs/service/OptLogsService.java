package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.bean.OptionLogs;

public interface OptLogsService {

	/**
	 * 获取某个用户的所有动态
	 * @param userid
	 * @return
	 */
	List<OptionLogs> getUserLogs(int userid);
	
	/**
	 * 为某个动态点赞
	 * @param opt_id
	 * @return
	 */
	boolean starOption(int opt_id);
	
	/**
	 * 插入一条动态
	 * @param opt
	 * @return
	 */
	boolean insertOption(OptionLogs opt);
	
}
