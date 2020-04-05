package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.InstallPointDao;
import com.gxuwz.fireControl.model.entity.InstallPoint;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
@Repository("installPointDao")
@SuppressWarnings("unchecked")
public class InstallPointDaoImpl extends BaseDaoImpl<InstallPoint> implements InstallPointDao {

	@Override
	public Result<InstallPoint> findByPage(InstallPoint installpoint, Integer page, Integer row) {
		getSession().getSessionFactory().getCurrentSession();
		String queryString = null;
		// 如果二级管辖域编号不为空，则根据二级管辖域编号查询出所负责的安装点
		if (null != installpoint.getAs().getAsId()) {
			queryString = "from InstallPoint a join fetch a.as where a.as.asId ='" + installpoint.getAs().getAsId() + "'";
		} else {// 否则根据二级管辖员工号查询出所负责的安装点
			queryString = "from InstallPoint a join fetch a.as where a.as.usId ='" + installpoint.getAs().getUsId() + "'";
		}
		if (null != installpoint.getInsName()) {
			queryString = queryString + " and a.insName like '%" + installpoint.getInsName() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<InstallPoint>) super.find(queryString, null, null, start, limit);
	}

	// 查询安装点是否存在重名
	@Override
	public InstallPoint getOneByInsName(String insName) {
		Criteria criteria = getSession().createCriteria(InstallPoint.class);
		List<InstallPoint> list = criteria.add(Restrictions.eq("insName", insName)).list();
		return list.size() == 0 ? null : list.get(0);
	}

	public InstallPoint getList(InstallPoint installpoint) {
		String queryString = "from InstallPoint where insName = '" + installpoint.getInsName() + "'";
		List<InstallPoint> list = super.findByHql(queryString, null);
		return list.size() == 0 ? null : list.get(0);

	}

	@Override
	public List<InstallPoint> findAll(String asId) {// 根据二级管辖域编号查询出该管辖域下的所有安装点
		String queryString = "from InstallPoint a join fetch a.as where a.as.asId ='" + asId + "'";
		List<InstallPoint> list = super.findByHql(queryString, null);
		return list;
	}
}
