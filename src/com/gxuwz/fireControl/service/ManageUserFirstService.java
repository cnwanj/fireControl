package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月7日
 */
public interface ManageUserFirstService {

	/**
	 * 分页查询一级管辖员
	 * 
	 * @Description 分页查询一级管辖员
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst 一级管辖员
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result<ManageUserFirst> findByPage(ManageUserFirst userFirst, Integer page, Integer row);

	/**
	 * 保存一级管辖员信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst
	 * @return
	 */
	boolean save(ManageUserFirst userFirst);

	/**
	 * 删除一级管辖员信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst
	 * @return
	 */
	boolean delete(ManageUserFirst userFirst);

	/**
	 * 更新一级管辖员信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst
	 * @return
	 */
	boolean update(ManageUserFirst userFirst);

	/**
	 * 根据一级管辖员工号获取对应的一级管辖员信息。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param ufId 一级管辖员工号
	 * @return
	 */
	ManageUserFirst getOne(String ufId);

	List<ManageUserFirst> getManageUserFirstByDeptUserId(String deptUserId);
	
	//后添
	
	
	

	
}
