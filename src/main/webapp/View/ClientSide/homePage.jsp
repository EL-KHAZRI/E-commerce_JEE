<%@page import="com.ecommerce.Entity.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
		List<Produit> productsList =(List<Produit>) request.getAttribute("ProductsList");
    	String productName = (String) request.getAttribute("productName");
	%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>My Online Store</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!-- FontAwsome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<!-- Main CSS -->
	<link rel="stylesheet" href="View/ClientSide/homePage.css" />
</head>
<body>
	
	<header class="header bg-light py-3">
        <div class="container main_header_container">
            <img src="images/websiteImages/logo.png" />
            
            <form action="homepage" method="get">
            	<input class="form-control" name="productName" placeholder="Product Name" value="<% if (productName != null) { out.print(productName); } %>" />
            	<button class="btn text-white ml-1" style="background: linear-gradient(to bottom, #800080, #4b0082);">
            		<i class="fa-solid fa-magnifying-glass"></i>
            	</button>
            </form>
            
            <div class="right_btns">
            	<a href="">
            		<i class="fa-solid fa-cart-shopping fa-xl" style="color: #800080;"></i>
            	</a>
            	<a href="">
            		<i class="fa-solid fa-user fa-xl" style="color: #800080;"></i>
            	</a>
            </div>
        </div>
    </header>
    
    
    <nav class="menu py-3" style="background: linear-gradient(to bottom, #800080, #4b0082);">
        <div class="container">
            <div id="menu-links" class="menu-links">
                <a href="#" class="text-white">Home</a>
                <a href="#" class="text-white">Products</a>
                <a href="#" class="text-white">Categories</a>
                <a href="#" class="text-white">About Us</a>
                <a href="#" class="text-white">Contact</a>
            </div>
        </div>
    </nav>
    
    <section class="products py-5">
        <div class="container">
            <div class="row" style="row-gap: 1rem;">
            <% for(Produit product : productsList) { %>
                <!-- Product cards go here -->
                <div class="col-md-4">
                    <div class="product-card border p-3">
                        <img id="product_image" src="images/products/<%=product.getImageName() %>.webp" alt="Product 1" class="img-fluid mb-3">
                        <p id="category_name"><%=product.getCategorie().getCategorie() %></p>
                        <h4 id="max_letters_title" title="<%=product.getTitre() %>" style="font-size: 1.25rem;"><%=product.getTitre() %></h4>
                        <p>$<%=product.getPrix() %></p>
                        <input type="number" value="1" min="1" max="<%=product.getQuantityDispo() %>" class="form-control mb-2">
                        <a href="" class="btn text-white w-100" style="background: linear-gradient(to bottom, #800080, #4b0082);">
                        	Add to Cart
                        </a>
                    </div>
                </div>
                <!-- Repeat for other products -->
               <% } %>
            </div>
        </div>
    </section>

    <!-- User Modal -->
    <div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userModalLabel">User Login/Logout</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Add your login/logout form here -->
                </div>
            </div>
        </div>
    </div>
</body>
</html>