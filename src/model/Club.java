package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

enum PetOwnerOrder {
	NAMES,
	LASTNAMES,
	ID,
	BIRTHDATE,
	PREFPETTYPE,
	NUMBEROFPETS,
	NONE;
}

public class Club implements Comparable<Club>, Comparator<Club> {

	private String name;
	private String id;
	private String creationDate;
	private String type;
	PetOwnerOrder order;
	
	private ArrayList<PetOwner> petOwners;
	
	public Club(String name, String id, String creationDate, String type)  {
		
		this.name = name;
		this.id = id;
		this.creationDate = creationDate;
		this.type = type;
		
		petOwners = new ArrayList<PetOwner>();
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getCreationDate() {
		return creationDate;
	}

	public String getType() {
		return type;
	}
	public ArrayList<PetOwner> getPetOwners() {
		return petOwners;
	}


	public String showInfo()  {
		String info = "";
		
		info += "Nombre: " + name + "\n";
		info += "ID: " + id + "\n";
		info += "Fecha de creacion: " + creationDate + "\n";
		info += "Tipo de mascota: " + type + "\n";
		info += "Numero de miembros: " + petOwners.size() + "\n";
		
		return info;
 	}
	
	public String getData() {
		String ret = "";
		ret += name + ",";
		ret += id + ",";
		ret += creationDate + ",";
		ret += type;
		
		return ret;
	}
	
	public void addPetOwner(String names, String lastNames, String id, String birthDate, String prefPetType ) {
		petOwners.add(new PetOwner(names, lastNames, id, birthDate, prefPetType));
		saveOwners();
	}

	
	public void saveOwners() {
		
		File f = new File("res\\" + id + ".se");
		
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(petOwners);
			oos.close();
			
		}
		catch (IOException e) {
			// e.printStackTrace();
		}
		
	}
	
