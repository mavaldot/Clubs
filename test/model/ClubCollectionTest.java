package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClubCollectionTest {

	private ClubCollection cc;
	private Club c1;
	private Club c2;
	private Club c3;
	
	private void loadScene1() {
		
		cc = new ClubCollection();
		c1 = new Club("Johan Club", "12345678", "2000/05/05", "Perros");
		c2 = new Club("Ariza Club", "12345679", "1993/09/05", "Gatos");
		c3 = new Club("Valdes Club", "55554444", "1956/10/06", "Pajaros"); 
		
		c1.addPetOwner(new PetOwner("Juan David", "Ossa", "1234", "2000/01/01", "Perros"));
		c1.addPetOwner(new PetOwner("Esteban", "Ariza", "4445", "2005/02/04", "Gatos"));
		c1.addPetOwner(new PetOwner("Johan", "Giraldo", "5656", "2004/06/24", "Serpientes"));
		
		c2.addPetOwner(new PetOwner("David Jhun", "Kim", "4321", "1998/06/04", "Perros"));
		c2.addPetOwner(new PetOwner("Mateo", "Valdes", "3333", "2009/02/27", "Hamsters"));

		c3.addPetOwner(new PetOwner("Juan", "Sanchez", "7767", "2010/07/15", "Peces"));

		
		cc.addClub(c1);
		cc.addClub(c2);
		cc.addClub(c3);
	}
	
	@Test
	public void testOrderByName() {
		loadScene1();
		cc.orderByName();
		assertEquals(cc.getClubs().get(0).getName(), "Ariza Club");
		assertEquals(cc.getClubs().get(1).getName(), "Johan Club");
		assertEquals(cc.getClubs().get(2).getName(), "Valdes Club");
	}
	
	@Test
	public void testOrderById() {
		loadScene1();
		cc.orderById();
		assertEquals(cc.getClubs().get(0).getId(), "12345678");
		assertEquals(cc.getClubs().get(1).getId(), "12345679");
		assertEquals(cc.getClubs().get(2).getId(), "55554444");
	}

	@Test
	public void testOrderByCreationDate() {
		loadScene1();
		cc.orderByCreationDate();
		assertEquals(cc.getClubs().get(0).getCreationDate(), "1956/10/06");
		assertEquals(cc.getClubs().get(1).getCreationDate(), "1993/09/05");
		assertEquals(cc.getClubs().get(2).getCreationDate(), "2000/05/05");
	}
	
	@Test
	public void testOrderByType() {
		loadScene1();
		cc.orderByType();
		
		assertEquals(cc.getClubs().get(0).getType(), "Gatos");
		assertEquals(cc.getClubs().get(1).getType(), "Pajaros");
		assertEquals(cc.getClubs().get(2).getType(), "Perros");
	}
	
	@Test
	public void testOrderByNumberOfPetOwners() {
		loadScene1();
		cc.orderByNumberOfPetOwners();
		
		assertEquals(cc.getClubs().get(0).getName(), "Valdes Club");
		assertEquals(cc.getClubs().get(1).getName(), "Ariza Club");
		assertEquals(cc.getClubs().get(2).getName(), "Johan Club");
	}
	
	@Test
	public void testBinaryClubSearchName() {
		loadScene1();
		
		cc.orderByName();
		
		assertEquals(cc.binaryClubSearchName("Johan Club", 0, cc.getClubs().size()-1), c1.showInfo());
		assertEquals(cc.binaryClubSearchName("Ariza Club", 0, cc.getClubs().size()-1), c2.showInfo());
		assertEquals(cc.binaryClubSearchName("Valdes Club", 0, cc.getClubs().size()-1), c3.showInfo());
	}
	
	@Test
	public void testClubSearchName() {
		loadScene1();
		cc.orderByName();
		
		assertEquals(cc.clubSearchName("Johan Club"), c1.showInfo());
		assertEquals(cc.clubSearchName("Ariza Club"), c2.showInfo());
		assertEquals(cc.clubSearchName("Valdes Club"), c3.showInfo());
	}
	
	@Test
	public void testBinaryClubSearchId() {
		loadScene1();
		cc.orderById();
		
		assertEquals(cc.binaryClubSearchId("12345678", 0, cc.getClubs().size()-1), c1.showInfo());
		assertEquals(cc.binaryClubSearchId("12345679", 0, cc.getClubs().size()-1), c2.showInfo());
		assertEquals(cc.binaryClubSearchId("55554444", 0, cc.getClubs().size()-1), c3.showInfo());
	}
	
	@Test
	public void testClubSearchId() {
		loadScene1();
		cc.orderById();
		assertEquals(cc.clubSearchId("12345678"), c1.showInfo());
		assertEquals(cc.clubSearchId("12345679"), c2.showInfo());
		assertEquals(cc.clubSearchId("55554444"), c3.showInfo());
	}
	
	@Test
	public void testBinaryClubSearchCreationDate() {
		loadScene1();
		cc.orderByCreationDate();
		
		assertEquals(cc.binaryClubSearchCreationDate("2000/05/05", 0, cc.getClubs().size()-1), c1.showInfo());
		assertEquals(cc.binaryClubSearchCreationDate("1993/09/05", 0, cc.getClubs().size()-1), c2.showInfo());
		assertEquals(cc.binaryClubSearchCreationDate("1956/10/06", 0, cc.getClubs().size()-1), c3.showInfo());
	}
	
	@Test
	public void testClubSearchCreationDate() {
		loadScene1();
		cc.orderByCreationDate();
		assertEquals(cc.clubSearchCreationDate("2000/05/05"), c1.showInfo());
		assertEquals(cc.clubSearchCreationDate("1993/09/05"), c2.showInfo());
		assertEquals(cc.clubSearchCreationDate("1956/10/06"), c3.showInfo());
	}
	
	@Test
	public void testBinaryClubSearchType() {
		loadScene1();
		cc.orderByType();
		
		assertEquals(cc.binaryClubSearchType("Perros", 0, cc.getClubs().size()-1), c1.showInfo());
		assertEquals(cc.binaryClubSearchType("Gatos", 0, cc.getClubs().size()-1), c2.showInfo());
		assertEquals(cc.binaryClubSearchType("Pajaros", 0, cc.getClubs().size()-1), c3.showInfo());

	}
	
	@Test
	public void testClubSearchType() {
		loadScene1();
		cc.orderByCreationDate();
		assertEquals(cc.clubSearchType("Perros"), c1.showInfo());
		assertEquals(cc.clubSearchType("Gatos"), c2.showInfo());
		assertEquals(cc.clubSearchType("Pajaros"), c3.showInfo());
	}
}
