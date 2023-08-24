package com.ecommerce.clientDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ecommerce.DAO.DataBaseConnection;
import com.ecommerce.Entity.Categorie;
import com.ecommerce.Entity.Commande;
import com.ecommerce.Entity.LigneCommande;
import com.ecommerce.Entity.LignePanier;
import com.ecommerce.Entity.Panier;
import com.ecommerce.Entity.Produit;
import com.ecommerce.Entity.Role;
import com.ecommerce.Entity.User;
import com.ecommerce.userDAO.UserDAO;




public class ClientDAO {
	
	
	/*
	 * getProductsById : helper method
	 * */
	public static Produit getProductsById(int productId) {
		Produit produit = new Produit();
		
		int id, quantityDispo;
		Categorie categorie;
		String nom;
		double prix;

		try (Connection connection = DataBaseConnection.connectToDB();) {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Produit WHERE id = ?");
	    	preparedStatement.setString(1, "productId");

	    	ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	id = resultSet.getInt("id");
	        	quantityDispo = resultSet.getInt("quantityDispo");
 	        	nom = resultSet.getString("nom");
	        	prix = resultSet.getDouble("prix");
	        	int idCategorie = resultSet.getInt("idCategorie");
	        	
	        	Optional<Categorie> optionalCategorie = UserDAO.getCategorieById(idCategorie);
	        	 
	        	if (optionalCategorie.isPresent()) {
	        		categorie = optionalCategorie.get();
	        		// produit = new Produit(id, categorie, nom, quantityDispo, prix);
	        	}
	        }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
		
	     return produit;		
	}

	
	public void addProductToPanier(int idProduit, int quantite) {
		Panier panier = new Panier(); // 
		Produit produit = ClientDAO.getProductsById(idProduit);
		if (produit.getQuantityDispo() >= quantite) {
			LignePanier lPanier = new LignePanier(produit, panier, quantite);
			Panier.insertIntoListLignePanier(lPanier); //  static?
			//Panier.getListLignePanier().add(lPanier); //  static?
		}	
	}
	
	
	
	//public affichePanier() {} we already have getListLignePanier() 
	
	public void addCommande(int idProduit, int quantite, String dateCommande, User user) {
		
		// i think we should do the same thing we have been did with the previous method
		Commande commande = new Commande(dateCommande, user);
		Produit produit = ClientDAO.getProductsById(idProduit);
		LigneCommande lCommande = new LigneCommande(commande, produit, quantite);
		 try (Connection connection = DataBaseConnection.connectToDB();){
		        

		        PreparedStatement insertInToCmd = connection.prepareStatement("insert into Commande (date, user) values (?, ? ) ;");
		        User u = commande.getUser();

		        insertInToCmd.setString(1,commande.getDateCommande());
		        insertInToCmd.setInt(2,u.getId());
		        insertInToCmd.executeUpdate();
		        
		        PreparedStatement insertInToLigneCmd = connection.prepareStatement("insert into LigneCommande (idCommande, idProduit, quantite) values (?, ?, ?) ;");

		        insertInToLigneCmd.setInt(1, commande.getId());
		        insertInToLigneCmd.setInt(2, idProduit);
		        insertInToLigneCmd.setInt(3, quantite);
		        insertInToLigneCmd.executeUpdate();
		            
		        } catch(SQLException e) {
		        	e.printStackTrace();
		        }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
public void addCommande2(Panier panier, String dateCommande, User user) {
		
		// i think we should do the same thing we have been did with the previous method
		Commande commande = new Commande(dateCommande, user);
		try (Connection connection = DataBaseConnection.connectToDB();){
	        

	        PreparedStatement insertInToCmd = connection.prepareStatement("insert into Commande (date, user) values (?, ? ) ;");
	        User u = commande.getUser();

	        insertInToCmd.setString(1,commande.getDateCommande());
	        insertInToCmd.setInt(2,u.getId());
	        insertInToCmd.executeUpdate();
		
	        
		List<LignePanier> listLignePanier = panier.getListLignePanier();
		//Produit produit = ClientDAO.getProductsById(idProduit);
		
		for(LignePanier lignePanier: listLignePanier) {
			LigneCommande lCommande = new LigneCommande(commande, lignePanier.getProduit(), lignePanier.getQuantite());
			 
	        
	        PreparedStatement insertInToLigneCmd = connection.prepareStatement("insert into LigneCommande (idCommande, idProduit, quantite) values (?, ?, ?) ;");

	        insertInToLigneCmd.setInt(1, commande.getId());
	        insertInToLigneCmd.setInt(2, lignePanier.getProduit().getId());
	        insertInToLigneCmd.setInt(3, lignePanier.getProduit().getQuantityDispo());
	        insertInToLigneCmd.executeUpdate();
			
		}
		          
		        } catch(SQLException e) {
		        	e.printStackTrace();
		        }
	}
	
	
	
	public void removeProduitFromPanier(int idlLignePanier) {
	    List<LignePanier> listLignePanier = Panier.getListLignePanier();
	    
	    listLignePanier.removeIf(ligne -> ligne.getId() == idlLignePanier);
	}

		
	//public afiche panier()  getListlignePanier()
}
	
	
	
	






