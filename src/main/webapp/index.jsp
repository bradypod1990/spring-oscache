<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
System.out.println(path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="utf-8"> 
    <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script> 
<script> 


$(document).ready(function(){ 
       
       
$("#but1").click(function(){ 
	 var url = $("#url").val();
	 var param = $("#param").val();
     $.ajax({ 
        //url:'https://mall.longone.com.cn/order/updateQdxx', 
		//url:'http://128.130.11.38/order/updateQdxx',
		url:'http://128.130.11.38:8080/test/testService',
		data:{url:url,param:param},
        type: "get", 
		dataType: "jsonp", 
        jsonp: "jsoncallback", //服务端用于接收callback调用的function名的参数  
        jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来  
        success: function(json) { 
			console.log(json)
        }, 
        error: function(e){console.log(e);} 
}); 
}); 

$("#but2").click(function(){ 
	 var url = $("#url").val();
	 var param = $("#param").val();
     $.ajax({ 
        //url:'https://mall.longone.com.cn/order/updateQdxx', 
		//url:'http://128.130.11.38/order/updateQdxx',
		url:'/test/testService2',
		data:{url:url,param:param},
        type: "get", 
		dataType: "json", 
        success: function(json) { 
			console.log(json);
			$("#result").html(JSON.stringify(json));
        }, 
        error: function(e){console.log(e);} 
}); 
}); 
   
}); 
</script> 
</head> 
<body> 
<form>
	URL:<input type="text" name="url" id="url" style="width: 436px;height: 25px;" value="http://128.130.11.38/service"/> <br/><br/>
	Param:<textarea rows=6 cols=60 name="param" id="param">{"func":"getOrderResult","req_data":{"key":"005010375989_A0D095_1454135733511"}}</textarea><br/><br/>
</form>
<button id="but1">app抢单接口测试</button> <br/> 

<button id="but2">app抢单接口测试2</button> <br/> 
<br/> <br/> 
<h>结果：</h>
<p id="result"></p>
</body> 
</html>