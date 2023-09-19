package Scrapping;

import static Scrapping.Scrapper.addPrint;
import static Scrapping.Scrapper.readUrlsFromFile;
import java.io.BufferedReader;
import java.io.FileReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//Image Scrapper works but only works for the 
//first 2 url as farfetch blocks any other result coming in so it is recommended to remove more than 2 url

public class ImageScraper {

    private static void downloadImage(String imageUrl, String imageName) {
        try {
            String fileName = imageName + ".jpg";
            URL url = new URL(imageUrl);
            Path imagePath = Paths.get("images", fileName);  // Save in the /images/ directory
            Files.copy(url.openStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Downloaded image: " + fileName);
        } catch (IOException e) {
            System.err.println("Error downloading image: " + imageUrl);
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws InterruptedException {

        String[] urls = readUrlsFromFile("urls.txt");
        int index = 0;
        for (String url : urls) {
            try {
                index = index +1;
                Document doc = Jsoup.connect(url).get();
                Elements imageElements = doc.getElementsByClass("ltr-1w2up3s");
                System.out.println("----------------------");
                System.out.println("Number of image elements: " + imageElements.size());
                System.out.println("Note the Elements found arent required to have a url so they will not be downloaded ");
                for (Element element : imageElements) {
                    if (element.hasAttr("src")) {
                        Thread.sleep(100);
                        String imageUrl = element.attr("src");
                        System.out.println("Image URL: " + imageUrl);
                        String imageName = "custom_image_name" + index;  // Provide your custom image name here
                        downloadImage(imageUrl, imageName);
                        index = index +1;
                    }
                }

                System.out.println("Images downloaded successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }

    }

}
