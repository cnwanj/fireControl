package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.Dept;

public interface DeptDao extends BaseDao<Dept> {

	Result<Dept> findByPage(Dept dept, Integer page, Integer row);
	
	List<Dept> findByUserId(String userId);

	void updateByUserId(String userId, String deptId);

	/**
	 * 根据单位管理员工号查询对应的单位信息
	 * 
	 * @Description TODO
	 * @author ；刘家乐
	 * @date 2020年2月7日
	 * @param userId 单位管理员工号
	 * @return
	 */
	Dept getDeptByDeptUserId(String userId);

}
