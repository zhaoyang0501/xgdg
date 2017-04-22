<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ch">
<%@ include file="../common/meta.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace/admin.guest.js"></script>

 <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		<c:if test="${tip!=null}">
		noty({"text":"${tip}","layout":"top","type":"warning"});
</c:if>
	});
</script>
</head>
<body>

	<div class="layout">
		<!-- top -->
		<%@ include file="../top.jsp"%>
		<!-- 导航 -->
		<%@ include file="../menu.jsp"%>
		
		<input type="hidden" id="hf_id" />

		<div class="main-wrapper">
			<div class="container-fluid">
				<div class="row-fluid ">
					<div class="span12">
						<div class="content-widgets light-gray">
							<div class="widget-head  bondi-blue" >
								<h3>修改密码</h3>
							</div>
							
							<div class="widget-container">
								
							<form action="${pageContext.request.contextPath}/admin/adminuser/doreset" method="post" id=''>
									<input type="hidden"  value='${sessionScope.adminuser.id}' name="adminuser.id" placeholder="">
							
							<div class="control-group">
								<label for="title" class="control-label">用户名：</label>
								<div class="controls">
									<input type="text" readonly="readonly" value='${sessionScope.adminuser.name}' name="adminuser.name" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">原始密码：</label>
								<div class="controls">
									<input type="password"  name="adminuser.password" placeholder="">
								</div>
							</div>
							<div class="control-group">
								<label for="title" class="control-label">新密码：</label>
								<div class="controls">
									<input type="password"  name="passwordnew" placeholder="">
								</div>
							</div>
								<div class="modal-footer center" id="div_footer">
									<button type="submit" class='btn btn-primary'>提交</button>
								</div>
						</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../foot.jsp"%>
	</div>
</body>
</html>