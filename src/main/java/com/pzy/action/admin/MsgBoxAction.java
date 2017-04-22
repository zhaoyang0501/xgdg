package com.pzy.action.admin;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.pzy.action.PageAction;
import com.pzy.entity.MsgBox;
import com.pzy.service.MsgBoxService;
/**
 * 
 * @author 263608237@qq.com
 *
 */
@Namespace("/admin/msgbox")
@ParentPackage("json-default") 
public class MsgBoxAction extends PageAction {
	private String name;
	private Long id;
	private MsgBox msgBox;
	private String tip;
	private String reply;
	private List<MsgBox> msgBoxs;
	@Autowired
	private MsgBoxService msgBoxService;
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/views/admin/msgbox/index.jsp") })
	public String index() {
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String list() {
		int pageNumber = (int) (this.getIDisplayStart() / this.getIDisplayLength()) + 1;
		int pageSize =  this.getIDisplayLength();
		Page<MsgBox> list = msgBoxService.findAll(pageNumber, pageSize,name);
		this.getResultMap().put("aaData", list.getContent());
		this.getResultMap().put("iTotalRecords", list.getTotalElements());
		this.getResultMap().put("iTotalDisplayRecords", list.getTotalElements());
		this.getResultMap().put("sEcho", getSEcho());
		return SUCCESS;
	}

	@Action(value = "delete", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String delete() {
		msgBoxService.delete(id);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "删除成功");
		return SUCCESS;
	}

	@Action(value = "reply", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String reply() {
		MsgBox msg = msgBoxService.find(id);
		msg.setReply(reply);
		msg.setCreateDate(new Date());
		msgBoxService.save(msg);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "回复成功！");
		return SUCCESS;
	}
	
	@Action(value = "get", results = { @Result(name = "success", type = "json",params={"ignoreHierarchy","false"}) })  
	public String get() {
		getResultMap().put("object", msgBoxService.find(id));
		getResultMap().put("state", "success");
		getResultMap().put("msg", "删除成功");
		return SUCCESS;
	}

	@Action(value = "update", results = { @Result(name = "success",  type = "json",params={"ignoreHierarchy","false"}) })  
	public String update() {
		MsgBox bean = msgBoxService.find(msgBox.getId());
		BeanUtils.copyProperties(msgBox, bean);
		msgBox.setCreateDate(new Date());
		msgBoxService.save(msgBox);
		getResultMap().put("state", "success");
		getResultMap().put("msg", "修改成功");
		return SUCCESS;
	}
	@Action(value = "save",  results = { @Result(name = "success", location = "/WEB-INF/views/admin/msgBox/create.jsp") })
	public String saveit() {
		msgBox.setCreateDate(new Date());
		msgBoxService.save(msgBox);
		tip="发布成功！";
		return SUCCESS;
	}
	
	public MsgBox getMsgBox() {
		return msgBox;
	}

	public void setMsgBox(MsgBox msgBox) {
		this.msgBox = msgBox;
	}

	public List<MsgBox> getMsgBoxs() {
		return msgBoxs;
	}

	public void setMsgBoxs(List<MsgBox> msgBoxs) {
		this.msgBoxs = msgBoxs;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
