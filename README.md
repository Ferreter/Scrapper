
# Farfetch Data Scraper

This Java program demonstrates how to scrape product data from the Farfetch website. It retrieves various product details, such as product name, brand, description, category, price, and more, and then adds the scraped data to a database.

## How it Works

### Scrapping

The core functionality of the program is in the `Scrapper` class. Here's how it works:

1. **Scraping Data**: The `scrapeData` method is used to scrape data from a specified URL by fetching the HTML content and selecting elements with a given CSS class.

   ```java
   public static List<String> scrapeData(String url, String cssClass)
   ```

   - `url`: The URL of the product page to scrape.
   - `cssClass`: The CSS class of the elements to select and scrape.

2. **Removing Duplicates**: The `removeDuplicates` method is used to remove duplicate elements from a list.

   ```java
   public static List<String> removeDuplicates(List<String> list)
   ```

3. **Converting Price**: The `convertPriceStringToDouble` method is used to convert a price string to a double, handling cases where the price is in decimal format with different separators.

   ```java
   public static double convertPriceStringToDouble(String priceString)
   ```

### Adding Data to Database

After scraping the data, the program creates a `ProductDTO` object to store the product details and adds the data to a database using a `ProductDAO`. The following product information is collected:

- **Product ID**: Extracted from the "Brand style ID" field on the product page.
- **Product Name**: Extracted from the product name element on the page.
- **Product Brand**: Extracted from the brand information.
- **Product Tags**: A combination of the brand and category information.
- **Product Description**: Extracted from the product description section.
- **Product Category**: Extracted from the category information.
- **Product MRP (Maximum Retail Price)**: The converted price of the product.

### Reading URLs from File

The program reads a list of product URLs from a text file named `urls.txt`. Each URL should be on a separate line. It then iterates through the URLs and scrapes data for each product.


## Usage

1. Create a text file named `urls.txt` and add the product URLs you want to scrape, with each URL on a separate line.

2. Run the program by executing the `main` method in the `Scrapper` class.

3. The program will scrape data from each URL and display the product details in the console.

4. The scraped data will also be added to the database using the `ProductDAO` class.

## Notes

- The program uses the JSoup library to fetch and parse HTML content from web pages.

- It handles different formats of product prices, including cases where the decimal separator is a dot or a comma.

- You can customize the program to add more product details or database integration as needed.

- Ensure that you have the necessary dependencies and configurations for connecting to the database if you plan to use the database functionality.

## Why I Made This App

I created this app with the intention of helping anyone working on their college project or research that requires a sizable dataset. When I was in my final year, I realized the importance of having access to a diverse and extensive dataset for my project. Unfortunately, I faced challenges in obtaining such data. This program is designed to automate the process of data collection from the Farfetch website, making it easier for students and researchers to gather product information for their projects. By providing a tool to collect product data, I hope to make the process more efficient and accessible to others in a similar situation.

# Farfetch Image Scraper

This Java program is designed to scrape images from Farfetch product pages. Please note that it has been observed that Farfetch may block additional requests beyond the first two URLs. Therefore, it is recommended to use this script for the first two URLs only.

## Prerequisites

Before running the script, make sure you have the following:

- Java Development Kit (JDK) installed on your system.
- Jsoup library added to your project. You can download it from [Jsoup's official website](https://jsoup.org/download).

## Usage

1. Clone or download this repository to your local machine.

2. Create a text file named `urls.txt` in the same directory as the Java source code (`ImageScraper.java`). Add the URLs of the Farfetch product pages you want to scrape, one URL per line.

3. Compile and run the `ImageScraper.java` file using your Java development environment or command line.

   ```shell
   javac ImageScraper.java
   java ImageScraper
   ```
The script will connect to each URL, scrape images from the specified class (ltr-1w2up3s), and save them in the /images/ directory with custom image names.

The images will be downloaded and saved in the /images/ directory.

Notes
- The script may not work for more than two URLs due to potential blocking by Farfetch. Use it with caution and consider scraping only a small number of pages at a time.

- You can customize the image naming logic by modifying the imageName variable in the code.

- Ensure you have proper permissions and rights to scrape content from websites. Respect the website's terms of service and scraping guidelines.

Happy scraping!
