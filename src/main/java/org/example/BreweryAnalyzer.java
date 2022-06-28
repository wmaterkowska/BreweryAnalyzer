package org.example;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreweryAnalyzer {

    private List<String> listOfStatesWithBreweries(List<Brewery> breweries) throws FileNotFoundException {
        List<String> states = new ArrayList<>();
        for (Brewery brewery: breweries) {
            if (! (states.contains(brewery.getProvince())))
                states.add(brewery.getProvince());
        }
        return states;
    }

    private List<String> listOfCitiesWithBreweries(List<Brewery> breweries) throws FileNotFoundException {
        List<String> cities = new ArrayList<>();
        for (Brewery brewery: breweries) {
            if (! (cities.contains(brewery.getCity())))
                cities.add(brewery.getCity());
        }
        return cities;
    }

    private int getNumberOfBreweriesInState(List<Brewery> breweries, String state) {
        int counterState = 0;
        for (Brewery brewery: breweries) {
            if(brewery.getProvince().equals(state)){
                counterState += 1;
            }
        }
        return counterState;
    }

    // print number of breweries in each state [2a: What is the number of breweries in each state?]
    public Map<String, Integer> numberOfBreweriesInEachStateMap(List<Brewery> breweries) throws FileNotFoundException {
        List<String> states = listOfStatesWithBreweries(breweries);

        Map<String, Integer> numberInState = new HashMap<>();
        for (String state: states) {
            int counterState = getNumberOfBreweriesInState(breweries, state);
            numberInState.put(state, counterState);
            System.out.println("number of breweries in " + state + " : " + counterState);
        }
        return numberInState;
    }


    // [2b: What are the top cities for breweries?]
    public void topCitiesForBreweries(List<Brewery> breweries) throws FileNotFoundException {
        List<String> cities = listOfCitiesWithBreweries(breweries);

        Map<String, Integer> cityBreweriesNr = new HashMap<>();
        for (String city: cities) {
            int counterCity = 0;
            for (Brewery brewery: breweries) {
                if(brewery.getCity() == city){
                    counterCity += 1;
                }
            }
            cityBreweriesNr.put(city, counterCity);
            //System.out.println("number of breweries in " + city + " : " + counterCity);
        }

        cityBreweriesNr.values().stream().sorted();
        //TODO finish the method - print top 10 cities

    }


    //print number of breweries with link to the website [2c: How many breweries have the link to the website?]
    public void numberOfBreweryWithWebsite(List<Brewery> breweries) throws FileNotFoundException {

        int counterBreweryWithWebsite = 0;
        for (int listElement = 0; listElement < breweries.size(); listElement++){
            if (breweries.get(listElement).getWebsites() != null){
                counterBreweryWithWebsite += 1;
            }
        }
        System.out.println(counterBreweryWithWebsite);
    }

    // prints number of breweries in Delaware that offer tacos [2d: How many breweries located in Delaware state also offer tacos? ]
    public void numberOfBreweryInDelawareWithTacos(List<Brewery> breweries) throws FileNotFoundException {

        int counterBreweryDelawareTacos = 0;
        for (int listElement = 0; listElement < breweries.size(); listElement++){
            if (breweries.get(listElement).getProvince().equals("DE")  && breweries.get(listElement).getMenus().contains("tacos")){
                counterBreweryDelawareTacos += 1;
            }
        }
        System.out.println(counterBreweryDelawareTacos);
    }


    private Map<String, Integer> numberOfBreweryInStateWithWine(List<Brewery> breweries) throws FileNotFoundException {
        List<String> states = listOfStatesWithBreweries(breweries);

        Map<String, Integer> stateBreweriesWithWineNr = new HashMap<>();
        for (String state : states){
            int counterBreweryStateWine = 0;
            for (int listElement = 0; listElement < breweries.size(); listElement++){
                if (breweries.get(listElement).getProvince().equals(state)  && breweries.get(listElement).getMenus().contains("wine")){
                    counterBreweryStateWine += 1;
                }
            }
            stateBreweriesWithWineNr.put(state, counterBreweryStateWine);
            System.out.println(state + " : " + counterBreweryStateWine);
        }
        return stateBreweriesWithWineNr;
    }

    // [2e: What percentage of breweries in each state offers wine?]
    public void breweriesOffersWinePercent(List<Brewery> breweries) throws FileNotFoundException {
        Map<String, Integer> breweriesWine = numberOfBreweryInStateWithWine(breweries);

        for (String key : breweriesWine.keySet()){
            int numberInState = numberOfBreweriesInEachStateMap(breweries).get(key);
            double percentage = (breweriesWine.get(key) / numberInState * 100) ;
        }
    }

}
