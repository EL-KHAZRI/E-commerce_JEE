package com.ecommerce.Entity;

import java.util.*;

public class Commande {
	private int id;
	private String dateCommande;
	private User user;
	//private List<LigneCommande> listeLigneCommande;
	//list de ligne de commande ?
	
	public Commande() {}
	
	public Commande(String dateCommande, User user) {		// user ??
		this.dateCommande = dateCommande;
		this.user = user;
	}

	public Commande(int id, String dateCommande, User user) { // user ??
		this.id = id;
		this.dateCommande = dateCommande;
		this.user = user;
	}
/*
	public List<LigneCommande> getListeLigneCommande() {
		return listeLigneCommande;
	}

	public void setListeLigneCommande(List<LigneCommande> listeLigneCommande) {
		this.listeLigneCommande = listeLigneCommande;
	}
	
	public void insertIntoListLigneCommande(LigneCommande ligneCommande) {
		listeLigneCommande.add(ligneCommande);
		
	}
	*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}