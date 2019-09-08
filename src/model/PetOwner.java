package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

enum PetOrder {
	NAME,
	ID,
	BIRTHDATE,
	GENDER,
	TYPE,
	NONE;
}

public class PetOwner implements Serializable, Comparable<PetOwner>, Comparator<PetOwner> {

	private String names;
	private String lastNames;
	private String id;
	private String birthDate;
	private String prefPetType;
	PetOrder order;
	
	private ArrayList<Pet> pets;
	
	public PetOwner(String names, String lastNames, String id, String birthDate, String prefPetType) {
		this.names = names;
		this.lastNames = lastNames;
		this.id = id;
		this.birthDate = birthDate;
		this.prefPetType = prefPetType;
		
		
		pets = new ArrayList<Pet>();
	}
	
	
	public String getNames() {
		return names;
	}

	public String getLastNames() {
		return lastNames;
	}
	
	public String getId() {
		return id;
	}

	public String getBirthDate() {
		return birthDate;
	}
	
	public String getPrefPetType() {
		return prefPetType;
	}

	public ArrayList<Pet> getPets() {
		return pets;
	}
	
	public String showInfo() {
		
		String info = "";
		info += "Nombres: " + names + "\n";
		info += "Apellidos: " + lastNames + "\n";
		info += "ID: " + id + "\n";
		info += "Fecha de nacimiento: " + birthDate + "\n";
		info += "Mascota de preferencia: " + prefPetType + "\n";
		info += "Numero de mascotas: " + pets.size() + "\n";
		
		return info;
	}

	public void addPet(String name, String id, String birthDate, String gender, String type) {
		pets.add(new Pet(name, id, birthDate, gender, type));
	}
	
	public boolean deletePet(String name) {
		
		boolean found = false;
		
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getName().equals(name)) {
				pets.remove(i);
				found = true;
			}
		}
		
		return found;
	}

	@Override
	public int compareTo(PetOwner po) {
		
		int ret = names.compareToIgnoreCase(po.getNames());
		
		return ret;
	}
	
	public int compareLastNames(PetOwner po) {
		
		int ret = lastNames.compareToIgnoreCase(po.getLastNames());
		return ret;
	}


	@Override
	public int compare(PetOwner o1, PetOwner o2) {
		
		int ret = o1.getId().compareToIgnoreCase(o2.getId());
		return ret;
	}
	
	public int compareBirthDate(PetOwner po) {
		
		int ret = birthDate.compareToIgnoreCase(po.getBirthDate());
		return ret;
	}
	
	public int comparePrefPetType(PetOwner po) {
	
		int ret = prefPetType.compareToIgnoreCase(po.getPrefPetType());
		return ret;
	}

	public int compareNumberOfPets(PetOwner po) {
		
		int ret = pets.size() - po.getPets().size();
		return ret;
		
	}


	public String showPetList(int petOrder) {
		
		String ret = "";
		boolean error = false;
		
		switch (petOrder) {
		
		case 1:
			
			orderByName();
			break;
		
		case 2:
			
			orderById();
			break;
			
		case 3:
			
			orderByBirthDate();
			break;
			
		case 4:
			
			orderByGender();
			break;
			
		case 5:
			
			orderByType();
			break;
			
		default:
			error = true;
			break;
		}
		
		if (!error) {
			ret += "\nLISTA DE MASCOTAS:\n\n";

			for(int i = 0; i < pets.size(); i++) {
				
				ret += "Mascota #" + (i+1) + "\n";
				ret += pets.get(i).showInfo();
				ret += "\n";
				
			}
			
		} 
		else {
			ret = "Error. Ha digitado un numero erroneo.";
		}

		return ret;
	}

	//insertion sort
	public void orderByType() {
		if (order != PetOrder.TYPE) {
			
			for(int i = 0; i < pets.size() - 1; i++) {
				for(int j = 0; j < pets.size() - i - 1; j++) {
					if(pets.get(j).compareType(pets.get(j+1)) > 0) {
						Pet tmp = pets.get(j);
						pets.set(j, pets.get(j+1));
						pets.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOrder.TYPE;		
		}
		
	}

	//insertion sort
	public void orderByGender() {
		if (order != PetOrder.GENDER) {
			
			for(int i = 0; i < pets.size() - 1; i++) {
				for(int j = 0; j < pets.size() - i - 1; j++) {
					if(pets.get(j).compareGender(pets.get(j+1)) > 0) {
						Pet tmp = pets.get(j);
						pets.set(j, pets.get(j+1));
						pets.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOrder.GENDER;		
		}
		
	}

	//insertion sort
	public void orderByBirthDate() {
		if (order != PetOrder.BIRTHDATE) {
			
			for(int i = 0; i < pets.size() - 1; i++) {
				for(int j = 0; j < pets.size() - i - 1; j++) {
					if(pets.get(j).compareBirthDate(pets.get(j+1)) > 0) {
						Pet tmp = pets.get(j);
						pets.set(j, pets.get(j+1));
						pets.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOrder.BIRTHDATE;		
		}
		
	}

	//insertion sort
	public void orderById() {
		
		if (order != PetOrder.ID) {
			
			for(int i = 0; i < pets.size() - 1; i++) {
				for(int j = 0; j < pets.size() - i - 1; j++) {
					if(pets.get(j).compare(pets.get(j), pets.get(j+1)) > 0) {
						Pet tmp = pets.get(j);
						pets.set(j, pets.get(j+1));
						pets.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOrder.ID;		
		}
	}

	//insertion sort
	public void orderByName() {
		if (order != PetOrder.NAME) {
			
			for(int i = 0; i < pets.size() - 1; i++) {
				for(int j = 0; j < pets.size() - i - 1; j++) {
					if(pets.get(j).compareTo(pets.get(j+1)) > 0) {
						Pet tmp = pets.get(j);
						pets.set(j, pets.get(j+1));
						pets.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOrder.NAME;		
		}
		
	}

	public String searchPets(int criteria, String item) {
		
		String result = "";
		
		switch (criteria) {
		
		}
		
		return result;
	}
		
	
}
