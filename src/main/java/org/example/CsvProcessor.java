package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvMalformedLineException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CsvProcessor {

    // retrieve the headers from csv file
    public ArrayList<String> getHeadersFromCsv(String filename) throws IOException, CsvException {
        ArrayList<String> headers = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] header = reader.readNext();
            for (int fieldNr = 0; fieldNr < header.length; fieldNr++) {
                headers.add(header[fieldNr]);
            }
        }
        return headers;
    }

    // return number of headers
    public int numberOfHeaders(String filename) throws IOException, CsvException {
        ArrayList<String> headers = getHeadersFromCsv(filename);
        int numberOfHeaders = headers.size();
        return numberOfHeaders;
        //return headers.size();
    }


    // read Csv file and print it to the terminal
    public void readCsvAndPrint(String filename){
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> r = reader.readAll();
            reader.close();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        } catch (CsvMalformedLineException | FileNotFoundException e){
            log.error("invalid data from CSV line", e);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    // read Csv file and put it to the list
    public List<String[]> readCsvToList(String filename){
            List<String[]> r = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            r = reader.readAll();
            reader.close();
            r.forEach(x -> System.out.println(Arrays.toString(x)));

        } catch (CsvMalformedLineException | FileNotFoundException e){
            log.error("invalid data from CSV line", e);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    // read file line by line and print it to terminal
    public void readCsvLineByLineAndPrint(String filename){
        // read file line by line and print it to terminal
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {

            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                for(int column = 0 ; column < lineInArray.length; column++ ){
                    System.out.print(lineInArray[column] + "|");
                }
                System.out.println();
            }
        } catch(CsvMalformedLineException e) {
            log.error("invalid data from CSV line", e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    // read file line by line and put it to list
    public List<String[]> readCsvLineByLineToList(String filename){
        List<String[]> listBreweries = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                for(int column = 0 ; column < lineInArray.length; column++ ){
                    listBreweries.add(lineInArray);
                }
            }
        } catch(CsvMalformedLineException e) {
            log.error("invalid data from CSV line", e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return listBreweries;
    }

    // read CSV file into object - Brewery list
    public List<Brewery> readCsvPutIntoBreweryList(String filename) throws FileNotFoundException {

        List<Brewery> breweries = new CsvToBeanBuilder(new FileReader(filename))
                .withType(Brewery.class).withSkipLines(1).build().parse();
        return breweries;

    }

//    public void test(String filename) throws IOException, CsvValidationException {
//
//        BeanListProcessor<Brewery> rowProcessor = new BeanListProcessor<Brewery>(Brewery.class);
//
//        CsvParserSettings parserSettings = new CsvParserSettings();
//        parserSettings.setRowProcessor(rowProcessor);
//        parserSettings.setHeaderExtractionEnabled(true);
//        parserSettings.setMaxCharsPerColumn(100000);
//
//        CsvParser parser = new CsvParser(parserSettings);
//        parser.parse(new FileReader(Paths.get(filename).toFile()));
//
//// The BeanListProcessor provides a list of objects extracted from the input.
//        List<Brewery> beans = rowProcessor.getBeans();
//        System.out.println(beans);
//
//    }

}
