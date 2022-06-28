package org.example;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

//        Scanner inputPath = new Scanner(System.in);
//        System.out.println("give a filename and path to it as a String (for example: src/main/resources/breweries_usa.csv) ");
//        String fileName = inputPath.next();

        String fileName = "src/main/resources/breweries_usa.csv";

//        // read whole file at once and print it to terminal
//        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
//            List<String[]> r = reader.readAll();
//            r.forEach(x -> System.out.println(Arrays.toString(x)));
//        } catch (CsvMalformedLineException e){
//            System.out.println("invalid data");
//
//        }


//        // read file line by line and print it to terminal
//        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
//            //find all headers
////            String[] header = reader.readNext();
////            ArrayList<String> headers = new ArrayList<>();
////            for (int fieldNr = 0; fieldNr < header.length; fieldNr++){
////                headers.add(header[fieldNr]);
////            }
////            System.out.println(headers);
//
//            String[] lineInArray;
//            while ((lineInArray = reader.readNext()) != null) {
//                for(int column = 0 ; column < lineInArray.length; column++ ){
//                    System.out.print(lineInArray[column] + "|");
//                }
//                System.out.println();
//            }
//        } catch(CsvMalformedLineException e) {
//            System.out.println("invalid data");
//            System.out.println(e);
//        }


        // test for CsvProcessor headers method
        CsvProcessor headers = new CsvProcessor();
        System.out.println(headers.numberOfHeaders(fileName));
        System.out.println(headers.getHeadersFromCsv(fileName));
        System.out.println(headers.numberOfHeaders(fileName));

        // test for CSvProcessor reading methods
        CsvProcessor fileReader = new CsvProcessor();
        fileReader.readCsvToList(fileName);
        fileReader.readCsvAndPrint(fileName);

        CsvProcessor fileReaderLines = new CsvProcessor();
        fileReaderLines.readCsvLineByLineAndPrint(fileName);


        var brewery = new Brewery();

        try{
            List<Brewery> breweries = new CsvToBeanBuilder(new FileReader(fileName)).withType(Brewery.class).build().parse();
            breweries.forEach(System.out::println);
        } catch (RuntimeException e){
            System.out.println("invalid data " + e);
        }

        List<Brewery> breweries = new CsvToBeanBuilder(new FileReader(fileName)).withType(Brewery.class).build().parse();

        // BreweryAnalyzer test
        BreweryAnalyzer analyzer = new BreweryAnalyzer();

        analyzer.numberOfBreweryWithWebsite(breweries);
        analyzer.numberOfBreweryInDelawareWithTacos(breweries);



    }
}