<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
         <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header>

  <div class="headerstrip">
    <div class="container">
      <div class="row">
      	<div class='span1'>
         
         </div> 
          <!-- Top Nav Start -->
          <div class="span7">
            <div class="navbar" id="topnav">
              <div class="navbar-inner">
                <ul class="nav">
                  <li><a class="home active" href="${pageContext.request.contextPath}/index">首页</a>
                  </li>
                  
                  <li><a class="checkout" href="${pageContext.request.contextPath}/center">我的信息</a>
                  </li>
                   <li><a class="checkout" href="${pageContext.request.contextPath}/goorder">我的购物车</a>
                  </li>
                   <li><a class="checkout" href="${pageContext.request.contextPath}/myorder">我的订单</a>
                  </li>
                   <li><a class="checkout" href="${pageContext.request.contextPath}/msgBox">我要留言</a>
                  </li>
                 <%--  <li><a class="myaccount" href="${pageContext.request.contextPath}/gocategory?category.id=1">我的购物车</a>
                  </li>
                  
                  <li><a class="myaccount" href="${pageContext.request.contextPath}/gocategory?category.id=1">商品分类</a>
                  </li>
                  <li><a class="checkout" href="${pageContext.request.contextPath}/myorder">个人中心</a> --%>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="span2">
            <form class="form-search top-search" action="search" method="post">
              <input type="text" name='key' class="input-medium search-query" placeholder="搜索鲜花">
            </form>
          </div>
          
          <div class="dropdown span1" style="margin-left: 110px">
	          <c:if test="${sessionScope.user==null}">
	          <a href="${pageContext.request.contextPath}/login" style="color: #fff;" >登陆</a>|
	          <a href="${pageContext.request.contextPath}/register" style="color: #fff;" >注册</a>
	          </c:if>
	          <c:if test="${sessionScope.user!=null}">
	           <a href="javascript:void(0)" style="color: #fff;" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.nickname }<b class="caret"></b></a>
	               <ul class="dropdown-menu language" id='top_center'>
	                <li>
	                  <a href="${pageContext.request.contextPath}/myorder">个人中心</a>
	                </li>
	                 <li>
	                  <a href="${pageContext.request.contextPath}/loginout">退出</a>
	                </li>
	              </ul>
	          </c:if>
	      </div>
          
      </div>
    </div>
  </div>
</header>