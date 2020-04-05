package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.FacilityDao;
import com.gxuwz.fireControl.model.entity.Facility;
import com.gxuwz.fireControl.model.entity.InstallPoint;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
@Repository("facilityDao")
@SuppressWarnings("unchecked")
public class FacilityDaoImpl extends BaseDaoImpl<Facility> implements FacilityDao {

	@Override
	public Result<Facility> findByPage(Facility facility, Integer page, Integer row) {
		String queryString = "from Facility f join fetch f.warnUser w join fetch w.point p where p.as ='"
				+ facility.getPoint().getAs().getAsId() + "'";
		if (null != facility.getFacName()) {
			queryString = queryString + " and f.facName like '%" + facility.getFacName() + "%'";
		}
		int start = (page - 1) * row;
		return (Result<Facility>) super.find(queryString, null, null, start, row);
	}

	@Override
	public Facility getOneByFacName(String facName) {
		Criteria criteria = getSession().createCriteria(Facility.class);
		List<Facility> list = criteria.add(Restrictions.eq("facName", facName)).list();
		return list.size() == 0 ? null : list.get(0);
	}

	@Override
	public Result<Facility> findByInsIdByPage(List<InstallPoint> installPointList, Facility facility, Integer page, Integer row) {
		String queryString = "from Facility where 1=1";
		if (null != installPointList && installPointList.size() > 0) {
			for (InstallPoint installPoint : installPointList) {
				queryString = queryString + " or point.insId ='" + installPoint.getInsId() + "'";
			}
		} else if (null != facility.getPoint().getInsId()) {
			queryString = queryString + " and point.insId ='" + facility.getPoint().getInsId() + "'";
		}
		int start = (page - 1) * row;
		return (Result<Facility>) super.find(queryString, null, null, start, row);
	}

}
