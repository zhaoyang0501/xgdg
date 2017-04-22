package com.pzy.action.index;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pzy.entity.Cart;
import com.pzy.entity.Category;
import com.pzy.entity.Item;
import com.pzy.entity.MsgBox;
import com.pzy.entity.News;
import com.pzy.entity.Order;
import com.pzy.entity.User;
import com.pzy.service.CartService;
import com.pzy.service.CategoryService;
import com.pzy.service.ItemService;
import com.pzy.service.MsgBoxService;
import com.pzy.service.NewsService;
import com.pzy.service.OrderService;
import com.pzy.service.UserService;
import com.pzy.util.MailUtil;
/***
 * 处理前台的各种跳转
 * @author 263608237@qq.com
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/")
public class IndexAction extends ActionSupport {

	private List<Category> categorys;
	@Autowired
	ItemService itemService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	private Item item;
	private Order order;
	private Category category;
	private List<Item> itemNews;
	private List<Item> itemHots;
	private List<Item> items;
	private List<Cart> cards;
	private List<Order> orders;
	private String tip;
	private String key;
	private String userName;
	private User user;
	private MsgBox msgBox;
	private List<MsgBox> msgBoxs;
	private List<News> newss;
	@Autowired
	private MsgBoxService msgBoxService;
	@Autowired
	private CartService carsCartService;
	@Autowired
	private NewsService newsService;
	/***首页
	 */
	public String execute() throws Exception {
		itemNews = itemService.findNew();
		itemHots = itemService.findHot();
		categorys = categoryService.findCategorys();
		newss=newsService.findTop3();
		return SUCCESS;
	}
	@Action(value = "orderdetail", results = { @Result(name = "success", location = "/WEB-INF/views/orderdetail.jsp") })
	public String orderdetail() throws Exception {
		order=orderService.find(order.getId());
		cards = carsCartService.findByOrder(order);
		return SUCCESS;
	}
	/***
	 * 点击商品显示详细信息页面
	 * @return
	 * @throws Exception
	 */
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/views/detail.jsp") })
	public String detail() throws Exception {
		item = itemService.find(item.getId());
		return SUCCESS;
	}
	/***
	 * 点击修改
	 * @return
	 * @throws Exception
	 */
	@Action(value = "goorderDelete", results = { @Result(name = "success", location = "/WEB-INF/views/order.jsp"),
	@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String goorderDelete() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		}
		
		List<Cart> carts = (List<Cart>) ServletActionContext.getRequest().getSession().getAttribute("carts");
		if(carts==null){
			carts=new ArrayList<Cart>();
			ServletActionContext.getRequest().getSession().setAttribute("carts",carts);
		}
		int index=0;
		for(int i=0;i<carts.size();i++){
			if(carts.get(i).getItem().getId().equals(item.getId()))
				index=i;
		}	
		carts.remove(index);
		ServletActionContext.getRequest().getSession().setAttribute("carts",carts);	
		tip = "删除成功！";
		return SUCCESS;
	}
	/***
	 * 点击修改
	 * @return
	 * @throws Exception
	 */
	@Action(value = "goorderUpdate", results = { @Result(name = "success", location = "/WEB-INF/views/order.jsp"),
	@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String goorderUpdate() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		}
		
		List<Cart> carts = (List<Cart>) ServletActionContext.getRequest().getSession().getAttribute("carts");
		if(carts==null){
			carts=new ArrayList<Cart>();
			ServletActionContext.getRequest().getSession().setAttribute("carts",carts);
		}
		for(int i=0;i<carts.size();i++){
			if(carts.get(i).getItem().getId().equals(item.getId()))
				carts.get(i).setCount(item.getCount());
		}	
		ServletActionContext.getRequest().getSession().setAttribute("carts",carts);	
		tip = "数量修改成功！";
		return SUCCESS;
	}
	/***
	 * 点击购买链接跳转
	 * @return
	 * @throws Exception
	 */
	@Action(value = "goorder", results = { @Result(name = "success", location = "/WEB-INF/views/order.jsp"),
	@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String goorder() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		}
		if(item==null) return SUCCESS;
		item = itemService.find(item.getId());
		List<Cart> carts = (List<Cart>) ServletActionContext.getRequest().getSession().getAttribute("carts");
		if(carts==null){
			carts=new ArrayList<Cart>();
			ServletActionContext.getRequest().getSession().setAttribute("carts",carts);
		}
		int index=-1;
		for(int i=0;i<carts.size();i++){
			if(carts.get(i).getItem().getId().equals(item.getId()))
				index=i;
		}	
		if(index==-1){
			Cart cart=new Cart();
			cart.setCount(1);
			cart.setItem(item);
			carts.add(cart);
		}else{
			carts.get(index).setCount(carts.get(index).getCount()+1);
		}
		ServletActionContext.getRequest().getSession().setAttribute("carts",carts);	
		return SUCCESS;
	}
	/***
	 * 点击首页我的个人中心
	 * @return
	 * @throws Exception
	 */
	@Action(value = "myorder", results = {
			@Result(name = "success", location = "/WEB-INF/views/myorder.jsp"),
			@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String myorder() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录后在查询！";
			return LOGIN;
		} else {
			orders = orderService.findByUser(user);
		}
		return SUCCESS;
	}
	/***
	 * 提交订单动作
	 * @return
	 * @throws Exception
	 */
	@Action(value = "gotoorder", results = {
			@Result(name = "success", location = "/WEB-INF/views/myorder.jsp"),
			@Result(name = "input", location = "/WEB-INF/views/order.jsp"),
			@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String order() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录后在购买！";
			return LOGIN;
		} else {
			List<Cart> carts = (List<Cart>) ServletActionContext.getRequest().getSession().getAttribute("carts");
			if(carts.size()==0){
				tip = "您没有添加任何商品哦！";
				return INPUT;
			}
				
			Double price=0d;
			for(int i=0;i<carts.size();i++){
				price+=carts.get(i).getItem().getPrice()*carts.get(i).getCount();
			}
			order.setTotalPrice(price);
			order.setCreateDate(new Date(System.currentTimeMillis()));
			order.setUser(user);
			order.setState("待审核");
			this.orderService.save(order);
			for(int i=0;i<carts.size();i++){
				carts.get(i).setOrder(order);
			}
			for(int i=0;i<carts.size();i++){
				this.carsCartService.save(carts.get(i));
			}
			this.tip = "下单成功，请等待客服审核！";
			orders = orderService.findByUser(user);
			 ServletActionContext.getRequest().getSession().removeAttribute("carts");
			return SUCCESS;
		}

	}
	/***
	 * 点击左侧分类菜单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "gocategory", results = { @Result(name = "success", location = "/WEB-INF/views/find.jsp") })
	public String gocategory() throws Exception {
		category = categoryService.findCategory(category.getId());
		items = itemService.findByCategory(category);
		categorys = categoryService.findCategorys();
		newss=newsService.findTop3();
		return SUCCESS;
	}
	/***
	 * 查找商品搜索框
	 * @return
	 * @throws Exception
	 */
	@Action(value = "search", results = { @Result(name = "success", location = "/WEB-INF/views/search.jsp") })
	public String search() throws Exception {
		items = itemService.findAll(1, 1000, key).getContent();
		categorys = categoryService.findCategorys();
		newss=newsService.findTop3();
		return SUCCESS;
	}
	/***
	 * 找回密码
	 */
	@Action(value = "forgot", results = { @Result(name = "success", location = "/WEB-INF/views/forgot.jsp") })
	public String forgot() throws Exception {
		return SUCCESS;
	}
	/***
	 * 找回密码
	 */
	@Action(value = "doforgot", results = { @Result(name = "success", location = "/WEB-INF/views/forgot.jsp"),@Result(name = "input", location = "/WEB-INF/views/forgot.jsp") })
	public String doforgot() throws Exception {
		User user=this.userService.find(userName);
		if(user==null){
			tip="用户不存在";
			return INPUT;
		}
		if(user.getEmail()==null){
			tip="用户邮箱不正确";
			return INPUT;
		}
		try {
			MailUtil.sendEmail(user);
		} catch (Exception e) {
			tip="邮件发送失败";
			return INPUT;
		}
		tip="已发送找回密码到邮箱"+user.getEmail()+"，请注意查收";
		return SUCCESS;
	}
	
	@Action(value = "center", results = { @Result(name = "success", location = "/WEB-INF/views/center.jsp"),@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String center() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		} else {
			return SUCCESS;
		}
		
	}
	@Action(value = "doCenter", results = { @Result(name = "success", location = "/WEB-INF/views/center.jsp"),@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String doCenter() throws Exception {
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		} else {
			tip = "信息修改成功！";
			userService.save(user);
			ServletActionContext.getRequest().getSession().setAttribute("user",user);
			return SUCCESS;
		}
		
	}
	@Action(value = "msgBox", results = { @Result(name = "success", location = "/WEB-INF/views/msgBox.jsp"),@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String msgBox() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		} else {
			this.msgBoxs=this.msgBoxService.findAll();
			return SUCCESS;
		}
	}
	@Action(value = "doMsgBox", results = { @Result(name = "success", location = "/WEB-INF/views/msgBox.jsp"),@Result(name = "login", location = "/WEB-INF/views/login.jsp") })
	public String doMsgBox() throws Exception {
		tip = "留言发布成功！";
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			tip = "您还没有登录，请登录！";
			return LOGIN;
		} else {
			msgBox.setUser(user);
			this.msgBoxService.save(msgBox);
			this.msgBoxs=this.msgBoxService.findAll();
			return SUCCESS;
		}
		
	}
	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public List<Item> getItemNews() {
		return itemNews;
	}

	public void setItemNews(List<Item> itemNews) {
		this.itemNews = itemNews;
	}

	public List<Item> getItemHots() {
		return itemHots;
	}

	public void setItemHots(List<Item> itemHot) {
		this.itemHots = itemHot;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MsgBox getMsgBox() {
		return msgBox;
	}
	public void setMsgBox(MsgBox msgBox) {
		this.msgBox = msgBox;
	}
	public MsgBoxService getMsgBoxService() {
		return msgBoxService;
	}
	public void setMsgBoxService(MsgBoxService msgBoxService) {
		this.msgBoxService = msgBoxService;
	}
	public List<MsgBox> getMsgBoxs() {
		return msgBoxs;
	}
	public void setMsgBoxs(List<MsgBox> msgBoxs) {
		this.msgBoxs = msgBoxs;
	}
	public List<News> getNewss() {
		return newss;
	}
	public void setNewss(List<News> newss) {
		this.newss = newss;
	}
	public List<Cart> getCards() {
		return cards;
	}
	public void setCards(List<Cart> cards) {
		this.cards = cards;
	}
}
