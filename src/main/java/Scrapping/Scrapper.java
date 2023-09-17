/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package Scrapping;

import DAO.ProductDAO;
import DTO.ProductDTO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hkhat
 */
public class Scrapper {

    public static List<String> scrapeData(String url, String cssClass) {
        List<String> dataList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass(cssClass);
            if (!elements.isEmpty()) {
                for (Element element : elements) {
                    dataList.add(element.text());
                }
            } else {
                System.out.println("No elements with CSS class '" + cssClass + "' found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch data. " + e.getMessage());
        }
        return dataList;
    }

    public static void addPrint(String url) {
        ProductDTO product = new ProductDTO(null, null, null, null, null, null, 0.0);

        //class for Id,Name,Description
        String INDClass = "ltr-4y8w0i-Body";
        //class for Price
        String PriceClass = "ltr-194u1uv-Heading";
        //class for categoryandBrand
        String CategoryClass = "ltr-1h8w6zn-Footnote";
        List<String> resultIND = scrapeData(url, INDClass);
        List<String> resultPrice = scrapeData(url, PriceClass);
        String price = resultPrice.get(0);
        //removing euro symbol and converting to double
        String numbersOnly = price.replaceAll("[^0-9.]", "");
        double convertedPrice = Double.parseDouble(numbersOnly);
        List<String> CategoryBrand = scrapeData(url, CategoryClass);

        //getting & setting id 
        String productId = null;
        for (String item : resultIND) {
            if (item.startsWith("Brand style ID")) {
                // Assuming the product ID follows the "Brand style ID" in the same format
                // You may need to adjust the index based on your specific data structure
                productId = item.substring("Brand style ID".length()).trim();
                break; // Stop the loop once you find the product ID
            }
        }

        if (productId != null) {
            product.setId(productId);
        } else {
            product.setId("Couldnt Retrieve ID");
        }

        //setting the values
   
        product.setName(resultIND.get(0));
        product.setDescription(resultIND.get(1));
        product.setBrand(CategoryBrand.get(1));
        product.setCategory(CategoryBrand.get(3));
        product.setMrp(convertedPrice);

        System.out.println(product.toString());
        //printing the values
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Brand: " + product.getBrand());
        System.out.println("Product Tags: " + product.getTags());
        System.out.println("Product Description: " + product.getDescription());
        System.out.println("Product Category: " + product.getCategory());
        System.out.println("Product MRP: " + product.getMrp());
        System.out.println("-------------------------------------------------------------------------");
        System.out.print(String.join("\n", resultIND));

        System.out.println("Adding to Database");
        ProductDAO productDAO = new ProductDAO();
        //productDAO.addProduct(product);

    }

    public static String[] readUrlsFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder urlList = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Append each URL to the StringBuilder with a newline separator
                urlList.append(line).append("\n");
            }

            reader.close();

            // Split the concatenated URLs into an array based on newline separator
            return urlList.toString().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0]; // Return an empty array if there's an error
        }
    }

    public static void main(String[] args) {

        String[] urls = readUrlsFromFile("urls.txt");
        for (String url : urls) {
            addPrint(url);
        }

    }
}
