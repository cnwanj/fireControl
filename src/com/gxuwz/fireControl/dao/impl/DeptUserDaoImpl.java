package com.gxuwz.fireControl.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.DeptUserDao;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;

/**
 * 单位管理员信息
 * @author 韦永恒  刘家乐
 * @date 2020.02.06
 */
@SuppressWarnings("unchecked")
@Repository("deptUserDao")
public class DeptUserDaoImpl extends BaseDaoImpl<DeptUser> implements DeptUserDao {

	// 单位管理员分页显示
	public Result<DeptUser> findByPage(DeptUser deptUser, int page, int row) {
		String queryString = "from DeptUser where 1=1";
		if (null != deptUser.getUserName()) {
			queryString = queryString + " and userName like '%"
					+ deptUser.getUserName() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<DeptUser>) super.find(queryString, null, null, start, limit);
	}
	
	// 根据用户名查询
	public DeptUser getOneByUserName(String loginName) {
		Criteria criteria = getSession().createCriteria(DeptUser.class);
		List<DeptUser> list = criteria.add(Restrictions.eq("loginName", loginName)).list();
		return list.size()==0?null:list.get(0);
	}

	@Override
	public DeptUser getAfByDeptId(String deptId) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(DeptUser.class);
		// 添加查询条件:单位编号
		criteria.add(Restrictions.eq("dept.deptId", deptId));
		// 查询结果
		List<DeptUser> list = criteria.list();
		return list.size() == 0 ? null : list.get(0);
	}

	@Override
	public List<ManageUserFirst> getManageUserFirstByDeptUserId(String userId) {
		Criteria criteria = getSession().createCriteria(ManageUserFirst.class);
		criteria.add(Restrictions.eq("deptUser.userId", userId));
		return criteria.list();
	}

}
