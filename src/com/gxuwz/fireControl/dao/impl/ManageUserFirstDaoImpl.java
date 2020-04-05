package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.ManageUserFirstDao;
import com.gxuwz.fireControl.dao.ManageUserSecondDao;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月7日
 */
@SuppressWarnings("unchecked")
@Repository("manageUserFirstDao")
public class ManageUserFirstDaoImpl extends BaseDaoImpl<ManageUserFirst> implements ManageUserFirstDao {

	@Override
	public Result<ManageUserFirst> findByPage(ManageUserFirst userFirst, Integer page, Integer row) {
		String queryString = "from ManageUserFirst where 1=1";
		if (null != userFirst.getDeptUser()) {
			queryString = queryString + " and deptUser like '%" + userFirst.getDeptUser().getUserId() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<ManageUserFirst>) super.find(queryString, null, null, start, limit);
	}

	@Override
	public ManageUserFirst getManageUserFirstByLoginName(String loginName) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(ManageUserFirst.class);
		// 添加查询条件:一级管辖员用户名
		criteria.add(Restrictions.eq("loginName", loginName));
		// 查询结果
		List<ManageUserFirst> list = criteria.list();
		return list.size() == 0 ? null : list.get(0);
	}

}
