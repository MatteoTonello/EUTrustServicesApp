package code.eutrustservicesapplication;

import javafx.scene.image.Image;

import java.util.*;

/**
 * Classe che mette in relazione ogni nazione con l'immagine della relativa bandiera
*/
public class ImagesNations {
    private ImagesNations(){}
    //mappa delle associazioni nazione-immagine, con chiave la stringa nazione
    private static final Map<String, Image> map=new HashMap<>();
    /**
     * Aggiunge alla mappa tutte le associazioni nome nazione e immagine della bandiera corrispondente
     * (tutte le bandiere sono state salvate col nome della sigla dello stato corrispondente)
     * @param countries Lista contenente l'insieme delle nazioni
     * @throws IllegalParameters Possono essere lanciate se il parametro in ingresso è null
    */
    public static void initialize(List<String> countries) throws IllegalParameters{
        if(countries==null) throw new IllegalParameters();
        for (String country : countries) {
            map.put(country, new Image(ImagesNations.class.getResource("items/".concat(country.toLowerCase().concat(".png"))).toExternalForm()));
        }
    }

    /**
     * Ritorna una mappa non modificabile contenente tutte le associazioni nome nazione e immagine della bandiera corrispondente
     * con chiave la stringa rappresentante la sigla della nazione
     * @return Mappa non modificabile contenente tutte le associazioni nome nazione e immagine della bandiera corrispondente
     */
    public static Map<String, Image> map(){return Collections.unmodifiableMap(map);}
}
