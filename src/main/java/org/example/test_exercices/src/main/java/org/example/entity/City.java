//package org.example.entity;
//
//import jakarta.ws.rs.NotFoundException;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Nodes.collect;
//
//public class City {
//
//
////    Si le texte de la recherche contient moins de 2 caractères, aucun résultat ne devrait être renvoyé ou Une exception est levée de type NotFoundException.
////    Si le texte de recherche est égal ou supérieur à 2 caractères, il doit renvoyer tous les noms de ville commençant par le texte de recherche exact.
////    Par exemple, pour le texte de recherche "Va", la fonction doit renvoyer Valence et Vancouver
////    La fonctionnalité de recherche doit être insensible à la casse La fonctionnalité de recherche devrait également fonctionner lorsque
////    le texte de recherche n'est qu'une partie d'un nom de ville Par exemple "ape" devrait renvoyer la ville "Budapest" Si le texte de recherche
////    est un « * » (astérisque), il doit renvoyer tous les noms de ville.
////


//    private static List<String> cities = Arrays.asList(
//            "Paris", "Budapest", "Skopje", "Rotterdam", "Valence", "Vancouver",
//            "Amsterdam", "Vienne", "Sydney", "New York", "Londres", "Bangkok", "Hong Kong", "Dubaï", "Rome", "Istanbul");
//
//    public static List<String> findCities(String searchText) {
//        if (searchText.equals("*")) {
//            return cities;
//        }
//        else if (searchText == null || searchText.length() < 2) {
//            throw new NotFoundException("must be higher than 2 characters ! ");
//        } else if (searchText.length() > 2 ) {
//            return cities.stream()
//                    .filter(city -> city.contains(searchText);
//                    .collect(Collectors.toList());
//        }
//        return null;
//    }
//}
