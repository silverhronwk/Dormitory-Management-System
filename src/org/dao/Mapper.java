package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.Administrator;
import org.entity.Building;
import org.entity.BuildingAdministrator;
import org.entity.Dormitory;
import org.entity.Lackofsleep;
import org.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface Mapper {

	//系统管理员
	public boolean addAdministrator(String name,String pwd);//添加系统管理员
	public List<Administrator> getAdministrators();//查找所有系统管理员
	public Administrator getAdministratorById(int id);//通过id查找系统管理员
	public Administrator getAdministratorByNamePwd(String name,String pwd);//通过name,pwd查找系统管理员
	public boolean delAdministrator(int id);//删除系统管理员
	public boolean updateAdministrator(String name,String pwd,int id);//修改系统管理员
	public boolean updatePwdByName(@Param("tableName") String tableName,@Param("pwd") String pwd,@Param("name") String  name);//修改密码
	
	//楼宇管理员BuildingAdministrator
	public boolean addBuildingAdministrator(String name,String pwd,String realname,String sex,String contact);//添加楼宇管理员
	public List<BuildingAdministrator> getBuildingAdministrators();//查找所有楼宇管理员
	public BuildingAdministrator getBuildingAdministratorById(int id);//通过id查找楼宇管理员
	public BuildingAdministrator getBuildingAdministratorByNamePwd(String name,String pwd);//通过NamePwd查找楼宇管理员 
	public List<BuildingAdministrator> getBuildingAdministratorByor(@Param("SearchRow") String SearchRow,@Param("SearchKey") String SearchKey);//通过用户名/真实姓名/电话查找楼宇管理员
	public boolean delBuildingAdministrator(int id);//删除楼宇管理员
	public boolean updateBuildingAdministrator(String name,String pwd,String realname,String sex,String contact,int id);//修改楼宇管理员
	
	//学生Student
	public boolean addStudent(int stu_ID,String name,String pwd,String sex,String class1,String state);//添加学生
	public boolean addStudentIntoDormitory(int dormitory_ID,int id);//学生入住宿舍
	public List<Student> getStudentsUnemigration();//查找所有未毕业学生
	public List<Student> getStudents();//查找所有学生
	public Student getStudentById(int id);//通过id查找学生
	public Student getStudentBystu_ID(int stu_ID);//通过stu_ID查找学生
	public Student getStudentByNamePwd(String name,String pwd);//通过NamePwd查找学生
	public List<Student> getstudentByor(@Param("State") String State,@Param("SearchRow") String SearchRow,@Param("SearchKey") String SearchKey);//通过state/学号/真实姓名/电话查找学生 
	public boolean delStudent(int id);//删除学生
	public boolean updateStudent(int stu_ID,String name,String pwd,String sex,String class1,int id);//修改学生
	public boolean updateStudentstate(String state,int id);//修改学生状态
	public boolean updateStudentDormitoryIdBystu_ID(int dormitory_ID,int stu_ID);//修改学生所住宿舍 
	public boolean updateStudentstateAndExitnotes(String state,String Exitnotes,String time,int id);//迁出学生
	
	//楼宇Building
	public boolean addBuilding(String name,String introduction,String sex);//添加楼宇
	public boolean updateBuilding_buildingadministrator_ID_ById(Integer buildingadministrator_ID,int id);//添加楼宇的管理员
	public List<Building> getBuildings();//查找所有楼宇
	public Building getBuildingById(int id);//通过id查找楼宇
	public List<Building> getBuildingByName(String name);//通过name查找楼宇
	public boolean delBuilding(int id);//删除楼宇
	public boolean updateBuilding(String name,String introduction,String sex,int id);//修改楼宇
	
	//宿舍Dormitory
	public boolean addDormitory(int building_ID,String name,int type,int number,String contact);//添加宿舍
	public List<Dormitory> getDormitorys();//查找所有宿舍
	public Dormitory getDormitoryById(int id);//通过id查找宿舍
	public List<Dormitory> getDormitoryBybuilding_ID(int building_ID);//通过/building_ID查找宿舍 
	public List<Dormitory> getDormitoryByCon(Dormitory dormitory);//通过通过/楼宇/宿舍名/电话查找宿舍
	public boolean delDormitory(int id);//删除宿舍
	public boolean updateDormitory(int building_ID,String name,int type,int number,String contact,int id);//修改宿舍
	
	//缺寝记录Lackofsleep
	public boolean addLackofsleep(int stu_ID,String time,String des);//添加缺寝记录
	public List<Lackofsleep> getLackofsleeps();//查找所有缺寝记录
	public List<Lackofsleep> getLackofsleepsBystu_ID(int stu_ID);//通过学生id查找缺寝记录
	public boolean delLackofsleep(int id);//删除缺寝记录
	

}
