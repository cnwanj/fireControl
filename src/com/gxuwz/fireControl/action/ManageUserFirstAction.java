package com.gxuwz.fireControl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.ManageAreaSecond;
import com.gxuwz.fireControl.model.entity.ManageUserFirst;
import com.gxuwz.fireControl.model.entity.ManageUserSecond;
import com.gxuwz.fireControl.model.entity.SysLog;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.ManageAreaFirstService;
import com.gxuwz.fireControl.service.ManageAreaSecondService;
import com.gxuwz.fireControl.service.ManageUserFirstService;
import com.gxuwz.fireControl.service.ManageUserSecondService;
import com.gxuwz.fireControl.service.SysUserService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 2020年2月7日
 */
public class ManageUserFirstAction extends BaseAction implements ModelDriven<ManageUserFirst>, Preparable {

	private static final long serialVersionUID = 1L;

	private ManageUserFirst userFirst;
	// 单位管理员编号    
	private String userId;
	private String deptUserId;
	// 一级管辖域集合    
	private List<ManageAreaFirst> list;
	
	@Autowired
	private ManageUserFirstService manageUserFirstService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ManageAreaFirstService manageAreaFirstService;
	
	/**
	 * 分页显示一级管辖员信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String findByPage() {
		pageResult = manageUserFirstService.findByPage(userFirst, getPage(), getRow());
		forwardView = "/pages/deptUser/manageUserFirst/manageUserFirst_list.jsp";
		return SUCCESS;

	}

	/**
	 * 1、显示一级管辖员信息添加页面。 2、根据单位管理员工号获取一级管辖域。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String toManageUserFirstAddPage() {
		forwardView = "/pages/deptUser/manageUserFirst/manageUserFirst_save.jsp";
		list = manageAreaFirstService.getManageAreaFirstByDeptUserId(userId);
		return SUCCESS;
	}

	/**
	 * 1、显示一级管辖员信息修改页面。 2、根据单位管理员工号获取一级管辖域。3、根据一级管辖员工号获取对应的一级管辖员信息。
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String toManageUserFirstUpdatePage() {
		forwardView = "/pages/deptUser/manageUserFirst/manageUserFirst_update.jsp";
		list = manageAreaFirstService.getManageAreaFirstByDeptUserId(deptUserId);
		userFirst = manageUserFirstService.getOne(userFirst.getUfId());
		return SUCCESS;

	}

	/**
	 * 保存一级管辖员信息和用户登录信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String save() {
		userFirst.setUfId(code.dateRandom());
		// 保存一级管辖员信息
		targ = manageUserFirstService.save(userFirst);
		// 如果一级管辖员信息保存成功
		if (targ == true) {
			// 1、实例化SysUsers对象
			SysUsers users = new SysUsers();
			// 2、设置各个属性
			users.setLoginName(userFirst.getLoginName());
			users.setLoginPwd(userFirst.getLoginPwd());
			users.setUserCode(userFirst.getUfId());
			users.setUserName(userFirst.getUfName());
			users.setUserPhone(userFirst.getUfPhone());
			// 3、用户类型设置为4: 一级管辖员
			users.setUserType(4);
			// 4、保存用户登录信息到sys_users表
			sysUserService.save(users);
		}
		res.put("targ", targ);
		return SUCCESS;

	}

	/**
	 * 更新一级管辖员信息（注：同时也要更新用户登录信息）
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String update() {
		System.out.println(userFirst);
		targ = manageUserFirstService.update(userFirst);
		System.out.println("targ-------:" + targ);
		res.put("targ", targ);
		return SUCCESS;
	}

	/**
	 * 删除一级管辖员信息（注：同时也要删除用户登录信息）
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 2020年2月7日
	 * @return
	 */
	public String delete() {
		targ = manageUserFirstService.delete(userFirst);
		res.put("targ", targ);
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (userFirst == null) {
			userFirst = new ManageUserFirst();
		}

	}

	public ManageUserFirst getModel() {

		return userFirst == null ? new ManageUserFirst() : userFirst;
	}

	public ManageUserFirst getUserFirst() {
		return userFirst;
	}

	public void setUserFirst(ManageUserFirst userFirst) {
		this.userFirst = userFirst;
	}


	public String getDeptUserId() {
		return deptUserId;
	}

	public void setDeptUserId(String deptUserId) {
		this.deptUserId = deptUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ManageAreaFirst> getList() {
		return list;
	}

	public void setList(List<ManageAreaFirst> list) {
		this.list = list;
	}

}
