package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.WarnUser;
/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public interface WarnUserService {

	boolean save(WarnUser warnUser);

	boolean delete(WarnUser warnUser);

	boolean update(WarnUser warnUser);
	
	WarnUser getOne(String userId);
	
	Result<WarnUser> findByPage(WarnUser warnUser,Integer page, Integer limit);
	
	public List<WarnUser> findAll(String asId);
}
