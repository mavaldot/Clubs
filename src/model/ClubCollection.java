package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClubCollection {

	private String name;
	
	private ArrayList<Club> clubs;
	private Club selectedClub;
	private PetOwner selectedPetOwner;
	
	public ClubCollection() {
		name = "Hello";
		clubs = new ArrayList<Club>();
	}
	
	public void saveClubs() {
		
		File res = new File("res");
		if(!res.exists())
			res.mkdir();
		
		File f = new File("res\\clubs.txt");
		
		try {
			PrintWriter out = new PrintWriter(f);
			for(Club c : clubs) {
				out.println(c.getData());
			}
			out.close();
		}
		catch (FileNotFoundException e) {
		}
	}
	
	public void loadClubs() {
		
		File res = new File("res");
		
		if(!res.exists())
			res.mkdir();
		
		File f = new File("res\\clubs.txt");
		
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String next = null;
			
			
			while ( (next=br.readLine()) != null) {
				String name = next;
				String id = br.readLine();
				String creationDate = br.readLine();
				String type = br.readLine();
				br.readLine();
				
				System.out.println(name + id + creationDate + type);
				
				clubs.add(new Club(name, id, creationDate, type));
			}
			
		}
		catch (FileNotFoundException e) {
		} 
		catch (IOException e) {
		}
		
		
	}
	
	public void save() {
		File f = new File("ClubCollection.txt");
		
		try {
			PrintWriter out = new PrintWriter(f);
			out.print(name);
			out.close();
		}
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void addClub(String id, String name, String creationDate, String type) {
		clubs.add(new Club(id, name, creationDate, type));
	}
	
	public boolean deleteClub(String id) {
		
		boolean success = false;
		
		for(int i = 0; i < clubs.size() && !success; i++) {
			if(clubs.get(i).getId().equals(id)) {
				clubs.remove(i);
				success = true;
			}
		}
		
		return success;
	}
	
	public boolean findClub(String id) {
		boolean exists = false;
		
		for (int i = 0; i < clubs.size() && !exists; i++) {
			if (clubs.get(i).getId().equals(id)) {
				clubs.remove(i);
				exists = true;
			}
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
					catch ( Exception e) {

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
