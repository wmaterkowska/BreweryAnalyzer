package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

//        Scanner inputPath = new Scanner(System.in);
//        System.out.println("give a filename and path to it as a String (for example: src/main/resources/breweries_usa.csv) ");
//        String fileName = inputPath.next();

        String fileName = "src/main/resources/breweries_usa_whole.csv";

//        // test for CsvProcessor headers method
//        CsvProcessor headers = new CsvProcessor();
//        System.out.println(headers.numberOfHeaders(fileName));
//        System.out.println(headers.getHeadersFromCsv(fileName));
//        System.out.println(headers.numberOfHeaders(fileName));

        // test for CSvProcessor reading methods
        CsvProcessor fileReader = new CsvProcessor();
//        fileReader.readCsvToList(fileName);
//        fileReader.readCsvAndPrint(fileName);
//        fileReader.readCsvLineByLineToList(fileName);
//        fileReader.readCsvLineByLineAndPrint(fileName);
//        fileReader.test(fileName);

        List<Brewery> breweries = fileReader.readCsvPutIntoBreweryList(fileName);


//        CsvProcessor processor = new CsvProcessor();
//        List<Brewery> breweries = new ArrayList<>();
//        breweries = processor.readCsvPutIntoBreweryList(fileName);

//        try{
//            List<Brewery> breweries = new CsvToBeanBuilder(new FileReader(fileName)).withType(Brewery.class).build().parse();
//            breweries.forEach(System.out::println);
//        } catch (RuntimeException e){
//            System.out.println("invalid data " + e);
//        }

//        List<Brewery> breweries = new CsvToBeanBuilder(new FileReader(fileName)).withType(Brewery.class).withSkipLines(1).build().parse();
//        breweries.forEach(System.out::println);
//
        // BreweryAnalyzer test
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        analyzer.numberOfBreweriesInEachStateMap(breweries);
        System.out.println("number of breweries in cities: " + analyzer.numberOfBreweriesInEachStateMap(breweries));
        analyzer.topCitiesForBreweries(breweries);
        analyzer.numberOfBreweryWithWebsite(breweries);
        analyzer.numberOfBreweryInDelawareWithTacos(breweries);
        analyzer.breweriesOffersWinePercentState(breweries);


    }
}