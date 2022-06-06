package code.eutrustservicesapplication;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Classe Results che gestisce il caricamento degli elementi grafici rappresentanti i risultati
 */
public class Results {
    private Results(){}

    private static final Vector<AnchorPane> resultsPane = new Vector<>();  //Vettore contenente i pannelli rappresentanti i risultati finali

    /**
     * Inizializza il vettore contenente i pannelli che rappresentano i risultati passati come parametri
     * @param final_results Risultati di cui creare la grafica
     * @throws IOException Possono essere lanciate dal costruttore di GraphicResults
     * @throws IllegalParameters Possono essere lanciate dal costruttore di GraphicResults o se il parametro in ingresso è null
     */
    public static void initialize(List<Node> final_results) throws IOException,IllegalParameters
    {
        if(final_results==null) throw new IllegalParameters();
        resultsPane.clear();
        for (Node final_result : final_results) {
            resultsPane.add(new GraphicResult(final_result).pane());
        }
    }

    /**
     * Ritorna una lista non modificabile con i pannelli caratteristici dei risultati
     * @return Lista non modificabile con i pannelli caratteristici dei risultati
     */
    public static List<AnchorPane> resultsPane()
    {
        return Collections.unmodifiableList(resultsPane);
    }
}
