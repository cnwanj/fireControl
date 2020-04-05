package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.Facility;
import com.gxuwz.fireControl.model.entity.InstallPoint;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public interface FacilityDao extends BaseDao<Facility> {

	Result<Facility> findByPage(Facility facility, Integer page, Integer row);

	Facility getOneByFacName(String facName);

	/**
	 * 根据安装点编号分页查询
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月18日
	 * @param facility
	 * @param page
	 * @param row
	 * @return
	 */
	Result<Facility> findByInsIdByPage(List<InstallPoint> installPointList, Facility facility, Integer page, Integer row);
}
