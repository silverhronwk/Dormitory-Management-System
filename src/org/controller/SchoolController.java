package org.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.entity.Building;
import org.entity.BuildingAdministrator;
import org.entity.Dormitory;
import org.entity.Lackofsleep;
import org.entity.Student;
import org.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SchoolController {

	@Autowired
	private SchoolService service;
	
	
	//加载登录首页
	@RequestMapping(value="Loginjsp")
	public ModelAndView Loginjsp() {
		return new ModelAndView("Login");
	}
	
	//加载后台首页
	@RequestMapping(value="Indexjsp")
	public ModelAndView Indexjsp() {
		return new ModelAndView("Index");
	}
	
	//加载楼宇管理员页面
	@RequestMapping(value="TeacherManagerjsp")
	public ModelAndView TeacherManagerjsp() {
		return new ModelAndView("TeacherManager","TeacherManagers", service.getBuildingAdministrators());
	}
	
	//加载学生管理页面
	@RequestMapping(value="StudentManagerjsp")
	public ModelAndView StudentManagerjsp() {
		return new ModelAndView("StudentManager","Students", service.getStudentsUnemigration());
	}
	
	//加载楼宇管理页面
	@RequestMapping(value="BuildingManagerjsp")
	public ModelAndView BuildingManagerjsp() {
		return new ModelAndView("BuildingManager","Buildings", service.getBuildings());
	}
	
	//加载宿舍管理页面
	@RequestMapping(value="DomitoryManagerjsp")
	public ModelAndView DomitoryManagerjsp() {
		ModelAndView model=new ModelAndView("DomitoryManager");
		model.addObject("allBuildings",service.getBuildings());
		model.addObject("Domitorys",service.getDormitorys());
		return model;
	}
	
	//加载 添加楼宇管理员页面
	@RequestMapping(value="TeacherAddjsp")
	public ModelAndView TeacherAddjsp() {
		return new ModelAndView("TeacherAdd");
	}
	
	//加载 添加学生页面
	@RequestMapping(value="StudentAddjsp")
	public ModelAndView StudentAddjsp() {
		return new ModelAndView("StudentAdd");
	}
	
	//加载 添加楼宇页面
	@RequestMapping(value="BuildingAddjsp")
	public ModelAndView BuildingAddjsp() {
		return new ModelAndView("BuildingAdd");
	}
	
	//加载 添加宿舍页面
	@RequestMapping(value="DomitoryAddjsp")
	public ModelAndView DomitoryAddjsp() {
		return new ModelAndView("DomitoryAdd","DomitoryBuildings",service.getBuildings());
	}
	
	//加载 添加楼宇   的管理员页面
	@RequestMapping(value="TBManagerjsp")
	public ModelAndView TBManagerjsp(Integer Build_id){
		ModelAndView model=new ModelAndView("TBManager");
		model.addObject("Build_id", Build_id);
		model.addObject("Building", service.getBuildingById(Build_id));
		model.addObject("BuildingAdministrators", service.getBuildingAdministrators());
		return model;
	}
	
	//加载学生迁出登记页面
	@RequestMapping(value="StudentQCjsp")
	public ModelAndView StudentQCjsp(){
		return new ModelAndView("StudentQC");
	}
	
	//加载MyStudent.jsp
	@RequestMapping(value="MyStudentjsp")
	public ModelAndView MyStudentjsp(){
		return new ModelAndView("MyStudent","Buildings",service.getBuildings());
	}
	
	//加载LogAdd.jsp缺寝登记页面
	@RequestMapping(value="LogAddjsp")
	public ModelAndView LogAddjsp(Integer Student_ID){
		return new ModelAndView("LogAdd","Student",service.getStudentById(Student_ID));
	}
	
	//楼宇管理员添加学生缺寝
	@RequestMapping(value="LogAddSave")
	public ModelAndView LogAddSave(Integer Building_ID,Integer Log_StudentID,String Log_Date,String Log_Remark){
		service.addLackofsleep(Log_StudentID, Log_Date, Log_Remark);
		return new ModelAndView("LogAdd","msg","<script>alert('添加成功');location.href='MyStudentList.action?Building_ID="+Building_ID+"'</script>");
	}
	
	//加载MyLog.jsp
	@RequestMapping(value="MyLogjsp")
	public ModelAndView MyLogjsp(){
		return new ModelAndView("MyLog","Buildings",service.getBuildings());
	}
	
	//加载MyLog.jsp
	@RequestMapping(value="MyLogList")
	public ModelAndView MyLogList(Integer Domitory_ID,String SearchRow,String SearchKey,Integer Building_ID){
		ModelAndView modelAndView=new ModelAndView("MyLogList");
		//获取当前楼宇的所有在住学生缺寝记录
		List<Lackofsleep> lackofsleeps=new ArrayList<Lackofsleep>();
		for (Lackofsleep lackofsleep : service.getLackofsleeps()) {
			if (lackofsleep.getStudent().getState().equals("入住")) {
				if (lackofsleep.getStudent().getDormitory().getBuilding().getId() == Building_ID) {
					if (Domitory_ID != null && SearchKey != null) {
						if (lackofsleep.getStudent().getDormitory().getId() == Domitory_ID) {
							if (SearchRow.equals("name")) {
								if (lackofsleep.getStudent().getName().equals(SearchKey)) {
									lackofsleeps.add(lackofsleep);
									continue;
								}
							}
							if (SearchRow.equals("stu_ID")) {
								if (lackofsleep.getStudent().getStu_ID() == Integer.parseInt(SearchKey)) {
									lackofsleeps.add(lackofsleep);
									continue;
								}
							}
							if (SearchRow.equals("class1")) {
								if (lackofsleep.getStudent().getClass1().equals(SearchKey)) {
									lackofsleeps.add(lackofsleep);
									continue;
								}
							}
						}
					}else {
						lackofsleeps.add(lackofsleep);
					}
					
				}
			}
		}
		modelAndView.addObject("lackofsleeps", lackofsleeps);
		
		//获取当前楼宇的所有寝室
		List<Dormitory> dormitories=new ArrayList<Dormitory>();
		for (Dormitory dormitory : service.getDormitorys()) {
			if (dormitory.getBuilding().getId() == Building_ID) {
				dormitories.add(dormitory);
			}
		}
		modelAndView.addObject("dormitories", dormitories);
		
		modelAndView.addObject("Building_ID", Building_ID);
		return modelAndView;
	}

	
	//加载MyStudentList.jsp
	@RequestMapping(value="MyStudentList")
	public ModelAndView MyStudentList(Integer Domitory_ID,String SearchRow,String  SearchKey,Integer Building_ID){
		ModelAndView modelAndView=new ModelAndView("MyStudentList");
		
		List<Student> students=new ArrayList<Student>();
		List<Dormitory> dormitories=new ArrayList<Dormitory>();
		for (Student student : service.getStudents()) {
			if (student.getState().equals("入住") && student.getDormitory().getBuilding().getId() ==Building_ID) {
				if (Domitory_ID != null && SearchKey != null) {
					if (student.getDormitory().getId() == Domitory_ID) {
						if (SearchRow.equals("name")) {
							if (student.getName().equals(SearchKey)) {
								students.add(student);
								continue;
							}
						}
						if (SearchRow.equals("stu_ID")) {
							if (student.getStu_ID() == Integer.parseInt(SearchKey)) {
								students.add(student);
								continue;
							}
						}
						if (SearchRow.equals("class1")) {
							if (student.getClass1().equals(SearchKey)) {
								students.add(student);
								continue;
							}
						}
					}
				}else {
					students.add(student);
				}
			}
		}
		for (Dormitory dormitory : service.getDormitorys()) {
			if (dormitory.getBuilding().getId() == Building_ID) {
				dormitories.add(dormitory);
			}
		}
		modelAndView.addObject("students", students);
		modelAndView.addObject("dormitories", dormitories);
		modelAndView.addObject("Building_ID", Building_ID);
		return modelAndView;
	}
	
	//加载学生迁出登记2页面
	@RequestMapping(value="StudentQC2jsp")
	public ModelAndView StudentQC2jsp(Integer Student_Username){
		if (service.getStudentBystu_ID(Student_Username) != null) {
			if (service.getStudentBystu_ID(Student_Username).getState().equals("入住")) {
				return new ModelAndView("StudentQC2","student",service.getStudentBystu_ID(Student_Username));
			}
			return new ModelAndView("StudentQC","msg","该学生未入住或已毕业");
		}
		return new ModelAndView("StudentQC","msg","该学号不存在");
	}
	
	
	
	//登录
	@RequestMapping(value="GoLogin")
	public ModelAndView GoLogin(String Type,String Username,String Password,HttpServletRequest request) {
		HttpSession session=request.getSession();
		if (Type.equals("系统管理员")) {
			if (service.getAdministratorByNamePwd(Username, Password) != null) {
				session.setAttribute("user", Username);
				session.setAttribute("type", Type);
				return new ModelAndView("Index");
			}
		}else if (Type.equals("楼宇管理员")) {
			if (service.getBuildingAdministratorByNamePwd(Username, Password) != null) {
				session.setAttribute("user", Username);
				session.setAttribute("type", Type);
				return new ModelAndView("Index");
			}
		}else if (Type.equals("学生")) {
			if (service.getStudentByNamePwd(Username, Password) != null && service.getStudentByNamePwd(Username, Password).getState().equals("入住")) {
				session.setAttribute("user", Username);
				session.setAttribute("type", Type);
				session.setAttribute("student", service.getStudentByNamePwd(Username, Password));
				return new ModelAndView("Index");
			}
		}
		return new ModelAndView("Login","msg","<script>alert('用户名或密码错误');location.href='Loginjsp.action'</script>");
	}
	
	//添加楼宇管理员
	@RequestMapping(value="TeacherAddSave")
	public ModelAndView TeacherAddSave(Integer id,String Teacher_Username, String Teacher_Password, String Teacher_Name, String Teacher_Sex, String Teacher_Tel) {
		if (id != null) {
			if (!service.getBuildingAdministratorById(id).getName().equals(Teacher_Username)) {
				for(BuildingAdministrator buildingAdministrator:service.getBuildingAdministrators()) {
					if (buildingAdministrator.getName().equals(Teacher_Username)) {
						return new ModelAndView("TeacherAdd","msg","<script>alert('该用户名已存在');location.href='TeacherUpdatejsp.action?id="+id+"';</script>");
					}
				}
			}
			if (service.updateBuildingAdministrator(Teacher_Username, Teacher_Password, Teacher_Name, Teacher_Sex, Teacher_Tel, id)) {
				return new ModelAndView("TeacherAdd","msg","<script>alert('修改楼宇管理员成功');location.href='TeacherManagerjsp.action';</script>");
			}
			return new ModelAndView("TeacherAdd","msg","<script>alert('信息有误');location.href='TeacherUpdatejsp.action?id="+id+"';</script>");
		}else {
			for (BuildingAdministrator buildingAdministrator : service.getBuildingAdministrators()) {
				if (buildingAdministrator.getName().equals(Teacher_Username)) {
					return new ModelAndView("TeacherAdd","msg","<script>alert('该用户名已存在');location.href='TeacherAddjsp.action';</script>");
				}
			}
			if (service.addBuildingAdministrator(Teacher_Username, Teacher_Password, Teacher_Name, Teacher_Sex, Teacher_Tel)) {
				return new ModelAndView("TeacherAdd","msg","<script>alert('添加楼宇管理员成功');location.href='TeacherManagerjsp.action';</script>");
			}
			return new ModelAndView("TeacherAdd","msg","<script>alert('信息有误');location.href='TeacherAddjsp.action';</script>");
		}
	}
	
	//添加学生
	@RequestMapping(value="StudentAddSave")
	public ModelAndView StudentAddSave(Integer id,Integer Student_Username, String Student_Name, String Student_Password, String Student_Sex, String Student_Class) {
		if (id != null) {
			if (!service.getStudentById(id).getName().equals(Student_Name)) {
				for (Student student : service.getStudents()) {
					if (student.getName().equals(Student_Name)) {
						return new ModelAndView("StudentAdd","msg","<script>alert('该用户名已存在');location.href='StudentUpdatejsp.action?id="+id+"';</script>");
					}
				}
			}
			if (service.getStudentById(id).getState().equals("入住")) {
				if (service.getStudentById(id).getDormitory().getBuilding().getSex().equals(Student_Sex)) {
					if (service.updateStudent(Student_Username, Student_Name, Student_Password, Student_Sex, Student_Class, id)) {
						return new ModelAndView("StudentAdd","msg","<script>alert('修改学生成功');location.href='StudentManagerjsp.action';</script>");
					}
					return new ModelAndView("StudentAdd","msg","<script>alert('信息有误');location.href='StudentUpdatejsp.action?id="+id+"';</script>");
				}
				return new ModelAndView("StudentAdd","msg","<script>alert('该学生若更改性别，请更换楼宇');location.href='StudentUpdatejsp.action?id="+id+"';</script>");
			}
			
			if (service.updateStudent(Student_Username, Student_Name, Student_Password, Student_Sex, Student_Class, id)) {
				return new ModelAndView("StudentAdd","msg","<script>alert('修改学生成功');location.href='StudentManagerjsp.action';</script>");
			}
			return new ModelAndView("StudentAdd","msg","<script>alert('信息有误');location.href='StudentUpdatejsp.action?id="+id+"';</script>");
		}else {
			for (Student student : service.getStudents()) {
				if (student.getName().equals(Student_Name)) {
					return new ModelAndView("StudentAdd","msg","<script>alert('该用户名已存在');location.href='StudentAddjsp.action';</script>");
				}else if (student.getStu_ID() == Student_Username) {
					return new ModelAndView("StudentAdd","msg","<script>alert('该学号已存在');location.href='StudentAddjsp.action';</script>");
				}
			}
			if (service.getStudentBystu_ID(Student_Username) == null) {
				if (service.addStudent(Student_Username, Student_Name,Student_Password , Student_Sex, Student_Class, "未入住")) {
					return new ModelAndView("StudentAdd","msg","<script>alert('添加学生成功');location.href='StudentManagerjsp.action';</script>");
				}
				return new ModelAndView("StudentAdd","msg","<script>alert('信息有误，添加学生失败');location.href='StudentAddjsp.action';</script>");
			}
			return new ModelAndView("StudentAdd","msg","<script>alert('该学号已存在');location.href='StudentAddjsp.action';</script>");
		}
	}
	
	//添加楼宇
	@RequestMapping(value="BuildingAddSave")
	public ModelAndView BuildingAddSave(Integer id,String Building_Name, String Building_Introduction, String Building_Sex) {
		if (id != null) {
			if (!service.getBuildingById(id).getSex().equals(Building_Sex)) {
				for (Student student : service.getStudents()) {
					if (student.getState().equals("入住") && student.getDormitory().getBuilding().getId() == id) {
						return new ModelAndView("BuildingAdd","msg","<script>alert('有学生在内入住，不可更改楼宇性别');location.href='BuildingUpdatejsp.action?id="+id+"';</script>");
					}
				}
			}
			service.updateBuilding(Building_Name, Building_Introduction, Building_Sex, id);
			return new ModelAndView("BuildingAdd","msg","<script>alert('楼宇修改成功');location.href='BuildingManagerjsp.action';</script>");
		}else {
			if (service.addBuilding(Building_Name, Building_Introduction, Building_Sex)) {
				return new ModelAndView("BuildingAdd","msg","<script>alert('楼宇添加成功');location.href='BuildingManagerjsp.action';</script>");
			}
			return new ModelAndView("BuildingAdd","msg","<script>alert('楼宇添加失败');location.href='BuildingAddjsp.action';</script>");
		}
	}
	
	//添加宿舍
	@RequestMapping(value="DomitoryAddSave")
	public ModelAndView DomitoryAddSave(Integer id,Integer Domitory_BuildingID, String Domitory_Name, Integer Domitory_Type,Integer Domitory_Number,String Domitory_Tel) {
		for (Dormitory dormitory : service.getDormitorys()) {
			if (dormitory.getBuilding().getId() == Domitory_BuildingID && dormitory.getName().equals(Domitory_Name)) {
				return new ModelAndView("DomitoryAdd","msg","<script>alert('该宿舍已创建');location.href='DomitoryUpdatejsp.action?Domitory_ID="+id+"';</script>");
			}
		}
		if (id != null) {
			if (Domitory_Type  >= Domitory_Number) {
				service.updateDormitory(Domitory_BuildingID, Domitory_Name, Domitory_Type, Domitory_Number, Domitory_Tel, id);
				return new ModelAndView("DomitoryAdd","msg","<script>alert('修改宿舍成功');location.href='DomitoryManagerjsp.action';</script>");
			}
			return new ModelAndView("DomitoryAdd","msg","<script>alert('宿舍已住人数大于宿舍类型');location.href='DomitoryUpdatejsp.action?Domitory_ID="+id+"';</script>");
		}else {
			if (Domitory_Type >= Domitory_Number ) {
				service.addDormitory(Domitory_BuildingID, Domitory_Name, Domitory_Type, Domitory_Number, Domitory_Tel);
				return new ModelAndView("DomitoryAdd","msg","<script>alert('添加宿舍成功');location.href='DomitoryManagerjsp.action';</script>");
			}
			return new ModelAndView("DomitoryAdd","msg","<script>alert('宿舍已住人数大于宿舍类型');location.href='DomitoryAddjsp.action';</script>");
		}
	}
	
	//添加楼宇  的管理员
	@RequestMapping(value="TBAddSave")
	public ModelAndView TBAddSave(Integer Build_id,Integer TB_TeacherID) {
		for (Building building : service.getBuildings()) {
			if (building.getBuildingAdministrator() != null) {
				if (building.getBuildingAdministrator().getId() == TB_TeacherID) {
					return new ModelAndView("TBManager","msg", "<script>alert('该楼宇管理员不可同时管理多栋楼宇');location.href='BuildingManagerjsp.action';</script>");
				}
			}
		}
		service.updateBuilding_buildingadministrator_ID_ById(TB_TeacherID, Build_id);
		return new ModelAndView("TBManager","msg", "<script>alert('楼宇添加管理员成功');location.href='BuildingManagerjsp.action';</script>");
	}
	
	
	//通过用户名/真实姓名/电话查找楼宇管理员
	@RequestMapping(value="findTeacherManager")
	public ModelAndView findTeacherManager(String SearchRow, String SearchKey) {
		return new ModelAndView("TeacherManager","TeacherManagers",service.getBuildingAdministratorByor(SearchRow, SearchKey));
	}
	
	//通过state/学号/真实姓名/电话查找学生
	@RequestMapping(value="findStudent")
	public ModelAndView findStudent(String State,String SearchRow, String SearchKey) {
		return new ModelAndView("StudentManager","Students",service.getstudentByor(State, SearchRow, SearchKey));
	}
	
	//通过名称查找楼宇
	@RequestMapping(value="findBuilding")
	public ModelAndView findBuilding(String SearchKey) {
		return new ModelAndView("BuildingManager","Buildings",service.getBuildingByName(SearchKey));
	}
	
	//通过/楼宇/宿舍名/电话查找宿舍
	@RequestMapping(value="findDomitory")
	public ModelAndView findDomitory(Integer Domitory_BuildingID,String SearchRow,String SearchKey) {
		List<Dormitory> dormitories=new ArrayList<Dormitory>();
		if (Domitory_BuildingID == null) {
			if (SearchRow.equals("name")) {
				for (Dormitory dormitory : service.getDormitorys()) {
					if (dormitory.getName().equals(SearchKey)) {
						dormitories.add(dormitory);
					}
				}
			}else {
				for (Dormitory dormitory : service.getDormitorys()) {
					if (dormitory.getContact().equals(SearchKey)) {
						dormitories.add(dormitory);
					}
				}
			}
		}else {
			if (SearchRow.equals("name")) {
				for (Dormitory dormitory : service.getDormitorys()) {
					if (dormitory.getName().equals(SearchKey) && dormitory.getBuilding().getId() ==Domitory_BuildingID ) {
						dormitories.add(dormitory);
					}
				}
			}else {
				for (Dormitory dormitory : service.getDormitorys()) {
					if (dormitory.getContact().equals(SearchKey) && dormitory.getBuilding().getId() ==Domitory_BuildingID) {
						dormitories.add(dormitory);
					}
				}
			}
		}
		return new ModelAndView("DomitoryManager","Domitorys",dormitories);
	}
	
	
	//删除楼宇管理员
	@RequestMapping(value="TeacherDel")
	public ModelAndView TeacherDel(Integer id) {
		for (Building building : service.getBuildings()) {
			if (building.getBuildingAdministrator() != null) {
				if (building.getBuildingAdministrator().getId() == id) {
					return new ModelAndView("TeacherManager","msg","<script>alert('该楼宇管理员正在管理"+building.getName()+"');location.href='TeacherManagerjsp.action';</script>");
				}
			}
		}
		service.delBuildingAdministrator(id);
		return new ModelAndView("redirect:TeacherManagerjsp.action");
	}
	
	//删除学生
	@RequestMapping(value="StudentDel")
	public ModelAndView StudentDel(Integer id) {
		if (service.getStudentById(id).getState().equals("未入住")) {
			service.delStudent(id);
			return new ModelAndView("StudentManager","msg","<script>alert('删除学生成功');location.href='StudentManagerjsp.action';</script>");
		}else {
			service.updateDormitory(service.getStudentById(id).getDormitory().getBuilding().getId(), service.getStudentById(id).getDormitory().getName(), service.getStudentById(id).getDormitory().getType(), service.getStudentById(id).getDormitory().getNumber()-1, service.getStudentById(id).getDormitory().getContact(), service.getStudentById(id).getDormitory().getId());
			for (Lackofsleep lackofsleep : service.getLackofsleeps()) {
				if (lackofsleep.getStudent().getStu_ID() == service.getStudentById(id).getStu_ID()) {
					service.delLackofsleep(lackofsleep.getId());
				}
			}
			service.delStudent(id);
			return new ModelAndView("StudentManager","msg","<script>alert('删除学生成功');location.href='StudentManagerjsp.action';</script>");
		}
	}
	
	//删除楼宇的管理员
	@RequestMapping(value="TBDel")
	public ModelAndView TBDel(Integer Build_id) {
		service.updateBuilding_buildingadministrator_ID_ById((Integer) null,Build_id);
		return new ModelAndView("TBManager","msg", "<script>alert('移除成功');location.href='BuildingManagerjsp.action';</script>");
	}
	
	//删除楼宇
	@RequestMapping(value="BuildingDel")
	public ModelAndView BuildingDel(Integer id) {
		for (Dormitory dormitory : service.getDormitorys()) {
			if (dormitory.getBuilding().getId() == id) {
				return new ModelAndView("BuildingManager","msg","<script>alert('该楼宇内已建立宿舍，请先删除宿舍');location.href='BuildingManagerjsp.action';</script>");
			}
		}
		if (service.delBuilding(id)) {
			return new ModelAndView("BuildingManager","msg","<script>alert('删除成功');location.href='BuildingManagerjsp.action';</script>");
		}
		return new ModelAndView("redirect:BuildingManagerjsp.action");
	}
	
	//删除宿舍
	@RequestMapping(value="DomitoryDel")
	public ModelAndView DomitoryDel(Integer Domitory_ID) {
		if (service.getDormitoryById(Domitory_ID).getNumber() == 0 ) {
			service.delDormitory(Domitory_ID);
			return new ModelAndView("DomitoryManager","msg","<script>alert('删除宿舍成功');location.href='DomitoryManagerjsp.action';</script>");
		}
		return new ModelAndView("DomitoryManager","msg","<script>alert('宿舍内已有学生入住，请先将宿舍内学生迁出或更换宿舍');location.href='DomitoryManagerjsp.action';</script>");
	}
	
	//加载 修改楼宇管理员页面
	@RequestMapping(value="TeacherUpdatejsp")
	public ModelAndView TeacherUpdatejsp(Integer id) {
		return new ModelAndView("TeacherAdd","buildingadministrator",service.getBuildingAdministratorById(id));
	}
	
	//加载 修改学生页面
	@RequestMapping(value="StudentUpdatejsp")
	public ModelAndView StudentUpdatejsp(Integer id) {
		return new ModelAndView("StudentAdd","student", service.getStudentById(id));
	}
	
	//加载 修改楼宇页面
	@RequestMapping(value="BuildingUpdatejsp")
	public ModelAndView BuildingUpdatejsp(Integer id) {
		return new ModelAndView("BuildingAdd","Building", service.getBuildingById(id));
	}
	
	//加载 修改宿舍页面
	@RequestMapping(value="DomitoryUpdatejsp")
	public ModelAndView DomitoryUpdatejsp(Integer Domitory_ID) {
		ModelAndView model=new ModelAndView("DomitoryAdd");
		model.addObject("DomitoryBuildings",service.getBuildings());
		model.addObject("updateDomitory",service.getDormitoryById(Domitory_ID));
		return model;
	}
	
	//加载学生入住登记页面
	@RequestMapping(value="StudentRZjsp")
	public ModelAndView StudentRZjsp(Integer BuildingID) {
		ModelAndView model=new ModelAndView("StudentRZ");
		model.addObject("Buildings", service.getBuildings());
		if (BuildingID != null) {
			model.addObject("BuildingID", BuildingID);
			model.addObject("Dormitorys", service.getDormitoryBybuilding_ID(BuildingID));
		}
		return model ;
	}
	
	//加载调换学生学号页面
	@RequestMapping(value="StudentTHjsp")
	public ModelAndView StudentTHjsp() {
		return new ModelAndView("StudentTH") ;
	}
	
	//加载学生寝室调换页面
	@RequestMapping(value="StudentTH2jsp")
	public ModelAndView StudentTH2jsp(Integer BuildingID,Integer Student_Username) {
		ModelAndView model=new ModelAndView("StudentTH");
		if (service.getStudentBystu_ID(Student_Username) != null) {
			if (service.getStudentBystu_ID(Student_Username).getState().equals("入住")) {
				ModelAndView model1=new ModelAndView("StudentTH2");
				model1.addObject("Student", service.getStudentBystu_ID(Student_Username));
				model1.addObject("Buildings", service.getBuildings());
				if (BuildingID !=null) {
					model1.addObject("BuildingID", BuildingID);
					model1.addObject("Dormitorys", service.getDormitoryBybuilding_ID(BuildingID));
				}
				return model1;
			}
			model.addObject("msg","<script>alert('该学生未入住或已毕业');location.href='StudentTHjsp.action'</script>");
			return model;
		}
		model.addObject("msg","<script>alert('不存在该学号');location.href='StudentTHjsp.action'</script>");
		return model;
	}
	
	//学生入住登记
	@RequestMapping(value="StudentRZSave")
	public ModelAndView StudentRZSave( Integer Domitory_ID,Integer Student_Username){
		if (service.getStudentBystu_ID(Student_Username) != null) {
			if (service.getStudentBystu_ID(Student_Username).getState().equals("未入住")) {
				if (service.getDormitoryById(Domitory_ID).getNumber() < service.getDormitoryById(Domitory_ID).getType()) {
					if (service.getDormitoryById(Domitory_ID).getBuilding().getSex().equals(service.getStudentBystu_ID(Student_Username).getSex())) {
						service.addStudentIntoDormitory(Domitory_ID, service.getStudentBystu_ID(Student_Username).getId());
						service.updateStudentstate("入住", service.getStudentBystu_ID(Student_Username).getId());
						service.updateDormitory(service.getDormitoryById(Domitory_ID).getBuilding().getId(), service.getDormitoryById(Domitory_ID).getName(), service.getDormitoryById(Domitory_ID).getType(), service.getDormitoryById(Domitory_ID).getNumber()+1, service.getDormitoryById(Domitory_ID).getContact(), Domitory_ID);
						return new ModelAndView("StudentRZ","msg","<script>alert('入住成功');location.href='StudentRZjsp.action';</script>");
					}
					return new ModelAndView("StudentRZ","msg","<script>alert('该学生与楼宇性别不符');location.href='StudentRZjsp.action';</script>");
				}
				return new ModelAndView("StudentRZ","msg","<script>alert('该宿舍已满');location.href='StudentRZjsp.action';</script>");
			}
			return new ModelAndView("StudentRZ","msg","<script>alert('该学生已入住或已毕业');location.href='StudentRZjsp.action';</script>");
		}
		return new ModelAndView("StudentRZ","msg","<script>alert('不存在该学号');location.href='StudentRZjsp.action';</script>");
	}
	
	
	//学生调换
	@RequestMapping(value="StudentTHSave")
	public ModelAndView StudentTHSave(Integer Building_ID,Integer Student_stu_ID,Integer new_Domitory_ID ,Integer old_dormitory_ID){
		if (service.getDormitoryById(new_Domitory_ID).getNumber() < service.getDormitoryById(new_Domitory_ID).getType()) {
			if (service.getDormitoryById(new_Domitory_ID).getBuilding().getSex().equals(service.getStudentBystu_ID(Student_stu_ID).getSex())) {
				service.updateStudentDormitoryId(new_Domitory_ID, Student_stu_ID);
				service.updateDormitory(service.getDormitoryById(new_Domitory_ID).getBuilding().getId(), service.getDormitoryById(new_Domitory_ID).getName(), service.getDormitoryById(new_Domitory_ID).getType(), service.getDormitoryById(new_Domitory_ID).getNumber()+1, service.getDormitoryById(new_Domitory_ID).getContact(), new_Domitory_ID);
				service.updateDormitory(service.getDormitoryById(old_dormitory_ID).getBuilding().getId(), service.getDormitoryById(old_dormitory_ID).getName(), service.getDormitoryById(old_dormitory_ID).getType(), service.getDormitoryById(old_dormitory_ID).getNumber()-1, service.getDormitoryById(old_dormitory_ID).getContact(), old_dormitory_ID);
				return new ModelAndView("StudentTH2","msg","<script>alert('调换成功');location.href='StudentTHjsp.action';</script>");
			}
			return new ModelAndView("StudentTH2","msg","<script>alert('该学生性别与楼宇不符');location.href='StudentTH2jsp.action?BuildingID="+Building_ID+"&Student_Username="+Student_stu_ID+"';</script>");
		}
		return new ModelAndView("StudentTH2","msg","<script>alert('该宿舍已满');location.href='StudentTH2jsp.action?BuildingID="+Building_ID+"&Student_Username="+Student_stu_ID+"';</script>");
	}
	
	
	//学生迁出
	@RequestMapping(value="StudentQCSave")
	public ModelAndView StudentQCSave(Integer Student_ID,String Out_Remark ,Integer Student_DormitoryID){
		service.updateDormitory(service.getDormitoryById(Student_DormitoryID).getBuilding().getId(), service.getDormitoryById(Student_DormitoryID).getName(), service.getDormitoryById(Student_DormitoryID).getType(), service.getDormitoryById(Student_DormitoryID).getNumber()-1, service.getDormitoryById(Student_DormitoryID).getContact(), Student_DormitoryID);
		service.updateStudentstateAndExitnotes("毕业", Out_Remark,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), Student_ID);
		return new ModelAndView("StudentQC2","msg","迁出成功");
	}
	
	//加载搜索缺寝学生页面
	@RequestMapping(value="AdminLogjsp")
	public ModelAndView AdminLogjsp(Integer BuildingID){
		ModelAndView model=new ModelAndView("AdminLog");
		model.addObject("Buildings", service.getBuildings());
		if (BuildingID != null) {
			model.addObject("BuildingID", BuildingID);
			model.addObject("Dormitorys", service.getDormitoryBybuilding_ID(BuildingID));
		}
		return model;
	}
	
	//加载所有毕业的学生
	@RequestMapping(value="OutListjsp")
	public ModelAndView OutListjsp(){
		ModelAndView modelAndView=new ModelAndView("OutList");
		List<Student> out_students=new ArrayList<Student>();
		for (Student student : service.getStudents()) {		
			if (student.getState().equals("毕业")) {
				out_students.add(student);
			}
		}
		modelAndView.addObject("out_students", out_students);
		return modelAndView;
	}
	
	//通过学号查找该学生的缺寝记录
	@RequestMapping(value="AdminLogList")
	public ModelAndView AdminLogList(Integer Student_Username,HttpServletRequest request){
		if (service.getStudentBystu_ID(Student_Username) != null) {
			String state=service.getStudentBystu_ID(Student_Username).getState();
			if (state.equals("入住") || state.equals("毕业")  ) {
				if (service.getLackofsleepsBystu_ID(Student_Username).size() > 0) {
					return new ModelAndView("AdminLogList","Lackofsleeps",service.getLackofsleepsBystu_ID(Student_Username));
				}
				return new ModelAndView("AdminLog","msg","<script>alert('该学生无缺寝记录');location.href='AdminLogjsp.action';</script>");
			}
			return new ModelAndView("AdminLog","msg","<script>alert('该学生未入住过');location.href='AdminLogjsp.action';</script>");
		}
		return new ModelAndView("AdminLog","msg","<script>alert('未找到该学号');location.href='AdminLogjsp.action';</script>");
	}
	
	//加载修改密码页面
	@RequestMapping(value="PasswordUpdatejsp")
	public ModelAndView PasswordUpdatejsp(){
		return new ModelAndView("PasswordUpdate");
	}
	
	//加载学生的缺寝记录
	@RequestMapping(value="StudentLogjsp")
	public ModelAndView StudentLogjsp(HttpServletRequest request){
		HttpSession session=request.getSession();
		Student student=(Student) session.getAttribute("student");
		ModelAndView modelAndView=new ModelAndView("StudentLog");
		modelAndView.addObject("myLackofsleeps", service.getLackofsleepsBystu_ID(student.getStu_ID()));
		return modelAndView;
	}
	
	//修改密码
	@RequestMapping(value="PasswordUpdateSave")
	public ModelAndView PasswordUpdateSave(String Password,String Password2,HttpServletRequest request){
		HttpSession session=request.getSession();
		if (session.getAttribute("type").equals("系统管理员")) {
			if (service.getAdministratorByNamePwd(session.getAttribute("user").toString(), Password) != null) {
				service.updatePwdByName("administrator",Password2, session.getAttribute("user").toString());
				return new ModelAndView("PasswordUpdate","msg1","密码修改成功");
			}
			return new ModelAndView("PasswordUpdate","msg","原密码错误");
		}else if (session.getAttribute("type").equals("楼宇管理员")) {
			if (service.getBuildingAdministratorByNamePwd(session.getAttribute("user").toString(), Password) != null) {
				service.updatePwdByName("buildingadministrator",Password2, session.getAttribute("user").toString());
				return new ModelAndView("PasswordUpdate","msg1","密码修改成功");
			}
			return new ModelAndView("PasswordUpdate","msg","原密码错误");
		}else if (session.getAttribute("type").equals("学生")) {
			if (service.getStudentByNamePwd(session.getAttribute("user").toString(), Password) != null) {
				service.updatePwdByName("student",Password2, session.getAttribute("user").toString());
				return new ModelAndView("PasswordUpdate","msg1","密码修改成功");
			}
			return new ModelAndView("PasswordUpdate","msg","原密码错误");
		}
		return new ModelAndView("Loginjsp");
	}
	
	//退出系统
	@RequestMapping(value="Quit")
	public ModelAndView Quit(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		session.removeAttribute("type");
		return new ModelAndView("Login");
	}
	
	
	//下载所有入住学生文件
	@RequestMapping(value="downloadstudents")
	public @ResponseBody String downloadstudents(HttpServletResponse response ){
		response.setContentType("application/binary;charset=UTF-8");
		try {
			ServletOutputStream out=response.getOutputStream();
			try {
				//设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
				response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(" .xls", "UTF-8"));
			} catch (UnsupportedEncodingException  e1) {
				e1.printStackTrace();
			}
			String[] titles = { "宿舍名", "楼宇管理员", "楼宇名称", "学生" };
			export(titles, out);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败"; 
		}
		
	}
	
	
	
	public void export(String[] titles, ServletOutputStream out) throws Exception{
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			 // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = hssfSheet.createRow(0);
            
           // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示                
            }
            
            // 第五步，写入实体数据 
            
            //取出所有入住的学生
            List<Student> students=new ArrayList<Student>();
            for (Student student : service.getStudents()) {
				if (student.getState().equals("入住")) {
					students.add(student);
				}
			}
            
            // 第六步，创建单元格，并设置值 
            for (int i = 0; i < students.size(); i++) {
            	row=hssfSheet.createRow(i+1);
            	Student student=students.get(i);
            	
            	row.createCell(0).setCellValue(student.getDormitory().getName());
            	
            	String  buildingAdministrator_name = null;
            	if(student.getDormitory().getBuilding().getBuildingAdministrator() != null){
               	 buildingAdministrator_name =student.getDormitory().getBuilding().getBuildingAdministrator().getName();
                }
                row.createCell(1).setCellValue(buildingAdministrator_name);
                
                row.createCell(2).setCellValue(student.getDormitory().getBuilding().getName());
                row.createCell(3).setCellValue(student.getName());
            }
            try {
				workbook.write(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导出信息失败！");
		}
	}
}
