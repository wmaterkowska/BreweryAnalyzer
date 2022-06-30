package org.example;

import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Slf4j
public class Main {

    public static final String DEFAULT_BREWERIES_CSV = "src/main/resources/breweries_usa.csv";

    public static void main(String[] args) throws IOException, CsvException {


        Scanner inputPath = new Scanner(System.in);
        System.out.println("give a file name and path to a csv file you want to analyze, as a String (for example: src/main/resources/breweries_usa.csv) ");
        FileReader fileReader;
        try {
                String fileName = inputPath.next();
                fileReader = new FileReader(fileName);
        } catch (FileNotFoundException | NoSuchElementException e){
            log.error("file not found" + e );
            log.info("using default file {}", DEFAULT_BREWERIES_CSV);
            fileReader = new FileReader(DEFAULT_BREWERIES_CSV);
        }

        // reading the csv file ========================================
        CsvProcessor csvProcessor = new CsvProcessor();

        List<Brewery> breweries = csvProcessor.readCsvPutIntoBreweryList(fileReader);
        // =========================================================


        // analyzing the csv file ======================================
        BreweryAnalyzer analyzer = new BreweryAnalyzer();

        analyzer.numberOfBreweriesInEachStateMap(breweries, true); // 2a: What is the number of breweries in each state?
        analyzer.topCitiesForBreweries(breweries); // 2b:  What are the top cities for breweries?
        analyzer.numberOfBreweryWithWebsite(breweries); // 2c: How many breweries have the link to the website?
        analyzer.numberOfBreweryInDelawareWithTacos(breweries); // 2d: How many breweries located in Delaware state also offer tacos?
        analyzer.breweriesOffersWinePercentState(breweries); // 2e: What percentage of breweries in each state offers wine?

        analyzer.findDuplicates(breweries); // number of duplicates


    }
}