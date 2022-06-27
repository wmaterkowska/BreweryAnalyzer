package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvMalformedLineException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

        String fileName = "src/main/resources/breweries_usa.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        } catch (CsvMalformedLineException e){
            System.out.println("invalid data");

        }

//        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
//            String[] header = reader.readNext();
//            ArrayList<String> headers = new ArrayList<>();
//            for (int fieldNr = 0; fieldNr < header.length; fieldNr++){
//                headers.add(header[fieldNr]);
//            }
//            System.out.println(headers);
//            String[] lineInArray;
//            while ((lineInArray = reader.readNext()) != null) {
//                for(int column = 0 ; column < lineInArray.length; column++ ){
//                    System.out.print(lineInArray[column] + "|");
//                }
//                System.out.println();
//            }
//        }

        CsvProcessor headers = new CsvProcessor();
        headers.getHeadersFromCsv();
        System.out.println(headers);
        System.out.println(headers.numberOfHeaders());

        var brewery = new Brewery();
        brewery.getAddress();


    }
}