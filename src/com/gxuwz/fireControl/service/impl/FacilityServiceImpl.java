package com.gxuwz.fireControl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.FacilityDao;
import com.gxuwz.fireControl.model.entity.Facility;
import com.gxuwz.fireControl.model.entity.InstallPoint;
import com.gxuwz.fireControl.service.FacilityService;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
@Transactional
@Service("facilityService")
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityDao facilitydao;
	private Class<Facility> fy = Facility.class;

	@Override
	public boolean save(Facility facility) {
		if (facilitydao.getOneByFacName(facility.getFacName()) == null) {
			// 假如为解绑状态，则该设备不属于任何安装点，但仍在列表中
			if (facility.getFacBind() == 0) {
				facility.setPoint(null);
			}
			facilitydao.save(facility);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Facility facility) {
		if (facilitydao.get(fy, facility.getFacId()) != null) {
			facilitydao.remove(fy, facility.getFacId());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(Facility facility) {
		if (facility.getFacBind() == 0) {
			facility.setPoint(null);
		}
		facilitydao.update(facility);
		return true;
	}

	@Override
	public Facility getOne(String facId) {
		return facilitydao.get(fy, facId);
	}

	@Override
	public Result<Facility> findByPage(Facility facility, Integer page, Integer limit) {
		return facilitydao.findByPage(facility, page, limit);
	}

	@Override
	public Result<Facility> findByInsIdByPage(List<InstallPoint> installPointList, Facility facility, Integer page, Integer row) {
		return facilitydao.findByInsIdByPage(installPointList, facility, page, row);
	}
}
