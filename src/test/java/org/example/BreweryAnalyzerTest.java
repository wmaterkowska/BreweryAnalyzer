package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class BreweryAnalyzerTest {

    private List<Brewery> getExampleList(){
        List<Brewery> listBreweriesExample = new ArrayList<>();

        Brewery breweryExample1 = new Brewery();
        breweryExample1.setId("id1");
        breweryExample1.setCity("New York");
        breweryExample1.setProvince("New York");
        breweryExample1.setWebsites("website");
        listBreweriesExample.add(breweryExample1);

        Brewery breweryExample2 = Brewery.builder().id("id2").city("Los Angeles").province("California").menus("").websites("website").build();
        listBreweriesExample.add(breweryExample2);

        Brewery breweryExample3 = Brewery.builder().id("id3").city("Los Angeles").province("California").menus("").websites("website").build();
        listBreweriesExample.add(breweryExample3);

        Brewery breweryExample4 = Brewery.builder().id("id4").city("Dover").province("DE").menus("tacos").build();
        listBreweriesExample.add(breweryExample4);

        Brewery breweryExample5 = Brewery.builder().id("id5").city("Sacramento").province("California").menus("wine").build();
        listBreweriesExample.add(breweryExample5);

        Brewery breweryExample6 = Brewery.builder().id("id6").city("Los Angeles").province("California").menus("wine").build();
        listBreweriesExample.add(breweryExample6);

        Brewery breweryExample7 = Brewery.builder().id("id7").city("New York").province("New York").build();
        listBreweriesExample.add(breweryExample7);

        Brewery breweryExample8 = Brewery.builder().id("id8").city("Seattle").province("Washington").build();
        listBreweriesExample.add(breweryExample8);

        return listBreweriesExample;
    }

    @Test
    void numberOfBreweriesInEachStateMap() throws FileNotFoundException {
        // arrange
        List<Brewery> listBreweriesExample = getExampleList();
        BreweryAnalyzer analyzer = new BreweryAnalyzer();
        // act
        Map<String, Integer> breweriesState = new HashMap<>();
        breweriesState = analyzer.numberOfBreweriesInEachStateMap(listBreweriesExample, true);
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

        //System.out.println("test: " + mapExampleTopCities);
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
        assertThat(number).isEqualTo(3) ;

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