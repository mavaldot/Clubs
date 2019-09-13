package model;

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
		info += "Numero de mascotas: " + pets.size() + "\n\n";
		
		return info;
	}

	public void addPet(String name, String id, String birthDate, String gender, String type) {
		pets.add(new Pet(name, id, birthDate, gender, type));
	}
	
	public void addPet(Pet p) {
		pets.add(p);
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

			for (int i = 0; i < pets.size(); i++) {
				ret += "Mascota #" + (i+1) + "\n";
				ret += pets.get(i).showInfo();
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
			
			for (int i = 0; i < pets.size() - 1; i++) {
				for (int j = 0; j < pets.size() - i - 1; j++) {
					if (pets.get(j).compareType(pets.get(j+1)) > 0) {
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
			
			for (int i = 0; i < pets.size() - 1; i++) {
				for (int j = 0; j < pets.size() - i - 1; j++) {
					if (pets.get(j).compareGender(pets.get(j+1)) > 0) {
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
			
			for (int i = 0; i < pets.size() - 1; i++) {
				for (int j = 0; j < pets.size() - i - 1; j++) {
					if (pets.get(j).compareBirthDate(pets.get(j+1)) > 0) {
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
			
			for (int i = 0; i < pets.size() - 1; i++) {
				for (int j = 0; j < pets.size() - i - 1; j++) {
					if (pets.get(j).compare(pets.get(j), pets.get(j+1)) > 0) {
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
			
			for (int i = 0; i < pets.size() - 1; i++) {
				for (int j = 0; j < pets.size() - i - 1; j++) {
					if (pets.get(j).compareTo(pets.get(j+1)) > 0) {
						Pet tmp = pets.get(j);
						pets.set(j, pets.get(j+1));
						pets.set(j+1, tmp);
					}
				}
				
			}
			
			order = PetOrder.NAME;		
		}
		
	}

	public boolean findPet(String name) {
		
		boolean found = false;
		
		for (Pet p : pets) {
			if(p.getName().equals(name)) {
				found = true;
			}
		}
		
		return found;
		
	}

	public String searchPets(int criteria, String item) {
		
		String result = "";
		
		switch (criteria) {
		
		case 1:
			
			orderByName();
			
			long t1name = System.nanoTime();
			result += petsBinarySearchName(item, 0, pets.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1name) + " nanosegundos\n"; 
			
			long t2name = System.nanoTime();
			petSearchName(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2name) + " nanosegundos\n"; 
			
			break;
			
		case 2:
			
			orderById();
			
			long t1lname = System.nanoTime();
			result += petsBinarySearchId(item, 0, pets.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1lname) + " nanosegundos\n";  
			
			long t2lname = System.nanoTime();
			petSearchId(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2lname) + " nanosegundos\n"; 
			
			break;
			
		case 3:
			
			orderByBirthDate();
			
			long t1id = System.nanoTime();
			result += petsBinarySearchBirthDate(item, 0, pets.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1id) + " nanosegundos\n";  
			
			long t2id = System.nanoTime();
			petSearchBirthDate(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2id) + " nanosegundos\n"; 

			break;
			
		case 4:
			
			orderByGender();
			
			long t1bd = System.nanoTime();
			result += petsBinarySearchGender(item, 0, pets.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1bd) + " nanosegundos\n"; 
			
			long t2bd = System.nanoTime();
			petSearchGender(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2bd) + " nanosegundos\n"; 
			break;
			
		case 5:
			
			orderByType();
			
			long t1t = System.nanoTime();
			result += petsBinarySearchType(item, 0, pets.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1t) + " nanosegundos\n"; 
			
			long t2t = System.nanoTime();
			petSearchType(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2t) + " nanosegundos\n"; 
			break;
			
		default:
			
			result = "ERROR. Ha introducido un criterio de busqueda invalido.";
			break;
		
		}
		
		return result;
	}
	
	public String petsBinarySearchName(String pname, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (pets.get(mid).getName().equals(pname)) {
				found = true;
				ret += pets.get(mid).showInfo();
				
			}
			else if (pets.get(mid).getName().compareTo(pname) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro una mascota con ese nombre\n";
		}
		
		return ret;
	}
	
	public String petsBinarySearchId(String pid, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (pets.get(mid).getId().equals(pid)) {
				found = true;
				ret += pets.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && pets.get(mid).getId().equals(pid)) {
					ret += pets.get(mid).showInfo();
				}
				
				while (--i >= beg && pets.get(i).getId().equals(pid)) {
					ret += pets.get(i).showInfo();
				}	
				
			}
			else if (pets.get(mid).getId().compareTo(pid) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro una mascota con ese id\n";
		}
		
		return ret;
	}
	
	public String petsBinarySearchBirthDate(String bd, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (pets.get(mid).getBirthDate().equals(bd)) {
				found = true;
				ret += pets.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && pets.get(mid).getBirthDate().equals(bd)) {
					ret += pets.get(mid).showInfo();
				}
				
				while (--i >= beg && pets.get(i).getBirthDate().equals(bd)) {
					ret += pets.get(i).showInfo();
				}	
				
			}
			else if (pets.get(mid).getBirthDate().compareTo(bd) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro una mascota con esa fecha de nacimiento\n";
		}
		
		return ret;
	}
	
	public String petsBinarySearchGender(String gender, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (pets.get(mid).getGender().equals(gender)) {
				found = true;
				ret += pets.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && pets.get(mid).getGender().equals(gender)) {
					ret += pets.get(mid).showInfo();
				}
				
				while (--i >= beg && pets.get(i).getGender().equals(gender)) {
					ret += pets.get(i).showInfo();
				}	
				
			}
			else if (pets.get(mid).getGender().compareTo(gender) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro una mascota con ese genero\n";
		}
		
		return ret;
	}
	
	public String petsBinarySearchType(String tp, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (pets.get(mid).getType().equals(tp)) {
				found = true;
				ret += pets.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && pets.get(mid).getType().equals(tp)) {
					ret += pets.get(mid).showInfo();
				}
				
				while (--i >= beg && pets.get(i).getType().equals(tp)) {
					ret += pets.get(i).showInfo();
				}	
				
			}
			else if (pets.get(mid).getType().compareTo(tp) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro una mascota de ese tipo\n";
		}
		
		return ret;
	}
	
	public String petSearchName(String pName) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < pets.size() && !found; i++) {
			if(pets.get(i).getName().equals(pName)) {
				ret += pets.get(i).showInfo();
				found = true;
			}	
		}
		
		if(!found) {
			ret = "No se encontro una mascota con ese nombre\n";
		}
		
		return ret;
	}

	public String petSearchId(String pid) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < pets.size() && !found; i++) {
			if(pets.get(i).getId().equals(pid)) {
				ret += pets.get(i).showInfo();
				found = true;
				
				while(++i <= pets.size()-1 && pets.get(i).getId().equals(pid)) {
					ret += pets.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro una mascota con ese id\n";
		}
		
		return ret;
	}
	
	public String petSearchBirthDate(String bd) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < pets.size() && !found; i++) {
			if(pets.get(i).getBirthDate().equals(bd)) {
				ret += pets.get(i).showInfo();
				found = true;
				
				while(++i <= pets.size()-1 && pets.get(i).getBirthDate().equals(bd)) {
					ret += pets.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro una mascota con esa fecha de nacimiento\n";
		}
		
		return ret;
	}
	
	public String petSearchGender(String gender) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < pets.size() && !found; i++) {
			if(pets.get(i).getGender().equals(gender)) {
				ret += pets.get(i).showInfo();
				found = true;
				
				while(++i <= pets.size()-1 && pets.get(i).getGender().equals(gender)) {
					ret += pets.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro una mascota con ese genero\n";
		}
		
		return ret;
	}
	
	public String petSearchType(String tp) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < pets.size() && !found; i++) {
			if(pets.get(i).getType().equals(tp)) {
				ret += pets.get(i).showInfo();
				found = true;
				
				while(++i <= pets.size()-1 && pets.get(i).getType().equals(tp)) {
					ret += pets.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro una mascota de ese tipo\n";
		}
		
		return ret;
	}
}
