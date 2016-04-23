<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common.jspf"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<title>欢迎使用SWF展示PDF</title>
	
 	<script type="text/javascript" src="js/FlexPaper_2.2.4/flexpaper.js"></script>  	
	<script type="text/javascript">  
          var fp = new FlexPaperViewer( 
                   'FlexPaperViewer',  
                   'viewerPlaceHolder', { config : {  
                   SwfFile : escape("${swfPath}"),//swf文件路径  
                   Scale : 0.6,  
                   ZoomTransition : 'easeOut',  
                   ZoomTime : 0.5,  
                   ZoomInterval : 0.2,  
                   FitPageOnLoad : true,  
                   FitWidthOnLoad : true, //适合初始页宽度大小的装载页  
                   FullScreenAsMaxWindow : true,  
                   ProgressiveLoading : false,  
                   MinZoomSize : 0.2,  
                   MaxZoomSize : 5,  
                   SearchMatchAll : false,  
                   InitViewMode : 'Portrait',  
                   PrintPaperAsBitmap : false,  
                   ViewModeToolsVisible : true,  
                   ZoomToolsVisible : true,  
                   NavToolsVisible : true,  
                   CursorToolsVisible : true,  
                   SearchToolsVisible : true,                          
                   localeChain: 'zh_CN'  
           }});    
      </script>
</head>
<body>
	SWF 展示面板
	
	<a id="viewerPlaceHolder" style="width:100%;height:100%;display:block"></a>  
	
</body>
</html>