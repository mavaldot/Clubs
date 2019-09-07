package model;

import java.io.Serializable;
import java.util.Comparator;

public class Pet implements Serializable, Comparable<Pet>, Comparator<Pet>{

	private String name;
	private String id;
	private String birthDate;
	private String gender;
	private String type;
	
	public Pet(String name, String id, String birthDate, String gender, String type) {
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.gender = gender;
		this.type = type;
	}
	
	
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public String getGender() {
		return gender;
	}
	
	public String getType() {
		return type;
	}

	public String showInfo() {
		
		String info = "";
		info += "Nombre: " + name + "\n";
		info += "ID: " + id + "\n";
		info += "Fecha de nacimiento: " + birthDate + "\n";
		info += "Genero: " + gender + "\n";
		info += "Tipo: " + type + "\n";
	
		return info;
		
	}



	@Override
	public int compare(Pet o1, Pet o2) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int compareTo(Pet o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
