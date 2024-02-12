//package org.example.entity;
//
//import jakarta.ws.rs.NotFoundException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//
//import java.util.Arrays;
//import java.util.List;
//
//public class CityTest {
//
//    @Test
//    void searchWithLessThanTwoCharactersShouldThrowNotFoundException() {
//        Assertions.assertThrows(NotFoundException.class, () -> City.findCities("V"));
//    }
//
//    @Test
//    void searchWithTwoOrMoreCharactersShouldReturnMatchingCities() {
//        List<String> expected = Arrays.asList("Valence", "Vancouver");
//        Assertions.assertEquals(expected, City.findCities("Va"));
//    }
//
//    @Test
//    void searchShouldBeCaseInsensitive() {
//        List<String> expected = List.of("Amsterdam");
//        Assertions.assertEquals(expected, City.findCities("amster"));
//    }
//
//    @Test
//    void searchWithPartOfCityNameShouldReturnMatchingCity() {
//        List<String> expected = List.of("Budapest");
//        Assertions.assertEquals(expected, City.findCities("ape"));
//    }
//
//    @Test
//    void searchWithAsteriskShouldReturnAllCities() {
//        List<String> expected = List.of("Paris", "Budapest", "Skopje", "Rotterdam", "Valence", "Vancouver",
//                "Amsterdam", "Vienne", "Sydney", "New York", "Londres", "Bangkok", "Hong Kong", "Dubaï", "Rome", "Istanbul");
//        Assertions.assertEquals(expected, City.findCities("*"));
//    }
//
//
//}
