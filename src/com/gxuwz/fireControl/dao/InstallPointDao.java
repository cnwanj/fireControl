package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.InstallPoint;
/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public interface InstallPointDao extends BaseDao<InstallPoint>{
	
	Result<InstallPoint> findByPage(InstallPoint installpoint,Integer page, Integer row);
	
	InstallPoint getOneByInsName(String insName);
	
	InstallPoint getList(InstallPoint installpoint);
	
	public List<InstallPoint> findAll(String asId);
}
