package Scrapping;


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

public class ImageScraper {
    public static void main(String[] args) {
        String url = "https://www.farfetch.com/ie/shopping/men/palm-angels-logo-print-organic-cotton-t-shirt-item-19256863.aspx?q=PMAA066S23JER0021084&ffref=recentSearch;RecentSearch;PMAA066S23JER0021084;1";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements imageElements = doc.getElementsByClass("ltr-1w2up3s");

            System.out.println("Number of image elements: " + imageElements.size());

            int imageIndex = 0;
            for (Element element : imageElements) {
                if (element.hasAttr("src")) {
                    String imageUrl = element.attr("src");
                    System.out.println("Image URL: " + imageUrl);
                    String imageName = "custom_image_name" + (imageIndex > 0 ? imageIndex : "");  // Provide your custom image name here
                    downloadImage(imageUrl, imageName);
                    imageIndex++;
                }
            }

            System.out.println("Images downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}