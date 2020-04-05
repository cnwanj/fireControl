package com.gxuwz.fireControl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
import com.gxuwz.fireControl.service.DeptUserService;
import com.gxuwz.fireControl.service.ManageAreaFirstService;
import com.gxuwz.fireControl.service.ManageUserFirstService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月6日
 */
public class ManageAreaFirstAction extends BaseAction implements ModelDriven<ManageAreaFirst>, Preparable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ManageAreaFirst manageAreaFirst;
	// 单位管理员编号 后添
	private String userId;
	private String deptUserId;
	private String deptId;
	// 一级管辖域编号 后添
	private String afId;
	// 后添
	private ManageUserFirst userFirst;

	// 一级管辖员集合 后添
	private List<ManageUserFirst> list;

	@Autowired
	private ManageAreaFirstService manageAreaFirstService;
	// 后添
	@Autowired
	private ManageUserFirstService manageUserFirstService;
	@Autowired
	private DeptUserService deptUserService;

	public void prepare() throws Exception {
		if (manageAreaFirst == null) {
			manageAreaFirst = new ManageAreaFirst();
		}

	}

	public ManageAreaFirst getModel() {
		return manageAreaFirst == null ? new ManageAreaFirst() : manageAreaFirst;
	}

	/**
	 * 分页显示一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @return
	 */
	public String findByPage() {
		DeptUser deptUser = deptUserService.getOne(userId);
		String deptId = deptUser.getDept().getDeptId();
		Dept dept = new Dept();
		manageAreaFirst.setDept(dept);
		manageAreaFirst.getDept().setDeptId(deptId);
		pageResult = manageAreaFirstService.findByPage(manageAreaFirst, getPage(), getRow());
		forwardView = "/pages/deptUser/manageAreaFirst/manageAreaFirst_list.jsp";
		return SUCCESS;
	}

	/**
	 * 1、显示一级管辖域信息添加页面。 2、根据单位管理员工号获取一级管辖域。3、根据一级管辖员工号获取对应的一级管辖员信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @return
	 */
	public String toManageAreaFirstAddPage() {
		deptId = deptUserService.getOne(userId).getDept().getDeptId();
		list = manageUserFirstService.getManageUserFirstByDeptUserId(userId);
		forwardView = "/pages/deptUser/manageAreaFirst/manageAreaFirst_save.jsp";
		return SUCCESS;
	}

	/**
	 * 1、显示一级管辖域信息修改页面。 2、根据单位管理员工号获取一级管辖域
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @return
	 */
	public String toManageAreaFirstUpdatePage() {
		manageAreaFirst = manageAreaFirstService.getOne(manageAreaFirst.getAfId());
		list = manageUserFirstService.getManageUserFirstByDeptUserId(userId);
		forwardView = "/pages/deptUser/manageAreaFirst/manageAreaFirst_update.jsp";
		return SUCCESS;
	}

	/**
	 * 保存一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @return
	 */
	public String save() {
		manageAreaFirst.setAfId(code.dateRandom());
		if ("".equals(manageAreaFirst.getUfId())) {
			manageAreaFirst.setUfId(null);
		}
		// 保存一级管辖域信息
		targ = manageAreaFirstService.save(manageAreaFirst);
		res.put("targ", targ);
		return SUCCESS;

	}

	/**
	 * 更新一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月8日
	 * @return
	 */
	public String update() {
		if ("".equals(manageAreaFirst.getUfId())) {
			manageAreaFirst.setUfId(null);
		}
		// 修改二级管辖域信息
		targ = manageAreaFirstService.update(manageAreaFirst);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 删除一级管辖域信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String delete() {
		System.out.println();
		targ = manageAreaFirstService.delete(manageAreaFirst);
		res.put("targ", targ);
		return SUCCESS;
	}

	public ManageAreaFirst getManageAreaFirst() {
		return manageAreaFirst;
	}

	public void setManageAreaFirst(ManageAreaFirst manageAreaFirst) {
		this.manageAreaFirst = manageAreaFirst;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptUserId() {
		return deptUserId;
	}

	public void setDeptUserId(String deptUserId) {
		this.deptUserId = deptUserId;
	}

	public List<ManageUserFirst> getList() {
		return list;
	}

	public void setList(List<ManageUserFirst> list) {
		this.list = list;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
