package com.ecommerce.userDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerce.DAO.DataBaseConnection;
import com.ecommerce.Entity.Categorie;
import com.ecommerce.Entity.Produit;

public class UserDAO {
	public static Optional<Categorie> getCategorieById(int id) {
		Categorie categorie = null;
		
	     try (Connection connection = DataBaseConnection.connectToDB();) {
	    	 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Categorie WHERE id = ?");
	    	 preparedStatement.setInt(1, id);

	    	 ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	        	 // Initialaise The Object
	        	 categorie = new Categorie();
	        	 
	        	 categorie.setId(id);
	        	 categorie.setCategorie(resultSet.getString("categorie"));
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
		
		return Optional.ofNullable(categorie);
	}
	
	public static Optional<Categorie> getCategorieByName(String categorieName) {
		Categorie categorie = null;
		
	    try (Connection connection = DataBaseConnection.connectToDB();) {
	    	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Categorie WHERE categorie = ?");
	    	preparedStatement.setString(1, categorieName);

	    	ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	// Initialise The Object
	        	categorie = new Categorie();
	        	 
	        	categorie.setId(resultSet.getInt("id"));
	        	categorie.setCategorie(categorieName);
	        }
	    } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
		
		return Optional.ofNullable(categorie);
	}
	
	public static List<Categorie> getAllCategories() {
		List<Categorie> allCategories = new ArrayList<>();
		
		 int id;
	     String categorie = "";

	     try (Connection connection = DataBaseConnection.connectToDB();) {
	    	 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Categorie");

	    	 ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	        	 id = resultSet.getInt("id");
	        	 categorie = resultSet.getString("categorie");

	        	 allCategories.add(new Categorie(id, categorie));
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
		
	     return allCategories;
	}
	
	public static List<Produit> getAllProducts() {
		List<Produit> allProducts = new ArrayList<>();
		
		int id, quantityDispo;
		Categorie categorie;
		String titre, imageName;
		double prix;

	     try (Connection connection = DataBaseConnection.connectToDB();) {
	    	 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Produit");

	    	 ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	        	 id = resultSet.getInt("id");
	        	 titre = resultSet.getString("titre");
	        	 prix = resultSet.getDouble("prix");
	        	 quantityDispo = resultSet.getInt("quantiteDispo");
	        	 imageName = resultSet.getString("image");
	        	 int idCategorie = resultSet.getInt("idCategorie");
	        	 
	        	 Optional<Categorie> optionalCategorie = getCategorieById(idCategorie);
	        	 
	        	 System.out.println("Beffore: " + id);
	        	 if (optionalCategorie.isPresent()) {
		        	categorie = optionalCategorie.get();
		        	allProducts.add(new Produit(id, titre, prix, quantityDispo, imageName, categorie));
		        	System.out.println("Added: " + id);
	        	 }
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
		
	     return allProducts;
	}
	
	public static List<Produit> getProductsByCategorie(String categorieName) {
		List<Produit> allProducts = new ArrayList<Produit>();
		
		int id, quantityDispo;
		Categorie categorie;
		Optional<Categorie> optionalCategorie = getCategorieByName(categorieName); 
		String titre, imageName;
		double prix;

		if (optionalCategorie.isPresent()) {
			categorie = optionalCategorie.get();
			
	     try (Connection connection = DataBaseConnection.connectToDB();) {
	    	 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Produit WHERE idCategorie = ?");
	    	 preparedStatement.setInt(1, categorie.getId());

	    	 ResultSet resultSet = preparedStatement.executeQuery();

	         while (resultSet.next()) {
	        	 id = resultSet.getInt("id");
	        	 quantityDispo = resultSet.getInt("quantityDispo");
	        	 titre = resultSet.getString("titre");
	        	 prix = resultSet.getDouble("prix");
	        	 imageName = resultSet.getString("imageName");
	        	 
	        	 allProducts.add(new Produit(id, titre, prix, quantityDispo, imageName, categorie));
	         }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
	     
		}
		
	     return allProducts;
	}
	
	public static List<Produit> getProductsByName(String productName) {
		List<Produit> allProducts = new ArrayList<Produit>();
		
		int id, quantityDispo;
		Categorie categorie;
		String titre, imageName;
		double prix;

		try (Connection connection = DataBaseConnection.connectToDB();) {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Produit WHERE titre LIKE ?");
	    	preparedStatement.setString(1, "%" + productName + "%");

	    	ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	id = resultSet.getInt("id");
	        	quantityDispo = resultSet.getInt("quantiteDispo");
	        	titre = resultSet.getString("titre");
	        	prix = resultSet.getDouble("prix");
	        	imageName = resultSet.getString("image");
	        	int idCategorie = resultSet.getInt("idCategorie");
	        	
	        	Optional<Categorie> optionalCategorie = getCategorieById(idCategorie);
	        	 
	        	if (optionalCategorie.isPresent()) {
	        		categorie = optionalCategorie.get();
	        		allProducts.add(new Produit(id, titre, prix, quantityDispo, imageName, categorie));
	        	}
	        }
	     } catch (SQLException e) {
	    	 e.printStackTrace();
	     }
		
	     return allProducts;		
	}
}
