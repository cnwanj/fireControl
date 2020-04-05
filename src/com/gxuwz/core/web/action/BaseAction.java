package com.gxuwz.core.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxuwz.core.SystemContext;
import com.gxuwz.core.pagination.Result;
import com.gxuwz.fireControl.service.DeptService;
import com.gxuwz.fireControl.service.DeptUserService;
import com.gxuwz.fireControl.service.SysUserService;
import com.gxuwz.fireControl.util.DateAndRandom;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ BaseAction.java 2015-7-26 下午04:20:18
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -5391490758289533136L;
	protected Integer page = 0;// 1
	protected Integer row = 0;// 100
	protected String action;

	protected String forwardView; // 自定义的跳转页面

	protected HttpServletResponse response; // 获取response

	protected PrintWriter printWriter; // 获取输出

	protected Result pageResult; // 分页集合
	protected Map<String, Object> res = new HashMap<String, Object>();
	protected DateAndRandom code = new DateAndRandom();// 生成工号
	protected boolean targ; // 标记
	protected String t;		// 添加、删除标记

	@Autowired
	protected SysUserService userService; 		// 人员登录信息
	@Autowired
	protected DeptService deptService;			// 单位信息
	@Autowired
	protected DeptUserService deptUserService;	// 单位管理员

	public HttpServletResponse getResponse() {
		if (null == response) {
			response = ServletActionContext.getResponse();
			response.setContentType(SystemContext.ACTION_CONTENT_TYPE);
			response.setHeader("Cache-Control", "no-cache");
		}
		return response;
	}

	public PrintWriter getPrintWriter() {
		if (null == printWriter) {
			try {
				printWriter = getResponse().getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return printWriter;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPage() {
		if (page == null || page == 0) {
			page = SystemContext.DEFUALT_PAGE_NUM;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRow() {
		if (row == null || row == 0) {
			row = SystemContext.DEFUALT_PAGE_SIZE;
		}
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	private String[] delids;

	public String[] getDelids() {
		return delids;
	}

	public void setDelids(String[] delids) {
		this.delids = delids;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected String getContextPath() {
		return getRequest().getContextPath();
	}

	/**
	 * 
	 * @param paramName
	 * @return
	 */
	protected String getParameters(String paramName) {
		HttpServletRequest req = ServletActionContext.getRequest();
		return req.getParameter(paramName);
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	public String getRootPath() {
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String rootURL = sc.getRealPath("/");
		return rootURL;
	}

	public String getForwardView() {
		return forwardView;
	}

	public void setForwardView(String forwardView) {
		this.forwardView = forwardView;
	}

	public Result getPageResult() {
		return pageResult;
	}

	public void setPageResult(Result pageResult) {
		this.pageResult = pageResult;
	}

	public Map<String, Object> getRes() {
		return res;
	}

	public void setRes(Map<String, Object> res) {
		this.res = res;
	}

	public boolean isTarg() {
		return targ;
	}

	public void setTarg(boolean targ) {
		this.targ = targ;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}
}
