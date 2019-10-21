<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table  width="155" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="31" align="center" background="Images/left1.jpg"><strong>系统选项</strong></td>
            </tr>
            <tr>
              <td height="50" align="center" valign="top"><table width="150" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="Indexjsp.action">后台首页</a></td><!-- TODO -->
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                
                <c:if test="${type  == '系统管理员'}">
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="TeacherManagerjsp.action">楼宇管理员管理</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentManagerjsp.action">学生管理</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="BuildingManagerjsp.action">楼宇管理</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="DomitoryManagerjsp.action">宿舍管理</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentRZjsp.action">学生入住登记</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentTHjsp.action">学生寝室调换</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentQCjsp.action">学生迁出登记</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="AdminLogjsp.action">学生缺寝记录</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="OutListjsp.action">迁出记录</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
               </c:if>
                
                <c:if test="${type  == '楼宇管理员'}">
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="MyStudentjsp.action">学生管理</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="MyLogjsp.action">学生缺寝记录</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
               </c:if>
                
               <c:if test="${type  == '学生'}">
               		<tr>
	                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentLogjsp.action">我的缺寝记录</a></td>
	                </tr>
	                <tr>
	                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
	                </tr>
               </c:if>
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="PasswordUpdatejsp.action">修改密码</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="Quit.action" onclick="return confirm('确定要退出系统吗？')">退出系统</a></td>
                </tr>
              </table>
              </td>
            </tr>
          </table>