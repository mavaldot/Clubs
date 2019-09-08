package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

enum PetOwnerOrder {
	NONE;
}

public class Club implements Comparable<Club>, Comparator<Club> {

	private String name;
	private String id;
	private String creationDate;
	private String type;
	
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
		info += "Tipo: " + type + "\n";
		
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
			
			petOwners.forEach( po -> System.out.println(po.toString()) );
			
			
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
		
		for(PetOwner po : petOwners) {
			if(po.getId().equals(id)) {
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
		
		int ret = creationDate.compareToIgnoreCase(c.getCreationDate());
		
		return ret;
		
	}
	
	public int compareType(Club c) {
		
		int ret = type.compareToIgnoreCase(c.getCreationDate());
		
		return ret;
	}
}
