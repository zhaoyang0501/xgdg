package com.pzy.action.index;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.User;
import com.pzy.service.UserService;
/***
 * 登录操作
 * @author 263608237@qq.com
 *
 */
@ParentPackage("struts-default")  
@Namespace("/")
public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String,Object> session;
	private String tip;
	private User user;
	@Autowired
	UserService userService;
	/***
	 * 跳转到登录页面
	 * @return
	 */
	@Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/login.jsp") })
	public String login(){
		return SUCCESS;
	}
	
	/***
	 * 登录逻辑 查找数据库比较用户名密码
	 * @return
	 * @throws Exception
	 */
	 @Action(value = "dologin", 
	    		results = { @Result(name = "success" ,type="redirect", location = "index") ,
	    					@Result(name = "login", location = "/WEB-INF/views/login.jsp") })  
	 public String dologin() throws Exception { 
	    	User loginuser=userService.login(user.getName(), user.getPassword());
	    	if(loginuser!=null){
	    		session.put("user",loginuser );
	            return SUCCESS; 
	    	}
	    	else{
	    		ActionContext.getContext().getSession().clear();
	    		this.tip="登陆失败 不存在此用户名或密码!";
	    		return LOGIN;
	    	}
	    	
	}
	
	 /***
	  * 退出
	  * @return
	  * @throws Exception
	  */
	@Action(value = "loginout", results = { @Result(type="redirect", location = "index") })  
	public String loginout() throws Exception {  
	    	ActionContext.getContext().getSession().clear();
	    	return SUCCESS;
	 }
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
