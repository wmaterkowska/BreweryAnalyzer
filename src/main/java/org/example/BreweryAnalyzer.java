package org.example;

import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

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
            if ( !(cities.contains(brewery.getCity())))
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

    // returns map with number of breweries in each state [2a: What is the number of breweries in each state?]
    public Map<String, Integer> numberOfBreweriesInEachStateMap(List<Brewery> breweries) throws FileNotFoundException {
        List<String> states = listOfStatesWithBreweries(breweries);

        Map<String, Integer> numberInState = new HashMap<>();
        for (String state: states) {
            int counterState = getNumberOfBreweriesInState(breweries, state);
            numberInState.put(state, counterState);
            //System.out.println("number of breweries in " + state + " : " + counterState);
        }
        return numberInState;
    }

    // return map with 10 top cities for breweries [2b: What are the top cities for breweries?]
    public LinkedHashMap<String, Integer> topCitiesForBreweries(List<Brewery> breweries) throws FileNotFoundException {
        List<String> cities = listOfCitiesWithBreweries(breweries);

        Map<String, Integer> cityBreweriesNumber = new HashMap<>();
        for (String city: cities) {
            int counterCity = 0;
            for (Brewery brewery: breweries) {
                if(brewery.getCity().equals(city)){
                    counterCity += 1;
                }
            }
            cityBreweriesNumber.put(city, counterCity);
            //System.out.println("number of breweries in " + city + " : " + counterCity);
        }

        LinkedHashMap<String, Integer> topThreeCitiesWithBreweries =
                cityBreweriesNumber.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(3)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//        ArrayList<Map.Entry<String, Integer>> sortedCitiesBreweries = new ArrayList<>(cityBreweriesNumber.entrySet());
//        sortedCitiesBreweries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
//
//        Map<String, Integer> topThreeCitiesWithBreweries = new LinkedHashMap<>();
//        int total = 0;
//        for (Map.Entry<String, Integer> entry: sortedCitiesBreweries){
//            topThreeCitiesWithBreweries.put(entry.getKey(), entry.getValue());
//            if (++total >= 3){
//                break;
//            }
//        }

        System.out.println("top cities with breweries: " + topThreeCitiesWithBreweries);
        return topThreeCitiesWithBreweries;
    }

    // returns number of breweries with link to the website [2c: How many breweries have the link to the website?]
    public int numberOfBreweryWithWebsite(List<Brewery> breweries) throws FileNotFoundException {

        int counterBreweryWithWebsite = 0;
        for (int listElement = 0; listElement < breweries.size(); listElement++){
            if ( breweries.get(listElement).getWebsites() != null ){
                counterBreweryWithWebsite += 1;
            }
        }
        System.out.println("number of breweries with link to the website: " + counterBreweryWithWebsite);
        return counterBreweryWithWebsite;
    }

    // returns number of breweries in Delaware that offer tacos [2d: How many breweries located in Delaware state also offer tacos? ]
    public int numberOfBreweryInDelawareWithTacos(List<Brewery> breweries) throws FileNotFoundException {

        int counterBreweryDelawareTacos = 0;
        for (Brewery brewery: breweries) {
            if (brewery.getProvince().equals("DE")
                    && brewery.getMenus() != null
                    && brewery.getMenus().contains("tacos")){
                counterBreweryDelawareTacos += 1;
            }
        }
        System.out.println("number of breweries located in Delaware that also offer tacos: " + counterBreweryDelawareTacos);
        return counterBreweryDelawareTacos;
    }

    private Map<String, Integer> numberOfBreweryInStateWithWine(List<Brewery> breweries) throws FileNotFoundException {
        List<String> states = listOfStatesWithBreweries(breweries);

        Map<String, Integer> stateBreweriesWithWineNr = new HashMap<>();
        for (String state : states){
            int counterBreweryStateWine = 0;
            for (int listElement = 0; listElement < breweries.size(); listElement++){
                if (breweries.get(listElement).getProvince().equals(state)
                        && breweries.get(listElement).getMenus() != null
                        && breweries.get(listElement).getMenus().contains("wine")){
                    counterBreweryStateWine += 1;
                }
            }
            stateBreweriesWithWineNr.put(state, counterBreweryStateWine);
            //System.out.println("number of breweries with wine in " + state + ": " + counterBreweryStateWine);
        }
        return stateBreweriesWithWineNr;
    }

    // returns map with percentage of breweries that offer wine in each state [2e: What percentage of breweries in each state offers wine?]
    public Map<String, Double> breweriesOffersWinePercentState(List<Brewery> breweries) throws FileNotFoundException {
        Map<String, Integer> breweriesWine = numberOfBreweryInStateWithWine(breweries);

        Map<String, Double> percentageBreweriesWithWineInState = new HashMap<>();
        for (String key : breweriesWine.keySet()){
            int numberInState = numberOfBreweriesInEachStateMap(breweries).get(key);
            double percentage = (( (double) breweriesWine.get(key) / numberInState) * 100);
            percentageBreweriesWithWineInState.put(key, percentage);
        }
        System.out.println(percentageBreweriesWithWineInState);
        return percentageBreweriesWithWineInState;
    }

}
