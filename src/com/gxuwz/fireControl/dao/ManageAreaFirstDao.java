package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;

public interface ManageAreaFirstDao extends BaseDao<ManageAreaFirst> {

	/**
	 * 根据一级管辖员工号查询对应的一级管辖域信息
	 * 
	 * @Description TODO
	 * @author ；王海明
	 * @date 2020年2月5日
	 * @param ufId 一级管辖员工号
	 * @return
	 */
	ManageAreaFirst getManageAreaFirstByUfId(String ufId);
 
	/**
	 * 根据对应的单位编号查询对应的一级管辖域信息
	 * 
	 * @Description TODO
	 * @author ；刘家乐
	 * @date 2020年2月6日
	 * @param deptId 单位编号
	 * @return
	 */
	
	List<ManageAreaFirst> getManageAreaFirstByDeptId(String deptId);
	
	//后添
	/**
	 * 分页查询一级管辖域
	 * 
	 * @Description 分页查询一级管辖域
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst 一级管辖域
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result<ManageAreaFirst> findByPage(ManageAreaFirst manageAreaFirst, int page, int row);
	/**
	 * 根据对应的一级管辖域编号查询对应的一级管辖域信息
	 * 
	 * @Description TODO
	 * @author ；刘家乐
	 * @date 2020年2月6日
	 * @param afId 一级管辖域编号
	 * @return
	 */
	ManageAreaFirst getManageAreaFirstByAfId(String afId);

	

	
}
	
	

