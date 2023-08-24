package com.ecommerce.adminDAO;

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
import com.ecommerce.userDAO.UserDAO;

public class AdminDAO {
    // Méthode pour ajouter une nouvelle catégorie
    public static void addCategory(Categorie categorie) {
    	
    	Optional<Categorie> optCategorie = UserDAO.getCategorieByName(categorie.getCategorie());
    	
    	if (optCategorie.isEmpty()) { 	
	        try (Connection connection = DataBaseConnection.connectToDB()) {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Categorie (categorie) VALUES (?)");
	            preparedStatement.setString(1, categorie.getCategorie().toLowerCase());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
    	}
    }
    
    // Méthode pour mettre à jour une catégorie
    public static void updateCategory(Categorie categorie) {// signature de lan methode 
        try (Connection connection = DataBaseConnection.connectToDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Categorie SET categorie = ? WHERE id = ?");
            preparedStatement.setString(1, categorie.getCategorie());
            preparedStatement.setInt(2, categorie.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode pour supprimer une catégorie
    public static void deleteCategory(int categoryId) {
        try (Connection connection = DataBaseConnection.connectToDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Categorie WHERE id = ?");
            preparedStatement.setInt(1, categoryId);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//Méthode pour ajouter un produit
public static void addProduct(Produit produit) {
    try (Connection connection = DataBaseConnection.connectToDB()) {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Produit (nom, quantityDispo, prix, idCategorie) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, produit.getTitre());
        preparedStatement.setInt(2, produit.getQuantityDispo());
        preparedStatement.setDouble(3, produit.getPrix());
        preparedStatement.setInt(4, produit.getCategorie().getId());
        
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Méthode pour mettre à jour un produit
public static void updateProduct(Produit produit) {
    try (Connection connection = DataBaseConnection.connectToDB()) {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Produit SET nom = ?, quantityDispo = ?, prix = ?, idCategorie = ? WHERE id = ?");
        preparedStatement.setString(1, produit.getTitre());
        preparedStatement.setInt(2, produit.getQuantityDispo());
        preparedStatement.setDouble(3, produit.getPrix());
        preparedStatement.setInt(4, produit.getCategorie().getId());
        preparedStatement.setInt(5, produit.getId());
        
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Méthode pour supprimer un produit
public static void deleteProduct(int productId) {
    try (Connection connection = DataBaseConnection.connectToDB()) {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Produit WHERE id = ?");
        preparedStatement.setInt(1, productId);
        
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Méthode pour mettre à jour la quantité d'un produit
public static void updateProductQuantity(int productId, int newQuantity) {
    try (Connection connection = DataBaseConnection.connectToDB()) {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Produit SET quantityDispo = ? WHERE id = ?");
        preparedStatement.setInt(1, newQuantity);
        preparedStatement.setInt(2, productId);
        
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
}








// Méthode pour récupérer la liste des commandes passées par les utilisateurs (simplifié ici)
  public static List<String> getAllOrders() {
    List<String> orders = new ArrayList<>(); 
    
    try (Connection connection = DataBaseConnection.connectToDB()) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Commande");

        ResultSet resultSet = preparedStatement.executeQuery(); // film 

        while (resultSet.next()) {
            String orderDetails = resultSet.getString("details");
            orders.add(orderDetails);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return orders;
}
}
