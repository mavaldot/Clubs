package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Club;
import model.ClubCollection;
import model.ClubNotFoundException;
import model.PetOwner;

public class Menu {

	private ClubCollection clubCollection;
	private Scanner s;
	
	public Menu() {
		clubCollection = new ClubCollection();
		s = new Scanner(System.in);
	}
	
	public void mainMenu() {
		
		boolean running = true;
		
		clubCollection.loadClubs();
		clubCollection.loadOwners();
		
		while (running) {
			
			System.out.println("\nBienvenido al menu principal! Elija una opcion:");

			System.out.println("1. Ver la lista de los clubes");
			System.out.println("2. Entrar a un club");
			System.out.println("3. Agregar un club");
			System.out.println("4. Eliminar un club");
			System.out.println("5. Salir\n");
			
			int choice = askInt();
			
			switch (choice) {
			
			case 1:

				System.out.println("Por favor elija el criterio de ordenamiento:");
				System.out.println("1. Ordenar por nombre");
				System.out.println("2. Ordenar por ID");
				System.out.println("3. Ordenar por fecha de creacion");
				System.out.println("4. Ordenar por tipo");
				System.out.println("5. No ordenar");
				
				break;
				
			case 2:
				
				String id = askString("Por favor digite el id del club");
				
				if (clubCollection.selectClub(id)) 
					clubMenu();
				else
					System.out.println("ERROR. No se encontro un club con ese ID");
				
				break;
				
			case 3:
				
				String clubID = askString("Por favor digite el ID del club");
				String clubName = askString("Por favor digite el nombre del club");
				String clubCreationDate = askString("Por favor digite la fecha que se creo el club");
				String clubType = askString("Por favor digite el tipo de club");
				
				if (!clubCollection.findClub(clubID)) {
					clubCollection.addClub(clubID, clubName, clubCreationDate, clubType);
					System.out.println("Su club ha sido agregado!");
				} 
				else {
					System.out.println("ERROR. Ya existe un club con ese ID");
				}
				
				clubCollection.saveClubs();
				
				
				break;
				
			case 4:
					
				String delID = askString("Por favor digite el ID del club que desea eliminar");
				if (clubCollection.deleteClub(delID))
					System.out.println("El club ha sido eliminado exitosamente");
				else 
					System.out.println("No se encontro un club con ese ID");
				break;
				
			case 5:
				
				running = false;
				clubCollection.saveClubs();
				System.out.println("Hasta luego!");
				break;
				
			case 6:
				
				break;
			
			default:
				
				System.out.println("ERROR. Por favor digite un valor valido");	
				break;
			}
			
		}

		
//		Club c = new Club("JC1234", "JOHAN CLUB", "1 de enero", "Perros");
//		c.save();
//		
//		c.addPetOwner("Johan", "Giraldo", "1234", "2005", "Perros");
//		c.saveOwners();
//		
//		Club c2 = new Club("ARIZA4444", "ARIZA CLUB", "2 de enero", "Patos");
//		c2.save();
	}
	
