package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PetOwnerTest {

	private PetOwner p1;
	private PetOwner p2;
	private PetOwner p3;
	
	private Pet pt1;
	private Pet pt2;
	private Pet pt3;
	private Pet pt4;
	private Pet pt5;
	private Pet pt6;
	
	private void loadScene1() {
		
		p1 = new PetOwner("Juan David", "Ossa", "1234", "2000/01/01", "Perros");
		p2 = new PetOwner("Esteban", "Ariza", "4445", "2005/02/04", "Gatos");
		p3 = new PetOwner("Johan", "Giraldo", "5656", "2004/06/24", "Serpientes");
		
		pt1 = new Pet("Max", "4321", "2005/01/05", "M", "Perro");
		pt2 = new Pet("Draco", "7564", "2015/10/05", "M", "Serpiente");
		pt3 = new Pet("Impa", "4445", "2017/12/18", "F", "Gato");
		pt4 = new Pet("Milo", "0123", "2018/01/05", "M", "Perro");
		pt5 = new Pet("Pocoya", "4321", "2012/01/05", "F", "Pollo");
		pt6 = new Pet("Porkosinia", "3232", "2008/01/05", "F", "Cerdo");
		
		p1.addPet(pt1);
		p1.addPet(pt2);
		p1.addPet(pt3);
		
		p2.addPet(pt4);
		p2.addPet(pt5);
		
		p3.addPet(pt6);
	}
	
	@Test
	public void testOrderByName() {
		loadScene1();
		p1.orderByName();
		
		assertEquals(p1.getPets().get(0).getName(), "Draco");
		assertEquals(p1.getPets().get(1).getName(), "Impa");
		assertEquals(p1.getPets().get(2).getName(), "Max");
	}
	
	@Test
	public void testOrderById() {
		loadScene1();
		p1.orderById();
		
		assertEquals(p1.getPets().get(0).getId(), "4321");
		assertEquals(p1.getPets().get(1).getId(), "4445");
		assertEquals(p1.getPets().get(2).getId(), "7564");
	}
	
	@Test
	public void testOrderByBirthDate() {
		loadScene1();
		p1.orderByBirthDate();
		
		assertEquals(p1.getPets().get(0).getBirthDate(), "2005/01/05");
		assertEquals(p1.getPets().get(1).getBirthDate(), "2015/10/05");
		assertEquals(p1.getPets().get(2).getBirthDate(), "2017/12/18");
	}
	
	@Test
	public void testOrderByGender() {
		loadScene1();
		p1.orderByGender();
		
		assertEquals(p1.getPets().get(0).getGender(), "F");
		assertEquals(p1.getPets().get(1).getGender(), "M");
		assertEquals(p1.getPets().get(2).getGender(), "M");
	}
	
	@Test
	public void testOrderByType() {
		loadScene1();
		p1.orderByType();
		
		assertEquals(p1.getPets().get(0).getType(), "Gato");
		assertEquals(p1.getPets().get(1).getType(), "Perro");
		assertEquals(p1.getPets().get(2).getType(), "Serpiente");
	}
	
	@Test
	public void testPetsBinarySearchName() {
		loadScene1();
		p1.orderByName();
		
		assertEquals(p1.petsBinarySearchName("Max", 0, p1.getPets().size()-1), pt1.showInfo());
		assertEquals(p1.petsBinarySearchName("Draco", 0, p1.getPets().size()-1), pt2.showInfo());
		assertEquals(p1.petsBinarySearchName("Impa", 0, p1.getPets().size()-1), pt3.showInfo());
	}
	
	@Test
	public void testPetsBinarySearchId() {
		loadScene1();
		p1.orderById();
		
		assertEquals(p1.petsBinarySearchId("4321", 0, p1.getPets().size()-1), pt1.showInfo());
		assertEquals(p1.petsBinarySearchId("7564", 0, p1.getPets().size()-1), pt2.showInfo());
		assertEquals(p1.petsBinarySearchId("4445", 0, p1.getPets().size()-1), pt3.showInfo());

	}
	
	@Test
	public void testPetsBinarySearchBirthDate() {
		loadScene1();
		p1.orderByBirthDate();
		
		assertEquals(p1.petsBinarySearchBirthDate("2005/01/05", 0, p1.getPets().size()-1), pt1.showInfo());
		assertEquals(p1.petsBinarySearchBirthDate("2015/10/05", 0, p1.getPets().size()-1), pt2.showInfo());
		assertEquals(p1.petsBinarySearchBirthDate("2017/12/18", 0, p1.getPets().size()-1), pt3.showInfo());
		
	}
	
	@Test
	public void testBinarySearchGender() {
		loadScene1();
		p2.orderByGender();
		
		
		assertEquals(p2.petsBinarySearchGender("M", 0, p2.getPets().size()-1), pt4.showInfo());
		assertEquals(p2.petsBinarySearchGender("F", 0, p2.getPets().size()-1), pt5.showInfo());

	}
	
	@Test
	public void testPetsBinarySearchType() {
		loadScene1();
		p1.orderByType();
		
		assertEquals(p1.petsBinarySearchType("Perro", 0, p1.getPets().size()-1), pt1.showInfo());
		assertEquals(p1.petsBinarySearchType("Serpiente", 0, p1.getPets().size()-1), pt2.showInfo());
		assertEquals(p1.petsBinarySearchType("Gato", 0, p1.getPets().size()-1), pt3.showInfo());

	}
	
	@Test
	public void testPetsSearchName() {
		loadScene1();
		p1.orderByName();
		
		assertEquals(p1.petSearchName("Max"), pt1.showInfo());
		assertEquals(p1.petSearchName("Draco"), pt2.showInfo());
		assertEquals(p1.petSearchName("Impa"), pt3.showInfo());
		
	}
	
	@Test
	public void testPetsSearchId() {
		loadScene1();
		p1.orderById();
		
		assertEquals(p1.petSearchId("4321"), pt1.showInfo());
		assertEquals(p1.petSearchId("7564"), pt2.showInfo());
		assertEquals(p1.petSearchId("4445"), pt3.showInfo());
		
	}
	
	@Test
	public void testPetsSearchBirthDate() {
		loadScene1();
		p1.orderById();
		
		assertEquals(p1.petSearchBirthDate("2005/01/05"), pt1.showInfo());
		assertEquals(p1.petSearchBirthDate("2015/10/05"), pt2.showInfo());
		assertEquals(p1.petSearchBirthDate("2017/12/18"), pt3.showInfo());
		
	}
	
	@Test
	public void testPetsSearchGender() {
		loadScene1();
		p2.orderById();
		
		assertEquals(p2.petSearchGender("M"), pt4.showInfo());
		assertEquals(p2.petSearchGender("F"), pt5.showInfo());
		
	}
	
	@Test
	public void testPetsSearchType() {
		loadScene1();
		p1.orderByType();
		
		assertEquals(p1.petSearchType("Perro"), pt1.showInfo());
		assertEquals(p1.petSearchType("Serpiente"), pt2.showInfo());
		assertEquals(p1.petSearchType("Gato"), pt3.showInfo());
	}
	

}
