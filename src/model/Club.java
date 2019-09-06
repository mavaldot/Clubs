package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

enum OwnerOrder {
	NONE;
}

public class Club implements Comparable<Club> {

	private String id;
	private String name;
	private String creationDate;
	private String type;
	
	private ArrayList<PetOwner> petOwners;
	
	public Club(String id, String name, String creationDate, String type)  {
		
		this.id = id;
		this.name = name;
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

	public String showInfo()  {
		String info = "";
		
		info += "Nombre: " + name + "\n";
		info += "ID: " + id + "\n";
		info += "Fecha de creacion: " + creationDate + "\n";
		info += "Tipo: " + type + "\n";
		
		return info;
 	}
	
	public String getData() {
		String ret = "";
		ret += name + System.lineSeparator();
		ret += id + System.lineSeparator();
		ret += creationDate + System.lineSeparator();
		ret += type + System.lineSeparator();
		
		return ret;
	}
	
	public void addPetOwner(String names, String lastNames, String id, String birthDate, String prefPetType ) {
		petOwners.add(new PetOwner(names, lastNames, id, birthDate, prefPetType));
	}
	
	public void save() {
		
		String filename = "res\\clubs\\" + name + ".txt";
		
		File f = new File(filename);
		
		try {
			
			PrintWriter out = new PrintWriter(f);
			
			out.println(this.toString());
			
			for (PetOwner po : petOwners) {
				out.println(po.toString());
			}
			out.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void saveOwners() {
		
		String dir = "res\\" + name;
		
		File d = new File(dir);
		if (!d.exists()) {
			d.mkdirs();
		}
		
		for (PetOwner po : petOwners) {
			
			try {
				
				String filename = dir + "\\" + po.getID() + ".se";
				File f = new File(filename);
				
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				
				oos.writeObject(po);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void loadOwners() {
		
		String dir = "res\\" + name;
		
		File d = new File(dir);
		if (!d.exists()) {
			d.mkdirs();
		}
		
	}
	
	public boolean findOwner(String id) {
		
		boolean found = false;
		
		for (PetOwner po : petOwners) {
			if(po.getID().equals(id)) {
				found = true;
			}
		}
		
		return found;
		
	}

	@Override
	public int compareTo(Club c) {
		
		int ret = name.compareTo(c.getName());
		
		return ret;
	}

	public PetOwner selectPetOwner(String id) {
		
		PetOwner retPO = null;
		
		for(PetOwner po : petOwners) {
			if(po.getID().equals(id)) {
				retPO = po;
			}
		}
		
		return retPO;
		
	}
	
}
