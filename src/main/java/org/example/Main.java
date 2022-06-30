package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

        Instant start = Instant.now();
//        Scanner inputPath = new Scanner(System.in);
//        System.out.println("give a filename and path to it as a String (for example: src/main/resources/breweries_usa.csv) ");
//        String fileName = inputPath.next();

        String fileName = "src/main/resources/breweries_usa.csv";

        // reading the file========================================
        CsvProcessor fileReader = new CsvProcessor();

        List<Brewery> breweries = fileReader.readCsvPutIntoBreweryList(fileName);

        Instant current = Instant.now();
        long timeElapsed = Duration.between(start, current).toMillis();
        System.out.println(timeElapsed);
        // =========================================================


        // analyzing the file ======================================
        BreweryAnalyzer analyzer = new BreweryAnalyzer();

        analyzer.numberOfBreweriesInEachStateMap(breweries); // 2a: What is the number of breweries in each state?
        analyzer.topCitiesForBreweries(breweries); // 2b:  What are the top cities for breweries?
        analyzer.numberOfBreweryWithWebsite(breweries); // 2c: How many breweries have the link to the website?
        analyzer.numberOfBreweryInDelawareWithTacos(breweries); // 2d: How many breweries located in Delaware state also offer tacos?

        Instant end = Instant.now();
        timeElapsed = Duration.between(current, end).toMillis();
        System.out.println(timeElapsed);

        analyzer.breweriesOffersWinePercentState(breweries); // 2e: What percentage of breweries in each state offers wine?

        Instant finish = Instant.now();
        timeElapsed = Duration.between(end, finish).toMillis();
        System.out.println(timeElapsed);

        analyzer.findDuplicates(breweries); // number of dupliates

        timeElapsed = Duration.between(finish, Instant.now()).toMillis();
        System.out.println(timeElapsed);

    }
}