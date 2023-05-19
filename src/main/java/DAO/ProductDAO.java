/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author hkhat
 */
import DTO.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ProductDAO {
    private final ConnectionDAO connectionDAO;

    public ProductDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public void addProduct(ProductDTO product) {
        Connection connection = null;
        try {
            connection = connectionDAO.getConnection();

            String query = "INSERT INTO products (id, Name, Brand, Tags, Description, Category, MRP) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getBrand());
            statement.setString(4, product.getTags());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getCategory());
            statement.setDouble(7, product.getMrp());

            statement.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        } finally {
            connectionDAO.freeConnection(connection);
        }
    }

    public void addRandomProduct() {
        String randomId = UUID.randomUUID().toString();
        String randomName = "Random Product";
        String randomBrand = "Random Brand";
        String randomTags = "Random Tags";
        String randomDescription = "Random Description";
        String randomCategory = "Random Category";
        double randomMrp = 9.99;

        ProductDTO randomProduct = new ProductDTO(randomId, randomName, randomBrand, randomTags, randomDescription, randomCategory, randomMrp);
        addProduct(randomProduct);
    }
    
    //code for testing
//        public static void main(String[] args) {
//        ProductDAO productDAO = new ProductDAO();
//        productDAO.addRandomProduct();
//    }
}
