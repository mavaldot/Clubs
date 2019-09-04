package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClubCollection {

	private String name;
	
	private ArrayList<Club> clubs;
	
	public ClubCollection() {
		name = "Hello";
		clubs = new ArrayList<Club>();
	}
	
	public void saveClubs() {
		
	}
	
	public void save() {
		File f = new File("ClubCollection.txt");
		
		try {
			PrintWriter out = new PrintWriter(f);
			out.print(name);
			out.close();
		} catch (FileNotFoundException e) {
			
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
		
		for(int i = 0; i < clubs.size() && !exists; i++) {
			if(clubs.get(i).getId().equals(id)) {
				clubs.remove(i);
				exists = true;
			}
		}
		
		return exists;
	}
	
	public String showInfo(String id) throws ClubNotFoundException {
		
		boolean found = false;
		String msg = "";
		
		for(int i = 0; i < clubs.size() && !found; i++) {
			if(clubs.get(i).getId().equals(id)) {
				
				found = true; 
				msg += clubs.get(i).showInfo();
			}
		}
		
		if(!found)
			throw new ClubNotFoundException("El club no se pudo encontrar");
		
		return msg;
	}
	
	public boolean load() {
		
		boolean success = false;
		File res = new File("res\\clubs");
		
		if(!res.exists()) {
			res.mkdirs();
		} else {

			File[] resFiles = res.listFiles();
			
			if(resFiles != null) {
				for(File child : resFiles) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(child));
						String id = br.readLine();
						String name = br.readLine();
						String creationDate = br.readLine();
						String type = br.readLine();
						
						addClub(id, name, creationDate, type);
						success = true;
						
					} catch ( Exception e) {

					}
				}
			}
		}
		
		for(Club c : clubs) {
			System.out.println(c.toString());
		}
		
		return success;
		
	}
	
	
	
}
