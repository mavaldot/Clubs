package ui;

import java.util.Scanner;
import model.ClubCollection;
import model.MockGenerator;

public class Menu {

	private ClubCollection clubCollection;
	private Scanner s;
	
	private MockGenerator mockGenerator;
	
	public Menu() {
		clubCollection = new ClubCollection();
		s = new Scanner(System.in);
		mockGenerator = new MockGenerator();
	}
	
	public void mainMenu() {
		
		boolean running = true;
		
		clubCollection.loadClubs();
		clubCollection.loadOwners();
		
		//remove the comment (//) and run the program to generate 10000 owners and 100000 to 200000 pets in each club
		//mockGenerator.generateMockClubCollection(clubCollection);
		
		while (running) {
			
			System.out.println("\nBienvenido al menu principal! Por favor elija una opcion:");

			System.out.println("1. Ver la lista de los clubes");
			System.out.println("2. Entrar a un club");
			System.out.println("3. Agregar un club");
			System.out.println("4. Eliminar un club");
			System.out.println("5. Buscar clubes");
			System.out.println("6. Salir\n");
			
			int choice = askInt();
			
			switch (choice) {
			
			case 1:

				System.out.println("Por favor elija el criterio de ordenamiento:");
				System.out.println("1. Ordenar por nombre");
				System.out.println("2. Ordenar por ID");
				System.out.println("3. Ordenar por fecha de creacion");
				System.out.println("4. Ordenar por tipo de mascota");
				System.out.println("5. Ordenar por numero de duenos");
				
				int clubOrder = askInt();

				System.out.println(clubCollection.showClubList(clubOrder));
				
				break;
				
			case 2:
				
				String id = askString("Por favor digite el id del club");
				
				if (clubCollection.selectClub(id)) 
					clubMenu();
				else
					System.out.println("ERROR. No se encontro un club con ese ID");
				
				break;
				
			case 3:

				String clubName = askString("Por favor digite el nombre del club");
				String clubID = askString("Por favor digite el ID del club");
				String clubCreationDate = askDateWhen("se creo el club");
				String clubType = askString("Por favor digite el tipo de mascotas del club");
				
				if (!clubCollection.findClub(clubID)) {
					clubCollection.addClub(clubName, clubID, clubCreationDate, clubType);
					System.out.println("Su club ha sido agregado!");
				} 
				else {
					System.out.println("ERROR. Ya existe un club con ese ID");
				}
				
				clubCollection.saveClubs();
				
				clubCollection.orderByName();
				
				
				break;
				
			case 4:
					
				String delID = askString("Por favor digite el ID del club que desea eliminar");
				if (clubCollection.deleteClub(delID))
					System.out.println("El club ha sido eliminado exitosamente");
				else 
					System.out.println("No se encontro un club con ese ID");
				break;
				
			case 5:
				
				System.out.println("Por favor elija el criterio de busqueda");
				System.out.println("1. Buscar por nombre");
				System.out.println("2. Buscar por ID");
				System.out.println("3. Buscar por fecha de creacion");
				System.out.println("4. Buscar por tipo de mascota");
				System.out.println("5. Buscar por numero de duenos");
				
				int criteria = askInt();
				
				String item = askString("Digite lo que esta buscando"); 
				
				System.out.println(clubCollection.searchClubs(criteria, item));
				
				break;
				
			case 6:
				
				running = false;
				clubCollection.saveClubs();
				System.out.println("Hasta luego :)");
				break;
			
			default:
				
				System.out.println("Por favor digite un valor valido");	
				break;
			}
			
		}

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
			System.out.println("5. Buscar duenos");
			System.out.println("6. Salir del menu del club");
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
				
				System.out.println(clubCollection.showOwnerList(ownerOrder));
				
				break;
				
			case 2:
				
				String poNames = askString("Por favor digite los nombres");
				String poLastNames = askString("Por favor digite los apellidos");
				String poID = askString("Por favor digite el ID del dueno");
				String poBirthDate = askDateWhen("nacio el dueno");
				String poPrefPetType = askString("Por favor digite el tipo preferido de mascota");
				
				if (!clubCollection.findOwner(poID)) {
					clubCollection.addOwner(poNames, poLastNames, poID, poBirthDate, poPrefPetType);
					System.out.println("El dueno ha sido agregado exitosamente");
				}
					
				else
					System.out.println("ERROR. Ya existe un dueno con ese ID");
					
				break;
				
			case 3:
				
				String deleteId = askString("Por favor digite el ID del dueno");
				
				if(clubCollection.deleteOwner(deleteId)) 
					System.out.println("El dueno ha sido borrado exitosamente");
				else
					System.out.println("No se encontro un dueno con ese ID");
				
				break;
				
			case 4:
				
				String id = askString("Por favor digite el ID del dueno");
				
				if (clubCollection.selectPetOwner(id)) 
					ownerMenu();
				else 
					System.out.println("ERROR. No se encontro un dueno con ese ID");
					
				break;
				
			case 5:
			
				System.out.println("Por favor elija el criterio de busqueda");
				System.out.println("1. Buscar por nombres");
				System.out.println("2. Buscar por apellidos");
				System.out.println("3. Buscar por id");
				System.out.println("4. Buscar por fecha de nacimiento");
				System.out.println("5. Buscar por tipo de mascota preferido");
				
				int criteria = askInt();
				
				String item = askString("Digite lo que esta buscando"); 
				
				System.out.println(clubCollection.searchOwners(criteria, item));

				break;

			case 6:
				exit = true;
				break;
				
			default:
				System.out.println("Por favor digite una opcion valida");
					
				
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
			System.out.println("4. Buscar mascotas");
			System.out.println("5. Salir del menu del dueno");
			
			int choice = askInt();
			
			switch (choice) {
			
			case 1:
				
				System.out.println("Por favor elija el criterio de ordenamiento");
				System.out.println("1. Ordenar por el nombre");
				System.out.println("2. Ordenar por el id");
				System.out.println("3. Ordenar por la fecha de nacimiento");
				System.out.println("4. Ordenar por el genero");
				System.out.println("5. Ordenar por el tipo de mascota");
				System.out.println("");
				
				int petOrder = askInt();
				
				System.out.println(clubCollection.showPetList(petOrder));
				
				break;
				
			case 2:
				
				String pName = askString("Por favor digite el nombre de la mascota");
				String pID = askString("Por favor digite el ID de la mascota");
				String pBirthDate = askDateWhen("nacio la mascota (si no se la sabe ponga una aproximacion)");
				int genderStr = askInt("Por favor elija el genero de la mascota. Opciones:\n1. M\n2. F\n", 1, 2);
				String pGender = genderStr == 1 ? "M" : "F";		
				String pType = askString("Por favor digite el tipo de la mascota");
				
				if (!clubCollection.findPet(pName)) {
					clubCollection.addPet(pName, pID, pBirthDate, pGender, pType);
					System.out.println("La mascota ha sido agregada");
				} else
					System.out.println("ERROR. Ya existe una mascota con ese nombre");
				
				break;
				
			case 3:
				
				String delName = askString("Por favor digite el nombre de la mascota");
				
				if (clubCollection.deletePet(delName))
					System.out.println("La mascota ha sido eliminada exitosamente");
				else
					System.out.println("No se encontro una mascota con ese nombre");
				
				break;
				
			case 4:
				
				System.out.println("Por favor elija el criterio de busqueda");
				System.out.println("1. Buscar por nombre");
				System.out.println("2. Buscar por ID");
				System.out.println("3. Buscar por fecha de nacimiento");
				System.out.println("4. Buscar por genero");
				System.out.println("5. Buscar por tipo de mascota");
				
				int criteria = askInt();
				
				String item = askString("Digite lo que esta buscando"); 
				
				System.out.println(clubCollection.searchPets(criteria, item));
				
				
				break;
				
			case 5:
				
				exit = true;
				
				break;

			default:
				
				System.out.println("Por favor digite una opcion valida");
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
		catch (NumberFormatException nfe) {
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
	
	public int askInt(String msg, int min, int max) {
		System.out.println(msg);
		int ret = 0;
		boolean success = false;
		
		while (!success) {
			
			try {
				
				String ans = s.nextLine();
				ret = Integer.parseInt(ans);
				
				if (ret < min || ret > max)
					System.out.println("ERROR. Por favor digite un numero entre " + min + " y " + max);
				else
					success = true;
					
			} 
			catch (NumberFormatException nfe) {
				System.out.println("ERROR. Por favor digite un NUMERO");
			}
		}
		
		return ret;
	}
	
	
	public String askString(String msg) {
		System.out.println(msg);
		String ret = s.nextLine();
		return ret;
	}
	
	public String askDateWhen(String str) {
	
		int year = askInt("Por favor digite el ano en que " + str, 1000, 3000);
		int month = askInt("Por favor digite el mes del ano en que " + str, 1, 12);
		int day = askInt("Por favor digite el dia del mes en que " + str, 1, 31);
		
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
