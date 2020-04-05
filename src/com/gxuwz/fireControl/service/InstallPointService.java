package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.InstallPoint;
/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public interface InstallPointService {
	
	boolean save(InstallPoint installPoint);

	boolean delete(InstallPoint installPoint);

	boolean update(InstallPoint installPoint);
	
	InstallPoint getOne(Long insId);
	
	Result<InstallPoint> findByPage(InstallPoint installpoint,Integer page, Integer limit);
	
	public List<InstallPoint> findAll(String asId);
	
}
