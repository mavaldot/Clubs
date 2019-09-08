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
		
		int ret = o1.getId().compareToIgnoreCase(o2.getId());
		return ret;
	}



	@Override
	public int compareTo(Pet p) {

		int ret = name.compareToIgnoreCase(p.getName());
		return ret;
	}
	
	public int compareBirthDate(Pet p) {
		
		int ret = birthDate.compareToIgnoreCase(p.getBirthDate());
		return ret;
	}
	
	public int compareGender(Pet p) {
		int ret = gender.compareToIgnoreCase(p.getGender());
		return ret;
	}
	
	public int compareType(Pet p) {
		int ret = type.compareToIgnoreCase(p.getType());
		return ret;
	}
	
	
}
