package org.entity;

//楼宇
public class Building {

	private int id;
	private String name;//名称
	private String introduction;//简介
	private BuildingAdministrator buildingAdministrator;//管理员
	private String sex;//男女
	
	
	
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



	public String getIntroduction() {
		return introduction;
	}



	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}



	public BuildingAdministrator getBuildingAdministrator() {
		return buildingAdministrator;
	}



	public void setBuildingAdministrator(BuildingAdministrator buildingAdministrator) {
		this.buildingAdministrator = buildingAdministrator;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}


	

	public Building(int id, String name, String introduction, BuildingAdministrator buildingAdministrator, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.introduction = introduction;
		this.buildingAdministrator = buildingAdministrator;
		this.sex = sex;
	}
	
	public Building(int id) {
		super();
		this.id = id;
	}



	public Building() {}
	
}
