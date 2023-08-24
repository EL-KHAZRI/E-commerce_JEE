package com.ecommerce.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.Entity.Produit;
import com.ecommerce.userDAO.UserDAO;

/**
 * Servlet implementation class ShowProducts
 */
@WebServlet("/homepage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Produit> productList = new ArrayList<Produit>();
		
		System.out.println("category: " + request.getParameter("category"));
		System.out.println("productName: " + request.getParameter("productName"));
		
		if (request.getParameter("productName") != null || request.getParameter("category") != null) {
			if (request.getParameter("productName") != null) {
				String productName = request.getParameter("productName");
				request.setAttribute("productName", productName);
				request.removeAttribute("category");
				productList.addAll(UserDAO.getProductsByName(productName));
			}
			
			if (request.getParameter("category") != null) {
				String category = request.getParameter("category");
				request.setAttribute("category", category);
				request.removeAttribute("productName");
				productList.addAll(UserDAO.getProductsByCategorie(category));
			}
		}
		
		if (request.getParameter("productName") == null && request.getParameter("category") == null) {
			request.removeAttribute("category");
			request.removeAttribute("productName");
			productList.addAll(UserDAO.getAllProducts());
		}
		
		request.setAttribute("ProductsList", productList);
		request.getRequestDispatcher("View/ClientSide/homePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
