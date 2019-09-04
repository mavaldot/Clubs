package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PetOwner implements Serializable {

	private String names;
	private String lastNames;
	private String id;
	private String birthDate;
	private String prefPetType;
	
	private ArrayList<Pet> pets;
	
	public PetOwner() {
		names = "Johan";
	}
	
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
	
	public void save(String dir) {
		
		File d = new File(dir);
		if(!d.exists()) {
			d.mkdirs();
		}
		
		String filename = dir + "\\" + id + ".se";
		File f = new File(filename);
		Pet pito = new Pet();;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			
			oos.writeObject(pito);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void savePet(String dir) {
		
		File d = new File(dir);
		if(!d.exists()) {
			d.mkdirs();
		}
		
		String filename = dir + "\\" + id + ".se";
		File f = new File(filename);
		Pet pito = new Pet();;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			
			oos.writeObject(pito);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
}
