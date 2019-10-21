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
   if(isNull(form1.TB_TeacherID.value)){  
   alert("请选择要添加的管理员！"); 
   return false;
   }
}

function isNull(str){
if ( str == "" ) return true;
var regu = "^[ ]+$";
var re = new RegExp(regu);
return re.test(str);
}

function getBuildingAdministratorById(str){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
	}
   
   
</script>
<body>
<c:if test="${msg != null }">${msg }</c:if> 
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
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">楼宇管理员设置</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="TBAddSave.action">
              	<input type="hidden" name="Build_id" value="${Build_id }">
                <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="42%" height="30" style="padding-left:20px;"> 功能导航： <a href="BuildingManagerjsp.action">返回上层</a></td>
                    <td width="58%">添加管理员：
                      <select name="TB_TeacherID" id="TB_TeacherID">
                        <option value="">请选择</option>
                        <c:forEach items="${BuildingAdministrators}" var="BuildingAdministrator">
                        	<option  value="${BuildingAdministrator.id}">${BuildingAdministrator.realname}</option>
                        </c:forEach>
                      </select>
                      <input type="submit" name="button" id="button" value="点击添加">
                      <label for="TB_BuildingID"></label>
                      <input name="TB_BuildingID" type="text" class="noshow" id="TB_BuildingID" value="${Building_ID}"></td>
                  </tr>
                </table>
              </form>
              
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr align="center"  class="t1">
                    <td height="25" bgcolor="#D5E4F4"><strong>姓名</strong></td>
                    <td bgcolor="#D5E4F4"><strong>性别</strong></td>
                    <td bgcolor="#D5E4F4"><strong>电话</strong></td>
                    <td bgcolor="#D5E4F4"><strong>用户名</strong></td>
                    <td bgcolor="#D5E4F4"><strong>操作</strong></td>
                  </tr>
                  <c:if test="${Building != null}">
                  	<c:if test="${Building.buildingAdministrator !=null}">
	                  	<tr align="center">
	                      <td height="25" align="center">${Building.buildingAdministrator.realname}</td>
	                      <td>${Building.buildingAdministrator.sex}</td>
	                      <td>${Building.buildingAdministrator.contact}</td>
	                      <td align="center">${Building.buildingAdministrator.name}</td>
	                      <td align="center"><a href="TBDel.action?Build_id=${Building.id}" onClick="return confirm('确定要将该管理员从该楼宇移除吗？')">移除</a>  </td>
	                    </tr>
                  	</c:if> 
                  </c:if>
                </table></td>
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
