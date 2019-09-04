package model;

import java.io.Serializable;

public class Pet implements Serializable {

	private String name;
	private String id;
	private String date;
	private String gender;
	private String type;
	
	public Pet() {
		name = "FUUUCK YOUUUUUU";
	}
	
	public Pet(String name, String id, String date, String gender, String type) {
		this.name = name;
		this.id = id;
		this.date = date;
		this.gender = gender;
		this.type = type;
	}
	
	
	
	
}
