package org.entity;

//学生
public class Student {

	private int id;
	private int stu_ID;//学号
	private String name;//姓名
	private String pwd;//密码
	private String sex;//性别
	private String class1;//班级
	private String state;//状态
	private Dormitory dormitory;//宿舍
	private String exitnotes;//迁出备注
	private String time;//迁出时间
	
	
	
	
	




	




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getStu_ID() {
		return stu_ID;
	}




	public void setStu_ID(int stu_ID) {
		this.stu_ID = stu_ID;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPwd() {
		return pwd;
	}




	public void setPwd(String pwd) {
		this.pwd = pwd;
	}




	public String getSex() {
		return sex;
	}




	public void setSex(String sex) {
		this.sex = sex;
	}




	public String getClass1() {
		return class1;
	}




	public void setClass1(String class1) {
		this.class1 = class1;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public Dormitory getDormitory() {
		return dormitory;
	}




	public void setDormitory(Dormitory dormitory) {
		this.dormitory = dormitory;
	}




	public String getExitnotes() {
		return exitnotes;
	}




	public void setExitnotes(String exitnotes) {
		this.exitnotes = exitnotes;
	}




	public String getTime() {
		return time;
	}




	public void setTime(String time) {
		this.time = time;
	}




	public Student(int id, int stu_ID, String name, String pwd, String sex, String class1, String state,
			Dormitory dormitory, String exitnotes, String time) {
		super();
		this.id = id;
		this.stu_ID = stu_ID;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.class1 = class1;
		this.state = state;
		this.dormitory = dormitory;
		this.exitnotes = exitnotes;
		this.time = time;
	}




	public Student() {}
	
	
	
	
	
	
}
