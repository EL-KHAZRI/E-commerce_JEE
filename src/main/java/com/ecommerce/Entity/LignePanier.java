package com.ecommerce.Entity;

import java.time.LocalTime;

public class LignePanier {
	private static int idCounter = 0;
	private int id;
	private Produit produit;
	private Panier panier;
	private int quantite;
	
	public LignePanier(Produit produit, Panier panier, int quantite) {
		this.id = idCounter;
		this.produit = produit;
		this.panier = panier;
		this.quantite = quantite;
		
		idCounter++;
	}
	
	public LignePanier(int id, Produit produit, Panier panier, int quantite) {
		this.id = id;
		this.produit = produit;
		this.panier = panier;
		this.quantite = quantite;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}