	public void loadOwners() {
		
		File f = new File("res\\" + id + ".se");
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			petOwners = (ArrayList<PetOwner>) ois.readObject();
	
			
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}
		
	}
	
	public boolean findOwner(String id) {
		
		boolean found = false;
		
		for (PetOwner po : petOwners) {
			if(po.getId().equals(id)) {
				found = true;
			}
		}
		
		return found;
		
	}

	public PetOwner selectPetOwner(String id) {
		
		PetOwner retPO = null;
		
		for (PetOwner po : petOwners) {
			
			if (po.getId().equals(id)) {
				retPO = po;
			}
		}
		
		return retPO;
		
	}

	@Override
	public int compareTo(Club c) {
		
		int ret = name.compareToIgnoreCase(c.getName());
		
		return ret;
	}
	
	@Override
	public int compare(Club o1, Club o2) {
		
		int ret = o1.getId().compareToIgnoreCase(o2.getId());
		
		return ret;
	}
	
	public int compareCreationDate(Club c) {
		
		int ret = creationDate.compareTo(c.getCreationDate());
		
		return ret;
		
	}
	
	public int compareType(Club c) {
		
		int ret = type.compareToIgnoreCase(c.getType());
		
		return ret;
	}
	
	public int compareSize(Club c) {
		
		int ret = petOwners.size() - c.getPetOwners().size();
		
		return ret;
		
	}

	public String showOwnerList(int ownerOrder) {
		
		String ret = "";
		boolean error = false;
		
		switch (ownerOrder) {
		
		case 1:		
			orderByName();
			break;
		
		case 2:		
			orderByLastName();
			break;
	
		case 3:		
			orderById();
			break;
			
		case 4:	
			orderByBirthDate();
			break;
			
		case 5:		
			orderByPrefPetType();
			break;
			
		case 6:		
			orderByNumberOfPets();
			break;
			
		default:
			error = true;
			break;
		}
		
		if (!error) {
			ret += "\nLISTA DE DUENOS DE MASCOTAS:\n\n";

			for(int i = 0; i < petOwners.size(); i++) {
				
				ret += "Dueno #" + (i+1) + "\n";
				ret += petOwners.get(i).showInfo();
				ret += "\n";
				
			}
			
		} 
		else {
			ret = "Error. Ha digitado un numero erroneo.";
		}

		return ret;
		
	}
	
	//insertion sort
	public void orderByName() {
		
		if (order != PetOwnerOrder.NAMES) {
			
			for(int i = 1; i < petOwners.size(); i++) {
				for(int j = i - 1; j >= 0 && petOwners.get(j).compareTo(petOwners.get(j+1)) > 0; j--) {
					PetOwner tmp = petOwners.get(j);
					petOwners.set(j, petOwners.get(j+1));
					petOwners.set(j+1, tmp);
				}
			}
			
			order = PetOwnerOrder.NAMES;
		}
		
	}
	
	//insertion sort
	public void orderByLastName() {
		
		if (order != PetOwnerOrder.LASTNAMES) {
			
			for(int i = 1; i < petOwners.size(); i++) {
				for(int j = i - 1; j >= 0 && petOwners.get(j).compareLastNames(petOwners.get(j+1)) > 0; j--) {
					PetOwner tmp = petOwners.get(j);
					petOwners.set(j, petOwners.get(j+1));
					petOwners.set(j+1, tmp);
				}
			}
			
			order = PetOwnerOrder.LASTNAMES;
		}
		
	}
	
	//bubble sort
	public void orderById() {
		
		if (order != PetOwnerOrder.ID) {
			
			for(int i = 0; i < petOwners.size() - 1; i++) {
				for(int j = 0; j < petOwners.size() - i - 1; j++) {
					if(petOwners.get(j).compare(petOwners.get(j), petOwners.get(j+1)) > 0) {
						PetOwner tmp = petOwners.get(j);
						petOwners.set(j, petOwners.get(j+1));
						petOwners.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOwnerOrder.ID;		
		}
		
	}
	
	//selection sort
	public void orderByBirthDate() {
		
		if (order != PetOwnerOrder.BIRTHDATE) {
			
			for (int i = 0; i < petOwners.size() - 1; i++) {
				
				PetOwner min = petOwners.get(i);
				boolean change = false;
				int pos = 0;
				
				for (int j = i + 1; j < petOwners.size(); j++) {	
					if (min.compareBirthDate(petOwners.get(j)) > 0) {
						min = petOwners.get(j);
						pos = j;
						change = true;
					}		
				}
				
				if (change) {
					PetOwner tmp = petOwners.get(i);
					petOwners.set(i, min);
					petOwners.set(pos, tmp);
				}
			}
			
			order = PetOwnerOrder.BIRTHDATE;
			
			System.out.println(order);
		}
		
	}

	//insertion sort
	public void orderByPrefPetType() {
		
		if (order != PetOwnerOrder.PREFPETTYPE) {
			
			for(int i = 1; i < petOwners.size(); i++) {
				for(int j = i - 1; j >= 0 && petOwners.get(j).comparePrefPetType(petOwners.get(j+1)) > 0; j--) {
					PetOwner tmp = petOwners.get(j);
					petOwners.set(j, petOwners.get(j+1));
					petOwners.set(j+1, tmp);
				}
			}
			
			order = PetOwnerOrder.PREFPETTYPE;
		}
	}

	//insertion sort
	public void orderByNumberOfPets() {

		if (order != PetOwnerOrder.NUMBEROFPETS) {
			
			for(int i = 1; i < petOwners.size(); i++) {
				for(int j = i - 1; j >= 0 && petOwners.get(j).compareNumberOfPets(petOwners.get(j+1)) > 0; j--) {
					PetOwner tmp = petOwners.get(j);
					petOwners.set(j, petOwners.get(j+1));
					petOwners.set(j+1, tmp);
				}
			}
			
			order = PetOwnerOrder.NUMBEROFPETS;
		}
		
	}

	public boolean deleteOwner(String deleteId) {
		
		boolean found = false;
		
		for (int i = 0; i < petOwners.size(); i++) {
			if (petOwners.get(i).getId().equals(id)) {
				petOwners.remove(i);
				found = true;
			}
		}
		
		return found;
	}
	
	public String searchPetOwner(int criteria, String item) {
		
		String result = "";
		
		switch (criteria) {
		
		
		
		}
		
		return result;
		
	}
	
}
