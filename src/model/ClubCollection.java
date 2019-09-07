package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClubCollection {
	
	private ArrayList<Club> clubs;
	private Club selectedClub;
	private PetOwner selectedPetOwner;
	
	public ClubCollection() {
		clubs = new ArrayList<Club>();
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
				
				System.out.println(data[0] + data[1] + data[2] + data[3]);
				
				clubs.add(new Club(data[0], data[1], data[2], data[3]));
			}
			
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
	
	
	
	public boolean load() {
		
		boolean success = false;
		File res = new File("res\\clubs");
		
		if (!res.exists()) {
			res.mkdirs();
		}
		else {

			File[] resFiles = res.listFiles();
			
			if (resFiles != null) {
				for (File child : resFiles) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(child));
						String id = br.readLine();
						String name = br.readLine();
						String creationDate = br.readLine();
						String type = br.readLine();
						
						addClub(id, name, creationDate, type);
						success = true;
						
					}
					catch ( IOException e) {

					}
				}
			}
			
		}
		
		for (Club c : clubs) {
			System.out.println(c.toString());
		}
		
		return success;
		
	}
	
	
	
	
}
