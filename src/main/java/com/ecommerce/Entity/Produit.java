package com.ecommerce.Entity;

public class Produit {
	private int id;
	private String titre;
	private double prix;
	private int quantityDispo;
	private String imageName;
	private Categorie categorie;
	
	public Produit() {}
	
	public Produit(String titre, double prix, int quantityDispo, String imageName, Categorie categorie) {
		this.titre = titre;
		this.prix = prix;
		this.quantityDispo = quantityDispo;
		this.imageName = imageName;
		this.categorie = categorie;
	}
	
	public Produit(int id, String titre, double prix, int quantityDispo, String imageName, Categorie categorie) {
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.quantityDispo = quantityDispo;
		this.imageName = imageName;
		this.categorie = categorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantityDispo() {
		return quantityDispo;
	}

	public void setQuantityDispo(int quantityDispo) {
		this.quantityDispo = quantityDispo;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	
}