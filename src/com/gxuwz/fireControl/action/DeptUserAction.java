package com.gxuwz.fireControl.action;

import java.util.ArrayList;
import java.util.List;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @Description 单位管理员信息管理
 * @author 韦永恒
 * @date 2020.02.09
 */
public class DeptUserAction  extends BaseAction implements ModelDriven<DeptUser>, Preparable {
	
	private static final long serialVersionUID = 1L;
	
	private DeptUser deptUser;
	private SysUsers sysUser;
	private Dept dept;
	private List<Dept> list = new ArrayList<Dept>();
	
	/**
	 * 分页查询
	 * @return
	 */
	public String findByPage(){
		pageResult = deptUserService.findByPage(deptUser, getPage(), getRow());
		forwardView = "/pages/sysUser/deptUser/deptUser_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 查询未添加单位管理员的  单位信息
	 * @return
	 */
	public String findByDept(){
		list = deptService.findByUserId("暂无");
		// 跳转到添加页面
		forwardView = "/pages/sysUser/deptUser/deptUser_save.jsp";
		return SUCCESS;
	}
	
	/**
	 * 添加单位管理员信息
	 * @return
	 */
	public String save(){
		String ran = code.dateRandom();
		deptUser.setUserId(ran);
		// 添加登录信息
		sysUser = new SysUsers(null, deptUser.getLoginName(),deptUser.getLoginPwd(),
				ran,deptUser.getUserName(),deptUser.getUserPhone(),3);
		targ = userService.save(sysUser);
		// 如果添加登录表成功则添加单位管理员信息
		if(targ){
			targ = deptUserService.save(deptUser);
			if(!targ){	// 如果添加失败，则删除原来登录信息
				userService.delete(sysUser);
			}else{		// 若添加成功，则修改单位信息中的单位管理员为null
				dept = deptService.getOne(deptUser.getDept().getDeptId());
				dept.setUserId(deptUser.getUserId());
				deptService.update(dept);
			}
		}
		res.put("targ", targ);
		deptUser.setUserName(null);
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		// 将单位信息中的  单位管理员编号修改为"暂无"
		deptUser = deptUserService.getOne(deptUser.getUserId());
		if(deptUser.getDept() != null){
			dept = deptService.getOne(deptUser.getDept().getDeptId());
			dept.setUserId("暂无");
			deptService.update(dept);
		}
		// 删除登录信息
		sysUser = userService.getOneByUserCode(deptUser.getUserId());
		if(sysUser != null){
			userService.delete(sysUser);
		}
		// 删除单位管理员
		targ = deptUserService.delete(deptUser);
		res.put("targ", targ);
		return SUCCESS;
	}
	
	/**
	 * 显示详情
	 * @return
	 */
	public String getOne(){
		deptUser = deptUserService.getOne(deptUser.getUserId());
		// 查询未添加单位管理员的单位信息
		list = deptService.findByUserId("暂无");
		forwardView = "/pages/sysUser/deptUser/deptUser_update.jsp";
		return SUCCESS;
	}
	
	/**
	 * 修改信息
	 * @return
	 */
	public String update(){
		// 根据工号查询登录表信息
		sysUser = userService.getOneByUserCode(deptUser.getUserId());
		if(sysUser != null){
			sysUser = new SysUsers(sysUser.getUserId(), deptUser.getLoginName(), deptUser.getLoginPwd(),
					deptUser.getUserId(),deptUser.getUserName(),deptUser.getUserPhone(),3);
			// 修改登录表信息
			userService.update(sysUser);
		}
		
		// 修改单位信息中的单位管理员编号，查询数据库中的单位管理员信息
		DeptUser du = deptUserService.getOne(deptUser.getUserId());
		if(du.getDept() != null){
			// 若数据库中的单位管理员信息与前台传来的不一致
			if(!du.getDept().getDeptId().equals(deptUser.getDept().getDeptId())){
				// 修改原来的单位信息为"暂无"
				Dept d1 = deptService.getOne(du.getDept().getDeptId());
				d1.setUserId("暂无");
				deptService.update(d1);
				// 修改当前的单位信息为前台传来的
				Dept d2 = deptService.getOne(deptUser.getDept().getDeptId());
				d2.setUserId(deptUser.getUserId());
				deptService.update(d2);
			}
			// 修改单位管理员信息
			targ = deptUserService.update(deptUser);
		}
		res.put("targ", targ);
		deptUser.setUserName(null);
		return SUCCESS;
	}
	
	public void prepare() throws Exception{
		if(deptUser == null){
			deptUser = new DeptUser();
		}
	}
	
	public DeptUser getModel(){
		return deptUser;
	}

	public DeptUser getDeptUser() {
		return deptUser;
	}

	public void setDeptUser(DeptUser deptUser) {
		this.deptUser = deptUser;
	}

	public List<Dept> getList() {
		return list;
	}

	public void setList(List<Dept> list) {
		this.list = list;
	}
}
