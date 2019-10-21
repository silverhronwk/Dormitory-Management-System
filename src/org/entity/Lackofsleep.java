package org.entity;

//缺寝
public class Lackofsleep {

	private int id;
	private Student student;//入住学生
	private String time ;//缺寝日期
	private String des;//备注
	
	
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Student getStudent() {
		return student;
	}




	public void setStudent(Student student) {
		this.student = student;
	}




	public String getTime() {
		return time;
	}




	public void setTime(String time) {
		this.time = time;
	}




	public String getDes() {
		return des;
	}




	public void setDes(String des) {
		this.des = des;
	}


	


	public Lackofsleep(int id, Student student, String time, String des) {
		super();
		this.id = id;
		this.student = student;
		this.time = time;
		this.des = des;
	}




	public Lackofsleep() {}
	
}
