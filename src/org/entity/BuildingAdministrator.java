package org.entity;


//楼宇管理员
public class BuildingAdministrator {

	private int id;
	private String name;//用户名
	private String pwd;//密码
	private String realname;//真实姓名
	private String sex;//性别
	private String contact;//联系电话
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public BuildingAdministrator(int id, String name, String pwd, String realname, String sex, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.realname = realname;
		this.sex = sex;
		this.contact = contact;
	}
	
	public BuildingAdministrator() {}
	
	
	
	
	
	
	
}
