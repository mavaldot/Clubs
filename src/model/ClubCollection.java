package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

enum ClubOrder {
	NAME,
	ID,
	CREATIONDATE,
	TYPE,
	NUMBEROFPETOWNERS,
	NONE;
}

public class ClubCollection {
	
	private ArrayList<Club> clubs;
	private Club selectedClub;
	private PetOwner selectedPetOwner;
	ClubOrder order;
	
	public ClubCollection() {
		clubs = new ArrayList<Club>();
		selectedClub = null;
		selectedPetOwner = null;
		order = ClubOrder.NONE;
	}
	
	public void saveClubs() {
		
		File res = new File("res");
		if (!res.exists())
			res.mkdir();
		
		File f = new File("res\\clubs.txt");
		
		try {
			PrintWriter out = new PrintWriter(f);
			clubs.forEach(c -> out.println(c.getData()));
			out.close();
		}
		catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
	}
	
	public void loadClubs() {
		
		File res = new File("res");
		
		if (!res.exists())
			res.mkdir();
		
		File f = new File("res\\clubs.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String next = null;
			
			while ( (next=br.readLine()) != null) {
				
				String[] data = next.split(",");
				
				clubs.add(new Club(data[0], data[1], data[2], data[3]));
			}
			
			br.close();
		}
		catch (FileNotFoundException e) {
			//e.printStackTrace();
		} 
		catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	public void loadOwners() {
		
		clubs.forEach(c -> c.loadOwners());
		
		
	}
	
	public void addClub(String id, String name, String creationDate, String type) {
		clubs.add(new Club(id, name, creationDate, type));
		order = ClubOrder.NONE;
		saveClubs();
	}
	
	public boolean deleteClub(String id) {
		
		boolean success = false;
		
		for (int i = 0; i < clubs.size() && !success; i++) {
			if (clubs.get(i).getId().equals(id)) {
				clubs.remove(i);
				success = true;
				saveClubs();
			}
		}
		
		return success;
	}
	
	public boolean findClub(String id) {
		boolean exists = false;
		
		for (int i = 0; i < clubs.size() && !exists; i++) {
			if (clubs.get(i).getId().equals(id))
				exists = true;
		}
		
		return exists;
	}
	
	public boolean findOwner(String id) {
		boolean ret = selectedClub.findOwner(id);
		
		return ret;
	}
	
	public boolean findPet(String name) {
		boolean ret = selectedPetOwner.findPet(name);
		
		return ret;
	}
	
	public void addOwner(String names, String lastNames, String id, String birthDate, String prefPetType) {
		selectedClub.addPetOwner(names, lastNames, id, birthDate, prefPetType);
	}
	
	public void addPet(String name, String id, String birthDate, String gender, String type) {
		selectedPetOwner.addPet(name, id, birthDate, gender, type);
		selectedClub.saveOwners();
	}
	
	public boolean selectPetOwner(String id) {
		
		selectedPetOwner = selectedClub.selectPetOwner(id);
		
		boolean ret = (selectedPetOwner != null);
		
		return ret;
	}
	
	public boolean selectClub(String id) {
		
		boolean found = false;
		
		for (int i = 0; i < clubs.size() && !found; i++) {
			if (clubs.get(i).getId().equals(id)) {
				
				found = true; 
				selectedClub = clubs.get(i);
			}
		}
		
		return found;
		
	}
	
	public boolean deletePet(String name) {
		boolean ret = selectedPetOwner.deletePet(name);
		return ret;
	}
	
	public String showClubInfo()  {

		String msg = "CLUB SELECCIONADO:\n" + selectedClub.showInfo();
		
		return msg;
	}
	
	public String showPetOwnerInfo() {
		
		String msg = "DUENO DE MASCOTA SELECCIONADO:\n" + selectedPetOwner.showInfo();
		
		return msg;
	}
	
	public String showClubList(int clubOrder) {
		
		String ret = "";
		boolean error = false;
		
		switch (clubOrder) {
		
		case 1:
			
			orderByName();
			break;
			
		case 2:
			
			orderById();
			break;
		
		case 3:
			
			orderByCreationDate();
			break;
			
		case 4:
			
			orderByType();
			break;
		
		case 5:
			
			orderByNumberOfPetOwners();
			break;
			
		default:
			error = true;
			break;
		}
		
		if (!error) {
			ret += "\nLISTA DE CLUBES:\n\n";

			for(int i = 0; i < clubs.size(); i++) {
				
				ret += "Club #" + (i+1) + "\n";
				ret += clubs.get(i).showInfo();
				
			}
			
		} 
		else {
			ret = "Error. Ha digitado un numero erroneo.";
		}

		
		return ret;
		
	}


	//insertion sort
	public void orderByName() {
		
		if (order != ClubOrder.NAME) {
			
			for(int i = 1; i < clubs.size(); i++) {
				for(int j = i - 1; j >= 0 && clubs.get(j).compareTo(clubs.get(j+1)) > 0; j--) {
					Club tmp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, tmp);
				}
			}
			
			order = ClubOrder.NAME;
		}
		
	}
	
