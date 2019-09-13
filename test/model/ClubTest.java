package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClubTest {

	private Club c1;
	private Club c2;
	private Club c3;
	
	private PetOwner p1;
	private PetOwner p2;
	private PetOwner p3;
	private PetOwner p4;
	private PetOwner p5;
	private PetOwner p6;
	
	private void loadScene1() {
		c1 = new Club("Johan Club", "12345678", "2000/05/05", "Perros");
		c2 = new Club("Ariza Club", "12345679", "1993/09/05", "Gatos");
		c3 = new Club("Valdes Club", "55554444", "1956/10/06", "Pajaros"); 
		
		p1 = new PetOwner("Juan David", "Ossa", "1234", "2000/01/01", "Perros");
		p2 = new PetOwner("Esteban", "Ariza", "4445", "2005/02/04", "Gatos");
		p3 = new PetOwner("Johan", "Giraldo", "5656", "2004/06/24", "Serpientes");
		
		p4 = new PetOwner("David Jhun", "Kim", "4321", "1998/06/04", "Perros");
		p5 = new PetOwner("Mateo", "Valdes", "3333", "2009/02/27", "Hamsters");
		
		p6 = new PetOwner("Juan", "Sanchez", "7767", "2010/07/15", "Peces");
		
		c1.addPetOwner(p1);
		c1.addPetOwner(p2);
		c1.addPetOwner(p3);
		
		c2.addPetOwner(new PetOwner("David Jhun", "Kim", "4321", "1998/06/04", "Perros"));
		c2.addPetOwner(new PetOwner("Mateo", "Valdes", "3333", "2009/02/27", "Hamsters"));

		c3.addPetOwner(p6);
	}

	
	@Test
	public void testFindOwner() {
		loadScene1();
		
		assertTrue(c1.findOwner("1234"));
		assertTrue(c1.findOwner("4445"));
		assertTrue(c1.findOwner("5656"));
		
		assertTrue(c1.findOwner("0000") == false);
		
		assertTrue(c2.findOwner("4321"));
		assertTrue(c2.findOwner("3333"));
		
		assertTrue(c2.findOwner("0909") == false);
	}
	
	@Test
	public void testCompareTo() {
		
		loadScene1();
		
		assertTrue(c1.compareTo(c2) > 0);
		assertTrue(c3.compareTo(c1) > 0);
		assertTrue(c2.compareTo(c3) < 0);
		
	}
	
	@Test
	public void testOrderByName() {
		
		loadScene1();
		c1.orderByName();
		
		assertEquals(c1.getPetOwners().get(0).getNames(), "Esteban");
		assertEquals(c1.getPetOwners().get(1).getNames(), "Johan");
		assertEquals(c1.getPetOwners().get(2).getNames(), "Juan David");
		
	}

	@Test
	public void testOrderByLastName() {
		
		loadScene1();
		c1.orderByLastName();
		
		assertEquals(c1.getPetOwners().get(0).getLastNames(), "Ariza");
		assertEquals(c1.getPetOwners().get(1).getLastNames(), "Giraldo");
		assertEquals(c1.getPetOwners().get(2).getLastNames(), "Ossa");
		
	}
	
	@Test
	public void testOrderById() {
		loadScene1();
		c1.orderById();
		
		assertEquals(c1.getPetOwners().get(0).getId(), "1234");
		assertEquals(c1.getPetOwners().get(1).getId(), "4445");
		assertEquals(c1.getPetOwners().get(2).getId(), "5656");
	}

	@Test
	public void testOrderByBirthDate() {
		loadScene1();
		c1.orderByBirthDate();
		
		assertEquals(c1.getPetOwners().get(0).getBirthDate(), "2000/01/01");
		assertEquals(c1.getPetOwners().get(1).getBirthDate(), "2004/06/24");
		assertEquals(c1.getPetOwners().get(2).getBirthDate(), "2005/02/04");
		
	}
	
	@Test
	public void testPetOwnersBinarySearchName() {
		loadScene1();
		c1.orderByName();
		
		assertEquals(c1.petOwnersBinarySearchName("Juan David", 0, c1.getPetOwners().size()-1), p1.showInfo());
		assertEquals(c1.petOwnersBinarySearchName("Esteban", 0, c1.getPetOwners().size()-1), p2.showInfo());
		assertEquals(c1.petOwnersBinarySearchName("Johan", 0, c1.getPetOwners().size()-1), p3.showInfo());
		
	}
	
	@Test
	public void testPetOwnersBinarySearchLastName() {
		loadScene1();
		c1.orderByLastName();
		
		assertEquals(c1.petOwnersBinarySearchLastName("Ossa", 0, c1.getPetOwners().size()-1), p1.showInfo());
		assertEquals(c1.petOwnersBinarySearchLastName("Ariza", 0, c1.getPetOwners().size()-1), p2.showInfo());
		assertEquals(c1.petOwnersBinarySearchLastName("Giraldo", 0, c1.getPetOwners().size()-1), p3.showInfo());

	}
	
	@Test
	public void testPetOwnersBinarySearchId() {
		loadScene1();
		c1.orderById();
		
		assertEquals(c1.petOwnersBinarySearchId("1234", 0, c1.getPetOwners().size()-1), p1.showInfo());
		assertEquals(c1.petOwnersBinarySearchId("4445", 0, c1.getPetOwners().size()-1), p2.showInfo());
		assertEquals(c1.petOwnersBinarySearchId("5656", 0, c1.getPetOwners().size()-1), p3.showInfo());

	}
	
	@Test
	public void testPetOwnersBinarySearchBirthDate() {
		loadScene1();
		c1.orderByBirthDate();
		
		assertEquals(c1.petOwnersBinarySearchBirthDate("2000/01/01", 0, c1.getPetOwners().size()-1), p1.showInfo());
		assertEquals(c1.petOwnersBinarySearchBirthDate("2005/02/04", 0, c1.getPetOwners().size()-1), p2.showInfo());
		assertEquals(c1.petOwnersBinarySearchBirthDate("2004/06/24", 0, c1.getPetOwners().size()-1), p3.showInfo());
		
	}
	
	@Test
	public void testPetOWnersBinarySearchPrefPetType() {
		loadScene1();
		c1.orderByPrefPetType();
		
		assertEquals(c1.petOwnersBinarySearchPrefPetType("Perros", 0, c1.getPetOwners().size()-1), p1.showInfo());
		assertEquals(c1.petOwnersBinarySearchPrefPetType("Gatos", 0, c1.getPetOwners().size()-1), p2.showInfo());
		assertEquals(c1.petOwnersBinarySearchPrefPetType("Serpientes", 0, c1.getPetOwners().size()-1), p3.showInfo());
		

	}
	
	@Test
	public void testPetOwnersSearchName() {
		loadScene1();
		c1.orderByName();
		
		assertEquals(c1.petOwnersSearchName("Juan David"), p1.showInfo());
		assertEquals(c1.petOwnersSearchName("Esteban"), p2.showInfo());
		assertEquals(c1.petOwnersSearchName("Johan"), p3.showInfo());
	}
	
	@Test
	public void testPetOwnersSearchLastName() {
		loadScene1();
		c1.orderByLastName();
		
		assertEquals(c1.petOwnersSearchLastName("Ossa"), p1.showInfo());
		assertEquals(c1.petOwnersSearchLastName("Ariza"), p2.showInfo());
		assertEquals(c1.petOwnersSearchLastName("Giraldo"), p3.showInfo());
	}
	
	@Test
	public void testPetOwnersSearchId() {
		loadScene1();
		c1.orderById();
		
		assertEquals(c1.petOwnersSearchId("1234"), p1.showInfo());
		assertEquals(c1.petOwnersSearchId("4445"), p2.showInfo());
		assertEquals(c1.petOwnersSearchId("5656"), p3.showInfo());
	}
	
	@Test
	public void testPetOwnersSearchBirthDate() {
		loadScene1();
		c1.orderByBirthDate();
		
		assertEquals(c1.petOwnersSearchBirthDate("2000/01/01"), p1.showInfo());
		assertEquals(c1.petOwnersSearchBirthDate("2005/02/04"), p2.showInfo());
		assertEquals(c1.petOwnersSearchBirthDate("2004/06/24"), p3.showInfo());
	}
	
	@Test
	public void testPetOwnersSearchPrefPetType() {
		loadScene1();
		c1.orderByPrefPetType();
		
		assertEquals(c1.petOwnersSearchPrefPetType("Perros"), p1.showInfo());
		assertEquals(c1.petOwnersSearchPrefPetType("Gatos"), p2.showInfo());
		assertEquals(c1.petOwnersSearchPrefPetType("Serpientes"), p3.showInfo());
	}
}
