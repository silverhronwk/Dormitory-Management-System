package org.service;

import java.util.List;
import org.dao.Mapper;
import org.entity.Administrator;
import org.entity.Building;
import org.entity.BuildingAdministrator;
import org.entity.Dormitory;
import org.entity.Lackofsleep;
import org.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

	@Autowired
	private Mapper mapper;
	
	//系统管理员
	public boolean addAdministrator(String name,String pwd) {
		//添加系统管理员
		return mapper.addAdministrator(name, pwd);
	}
	public List<Administrator> getAdministrators(){
		//查找所有系统管理员
		return mapper.getAdministrators();
	}
	public Administrator getAdministratorById(int id) {
		//通过id查找系统管理员
		return mapper.getAdministratorById(id);
	}
	public Administrator getAdministratorByNamePwd(String name,String pwd) {
		//通过name,pwd查找系统管理员
		return mapper.getAdministratorByNamePwd(name, pwd);
	}
	public boolean delAdministrator(int id) {
		//删除系统管理员
		return mapper.delAdministrator(id);
	}
	public boolean updateAdministrator(String name,String pwd,int id) {
		//修改系统管理员
		return mapper.updateAdministrator(name, pwd, id);
	}
	public boolean updatePwdByName(String tableName,String pwd,String  name) {
		//修改密码
		return mapper.updatePwdByName(tableName,pwd, name);
	}
	
	//楼宇管理员BuildingAdministrator
	public boolean addBuildingAdministrator(String name,String pwd,String realname,String sex,String contact) {
		//添加楼宇管理员
		return mapper.addBuildingAdministrator(name, pwd, realname, sex, contact);
	}
	public List<BuildingAdministrator> getBuildingAdministrators(){
		//查找所有楼宇管理员
		return mapper.getBuildingAdministrators();
	}
	public BuildingAdministrator getBuildingAdministratorById(int id) {
		//通过id查找楼宇管理员
		return mapper.getBuildingAdministratorById(id);
	}
	public BuildingAdministrator getBuildingAdministratorByNamePwd(String name,String pwd) {
		//通过NamePwd查找楼宇管理员 
		return mapper.getBuildingAdministratorByNamePwd(name, pwd);
	}
	public List<BuildingAdministrator> getBuildingAdministratorByor(String SearchRow, String SearchKey) {
		//通过用户名/真实姓名/电话查找楼宇管理员
		return mapper.getBuildingAdministratorByor(SearchRow, SearchKey);
	}
	public boolean delBuildingAdministrator(int id) {
		//删除楼宇管理员
		return mapper.delBuildingAdministrator(id);
	}
	public boolean updateBuildingAdministrator(String name,String pwd,String realname,String sex,String contact,int id) {
		//修改楼宇管理员
		return mapper.updateBuildingAdministrator(name, pwd, realname, sex, contact, id);
	}
	
	//学生Student
	public boolean addStudent(int stu_ID,String name,String pwd,String sex,String class1,String state) {
		//添加学生
		return mapper.addStudent(stu_ID, name, pwd, sex, class1, state);
	}
	public boolean addStudentIntoDormitory(int dormitory_ID,int id) {
		//学生入住宿舍
		return mapper.addStudentIntoDormitory(dormitory_ID, id);
	}
	public List<Student> getStudentsUnemigration(){
		//查找所有未毕业学生
		return mapper.getStudentsUnemigration();
	}
	public List<Student> getStudents(){
		//查找所有学生
		return mapper.getStudents();
	}
	public Student getStudentById(int id) {
		//通过id查找学生
		return mapper.getStudentById(id);
	}
	public Student getStudentByNamePwd(String name,String pwd) {
		//通过NamePwd查找学生
		return mapper.getStudentByNamePwd(name, pwd);
	}
	public Student getStudentBystu_ID(int stu_ID) {
		//通过stu_ID查找学生
		return mapper.getStudentBystu_ID(stu_ID);
	}
	public List<Student> getstudentByor(String State,String SearchRow,String SearchKey){
		//通过state/学号/真实姓名/电话查找楼宇管理员
		return mapper.getstudentByor(State, SearchRow, SearchKey);
	}
	public boolean delStudent(int id) {
		//删除学生
		return mapper.delStudent(id);
	}
	public boolean updateStudent(int stu_ID,String name,String pwd,String sex,String class1,int id) {
		//修改学生
		return mapper.updateStudent(stu_ID, name, pwd, sex, class1, id);
	}
	public boolean updateStudentstate(String state,int id) {
		//修改学生状态
		return mapper.updateStudentstate(state, id);
	}
	public boolean updateStudentDormitoryId(int dormitory_ID,int stu_ID) {
		//修改学生所住宿舍
		return mapper.updateStudentDormitoryIdBystu_ID(dormitory_ID, stu_ID);
	}
	public boolean updateStudentstateAndExitnotes(String state,String Exitnotes,String time,int id) {
		//迁出学生
		return mapper.updateStudentstateAndExitnotes(state, Exitnotes,time, id);
	}
	
	//楼宇Building
	public boolean addBuilding(String name,String introduction,String sex) {
		//添加楼宇
		return mapper.addBuilding(name, introduction, sex);
	}
	public boolean updateBuilding_buildingadministrator_ID_ById(Integer buildingadministrator_ID,int id) {
		//添加楼宇的管理员
		return mapper.updateBuilding_buildingadministrator_ID_ById(buildingadministrator_ID, id);
	}
	public List<Building> getBuildings(){
		//查找所有楼宇
		return mapper.getBuildings();
	}
	public Building getBuildingById(int id) {
		//通过id查找楼宇
		return mapper.getBuildingById(id);
	}
	public List<Building> getBuildingByName(String name) {
		//通过name查找楼宇
		return mapper.getBuildingByName(name);
	}
	public boolean delBuilding(int id) {
		//删除楼宇
		return mapper.delBuilding(id);
	}
	public boolean updateBuilding(String name,String introduction,String sex,int id) {
		//修改楼宇
		return mapper.updateBuilding(name, introduction, sex, id);
	}
	
	//宿舍Dormitory
	public boolean addDormitory(int building_ID,String name,int type,int number,String contact) {
		//添加宿舍
		return mapper.addDormitory(building_ID, name, type, number, contact);
	}
	public List<Dormitory> getDormitorys(){
		//查找所有宿舍
		return mapper.getDormitorys();
	}
	public Dormitory getDormitoryById(int id) {
		//通过id查找宿舍
		return mapper.getDormitoryById(id);
	}
	public List<Dormitory> getDormitoryBybuilding_ID(int building_ID) {
		//通过/building_ID查找宿舍 
		return mapper.getDormitoryBybuilding_ID(building_ID);
	}
	public List<Dormitory> getDormitoryByCon(Dormitory dormitory){
		//通过通过/楼宇/宿舍名/电话查找宿舍
		return mapper.getDormitoryByCon(dormitory);
	}
	public boolean delDormitory(int id) {
		//删除宿舍
		return mapper.delDormitory(id);
	}
	public boolean updateDormitory(int building_ID,String name,int type,int number,String contact,int id) {
		//修改宿舍
		return mapper.updateDormitory(building_ID, name, type, number, contact, id);
	}
	
	
	//缺寝记录Lackofsleep
	public boolean addLackofsleep(int stu_ID,String time,String des) {
		//添加缺寝记录
		return mapper.addLackofsleep(stu_ID, time, des);
	}
	public List<Lackofsleep> getLackofsleeps(){
		//查找所有缺寝记录
		return mapper.getLackofsleeps();
	}
	public List<Lackofsleep> getLackofsleepsBystu_ID(int stu_ID){
		//通过学生id查找缺寝记录
		return mapper.getLackofsleepsBystu_ID(stu_ID);
	}
	public boolean delLackofsleep(int id) {
		//删除缺寝记录
		return mapper.delLackofsleep(id);
	}
	
	
}
