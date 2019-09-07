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

public class Main {
	
	public static void main(String[] args) {
		
		Menu m = new Menu();
		m.mainMenu();
	}
}
