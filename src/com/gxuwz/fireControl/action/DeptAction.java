package com.gxuwz.fireControl.action;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.Dept;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @Description 单位信息管理
 * @author 韦永恒
 * @date 2020.02.09
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept>, Preparable {
	
	private static final long serialVersionUID = 1L;
	
	private Dept dept;
	
	/**
	 * 分页查询
	 * @return
	 */
	public String findByPage(){
		pageResult = deptService.findByPage(dept, getPage(), getRow());
		forwardView = "/pages/sysUser/dept/dept_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 添加单位信息
	 * @return
	 */
	public String save(){
		dept.setUserId("暂无");
		targ = deptService.save(dept);
		res.put("targ", targ);
		dept.setDeptName(null);
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		targ = deptService.delete(dept);
		res.put("targ", targ);
		return SUCCESS;
	}
	
	/**
	 * 获取单位详情信息
	 * @return
	 */
	public String getOne(){
		dept = deptService.getOne(dept.getDeptId());
		forwardView = "/pages/sysUser/dept/dept_update.jsp";
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String update() {
		targ = deptService.update(dept);
		res.put("targ", targ);
		dept.setDeptName(null);
		return SUCCESS;
	}

	public void prepare() throws Exception{
		if(dept == null){
			dept = new Dept();
		}
	}
	
	public Dept getModel(){
		return dept;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

}
