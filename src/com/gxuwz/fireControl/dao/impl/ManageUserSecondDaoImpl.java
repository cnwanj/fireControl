package com.gxuwz.fireControl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.ManageUserSecondDao;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月4日
 */
@SuppressWarnings("unchecked")
@Repository("manageUserSecondDao")
public class ManageUserSecondDaoImpl extends BaseDaoImpl<ManageUserSecond> implements ManageUserSecondDao {

	@Override
	public Result<ManageUserSecond> findByPage(ManageUserSecond userSecond, Integer page, Integer row) {
		String queryString = "from ManageUserSecond where 1=1";
		if (null != userSecond.getUf()) {
			queryString = queryString + " and uf like '%" + userSecond.getUf().getUfId() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<ManageUserSecond>) super.find(queryString, null, null, start, limit);
	}

	@Override
	public ManageUserSecond getManageUserSecondByLoginName(String loginName) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(ManageUserSecond.class);
		// 添加查询条件:二级管辖员用户名
		criteria.add(Restrictions.eq("loginName", loginName));
		// 查询结果
		List<ManageUserSecond> list = criteria.list();
		return list.size() == 0 ? null : list.get(0);
	}

	@Override
	public List<ManageUserSecond> getManageUserSecondByUfId(String ufId) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(ManageUserSecond.class);
		// 添加查询条件:一级管辖员工号
		criteria.add(Restrictions.eq("uf.ufId", ufId));
		// 查询结果
		List<ManageUserSecond> list = criteria.list();
		return list.size() == 0 ? new ArrayList<ManageUserSecond>() : list;
	}

}
