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
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">学生管理</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE">
              <form name="form1" method="post" action="MyStudentList.action">
                <table width="100%%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="28%" height="30" style="padding-left:20px;"> 功能导航： <a href="MyStudentjsp.action">返回上层</a></td>
                    <td width="72%">查询：
                      <select name="Domitory_ID" id="Domitory_ID">
                        <option value="">全部寝室</option>
                        <c:forEach items="${dormitories }" var="dormitory">
                        <option value="${dormitory.id}">${dormitory.name}</option>
                        </c:forEach>
                      </select>
                      <select name="SearchRow" id="SearchRow">
                        <option value="name">姓名</option>
                        <option value="stu_ID">学号</option>
                        <option value="class1">班级</option>
                      </select>
                      <input name="SearchKey" type="text" class="text1" id="SearchKey">
                      <input type="submit" name="button" id="button" value="点击查询">
                      <label for="Building_ID"></label>
                      <input name="Building_ID" type="text" class="noshow" id="Building_ID" value="${Building_ID}"></td>
                  </tr>
                </table>
              </form>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr align="center"  class="t1">
                    <td height="25" bgcolor="#D5E4F4"><strong>寝室号</strong></td>
                    <td bgcolor="#D5E4F4"><strong>姓名</strong></td>
                    <td bgcolor="#D5E4F4"><strong>性别</strong></td>
                    <td bgcolor="#D5E4F4"><strong>班级</strong></td>
                    <td bgcolor="#D5E4F4"><strong>寝室类型</strong></td>
                    <td bgcolor="#D5E4F4"><strong>寝室人数</strong></td>
                    <td bgcolor="#D5E4F4"><strong>寝室电话</strong></td>
                    <td bgcolor="#D5E4F4"><strong>操作</strong></td>
                  </tr>
                  <c:forEach items="${students }" var="student">
                    <tr align="center">
                      <td height="25" align="center">${student.dormitory.name}</td>
                      <td>${student.name}</td>
                      <td>${student.sex}</td>
                      <td>${student.class1}</td>
                      <td>${student.dormitory.type}</td>
                      <td>${student.dormitory.number}</td>
                      <td>${student.dormitory.contact}</td>
                      <td align="center"><a href="LogAddjsp.action?Student_ID=${student.id}">缺寝登记</a></td>
                    </tr>
                  </c:forEach>
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