	public void clubMenu() {
		
		boolean exit = false;
		
		while (!exit) {
			
			System.out.println("");
			System.out.print(clubCollection.showClubInfo());
			
			System.out.println("\nPor favor elija un opcion: ");
			System.out.println("1. Ver una lista de los duenos");
			System.out.println("2. Agregar un dueno");
			System.out.println("3. Eliminar un dueno");
			System.out.println("4. Entrar al menu de un dueno");
			System.out.println("5. Salir del menu del club");
			System.out.println("");
			
			int choice = askInt();
			
			switch(choice) {
			
			case 1:
				
				System.out.println("Por favor elija el criterio de ordenamiento");
				System.out.println("1. Ordenar por el nombre");
				System.out.println("2. Ordenar por el apellido");
				System.out.println("3. Ordenar por el id");
				System.out.println("4. Ordenar por la fecha de nacimiento");
				System.out.println("5. Ordenar por el tipo preferido de mascota");
				System.out.println("");
				
				int ownerOrder = askInt();
				
				
				
				break;
				
			case 2:
				
				String poNames = askString("Por favor digite los nombres");
				String poLastNames = askString("Por favor digite los apellidos");
				String poID = askString("Por favor digite el ID del dueno");
				String poBirthDate = askString("Por favor digite la fecha de nacimiento del dueno");
				String poPrefPetType = askString("Por favor digite el tipo preferido de mascota");
				
				if (!clubCollection.findOwner(poID)) {
					clubCollection.addOwner(poNames, poLastNames, poID, poBirthDate, poPrefPetType);
					System.out.println("El dueno ha sido agregado exitosamente");
				}
					
				else
					System.out.println("ERROR. Ya existe un dueno con ese ID");
					
				break;
				
			case 3:
				
				String id = askString("Por favor digite el ID del dueno");
				
				if (clubCollection.selectPetOwner(id)) 
					ownerMenu();
				else 
					System.out.println("ERROR. No se encontro un dueno con ese ID");
					
				break;
				
			case 4:
				
				break;	
				
			case 5:
			
				exit = true;
				break;
			
			default:
				System.out.println("ERROR. Digite una opcion valida");
					
				
			}
		}
		
	}
	
	public void ownerMenu() {
		
		boolean exit = false;
		
		while(!exit) {
			
			System.out.println(clubCollection.showPetOwnerInfo());
			
			System.out.println("");
			System.out.println("1. Ver la lista de mascotas");
			System.out.println("2. Agregar una mascota ");
			System.out.println("3. Eliminar una mascota");
			System.out.println("4. Buscar una mascota");
			System.out.println("5. Salir del menu del club");
			
			int choice = askInt();
			
			switch (choice) {
			
			case 1:
				
				System.out.println("Por favor elija el criterio de ordenamiento");
				System.out.println("1. Ordenar por el nombre");
				System.out.println("2. Ordenar por el apellido");
				System.out.println("3. Ordenar por el id");
				System.out.println("4. Ordenar por la fecha de nacimiento");
				System.out.println("5. Ordenar por el tipo preferido de mascota");
				System.out.println("");
				
				break;
				
			case 2:
				
				String pName = askString("Por favor digite el nombre de la mascota");
				String pID = askString("Por favor digite el ID de la mascota");
				String pBirthDate = askString("Por favor digite la fecha de nacimiento de la mascota");
				String pGender = askString("Por favor digite el genero de la mascota");
				String pType = askString("Por favor digite el tipo de la mascota");
				
				clubCollection.addPet(pName, pID, pBirthDate, pGender, pType);
				
				break;
				
			case 3:
				
				String delID = askString("Por favor digite el ID de la mascota");
				
				if (clubCollection.deletePet(delID))
					System.out.println("La mascota ha sido eliminada exitosamente");
				else
					System.out.println("No se encontro una mascota con ese ID");
				
				break;
				
			case 4:
				
				System.out.println("Por favor elija el criterio de busqueda");
				
				
				break;
				
			case 5:
				
				exit = true;
				break;
				
			default:
				
				System.out.println("ERROR. Digite una opcion valida");
				break;
			
			}
			
		}
	}
	
	
	public int askInt() {
		int ret = 0;
		try {
			String ans = s.nextLine();
			ret = Integer.parseInt(ans);
		}
		catch (NumberFormatException nfe){
			System.out.println("ERROR. Por favor digite un NUMERO");
		}
		
		return ret;
	}
	
	public int askInt(String msg) {
		System.out.println(msg);
		int ret = 0;
		try {
			String ans = s.nextLine();
			ret = Integer.parseInt(ans);
		} 
		catch (NumberFormatException nfe) {
			System.out.println("ERROR. Por favor digite un NUMERO");
		}
		
		return ret;
	}
	
	public String askString(String msg) {
		System.out.println(msg);
		String ret = s.nextLine();
		return ret;
	}
	
}
