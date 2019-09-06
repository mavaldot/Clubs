package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

enum PetOrder {
	NONE;
}

public class PetOwner implements Serializable {

	private String names;
	private String lastNames;
	private String id;
	private String birthDate;
	private String prefPetType;
	
	private ArrayList<Pet> pets;
	
	public PetOwner(String names, String lastNames, String id, String birthDate, String prefPetType) {
		this.names = names;
		this.lastNames = lastNames;
		this.id = id;
		this.birthDate = birthDate;
		this.prefPetType = prefPetType;
		
		
		pets = new ArrayList<Pet>();
	}
	
	public String getID() {
		return id;
	}
	
	public String showInfo() {
		
		String info = "";
		info += "Nombres: " + names + "\n";
		info += "Apellidos: " + lastNames + "\n";
		info += "ID: " + id + "\n";
		info += "Fecha de nacimiento: " + birthDate + "\n";
		info += "Mascota de preferencia: " + prefPetType + "\n";
		
		return info;
	}
	
	public void save(String dir) {
		
		File d = new File(dir);
		if(!d.exists()) {
			d.mkdirs();
		}
		
		String filename = dir + "\\" + id + ".se";
		File f = new File(filename);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addPet(String name, String id, String birthDate, String gender, String type) {
		pets.add(new Pet(name, id, birthDate, gender, type));
	}
	
	public boolean deletePet(String id) {
		
		boolean found = false;
		
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getId().equals(id)) {
				pets.remove(i);
				found = true;
			}
		}
		
		return found;
	}
	
	public void savePet(String dir) {
		
		File d = new File(dir);
		if(!d.exists()) {
			d.mkdirs();
		}
		
		String filename = dir + "\\" + id + ".se";
		File f = new File(filename);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
}
