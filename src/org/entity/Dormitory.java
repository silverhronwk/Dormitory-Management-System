package org.entity;

//宿舍
public class Dormitory {

	private int id;
	private Building building;//楼宇
	private String name;//宿舍号
	private int type;//宿舍类型
	private int number;//人数
	private String contact;//联系电话
	
	
	private int building_ID;
	
	public Dormitory(int building_ID,String name,String contact) {
		super();
		this.building_ID = building_ID;
		this.name = name;
		this.contact = contact;
	}
	
	
	
	
	
	public Dormitory(int id, Building building, String name, int type, int number, String contact) {
		super();
		this.id = id;
		this.building = building;
		this.name = name;
		this.type = type;
		this.number = number;
		this.contact = contact;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public Building getBuilding() {
		return building;
	}





	public void setBuilding(Building building) {
		this.building = building;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getType() {
		return type;
	}





	public void setType(int type) {
		this.type = type;
	}





	public int getNumber() {
		return number;
	}





	public void setNumber(int number) {
		this.number = number;
	}





	public String getContact() {
		return contact;
	}





	public void setContact(String contact) {
		this.contact = contact;
	}





	public Dormitory() {}
	
	
}
