package code.eutrustservicesapplication;

import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Classe Tipo di Servizio che gestisce il caricamento degli elementi grafici rappresentanti il tipo di servizio
 */
public class TypeofService {

    private static final Vector<Pane> typeOfServicesPane=new Vector<>(); //Vettore di Pane rappresentanti i tipi di servizi
    private TypeofService()
    {
    }

    /**
     * Metodo che inizializza tutti gli elementi grafici dei tipi di servizio forniti dall'API e li carica graficamente
     * @param type_of_services Lista contenente l'insieme dei tipi di servizi
     * @throws IOException Possono essere lanciate dal costruttore di LabelType
     * @throws IllegalParameters Possono essere lanciate dal costruttore di LabelType o se il parametro in ingresso è null
     */
    public static void initialize(List<String> type_of_services) throws IOException,IllegalParameters
    {
        if(type_of_services==null) throw new IllegalParameters();
        typeOfServicesPane.clear();
        for (String type_of_service : type_of_services) {
            typeOfServicesPane.add(new LabelType(type_of_service).pane());
        }
    }

    /**
     * Ritorna una lista non modificabile contenente tutti i pannelli rappresentanti ogni tipo di servizio
     * @return Una lista non modificabile contenente tutti i pannelli rappresentanti ogni tipo di servizio
     */
    public static List<Pane> typeOfServicesPane()
    {
        return Collections.unmodifiableList(typeOfServicesPane);
    }
}
