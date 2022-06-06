package code.eutrustservicesapplication;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Classe Nations che gestisce il caricamento degli elementi grafici, rappresentanti le nazioni, e la selezione
 */
public class Nations {
    /**
     * Vettore contenente le nazioni selezionate
     */
    public static final Vector<String> selected_nations = new Vector<>();
    /**
     * Vettore contenenti gli oggetti GraphicNation rappresentanti ogni nazione
     */
    public static final Vector<GraphicNation> nations = new Vector<>();
    private Nations(){}

    /**
     * Inizializza il vettore contenente gli oggetti GraphicNation rappresentanti ogni nazione
     * @param countries Lista contenente le sigle delle nazioni
     * @param name_countries Lista contenente i nomi delle nazioni
     * @throws IOException Possono essere lanciate dal costruttore di GraphicNation
     * @throws IllegalParameters Possono essere lanciate dal costruttore di GraphicNation, se i parametri in ingresso sono null o se hanno dimensioni diverse
     */
    public static void initialize(List<String> name_countries,List<String> countries) throws IOException,IllegalParameters
    {
        if(name_countries==null) throw new IllegalParameters();
        if(countries==null) throw new IllegalParameters();
        if(name_countries.size()!=countries.size()) throw new IllegalParameters();
        nations.clear();
        selected_nations.clear();
        for (int i = 0; i< countries.size(); i++) {
            nations.add(new GraphicNation(name_countries.get(i),countries.get(i)));
        }
    }

}
