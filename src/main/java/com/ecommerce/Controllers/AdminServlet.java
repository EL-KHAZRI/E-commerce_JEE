package com.ecommerce.Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.Entity.Categorie;
import com.ecommerce.Entity.Produit;
import com.ecommerce.adminDAO.AdminDAO;
import com.ecommerce.userDAO.UserDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Categorie> categoriesList = UserDAO.getAllCategories();
        List<Produit> produitsList = UserDAO.getAllProducts();
        
        request.setAttribute("categories", categoriesList);
        request.setAttribute("produits", produitsList);
        
        request.getRequestDispatcher("View/AdminSide/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Post action
    	
    	String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("addCategory")) {
                String categoryName = request.getParameter("categoryName");
                AdminDAO.addCategory(new Categorie(categoryName));
            } else if (action.equals("addProduct")) {
                String productName = request.getParameter("productName");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                
                // Produit produit = new Produit(new Categorie(categoryId, ""),productName, quantity, price);
                //AdminDAO.addProduct(produit);
            } else if (action.equals("updateProductQuantity")) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
                AdminDAO.updateProductQuantity(productId, newQuantity);
            } else if (action.equals("deleteProduct")) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                AdminDAO.deleteProduct(productId);
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/admin");
    }
    	
    }

