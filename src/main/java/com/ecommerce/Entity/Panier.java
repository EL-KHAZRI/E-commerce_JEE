package com.ecommerce.Entity;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	private static int idCounter = 0;
	private int id;
	private static List<LignePanier> listLignePanier = new ArrayList<LignePanier>();

	public Panier() {
		this.id = idCounter;
		idCounter++;
	}
	
	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Panier.idCounter = idCounter;
	}

	public static List<LignePanier> getListLignePanier() {
		return listLignePanier;
	}

	public static void setListLignePanier(List<LignePanier> listLignePanier) {
		Panier.listLignePanier = listLignePanier;
	}

	public Panier(int id) {
		this.id = idCounter;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static void insertIntoListLignePanier(LignePanier lignePanier) {
		listLignePanier.add(lignePanier);
		
		
	}
	
	
}