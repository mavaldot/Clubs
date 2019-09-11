package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClubTest {

	private Club c1;
	private Club c2;
	private Club c3;
	private Club c4;
	
	private void loadScene1() {
		c1 = new Club("Johan Club", "12345678", "2000/05/05", "Perros");
		c2 = new Club("Ariza Club", "12345679", "1993/09/05", "Gatos");
		c3 = new Club("Valdes Club", "55554444", "1956/10/06", "Pajaros"); 
		
		c1.addPetOwner(new PetOwner("Juan David", "Ossa", "1234", "2000/01/01", "Perros"));
		c1.addPetOwner(new PetOwner("Esteban", "Ariza", "4445", "2005/02/04", "Gatos"));
		c1.addPetOwner(new PetOwner("Johan", "Giraldo", "5656", "2004/06/24", "Serpientes"));
		
		c2.addPetOwner(new PetOwner("David Jhun", "Kim", "4321", "1998/06/04", "Perros"));
		c2.addPetOwner(new PetOwner("Mateo", "Valdes", "3333", "2009/02/27", "Hamsters"));

		c3.addPetOwner(new PetOwner("Juan", "Sanchez", "7767", "2010/07/15", "Peces"));
	}
	
	private void loadScene2() {
		
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
		assertTrue(c1.compareTo(c4) == 0);
		assertTrue(c3.compareTo(c1) > 0);
		assertTrue(c2.compareTo(c3) < 0);
		
	}
	
	@Test
	public void testOrderByName() {
		
		c1.orderByName();
		
	}

	
	@Test
	void test() {
		loadScene1();
	}

}
