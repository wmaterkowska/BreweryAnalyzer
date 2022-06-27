package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvProcessor {

    String fileName;
    ArrayList<String> headers = new ArrayList<>();

    public ArrayList<String> getHeadersFromCsv() throws IOException, CsvException {
        fileName = "src/main/resources/breweries_usa.csv";
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] header = reader.readNext();
            for (int fieldNr = 0; fieldNr < header.length; fieldNr++) {
                headers.add(header[fieldNr]);
            }
            System.out.println(headers);
        }
        return headers;
    }

    public int numberOfHeaders(){

        return headers.size();
    }

}
