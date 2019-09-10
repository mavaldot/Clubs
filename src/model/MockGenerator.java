package model;

import java.util.ArrayList;
import java.util.Random;

public class MockGenerator {

	private Random rand;
	
	public final String CAPITAL_LETTERS = "ABCDEFGIJKLMNOPQRSTUVWXYZ";
	public final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
	public final String NUMBERS = "1234567890";
	public final String[] ANIMALS = {"Gatos", "Perros", "Hamsters", "Pajaros", "Serpientes",
	"Ratones", "Aranas", "Cerdos", "Conejos", "Tortugas", "Peces", "Pollos"};
	public final String[] ANIMAL = {"Gato", "Perro", "Hamster", "Pajaro", "Serpiente",
	"Ratone", "Arana", "Cerdo", "Conejo", "Tortuga", "Pez", "Pollo"};
	
	public MockGenerator() {
		rand = new Random();
	}
	
	public void generateMockClubCollection(ClubCollection cc) {
		
		ArrayList<Club> clubs = cc.getClubs();
		
		for (Club c : clubs) {
			
			for (int i = 0; i < 10000; i++) {
				String name = createRandomName();
				String lastName = createRandomName();
				String oid = createRandomId();
				String birthDate = createRandomDate();
				String prefPetType = ANIMALS[rand.nextInt(ANIMALS.length)];
				
				PetOwner po = new PetOwner(name, lastName, oid, birthDate, prefPetType);
				
				int petNum = rand.nextInt(2) + 1;
				
				while (petNum-- > 0) {
					String pname = createRandomName();
					String pid = createRandomId();
					String bd = createRandomDate();
					String gndr = rand.nextInt(2) == 1 ? "M" : "F";
					String typ = ANIMAL[rand.nextInt(ANIMAL.length)];
					
					po.addPet(pname, pid, bd, gndr, typ);
				}
				
				c.addPetOwner(po);
				
				System.out.println("Generated" + i);
				
				
			}
			
			c.saveOwners();
			
		}
		
		cc.saveClubs();
		
	}
	
	public String createRandomName() {
		
		StringBuilder sb = new StringBuilder();
		
		int length = rand.nextInt(5)+2;
		
		sb.append(CAPITAL_LETTERS.charAt(rand.nextInt(CAPITAL_LETTERS.length())));
		
		while (length-- > 0) {
			
			sb.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
			
		}
		
		String nam = sb.toString();
		
		return nam;
		
	}
	
	public String createRandomId() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 9; i++) {
			sb.append(NUMBERS.charAt(rand.nextInt(NUMBERS.length())));
		}
		
		String num = sb.toString();
		
		return num;
		
	}
	
	public String createRandomDate() {
		
		int year = rand.nextInt(95) + 1920;
		
		int month = rand.nextInt(12) + 1;
				
		int day = rand.nextInt(31) + 1;
	
		String date = "";
		
		if (month < 10)
			date = year + "/" + "0" + month;
		else 
			date = year + "/" + month;
		
		if (day < 10)
			date += "/" + "0" + day;
		else
			date += "/" + day;
		
		return date;
		
	}
}
