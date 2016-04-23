<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仿flash效果的jquery下拉导航菜单代码</title>
<link href="css/slide-Style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/slide.js"></script>
</head>
<body>
	<br />
	<br />
	<br />
	<div id="menu">
		<ul id="nav">
			<li class="jquery_out">
				<div class="jquery_inner">
					<div class="jquery">
						<span class="text">导航菜单</span><span class="smile"></span>
					</div>
				</div>
			</li>

			<li class="mainlevel" id="mainlevel_01">
				<a href="page/crawlerPage/findBjQyxyPage.jsp" target="contentViewFrameId">本应用导航</a>
				<ul id="sub_01">
					<li><a href="page/crawlerPage/findBjQyxyPage.jsp" target="contentViewFrameId">验证码测试</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideUp</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">animate</a></li>
				</ul>
			</li>
			<li class="mainlevel" id="mainlevel_02">
				<a href="page/viewPdf/viewFileWithSwt.jsp" target="contentViewFrameId">SWF查看PDF</a>
				<ul id="sub_01">
					<li><a href="page/crawlerPage/findBjQyxyPage.jsp" target="contentViewFrameId">验证码测试</a></li>
					<li><a href="http://blog.csdn.net/authorzhh/article/details/7470048" target="_blank">参考资料一</a></li>
					<li><a href="http://blog.csdn.net/kalision/article/details/9448355" target="_blank">参考资料二</a></li>
					<li><a href="http://dxx23.iteye.com/blog/1947083" target="_blank">参考资料三</a></li>
					<li><a href="http://blog.csdn.net/authorzhh/article/details/7470048" target="_blank">参考资料四</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">animate</a></li>
				</ul>
			</li>
			<li class="mainlevel" id="mainlevel_03"><a
				href="http://www.mobanwang.com/" target="_blank">do touch me</a>
				<ul id="sub_03">
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">JavaScript</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideUp</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up
							and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">animate</a></li>
				</ul></li>

			<li class="mainlevel" id="mainlevel_04"><a
				href="http://www.mobanwang.com/" target="contentViewFrameId">do touch me</a>
				<ul id="sub_04">
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">JavaScript</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideUp</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up
							and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">animate</a></li>
				</ul></li>

			<li class="mainlevel" id="mainlevel_05"><a
				href="http://www.mobanwang.com/" target="contentViewFrameId">do touch me</a>
				<ul id="sub_05">
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">JavaScript</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">网页幻灯</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up
							and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">animate</a></li>
				</ul></li>

			<li class="mainlevel" id="mainlevel_06"><a
				href="http://www.mobanwang.com/" target="contentViewFrameId">do touch me</a>
				<ul id="sub_06">
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">JavaScript</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideUp</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up
							and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">网页特效</a></li>
				</ul>
			</li>
			<li class="mainlevel" id="mainlevel_07"><a
				href="http://www.mobanwang.com/" target="contentViewFrameId">do touch me</a>
				<ul id="sub_06">
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">JavaScript</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideUp</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up
							and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">网页特效</a></li>
				</ul>
			</li>
			<li class="mainlevel" id="mainlevel_08"><a
				href="http://www.mobanwang.com/" target="contentViewFrameId">do touch me</a>
				<ul id="sub_06">
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">JavaScript</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">jQuery</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideUp</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">slideDown</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">up
							and down</a></li>
					<li><a href="http://www.mobanwang.com/" target="contentViewFrameId">网页特效</a></li>
				</ul>
			</li>
			<div class="clear"></div>
		</ul>
	</div>
	
	<div class="content">
		<iframe id="contentViewFrameId" name="contentViewFrameId" width="99%" height="400px" sr="" frameborder="1" align="center"></iframe>
	</div>
	
</body>
</html>
