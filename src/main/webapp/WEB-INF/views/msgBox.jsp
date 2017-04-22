<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html><html lang="en"><head>
<meta charset="utf-8">
<title>一起购吧！</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="css/font.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/flexslider.css" type="text/css" media="screen" rel="stylesheet">
<link href="css/jquery.fancybox.css" rel="stylesheet">
<link href="css/cloud-zoom.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- fav -->
<link rel="shortcut icon" href="assets/ico/favicon.html">
</head>
<body>
<!-- Header Start -->
<%@include file="./head.jsp" %>
<!-- Header End -->

<div id="maincontainer">
  <section id="product">
    <div class="container">
      <div class="row">        
       <div class="span12">
        <h2 class="heading2" style="text-align: center;">${tip }</h2>
          <h1 class="heading1"><span class="maintext">留言板</span></h1>
          <section class="commentsblog">
                <h2 class="heading2"><span>欢迎留言</span></h2>
                <ul class="comments">
                <c:forEach items="${msgBoxs}" var="bean">
                <li>
                    <a class="avtar thumbnail"><img src="img/avtar.jpg" alt=""></a>
                    <div class="commentdetail">
                      <a class="blogtitle" href="#">${bean.title }</a>
                      <p>${bean.context }</p>
                      <a href="#">${bean.user.nickname } 发表于 ${bean.createDate }</a>
                      <c:if test="${bean.reply!=null }">
                        <p style="font-weight:bold ">管理员回复：${bean.reply }</p>
                      </c:if>
                    </div>
                  </li>
                </c:forEach>
                </ul>
              </section>
              <section class="leavecomment">
                <h2 class="heading2"><span>发表</span></h2>
                <form method="post" action="doMsgBox" class="form-horizontal commentform2" novalidate="novalidate">
                  <fieldset>
                    <div class="control-group">
                      <label class="control-label">标题 <span class="required">*</span></label>
                      <div class="controls">
                        <input type="text" name="msgBox.title" id="name" class="required">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label">内容</label>
                      <div class="controls">
                        <textarea name="msgBox.context" id="message" cols="40" rows="6" class="required" style="margin-left: 0px; margin-right: 0px; width: 496px;"></textarea>
                      </div>
                    </div>
                    <div class="controls">
                      <input type="submit" id="submit_id" value="提 交" class="btn btn-orange">
                    </div>
                  </fieldset>
                </form>
              </section>
          <div class="clearfix"></div>
          <br>
        </div>
       </div>
    </div>
  </section>
</div>
<%@include file="./foot.jsp" %>
<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/respond.min.js"></script>
<script src="js/application.js"></script>
<script src="js/bootstrap-tooltip.js"></script>
<script defer="" src="js/jquery.fancybox.js"></script>
<script defer="" src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/jquery.tweet.js"></script>
<script src="js/cloud-zoom.1.0.2.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script type="text/javascript" src="js/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript" src="js/jquery.ba-throttle-debounce.min.js"></script>
<script defer="" src="js/custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var formvalidate= $("#form").validate({
			errorPlacement: function(error, element) {
				$( element ).closest(".controls").append( error );
			},
			ignore:"",
			rules: {
				"user.name":  "required",
				"user.password":  {required:true, minlength: 8},
				"user.nickname":  "required",
				"user.email":  "email",
				"user.address":  "required"
				},
			messages: {
				"user.name":"请填写用户名",
				"user.password":{ required: "请输入密码",
					minlength: "密码至少8位"},
				"user.nickname":"请填写昵称",
				"user.email":"请填写电子邮件",
				"user.address":"请填写地址"
			}
		});
		
	});
</script>
</body></html>