	//bubble sort
	public void orderById() {
		
		if (order != ClubOrder.ID) {
			
			for(int i = 0; i < clubs.size() - 1; i++) {
				for(int j = 0; j < clubs.size() - i - 1; j++) {
					if(clubs.get(j).compare(clubs.get(j), clubs.get(j+1)) > 0) {
						Club tmp = clubs.get(j);
						clubs.set(j, clubs.get(j+1));
						clubs.set(j+1, tmp);
					}
				}
				
			}
			
			order = ClubOrder.ID;		
		}
		
		
	}
	
	//selection sort
	public void orderByCreationDate() {
		
		if (order != ClubOrder.CREATIONDATE) {
			
			for (int i = 0; i < clubs.size() - 1; i++) {
				
				Club min = clubs.get(i);
				boolean change = false;
				int pos = 0;
				
				for (int j = i + 1; j < clubs.size(); j++) {	
					if (min.compareCreationDate(clubs.get(j)) > 0) {
						min = clubs.get(j);
						pos = j;
						change = true;
					}		
				}
				
				if (change) {
					Club tmp = clubs.get(i);
					clubs.set(i, min);
					clubs.set(pos, tmp);
				}
			}
			
			order = ClubOrder.CREATIONDATE;
		}
		
	}

	//insertion sort
	public void orderByType() {
		
		if (order != ClubOrder.TYPE) {
			
			for(int i = 1; i < clubs.size(); i++) {
				for(int j = i - 1; j >= 0 && clubs.get(j).compareType(clubs.get(j+1)) > 0; j--) {
					Club tmp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, tmp);
				}
			}
			
			order = ClubOrder.TYPE;
		}
	}

	//insertion sort
	public void orderByNumberOfPetOwners() {

		if (order != ClubOrder.NUMBEROFPETOWNERS) {
			
			for(int i = 1; i < clubs.size(); i++) {
				for(int j = i - 1; j >= 0 && clubs.get(j).compareSize(clubs.get(j+1)) > 0; j--) {
					Club tmp = clubs.get(j);
					clubs.set(j, clubs.get(j+1));
					clubs.set(j+1, tmp);
				}
			}
			
			order = ClubOrder.NUMBEROFPETOWNERS;
		}
		
	}

	public String showOwnerList(int ownerOrder) {
		
		String ret = selectedClub.showOwnerList(ownerOrder);
		
		return ret;
				
	}

	public boolean deleteOwner(String deleteId) {
		
		boolean ret = selectedClub.deleteOwner(deleteId); 
	
		return ret;
	}

	public String showPetList(int petOrder) {

		String ret = selectedPetOwner.showPetList(petOrder);
		
		return ret;
	}

	public String searchClubs(int criteria, String item) {
		
		String result = "";
		
		switch (criteria) {
		
		case 1:
			
			orderByName();
			
			long t1name = System.nanoTime();
			result += binaryClubSearchName(item, 0, clubs.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1name) + " nanosegundos\n"; 
			
			long t2name = System.nanoTime();
			clubSearchName(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2name) + " nanosegundos\n"; 
			
			break;
			
		case 2:
			
			orderById();
			
			long t1id = System.nanoTime();
			result += binaryClubSearchId(item, 0, clubs.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1id) + " nanosegundos\n";  
			
			long t2id = System.nanoTime();
			clubSearchId(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2id) + " nanosegundos\n"; 
			
			break;
			
		case 3:
			
			orderByCreationDate();
			
			long t1cd = System.nanoTime();
			result += binaryClubSearchCreationDate(item, 0, clubs.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1cd) + " nanosegundos\n";  
			
			long t2cd = System.nanoTime();
			 clubSearchCreationDate(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2cd) + " nanosegundos\n"; 

			break;
			
		case 4:
			
			orderByType();
			
			long t1t = System.nanoTime();
			result += binaryClubSearchType(item, 0, clubs.size()-1) + "\n";
			result += "Tiempo busqueda binaria: " + (System.nanoTime() - t1t) + " nanosegundos\n"; 
			
			long t2t = System.nanoTime();
			clubSearchType(item);
			result += "Tiempo busqueda secuencial: " + (System.nanoTime() - t2t) + " nanosegundos\n"; 
			break;
			
		default:
			
			result = "ERROR. Ha introducido un criterio de busqueda invalido.";
			break;
		
		}
		
		return result;
	}
	
	public String binaryClubSearchName(String name, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (clubs.get(mid).getName().equals(name)) {
				found = true;
				ret += clubs.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && clubs.get(mid).getName().equals(name)) {
					ret += clubs.get(mid).showInfo();
				}
				
				while (--i >= beg && clubs.get(i).getName().equals(name)) {
					ret += clubs.get(i).showInfo();
				}	
				
			}
			else if (clubs.get(mid).getName().compareTo(name) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un club con ese nombre\n";
		}
		
		return ret;
	}
	
	public String binaryClubSearchId(String id, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (clubs.get(mid).getId().equals(id)) {
				found = true;
				ret += clubs.get(mid).showInfo();
				
			}
			else if (clubs.get(mid).getId().compareTo(id) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un club con ese ID\n";
		}
		
		return ret;
	}
	
	public String binaryClubSearchCreationDate(String creationDate, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (clubs.get(mid).getCreationDate().equals(creationDate)) {
				found = true;
				ret += clubs.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && clubs.get(mid).getCreationDate().equals(creationDate)) {
					ret += clubs.get(mid).showInfo();
				}
				
				while (--i >= beg && clubs.get(i).getCreationDate().equals(creationDate)) {
					ret += clubs.get(i).showInfo();
				}
				
			}
			else if (clubs.get(mid).getCreationDate().compareTo(creationDate) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un club con esa fecha de creacion\n";
		}
		
		return ret;
	}
	
	public String binaryClubSearchType(String type, int beg, int end) {
		
		String ret = "";
		
		boolean found = false;
		
		while (!found && beg <= end) {
			
			int mid = (beg + end) / 2;
			
			if (clubs.get(mid).getType().equals(type)) {
				found = true;
				ret += clubs.get(mid).showInfo();
				
				int i = mid;
				
				while (++mid <= end && clubs.get(mid).getType().equals(type)) {
					ret += clubs.get(mid).showInfo();
				}
				
				while (--i >= beg && clubs.get(i).getType().equals(type)) {
					ret += clubs.get(i).showInfo();
				}
				
			}
			else if (clubs.get(mid).getType().compareTo(type) < 0)
				beg = mid + 1;
			else
				end = mid - 1;
		}
		
		if(!found) {
			ret = "No se encontro un club con ese tipo de mascota\n";
		}
		
		return ret;
	}
	
	public String clubSearchName(String name) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < clubs.size() && !found; i++) {
			if(clubs.get(i).getName().equals(name)) {
				ret += clubs.get(i).showInfo();
				found = true;
				
				while(++i <= clubs.size()-1 && clubs.get(i).getName().equals(name)) {
					ret += clubs.get(i).showInfo();
				}
				
			}	
		}
		
		if(!found) {
			ret = "No se encontro un club con ese nombre\n";
		}
		
		return ret;
		
	}
	
	public String clubSearchId(String id) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < clubs.size() && !found; i++) {
			if(clubs.get(i).getId().equals(id)) {
				ret += clubs.get(i).showInfo();
				found = true;
			}
		}
		
		if(!found) {
			ret = "No se encontro un club con ese ID\n";
		}
		
		return ret;
		
	}
	
	public String clubSearchCreationDate(String creationDate) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < clubs.size() && !found; i++) {
			if(clubs.get(i).getCreationDate().equals(creationDate)) {
				ret += clubs.get(i).showInfo();
				found = true;
				
				while(++i <= clubs.size()-1 && clubs.get(i).getCreationDate().equals(creationDate)) {
					ret += clubs.get(i).showInfo();
				}
			}
			
			
		}
		
		if(!found) {
			ret = "No se encontro un club con esa fecha de creacion\n";
		}
		
		return ret;
		
	}
	
	public String clubSearchType(String type) {
		
		String ret = "";
		
		boolean found = false;
		
		for(int i = 0; i < clubs.size() && !found; i++) {
			if(clubs.get(i).getType().equals(type)) {
				ret += clubs.get(i).showInfo();
				found = true;
				
				while(++i <= clubs.size()-1 && clubs.get(i).getType().equals(type)) {
					ret += clubs.get(i).showInfo();
				}
			}
		}
		
		if(!found) {
			ret = "No se encontro un club con ese tipo de mascota\n";
		}
		
		return ret;
		
	}

	public String searchOwners(int criteria, String item) {

		String ret = selectedClub.searchOwners(criteria, item);
		
		return ret;
	}
	
	public String searchPets(int criteria, String item) {
		
		String ret = selectedPetOwner.searchPets(criteria, item);
		
		return ret;
		
	}
}
