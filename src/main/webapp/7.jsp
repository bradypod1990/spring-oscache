<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
System.out.println(path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
    <head>  
        <title></title>  
        <style>  
       .black_overlay{  
            display: none;  
            position: absolute;  
            top: 0%;  
            left: 0%;  
            width: 100%;  
            height: 100%;  
            background-color: black;  
            z-index:1001;  
            -moz-opacity: 0.8;  
            opacity:.80;  
            filter: alpha(opacity=88);  
        }  
        .white_content {  
            display: none;  
            position: absolute;  
            top: 15%;  
            left: 2%;  
            width: 96%;  
            height: 75%;  
            padding: 20px;  
            border: 1px solid ;  
            background-color: white;  
            z-index:1002;  
        }  
    </style>
	<script language="JavaScript" type="text/JavaScript">
		function NewTM(target){
			var target = document.getElementById(target);
			target.style.display='block';
		}
		function close1(remote){
			var remote = document.getElementById(remote);
			remote.style.display='none';
		}
		function showDiv(targetId){
			if (targetId=="WB"){
                document.getElementById("WB").style.display="block";
				document.getElementById("SZWB").style.display="none";
				document.getElementById("DanX").style.display="none";
				document.getElementById("DuoX").style.display="none";
            }if (targetId=="SZWB"){
                document.getElementById("WB").style.display="none";
				document.getElementById("SZWB").style.display="block";
				document.getElementById("DanX").style.display="none";
				document.getElementById("DuoX").style.display="none";
            }if (targetId=="DanX"){
                document.getElementById("WB").style.display="none";
				document.getElementById("SZWB").style.display="none";
				document.getElementById("DanX").style.display="block";
				document.getElementById("DuoX").style.display="none";
            }if (targetId=="DuoX"){
                document.getElementById("WB").style.display="none";
				document.getElementById("SZWB").style.display="none";
				document.getElementById("DanX").style.display="none";
				document.getElementById("DuoX").style.display="block";
            }
			
			
			//var target=document.getElementById(targetId);
			//var redios=document.getElementById(radioId);
			
			//if (target.style.display="none"){
            //    target.style.display=="block";
            //}
			
            //if (target.style.display=="block"){
            //    target.style.display="none";
            //} else {
            //    target.style.display="block";
            //}   
		}
	</script>
    </head>  
    <body>
		<div width="100%">
当前位置：辅助功能&gt;调查问卷&gt;新建 <font width= "80%" align="right" >调查状态：未开始</font>
		</div>
		<div>
			<table width="80%" align="center">
				<tr>
					<td width="15%" align="right"><Font COLOR = "red" >*</font>问卷标题：</td>
					<td width="85%" align="left"><input type="text" id="WJBT" width="200PX" size="100"></td>
				</tr>
			</table>
		</div>
		
        <div width="100%" align="right">
			<input type="button" value="新增题目" onclick = "NewTM('light')" />
		</div>
		<div id="" width="100%" align="center">
			<table id="" border='1' width="100%" >
				<tr>
					<td width="10%" align="center">序&nbsp;&nbsp;号</td>
					<td width="50%" align="center">题&nbsp;&nbsp;目</td>
					<td width="40%" align="center">编&nbsp;&nbsp;辑</td>
				</tr>
				<tr id="model" style="display:none" >
					<td></td>
					<td></td>
					<td>
						<A HREF="">&nbsp;编&nbsp;辑&nbsp;</A>&nbsp;&nbsp;
						<A HREF="">&nbsp;删&nbsp;除&nbsp;</A>&nbsp;&nbsp;
						<A HREF="">&nbsp;复&nbsp;制&nbsp;</A>&nbsp;&nbsp;
						<A HREF="">&nbsp;上&nbsp;移&nbsp;</A>&nbsp;&nbsp;
						<A HREF="">&nbsp;下&nbsp;移&nbsp;</A>&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
        <div id="light" class="white_content">
			<div id="" class="" width="100%" height="8%" background-color="#A8A9AC" >
				&nbsp;&nbsp;新&nbsp;&nbsp;增&nbsp;&nbsp;题&nbsp;&nbsp;目&nbsp;&nbsp;
			</div>
			<p></p>
			<div>
				<div>
					<TABLE id="" border='1' width="100%" >
						<TR>
							<TD align="center">题目内容</TD>
							<TD colspan="4" align="left"><TEXTAREA NAME="" ROWS="" COLS="100"></TEXTAREA></TD>
						</TR>
						<TR>
							<TD align="center">控件</TD>
							<TD align="center"><INPUT id="wenben" TYPE="radio" NAME="KJ" value="WB" onclick="showDiv('WB')" >文本</TD>
							<TD align="center"><INPUT id="shuziwenben" TYPE="radio" NAME="KJ" value="SZWB" onclick="showDiv('SZWB')" >数字文本</TD>
							<TD align="center"><INPUT id="danxuan" TYPE="radio" NAME="KJ" value="DanX" onclick="showDiv('DanX')" >单选</TD>
							<TD align="center"><INPUT id="duoxuan" TYPE="radio" NAME="KJ" value="DuoX" onclick="showDiv('DuoX')" >多选</TD>
						</TR>
					</TABLE>
				</div>
				
				<div id="WB" style="display:none;width:200px;height:400px">
					name:<input type="text" name="name" />
					<br>
					age:<input type="text" name="age"/>
				</div>
				<div id="SZWB" width="100%" style="display:none">
					<TABLE id="" border='1' width="100%" >
						<TR>
							<TD width="10%" >说明</TD>
							<TD width="90%" >单位：<INPUT TYPE="text" NAME="DW" size="2"></TD>
						</TR>
					</TABLE>
				</div>
				<div id="DanX" width="100%" style="display:none">
					<TABLE id="" border='1' width="100%" >
						<TR>
							<TD colspan="2" align="center" >说明</TD>
						</TR>
						<TR id="model" style="display:none" >
							<TD align="center" >选项：</TD>
							<TD align="left" ><INPUT TYPE="text" NAME="XX2" size="50"><A HREF="" align="right" onclick="">&nbsp;删&nbsp;除&nbsp;</A></TD>
						</TR>
					</TABLE>
				</div>
				<div id="DuoX" width="100%" style="display:none">
					<TABLE id="" border='1' width="100%" >
						<TR>
							<TD colspan="2" align="center" >说明</TD>
						</TR>
						<TR id="model" style="display:none" >
							<TD align="center" >选项：</TD>
							<TD align="left" ><INPUT TYPE="text" NAME="XX2" size="50"><A HREF="" align="right" onclick="">&nbsp;删&nbsp;除&nbsp;</A></TD>
						</TR>
						<TR>
							<TD align="center" ></TD>
							<TD align="left" ><INPUT TYPE="text" NAME="XX1" size="50"></TD>
						</TR>
						<TR>
							<TD align="center" >选项2：</TD>
							<TD align="left" ><INPUT TYPE="text" NAME="XX2" size="50"></TD>
						</TR>	
					</TABLE>
				</div>
			</div>
			<input type="button" value="保&nbsp;&nbsp;存" onclick = "close1('light')"  />
			<input type="button" value="关&nbsp;&nbsp;闭" onclick = "close1('light')"  />
		</div>
   </body>
</html>
<div></div><div></div><div></div>