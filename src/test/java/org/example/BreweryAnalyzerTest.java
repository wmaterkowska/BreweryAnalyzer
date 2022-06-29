package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

class BreweryAnalyzerTest {


    private List<Brewery> getExampleList(){
        List<Brewery> listBreweriesExample = new ArrayList<>();

        Brewery breweryExample1 = new Brewery();
        breweryExample1.setId("id1");
        breweryExample1.setCity("New York");
        breweryExample1.setProvince("New York");
        breweryExample1.setMenus("");
        breweryExample1.setWebsites("website");
        listBreweriesExample.add(breweryExample1);

        Brewery breweryExample2 = new Brewery();
        breweryExample2.setId("id2");
        breweryExample2.setCity("Los Angeles");
        breweryExample2.setProvince("California");
        breweryExample1.setMenus("");
        breweryExample2.setWebsites("website");
        listBreweriesExample.add(breweryExample2);

        Brewery breweryExample3 = new Brewery();
        breweryExample3.setId("id3");
        breweryExample3.setCity("Los Angeles");
        breweryExample3.setProvince("California");
        breweryExample1.setMenus("");
        breweryExample3.setWebsites("website");
        listBreweriesExample.add(breweryExample3);

        Brewery breweryExample4 = new Brewery();
        breweryExample4.setId("id4");
        breweryExample4.setCity("Dover");
        breweryExample4.setProvince("DE");
        breweryExample4.setMenus("tacos");
        listBreweriesExample.add(breweryExample4);

        Brewery breweryExample5 = new Brewery();
        breweryExample5.setId("id5");
        breweryExample5.setCity("Sacramento");
        breweryExample5.setProvince("California");
        breweryExample5.setMenus("wine");
        listBreweriesExample.add(breweryExample5);

        Brewery breweryExample6 = new Brewery();
        breweryExample6.setId("id6");
        breweryExample6.setCity("Los Angeles");
        breweryExample6.setProvince("California");
        breweryExample6.setMenus("wine");
        listBreweriesExample.add(breweryExample6);

        Brewery breweryExample7 = new Brewery();
        breweryExample7.setId("id7");
        breweryExample7.setCity("New York");
        breweryExample7.setProvince("New York");
        listBreweriesExample.add(breweryExample7);

        return listBreweriesExample;
    }

    @Test
    void numberOfBreweriesInEachStateMap() throws FileNotFoundException {
        // arrange
        List<Brewery> listBreweriesExample = getExampleList();
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        // act
        Map<String, Integer> breweriesState = new HashMap<>();
        breweriesState = analyzer.numberOfBreweriesInEachStateMap(listBreweriesExample);
        // verify
        assert( breweriesState.get("California") == 4);
    }

    @Test
    void topCitiesForBreweries() throws FileNotFoundException {
        // arrange
        List<Brewery> listBreweriesExample = getExampleList();
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        // act
        Map<String, Integer> topCities = new LinkedHashMap<>();
        topCities = analyzer.topCitiesForBreweries(listBreweriesExample);
        // verify
        LinkedHashMap<String, Integer> mapExampleTopCities = new LinkedHashMap<>();
        mapExampleTopCities.put("Los Angeles", 3);
        mapExampleTopCities.put("New York", 2);
        mapExampleTopCities.put("Sacramento", 1);

        System.out.println("test: " + mapExampleTopCities);
        assert (mapExampleTopCities.equals(topCities));
    }

    @Test
    void numberOfBreweryWithWebsite() throws FileNotFoundException {
        // arrange
        List<Brewery> listBreweriesExample = getExampleList();
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        // act
        int number = analyzer.numberOfBreweryWithWebsite(listBreweriesExample);
        // verify
        assert (number == 3);

    }

    @Test
    void numberOfBreweryInDelawareWithTacos() throws FileNotFoundException {
        // arrange
        List<Brewery> listBreweriesExample = getExampleList();
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        // act
        int numberTacos;
        numberTacos = analyzer.numberOfBreweryInDelawareWithTacos(listBreweriesExample);
        // verify
        assert(numberTacos == 1);

    }

    @Test
    void breweriesOffersWinePercentState() throws FileNotFoundException {
        // arrange
        List<Brewery> listBreweriesExample = getExampleList();
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        // act
        Map<String, Double> percentageStateWine;
        percentageStateWine = analyzer.breweriesOffersWinePercentState(listBreweriesExample);
        // verify
        assert(percentageStateWine.get("California") == (double) 1/2 * 100);
    }
}