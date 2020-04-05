package com.gxuwz.fireControl.service;

import java.util.List;

import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;

/**
 * 
 * @Description TODO
 * @author 王海明
 * @version v1.0
 * @date 2020年2月5日
 */
public interface ManageAreaSecondService {

	/**
	 * 根据一级管辖员工号获取二级管辖域：1、首先根据一级管辖员工号查询对应的一级管辖域信息（或者一级管辖员信息）以便获取一级管辖域编号。 2、然后根据对应的一级管辖域编号查询对应的二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月5日
	 * @param ufId 一级管辖员工号
	 * @return
	 */
	List<ManageAreaSecond> getManageAreaSecondByUfId(String ufId);
	
	public List<ManageAreaSecond> findAll(String asId);

	/**
	 * 分页查询二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param ufId 一级管辖员工号
	 * @param manageAreaSecond 二级管辖域
	 * @param page 当前页
	 * @param row 每页记录数
	 * @return
	 */
	Result<ManageAreaSecond> findByPage(String ufId, ManageAreaSecond manageAreaSecond, Integer page, Integer row);

	/**
	 * 保存二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param manageAreaSecond
	 * @return
	 */
	boolean save(ManageAreaSecond manageAreaSecond);

	/**
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param asId
	 * @return
	 */
	ManageAreaSecond getOne(String asId);

	/**
	 * 修改二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param manageAreaSecond
	 * @return
	 */
	boolean update(ManageAreaSecond manageAreaSecond);

	/**
	 * 删除二级管辖域信息
	 * 
	 * @Description TODO
	 * @author 王海明
	 * @date 2020年2月11日
	 * @param manageAreaSecond
	 * @return
	 */
	boolean delete(ManageAreaSecond manageAreaSecond);

}
