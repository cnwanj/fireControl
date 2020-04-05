package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月6日
 */
public interface ManageAreaFirstService {

	/**
	 * 根据单位管理员工号获取一级管辖域：1、首先根据单位管理员工号查询对应的单位信息（或者单位管理员信息）以便获取单位编号。 2、然后根据对应的单位编号查询对应的一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月6日
	 * @param userId 单位管理员工号
	 * @return
	 */
	List<ManageAreaFirst> getManageAreaFirstByDeptUserId(String userId);
	//后添
	/**
	 * 分页查询一级管辖域
	 * 
	 * @Description 分页查询一级管辖域
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @param userFirst 一级管辖员
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result findByPage(ManageAreaFirst manageAreaFirst, int page, int row);
	/**
	 * 保存一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst
	 * @return
	 */
	boolean save(ManageAreaFirst manageAreaFirst);
	/**
	 * 更新一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst
	 * @return
	 */
	boolean update(ManageAreaFirst manageAreaFirst);
	/**
	 * 删除一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param manageAreaFirst
	 * @return
	 */
	boolean delete(ManageAreaFirst manageAreaFirst);
	//后添
	/**
	 * 根据一级管辖员编号获取对应的一级管辖域信息。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @param afId 一级管辖域编号
	 * @return
	 */
	ManageAreaFirst getOne(String afId);
}
	
