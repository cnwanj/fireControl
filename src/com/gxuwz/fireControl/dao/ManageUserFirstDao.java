package com.gxuwz.fireControl.dao;

import java.util.List;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;

/**
 * 
 * @Description 一级管辖员dao层
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
public interface ManageUserFirstDao extends BaseDao<ManageUserFirst> {

	/**
	 * 分页查询一级管辖员
	 * 
	 * @Description 分页查询一级管辖员
	 * @author 王海明
	 * @date 2020年2月5日
	 * @param userFirst 一级管辖员
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result<ManageUserFirst> findByPage(ManageUserFirst userFirst, Integer page, Integer row);

	/**
	 * 根据一级管辖员用户名查询一级管辖员信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param loginName 一级管辖员用户名
	 * @return
	 */
	ManageUserFirst getManageUserFirstByLoginName(String loginName);
	
	//后添

}