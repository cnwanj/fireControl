package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.ManageAreaSecondDao;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
@SuppressWarnings("unchecked")
@Repository("manageAreaSecondDao")
public class ManageAreaSecondDaoImpl extends BaseDaoImpl<ManageAreaSecond> implements ManageAreaSecondDao {

	@Override
	public List<ManageAreaSecond> getManageAreaSecondByAfId(String afId) {
		Criteria criteria = getSession().createCriteria(ManageAreaSecond.class);
		criteria.add(Restrictions.eq("af.afId", afId));
		return criteria.list();
	}

	@Override
	public Result<ManageAreaSecond> findByPage(ManageAreaSecond manageAreaSecond, Integer page, Integer row) {
		String queryString = "from ManageAreaSecond where 1=1";
		if (null != manageAreaSecond.getAf() && null != manageAreaSecond.getAf().getAfId()) {
			queryString = queryString + " and af like '%" + manageAreaSecond.getAf().getAfId() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<ManageAreaSecond>) super.find(queryString, null, null, start, limit);
	}
	
	@Override
	public List<ManageAreaSecond> findAll(String asId) {
		String queryString="from ManageAreaSecond where 1=1 and asId ='"
				+asId+"'";
		List<ManageAreaSecond> list = super.findByHql(queryString, null);
		return list;
	}


}
