/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scrapping;
import DTO.ProductDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hkhat
 */
public class ScrappingTest {
    
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

    public static void main(String[] args) {
        ProductDTO product = new ProductDTO(null, null, null, null, null, null, 0.0);
        String url = "https://www.farfetch.com/ie/shopping/men/palm-angels-logo-print-organic-cotton-t-shirt-item-19256863.aspx?q=PMAA066S23JER0021084&ffref=recentSearch;RecentSearch;PMAA066S23JER0021084;1";
        String INDClass = "ltr-4y8w0i-Body";
        List<String> resultIND = scrapeData(url, INDClass);
        String PriceClass = "ltr-194u1uv-Heading";
        List<String> resultPrice = scrapeData(url, PriceClass);
        String price = resultPrice.get(1);
        String numbersOnly = price.replaceAll("[^0-9.]", "");
        double convertedPrice = Double.parseDouble(numbersOnly);
        String CategoryClass = "ltr-1h8w6zn-Footnote";
        List<String> CategoryBrand = scrapeData(url, CategoryClass);
        for (String data : CategoryBrand) {
            System.out.println(data);
        }
        product.setId(resultIND.get(11));
        product.setName(resultIND.get(0));
        product.setDescription(resultIND.get(1));
        product.setBrand(CategoryBrand.get(1));
        product.setCategory(CategoryBrand.get(3));
        product.setMrp(convertedPrice);
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
