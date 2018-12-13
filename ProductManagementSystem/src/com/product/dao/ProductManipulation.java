package com.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.product.model.Product;

public class ProductManipulation {
	ProductDao dbconnection = null;
	List<Product> productList = null;
	public ProductManipulation() {
		dbconnection = new ProductDao();
	}
	
	public List<Product> getAllProducts(){
		Statement statement = null;
		productList = new ArrayList<Product>();
		String query = "SELECT * from producttable;";
		try {
			statement = ProductDao.connection.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				Product p = new Product();
				p.setProductId(resultset.getString(1));
				p.setProductName(resultset.getString(2));
				p.setProductPrice(new Double(resultset.getString(3)));
				p.setProductDesc(resultset.getString(4));
				p.setQuantity(new Integer(resultset.getString(5)));
				productList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	/*public List<Product> addProducttoDb(Product p){
			String addEmployeeQuery = "INSERT INTO " + ProductDao.productTable + " values(\"" + p.getProductId() + "\",\""
					+ p.getProductName() + "\",\"" + p.getProductPrice() + "\",\""+p.getProductDesc()+"\",\"" + p.getQuantity() +"\")";
			Statement createStatement = null;
			int created = 0;
			try {
				createStatement = ProductDao.connection.createStatement();
				created = createStatement.executeUpdate(addEmployeeQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			productList = getAllProducts();
			return productList;
	}*/
	
	public int addProducttoDb(Product p){
		String addEmployeeQuery = "INSERT INTO " + ProductDao.productTable + " values(\"" + p.getProductId() + "\",\""
				+ p.getProductName() + "\",\"" + p.getProductPrice() + "\",\""+p.getProductDesc()+"\",\"" + p.getQuantity() +"\")";
		Statement createStatement = null;
		int created = 0;
		try {
			createStatement = ProductDao.connection.createStatement();
			created = createStatement.executeUpdate(addEmployeeQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//productList = getAllProducts();
		return created;
}
	
	public List<Product> deleteProductFromDB(String productID){
		String deleteQuery = "DELETE FROM " + ProductDao.productTable + " WHERE Productid=\"" + productID
				+ "\"";
		System.out.println("update query : " +deleteQuery );
		Statement createStmt = null;
		try {
			createStmt = ProductDao.connection.createStatement();
			createStmt.executeUpdate(deleteQuery);
			productList = getAllProducts();
		}catch(Exception e) {
			
		}
		return productList;
	}
	
	public List<Product> updateProducttoDb(Product product){
		System.out.println("producte vale : " + product);
		String updateQuery = "UPDATE " + ProductDao.productTable + " SET productname = \""+ product.getProductName() +"\", productprice = \"" + product.getProductPrice() + "\",productdescription=\""+ product.getProductDesc() + "\",productquantity = \""+ product.getQuantity() +"\"" + " WHERE Productid=\"" + product.getProductId()+ "\"";
		System.out.println("update query : " +updateQuery );
		Statement createStmt = null;
		try {
			createStmt = ProductDao.connection.createStatement();
			createStmt.executeUpdate(updateQuery);
			productList = getAllProducts();
		}catch(Exception e) {
			
		}
		return productList;
	}
}
