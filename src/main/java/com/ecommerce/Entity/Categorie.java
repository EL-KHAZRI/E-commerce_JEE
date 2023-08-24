package com.ecommerce.Entity;

import java.util.List;

public class Categorie {
	private int id;
	private String categorie;
	private List<Produit> listProduits;
	
	public Categorie() {}
	
	public Categorie(String categorie) {
		this.categorie = categorie;
	}
	
	public Categorie(int id, String categorie) {
		this.id = id;
		this.categorie = categorie;
	}
	
	public Categorie(int id, String categorie, List<Produit> listProduits) {
		this.id = id;
		this.categorie = categorie;
		this.listProduits = listProduits;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListProduits() {
		return listProduits;
	}
	public void setListProduits(List<Produit> listProduits) {
		this.listProduits = listProduits;
	}
	
}