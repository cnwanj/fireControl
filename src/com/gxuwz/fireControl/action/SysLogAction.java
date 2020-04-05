package com.gxuwz.fireControl.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.web.action.BaseAction;
import com.gxuwz.fireControl.model.entity.Dept;
import com.gxuwz.fireControl.model.entity.DeptUser;
import com.gxuwz.fireControl.model.entity.ManageAreaFirst;
import com.gxuwz.fireControl.model.entity.SysLog;
import com.gxuwz.fireControl.model.entity.SysUsers;
import com.gxuwz.fireControl.service.DeptUserService;
import com.gxuwz.fireControl.service.FacilityService;
import com.gxuwz.fireControl.service.SysLogService;
import com.gxuwz.fireControl.service.SysUserService;
import com.gxuwz.fireControl.util.DateAndRandom;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * 
 * @Description TODO
 * @author 刘家乐
 * @version v1.0
 * @date 
 */
public class SysLogAction extends BaseAction implements ModelDriven<SysLog>, Preparable {
	private static final long serialVersionUID = 1L;
	
	private SysLog sysLog;
	// 单位管理员编号
	//private String deptUserId;
	//用户工号（自增主键）
	private Long userId;
	//日记编号
	private String logId;
	// 生成操作时间
	protected DateAndRandom time1 = new DateAndRandom();

	@Autowired
	private SysLogService sysLogService;
	//@Autowired
	//private DeptUserService deptUserService;
	
	@Override
	public void prepare() throws Exception {
		if (sysLog == null) {
			sysLog = new SysLog();
		}
		
	}

	@Override
	public SysLog getModel() {
		return sysLog == null ? new SysLog() : sysLog;
	} 
	
	/**
	 * 分页显示日记信息
	 * 
	 * @Description TODO
	 * @author 刘家乐
	 * @date 
	 * @return
	 */
	public String findByPage() {
		pageResult = sysLogService.findByPage(sysLog, getPage(), getRow());
		forwardView = "/pages/deptUser/sysLog/sysLog_list.jsp";
		return SUCCESS;

	}
	
	//public String save() {
		//sysLog.setLogCode(code.dateRandom());
		//if ("".equals(sysLog.getLogId())) {
			//sysLog.setLogId(null);
		//}
		// 保存日记信息
		//targ = sysLogService.save(sysLog);
		//res.put("targ", targ);
		//return SUCCESS;
	//}
    
	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		targ = sysLogService.delete(sysLog);
		res.put("targ", targ);
		return SUCCESS;
	}
	
	public SysLog getSysLog() {
		return sysLog;
	}

	public void setSysLog(SysLog sysLog) {
		this.sysLog = sysLog;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
}
