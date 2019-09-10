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
		info += "Numero de miembros: " + petOwners.size() + "\n\n";
		
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
	
	public String searchOwners(int criteria, String item) {
		
		String result = "";
		
		switch (criteria) {
		
		case 1:
			
			orderByName();
			
			long t1name = System.nanoTime();
			result += petOwnersBinarySearchName(item, 0, petOwners.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1name) + " nanosegundos\n"; 
			
			long t2name = System.nanoTime();
			petOwnersSearchName(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2name) + " nanosegundos\n"; 
			
			break;
			
		case 2:
			
			orderByLastName();
			
			long t1lname = System.nanoTime();
			result += petOwnersBinarySearchLastName(item, 0, petOwners.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1lname) + " nanosegundos\n";  
			
			long t2lname = System.nanoTime();
			petOwnersSearchLastName(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2lname) + " nanosegundos\n"; 
			
			break;
			
		case 3:
			
			orderById();
			
			long t1id = System.nanoTime();
			result += petOwnersBinarySearchId(item, 0, petOwners.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1id) + " nanosegundos\n";  
			
			long t2id = System.nanoTime();
			petOwnersSearchId(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2id) + " nanosegundos\n"; 

			break;
			
		case 4:
			
			orderByBirthDate();
			
			long t1bd = System.nanoTime();
			result += petOwnersBinarySearchBirthDate(item, 0, petOwners.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1bd) + " nanosegundos\n"; 
			
			long t2bd = System.nanoTime();
			petOwnersSearchBirthDate(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2bd) + " nanosegundos\n"; 
			break;
			
		case 5:
			
			orderByPrefPetType();
			
			long t1ppt = System.nanoTime();
			result += petOwnersBinarySearchPrefPetType(item, 0, petOwners.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1ppt) + " nanosegundos\n"; 
			
			long t2ppt = System.nanoTime();
			petOwnersSearchPrefPetType(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2ppt) + " nanosegundos\n"; 
			break;
			
		default:
			
			result = "ERROR. Ha introducido un criterio de busqueda invalido.";
			break;
		
		}
		
		return result;
	}
	
	public String petOwnersBinarySearchName(String names, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (petOwners.get(mid).getNames().equals(names)) {
				found = true;
				ret += petOwners.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && petOwners.get(mid).getNames().equals(names)) {
					ret += petOwners.get(mid).showInfo();
				}
				
				while (--i >= beg && petOwners.get(i).getNames().equals(names)) {
					ret += petOwners.get(i).showInfo();
				}	
				
			}
			else if (petOwners.get(mid).getNames().compareTo(names) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese nombre\n";
		}
		
		return ret;
	}
	
	public String petOwnersBinarySearchLastName(String lastnames, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (petOwners.get(mid).getLastNames().equals(lastnames)) {
				found = true;
				ret += petOwners.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && petOwners.get(mid).getLastNames().equals(lastnames)) {
					ret += petOwners.get(mid).showInfo();
				}
				
				while (--i >= beg && petOwners.get(i).getLastNames().equals(lastnames)) {
					ret += petOwners.get(i).showInfo();
				}	
				
			}
			else if (petOwners.get(mid).getLastNames().compareTo(lastnames) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese apellido\n";
		}
		
		return ret;
	}
	
	public String petOwnersBinarySearchId(String poid, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (petOwners.get(mid).getId().equals(poid)) {
				found = true;
				ret += petOwners.get(mid).showInfo();
				
				int i = mid;
				
			}
			else if (petOwners.get(mid).getId().compareTo(poid) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese id\n";
		}
		
		return ret;
	}
	
	public String petOwnersBinarySearchBirthDate(String birthDate, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (petOwners.get(mid).getBirthDate().equals(birthDate)) {
				found = true;
				ret += petOwners.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && petOwners.get(mid).getBirthDate().equals(birthDate)) {
					ret += petOwners.get(mid).showInfo();
				}
				
				while (--i >= beg && petOwners.get(i).getBirthDate().equals(birthDate)) {
					ret += petOwners.get(i).showInfo();
				}	
				
			}
			else if (petOwners.get(mid).getBirthDate().compareTo(birthDate) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un dueno con esa fecha de nacimiento\n";
		}
		
		return ret;
	}
	
	public String petOwnersBinarySearchPrefPetType(String prefPetType, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (petOwners.get(mid).getPrefPetType().equals(prefPetType)) {
				found = true;
				ret += petOwners.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && petOwners.get(mid).getPrefPetType().equals(prefPetType)) {
					ret += petOwners.get(mid).showInfo();
				}
				
				while (--i >= beg && petOwners.get(i).getPrefPetType().equals(prefPetType)) {
					ret += petOwners.get(i).showInfo();
				}	
				
			}
			else if (petOwners.get(mid).getPrefPetType().compareTo(prefPetType) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese tipo de mascota preferido\n";
		}
		
		return ret;
	}
	
	
	
	public String petOwnersSearchName(String poName) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < petOwners.size() && !found; i++) {
			if(petOwners.get(i).getNames().equals(poName)) {
				ret += petOwners.get(i).showInfo();
				found = true;
				
				while(++i <= petOwners.size()-1 && petOwners.get(i).getNames().equals(poName)) {
					ret += petOwners.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese nombre\n";
		}
		
		return ret;
	}
	
	public String petOwnersSearchLastName(String poLastName) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < petOwners.size() && !found; i++) {
			if(petOwners.get(i).getLastNames().equals(poLastName)) {
				ret += petOwners.get(i).showInfo();
				found = true;
				
				while(++i <= petOwners.size()-1 && petOwners.get(i).getLastNames().equals(poLastName)) {
					ret += petOwners.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese apellido\n";
		}
		
		return ret;
	}

	public String petOwnersSearchId(String poid) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < petOwners.size() && !found; i++) {
			if(petOwners.get(i).getId().equals(poid)) {
				ret += petOwners.get(i).showInfo();
				found = true;
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese id\n";
		}
		
		return ret;
	}
	
	public String petOwnersSearchBirthDate(String bd) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < petOwners.size() && !found; i++) {
			if(petOwners.get(i).getBirthDate().equals(bd)) {
				ret += petOwners.get(i).showInfo();
				found = true;
				
				while(++i <= petOwners.size()-1 && petOwners.get(i).getBirthDate().equals(bd)) {
					ret += petOwners.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro un dueno con esa fecha de nacimiento\n";
		}
		
		return ret;
	}
	
	public String petOwnersSearchPrefPetType(String ppt) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < petOwners.size() && !found; i++) {
			if(petOwners.get(i).getPrefPetType().equals(ppt)) {
				ret += petOwners.get(i).showInfo();
				found = true;
				
				while(++i <= petOwners.size()-1 && petOwners.get(i).getPrefPetType().equals(ppt)) {
					ret += petOwners.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro un dueno con ese tipo de mascota preferido\n";
		}
		
		return ret;
	}
}
