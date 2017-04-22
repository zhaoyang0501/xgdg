<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.adminuser.name}</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="日报"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
				<li><a href="${pageContext.request.contextPath}/admin/adminuser/reset"><i class="icon-zoom-in"></i>修改密码</a></li>
				
				<c:if test="${sessionScope.adminuser.role6=='yes'}">
				 <!--  <li><a href="${pageContext.request.contextPath}/admin/adminuser/index"><i class="icon-zoom-in"></i>管理员维护</a></li>-->
				</c:if>	
				
				<c:if test="${sessionScope.adminuser.role1=='yes'}">
					<li><a href="${pageContext.request.contextPath}/admin/user/index"><i class="icon-pencil"></i> 注册会员管理</a></li>
				</c:if>
				<c:if test="${sessionScope.adminuser.role1=='yes'}">
					<li><a href="${pageContext.request.contextPath}/admin/seller/index"><i class="icon-pencil"></i> 花店管理</a></li>
				</c:if>
					<c:if test="${sessionScope.adminuser.role3=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/msgbox/index"><i class="icon-pencil"></i>留言管理</a></li>
					</c:if>
					<c:if test="${sessionScope.adminuser.role3=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/news/index"><i class="icon-pencil"></i>公告管理</a></li>
					</c:if>
					<c:if test="${sessionScope.adminuser.role3=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/news/create"><i class="icon-pencil"></i>发布公告 </a></li>
					</c:if>
					<c:if test="${sessionScope.adminuser.role2=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/category/index"><i class="icon-upload"></i>鲜花 分类管理 </a></li>
					</c:if>
					
					<c:if test="${sessionScope.adminuser.role4=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/item/index"><i class="icon-zoom-in"></i> 鲜花管理</a></li>
					</c:if>
					<c:if test="${sessionScope.adminuser.role5=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/order/index"><i class="icon-zoom-in"></i>订单管理</a></li>
					</c:if>
					<c:if test="${sessionScope.adminuser.role5=='yes'}">
						<li><a href="${pageContext.request.contextPath}/admin/report/index"><i class="icon-zoom-in"></i>财务统计</a></li>
					</c:if>
						
				</ul>
			</div>
		</div>
	</div>
</div>