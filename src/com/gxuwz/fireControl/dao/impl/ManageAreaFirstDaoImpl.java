package com.gxuwz.fireControl.dao.impl;

import java.util.List;
/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月6日
 */

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.impl.BaseDaoImpl;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.dao.ManageAreaFirstDao;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
@SuppressWarnings("unchecked")
@Repository("manageAreaFirstDao")
public class ManageAreaFirstDaoImpl extends BaseDaoImpl<ManageAreaFirst> implements ManageAreaFirstDao {

	@Override
	public ManageAreaFirst getManageAreaFirstByUfId(String ufId) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(ManageAreaFirst.class);
		// 添加查询条件:一级管辖员工号
		criteria.add(Restrictions.eq("ufId", ufId));
		// 查询结果
		List<ManageAreaFirst> list = criteria.list();
		return list.size() == 0 ? null : list.get(0);
	}
	
	/**
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @version v1.0
	 * @date 2020年2月6日
	 */
	@Override
	public List<ManageAreaFirst> getManageAreaFirstByDeptId(String deptId) {
		Criteria criteria = getSession().createCriteria(ManageAreaFirst.class);
		criteria.add(Restrictions.eq("dept.deptId", deptId));
		return criteria.list();
	}
    //后添
	@Override
	public Result<ManageAreaFirst> findByPage(ManageAreaFirst manageAreaFirst, int page, int row) {
		String queryString = "from ManageAreaFirst where 1=1";
		if (null != manageAreaFirst.getDept()) {
			queryString = queryString + " and dept like '%" + manageAreaFirst.getDept().getDeptId() + "%'";
		}
		int start = (page - 1) * row;
		int limit = row;
		return (Result<ManageAreaFirst>) super.find(queryString, null, null, start, limit);
	}
    //后添
	@Override
	public ManageAreaFirst getManageAreaFirstByAfId(String afId) {
		// 创建离线对象
		Criteria criteria = getSession().createCriteria(ManageAreaFirst.class);
		// 添加查询条件:一级管辖域编号
		criteria.add(Restrictions.eq("afId", afId));
		// 查询结果
		List<ManageAreaFirst> list = criteria.list();
		return list.size() == 0 ? null : list.get(0);
	}
}

	


	
