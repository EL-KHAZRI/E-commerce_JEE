package com.ecommerce.Entity;

public class LigneCommande {
	private int id;
	private Commande commande;
	private Produit produit;
	private int quantite;
	
	public LigneCommande() {}
	
	public LigneCommande(Commande commande, Produit produit, int quantite) {
		this.commande = commande;
		this.produit = produit;
		this.quantite = quantite;
	}
	
	public LigneCommande(int id, Commande commande, Produit produit, int quantite) {
		this.id = id;
		this.commande = commande;
		this.produit = produit;
		this.quantite = quantite;
	}
	
}