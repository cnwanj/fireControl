package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.WarnUserDao;
import com.gxuwz.fireControl.model.entity.WarnUser;

/**
 * @Description TODO
 * @author 马金典
 * @version v1.0
 * @date 2020 年2月10日
 */
@Repository("warnUserDao")
@SuppressWarnings("unchecked")
public class WarnUserDaoImpl extends BaseDaoImpl<WarnUser> implements WarnUserDao{

	@Override
	public Result<WarnUser> findByPage(WarnUser warnUser, Integer page, Integer row) {
		String queryString = "from WarnUser w join fetch w.point b where b.as ='"	
				 + warnUser.getPoint().getAs().getAsId() + "'";
			if (null != warnUser.getUserName()) {
				queryString = queryString + " and w.userName like '%"
						+ warnUser.getUserName() + "%'";
			}
			int start = (page - 1) * row;
			int limit = row;
			return (Result<WarnUser>) super.find(queryString, null, null, start,limit);
	}

	@Override
	public WarnUser getOneByLoginName(String loginName) {
		Criteria criteria = getSession().createCriteria(WarnUser.class);
		List<WarnUser> list = criteria.add(Restrictions.eq("loginName", loginName)).list();
		return list.size()==0?null:list.get(0);
	}

	@Override
	public List<WarnUser> findAll(String asId) {//根据二级管辖域编号找到该管辖域下的所有报警联系人
		String queryString="from WarnUser w join fetch w.point b where b.as ='"
				+asId+"'";
		List<WarnUser> list = super.findByHql(queryString, null);
		return list;
	}

}
