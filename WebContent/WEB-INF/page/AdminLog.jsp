<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>校园宿舍管理系统</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Style/Style.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript">


function mycheck(){
   if(isNull(form1.Student_Username.value)){  
   alert("请输入学号！"); 
   return false;
   }
   var g = /^[1-9]*[1-9][0-9]*$/;
   if(!g.test(form1.Student_Username.value)){
	   alert("学号请输入数字！"); 
	   return false;
   }
}

function isNull(str){
if ( str == "" ) return true;
var regu = "^[ ]+$";
var re = new RegExp(regu);
return re.test(str);
}
   
   
</script>
<body>
<center>
  <table width="900" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="60" bgcolor="#E6F5FF" style="color:#06F; font-size:19px; font-weight:bolder; padding-left:50px;">校园宿舍管理系统</td>
    </tr>
    <tr>
      <td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
    </tr>
    <tr>
      <td height="500" align="center" valign="top"><table width="900" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="191" height="500" align="center" valign="top" background="Images/leftbg.jpg">
          <%@ include file="Left.jsp"%>
          </td>
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生缺寝记录</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="AdminLogList.action" onSubmit="return mycheck()" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="33%" height="30" align="right">&nbsp;</td>
                    <td width="67%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="30" align="right">楼宇：</td>
                    <td><select name="BuildingID" id="Building_ID" onChange="javascript:window.location='AdminLogjsp.action?BuildingID='+this.value;">
                      <option value="">请选择</option>
                      <c:forEach items="${Buildings }" var="Building">
                      	<option value="${Building.id}" <c:if test="${ BuildingID == Building.id}">selected</c:if>>${Building.name}</option>
                      </c:forEach>
                      </select></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">寝室：</td>
                    <td><select name="Domitory_ID" id="Domitory_ID">
                      <option value="">请选择</option>
                      <c:forEach items="${Dormitorys }" var="Dormitory">
                        <option value="${Dormitory.id}">${Dormitory.name}</option>
                      </c:forEach>
                    </select></td>
                  </tr>
                  <tr>
                    <td height="30" align="right">学生学号：</td>
                    <td><label for="Student_ID"></label>
                      <input type="text" name="Student_Username" id="Student_Username"></td>
                  </tr>
                  <tr>
                    <td height="30">&nbsp;</td>
                    <td><input type="submit" name="button" id="button" value="开始查询" > <c:if test="${msg != null }">${msg}</c:if> </td>
                  </tr>
                </table>
              </form></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td height="35" background="Images/bootBg.jpg">&nbsp;</td>
    </tr>
  </table>

</center>
</body>
</html>
