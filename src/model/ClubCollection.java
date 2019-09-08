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
		else 
			System.out.println("aaaaa");
		
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
	
	public void addOwner(String names, String lastNames, String id, String birthDate, String prefPetType) {
		selectedClub.addPetOwner(names, lastNames, id, birthDate, prefPetType);
	}
	
	public void addPet(String name, String id, String birthDate, String gender, String type) {
		selectedPetOwner.addPet(name, id, birthDate, gender, type);
	}
	
	public boolean selectPetOwner(String id) {
		
		selectedPetOwner = selectedClub.selectPetOwner(id);
		
		boolean ret = (selectedClub != null);
		
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
	
	public boolean deletePet(String id) {
		boolean ret = selectedPetOwner.deletePet(id);
		return ret;
	}
	
	public String showClubInfo()  {

		String msg = "CLUB SELECCIONADO:\n" + selectedClub.showInfo();
		
		return msg;
	}
	
	public String showPetOwnerInfo() {
		
		String msg = "CLUB SELECCIONADO:\n" + selectedPetOwner.toString();
		
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
						System.out.println("cambio " + min.getData());
						change = true;
					}		
				}
				
				if (change) {
					Club tmp = min;
					clubs.set(i, min);
					clubs.set(pos, tmp);
				}
			}
			
			order = ClubOrder.CREATIONDATE;
		}
		
	}

	public void orderByType() {
		
		if (order != ClubOrder.TYPE) {
			
			
			order = ClubOrder.TYPE;
		}
	}


	
	public void orderByNumberOfPetOwners() {

		if (order != ClubOrder.NUMBEROFPETOWNERS) {
			
			for(;;) {
				break;
			}
			
			order = ClubOrder.NUMBEROFPETOWNERS;
		}
		
	}
	
}
