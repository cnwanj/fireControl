package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.Facility;
import com.gxuwz.fireControl.model.entity.InstallPoint;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
public interface FacilityService {
	boolean save(Facility facility);

	boolean delete(Facility facility);

	boolean update(Facility facility);

	Facility getOne(String facId);

	Result<Facility> findByPage(Facility facility, Integer page, Integer limit);

	Result<Facility> findByInsIdByPage(List<InstallPoint> installPointList, Facility facility, Integer page, Integer row);
}
