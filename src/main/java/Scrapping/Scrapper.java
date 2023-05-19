/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package Scrapping;

import DTO.ProductDTO;
import java.io.IOException;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import java.util.ArrayList;

/**
 *
 * @author hkhat
 */
public class Scrapper {

    public static List<String> scrapeIND(String url, String cssClass) { //IND is Id,Name,Description
        List<String> dataList = new ArrayList<>();
        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect(url).get();

            // Find all elements with the specified CSS class
            Elements elements = document.getElementsByClass(cssClass);

            if (!elements.isEmpty()) {
                // Extract the text from each element and add it to the list
                for (Element element : elements) {
                    String data = element.text();
                    dataList.add(data);
                }
            } else {
                System.out.println("No elements with CSS class '" + cssClass + "' found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch data. " + e.getMessage());
        }
        return dataList;
    }

    public static List<String> scrapeBrand(String url, String cssClass) { //IND is Id,Name,Description
        List<String> dataList = new ArrayList<>();
        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect(url).get();

            // Find all elements with the specified CSS class
            Elements elements = document.getElementsByClass(cssClass);

            if (!elements.isEmpty()) {
                // Extract the text from each element and add it to the list
                for (Element element : elements) {
                    String data = element.text();
                    dataList.add(data);
                }
            } else {
                System.out.println("No elements with CSS class '" + cssClass + "' found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch data. " + e.getMessage());
        }
        return dataList;
    }

    public static List<String> scrapePrice(String url, String cssClass) { //IND is Id,Name,Description
        List<String> dataList = new ArrayList<>();
        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect(url).get();

            // Find all elements with the specified CSS class
            Elements elements = document.getElementsByClass(cssClass);

            if (!elements.isEmpty()) {
                // Extract the text from each element and add it to the list
                for (Element element : elements) {
                    String data = element.text();
                    dataList.add(data);
                }
            } else {
                System.out.println("No elements with CSS class '" + cssClass + "' found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch data. " + e.getMessage());
        }
        return dataList;
    }
    
        public static List<String> scrapeCategory(String url, String cssClass) { //IND is Id,Name,Description
        List<String> dataList = new ArrayList<>();
        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect(url).get();

            // Find all elements with the specified CSS class
            Elements elements = document.getElementsByClass(cssClass);

            if (!elements.isEmpty()) {
                // Extract the text from each element and add it to the list
                for (Element element : elements) {
                    String data = element.text();
                    dataList.add(data);
                }
            } else {
                System.out.println("No elements with CSS class '" + cssClass + "' found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to fetch data. " + e.getMessage());
        }
        return dataList;
    }

    public static void main(String[] args) {
        // Create a new ProductDTO object with all null values
        ProductDTO product = new ProductDTO(null, null, null, null, null, null, 0.0);

        //url of the product
        String url = "https://www.farfetch.com/ie/shopping/men/palm-angels-logo-print-organic-cotton-t-shirt-item-19256863.aspx?q=PMAA066S23JER0021084&ffref=recentSearch;RecentSearch;PMAA066S23JER0021084;1";

        //for IND
        String INDClass = "ltr-4y8w0i-Body";

        List<String> resultIND = scrapeIND(url, INDClass); //IND is Id,Name,Description

        //for brand
        String BrandClass = "ltr-8gbn9h-Heading-HeadingBold";

        List<String> resultBrand = scrapeIND(url, BrandClass);

        //for price
        String PriceClass = "ltr-194u1uv-Heading";
        List<String> resultPrice = scrapePrice(url, PriceClass);
        //convert to result to double 
        String price = resultPrice.get(1); //set to one as FarFetch has a same element with value null with same heading
        String numbersOnly = price.replaceAll("[^0-9.]", ""); // Remove non-numeric characters except decimal point
        double convertedPrice = Double.parseDouble(numbersOnly);
        
        //for category
                //for brand
        String CategoryClass = "ltr-1h8w6zn-Footnote";
        List<String> CategoryBrand = scrapeCategory(url, CategoryClass);


        // Print the retrieved data
        for (String data : CategoryBrand) {
            System.out.println(data);
        }
        //set the product elements their value
        product.setId(resultIND.get(11));
        product.setName(resultIND.get(0));
        product.setDescription(resultIND.get(1));
        product.setBrand(resultBrand.get(0));
        product.setMrp(convertedPrice);

        // Print the retrieved data from the ProductDTO object
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Brand: " + product.getBrand());
        System.out.println("Product Tags: " + product.getTags());
        System.out.println("Product Description: " + product.getDescription());
        System.out.println("Product Category: " + product.getCategory());
        System.out.println("Product MRP: " + product.getMrp());
    }
}
